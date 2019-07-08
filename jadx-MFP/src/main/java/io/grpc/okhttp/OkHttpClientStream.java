package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.MethodDescriptor.MethodType;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import okio.Buffer;

class OkHttpClientStream extends AbstractClientStream {
    /* access modifiers changed from: private */
    public static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    /* access modifiers changed from: private */
    public String authority;
    /* access modifiers changed from: private */
    public volatile int id = -1;
    /* access modifiers changed from: private */
    public final MethodDescriptor<?, ?> method;
    private Object outboundFlowState;
    private final Sink sink = new Sink();
    /* access modifiers changed from: private */
    public final TransportState state;
    /* access modifiers changed from: private */
    public final StatsTraceContext statsTraceCtx;
    /* access modifiers changed from: private */
    public boolean useGet = false;
    /* access modifiers changed from: private */
    public final String userAgent;

    class Sink implements Sink {
        Sink() {
        }

        public void writeHeaders(Metadata metadata, byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            sb.append("/");
            sb.append(OkHttpClientStream.this.method.getFullMethodName());
            String sb2 = sb.toString();
            if (bArr != null) {
                OkHttpClientStream.this.useGet = true;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append("?");
                sb3.append(BaseEncoding.base64().encode(bArr));
                sb2 = sb3.toString();
            }
            synchronized (OkHttpClientStream.this.state.lock) {
                OkHttpClientStream.this.state.streamReady(metadata, sb2);
            }
        }

        public void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            Buffer buffer;
            if (writableBuffer == null) {
                buffer = OkHttpClientStream.EMPTY_BUFFER;
            } else {
                buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    OkHttpClientStream.this.onSendingBytes(size);
                }
            }
            synchronized (OkHttpClientStream.this.state.lock) {
                OkHttpClientStream.this.state.sendBuffer(buffer, z, z2);
                OkHttpClientStream.this.getTransportTracer().reportMessageSent(i);
            }
        }

        public void request(int i) {
            synchronized (OkHttpClientStream.this.state.lock) {
                OkHttpClientStream.this.state.requestMessagesFromDeframer(i);
            }
        }

        public void cancel(Status status) {
            synchronized (OkHttpClientStream.this.state.lock) {
                OkHttpClientStream.this.state.cancel(status, true, null);
            }
        }
    }

    class TransportState extends Http2ClientStreamTransportState {
        @GuardedBy("lock")
        private boolean canStart = true;
        @GuardedBy("lock")
        private boolean cancelSent = false;
        private boolean flushPendingData = false;
        @GuardedBy("lock")
        private final AsyncFrameWriter frameWriter;
        private final int initialWindowSize;
        /* access modifiers changed from: private */
        public final Object lock;
        @GuardedBy("lock")
        private final OutboundFlowController outboundFlow;
        @GuardedBy("lock")
        private Buffer pendingData = new Buffer();
        private boolean pendingDataHasEndOfStream = false;
        @GuardedBy("lock")
        private int processedWindow;
        @GuardedBy("lock")
        private List<Header> requestHeaders;
        @GuardedBy("lock")
        private final OkHttpClientTransport transport;
        @GuardedBy("lock")
        private int window;

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, AsyncFrameWriter asyncFrameWriter, OutboundFlowController outboundFlowController, OkHttpClientTransport okHttpClientTransport, int i2) {
            super(i, statsTraceContext, OkHttpClientStream.this.getTransportTracer());
            this.lock = Preconditions.checkNotNull(obj, "lock");
            this.frameWriter = asyncFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.transport = okHttpClientTransport;
            this.window = i2;
            this.processedWindow = i2;
            this.initialWindowSize = i2;
        }

        @GuardedBy("lock")
        public void start(int i) {
            Preconditions.checkState(OkHttpClientStream.this.id == -1, "the stream has been started with id %s", i);
            OkHttpClientStream.this.id = i;
            OkHttpClientStream.this.state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(OkHttpClientStream.this.useGet, false, OkHttpClientStream.this.id, 0, this.requestHeaders);
                OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, OkHttpClientStream.this.id, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        /* access modifiers changed from: protected */
        @GuardedBy("lock")
        public void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        /* access modifiers changed from: protected */
        @GuardedBy("lock")
        public void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            cancel(status, z, metadata);
        }

        @GuardedBy("lock")
        public void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        @GuardedBy("lock")
        public void bytesRead(int i) {
            this.processedWindow -= i;
            int i2 = this.processedWindow;
            float f = (float) i2;
            int i3 = this.initialWindowSize;
            if (f <= ((float) i3) * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(OkHttpClientStream.this.id(), (long) i4);
            }
        }

        @GuardedBy("lock")
        public void deframerClosed(boolean z) {
            onEndOfStream();
            super.deframerClosed(z);
        }

        @GuardedBy("lock")
        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        @GuardedBy("lock")
        public void transportHeadersReceived(List<Header> list, boolean z) {
            if (z) {
                transportTrailersReceived(Utils.convertTrailers(list));
            } else {
                transportHeadersReceived(Utils.convertHeaders(list));
            }
        }

        @GuardedBy("lock")
        public void transportDataReceived(Buffer buffer, boolean z) {
            this.window -= (int) buffer.size();
            if (this.window < 0) {
                this.frameWriter.rstStream(OkHttpClientStream.this.id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(OkHttpClientStream.this.id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), RpcProgress.PROCESSED, false, null, null);
                return;
            }
            super.transportDataReceived(new OkHttpReadableBuffer(buffer), z);
        }

        @GuardedBy("lock")
        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(OkHttpClientStream.this.id(), null, RpcProgress.PROCESSED, false, ErrorCode.CANCEL, null);
            } else {
                this.transport.finishStream(OkHttpClientStream.this.id(), null, RpcProgress.PROCESSED, false, null, null);
            }
        }

        /* access modifiers changed from: private */
        @GuardedBy("lock")
        public void cancel(Status status, boolean z, Metadata metadata) {
            if (!this.cancelSent) {
                this.cancelSent = true;
                if (this.canStart) {
                    this.transport.removePendingStream(OkHttpClientStream.this);
                    this.requestHeaders = null;
                    this.pendingData.clear();
                    this.canStart = false;
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportReportStatus(status, true, metadata);
                } else {
                    this.transport.finishStream(OkHttpClientStream.this.id(), status, RpcProgress.PROCESSED, z, ErrorCode.CANCEL, metadata);
                }
            }
        }

        /* access modifiers changed from: private */
        @GuardedBy("lock")
        public void sendBuffer(Buffer buffer, boolean z, boolean z2) {
            if (!this.cancelSent) {
                if (this.canStart) {
                    this.pendingData.write(buffer, (long) ((int) buffer.size()));
                    this.pendingDataHasEndOfStream |= z;
                    this.flushPendingData |= z2;
                } else {
                    Preconditions.checkState(OkHttpClientStream.this.id() != -1, "streamId should be set");
                    this.outboundFlow.data(z, OkHttpClientStream.this.id(), buffer, z2);
                }
            }
        }

        /* access modifiers changed from: private */
        @GuardedBy("lock")
        public void streamReady(Metadata metadata, String str) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, str, OkHttpClientStream.this.authority, OkHttpClientStream.this.userAgent, OkHttpClientStream.this.useGet);
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }
    }

    OkHttpClientStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, AsyncFrameWriter asyncFrameWriter, OkHttpClientTransport okHttpClientTransport, OutboundFlowController outboundFlowController, Object obj, int i, int i2, String str, String str2, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext, transportTracer, metadata, callOptions, methodDescriptor.isSafe());
        StatsTraceContext statsTraceContext2 = statsTraceContext;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext2, "statsTraceCtx");
        this.method = methodDescriptor;
        this.authority = str;
        this.userAgent = str2;
        this.attributes = okHttpClientTransport.getAttributes();
        TransportState transportState = new TransportState(i, statsTraceContext2, obj, asyncFrameWriter, outboundFlowController, okHttpClientTransport, i2);
        this.state = transportState;
    }

    /* access modifiers changed from: protected */
    public TransportState transportState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    public MethodType getType() {
        return this.method.getType();
    }

    public int id() {
        return this.id;
    }

    /* access modifiers changed from: 0000 */
    public boolean useGet() {
        return this.useGet;
    }

    public void setAuthority(String str) {
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
    }

    /* access modifiers changed from: 0000 */
    public void setOutboundFlowState(Object obj) {
        this.outboundFlowState = obj;
    }

    /* access modifiers changed from: 0000 */
    public Object getOutboundFlowState() {
        return this.outboundFlowState;
    }
}
