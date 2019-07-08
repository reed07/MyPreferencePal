package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ForwardingChannelBuilder;

@ExperimentalApi
public abstract class ForwardingChannelBuilder<T extends ForwardingChannelBuilder<T>> extends ManagedChannelBuilder<T> {
    /* access modifiers changed from: protected */
    public abstract ManagedChannelBuilder<?> delegate();

    protected ForwardingChannelBuilder() {
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
