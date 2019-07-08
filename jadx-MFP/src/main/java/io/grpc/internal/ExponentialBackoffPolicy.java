package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class ExponentialBackoffPolicy implements BackoffPolicy {
    private long initialBackoffNanos = TimeUnit.SECONDS.toNanos(1);
    private double jitter = 0.2d;
    private long maxBackoffNanos = TimeUnit.MINUTES.toNanos(2);
    private double multiplier = 1.6d;
    private long nextBackoffNanos = this.initialBackoffNanos;
    private Random random = new Random();

    public static final class Provider implements io.grpc.internal.BackoffPolicy.Provider {
        public BackoffPolicy get() {
            return new ExponentialBackoffPolicy();
        }
    }

    public long nextBackoffNanos() {
        long j = this.nextBackoffNanos;
        double d = (double) j;
        this.nextBackoffNanos = Math.min((long) (this.multiplier * d), this.maxBackoffNanos);
        double d2 = this.jitter;
        return j + uniformRandom((-d2) * d, d2 * d);
    }

    private long uniformRandom(double d, double d2) {
        Preconditions.checkArgument(d2 >= d);
        return (long) ((this.random.nextDouble() * (d2 - d)) + d);
    }
}
