package io.grpc.internal;

import io.grpc.LoadBalancer.Subchannel;
import javax.annotation.Nullable;

abstract class AbstractSubchannel extends Subchannel {
    /* access modifiers changed from: 0000 */
    @Nullable
    public abstract ClientTransport obtainActiveTransport();

    AbstractSubchannel() {
    }
}
