package io.fabric.sdk.android.services.concurrency.internal;

public class ExponentialBackoff implements Backoff {
    private final long baseTimeMillis;
    private final int power;

    public ExponentialBackoff(long j, int i) {
        this.baseTimeMillis = j;
        this.power = i;
    }

    public long getDelayMillis(int i) {
        return (long) (((double) this.baseTimeMillis) * Math.pow((double) this.power, (double) i));
    }
}
