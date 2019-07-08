package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ExperimentalApi;
import io.grpc.LoadBalancer;

@ExperimentalApi
public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
