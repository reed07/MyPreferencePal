package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.ClientStreamTracer.Factory;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Metadata.Key;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.StreamListener.MessageProducer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

abstract class RetriableStream<ReqT> implements ClientStream {
    /* access modifiers changed from: private */
    public static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    @VisibleForTesting
    static final Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    @VisibleForTesting
    static final Key<String> GRPC_RETRY_PUSHBACK_MS = Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    /* access modifiers changed from: private */
    public static Random random = new Random();
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    private final Metadata headers;
    private final Provider hedgingPolicyProvider;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public ClientStreamListener masterListener;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, ?> method;
    /* access modifiers changed from: private */
    public long nextBackoffIntervalNanos;
    /* access modifiers changed from: private */
    public boolean noMoreTransparentRetry;
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    @GuardedBy("lock")
    public long perRpcBufferUsed;
    /* access modifiers changed from: private */
    public RetryPolicy retryPolicy;
    /* access modifiers changed from: private */
    public final Provider retryPolicyProvider;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;
    /* access modifiers changed from: private */
    public Future<?> scheduledRetry;
    /* access modifiers changed from: private */
    public volatile State state;
    /* access modifiers changed from: private */
    @Nullable
    public final Throttle throttle;

    private interface BufferEntry {
        void runWith(Substream substream);
    }

    class BufferSizeTracer extends ClientStreamTracer {
        @GuardedBy("lock")
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream2) {
            this.substream = substream2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0080, code lost:
            if (r0 == null) goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0082, code lost:
            r0.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0085, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void outboundWireSize(long r8) {
            /*
                r7 = this;
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                r0 = 0
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r1.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream$State r2 = r2.state     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream$Substream r2 = r2.winningSubstream     // Catch:{ all -> 0x0088 }
                if (r2 != 0) goto L_0x0086
                io.grpc.internal.RetriableStream$Substream r2 = r7.substream     // Catch:{ all -> 0x0088 }
                boolean r2 = r2.closed     // Catch:{ all -> 0x0088 }
                if (r2 == 0) goto L_0x0024
                goto L_0x0086
            L_0x0024:
                long r2 = r7.bufferNeeded     // Catch:{ all -> 0x0088 }
                long r2 = r2 + r8
                r7.bufferNeeded = r2     // Catch:{ all -> 0x0088 }
                long r8 = r7.bufferNeeded     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                long r2 = r2.perRpcBufferUsed     // Catch:{ all -> 0x0088 }
                int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r4 > 0) goto L_0x0037
                monitor-exit(r1)     // Catch:{ all -> 0x0088 }
                return
            L_0x0037:
                long r8 = r7.bufferNeeded     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                long r2 = r2.perRpcBufferLimit     // Catch:{ all -> 0x0088 }
                r4 = 1
                int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r5 <= 0) goto L_0x0049
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0088 }
                r8.bufferLimitExceeded = r4     // Catch:{ all -> 0x0088 }
                goto L_0x0071
            L_0x0049:
                io.grpc.internal.RetriableStream r8 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream$ChannelBufferMeter r8 = r8.channelBufferUsed     // Catch:{ all -> 0x0088 }
                long r2 = r7.bufferNeeded     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream r9 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                long r5 = r9.perRpcBufferUsed     // Catch:{ all -> 0x0088 }
                long r2 = r2 - r5
                long r8 = r8.addAndGet(r2)     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                long r5 = r7.bufferNeeded     // Catch:{ all -> 0x0088 }
                r2.perRpcBufferUsed = r5     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                long r2 = r2.channelBufferLimit     // Catch:{ all -> 0x0088 }
                int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r5 <= 0) goto L_0x0071
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0088 }
                r8.bufferLimitExceeded = r4     // Catch:{ all -> 0x0088 }
            L_0x0071:
                io.grpc.internal.RetriableStream$Substream r8 = r7.substream     // Catch:{ all -> 0x0088 }
                boolean r8 = r8.bufferLimitExceeded     // Catch:{ all -> 0x0088 }
                if (r8 == 0) goto L_0x007f
                io.grpc.internal.RetriableStream r8 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0088 }
                io.grpc.internal.RetriableStream$Substream r9 = r7.substream     // Catch:{ all -> 0x0088 }
                java.lang.Runnable r0 = r8.commit(r9)     // Catch:{ all -> 0x0088 }
            L_0x007f:
                monitor-exit(r1)     // Catch:{ all -> 0x0088 }
                if (r0 == 0) goto L_0x0085
                r0.run()
            L_0x0085:
                return
            L_0x0086:
                monitor-exit(r1)     // Catch:{ all -> 0x0088 }
                return
            L_0x0088:
                r8 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0088 }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.BufferSizeTracer.outboundWireSize(long):void");
        }
    }

    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    private static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    private static final class State {
        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final boolean passThrough;
        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, @Nullable Substream substream, boolean z, boolean z2) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.cancelled = z;
            this.passThrough = z2;
            boolean z3 = false;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState(!z2 || substream != null, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            if (!z || substream != null) {
                z3 = true;
            }
            Preconditions.checkState(z3, "cancelled should imply committed");
        }

        /* access modifiers changed from: 0000 */
        @CheckReturnValue
        public State cancelled() {
            State state = new State(this.buffer, this.drainedSubstreams, this.winningSubstream, true, this.passThrough);
            return state;
        }

        /* access modifiers changed from: 0000 */
        @CheckReturnValue
        public State substreamDrained(Substream substream) {
            Collection collection;
            List<BufferEntry> list;
            boolean z = true;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                collection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                collection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                collection = Collections.unmodifiableCollection(arrayList);
            }
            boolean z2 = this.winningSubstream != null;
            List<BufferEntry> list2 = this.buffer;
            if (z2) {
                if (this.winningSubstream != substream) {
                    z = false;
                }
                Preconditions.checkState(z, "Another RPC attempt has already committed");
                list = null;
            } else {
                list = list2;
            }
            State state = new State(list, collection, this.winningSubstream, this.cancelled, z2);
            return state;
        }

        /* access modifiers changed from: 0000 */
        @CheckReturnValue
        public State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.drainedSubstreams);
            arrayList.remove(substream);
            State state = new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough);
            return state;
        }

        /* access modifiers changed from: 0000 */
        @CheckReturnValue
        public State committed(Substream substream) {
            boolean z;
            Collection collection;
            List<BufferEntry> list;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                list = null;
                collection = Collections.singleton(substream);
                z = true;
            } else {
                list = list2;
                collection = Collections.emptyList();
                z = false;
            }
            State state = new State(list, collection, substream, this.cancelled, z);
            return state;
        }
    }

    private final class Sublistener implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream2) {
            this.substream = substream2;
        }

        public void headersRead(Metadata metadata) {
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.headersRead(metadata);
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
            }
        }

        public void closed(Status status, Metadata metadata) {
            closed(status, RpcProgress.PROCESSED, metadata);
        }

        public void closed(Status status, RpcProgress rpcProgress, Metadata metadata) {
            synchronized (RetriableStream.this.lock) {
                RetriableStream.this.state = RetriableStream.this.state.substreamClosed(this.substream);
            }
            if (this.substream.bufferLimitExceeded) {
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.masterListener.closed(status, metadata);
                }
                return;
            }
            if (RetriableStream.this.state.winningSubstream == null) {
                if (rpcProgress == RpcProgress.REFUSED && !RetriableStream.this.noMoreTransparentRetry) {
                    RetriableStream.this.noMoreTransparentRetry = true;
                    RetriableStream.this.callExecutor.execute(new Runnable() {
                        public void run() {
                            RetriableStream.this.drain(RetriableStream.this.createSubstream(Sublistener.this.substream.previousAttempts));
                        }
                    });
                    return;
                } else if (rpcProgress != RpcProgress.DROPPED) {
                    RetriableStream.this.noMoreTransparentRetry = true;
                    if (RetriableStream.this.retryPolicy == null) {
                        RetriableStream retriableStream = RetriableStream.this;
                        retriableStream.retryPolicy = retriableStream.retryPolicyProvider.get();
                        RetriableStream retriableStream2 = RetriableStream.this;
                        retriableStream2.nextBackoffIntervalNanos = retriableStream2.retryPolicy.initialBackoffNanos;
                    }
                    RetryPlan makeRetryDecision = makeRetryDecision(RetriableStream.this.retryPolicy, status, metadata);
                    if (makeRetryDecision.shouldRetry) {
                        RetriableStream retriableStream3 = RetriableStream.this;
                        retriableStream3.scheduledRetry = retriableStream3.scheduledExecutorService.schedule(new Runnable() {
                            public void run() {
                                RetriableStream.this.scheduledRetry = null;
                                RetriableStream.this.callExecutor.execute(new Runnable() {
                                    public void run() {
                                        RetriableStream.this.drain(RetriableStream.this.createSubstream(Sublistener.this.substream.previousAttempts + 1));
                                    }
                                });
                            }
                        }, makeRetryDecision.backoffNanos, TimeUnit.NANOSECONDS);
                        return;
                    }
                }
            }
            if (!RetriableStream.this.hasHedging()) {
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.masterListener.closed(status, metadata);
                }
            }
        }

        private RetryPlan makeRetryDecision(RetryPolicy retryPolicy, Status status, Metadata metadata) {
            Integer num;
            long j;
            boolean contains = retryPolicy.retryableStatusCodes.contains(status.getCode());
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str != null) {
                try {
                    num = Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    num = Integer.valueOf(-1);
                }
            } else {
                num = null;
            }
            boolean z = true;
            boolean z2 = (RetriableStream.this.throttle == null || (!contains && (num == null || num.intValue() >= 0))) ? false : !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            if (retryPolicy.maxAttempts > this.substream.previousAttempts + 1 && !z2) {
                if (num == null) {
                    if (contains) {
                        j = (long) (((double) RetriableStream.this.nextBackoffIntervalNanos) * RetriableStream.random.nextDouble());
                        RetriableStream retriableStream = RetriableStream.this;
                        retriableStream.nextBackoffIntervalNanos = Math.min((long) (((double) retriableStream.nextBackoffIntervalNanos) * retryPolicy.backoffMultiplier), retryPolicy.maxBackoffNanos);
                        return new RetryPlan(z, j);
                    }
                } else if (num.intValue() >= 0) {
                    j = TimeUnit.MILLISECONDS.toNanos((long) num.intValue());
                    RetriableStream.this.nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
                    return new RetryPlan(z, j);
                }
            }
            j = 0;
            z = false;
            return new RetryPlan(z, j);
        }

        public void messagesAvailable(MessageProducer messageProducer) {
            State access$300 = RetriableStream.this.state;
            Preconditions.checkState(access$300.winningSubstream != null, "Headers should be received prior to messages.");
            if (access$300.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.messagesAvailable(messageProducer);
            }
        }

        public void onReady() {
            if (RetriableStream.this.state.drainedSubstreams.contains(this.substream)) {
                RetriableStream.this.masterListener.onReady();
            }
        }
    }

    private static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttempts;
        ClientStream stream;

        Substream(int i) {
            this.previousAttempts = i;
        }
    }

    static final class Throttle {
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount = new AtomicInteger();
        final int tokenRatio;

        Throttle(float f, float f2) {
            this.tokenRatio = (int) (f2 * 1000.0f);
            this.maxTokens = (int) (f * 1000.0f);
            int i = this.maxTokens;
            this.threshold = i / 2;
            this.tokenCount.set(i);
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            boolean z;
            int i2;
            do {
                i = this.tokenCount.get();
                z = false;
                if (i == 0) {
                    return false;
                }
                i2 = i - 1000;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            if (i2 > this.threshold) {
                z = true;
            }
            return z;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void onSuccess() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                i2 = this.maxTokens;
                if (i != i2) {
                } else {
                    return;
                }
            } while (!this.tokenCount.compareAndSet(i, Math.min(this.tokenRatio + i, i2)));
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            if (!(this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean hasHedging() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public abstract ClientStream newSubstream(Factory factory, Metadata metadata);

    /* access modifiers changed from: 0000 */
    public abstract void postCommit();

    /* access modifiers changed from: 0000 */
    @CheckReturnValue
    @Nullable
    public abstract Status prestart();

    RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService2, Provider provider, Provider provider2, @Nullable Throttle throttle2) {
        State state2 = new State(new ArrayList(8), Collections.emptyList(), null, false, false);
        this.state = state2;
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.headers = metadata;
        this.retryPolicyProvider = (Provider) Preconditions.checkNotNull(provider, "retryPolicyProvider");
        this.hedgingPolicyProvider = (Provider) Preconditions.checkNotNull(provider2, "hedgingPolicyProvider");
        this.throttle = throttle2;
    }

    /* access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(final Substream substream) {
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            AnonymousClass1CommitTask r2 = new Runnable() {
                public void run() {
                    for (Substream substream : collection) {
                        if (substream != substream) {
                            substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    RetriableStream.this.postCommit();
                }
            };
            return r2;
        }
    }

    /* access modifiers changed from: private */
    public void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            commit.run();
        }
    }

    /* access modifiers changed from: private */
    public Substream createSubstream(int i) {
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(new Factory() {
            public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, updateHeaders(this.headers, i));
        return substream;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        r1 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        if (r1.hasNext() == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        r3 = (io.grpc.internal.RetriableStream.BufferEntry) r1.next();
        r4 = r7.state;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006b, code lost:
        if (r4.winningSubstream == null) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006f, code lost:
        if (r4.winningSubstream == r8) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0074, code lost:
        if (r4.cancelled == false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
        if (r4.winningSubstream != r8) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007a, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007b, code lost:
        com.google.common.base.Preconditions.checkState(r0, "substream should be CANCELLED_BECAUSE_COMMITTED already");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0080, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
        r3.runWith(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0085, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain(io.grpc.internal.RetriableStream.Substream r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            r2 = r1
            r1 = 0
        L_0x0004:
            java.lang.Object r3 = r7.lock
            monitor-enter(r3)
            io.grpc.internal.RetriableStream$State r4 = r7.state     // Catch:{ all -> 0x0088 }
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream     // Catch:{ all -> 0x0088 }
            if (r5 == 0) goto L_0x001a
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream     // Catch:{ all -> 0x0088 }
            if (r5 == r8) goto L_0x001a
            monitor-exit(r3)     // Catch:{ all -> 0x0088 }
            io.grpc.internal.ClientStream r8 = r8.stream
            io.grpc.Status r0 = CANCELLED_BECAUSE_COMMITTED
            r8.cancel(r0)
            return
        L_0x001a:
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r5 = r4.buffer     // Catch:{ all -> 0x0088 }
            int r5 = r5.size()     // Catch:{ all -> 0x0088 }
            if (r1 != r5) goto L_0x002a
            io.grpc.internal.RetriableStream$State r8 = r4.substreamDrained(r8)     // Catch:{ all -> 0x0088 }
            r7.state = r8     // Catch:{ all -> 0x0088 }
            monitor-exit(r3)     // Catch:{ all -> 0x0088 }
            return
        L_0x002a:
            boolean r5 = r8.closed     // Catch:{ all -> 0x0088 }
            if (r5 == 0) goto L_0x0030
            monitor-exit(r3)     // Catch:{ all -> 0x0088 }
            return
        L_0x0030:
            int r5 = r1 + 128
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r6 = r4.buffer     // Catch:{ all -> 0x0088 }
            int r6 = r6.size()     // Catch:{ all -> 0x0088 }
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x0088 }
            if (r2 != 0) goto L_0x004a
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0088 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r4 = r4.buffer     // Catch:{ all -> 0x0088 }
            java.util.List r1 = r4.subList(r1, r5)     // Catch:{ all -> 0x0088 }
            r2.<init>(r1)     // Catch:{ all -> 0x0088 }
            goto L_0x0056
        L_0x004a:
            r2.clear()     // Catch:{ all -> 0x0088 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r4 = r4.buffer     // Catch:{ all -> 0x0088 }
            java.util.List r1 = r4.subList(r1, r5)     // Catch:{ all -> 0x0088 }
            r2.addAll(r1)     // Catch:{ all -> 0x0088 }
        L_0x0056:
            monitor-exit(r3)     // Catch:{ all -> 0x0088 }
            java.util.Iterator r1 = r2.iterator()
        L_0x005b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0085
            java.lang.Object r3 = r1.next()
            io.grpc.internal.RetriableStream$BufferEntry r3 = (io.grpc.internal.RetriableStream.BufferEntry) r3
            io.grpc.internal.RetriableStream$State r4 = r7.state
            io.grpc.internal.RetriableStream$Substream r6 = r4.winningSubstream
            if (r6 == 0) goto L_0x0072
            io.grpc.internal.RetriableStream$Substream r6 = r4.winningSubstream
            if (r6 == r8) goto L_0x0072
            goto L_0x0085
        L_0x0072:
            boolean r6 = r4.cancelled
            if (r6 == 0) goto L_0x0081
            io.grpc.internal.RetriableStream$Substream r1 = r4.winningSubstream
            if (r1 != r8) goto L_0x007b
            r0 = 1
        L_0x007b:
            java.lang.String r8 = "substream should be CANCELLED_BECAUSE_COMMITTED already"
            com.google.common.base.Preconditions.checkState(r0, r8)
            return
        L_0x0081:
            r3.runWith(r8)
            goto L_0x005b
        L_0x0085:
            r1 = r5
            goto L_0x0004
        L_0x0088:
            r8 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0088 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.drain(io.grpc.internal.RetriableStream$Substream):void");
    }

    public final void start(ClientStreamListener clientStreamListener) {
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.start(new Sublistener(substream));
                }
            });
        }
        drain(createSubstream(0));
    }

    public final void cancel(Status status) {
        Substream substream = new Substream(0);
        substream.stream = new NoopClientStream();
        Runnable commit = commit(substream);
        if (commit != null) {
            Future<?> future = this.scheduledRetry;
            if (future != null) {
                future.cancel(false);
                this.scheduledRetry = null;
            }
            this.masterListener.closed(status, new Metadata());
            commit.run();
            return;
        }
        this.state.winningSubstream.stream.cancel(status);
        synchronized (this.lock) {
            this.state = this.state.cancelled();
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        for (Substream runWith : collection) {
            bufferEntry.runWith(runWith);
        }
    }

    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    /* access modifiers changed from: 0000 */
    public final void sendMessage(final ReqT reqt) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(reqt));
                }
            });
        }
    }

    public final void request(final int i) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    public final void flush() {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    public final void halfClose() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }
}
