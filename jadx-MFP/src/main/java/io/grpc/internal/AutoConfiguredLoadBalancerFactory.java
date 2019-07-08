package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import io.grpc.Attributes;
import io.grpc.ChannelLogger.ChannelLogLevel;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Factory;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancer.PickResult;
import io.grpc.LoadBalancer.PickSubchannelArgs;
import io.grpc.LoadBalancer.Subchannel;
import io.grpc.LoadBalancer.SubchannelPicker;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.Status;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

final class AutoConfiguredLoadBalancerFactory extends Factory {
    /* access modifiers changed from: private */
    public static final LoadBalancerRegistry registry = LoadBalancerRegistry.getDefaultRegistry();

    @VisibleForTesting
    static final class AutoConfiguredLoadBalancer extends LoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider = AutoConfiguredLoadBalancerFactory.registry.getProvider("pick_first");
        private final Helper helper;

        AutoConfiguredLoadBalancer(Helper helper2) {
            this.helper = helper2;
            LoadBalancerProvider loadBalancerProvider = this.delegateProvider;
            if (loadBalancerProvider != null) {
                this.delegate = loadBalancerProvider.newLoadBalancer(helper2);
                return;
            }
            throw new IllegalStateException("Could not find LoadBalancer pick_first. The build probably threw away META-INF/services/io.grpc.LoadBalancerProvider");
        }

        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
            try {
                LoadBalancerProvider decideLoadBalancerProvider = decideLoadBalancerProvider(list, (Map) attributes.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG));
                if (this.delegateProvider == null || !decideLoadBalancerProvider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                    this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                    this.delegate.shutdown();
                    this.delegateProvider = decideLoadBalancerProvider;
                    LoadBalancer loadBalancer = this.delegate;
                    this.delegate = this.delegateProvider.newLoadBalancer(this.helper);
                    this.helper.getChannelLogger().log(ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", loadBalancer.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
                }
                getDelegate().handleResolvedAddressGroups(list, attributes);
            } catch (PolicyNotFoundException e) {
                this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                this.delegate.shutdown();
                this.delegateProvider = null;
                this.delegate = new NoopLoadBalancer();
            }
        }

        public void handleNameResolutionError(Status status) {
            getDelegate().handleNameResolutionError(status);
        }

        public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
            getDelegate().handleSubchannelState(subchannel, connectivityStateInfo);
        }

        public void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        @VisibleForTesting
        static LoadBalancerProvider decideLoadBalancerProvider(List<EquivalentAddressGroup> list, @Nullable Map<String, Object> map) throws PolicyNotFoundException {
            boolean z;
            Iterator it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((EquivalentAddressGroup) it.next()).getAttributes().get(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY) != null) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return AutoConfiguredLoadBalancerFactory.getProviderOrThrow("grpclb", "NameResolver has returned balancer addresses");
            }
            if (map != null) {
                String loadBalancingPolicyFromServiceConfig = ServiceConfigUtil.getLoadBalancingPolicyFromServiceConfig(map);
                if (loadBalancingPolicyFromServiceConfig != null) {
                    return AutoConfiguredLoadBalancerFactory.getProviderOrThrow(Ascii.toLowerCase(loadBalancingPolicyFromServiceConfig), "service-config specifies load-balancing policy");
                }
            }
            return AutoConfiguredLoadBalancerFactory.getProviderOrThrow("pick_first", "Using default policy");
        }
    }

    private static final class EmptyPicker extends SubchannelPicker {
        private EmptyPicker() {
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withNoResult();
        }
    }

    private static final class FailingPicker extends SubchannelPicker {
        private final Status failure;

        FailingPicker(Status status) {
            this.failure = status;
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withError(this.failure);
        }
    }

    private static final class NoopLoadBalancer extends LoadBalancer {
        public void handleNameResolutionError(Status status) {
        }

        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        }

        public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        public void shutdown() {
        }

        private NoopLoadBalancer() {
        }
    }

    static final class PolicyNotFoundException extends Exception {
        private static final long serialVersionUID = 1;
        final String choiceReason;
        final String policy;

        private PolicyNotFoundException(String str, String str2) {
            this.policy = str;
            this.choiceReason = str2;
        }

        public String getMessage() {
            StringBuilder sb = new StringBuilder();
            sb.append("Trying to load '");
            sb.append(this.policy);
            sb.append("' because ");
            sb.append(this.choiceReason);
            sb.append(", but it's unavailable");
            return sb.toString();
        }
    }

    AutoConfiguredLoadBalancerFactory() {
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    /* access modifiers changed from: private */
    public static LoadBalancerProvider getProviderOrThrow(String str, String str2) throws PolicyNotFoundException {
        LoadBalancerProvider provider = registry.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new PolicyNotFoundException(str, str2);
    }
}
