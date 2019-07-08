package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.LoadBalancer.Factory;
import io.grpc.LoadBalancer.Helper;

@ExperimentalApi
@Deprecated
public final class PickFirstBalancerFactory extends Factory {
    private final LoadBalancerProvider provider = ((LoadBalancerProvider) Preconditions.checkNotNull(LoadBalancerRegistry.getDefaultRegistry().getProvider("pick_first"), "pick_first balancer not available"));

    private PickFirstBalancerFactory() {
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return this.provider.newLoadBalancer(helper);
    }
}
