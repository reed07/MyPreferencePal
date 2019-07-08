package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class cb {
    private static final lo n = new lo(new Object());
    public final cq a;
    public final Object b;
    public final lo c;
    public final long d;
    public final long e;
    public final int f;
    public final boolean g;
    public final mz h;
    public final sb i;
    public final lo j;
    public volatile long k;
    public volatile long l;
    public volatile long m;

    public static cb a(long j2, sb sbVar) {
        sb sbVar2 = sbVar;
        cb cbVar = new cb(cq.a, null, n, j2, -9223372036854775807L, 1, false, mz.a, sbVar2, n, j2, 0, j2);
        return cbVar;
    }

    public cb(cq cqVar, Object obj, lo loVar, long j2, long j3, int i2, boolean z, mz mzVar, sb sbVar, lo loVar2, long j4, long j5, long j6) {
        this.a = cqVar;
        this.b = obj;
        this.c = loVar;
        this.d = j2;
        this.e = j3;
        this.f = i2;
        this.g = z;
        this.h = mzVar;
        this.i = sbVar;
        this.j = loVar2;
        this.k = j4;
        this.l = j5;
        this.m = j6;
    }

    public final lo a(boolean z, ct ctVar) {
        if (this.a.a()) {
            return n;
        }
        cq cqVar = this.a;
        return new lo(this.a.a(cqVar.a(cqVar.c(), ctVar, false, 0).b));
    }

    public final cb a(lo loVar, long j2, long j3) {
        cb cbVar = new cb(this.a, this.b, loVar, j2, loVar.a() ? j3 : -9223372036854775807L, this.f, this.g, this.h, this.i, loVar, j2, 0, j2);
        return cbVar;
    }

    public final cb a(lo loVar, long j2, long j3, long j4) {
        cb cbVar = new cb(this.a, this.b, loVar, j2, loVar.a() ? j3 : -9223372036854775807L, this.f, this.g, this.h, this.i, this.j, this.k, j4, j2);
        return cbVar;
    }

    public final cb a(mz mzVar, sb sbVar) {
        mz mzVar2 = mzVar;
        sb sbVar2 = sbVar;
        cb cbVar = new cb(this.a, this.b, this.c, this.d, this.e, this.f, this.g, mzVar2, sbVar2, this.j, this.k, this.l, this.m);
        return cbVar;
    }
}
