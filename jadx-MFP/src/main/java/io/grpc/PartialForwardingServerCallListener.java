package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerCall.Listener;

abstract class PartialForwardingServerCallListener<ReqT> extends Listener<ReqT> {
    /* access modifiers changed from: protected */
    public abstract Listener<?> delegate();

    PartialForwardingServerCallListener() {
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
