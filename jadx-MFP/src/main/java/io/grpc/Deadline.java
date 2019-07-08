package io.grpc;

import java.util.concurrent.TimeUnit;

public final class Deadline implements Comparable<Deadline> {
    private static final long MAX_OFFSET = TimeUnit.DAYS.toNanos(36500);
    private static final long MIN_OFFSET = (-MAX_OFFSET);
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private final long deadlineNanos;
    private volatile boolean expired;
    private final Ticker ticker;

    private static class SystemTicker extends Ticker {
        private SystemTicker() {
        }

        public long read() {
            return System.nanoTime();
        }
    }

    static abstract class Ticker {
        public abstract long read();

        Ticker() {
        }
    }

    public static Deadline after(long j, TimeUnit timeUnit) {
        return after(j, timeUnit, SYSTEM_TICKER);
    }

    static Deadline after(long j, TimeUnit timeUnit, Ticker ticker2) {
        checkNotNull(timeUnit, "units");
        return new Deadline(ticker2, timeUnit.toNanos(j), true);
    }

    private Deadline(Ticker ticker2, long j, boolean z) {
        this(ticker2, ticker2.read(), j, z);
    }

    private Deadline(Ticker ticker2, long j, long j2, boolean z) {
        this.ticker = ticker2;
        long min = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, j2));
        this.deadlineNanos = j + min;
        this.expired = z && min <= 0;
    }

    public boolean isExpired() {
        if (!this.expired) {
            if (this.deadlineNanos - this.ticker.read() > 0) {
                return false;
            }
            this.expired = true;
        }
        return true;
    }

    public boolean isBefore(Deadline deadline) {
        return this.deadlineNanos - deadline.deadlineNanos < 0;
    }

    public Deadline minimum(Deadline deadline) {
        return isBefore(deadline) ? this : deadline;
    }

    public long timeRemaining(TimeUnit timeUnit) {
        long read = this.ticker.read();
        if (!this.expired && this.deadlineNanos - read <= 0) {
            this.expired = true;
        }
        return timeUnit.convert(this.deadlineNanos - read, TimeUnit.NANOSECONDS);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(timeRemaining(TimeUnit.NANOSECONDS));
        sb.append(" ns from now");
        return sb.toString();
    }

    public int compareTo(Deadline deadline) {
        int i = ((this.deadlineNanos - deadline.deadlineNanos) > 0 ? 1 : ((this.deadlineNanos - deadline.deadlineNanos) == 0 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    private static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
