package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerStreamTracer.Factory;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport.PingCallback;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.ManagedClientTransport.Listener;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class InProcessTransport implements ConnectionClientTransport, ServerTransport {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    private final Attributes attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).build();
    private final String authority;
    private final int clientMaxInboundMetadataSize;
    /* access modifiers changed from: private */
    public Listener clientTransportListener;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public final InUseStateAggregator<InProcessStream> inUseState = new InUseStateAggregator<InProcessStream>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            InProcessTransport.this.clientTransportListener.transportInUse(true);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            InProcessTransport.this.clientTransportListener.transportInUse(false);
        }
    };
    private final InternalLogId logId = InternalLogId.allocate(getClass().getName());
    /* access modifiers changed from: private */
    public final String name;
    private int serverMaxInboundMetadataSize;
    private ScheduledExecutorService serverScheduler;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    /* access modifiers changed from: private */
    public Attributes serverStreamAttributes;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public List<Factory> serverStreamTracerFactories;
    /* access modifiers changed from: private */
    public ServerTransportListener serverTransportListener;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public boolean shutdown;
    @GuardedBy("this")
    private Status shutdownStatus;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public Set<InProcessStream> streams = new HashSet();
    @GuardedBy("this")
    private boolean terminated;
    private final String userAgent;

    private class InProcessStream {
        /* access modifiers changed from: private */
        public volatile String authority;
        private final CallOptions callOptions;
        /* access modifiers changed from: private */
        public final InProcessClientStream clientStream;
        /* access modifiers changed from: private */
        public final Metadata headers;
        /* access modifiers changed from: private */
        public final MethodDescriptor<?, ?> method;
        /* access modifiers changed from: private */
        public final InProcessServerStream serverStream;

        private class InProcessClientStream implements ClientStream {
            final CallOptions callOptions;
            @GuardedBy("this")
            private boolean closed;
            @GuardedBy("this")
            private int outboundSeqNo;
            @GuardedBy("this")
            private boolean serverNotifyHalfClose;
            @GuardedBy("this")
            private ArrayDeque<MessageProducer> serverReceiveQueue = new ArrayDeque<>();
            @GuardedBy("this")
            private int serverRequested;
            @GuardedBy("this")
            private ServerStreamListener serverStreamListener;
            final StatsTraceContext statsTraceCtx;

            public void flush() {
            }

            public void setCompressor(Compressor compressor) {
            }

            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            public void setFullStreamDecompression(boolean z) {
            }

            public void setMaxInboundMessageSize(int i) {
            }

            public void setMaxOutboundMessageSize(int i) {
            }

            InProcessClientStream(CallOptions callOptions2, Metadata metadata) {
                this.callOptions = callOptions2;
                this.statsTraceCtx = StatsTraceContext.newClientContext(callOptions2, metadata);
            }

            public void request(int i) {
                if (InProcessStream.this.serverStream.clientRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.serverStreamListener.onReady();
                        }
                    }
                }
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x0054, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean serverRequested(int r5) {
                /*
                    r4 = this;
                    monitor-enter(r4)
                    boolean r0 = r4.closed     // Catch:{ all -> 0x0055 }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r4)
                    return r1
                L_0x0008:
                    int r0 = r4.serverRequested     // Catch:{ all -> 0x0055 }
                    r2 = 1
                    if (r0 <= 0) goto L_0x000f
                    r0 = 1
                    goto L_0x0010
                L_0x000f:
                    r0 = 0
                L_0x0010:
                    int r3 = r4.serverRequested     // Catch:{ all -> 0x0055 }
                    int r3 = r3 + r5
                    r4.serverRequested = r3     // Catch:{ all -> 0x0055 }
                L_0x0015:
                    int r5 = r4.serverRequested     // Catch:{ all -> 0x0055 }
                    if (r5 <= 0) goto L_0x0034
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r5 = r4.serverReceiveQueue     // Catch:{ all -> 0x0055 }
                    boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0055 }
                    if (r5 != 0) goto L_0x0034
                    int r5 = r4.serverRequested     // Catch:{ all -> 0x0055 }
                    int r5 = r5 - r2
                    r4.serverRequested = r5     // Catch:{ all -> 0x0055 }
                    io.grpc.internal.ServerStreamListener r5 = r4.serverStreamListener     // Catch:{ all -> 0x0055 }
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r3 = r4.serverReceiveQueue     // Catch:{ all -> 0x0055 }
                    java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0055 }
                    io.grpc.internal.StreamListener$MessageProducer r3 = (io.grpc.internal.StreamListener.MessageProducer) r3     // Catch:{ all -> 0x0055 }
                    r5.messagesAvailable(r3)     // Catch:{ all -> 0x0055 }
                    goto L_0x0015
                L_0x0034:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r5 = r4.serverReceiveQueue     // Catch:{ all -> 0x0055 }
                    boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0055 }
                    if (r5 == 0) goto L_0x0047
                    boolean r5 = r4.serverNotifyHalfClose     // Catch:{ all -> 0x0055 }
                    if (r5 == 0) goto L_0x0047
                    r4.serverNotifyHalfClose = r1     // Catch:{ all -> 0x0055 }
                    io.grpc.internal.ServerStreamListener r5 = r4.serverStreamListener     // Catch:{ all -> 0x0055 }
                    r5.halfClosed()     // Catch:{ all -> 0x0055 }
                L_0x0047:
                    int r5 = r4.serverRequested     // Catch:{ all -> 0x0055 }
                    if (r5 <= 0) goto L_0x004d
                    r5 = 1
                    goto L_0x004e
                L_0x004d:
                    r5 = 0
                L_0x004e:
                    if (r0 != 0) goto L_0x0053
                    if (r5 == 0) goto L_0x0053
                    r1 = 1
                L_0x0053:
                    monitor-exit(r4)
                    return r1
                L_0x0055:
                    r5 = move-exception
                    monitor-exit(r4)
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.serverRequested(int):boolean");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0059, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void writeMessage(java.io.InputStream r9) {
                /*
                    r8 = this;
                    monitor-enter(r8)
                    boolean r0 = r8.closed     // Catch:{ all -> 0x005a }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r8)
                    return
                L_0x0007:
                    io.grpc.internal.StatsTraceContext r0 = r8.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r0.outboundMessage(r1)     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r2 = r8.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r3 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r4 = -1
                    r6 = -1
                    r2.outboundMessageSent(r3, r4, r6)     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream r0 = r0.serverStream     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r0.inboundMessage(r1)     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream r0 = r0.serverStream     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r1 = r0.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r2 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r3 = -1
                    r5 = -1
                    r1.inboundMessageRead(r2, r3, r5)     // Catch:{ all -> 0x005a }
                    int r0 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    int r0 = r0 + 1
                    r8.outboundSeqNo = r0     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$SingleMessageProducer r0 = new io.grpc.inprocess.InProcessTransport$SingleMessageProducer     // Catch:{ all -> 0x005a }
                    r1 = 0
                    r0.<init>(r9)     // Catch:{ all -> 0x005a }
                    int r9 = r8.serverRequested     // Catch:{ all -> 0x005a }
                    if (r9 <= 0) goto L_0x0053
                    int r9 = r8.serverRequested     // Catch:{ all -> 0x005a }
                    int r9 = r9 + -1
                    r8.serverRequested = r9     // Catch:{ all -> 0x005a }
                    io.grpc.internal.ServerStreamListener r9 = r8.serverStreamListener     // Catch:{ all -> 0x005a }
                    r9.messagesAvailable(r0)     // Catch:{ all -> 0x005a }
                    goto L_0x0058
                L_0x0053:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r9 = r8.serverReceiveQueue     // Catch:{ all -> 0x005a }
                    r9.add(r0)     // Catch:{ all -> 0x005a }
                L_0x0058:
                    monitor-exit(r8)
                    return
                L_0x005a:
                    r9 = move-exception
                    monitor-exit(r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.writeMessage(java.io.InputStream):void");
            }

            public void cancel(Status status) {
                Status access$1800 = InProcessTransport.stripCause(status);
                if (internalCancel(access$1800, access$1800)) {
                    InProcessStream.this.serverStream.clientCancelled(status);
                    InProcessStream.this.streamClosed();
                }
            }

            private synchronized boolean internalCancel(Status status, Status status2) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    MessageProducer messageProducer = (MessageProducer) this.serverReceiveQueue.poll();
                    if (messageProducer != null) {
                        while (true) {
                            InputStream next = messageProducer.next();
                            if (next != null) {
                                try {
                                    next.close();
                                } catch (Throwable th) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", th);
                                }
                            }
                        }
                    } else {
                        InProcessStream.this.serverStream.statsTraceCtx.streamClosed(status2);
                        this.serverStreamListener.closed(status);
                        return true;
                    }
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void halfClose() {
                /*
                    r1 = this;
                    monitor-enter(r1)
                    boolean r0 = r1.closed     // Catch:{ all -> 0x001a }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r1)
                    return
                L_0x0007:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r0 = r1.serverReceiveQueue     // Catch:{ all -> 0x001a }
                    boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x001a }
                    if (r0 == 0) goto L_0x0015
                    io.grpc.internal.ServerStreamListener r0 = r1.serverStreamListener     // Catch:{ all -> 0x001a }
                    r0.halfClosed()     // Catch:{ all -> 0x001a }
                    goto L_0x0018
                L_0x0015:
                    r0 = 1
                    r1.serverNotifyHalfClose = r0     // Catch:{ all -> 0x001a }
                L_0x0018:
                    monitor-exit(r1)
                    return
                L_0x001a:
                    r0 = move-exception
                    monitor-exit(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessClientStream.halfClose():void");
            }

            public void setAuthority(String str) {
                InProcessStream.this.authority = str;
            }

            public void start(ClientStreamListener clientStreamListener) {
                InProcessStream.this.serverStream.setListener(clientStreamListener);
                synchronized (InProcessTransport.this) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    InProcessTransport.this.streams.add(InProcessStream.this);
                    if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                        InProcessTransport.this.inUseState.updateObjectInUse(InProcessStream.this, true);
                    }
                    InProcessTransport.this.serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
            }
        }

        private class InProcessServerStream implements ServerStream {
            @GuardedBy("this")
            private Status clientNotifyStatus;
            @GuardedBy("this")
            private Metadata clientNotifyTrailers;
            @GuardedBy("this")
            private ArrayDeque<MessageProducer> clientReceiveQueue = new ArrayDeque<>();
            @GuardedBy("this")
            private int clientRequested;
            @GuardedBy("this")
            private ClientStreamListener clientStreamListener;
            @GuardedBy("this")
            private boolean closed;
            @GuardedBy("this")
            private int outboundSeqNo;
            final StatsTraceContext statsTraceCtx;

            public void flush() {
            }

            public void setCompressor(Compressor compressor) {
            }

            InProcessServerStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata) {
                this.statsTraceCtx = StatsTraceContext.newServerContext(InProcessTransport.this.serverStreamTracerFactories, methodDescriptor.getFullMethodName(), metadata);
            }

            /* access modifiers changed from: private */
            public synchronized void setListener(ClientStreamListener clientStreamListener2) {
                this.clientStreamListener = clientStreamListener2;
            }

            public void request(int i) {
                if (InProcessStream.this.clientStream.serverRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.clientStreamListener.onReady();
                        }
                    }
                }
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x006b, code lost:
                return r1;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized boolean clientRequested(int r6) {
                /*
                    r5 = this;
                    monitor-enter(r5)
                    boolean r0 = r5.closed     // Catch:{ all -> 0x006c }
                    r1 = 0
                    if (r0 == 0) goto L_0x0008
                    monitor-exit(r5)
                    return r1
                L_0x0008:
                    int r0 = r5.clientRequested     // Catch:{ all -> 0x006c }
                    r2 = 1
                    if (r0 <= 0) goto L_0x000f
                    r0 = 1
                    goto L_0x0010
                L_0x000f:
                    r0 = 0
                L_0x0010:
                    int r3 = r5.clientRequested     // Catch:{ all -> 0x006c }
                    int r3 = r3 + r6
                    r5.clientRequested = r3     // Catch:{ all -> 0x006c }
                L_0x0015:
                    int r6 = r5.clientRequested     // Catch:{ all -> 0x006c }
                    if (r6 <= 0) goto L_0x0034
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r6 = r5.clientReceiveQueue     // Catch:{ all -> 0x006c }
                    boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x006c }
                    if (r6 != 0) goto L_0x0034
                    int r6 = r5.clientRequested     // Catch:{ all -> 0x006c }
                    int r6 = r6 - r2
                    r5.clientRequested = r6     // Catch:{ all -> 0x006c }
                    io.grpc.internal.ClientStreamListener r6 = r5.clientStreamListener     // Catch:{ all -> 0x006c }
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r3 = r5.clientReceiveQueue     // Catch:{ all -> 0x006c }
                    java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x006c }
                    io.grpc.internal.StreamListener$MessageProducer r3 = (io.grpc.internal.StreamListener.MessageProducer) r3     // Catch:{ all -> 0x006c }
                    r6.messagesAvailable(r3)     // Catch:{ all -> 0x006c }
                    goto L_0x0015
                L_0x0034:
                    boolean r6 = r5.closed     // Catch:{ all -> 0x006c }
                    if (r6 == 0) goto L_0x003a
                    monitor-exit(r5)
                    return r1
                L_0x003a:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r6 = r5.clientReceiveQueue     // Catch:{ all -> 0x006c }
                    boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x006c }
                    if (r6 == 0) goto L_0x005e
                    io.grpc.Status r6 = r5.clientNotifyStatus     // Catch:{ all -> 0x006c }
                    if (r6 == 0) goto L_0x005e
                    r5.closed = r2     // Catch:{ all -> 0x006c }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r6 = io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x006c }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r6 = r6.clientStream     // Catch:{ all -> 0x006c }
                    io.grpc.internal.StatsTraceContext r6 = r6.statsTraceCtx     // Catch:{ all -> 0x006c }
                    io.grpc.Status r3 = r5.clientNotifyStatus     // Catch:{ all -> 0x006c }
                    r6.streamClosed(r3)     // Catch:{ all -> 0x006c }
                    io.grpc.internal.ClientStreamListener r6 = r5.clientStreamListener     // Catch:{ all -> 0x006c }
                    io.grpc.Status r3 = r5.clientNotifyStatus     // Catch:{ all -> 0x006c }
                    io.grpc.Metadata r4 = r5.clientNotifyTrailers     // Catch:{ all -> 0x006c }
                    r6.closed(r3, r4)     // Catch:{ all -> 0x006c }
                L_0x005e:
                    int r6 = r5.clientRequested     // Catch:{ all -> 0x006c }
                    if (r6 <= 0) goto L_0x0064
                    r6 = 1
                    goto L_0x0065
                L_0x0064:
                    r6 = 0
                L_0x0065:
                    if (r0 != 0) goto L_0x006a
                    if (r6 == 0) goto L_0x006a
                    r1 = 1
                L_0x006a:
                    monitor-exit(r5)
                    return r1
                L_0x006c:
                    r6 = move-exception
                    monitor-exit(r5)
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.clientRequested(int):boolean");
            }

            /* access modifiers changed from: private */
            public void clientCancelled(Status status) {
                internalCancel(status);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0059, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public synchronized void writeMessage(java.io.InputStream r9) {
                /*
                    r8 = this;
                    monitor-enter(r8)
                    boolean r0 = r8.closed     // Catch:{ all -> 0x005a }
                    if (r0 == 0) goto L_0x0007
                    monitor-exit(r8)
                    return
                L_0x0007:
                    io.grpc.internal.StatsTraceContext r0 = r8.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r0.outboundMessage(r1)     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r2 = r8.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r3 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r4 = -1
                    r6 = -1
                    r2.outboundMessageSent(r3, r4, r6)     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r0 = r0.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r1 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r0.inboundMessage(r1)     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream r0 = io.grpc.inprocess.InProcessTransport.InProcessStream.this     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r0 = r0.clientStream     // Catch:{ all -> 0x005a }
                    io.grpc.internal.StatsTraceContext r1 = r0.statsTraceCtx     // Catch:{ all -> 0x005a }
                    int r2 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    r3 = -1
                    r5 = -1
                    r1.inboundMessageRead(r2, r3, r5)     // Catch:{ all -> 0x005a }
                    int r0 = r8.outboundSeqNo     // Catch:{ all -> 0x005a }
                    int r0 = r0 + 1
                    r8.outboundSeqNo = r0     // Catch:{ all -> 0x005a }
                    io.grpc.inprocess.InProcessTransport$SingleMessageProducer r0 = new io.grpc.inprocess.InProcessTransport$SingleMessageProducer     // Catch:{ all -> 0x005a }
                    r1 = 0
                    r0.<init>(r9)     // Catch:{ all -> 0x005a }
                    int r9 = r8.clientRequested     // Catch:{ all -> 0x005a }
                    if (r9 <= 0) goto L_0x0053
                    int r9 = r8.clientRequested     // Catch:{ all -> 0x005a }
                    int r9 = r9 + -1
                    r8.clientRequested = r9     // Catch:{ all -> 0x005a }
                    io.grpc.internal.ClientStreamListener r9 = r8.clientStreamListener     // Catch:{ all -> 0x005a }
                    r9.messagesAvailable(r0)     // Catch:{ all -> 0x005a }
                    goto L_0x0058
                L_0x0053:
                    java.util.ArrayDeque<io.grpc.internal.StreamListener$MessageProducer> r9 = r8.clientReceiveQueue     // Catch:{ all -> 0x005a }
                    r9.add(r0)     // Catch:{ all -> 0x005a }
                L_0x0058:
                    monitor-exit(r8)
                    return
                L_0x005a:
                    r9 = move-exception
                    monitor-exit(r8)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.InProcessStream.InProcessServerStream.writeMessage(java.io.InputStream):void");
            }

            private synchronized boolean internalCancel(Status status) {
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while (true) {
                    MessageProducer messageProducer = (MessageProducer) this.clientReceiveQueue.poll();
                    if (messageProducer != null) {
                        while (true) {
                            InputStream next = messageProducer.next();
                            if (next != null) {
                                try {
                                    next.close();
                                } catch (Throwable th) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", th);
                                }
                            }
                        }
                    } else {
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(status);
                        this.clientStreamListener.closed(status, new Metadata());
                        return true;
                    }
                }
            }
        }

        private InProcessStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions2, String str) {
            this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, Param.METHOD);
            this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions2, "callOptions");
            this.authority = str;
            this.clientStream = new InProcessClientStream(callOptions2, metadata);
            this.serverStream = new InProcessServerStream(methodDescriptor, metadata);
        }

        /* access modifiers changed from: private */
        public void streamClosed() {
            synchronized (InProcessTransport.this) {
                boolean remove = InProcessTransport.this.streams.remove(this);
                if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                    InProcessTransport.this.inUseState.updateObjectInUse(this, false);
                }
                if (InProcessTransport.this.streams.isEmpty() && remove && InProcessTransport.this.shutdown) {
                    InProcessTransport.this.notifyTerminated();
                }
            }
        }
    }

    private static class SingleMessageProducer implements MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }

    public InProcessTransport(String str, int i, String str2, String str3) {
        this.name = str;
        this.clientMaxInboundMetadataSize = i;
        this.authority = str2;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", str3);
    }

    @CheckReturnValue
    public synchronized Runnable start(Listener listener) {
        this.clientTransportListener = listener;
        InProcessServer findServer = InProcessServer.findServer(this.name);
        if (findServer != null) {
            this.serverMaxInboundMetadataSize = findServer.getMaxInboundMetadataSize();
            this.serverSchedulerPool = findServer.getScheduledExecutorServicePool();
            this.serverScheduler = (ScheduledExecutorService) this.serverSchedulerPool.getObject();
            this.serverStreamTracerFactories = findServer.getStreamTracerFactories();
            this.serverTransportListener = findServer.register(this);
        }
        if (this.serverTransportListener == null) {
            Status status = Status.UNAVAILABLE;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find server: ");
            sb.append(this.name);
            this.shutdownStatus = status.withDescription(sb.toString());
            final Status status2 = this.shutdownStatus;
            return new Runnable() {
                public void run() {
                    synchronized (InProcessTransport.this) {
                        InProcessTransport.this.notifyShutdown(status2);
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            };
        }
        return new Runnable() {
            public void run() {
                synchronized (InProcessTransport.this) {
                    InProcessTransport.this.serverStreamAttributes = InProcessTransport.this.serverTransportListener.transportReady(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, new InProcessSocketAddress(InProcessTransport.this.name)).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, new InProcessSocketAddress(InProcessTransport.this.name)).build());
                    InProcessTransport.this.clientTransportListener.transportReady();
                }
            }
        };
    }

    public synchronized ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        if (this.shutdownStatus != null) {
            return failedClientStream(StatsTraceContext.newClientContext(callOptions, metadata), this.shutdownStatus);
        }
        metadata.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        if (this.serverMaxInboundMetadataSize != Integer.MAX_VALUE) {
            int metadataSize = metadataSize(metadata);
            if (metadataSize > this.serverMaxInboundMetadataSize) {
                return failedClientStream(StatsTraceContext.newClientContext(callOptions, metadata), Status.RESOURCE_EXHAUSTED.withDescription(String.format("Request metadata larger than %d: %d", new Object[]{Integer.valueOf(this.serverMaxInboundMetadataSize), Integer.valueOf(metadataSize)})));
            }
        }
        InProcessStream inProcessStream = new InProcessStream(methodDescriptor, metadata, callOptions, this.authority);
        return inProcessStream.clientStream;
    }

    private ClientStream failedClientStream(final StatsTraceContext statsTraceContext, final Status status) {
        return new NoopClientStream() {
            public void start(ClientStreamListener clientStreamListener) {
                statsTraceContext.clientOutboundHeaders();
                statsTraceContext.streamClosed(status);
                clientStreamListener.closed(status, new Metadata());
            }
        };
    }

    public synchronized void ping(final PingCallback pingCallback, Executor executor) {
        if (this.terminated) {
            final Status status = this.shutdownStatus;
            executor.execute(new Runnable() {
                public void run() {
                    pingCallback.onFailure(status.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable() {
                public void run() {
                    pingCallback.onSuccess(0);
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void shutdown(io.grpc.Status r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.shutdown     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r1.shutdownStatus = r2     // Catch:{ all -> 0x0019 }
            r1.notifyShutdown(r2)     // Catch:{ all -> 0x0019 }
            java.util.Set<io.grpc.inprocess.InProcessTransport$InProcessStream> r2 = r1.streams     // Catch:{ all -> 0x0019 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x0017
            r1.notifyTerminated()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.shutdown(io.grpc.Status):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r0.hasNext() == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        io.grpc.inprocess.InProcessTransport.InProcessStream.access$700((io.grpc.inprocess.InProcessTransport.InProcessStream) r0.next()).cancel(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void shutdownNow(io.grpc.Status r3) {
        /*
            r2 = this;
            java.lang.String r0 = "reason"
            com.google.common.base.Preconditions.checkNotNull(r3, r0)
            monitor-enter(r2)
            r2.shutdown(r3)     // Catch:{ all -> 0x0030 }
            boolean r0 = r2.terminated     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            return
        L_0x000f:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0030 }
            java.util.Set<io.grpc.inprocess.InProcessTransport$InProcessStream> r1 = r2.streams     // Catch:{ all -> 0x0030 }
            r0.<init>(r1)     // Catch:{ all -> 0x0030 }
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            java.util.Iterator r0 = r0.iterator()
        L_0x001b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002f
            java.lang.Object r1 = r0.next()
            io.grpc.inprocess.InProcessTransport$InProcessStream r1 = (io.grpc.inprocess.InProcessTransport.InProcessStream) r1
            io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream r1 = r1.clientStream
            r1.cancel(r3)
            goto L_0x001b
        L_0x002f:
            return
        L_0x0030:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0030 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.shutdownNow(io.grpc.Status):void");
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("name", (Object) this.name).toString();
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    /* access modifiers changed from: private */
    public synchronized void notifyShutdown(Status status) {
        if (!this.shutdown) {
            this.shutdown = true;
            this.clientTransportListener.transportShutdown(status);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void notifyTerminated() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.terminated     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.terminated = r0     // Catch:{ all -> 0x002a }
            java.util.concurrent.ScheduledExecutorService r0 = r2.serverScheduler     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x001a
            io.grpc.internal.ObjectPool<java.util.concurrent.ScheduledExecutorService> r0 = r2.serverSchedulerPool     // Catch:{ all -> 0x002a }
            java.util.concurrent.ScheduledExecutorService r1 = r2.serverScheduler     // Catch:{ all -> 0x002a }
            java.lang.Object r0 = r0.returnObject(r1)     // Catch:{ all -> 0x002a }
            java.util.concurrent.ScheduledExecutorService r0 = (java.util.concurrent.ScheduledExecutorService) r0     // Catch:{ all -> 0x002a }
            r2.serverScheduler = r0     // Catch:{ all -> 0x002a }
        L_0x001a:
            io.grpc.internal.ManagedClientTransport$Listener r0 = r2.clientTransportListener     // Catch:{ all -> 0x002a }
            r0.transportTerminated()     // Catch:{ all -> 0x002a }
            io.grpc.internal.ServerTransportListener r0 = r2.serverTransportListener     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0028
            io.grpc.internal.ServerTransportListener r0 = r2.serverTransportListener     // Catch:{ all -> 0x002a }
            r0.transportTerminated()     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r2)
            return
        L_0x002a:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.inprocess.InProcessTransport.notifyTerminated():void");
    }

    private static int metadataSize(Metadata metadata) {
        byte[][] serialize = InternalMetadata.serialize(metadata);
        if (serialize == null) {
            return 0;
        }
        long j = 0;
        for (int i = 0; i < serialize.length; i += 2) {
            j += (long) (serialize[i].length + 32 + serialize[i + 1].length);
        }
        return (int) Math.min(j, 2147483647L);
    }

    /* access modifiers changed from: private */
    public static Status stripCause(Status status) {
        if (status == null) {
            return null;
        }
        return Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
    }
}
