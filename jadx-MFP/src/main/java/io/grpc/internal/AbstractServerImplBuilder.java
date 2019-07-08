package io.grpc.internal;

import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.InternalNotifyOnServerBuild;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.CallTracer.Factory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractServerImplBuilder<T extends AbstractServerImplBuilder<T>> extends ServerBuilder<T> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new HandlerRegistry() {
    };
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    protected Factory callTracerFactory = CallTracer.getDefaultFactory();
    protected InternalChannelz channelz = InternalChannelz.instance();
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    final List<ServerInterceptor> interceptors = new ArrayList();
    private final List<InternalNotifyOnServerBuild> notifyOnBuildList = new ArrayList();
    private boolean recordFinishedRpcs = true;
    private boolean recordStartedRpcs = true;
    final Builder registryBuilder = new Builder();
    private boolean statsEnabled = true;
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    private boolean tracingEnabled = true;
    final List<ServerTransportFilter> transportFilters = new ArrayList();
    protected TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
}
