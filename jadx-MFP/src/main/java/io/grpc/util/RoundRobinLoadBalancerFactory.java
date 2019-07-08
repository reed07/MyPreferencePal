package io.grpc.util;

import com.google.common.base.Preconditions;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Factory;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;

@ExperimentalApi
@Deprecated
public final class RoundRobinLoadBalancerFactory extends Factory {
    private final LoadBalancerProvider provider = ((LoadBalancerProvider) Preconditions.checkNotNull(LoadBalancerRegistry.getDefaultRegistry().getProvider("round_robin"), "round_robin balancer not available"));

    private RoundRobinLoadBalancerFactory() {
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return this.provider.newLoadBalancer(helper);
    }
}
