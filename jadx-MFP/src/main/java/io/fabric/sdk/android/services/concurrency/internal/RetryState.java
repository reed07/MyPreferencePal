package io.fabric.sdk.android.services.concurrency.internal;

public class RetryState {
    private final Backoff backoff;
    private final int retryCount;
    private final RetryPolicy retryPolicy;

    public RetryState(Backoff backoff2, RetryPolicy retryPolicy2) {
        this(0, backoff2, retryPolicy2);
    }

    public RetryState(int i, Backoff backoff2, RetryPolicy retryPolicy2) {
        this.retryCount = i;
        this.backoff = backoff2;
        this.retryPolicy = retryPolicy2;
    }

    public long getRetryDelay() {
        return this.backoff.getDelayMillis(this.retryCount);
    }

    public RetryState nextRetryState() {
        return new RetryState(this.retryCount + 1, this.backoff, this.retryPolicy);
    }

    public RetryState initialRetryState() {
        return new RetryState(this.backoff, this.retryPolicy);
    }
}
