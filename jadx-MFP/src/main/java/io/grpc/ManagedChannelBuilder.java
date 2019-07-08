package io.grpc;

import io.grpc.ManagedChannelBuilder;

public abstract class ManagedChannelBuilder<T extends ManagedChannelBuilder<T>> {
    public abstract ManagedChannel build();

    public static ManagedChannelBuilder<?> forAddress(String str, int i) {
        return ManagedChannelProvider.provider().builderForAddress(str, i);
    }

    @ExperimentalApi
    @Deprecated
    public T usePlaintext(boolean z) {
        throw new UnsupportedOperationException();
    }
}
