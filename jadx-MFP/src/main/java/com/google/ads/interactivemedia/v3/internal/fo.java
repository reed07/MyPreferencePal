package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public class fo implements fy {
    private final long a;
    private final long b;
    private final int c;
    private final long d;
    private final int e;
    private final long f;

    public fo(long j, long j2, int i, int i2) {
        this.a = j;
        this.b = j2;
        if (i2 == -1) {
            i2 = 1;
        }
        this.c = i2;
        this.e = i;
        if (j == -1) {
            this.d = -1;
            this.f = -9223372036854775807L;
            return;
        }
        this.d = j - j2;
        this.f = a(j, j2, i);
    }

    public final boolean a() {
        return this.d != -1;
    }

    public final fz a(long j) {
        long j2 = this.d;
        if (j2 == -1) {
            return new fz(new gb(0, this.b));
        }
        long j3 = (((long) this.e) * j) / 8000000;
        int i = this.c;
        long a2 = this.b + vf.a((j3 / ((long) i)) * ((long) i), 0, j2 - ((long) i));
        long b2 = b(a2);
        gb gbVar = new gb(b2, a2);
        if (b2 < j) {
            int i2 = this.c;
            if (((long) i2) + a2 < this.a) {
                long j4 = a2 + ((long) i2);
                return new fz(gbVar, new gb(b(j4), j4));
            }
        }
        return new fz(gbVar);
    }

    public final long b() {
        return this.f;
    }

    public final long b(long j) {
        return a(j, this.b, this.e);
    }

    private static long a(long j, long j2, int i) {
        return ((Math.max(0, j - j2) << 3) * 1000000) / ((long) i);
    }
}
