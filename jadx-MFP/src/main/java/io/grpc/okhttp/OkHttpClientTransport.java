package io.grpc.okhttp;

import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.common.Constants;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.HttpUrl.Builder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.http.StatusLine;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.InternalChannelz.Security;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.MethodDescriptor.MethodType;
import io.grpc.Status;
import io.grpc.Status.Code;
import io.grpc.StatusException;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.GrpcUtil.Http2Error;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.KeepAliveManager.ClientKeepAlivePinger;
import io.grpc.internal.ManagedClientTransport.Listener;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.TransportTracer.FlowControlReader;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.FrameReader.Handler;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

class OkHttpClientTransport implements ConnectionClientTransport, TransportExceptionHandler {
    private static final OkHttpClientStream[] EMPTY_STREAM_ARRAY = new OkHttpClientStream[0];
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS = buildErrorCodeToStatusMap();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    /* access modifiers changed from: private */
    public final InetSocketAddress address;
    /* access modifiers changed from: private */
    public Attributes attributes = Attributes.EMPTY;
    /* access modifiers changed from: private */
    public ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    /* access modifiers changed from: private */
    public final ConnectionSpec connectionSpec;
    /* access modifiers changed from: private */
    public int connectionUnacknowledgedBytesRead;
    private final String defaultAuthority;
    private boolean enableKeepAlive;
    /* access modifiers changed from: private */
    public final Executor executor;
    /* access modifiers changed from: private */
    public AsyncFrameWriter frameWriter;
    @GuardedBy("lock")
    private boolean goAwaySent;
    @GuardedBy("lock")
    private Status goAwayStatus;
    @GuardedBy("lock")
    private boolean hasStream;
    /* access modifiers changed from: private */
    public HostnameVerifier hostnameVerifier;
    @GuardedBy("lock")
    private final InUseStateAggregator<OkHttpClientStream> inUseState = new InUseStateAggregator<OkHttpClientStream>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            OkHttpClientTransport.this.listener.transportInUse(true);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            OkHttpClientTransport.this.listener.transportInUse(false);
        }
    };
    /* access modifiers changed from: private */
    public final int initialWindowSize;
    /* access modifiers changed from: private */
    public KeepAliveManager keepAliveManager;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    /* access modifiers changed from: private */
    public Listener listener;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId = InternalLogId.allocate(getClass().getName());
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public int maxConcurrentStreams = 0;
    /* access modifiers changed from: private */
    public final int maxInboundMetadataSize;
    private final int maxMessageSize;
    @GuardedBy("lock")
    private int nextStreamId;
    /* access modifiers changed from: private */
    public OutboundFlowController outboundFlow;
    @GuardedBy("lock")
    private LinkedList<OkHttpClientStream> pendingStreams = new LinkedList<>();
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public Http2Ping ping;
    @Nullable
    @VisibleForTesting
    final ProxyParameters proxy;
    private final Random random = new Random();
    private ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public Security securityInfo;
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public SSLSocketFactory sslSocketFactory;
    @GuardedBy("lock")
    private boolean stopped;
    private final Supplier<Stopwatch> stopwatchFactory;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public final Map<Integer, OkHttpClientStream> streams = new HashMap();
    /* access modifiers changed from: private */
    public FrameReader testFrameReader;
    /* access modifiers changed from: private */
    public FrameWriter testFrameWriter;
    /* access modifiers changed from: private */
    public final Runnable tooManyPingsRunnable;
    @GuardedBy("lock")
    private final TransportTracer transportTracer;
    private final String userAgent;

    @VisibleForTesting
    class ClientFrameHandler implements Handler, Runnable {
        boolean firstSettings = true;
        FrameReader frameReader;

        public void ackSettings() {
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        ClientFrameHandler(FrameReader frameReader2) {
            this.frameReader = frameReader2;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            if (!GrpcUtil.IS_RESTRICTED_APPENGINE) {
                Thread.currentThread().setName("OkHttpClientTransport");
            }
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (OkHttpClientTransport.this.keepAliveManager != null) {
                        OkHttpClientTransport.this.keepAliveManager.onDataReceived();
                    }
                } catch (Throwable th) {
                    try {
                        this.frameReader.close();
                    } catch (IOException e) {
                        OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", e);
                    }
                    OkHttpClientTransport.this.listener.transportTerminated();
                    if (!GrpcUtil.IS_RESTRICTED_APPENGINE) {
                        Thread.currentThread().setName(name);
                    }
                    throw th;
                }
            }
            OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withDescription("End of stream or IOException"));
            try {
                this.frameReader.close();
            } catch (IOException e2) {
                OkHttpClientTransport.log.log(Level.INFO, "Exception closing frame reader", e2);
            }
            OkHttpClientTransport.this.listener.transportTerminated();
            if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
                return;
            }
            Thread.currentThread().setName(name);
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            OkHttpClientStream stream = OkHttpClientTransport.this.getStream(i);
            if (stream != null) {
                long j = (long) i2;
                bufferedSource.require(j);
                Buffer buffer = new Buffer();
                buffer.write(bufferedSource.buffer(), j);
                synchronized (OkHttpClientTransport.this.lock) {
                    stream.transportState().transportDataReceived(buffer, z);
                }
            } else if (OkHttpClientTransport.this.mayHaveCreatedStream(i)) {
                OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.INVALID_STREAM);
                bufferedSource.skip((long) i2);
            } else {
                OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
                StringBuilder sb = new StringBuilder();
                sb.append("Received data for unknown stream: ");
                sb.append(i);
                okHttpClientTransport.onError(errorCode, sb.toString());
                return;
            }
            OkHttpClientTransport.access$2312(OkHttpClientTransport.this, i2);
            if (((float) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead) >= ((float) OkHttpClientTransport.this.initialWindowSize) * 0.5f) {
                OkHttpClientTransport.this.frameWriter.windowUpdate(0, (long) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead);
                OkHttpClientTransport.this.connectionUnacknowledgedBytesRead = 0;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x004d A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x009a  */
        /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r5, boolean r6, int r7, int r8, java.util.List<io.grpc.okhttp.internal.framed.Header> r9, io.grpc.okhttp.internal.framed.HeadersMode r10) {
            /*
                r4 = this;
                io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this
                int r5 = r5.maxInboundMetadataSize
                r8 = 1
                r10 = 0
                r0 = 2147483647(0x7fffffff, float:NaN)
                if (r5 == r0) goto L_0x0045
                int r5 = r4.headerBlockSize(r9)
                io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                int r0 = r0.maxInboundMetadataSize
                if (r5 <= r0) goto L_0x0045
                io.grpc.Status r0 = io.grpc.Status.RESOURCE_EXHAUSTED
                java.lang.String r1 = "Response %s metadata larger than %d: %d"
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                if (r6 == 0) goto L_0x0025
                java.lang.String r3 = "trailer"
                goto L_0x0027
            L_0x0025:
                java.lang.String r3 = "header"
            L_0x0027:
                r2[r10] = r3
                io.grpc.okhttp.OkHttpClientTransport r3 = io.grpc.okhttp.OkHttpClientTransport.this
                int r3 = r3.maxInboundMetadataSize
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r2[r8] = r3
                r3 = 2
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r2[r3] = r5
                java.lang.String r5 = java.lang.String.format(r1, r2)
                io.grpc.Status r5 = r0.withDescription(r5)
                goto L_0x0046
            L_0x0045:
                r5 = 0
            L_0x0046:
                io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x00b3 }
                java.util.Map r1 = r1.streams     // Catch:{ all -> 0x00b3 }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x00b3 }
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x00b3 }
                io.grpc.okhttp.OkHttpClientStream r1 = (io.grpc.okhttp.OkHttpClientStream) r1     // Catch:{ all -> 0x00b3 }
                if (r1 != 0) goto L_0x0073
                io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x00b3 }
                boolean r5 = r5.mayHaveCreatedStream(r7)     // Catch:{ all -> 0x00b3 }
                if (r5 == 0) goto L_0x0097
                io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x00b3 }
                io.grpc.okhttp.AsyncFrameWriter r5 = r5.frameWriter     // Catch:{ all -> 0x00b3 }
                io.grpc.okhttp.internal.framed.ErrorCode r6 = io.grpc.okhttp.internal.framed.ErrorCode.INVALID_STREAM     // Catch:{ all -> 0x00b3 }
                r5.rstStream(r7, r6)     // Catch:{ all -> 0x00b3 }
                goto L_0x0096
            L_0x0073:
                if (r5 != 0) goto L_0x007d
                io.grpc.okhttp.OkHttpClientStream$TransportState r5 = r1.transportState()     // Catch:{ all -> 0x00b3 }
                r5.transportHeadersReceived(r9, r6)     // Catch:{ all -> 0x00b3 }
                goto L_0x0096
            L_0x007d:
                if (r6 != 0) goto L_0x008a
                io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x00b3 }
                io.grpc.okhttp.AsyncFrameWriter r6 = r6.frameWriter     // Catch:{ all -> 0x00b3 }
                io.grpc.okhttp.internal.framed.ErrorCode r8 = io.grpc.okhttp.internal.framed.ErrorCode.CANCEL     // Catch:{ all -> 0x00b3 }
                r6.rstStream(r7, r8)     // Catch:{ all -> 0x00b3 }
            L_0x008a:
                io.grpc.okhttp.OkHttpClientStream$TransportState r6 = r1.transportState()     // Catch:{ all -> 0x00b3 }
                io.grpc.Metadata r8 = new io.grpc.Metadata     // Catch:{ all -> 0x00b3 }
                r8.<init>()     // Catch:{ all -> 0x00b3 }
                r6.transportReportStatus(r5, r10, r8)     // Catch:{ all -> 0x00b3 }
            L_0x0096:
                r8 = 0
            L_0x0097:
                monitor-exit(r0)     // Catch:{ all -> 0x00b3 }
                if (r8 == 0) goto L_0x00b2
                io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r6 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>()
                java.lang.String r9 = "Received header for unknown stream: "
                r8.append(r9)
                r8.append(r7)
                java.lang.String r7 = r8.toString()
                r5.onError(r6, r7)
            L_0x00b2:
                return
            L_0x00b3:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00b3 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.headers(boolean, boolean, int, int, java.util.List, io.grpc.okhttp.internal.framed.HeadersMode):void");
        }

        private int headerBlockSize(List<Header> list) {
            long j = 0;
            for (int i = 0; i < list.size(); i++) {
                Header header = (Header) list.get(i);
                j += (long) (header.name.size() + 32 + header.value.size());
            }
            return (int) Math.min(j, 2147483647L);
        }

        public void rstStream(int i, ErrorCode errorCode) {
            Status augmentDescription = OkHttpClientTransport.toGrpcStatus(errorCode).augmentDescription("Rst Stream");
            OkHttpClientTransport.this.finishStream(i, augmentDescription, errorCode == ErrorCode.REFUSED_STREAM ? RpcProgress.REFUSED : RpcProgress.PROCESSED, augmentDescription.getCode() == Code.CANCELLED || augmentDescription.getCode() == Code.DEADLINE_EXCEEDED, null, null);
        }

        public void settings(boolean z, Settings settings) {
            boolean z2;
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpSettingsUtil.isSet(settings, 4)) {
                    OkHttpClientTransport.this.maxConcurrentStreams = OkHttpSettingsUtil.get(settings, 4);
                }
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    z2 = OkHttpClientTransport.this.outboundFlow.initialOutboundWindowSize(OkHttpSettingsUtil.get(settings, 7));
                } else {
                    z2 = false;
                }
                if (this.firstSettings) {
                    OkHttpClientTransport.this.listener.transportReady();
                    this.firstSettings = false;
                }
                OkHttpClientTransport.this.frameWriter.ackSettings(settings);
                if (z2) {
                    OkHttpClientTransport.this.outboundFlow.writeStreams();
                }
                OkHttpClientTransport.this.startPendingStreams();
            }
        }

        public void ping(boolean z, int i, int i2) {
            Http2Ping http2Ping;
            if (!z) {
                OkHttpClientTransport.this.frameWriter.ping(true, i, i2);
                return;
            }
            long j = (((long) i) << 32) | (((long) i2) & 4294967295L);
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpClientTransport.this.ping == null) {
                    OkHttpClientTransport.log.warning("Received unexpected ping ack. No ping outstanding");
                } else if (OkHttpClientTransport.this.ping.payload() == j) {
                    http2Ping = OkHttpClientTransport.this.ping;
                    OkHttpClientTransport.this.ping = null;
                } else {
                    OkHttpClientTransport.log.log(Level.WARNING, String.format("Received unexpected ping ack. Expecting %d, got %d", new Object[]{Long.valueOf(OkHttpClientTransport.this.ping.payload()), Long.valueOf(j)}));
                }
                http2Ping = null;
            }
            if (http2Ping != null) {
                http2Ping.complete();
            }
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            if (errorCode == ErrorCode.ENHANCE_YOUR_CALM) {
                String utf8 = byteString.utf8();
                OkHttpClientTransport.log.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", new Object[]{this, utf8}));
                if ("too_many_pings".equals(utf8)) {
                    OkHttpClientTransport.this.tooManyPingsRunnable.run();
                }
            }
            Status augmentDescription = Http2Error.statusForCode((long) errorCode.httpCode).augmentDescription("Received Goaway");
            if (byteString.size() > 0) {
                augmentDescription = augmentDescription.augmentDescription(byteString.utf8());
            }
            OkHttpClientTransport.this.startGoAway(i, null, augmentDescription);
        }

        public void pushPromise(int i, int i2, List<Header> list) throws IOException {
            OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.PROTOCOL_ERROR);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0063, code lost:
            if (r0 == false) goto L_0x007d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0065, code lost:
            r9 = r7.this$0;
            r10 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR;
            r0 = new java.lang.StringBuilder();
            r0.append("Received window_update for unknown stream: ");
            r0.append(r8);
            io.grpc.okhttp.OkHttpClientTransport.access$2200(r9, r10, r0.toString());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x007d, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void windowUpdate(int r8, long r9) {
            /*
                r7 = this;
                r0 = 0
                int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r2 != 0) goto L_0x0025
                java.lang.String r9 = "Received 0 flow control window increment."
                if (r8 != 0) goto L_0x0012
                io.grpc.okhttp.OkHttpClientTransport r8 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r10 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r8.onError(r10, r9)
                goto L_0x0024
            L_0x0012:
                io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.Status r10 = io.grpc.Status.INTERNAL
                io.grpc.Status r2 = r10.withDescription(r9)
                io.grpc.internal.ClientStreamListener$RpcProgress r3 = io.grpc.internal.ClientStreamListener.RpcProgress.PROCESSED
                r4 = 0
                io.grpc.okhttp.internal.framed.ErrorCode r5 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r6 = 0
                r1 = r8
                r0.finishStream(r1, r2, r3, r4, r5, r6)
            L_0x0024:
                return
            L_0x0025:
                r0 = 0
                io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                if (r8 != 0) goto L_0x003c
                io.grpc.okhttp.OkHttpClientTransport r8 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x007e }
                io.grpc.okhttp.OutboundFlowController r8 = r8.outboundFlow     // Catch:{ all -> 0x007e }
                r0 = 0
                int r10 = (int) r9     // Catch:{ all -> 0x007e }
                r8.windowUpdate(r0, r10)     // Catch:{ all -> 0x007e }
                monitor-exit(r1)     // Catch:{ all -> 0x007e }
                return
            L_0x003c:
                io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x007e }
                java.util.Map r2 = r2.streams     // Catch:{ all -> 0x007e }
                java.lang.Integer r3 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x007e }
                java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x007e }
                io.grpc.okhttp.OkHttpClientStream r2 = (io.grpc.okhttp.OkHttpClientStream) r2     // Catch:{ all -> 0x007e }
                if (r2 == 0) goto L_0x0059
                io.grpc.okhttp.OkHttpClientTransport r3 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x007e }
                io.grpc.okhttp.OutboundFlowController r3 = r3.outboundFlow     // Catch:{ all -> 0x007e }
                int r10 = (int) r9     // Catch:{ all -> 0x007e }
                r3.windowUpdate(r2, r10)     // Catch:{ all -> 0x007e }
                goto L_0x0062
            L_0x0059:
                io.grpc.okhttp.OkHttpClientTransport r9 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x007e }
                boolean r9 = r9.mayHaveCreatedStream(r8)     // Catch:{ all -> 0x007e }
                if (r9 != 0) goto L_0x0062
                r0 = 1
            L_0x0062:
                monitor-exit(r1)     // Catch:{ all -> 0x007e }
                if (r0 == 0) goto L_0x007d
                io.grpc.okhttp.OkHttpClientTransport r9 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r10 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Received window_update for unknown stream: "
                r0.append(r1)
                r0.append(r8)
                java.lang.String r8 = r0.toString()
                r9.onError(r10, r8)
            L_0x007d:
                return
            L_0x007e:
                r8 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x007e }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.windowUpdate(int, long):void");
        }
    }

    static /* synthetic */ int access$2312(OkHttpClientTransport okHttpClientTransport, int i) {
        int i2 = okHttpClientTransport.connectionUnacknowledgedBytesRead + i;
        okHttpClientTransport.connectionUnacknowledgedBytesRead = i2;
        return i2;
    }

    private static Map<ErrorCode, Status> buildErrorCodeToStatusMap() {
        EnumMap enumMap = new EnumMap(ErrorCode.class);
        enumMap.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enumMap.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        enumMap.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        enumMap.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        enumMap.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        enumMap.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        enumMap.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        enumMap.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_CANCELLED));
        enumMap.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        enumMap.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        enumMap.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enumMap.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        return Collections.unmodifiableMap(enumMap);
    }

    OkHttpClientTransport(InetSocketAddress inetSocketAddress, String str, @Nullable String str2, Executor executor2, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier2, ConnectionSpec connectionSpec2, int i, int i2, @Nullable ProxyParameters proxyParameters, Runnable runnable, int i3, TransportTracer transportTracer2) {
        this.address = (InetSocketAddress) Preconditions.checkNotNull(inetSocketAddress, "address");
        this.defaultAuthority = str;
        this.maxMessageSize = i;
        this.initialWindowSize = i2;
        this.executor = (Executor) Preconditions.checkNotNull(executor2, "executor");
        this.serializingExecutor = new SerializingExecutor(executor2);
        this.nextStreamId = 3;
        this.sslSocketFactory = sSLSocketFactory;
        this.hostnameVerifier = hostnameVerifier2;
        this.connectionSpec = (ConnectionSpec) Preconditions.checkNotNull(connectionSpec2, "connectionSpec");
        this.stopwatchFactory = GrpcUtil.STOPWATCH_SUPPLIER;
        this.userAgent = GrpcUtil.getGrpcUserAgent("okhttp", str2);
        this.proxy = proxyParameters;
        this.tooManyPingsRunnable = (Runnable) Preconditions.checkNotNull(runnable, "tooManyPingsRunnable");
        this.maxInboundMetadataSize = i3;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2);
        initTransportTracer();
    }

    private void initTransportTracer() {
        synchronized (this.lock) {
            this.transportTracer.setFlowControlWindowReader(new FlowControlReader() {
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void enableKeepAlive(boolean z, long j, long j2, boolean z2) {
        this.enableKeepAlive = z;
        this.keepAliveTimeNanos = j;
        this.keepAliveTimeoutNanos = j2;
        this.keepAliveWithoutCalls = z2;
    }

    /* access modifiers changed from: private */
    public boolean isForTest() {
        return this.address == null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        if (r1 == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r9.frameWriter.ping(false, (int) (r3 >>> 32), (int) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        r6.addCallback(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ping(io.grpc.internal.ClientTransport.PingCallback r10, java.util.concurrent.Executor r11) {
        /*
            r9 = this;
            io.grpc.okhttp.AsyncFrameWriter r0 = r9.frameWriter
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            com.google.common.base.Preconditions.checkState(r0)
            r3 = 0
            java.lang.Object r0 = r9.lock
            monitor-enter(r0)
            boolean r5 = r9.stopped     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x001e
            java.lang.Throwable r1 = r9.getPingFailure()     // Catch:{ all -> 0x0056 }
            io.grpc.internal.Http2Ping.notifyFailed(r10, r11, r1)     // Catch:{ all -> 0x0056 }
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x001e:
            io.grpc.internal.Http2Ping r5 = r9.ping     // Catch:{ all -> 0x0056 }
            if (r5 == 0) goto L_0x0027
            io.grpc.internal.Http2Ping r1 = r9.ping     // Catch:{ all -> 0x0056 }
            r6 = r1
            r1 = 0
            goto L_0x0044
        L_0x0027:
            java.util.Random r3 = r9.random     // Catch:{ all -> 0x0056 }
            long r3 = r3.nextLong()     // Catch:{ all -> 0x0056 }
            com.google.common.base.Supplier<com.google.common.base.Stopwatch> r5 = r9.stopwatchFactory     // Catch:{ all -> 0x0056 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0056 }
            com.google.common.base.Stopwatch r5 = (com.google.common.base.Stopwatch) r5     // Catch:{ all -> 0x0056 }
            r5.start()     // Catch:{ all -> 0x0056 }
            io.grpc.internal.Http2Ping r6 = new io.grpc.internal.Http2Ping     // Catch:{ all -> 0x0056 }
            r6.<init>(r3, r5)     // Catch:{ all -> 0x0056 }
            r9.ping = r6     // Catch:{ all -> 0x0056 }
            io.grpc.internal.TransportTracer r5 = r9.transportTracer     // Catch:{ all -> 0x0056 }
            r5.reportKeepAliveSent()     // Catch:{ all -> 0x0056 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0052
            io.grpc.okhttp.AsyncFrameWriter r0 = r9.frameWriter
            r1 = 32
            long r7 = r3 >>> r1
            int r1 = (int) r7
            int r4 = (int) r3
            r0.ping(r2, r1, r4)
        L_0x0052:
            r6.addCallback(r10, r11)
            return
        L_0x0056:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ping(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    public OkHttpClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        Object obj;
        Metadata metadata2 = metadata;
        Preconditions.checkNotNull(methodDescriptor, Param.METHOD);
        Preconditions.checkNotNull(metadata2, "headers");
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(callOptions, metadata2);
        Object obj2 = this.lock;
        synchronized (obj2) {
            try {
                r1 = r1;
                obj = obj2;
                OkHttpClientStream okHttpClientStream = new OkHttpClientStream(methodDescriptor, metadata, this.frameWriter, this, this.outboundFlow, this.lock, this.maxMessageSize, this.initialWindowSize, this.defaultAuthority, this.userAgent, newClientContext, this.transportTracer, callOptions);
                return okHttpClientStream;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @GuardedBy("lock")
    public void streamReadyToStart(OkHttpClientStream okHttpClientStream) {
        if (this.goAwayStatus != null) {
            okHttpClientStream.transportState().transportReportStatus(this.goAwayStatus, RpcProgress.REFUSED, true, new Metadata());
        } else if (this.streams.size() >= this.maxConcurrentStreams) {
            this.pendingStreams.add(okHttpClientStream);
            setInUse(okHttpClientStream);
        } else {
            startStream(okHttpClientStream);
        }
    }

    @GuardedBy("lock")
    private void startStream(OkHttpClientStream okHttpClientStream) {
        Preconditions.checkState(okHttpClientStream.id() == -1, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), okHttpClientStream);
        setInUse(okHttpClientStream);
        okHttpClientStream.transportState().start(this.nextStreamId);
        if (!(okHttpClientStream.getType() == MethodType.UNARY || okHttpClientStream.getType() == MethodType.SERVER_STREAMING) || okHttpClientStream.useGet()) {
            this.frameWriter.flush();
        }
        int i = this.nextStreamId;
        if (i >= 2147483645) {
            this.nextStreamId = Integer.MAX_VALUE;
            startGoAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
            return;
        }
        this.nextStreamId = i + 2;
    }

    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public boolean startPendingStreams() {
        boolean z = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            startStream((OkHttpClientStream) this.pendingStreams.poll());
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    @GuardedBy("lock")
    public void removePendingStream(OkHttpClientStream okHttpClientStream) {
        this.pendingStreams.remove(okHttpClientStream);
        maybeClearInUse(okHttpClientStream);
    }

    public Runnable start(Listener listener2) {
        this.listener = (Listener) Preconditions.checkNotNull(listener2, CastExtraArgs.LISTENER);
        if (this.enableKeepAlive) {
            this.scheduler = (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE);
            KeepAliveManager keepAliveManager2 = new KeepAliveManager(new ClientKeepAlivePinger(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            this.keepAliveManager = keepAliveManager2;
            this.keepAliveManager.onTransportStarted();
        }
        this.frameWriter = new AsyncFrameWriter(this, this.serializingExecutor);
        this.outboundFlow = new OutboundFlowController(this, this.frameWriter, this.initialWindowSize);
        this.serializingExecutor.execute(new Runnable() {
            /* JADX WARNING: type inference failed for: r8v0, types: [java.net.Socket] */
            /* JADX WARNING: type inference failed for: r8v1, types: [java.net.Socket, java.lang.Object] */
            /* JADX WARNING: type inference failed for: r5v10, types: [java.net.Socket] */
            /* JADX WARNING: type inference failed for: r5v11, types: [java.net.Socket] */
            /* JADX WARNING: type inference failed for: r8v8 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Unknown variable types count: 3 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r12 = this;
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    boolean r0 = r0.isForTest()
                    r1 = 2147483647(0x7fffffff, float:NaN)
                    r2 = 0
                    if (r0 == 0) goto L_0x0068
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.lang.Runnable r0 = r0.connectingCallback
                    if (r0 == 0) goto L_0x0019
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.lang.Runnable r0 = r0.connectingCallback
                    r0.run()
                L_0x0019:
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r3 = new io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler
                    io.grpc.okhttp.internal.framed.FrameReader r4 = r0.testFrameReader
                    r3.<init>(r4)
                    r0.clientFrameHandler = r3
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.util.concurrent.Executor r0 = r0.executor
                    io.grpc.okhttp.OkHttpClientTransport r3 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r3 = r3.clientFrameHandler
                    r0.execute(r3)
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.lang.Object r0 = r0.lock
                    monitor-enter(r0)
                    io.grpc.okhttp.OkHttpClientTransport r3 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0065 }
                    r3.maxConcurrentStreams = r1     // Catch:{ all -> 0x0065 }
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x0065 }
                    r1.startPendingStreams()     // Catch:{ all -> 0x0065 }
                    monitor-exit(r0)     // Catch:{ all -> 0x0065 }
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.AsyncFrameWriter r0 = r0.frameWriter
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.internal.framed.FrameWriter r1 = r1.testFrameWriter
                    io.grpc.okhttp.OkHttpClientTransport r3 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.net.Socket r3 = r3.socket
                    r0.becomeConnected(r1, r3)
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    com.google.common.util.concurrent.SettableFuture<java.lang.Void> r0 = r0.connectedFuture
                    r0.set(r2)
                    return
                L_0x0065:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0065 }
                    throw r1
                L_0x0068:
                    io.grpc.okhttp.OkHttpClientTransport$3$1 r0 = new io.grpc.okhttp.OkHttpClientTransport$3$1
                    r0.<init>()
                    okio.BufferedSource r0 = okio.Okio.buffer(r0)
                    io.grpc.okhttp.internal.framed.Http2 r3 = new io.grpc.okhttp.internal.framed.Http2
                    r3.<init>()
                    r4 = 1
                    io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.internal.ProxyParameters r5 = r5.proxy     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    if (r5 != 0) goto L_0x0098
                    java.net.Socket r5 = new java.net.Socket     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.InetSocketAddress r6 = r6.address     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.InetAddress r6 = r6.getAddress()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r7 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.InetSocketAddress r7 = r7.address     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    int r7 = r7.getPort()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    r5.<init>(r6, r7)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    r8 = r5
                    goto L_0x00b7
                L_0x0098:
                    io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.InetSocketAddress r6 = r6.address     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r7 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.internal.ProxyParameters r7 = r7.proxy     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.InetSocketAddress r7 = r7.proxyAddress     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r8 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.internal.ProxyParameters r8 = r8.proxy     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.lang.String r8 = r8.username     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r9 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.internal.ProxyParameters r9 = r9.proxy     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.lang.String r9 = r9.password     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.Socket r5 = r5.createHttpProxySocket(r6, r7, r8, r9)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    r8 = r5
                L_0x00b7:
                    io.grpc.okhttp.OkHttpClientTransport r5 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    javax.net.ssl.SSLSocketFactory r5 = r5.sslSocketFactory     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    if (r5 == 0) goto L_0x00e5
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    javax.net.ssl.SSLSocketFactory r6 = r2.sslSocketFactory     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    javax.net.ssl.HostnameVerifier r7 = r2.hostnameVerifier     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.lang.String r9 = r2.getOverridenHost()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    int r10 = r2.getOverridenPort()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.internal.ConnectionSpec r11 = r2.connectionSpec     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    javax.net.ssl.SSLSocket r8 = io.grpc.okhttp.OkHttpTlsUpgrader.upgrade(r6, r7, r8, r9, r10, r11)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    javax.net.ssl.SSLSession r2 = r8.getSession()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                L_0x00e5:
                    r8.setTcpNoDelay(r4)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    okio.Source r5 = okio.Okio.source(r8)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    okio.BufferedSource r0 = okio.Okio.buffer(r5)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    okio.Sink r5 = okio.Okio.sink(r8)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    okio.BufferedSink r5 = okio.Okio.buffer(r5)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Builder r7 = io.grpc.Attributes.newBuilder()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Key<java.net.SocketAddress> r9 = io.grpc.Grpc.TRANSPORT_ATTR_REMOTE_ADDR     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.SocketAddress r10 = r8.getRemoteSocketAddress()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Builder r7 = r7.set(r9, r10)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Key<java.net.SocketAddress> r9 = io.grpc.Grpc.TRANSPORT_ATTR_LOCAL_ADDR     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    java.net.SocketAddress r10 = r8.getLocalSocketAddress()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Builder r7 = r7.set(r9, r10)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Key<javax.net.ssl.SSLSession> r9 = io.grpc.Grpc.TRANSPORT_ATTR_SSL_SESSION     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Builder r7 = r7.set(r9, r2)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes$Key<io.grpc.SecurityLevel> r9 = io.grpc.internal.GrpcAttributes.ATTR_SECURITY_LEVEL     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    if (r2 != 0) goto L_0x011f
                    io.grpc.SecurityLevel r10 = io.grpc.SecurityLevel.NONE     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    goto L_0x0121
                L_0x011f:
                    io.grpc.SecurityLevel r10 = io.grpc.SecurityLevel.PRIVACY_AND_INTEGRITY     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                L_0x0121:
                    io.grpc.Attributes$Builder r7 = r7.set(r9, r10)     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.Attributes r7 = r7.build()     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    r6.attributes = r7     // Catch:{ StatusException -> 0x01c8, Exception -> 0x01a4 }
                    io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r7 = new io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler
                    io.grpc.okhttp.internal.framed.FrameReader r0 = r3.newReader(r0, r4)
                    r7.<init>(r0)
                    r6.clientFrameHandler = r7
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.util.concurrent.Executor r0 = r0.executor
                    io.grpc.okhttp.OkHttpClientTransport r6 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r6 = r6.clientFrameHandler
                    r0.execute(r6)
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.lang.Object r6 = r0.lock
                    monitor-enter(r6)
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x019f }
                    java.lang.String r7 = "socket"
                    java.lang.Object r7 = com.google.common.base.Preconditions.checkNotNull(r8, r7)     // Catch:{ all -> 0x019f }
                    java.net.Socket r7 = (java.net.Socket) r7     // Catch:{ all -> 0x019f }
                    r0.socket = r7     // Catch:{ all -> 0x019f }
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x019f }
                    r0.maxConcurrentStreams = r1     // Catch:{ all -> 0x019f }
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x019f }
                    r0.startPendingStreams()     // Catch:{ all -> 0x019f }
                    if (r2 == 0) goto L_0x0178
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x019f }
                    io.grpc.InternalChannelz$Security r1 = new io.grpc.InternalChannelz$Security     // Catch:{ all -> 0x019f }
                    io.grpc.InternalChannelz$Tls r7 = new io.grpc.InternalChannelz$Tls     // Catch:{ all -> 0x019f }
                    r7.<init>(r2)     // Catch:{ all -> 0x019f }
                    r1.<init>(r7)     // Catch:{ all -> 0x019f }
                    r0.securityInfo = r1     // Catch:{ all -> 0x019f }
                L_0x0178:
                    monitor-exit(r6)     // Catch:{ all -> 0x019f }
                    io.grpc.okhttp.internal.framed.FrameWriter r0 = r3.newWriter(r5, r4)
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.AsyncFrameWriter r1 = r1.frameWriter
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.net.Socket r2 = r2.socket
                    r1.becomeConnected(r0, r2)
                    r0.connectionPreface()     // Catch:{ Exception -> 0x0198 }
                    io.grpc.okhttp.internal.framed.Settings r1 = new io.grpc.okhttp.internal.framed.Settings     // Catch:{ Exception -> 0x0198 }
                    r1.<init>()     // Catch:{ Exception -> 0x0198 }
                    r0.settings(r1)     // Catch:{ Exception -> 0x0198 }
                    return
                L_0x0198:
                    r0 = move-exception
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    r1.onException(r0)
                    return
                L_0x019f:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch:{ all -> 0x019f }
                    throw r0
                L_0x01a2:
                    r1 = move-exception
                    goto L_0x01f3
                L_0x01a4:
                    r1 = move-exception
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x01a2 }
                    r2.onException(r1)     // Catch:{ all -> 0x01a2 }
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r2 = new io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler
                    io.grpc.okhttp.internal.framed.FrameReader r0 = r3.newReader(r0, r4)
                    r2.<init>(r0)
                    r1.clientFrameHandler = r2
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.util.concurrent.Executor r0 = r0.executor
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r1 = r1.clientFrameHandler
                    r0.execute(r1)
                    return
                L_0x01c8:
                    r1 = move-exception
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x01a2 }
                    r5 = 0
                    io.grpc.okhttp.internal.framed.ErrorCode r6 = io.grpc.okhttp.internal.framed.ErrorCode.INTERNAL_ERROR     // Catch:{ all -> 0x01a2 }
                    io.grpc.Status r1 = r1.getStatus()     // Catch:{ all -> 0x01a2 }
                    r2.startGoAway(r5, r6, r1)     // Catch:{ all -> 0x01a2 }
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r2 = new io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler
                    io.grpc.okhttp.internal.framed.FrameReader r0 = r3.newReader(r0, r4)
                    r2.<init>(r0)
                    r1.clientFrameHandler = r2
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.util.concurrent.Executor r0 = r0.executor
                    io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r1 = r1.clientFrameHandler
                    r0.execute(r1)
                    return
                L_0x01f3:
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r5 = new io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler
                    io.grpc.okhttp.internal.framed.FrameReader r0 = r3.newReader(r0, r4)
                    r5.<init>(r0)
                    r2.clientFrameHandler = r5
                    io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                    java.util.concurrent.Executor r0 = r0.executor
                    io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this
                    io.grpc.okhttp.OkHttpClientTransport$ClientFrameHandler r2 = r2.clientFrameHandler
                    r0.execute(r2)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.AnonymousClass3.run():void");
            }
        });
        return null;
    }

    /* access modifiers changed from: private */
    public Socket createHttpProxySocket(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, String str, String str2) throws IOException, StatusException {
        Socket socket2;
        try {
            if (inetSocketAddress2.getAddress() != null) {
                socket2 = new Socket(inetSocketAddress2.getAddress(), inetSocketAddress2.getPort());
            } else {
                socket2 = new Socket(inetSocketAddress2.getHostName(), inetSocketAddress2.getPort());
            }
            socket2.setTcpNoDelay(true);
            Source source = Okio.source(socket2);
            BufferedSink buffer = Okio.buffer(Okio.sink(socket2));
            Request createHttpProxyRequest = createHttpProxyRequest(inetSocketAddress, str, str2);
            HttpUrl httpUrl = createHttpProxyRequest.httpUrl();
            buffer.writeUtf8(String.format("CONNECT %s:%d HTTP/1.1", new Object[]{httpUrl.host(), Integer.valueOf(httpUrl.port())})).writeUtf8("\r\n");
            int size = createHttpProxyRequest.headers().size();
            for (int i = 0; i < size; i++) {
                buffer.writeUtf8(createHttpProxyRequest.headers().name(i)).writeUtf8(": ").writeUtf8(createHttpProxyRequest.headers().value(i)).writeUtf8("\r\n");
            }
            buffer.writeUtf8("\r\n");
            buffer.flush();
            StatusLine parse = StatusLine.parse(readUtf8LineStrictUnbuffered(source));
            while (!readUtf8LineStrictUnbuffered(source).equals("")) {
            }
            if (parse.code >= 200 && parse.code < 300) {
                return socket2;
            }
            Buffer buffer2 = new Buffer();
            try {
                socket2.shutdownOutput();
                source.read(buffer2, 1024);
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to read body: ");
                sb.append(e.toString());
                buffer2.writeUtf8(sb.toString());
            }
            try {
                socket2.close();
            } catch (IOException unused) {
            }
            throw Status.UNAVAILABLE.withDescription(String.format("Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s", new Object[]{Integer.valueOf(parse.code), parse.message, buffer2.readUtf8()})).asException();
        } catch (IOException e2) {
            throw Status.UNAVAILABLE.withDescription("Failed trying to connect with proxy").withCause(e2).asException();
        }
    }

    private Request createHttpProxyRequest(InetSocketAddress inetSocketAddress, String str, String str2) {
        HttpUrl build = new Builder().scheme(Constants.HTTPS).host(inetSocketAddress.getHostName()).port(inetSocketAddress.getPort()).build();
        Request.Builder url = new Request.Builder().url(build);
        String str3 = HttpHeaders.HOST;
        StringBuilder sb = new StringBuilder();
        sb.append(build.host());
        sb.append(":");
        sb.append(build.port());
        Request.Builder header = url.header(str3, sb.toString()).header("User-Agent", this.userAgent);
        if (!(str == null || str2 == null)) {
            header.header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(str, str2));
        }
        return header.build();
    }

    private static String readUtf8LineStrictUnbuffered(Source source) throws IOException {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 1) != -1) {
            if (buffer.getByte(buffer.size() - 1) == 10) {
                return buffer.readUtf8LineStrict();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\\n not found: ");
        sb.append(buffer.readByteString().hex());
        throw new EOFException(sb.toString());
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("address", (Object) this.address).toString();
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public String getOverridenHost() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getHost() != null) {
            return authorityToUri.getHost();
        }
        return this.defaultAuthority;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public int getOverridenPort() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getPort() != -1) {
            return authorityToUri.getPort();
        }
        return this.address.getPort();
    }

    public void shutdown(Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(this.goAwayStatus);
                stopIfNecessary();
            }
        }
    }

    public void shutdownNow(Status status) {
        shutdown(status);
        synchronized (this.lock) {
            Iterator it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                it.remove();
                ((OkHttpClientStream) entry.getValue()).transportState().transportReportStatus(status, false, new Metadata());
                maybeClearInUse((OkHttpClientStream) entry.getValue());
            }
            Iterator it2 = this.pendingStreams.iterator();
            while (it2.hasNext()) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) it2.next();
                okHttpClientStream.transportState().transportReportStatus(status, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    /* access modifiers changed from: 0000 */
    public OkHttpClientStream[] getActiveStreams() {
        OkHttpClientStream[] okHttpClientStreamArr;
        synchronized (this.lock) {
            okHttpClientStreamArr = (OkHttpClientStream[]) this.streams.values().toArray(EMPTY_STREAM_ARRAY);
        }
        return okHttpClientStreamArr;
    }

    public void onException(Throwable th) {
        Preconditions.checkNotNull(th, "failureCause");
        startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withCause(th));
    }

    /* access modifiers changed from: private */
    public void onError(ErrorCode errorCode, String str) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(str));
    }

    /* access modifiers changed from: private */
    public void startGoAway(int i, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (((Integer) entry.getKey()).intValue() > i) {
                    it.remove();
                    ((OkHttpClientStream) entry.getValue()).transportState().transportReportStatus(status, RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse((OkHttpClientStream) entry.getValue());
                }
            }
            Iterator it2 = this.pendingStreams.iterator();
            while (it2.hasNext()) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) it2.next();
                okHttpClientStream.transportState().transportReportStatus(status, RpcProgress.REFUSED, true, new Metadata());
                maybeClearInUse(okHttpClientStream);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    /* access modifiers changed from: 0000 */
    public void finishStream(int i, @Nullable Status status, RpcProgress rpcProgress, boolean z, @Nullable ErrorCode errorCode, @Nullable Metadata metadata) {
        synchronized (this.lock) {
            OkHttpClientStream okHttpClientStream = (OkHttpClientStream) this.streams.remove(Integer.valueOf(i));
            if (okHttpClientStream != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                }
                if (status != null) {
                    TransportState transportState = okHttpClientStream.transportState();
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportState.transportReportStatus(status, rpcProgress, z, metadata);
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                    maybeClearInUse(okHttpClientStream);
                }
            }
        }
    }

    @GuardedBy("lock")
    private void stopIfNecessary() {
        if (this.goAwayStatus != null && this.streams.isEmpty() && this.pendingStreams.isEmpty() && !this.stopped) {
            this.stopped = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportTermination();
                this.scheduler = (ScheduledExecutorService) SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.scheduler);
            }
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                http2Ping.failed(getPingFailure());
                this.ping = null;
            }
            if (!this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
            }
            this.frameWriter.close();
        }
    }

    @GuardedBy("lock")
    private void maybeClearInUse(OkHttpClientStream okHttpClientStream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportIdle();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, false);
        }
    }

    @GuardedBy("lock")
    private void setInUse(OkHttpClientStream okHttpClientStream) {
        if (!this.hasStream) {
            this.hasStream = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportActive();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, true);
        }
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            if (this.goAwayStatus != null) {
                StatusException asException = this.goAwayStatus.asException();
                return asException;
            }
            StatusException asException2 = Status.UNAVAILABLE.withDescription("Connection closed").asException();
            return asException2;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean mayHaveCreatedStream(int i) {
        boolean z;
        synchronized (this.lock) {
            z = true;
            if (i >= this.nextStreamId || (i & 1) != 1) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public OkHttpClientStream getStream(int i) {
        OkHttpClientStream okHttpClientStream;
        synchronized (this.lock) {
            okHttpClientStream = (OkHttpClientStream) this.streams.get(Integer.valueOf(i));
        }
        return okHttpClientStream;
    }

    @VisibleForTesting
    static Status toGrpcStatus(ErrorCode errorCode) {
        Status status = (Status) ERROR_CODE_TO_STATUS.get(errorCode);
        if (status != null) {
            return status;
        }
        Status status2 = Status.UNKNOWN;
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown http2 error code: ");
        sb.append(errorCode.httpCode);
        return status2.withDescription(sb.toString());
    }
}
