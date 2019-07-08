package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.RetryState;

class RetryManager {
    long lastRetry;
    private RetryState retryState;

    public RetryManager(RetryState retryState2) {
        if (retryState2 != null) {
            this.retryState = retryState2;
            return;
        }
        throw new NullPointerException("retryState must not be null");
    }

    public boolean canRetry(long j) {
        return j - this.lastRetry >= this.retryState.getRetryDelay() * 1000000;
    }

    public void recordRetry(long j) {
        this.lastRetry = j;
        this.retryState = this.retryState.nextRetryState();
    }

    public void reset() {
        this.lastRetry = 0;
        this.retryState = this.retryState.initialRetryState();
    }
}
