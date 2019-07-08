package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientCall.Listener;
import io.grpc.ClientInterceptor;
import io.grpc.ClientStreamTracer;
import io.grpc.ClientStreamTracer.Factory;
import io.grpc.ForwardingClientCall.SimpleForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.Metadata.BinaryMarshaller;
import io.grpc.Metadata.Key;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextSerializationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class CensusStatsModule {
    /* access modifiers changed from: private */
    public static final ClientTracer BLANK_CLIENT_TRACER = new ClientTracer();
    /* access modifiers changed from: private */
    public static final double NANOS_PER_MILLI = ((double) TimeUnit.MILLISECONDS.toNanos(1));
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(CensusStatsModule.class.getName());
    /* access modifiers changed from: private */
    public final boolean propagateTags;
    @VisibleForTesting
    final Key<TagContext> statsHeader;
    /* access modifiers changed from: private */
    public final StatsRecorder statsRecorder;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    public final Tagger tagger;

    @VisibleForTesting
    static final class ClientCallTracer extends Factory {
        @Nullable
        private static final AtomicIntegerFieldUpdater<ClientCallTracer> callEndedUpdater;
        @Nullable
        private static final AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> streamTracerUpdater;
        private volatile int callEnded;
        private final CensusStatsModule module;
        private final TagContext parentCtx;
        private final boolean recordFinishedRpcs;
        private final TagContext startCtx;
        private final Stopwatch stopwatch;
        private volatile ClientTracer streamTracer;

        static {
            AtomicIntegerFieldUpdater<ClientCallTracer> atomicIntegerFieldUpdater;
            AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> atomicReferenceFieldUpdater = null;
            try {
                AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> newUpdater = AtomicReferenceFieldUpdater.newUpdater(ClientCallTracer.class, ClientTracer.class, "streamTracer");
                atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientCallTracer.class, "callEnded");
                atomicReferenceFieldUpdater = newUpdater;
            } catch (Throwable th) {
                CensusStatsModule.logger.log(Level.SEVERE, "Creating atomic field updaters failed", th);
                atomicIntegerFieldUpdater = null;
            }
            streamTracerUpdater = atomicReferenceFieldUpdater;
            callEndedUpdater = atomicIntegerFieldUpdater;
        }

        ClientCallTracer(CensusStatsModule censusStatsModule, TagContext tagContext, String str, boolean z, boolean z2) {
            this.module = censusStatsModule;
            this.parentCtx = (TagContext) Preconditions.checkNotNull(tagContext);
            this.startCtx = censusStatsModule.tagger.toBuilder(tagContext).put(DeprecatedCensusConstants.RPC_METHOD, TagValue.create(str)).build();
            this.stopwatch = ((Stopwatch) censusStatsModule.stopwatchSupplier.get()).start();
            this.recordFinishedRpcs = z2;
            if (z) {
                censusStatsModule.statsRecorder.newMeasureMap().put(DeprecatedCensusConstants.RPC_CLIENT_STARTED_COUNT, 1).record(this.startCtx);
            }
        }

        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata metadata) {
            ClientTracer clientTracer = new ClientTracer();
            AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> atomicReferenceFieldUpdater = streamTracerUpdater;
            if (atomicReferenceFieldUpdater != null) {
                Preconditions.checkState(atomicReferenceFieldUpdater.compareAndSet(this, null, clientTracer), "Are you creating multiple streams per call? This class doesn't yet support this case");
            } else {
                Preconditions.checkState(this.streamTracer == null, "Are you creating multiple streams per call? This class doesn't yet support this case");
                this.streamTracer = clientTracer;
            }
            if (this.module.propagateTags) {
                metadata.discardAll(this.module.statsHeader);
                if (!this.module.tagger.empty().equals(this.parentCtx)) {
                    metadata.put(this.module.statsHeader, this.parentCtx);
                }
            }
            return clientTracer;
        }

        /* access modifiers changed from: 0000 */
        public void callEnded(Status status) {
            AtomicIntegerFieldUpdater<ClientCallTracer> atomicIntegerFieldUpdater = callEndedUpdater;
            if (atomicIntegerFieldUpdater != null) {
                if (atomicIntegerFieldUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else if (this.callEnded == 0) {
                this.callEnded = 1;
            } else {
                return;
            }
            if (this.recordFinishedRpcs) {
                this.stopwatch.stop();
                long elapsed = this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
                ClientTracer clientTracer = this.streamTracer;
                if (clientTracer == null) {
                    clientTracer = CensusStatsModule.BLANK_CLIENT_TRACER;
                }
                MeasureMap put = this.module.statsRecorder.newMeasureMap().put(DeprecatedCensusConstants.RPC_CLIENT_FINISHED_COUNT, 1).put(DeprecatedCensusConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, ((double) elapsed) / CensusStatsModule.NANOS_PER_MILLI).put(DeprecatedCensusConstants.RPC_CLIENT_REQUEST_COUNT, clientTracer.outboundMessageCount).put(DeprecatedCensusConstants.RPC_CLIENT_RESPONSE_COUNT, clientTracer.inboundMessageCount).put(DeprecatedCensusConstants.RPC_CLIENT_REQUEST_BYTES, (double) clientTracer.outboundWireSize).put(DeprecatedCensusConstants.RPC_CLIENT_RESPONSE_BYTES, (double) clientTracer.inboundWireSize).put(DeprecatedCensusConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES, (double) clientTracer.outboundUncompressedSize).put(DeprecatedCensusConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES, (double) clientTracer.inboundUncompressedSize);
                if (!status.isOk()) {
                    put.put(DeprecatedCensusConstants.RPC_CLIENT_ERROR_COUNT, 1);
                }
                put.record(this.module.tagger.toBuilder(this.startCtx).put(DeprecatedCensusConstants.RPC_STATUS, TagValue.create(status.getCode().toString())).build());
            }
        }
    }

    private static final class ClientTracer extends ClientStreamTracer {
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundUncompressedSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundWireSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundUncompressedSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundWireSizeUpdater;
        volatile long inboundMessageCount;
        volatile long inboundUncompressedSize;
        volatile long inboundWireSize;
        volatile long outboundMessageCount;
        volatile long outboundUncompressedSize;
        volatile long outboundWireSize;

        private ClientTracer() {
        }

        static {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater;
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater2;
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater3;
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater4;
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater5;
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater6 = null;
            try {
                AtomicLongFieldUpdater<ClientTracer> newUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundMessageCount");
                atomicLongFieldUpdater4 = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundMessageCount");
                atomicLongFieldUpdater3 = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundWireSize");
                atomicLongFieldUpdater2 = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundWireSize");
                atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundUncompressedSize");
                AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater7 = newUpdater;
                atomicLongFieldUpdater5 = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundUncompressedSize");
                atomicLongFieldUpdater6 = atomicLongFieldUpdater7;
            } catch (Throwable th) {
                CensusStatsModule.logger.log(Level.SEVERE, "Creating atomic field updaters failed", th);
                atomicLongFieldUpdater5 = null;
                atomicLongFieldUpdater4 = null;
                atomicLongFieldUpdater3 = null;
                atomicLongFieldUpdater2 = null;
                atomicLongFieldUpdater = null;
            }
            outboundMessageCountUpdater = atomicLongFieldUpdater6;
            inboundMessageCountUpdater = atomicLongFieldUpdater4;
            outboundWireSizeUpdater = atomicLongFieldUpdater3;
            inboundWireSizeUpdater = atomicLongFieldUpdater2;
            outboundUncompressedSizeUpdater = atomicLongFieldUpdater;
            inboundUncompressedSizeUpdater = atomicLongFieldUpdater5;
        }

        public void outboundWireSize(long j) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = outboundWireSizeUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndAdd(this, j);
            } else {
                this.outboundWireSize += j;
            }
        }

        public void inboundWireSize(long j) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = inboundWireSizeUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndAdd(this, j);
            } else {
                this.inboundWireSize += j;
            }
        }

        public void outboundUncompressedSize(long j) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = outboundUncompressedSizeUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndAdd(this, j);
            } else {
                this.outboundUncompressedSize += j;
            }
        }

        public void inboundUncompressedSize(long j) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = inboundUncompressedSizeUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndAdd(this, j);
            } else {
                this.inboundUncompressedSize += j;
            }
        }

        public void inboundMessage(int i) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = inboundMessageCountUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndIncrement(this);
            } else {
                this.inboundMessageCount++;
            }
        }

        public void outboundMessage(int i) {
            AtomicLongFieldUpdater<ClientTracer> atomicLongFieldUpdater = outboundMessageCountUpdater;
            if (atomicLongFieldUpdater != null) {
                atomicLongFieldUpdater.getAndIncrement(this);
            } else {
                this.outboundMessageCount++;
            }
        }
    }

    @VisibleForTesting
    final class StatsClientInterceptor implements ClientInterceptor {
        private final boolean recordFinishedRpcs;
        private final boolean recordStartedRpcs;

        StatsClientInterceptor(boolean z, boolean z2) {
            this.recordStartedRpcs = z;
            this.recordFinishedRpcs = z2;
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            final ClientCallTracer newClientCallTracer = CensusStatsModule.this.newClientCallTracer(CensusStatsModule.this.tagger.getCurrentTagContext(), methodDescriptor.getFullMethodName(), this.recordStartedRpcs, this.recordFinishedRpcs);
            return new SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions.withStreamTracerFactory(newClientCallTracer))) {
                public void start(Listener<RespT> listener, Metadata metadata) {
                    delegate().start(new SimpleForwardingClientCallListener<RespT>(listener) {
                        public void onClose(Status status, Metadata metadata) {
                            newClientCallTracer.callEnded(status);
                            super.onClose(status, metadata);
                        }
                    }, metadata);
                }
            };
        }
    }

    CensusStatsModule(Supplier<Stopwatch> supplier, boolean z) {
        this(Tags.getTagger(), Tags.getTagPropagationComponent().getBinarySerializer(), Stats.getStatsRecorder(), supplier, z);
    }

    public CensusStatsModule(final Tagger tagger2, final TagContextBinarySerializer tagContextBinarySerializer, StatsRecorder statsRecorder2, Supplier<Stopwatch> supplier, boolean z) {
        this.tagger = (Tagger) Preconditions.checkNotNull(tagger2, "tagger");
        this.statsRecorder = (StatsRecorder) Preconditions.checkNotNull(statsRecorder2, "statsRecorder");
        Preconditions.checkNotNull(tagContextBinarySerializer, "tagCtxSerializer");
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        this.propagateTags = z;
        this.statsHeader = Key.of("grpc-tags-bin", (BinaryMarshaller<T>) new BinaryMarshaller<TagContext>() {
            public byte[] toBytes(TagContext tagContext) {
                try {
                    return tagContextBinarySerializer.toByteArray(tagContext);
                } catch (TagContextSerializationException e) {
                    throw new RuntimeException(e);
                }
            }

            public TagContext parseBytes(byte[] bArr) {
                try {
                    return tagContextBinarySerializer.fromByteArray(bArr);
                } catch (Exception e) {
                    CensusStatsModule.logger.log(Level.FINE, "Failed to parse stats header", e);
                    return tagger2.empty();
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public ClientCallTracer newClientCallTracer(TagContext tagContext, String str, boolean z, boolean z2) {
        ClientCallTracer clientCallTracer = new ClientCallTracer(this, tagContext, str, z, z2);
        return clientCallTracer;
    }

    /* access modifiers changed from: 0000 */
    public ClientInterceptor getClientInterceptor(boolean z, boolean z2) {
        return new StatsClientInterceptor(z, z2);
    }
}
