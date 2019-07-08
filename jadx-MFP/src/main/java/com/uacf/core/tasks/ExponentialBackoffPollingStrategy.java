package com.uacf.core.tasks;

import java.util.concurrent.TimeUnit;

public class ExponentialBackoffPollingStrategy extends PollingStrategy {
    /* access modifiers changed from: protected */
    public long getNextInterval() {
        return TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, (double) this.count));
    }
}
