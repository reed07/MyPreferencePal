package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer.Helper;

@ExperimentalApi
public abstract class ForwardingLoadBalancerHelper extends Helper {
    /* access modifiers changed from: protected */
    public abstract Helper delegate();

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
