package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalChannelz;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver.Factory;
import io.grpc.NameResolverProvider;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ExponentialBackoffPolicy.Provider;
import io.opencensus.trace.Tracing;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public abstract class AbstractManagedChannelImplBuilder<T extends AbstractManagedChannelImplBuilder<T>> extends ManagedChannelBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final Factory DEFAULT_NAME_RESOLVER_FACTORY = NameResolverProvider.asFactory();
    @VisibleForTesting
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(30);
    @VisibleForTesting
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1);
    @Nullable
    @VisibleForTesting
    String authorityOverride;
    @Nullable
    BinaryLog binlog;
    @Nullable
    private CensusStatsModule censusStatsOverride;
    InternalChannelz channelz = InternalChannelz.instance();
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    @Nullable
    private final SocketAddress directServerAddress;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    boolean fullStreamDecompression;
    long idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
    private final List<ClientInterceptor> interceptors = new ArrayList();
    @Nullable
    LoadBalancer.Factory loadBalancerFactory;
    int maxHedgedAttempts = 5;
    private int maxInboundMessageSize = 4194304;
    int maxRetryAttempts = 5;
    int maxTraceEvents;
    private Factory nameResolverFactory = DEFAULT_NAME_RESOLVER_FACTORY;
    long perRpcBufferLimit = 1048576;
    private boolean recordFinishedRpcs = true;
    private boolean recordStartedRpcs = true;
    long retryBufferSize = 16777216;
    boolean retryEnabled = false;
    private boolean statsEnabled = true;
    final String target;
    boolean temporarilyDisableRetry;
    private boolean tracingEnabled = true;
    protected TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    @Nullable
    String userAgent;

    /* access modifiers changed from: protected */
    public abstract ClientTransportFactory buildTransportFactory();

    /* access modifiers changed from: protected */
    public final int maxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    protected AbstractManagedChannelImplBuilder(String str) {
        this.target = (String) Preconditions.checkNotNull(str, "target");
        this.directServerAddress = null;
    }

    public ManagedChannel build() {
        ManagedChannelImpl managedChannelImpl = new ManagedChannelImpl(this, buildTransportFactory(), new Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER);
        return new ManagedChannelOrphanWrapper(managedChannelImpl);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final List<ClientInterceptor> getEffectiveInterceptors() {
        ArrayList arrayList = new ArrayList(this.interceptors);
        this.temporarilyDisableRetry = false;
        if (this.statsEnabled) {
            this.temporarilyDisableRetry = true;
            CensusStatsModule censusStatsModule = this.censusStatsOverride;
            if (censusStatsModule == null) {
                censusStatsModule = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true);
            }
            arrayList.add(0, censusStatsModule.getClientInterceptor(this.recordStartedRpcs, this.recordFinishedRpcs));
        }
        if (this.tracingEnabled) {
            this.temporarilyDisableRetry = true;
            arrayList.add(0, new CensusTracingModule(Tracing.getTracer(), Tracing.getPropagationComponent().getBinaryFormat()).getClientInterceptor());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public Attributes getNameResolverParams() {
        return Attributes.EMPTY;
    }

    /* access modifiers changed from: 0000 */
    public Factory getNameResolverFactory() {
        String str = this.authorityOverride;
        if (str == null) {
            return this.nameResolverFactory;
        }
        return new OverrideAuthorityNameResolverFactory(this.nameResolverFactory, str);
    }
}
