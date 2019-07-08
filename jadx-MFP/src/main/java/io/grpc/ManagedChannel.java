package io.grpc;

import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class ManagedChannel extends Channel {
    public abstract boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    public abstract boolean isTerminated();

    public abstract ManagedChannel shutdown();

    public abstract ManagedChannel shutdownNow();
}
