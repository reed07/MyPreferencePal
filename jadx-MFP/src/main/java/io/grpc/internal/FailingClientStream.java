package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener.RpcProgress;

public final class FailingClientStream extends NoopClientStream {
    private final Status error;
    private final RpcProgress rpcProgress;
    private boolean started;

    public FailingClientStream(Status status) {
        this(status, RpcProgress.PROCESSED);
    }

    public FailingClientStream(Status status, RpcProgress rpcProgress2) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress2;
    }

    public void start(ClientStreamListener clientStreamListener) {
        Preconditions.checkState(!this.started, "already started");
        this.started = true;
        clientStreamListener.closed(this.error, this.rpcProgress, new Metadata());
    }
}
