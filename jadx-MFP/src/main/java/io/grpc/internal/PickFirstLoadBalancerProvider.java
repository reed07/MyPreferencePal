package io.grpc.internal;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancerProvider;

public final class PickFirstLoadBalancerProvider extends LoadBalancerProvider {
    public String getPolicyName() {
        return "pick_first";
    }

    public int getPriority() {
        return 5;
    }

    public boolean isAvailable() {
        return true;
    }

    public LoadBalancer newLoadBalancer(Helper helper) {
        return new PickFirstLoadBalancer(helper);
    }
}
