package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.util.concurrent.TimeUnit;

abstract class ForwardingManagedChannel extends ManagedChannel {
    private final ManagedChannel delegate;

    ForwardingManagedChannel(ManagedChannel managedChannel) {
        this.delegate = managedChannel;
    }

    public ManagedChannel shutdown() {
        return this.delegate.shutdown();
    }

    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public ManagedChannel shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        return this.delegate.newCall(methodDescriptor, callOptions);
    }

    public String authority() {
        return this.delegate.authority();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.delegate).toString();
    }
}
