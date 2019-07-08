package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ga implements fy {
    private final long a;
    private final fz b;

    public ga(long j) {
        this(j, 0);
    }

    public final boolean a() {
        return false;
    }

    public ga(long j, long j2) {
        this.a = j;
        this.b = new fz(j2 == 0 ? gb.a : new gb(0, j2));
    }

    public final long b() {
        return this.a;
    }

    public final fz a(long j) {
        return this.b;
    }
}
