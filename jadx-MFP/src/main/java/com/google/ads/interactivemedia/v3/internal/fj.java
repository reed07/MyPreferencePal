package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class fj {
    private final long a;
    private final long b;
    private final long c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;

    protected static long a(long j, long j2, long j3, long j4, long j5, long j6) {
        if (j4 + 1 >= j5 || j2 + 1 >= j3) {
            return j4;
        }
        long j7 = (long) (((float) (j - j2)) * (((float) (j5 - j4)) / ((float) (j3 - j2))));
        return vf.a(((j7 + j4) - j6) - (j7 / 20), j4, j5 - 1);
    }

    protected fj(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.a = j;
        this.b = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = j6;
        this.c = j7;
        this.h = a(j2, j3, j4, j5, j6, j7);
    }

    /* access modifiers changed from: private */
    public final long a() {
        return this.f;
    }

    /* access modifiers changed from: private */
    public final long b() {
        return this.g;
    }

    /* access modifiers changed from: private */
    public final long c() {
        return this.b;
    }

    /* access modifiers changed from: private */
    public final long d() {
        return this.a;
    }

    /* access modifiers changed from: private */
    public final void a(long j, long j2) {
        this.d = j;
        this.f = j2;
        f();
    }

    /* access modifiers changed from: private */
    public final void b(long j, long j2) {
        this.e = j;
        this.g = j2;
        f();
    }

    /* access modifiers changed from: private */
    public final long e() {
        return this.h;
    }

    private final void f() {
        this.h = a(this.b, this.d, this.e, this.f, this.g, this.c);
    }
}
