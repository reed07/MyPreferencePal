package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener.RpcProgress;
import io.grpc.internal.ClientTransport.PingCallback;
import java.util.concurrent.Executor;

class FailingClientTransport implements ClientTransport {
    @VisibleForTesting
    final Status error;
    private final RpcProgress rpcProgress;

    FailingClientTransport(Status status, RpcProgress rpcProgress2) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress2;
    }

    public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        return new FailingClientStream(this.error, this.rpcProgress);
    }

    public void ping(final PingCallback pingCallback, Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                pingCallback.onFailure(FailingClientTransport.this.error.asException());
            }
        });
    }

    public InternalLogId getLogId() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}
