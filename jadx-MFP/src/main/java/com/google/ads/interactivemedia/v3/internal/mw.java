package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class mw extends cq {
    private static final Object b = new Object();
    private final long c;
    private final long d;
    private final long e;
    private final long f;
    private final long g;
    private final long h;
    private final boolean i;
    private final boolean j;
    private final Object k;

    public mw(long j2, boolean z, boolean z2, Object obj) {
        this(j2, j2, 0, 0, z, false, obj);
    }

    public final int b() {
        return 1;
    }

    public final int d() {
        return 1;
    }

    private mw(long j2, long j3, long j4, long j5, boolean z, boolean z2, Object obj) {
        this(-9223372036854775807L, -9223372036854775807L, j2, j3, 0, 0, z, z2, obj);
    }

    public mw(long j2, long j3, long j4, long j5, long j6, long j7, boolean z, boolean z2, Object obj) {
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = j6;
        this.h = j7;
        this.i = z;
        this.j = z2;
        this.k = obj;
    }

    public final ct a(int i2, ct ctVar, boolean z, long j2) {
        long j3;
        qi.a(i2, 0, 1);
        Object obj = z ? this.k : null;
        long j4 = this.h;
        if (!this.j || j2 == 0) {
            j3 = j4;
        } else {
            long j5 = this.f;
            if (j5 == -9223372036854775807L) {
                j3 = -9223372036854775807L;
            } else {
                long j6 = j4 + j2;
                j3 = j6 > j5 ? -9223372036854775807L : j6;
            }
        }
        return ctVar.a(obj, this.c, this.d, this.i, this.j, j3, this.f, 0, 0, this.g);
    }

    public final cs a(int i2, cs csVar, boolean z) {
        qi.a(i2, 0, 1);
        return csVar.a(null, z ? b : null, 0, this.e, -this.g);
    }

    public final int a(Object obj) {
        return b.equals(obj) ? 0 : -1;
    }

    public final Object a(int i2) {
        qi.a(i2, 0, 1);
        return b;
    }
}
