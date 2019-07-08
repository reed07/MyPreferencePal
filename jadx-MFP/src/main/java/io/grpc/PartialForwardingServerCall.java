package io.grpc;

import com.google.common.base.MoreObjects;

abstract class PartialForwardingServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    /* access modifiers changed from: protected */
    public abstract ServerCall<?, ?> delegate();

    PartialForwardingServerCall() {
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
