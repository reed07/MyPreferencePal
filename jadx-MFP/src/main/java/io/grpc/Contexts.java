package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Status.Code;
import java.util.concurrent.TimeoutException;

public final class Contexts {
    private Contexts() {
    }

    @ExperimentalApi
    public static Status statusFromCancelled(Context context) {
        Preconditions.checkNotNull(context, "context must not be null");
        if (!context.isCancelled()) {
            return null;
        }
        Throwable cancellationCause = context.cancellationCause();
        if (cancellationCause == null) {
            return Status.CANCELLED.withDescription("io.grpc.Context was cancelled without error");
        }
        if (cancellationCause instanceof TimeoutException) {
            return Status.DEADLINE_EXCEEDED.withDescription(cancellationCause.getMessage()).withCause(cancellationCause);
        }
        Status fromThrowable = Status.fromThrowable(cancellationCause);
        if (!Code.UNKNOWN.equals(fromThrowable.getCode()) || fromThrowable.getCause() != cancellationCause) {
            return fromThrowable.withCause(cancellationCause);
        }
        return Status.CANCELLED.withDescription("Context cancelled").withCause(cancellationCause);
    }
}
