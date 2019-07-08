package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ChannelLogger;
import io.grpc.ChannelLogger.ChannelLogLevel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.InternalChannelz;
import io.grpc.InternalChannelz.ChannelStats;
import io.grpc.InternalChannelz.ChannelTrace.Event.Builder;
import io.grpc.InternalChannelz.ChannelTrace.Event.Severity;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancer.PickResult;
import io.grpc.LoadBalancer.PickSubchannelArgs;
import io.grpc.LoadBalancer.Subchannel;
import io.grpc.LoadBalancer.SubchannelPicker;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy.Provider;
import io.grpc.internal.CallTracer.Factory;
import io.grpc.internal.ManagedClientTransport.Listener;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<ChannelStats> {
    @VisibleForTesting
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    @VisibleForTesting
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    @VisibleForTesting
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    @VisibleForTesting
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    /* access modifiers changed from: private */
    public final Provider backoffPolicyProvider;
    private final ExecutorHolder balancerRpcExecutorHolder;
    private final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    /* access modifiers changed from: private */
    public final Factory callTracerFactory;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    /* access modifiers changed from: private */
    public final CallTracer channelCallTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final Listener delayedTransportListener;
    private final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public boolean fullStreamDecompression;
    /* access modifiers changed from: private */
    @CheckForNull
    public Boolean haveBackends;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    @VisibleForTesting
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    @Nullable
    public Map<String, Object> lastServiceConfig;
    /* access modifiers changed from: private */
    @Nullable
    public LbHelperImpl lbHelper;
    private final LoadBalancer.Factory loadBalancerFactory;
    private final InternalLogId logId = InternalLogId.allocate(getClass().getName());
    /* access modifiers changed from: private */
    public final int maxTraceEvents;
    /* access modifiers changed from: private */
    public NameResolver nameResolver;
    /* access modifiers changed from: private */
    @Nullable
    public BackoffPolicy nameResolverBackoffPolicy;
    private final NameResolver.Factory nameResolverFactory;
    private final Attributes nameResolverParams;
    /* access modifiers changed from: private */
    @Nullable
    public NameResolverRefresh nameResolverRefresh;
    /* access modifiers changed from: private */
    @Nullable
    public ScheduledFuture<?> nameResolverRefreshFuture;
    private boolean nameResolverStarted;
    private final Set<OobChannel> oobChannels;
    private boolean panicMode;
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public final boolean retryEnabled;
    private final ScheduledExecutorForBalancer scheduledExecutorForBalancer;
    /* access modifiers changed from: private */
    public final ServiceConfigInterceptor serviceConfigInterceptor;
    /* access modifiers changed from: private */
    public final AtomicBoolean shutdown;
    /* access modifiers changed from: private */
    public boolean shutdownNowed;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    @Nullable
    public volatile SubchannelPicker subchannelPicker;
    /* access modifiers changed from: private */
    public final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext = new SynchronizationContext(new UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable th) {
            Logger logger = ManagedChannelImpl.logger;
            Level level = Level.SEVERE;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(ManagedChannelImpl.this.getLogId());
            sb.append("] Uncaught exception in the SynchronizationContext. Panic!");
            logger.log(level, sb.toString(), th);
            ManagedChannelImpl.this.panic(th);
        }
    });
    private final String target;
    /* access modifiers changed from: private */
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch;
    /* access modifiers changed from: private */
    public volatile boolean terminating;
    /* access modifiers changed from: private */
    @Nullable
    public Throttle throttle;
    /* access modifiers changed from: private */
    public final TimeProvider timeProvider;
    /* access modifiers changed from: private */
    public final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final ClientTransportProvider transportProvider;
    /* access modifiers changed from: private */
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final String userAgent;

    private final class ChannelTransportProvider implements ClientTransportProvider {
        private ChannelTransportProvider() {
        }

        public ClientTransport get(PickSubchannelArgs pickSubchannelArgs) {
            SubchannelPicker access$1300 = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (access$1300 == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(access$1300.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            if (transportFromPickResult != null) {
                return transportFromPickResult;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        public <ReqT> RetriableStream<ReqT> newRetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            Preconditions.checkState(ManagedChannelImpl.this.retryEnabled, "retry should be enabled");
            AnonymousClass1RetryStream r2 = new RetriableStream<ReqT>(this, methodDescriptor, metadata, callOptions, context) {
                final /* synthetic */ ChannelTransportProvider this$1;
                final /* synthetic */ CallOptions val$callOptions;
                final /* synthetic */ Context val$context;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ MethodDescriptor val$method;

                {
                    ChannelTransportProvider channelTransportProvider = r16;
                    CallOptions callOptions = r19;
                    this.this$1 = channelTransportProvider;
                    this.val$method = r17;
                    this.val$headers = r18;
                    this.val$callOptions = callOptions;
                    this.val$context = r20;
                    ChannelBufferMeter access$1800 = ManagedChannelImpl.this.channelBufferUsed;
                    long access$1900 = ManagedChannelImpl.this.perRpcBufferLimit;
                    long access$2000 = ManagedChannelImpl.this.channelBufferLimit;
                    Executor access$2100 = ManagedChannelImpl.this.getCallExecutor(callOptions);
                    ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
                    Provider provider = (Provider) callOptions.getOption(ServiceConfigInterceptor.RETRY_POLICY_KEY);
                    Provider provider2 = (Provider) callOptions.getOption(ServiceConfigInterceptor.HEDGING_POLICY_KEY);
                    Throttle access$2300 = ManagedChannelImpl.this.throttle;
                }

                /* access modifiers changed from: 0000 */
                public Status prestart() {
                    return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                }

                /* access modifiers changed from: 0000 */
                public void postCommit() {
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                }

                /* access modifiers changed from: 0000 */
                public ClientStream newSubstream(ClientStreamTracer.Factory factory, Metadata metadata) {
                    CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                    ClientTransport clientTransport = this.this$1.get(new PickSubchannelArgsImpl(this.val$method, metadata, withStreamTracerFactory));
                    Context attach = this.val$context.attach();
                    try {
                        ClientStream newStream = clientTransport.newStream(this.val$method, metadata, withStreamTracerFactory);
                        return newStream;
                    } finally {
                        this.val$context.detach(attach);
                    }
                }
            };
            return r2;
        }
    }

    private final class DelayedTransportListener implements Listener {
        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        public void transportShutdown(Status status) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        public void transportInUse(boolean z) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, z);
        }

        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    private static final class ExecutorHolder {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        /* access modifiers changed from: 0000 */
        public synchronized void release() {
            if (this.executor != null) {
                this.executor = (Executor) this.pool.returnObject(this.executor);
            }
        }
    }

    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        /* access modifiers changed from: protected */
        public void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            if (!ManagedChannelImpl.this.shutdown.get()) {
                ManagedChannelImpl.this.rescheduleIdleTimer();
            }
        }
    }

    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        public void run() {
            ManagedChannelImpl.this.enterIdleMode();
        }
    }

    private class LbHelperImpl extends Helper {
        LoadBalancer lb;
        final NameResolver nr;

        LbHelperImpl(NameResolver nameResolver) {
            this.nr = (NameResolver) Preconditions.checkNotNull(nameResolver, "NameResolver");
        }

        /* access modifiers changed from: private */
        public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
                this.nr.refresh();
            }
        }

        public AbstractSubchannel createSubchannel(List<EquivalentAddressGroup> list, Attributes attributes) {
            List<EquivalentAddressGroup> list2 = list;
            Attributes attributes2 = attributes;
            try {
                ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            } catch (IllegalStateException e) {
                ManagedChannelImpl.logger.log(Level.WARNING, "We sugguest you call createSubchannel() from SynchronizationContext. Otherwise, it may race with handleSubchannelState(). See https://github.com/grpc/grpc-java/issues/5015", e);
            }
            Preconditions.checkNotNull(list2, "addressGroups");
            Preconditions.checkNotNull(attributes2, "attrs");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            final SubchannelImpl subchannelImpl = new SubchannelImpl(attributes2);
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("Subchannel");
            int access$4300 = ManagedChannelImpl.this.maxTraceEvents;
            StringBuilder sb = new StringBuilder();
            sb.append("Subchannel for ");
            sb.append(list2);
            ChannelTracer channelTracer = new ChannelTracer(allocate, access$4300, currentTimeNanos, sb.toString());
            SubchannelImpl subchannelImpl2 = subchannelImpl;
            final InternalSubchannel internalSubchannel = r2;
            long j = currentTimeNanos;
            InternalSubchannel internalSubchannel2 = new InternalSubchannel(list, ManagedChannelImpl.this.authority(), ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.syncContext, new Callback() {
                /* access modifiers changed from: 0000 */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.subchannels.remove(internalSubchannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: 0000 */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    LbHelperImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        LbHelperImpl.this.lb.handleSubchannelState(subchannelImpl, connectivityStateInfo);
                    }
                }

                /* access modifiers changed from: 0000 */
                public void onInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
                }

                /* access modifiers changed from: 0000 */
                public void onNotInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, allocate, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new Builder().setDescription("Child Subchannel created").setSeverity(Severity.CT_INFO).setTimestampNanos(j).setSubchannelRef(internalSubchannel).build());
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            SubchannelImpl subchannelImpl3 = subchannelImpl2;
            subchannelImpl3.subchannel = internalSubchannel;
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        internalSubchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.subchannels.add(internalSubchannel);
                    }
                }
            });
            return subchannelImpl3;
        }

        public void updateBalancingState(final ConnectivityState connectivityState, final SubchannelPicker subchannelPicker) {
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.updateSubchannelPicker(subchannelPicker);
                        if (connectivityState != ConnectivityState.SHUTDOWN) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Entering {0} state", connectivityState);
                            ManagedChannelImpl.this.channelStateManager.gotoState(connectivityState);
                        }
                    }
                }
            });
        }

        public void updateSubchannelAddresses(Subchannel subchannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(subchannel instanceof SubchannelImpl, "subchannel must have been returned from createSubchannel");
            ((SubchannelImpl) subchannel).subchannel.updateAddresses(list);
        }

        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }
    }

    private class NameResolverListenerImpl implements NameResolver.Listener {
        final LbHelperImpl helper;

        NameResolverListenerImpl(LbHelperImpl lbHelperImpl) {
            this.helper = lbHelperImpl;
        }

        public void onAddresses(final List<EquivalentAddressGroup> list, final Attributes attributes) {
            if (list.isEmpty()) {
                Status status = Status.UNAVAILABLE;
                StringBuilder sb = new StringBuilder();
                sb.append("Name resolver ");
                sb.append(this.helper.nr);
                sb.append(" returned an empty list");
                onError(status.withDescription(sb.toString()));
                return;
            }
            ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", list, attributes);
            if (ManagedChannelImpl.this.haveBackends == null || !ManagedChannelImpl.this.haveBackends.booleanValue()) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Address resolved: {0}", list);
                ManagedChannelImpl.this.haveBackends = Boolean.valueOf(true);
            }
            final Map map = (Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
            if (map != null && !map.equals(ManagedChannelImpl.this.lastServiceConfig)) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Service config changed");
                ManagedChannelImpl.this.lastServiceConfig = map;
            }
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (NameResolverListenerImpl.this.helper == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                        if (map != null) {
                            try {
                                ManagedChannelImpl.this.serviceConfigInterceptor.handleUpdate(map);
                                if (ManagedChannelImpl.this.retryEnabled) {
                                    ManagedChannelImpl.this.throttle = ManagedChannelImpl.getThrottle(attributes);
                                }
                            } catch (RuntimeException e) {
                                Logger logger = ManagedChannelImpl.logger;
                                Level level = Level.WARNING;
                                StringBuilder sb = new StringBuilder();
                                sb.append("[");
                                sb.append(ManagedChannelImpl.this.getLogId());
                                sb.append("] Unexpected exception from parsing service config");
                                logger.log(level, sb.toString(), e);
                            }
                        }
                        NameResolverListenerImpl.this.helper.lb.handleResolvedAddressGroups(list, attributes);
                    }
                }
            });
        }

        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), status});
            if (ManagedChannelImpl.this.haveBackends == null || ManagedChannelImpl.this.haveBackends.booleanValue()) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                ManagedChannelImpl.this.haveBackends = Boolean.valueOf(false);
            }
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (NameResolverListenerImpl.this.helper == ManagedChannelImpl.this.lbHelper) {
                        NameResolverListenerImpl.this.helper.lb.handleNameResolutionError(status);
                        if (ManagedChannelImpl.this.nameResolverRefreshFuture == null) {
                            if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                                ManagedChannelImpl.this.nameResolverBackoffPolicy = ManagedChannelImpl.this.backoffPolicyProvider.get();
                            }
                            long nextBackoffNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.DEBUG, "Scheduling DNS resolution backoff for {0} ns", Long.valueOf(nextBackoffNanos));
                            ManagedChannelImpl.this.nameResolverRefresh = new NameResolverRefresh();
                            ManagedChannelImpl.this.nameResolverRefreshFuture = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService().schedule(ManagedChannelImpl.this.nameResolverRefresh, nextBackoffNanos, TimeUnit.NANOSECONDS);
                        }
                    }
                }
            });
        }
    }

    @VisibleForTesting
    class NameResolverRefresh implements Runnable {
        boolean cancelled;

        NameResolverRefresh() {
        }

        public void run() {
            if (!this.cancelled) {
                ManagedChannelImpl.this.nameResolverRefreshFuture = null;
                ManagedChannelImpl.this.nameResolverRefresh = null;
                if (ManagedChannelImpl.this.nameResolver != null) {
                    ManagedChannelImpl.this.nameResolver.refresh();
                }
            }
        }
    }

    private class RealChannel extends Channel {
        private final String authority;

        private RealChannel(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            ClientCallImpl clientCallImpl = new ClientCallImpl(methodDescriptor, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, ManagedChannelImpl.this.retryEnabled);
            return clientCallImpl.setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
        }

        public String authority() {
            return this.authority;
        }
    }

    private static final class ScheduledExecutorForBalancer implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private ScheduledExecutorForBalancer(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return this.delegate.invokeAny(collection);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.invokeAny(collection, j, timeUnit);
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }

    private final class SubchannelImpl extends AbstractSubchannel {
        final Attributes attrs;
        @GuardedBy("shutdownLock")
        ScheduledFuture<?> delayedShutdownTask;
        final Object shutdownLock = new Object();
        @GuardedBy("shutdownLock")
        boolean shutdownRequested;
        InternalSubchannel subchannel;

        SubchannelImpl(Attributes attributes) {
            this.attrs = (Attributes) Preconditions.checkNotNull(attributes, "attrs");
        }

        /* access modifiers changed from: 0000 */
        public ClientTransport obtainActiveTransport() {
            return this.subchannel.obtainActiveTransport();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void shutdown() {
            /*
                r6 = this;
                java.lang.Object r0 = r6.shutdownLock
                monitor-enter(r0)
                boolean r1 = r6.shutdownRequested     // Catch:{ all -> 0x0053 }
                if (r1 == 0) goto L_0x001f
                io.grpc.internal.ManagedChannelImpl r1 = io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x0053 }
                boolean r1 = r1.terminating     // Catch:{ all -> 0x0053 }
                if (r1 == 0) goto L_0x001d
                java.util.concurrent.ScheduledFuture<?> r1 = r6.delayedShutdownTask     // Catch:{ all -> 0x0053 }
                if (r1 == 0) goto L_0x001d
                java.util.concurrent.ScheduledFuture<?> r1 = r6.delayedShutdownTask     // Catch:{ all -> 0x0053 }
                r2 = 0
                r1.cancel(r2)     // Catch:{ all -> 0x0053 }
                r1 = 0
                r6.delayedShutdownTask = r1     // Catch:{ all -> 0x0053 }
                goto L_0x0022
            L_0x001d:
                monitor-exit(r0)     // Catch:{ all -> 0x0053 }
                return
            L_0x001f:
                r1 = 1
                r6.shutdownRequested = r1     // Catch:{ all -> 0x0053 }
            L_0x0022:
                io.grpc.internal.ManagedChannelImpl r1 = io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x0053 }
                boolean r1 = r1.terminating     // Catch:{ all -> 0x0053 }
                if (r1 != 0) goto L_0x004a
                io.grpc.internal.ManagedChannelImpl r1 = io.grpc.internal.ManagedChannelImpl.this     // Catch:{ all -> 0x0053 }
                io.grpc.internal.ClientTransportFactory r1 = r1.transportFactory     // Catch:{ all -> 0x0053 }
                java.util.concurrent.ScheduledExecutorService r1 = r1.getScheduledExecutorService()     // Catch:{ all -> 0x0053 }
                io.grpc.internal.LogExceptionRunnable r2 = new io.grpc.internal.LogExceptionRunnable     // Catch:{ all -> 0x0053 }
                io.grpc.internal.ManagedChannelImpl$SubchannelImpl$1ShutdownSubchannel r3 = new io.grpc.internal.ManagedChannelImpl$SubchannelImpl$1ShutdownSubchannel     // Catch:{ all -> 0x0053 }
                r3.<init>()     // Catch:{ all -> 0x0053 }
                r2.<init>(r3)     // Catch:{ all -> 0x0053 }
                r3 = 5
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x0053 }
                java.util.concurrent.ScheduledFuture r1 = r1.schedule(r2, r3, r5)     // Catch:{ all -> 0x0053 }
                r6.delayedShutdownTask = r1     // Catch:{ all -> 0x0053 }
                monitor-exit(r0)     // Catch:{ all -> 0x0053 }
                return
            L_0x004a:
                monitor-exit(r0)     // Catch:{ all -> 0x0053 }
                io.grpc.internal.InternalSubchannel r0 = r6.subchannel
                io.grpc.Status r1 = io.grpc.internal.ManagedChannelImpl.SHUTDOWN_STATUS
                r0.shutdown(r1)
                return
            L_0x0053:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0053 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ManagedChannelImpl.SubchannelImpl.shutdown():void");
        }

        public void requestConnection() {
            this.subchannel.obtainActiveTransport();
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            return this.subchannel.getAddressGroups();
        }

        public Attributes getAttributes() {
            return this.attrs;
        }

        public String toString() {
            return this.subchannel.getLogId().toString();
        }
    }

    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        @GuardedBy("lock")
        Status shutdownStatus;
        @GuardedBy("lock")
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
            io.grpc.internal.ManagedChannelImpl.access$1500(r2.this$0).shutdown(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
            if (r1 == false) goto L_0x001d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onShutdown(io.grpc.Status r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.lock
                monitor-enter(r0)
                io.grpc.Status r1 = r2.shutdownStatus     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x0009
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                return
            L_0x0009:
                r2.shutdownStatus = r3     // Catch:{ all -> 0x001e }
                java.util.Collection<io.grpc.internal.ClientStream> r1 = r2.uncommittedRetriableStreams     // Catch:{ all -> 0x001e }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x001e }
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x001d
                io.grpc.internal.ManagedChannelImpl r0 = io.grpc.internal.ManagedChannelImpl.this
                io.grpc.internal.DelayedClientTransport r0 = r0.delayedTransport
                r0.shutdown(r3)
            L_0x001d:
                return
            L_0x001e:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ManagedChannelImpl.UncommittedRetriableStreamsRegistry.onShutdown(io.grpc.Status):void");
        }

        /* access modifiers changed from: 0000 */
        public void onShutdownNow(Status status) {
            ArrayList<ClientStream> arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList<>(this.uncommittedRetriableStreams);
            }
            for (ClientStream cancel : arrayList) {
                cancel.cancel(status);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(status);
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                if (this.shutdownStatus != null) {
                    Status status = this.shutdownStatus;
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /* access modifiers changed from: 0000 */
        public void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(status);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel shutdownNow : this.subchannels) {
                shutdownNow.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel internalSubchannel : this.oobChannels) {
                internalSubchannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        if (z) {
            boolean z2 = true;
            Preconditions.checkState(this.nameResolver != null, "nameResolver is null");
            if (this.lbHelper == null) {
                z2 = false;
            }
            Preconditions.checkState(z2, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolver = null;
            this.nameResolverStarted = false;
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void exitIdleMode() {
        if (!this.shutdown.get() && !this.panicMode) {
            if (this.inUseStateAggregator.isInUse()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log(ChannelLogLevel.INFO, "Exiting idle mode");
                LbHelperImpl lbHelperImpl = new LbHelperImpl(this.nameResolver);
                lbHelperImpl.lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
                this.lbHelper = lbHelperImpl;
                NameResolverListenerImpl nameResolverListenerImpl = new NameResolverListenerImpl(lbHelperImpl);
                try {
                    this.nameResolver.start(nameResolverListenerImpl);
                    this.nameResolverStarted = true;
                } catch (Throwable th) {
                    nameResolverListenerImpl.onError(Status.fromThrowable(th));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess(null);
        this.nameResolver = getNameResolver(this.target, this.nameResolverFactory, this.nameResolverParams);
        this.channelLogger.log(ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.isInUse()) {
            exitIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    /* access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j != -1) {
            this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
        }
    }

    private void cancelNameResolverBackoff() {
        ScheduledFuture<?> scheduledFuture = this.nameResolverRefreshFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.nameResolverRefresh.cancelled = true;
            this.nameResolverRefreshFuture = null;
            this.nameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    ManagedChannelImpl(AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder, ClientTransportFactory clientTransportFactory, Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, TimeProvider timeProvider2) {
        Channel channel;
        List<ClientInterceptor> list2;
        AbstractManagedChannelImplBuilder<?> abstractManagedChannelImplBuilder2 = abstractManagedChannelImplBuilder;
        ObjectPool<? extends Executor> objectPool2 = objectPool;
        final TimeProvider timeProvider3 = timeProvider2;
        boolean z = true;
        this.oobChannels = new HashSet(1, 0.75f);
        this.uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
        this.shutdown = new AtomicBoolean(false);
        this.terminatedLatch = new CountDownLatch(1);
        this.channelBufferUsed = new ChannelBufferMeter();
        this.delayedTransportListener = new DelayedTransportListener();
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelTransportProvider();
        this.target = (String) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.target, "target");
        this.nameResolverFactory = abstractManagedChannelImplBuilder.getNameResolverFactory();
        this.nameResolverParams = (Attributes) Preconditions.checkNotNull(abstractManagedChannelImplBuilder.getNameResolverParams(), "nameResolverParams");
        this.nameResolver = getNameResolver(this.target, this.nameResolverFactory, this.nameResolverParams);
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider3, "timeProvider");
        this.maxTraceEvents = abstractManagedChannelImplBuilder2.maxTraceEvents;
        InternalLogId internalLogId = this.logId;
        int i = abstractManagedChannelImplBuilder2.maxTraceEvents;
        long currentTimeNanos = timeProvider2.currentTimeNanos();
        StringBuilder sb = new StringBuilder();
        sb.append("Channel for '");
        sb.append(this.target);
        sb.append("'");
        ChannelTracer channelTracer2 = new ChannelTracer(internalLogId, i, currentTimeNanos, sb.toString());
        this.channelTracer = channelTracer2;
        this.channelLogger = new ChannelLoggerImpl(this.channelTracer, timeProvider3);
        if (abstractManagedChannelImplBuilder2.loadBalancerFactory == null) {
            this.loadBalancerFactory = new AutoConfiguredLoadBalancerFactory();
        } else {
            this.loadBalancerFactory = abstractManagedChannelImplBuilder2.loadBalancerFactory;
        }
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.executorPool, "executorPool");
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool2);
        this.executor = (Executor) Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
        this.delayedTransport = new DelayedClientTransport(this.executor, this.syncContext);
        this.delayedTransport.start(this.delayedTransportListener);
        this.backoffPolicyProvider = provider;
        this.transportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, this.executor);
        this.scheduledExecutorForBalancer = new ScheduledExecutorForBalancer(this.transportFactory.getScheduledExecutorService());
        this.retryEnabled = abstractManagedChannelImplBuilder2.retryEnabled && !abstractManagedChannelImplBuilder2.temporarilyDisableRetry;
        this.serviceConfigInterceptor = new ServiceConfigInterceptor(this.retryEnabled, abstractManagedChannelImplBuilder2.maxRetryAttempts, abstractManagedChannelImplBuilder2.maxHedgedAttempts);
        Channel intercept = ClientInterceptors.intercept((Channel) new RealChannel(this.nameResolver.getServiceAuthority()), this.serviceConfigInterceptor);
        if (abstractManagedChannelImplBuilder2.binlog != null) {
            channel = abstractManagedChannelImplBuilder2.binlog.wrapChannel(intercept);
            list2 = list;
        } else {
            channel = intercept;
            list2 = list;
        }
        this.interceptorChannel = ClientInterceptors.intercept(channel, list2);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (abstractManagedChannelImplBuilder2.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        } else {
            if (abstractManagedChannelImplBuilder2.idleTimeoutMillis < AbstractManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS) {
                z = false;
            }
            Preconditions.checkArgument(z, "invalid idleTimeoutMillis %s", abstractManagedChannelImplBuilder2.idleTimeoutMillis);
            this.idleTimeoutMillis = abstractManagedChannelImplBuilder2.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), this.syncContext, this.transportFactory.getScheduledExecutorService(), (Stopwatch) supplier.get());
        this.fullStreamDecompression = abstractManagedChannelImplBuilder2.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.compressorRegistry, "compressorRegistry");
        this.userAgent = abstractManagedChannelImplBuilder2.userAgent;
        this.channelBufferLimit = abstractManagedChannelImplBuilder2.retryBufferSize;
        this.perRpcBufferLimit = abstractManagedChannelImplBuilder2.perRpcBufferLimit;
        this.callTracerFactory = new Factory() {
            public CallTracer create() {
                return new CallTracer(timeProvider3);
            }
        };
        this.channelCallTracer = this.callTracerFactory.create();
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(abstractManagedChannelImplBuilder2.channelz);
        this.channelz.addRootChannel(this);
    }

    @VisibleForTesting
    static NameResolver getNameResolver(String str, NameResolver.Factory factory, Attributes attributes) {
        URI uri;
        String str2;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        if (uri != null) {
            NameResolver newNameResolver = factory.newNameResolver(uri, attributes);
            if (newNameResolver != null) {
                return newNameResolver;
            }
        }
        if (!URI_PATTERN.matcher(str).matches()) {
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("/");
                sb2.append(str);
                NameResolver newNameResolver2 = factory.newNameResolver(new URI(factory.getDefaultScheme(), "", sb2.toString(), null), attributes);
                if (newNameResolver2 != null) {
                    return newNameResolver2;
                }
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (sb.length() > 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" (");
            sb3.append(sb);
            sb3.append(")");
            str2 = sb3.toString();
        } else {
            str2 = "";
        }
        objArr[1] = str2;
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", objArr));
    }

    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.executeLater(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.uncommittedRetriableStreamsRegistry.onShutdown(SHUTDOWN_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.uncommittedRetriableStreamsRegistry.onShutdownNow(SHUTDOWN_NOW_STATUS);
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdownNowed) {
                    ManagedChannelImpl.this.shutdownNowed = true;
                    ManagedChannelImpl.this.maybeShutdownNowSubchannels();
                }
            }
        });
        return this;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void panic(final Throwable th) {
        if (!this.panicMode) {
            this.panicMode = true;
            cancelIdleTimer(true);
            shutdownNameResolverAndLoadBalancer(false);
            updateSubchannelPicker(new SubchannelPicker() {
                private final PickResult panicPickResult = PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(th));

                public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
                    return this.panicPickResult;
                }
            });
            this.channelLogger.log(ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* access modifiers changed from: private */
    public void updateSubchannelPicker(SubchannelPicker subchannelPicker2) {
        this.subchannelPicker = subchannelPicker2;
        this.delayedTransport.reprocess(subchannelPicker2);
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor2 = callOptions.getExecutor();
        return executor2 == null ? this.executor : executor2;
    }

    /* access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.terminated = true;
            this.terminatedLatch.countDown();
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.transportFactory.close();
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public static Throttle getThrottle(Attributes attributes) {
        return ServiceConfigUtil.getThrottlePolicy((Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG));
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("target", (Object) this.target).toString();
    }
}
