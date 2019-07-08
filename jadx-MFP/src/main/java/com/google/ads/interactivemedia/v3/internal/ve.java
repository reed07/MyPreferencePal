package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ve {
    private long a;
    private long b;
    private volatile long c = -9223372036854775807L;

    public ve(long j) {
        a(j);
    }

    public final synchronized void a(long j) {
        qi.c(this.c == -9223372036854775807L);
        this.a = j;
    }

    public final long a() {
        return this.a;
    }

    public final long b() {
        if (this.c != -9223372036854775807L) {
            return this.c + this.b;
        }
        long j = this.a;
        if (j != Long.MAX_VALUE) {
            return j;
        }
        return -9223372036854775807L;
    }

    public final long c() {
        if (this.a == Long.MAX_VALUE) {
            return 0;
        }
        if (this.c == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.b;
    }

    public final void d() {
        this.c = -9223372036854775807L;
    }

    public final long b(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            long e = e(this.c);
            long j2 = (4294967296L + e) / 8589934592L;
            long j3 = ((j2 - 1) * 8589934592L) + j;
            j += j2 * 8589934592L;
            if (Math.abs(j3 - e) < Math.abs(j - e)) {
                j = j3;
            }
        }
        return c(d(j));
    }

    public final long c(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            this.c = j;
        } else {
            long j2 = this.a;
            if (j2 != Long.MAX_VALUE) {
                this.b = j2 - j;
            }
            synchronized (this) {
                this.c = j;
                notifyAll();
            }
        }
        return j + this.b;
    }

    public final synchronized void e() throws InterruptedException {
        while (this.c == -9223372036854775807L) {
            wait();
        }
    }

    public static long d(long j) {
        return (j * 1000000) / 90000;
    }

    public static long e(long j) {
        return (j * 90000) / 1000000;
    }
}
