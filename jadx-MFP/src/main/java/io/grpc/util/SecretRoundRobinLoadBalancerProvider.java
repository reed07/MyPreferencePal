package io.grpc.util;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancer.Helper;
import io.grpc.LoadBalancerProvider;

final class SecretRoundRobinLoadBalancerProvider {

    public static final class Provider extends LoadBalancerProvider {
        public String getPolicyName() {
            return "round_robin";
        }

        public int getPriority() {
            return 5;
        }

        public boolean isAvailable() {
            return true;
        }

        public LoadBalancer newLoadBalancer(Helper helper) {
            return new RoundRobinLoadBalancer(helper);
        }
    }

    private SecretRoundRobinLoadBalancerProvider() {
    }
}
