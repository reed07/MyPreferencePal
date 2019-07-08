package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public final class re extends rg {
    private final rf b;
    private final long c;
    private final long d;
    private final long e;
    private final float f;
    private final long g;
    private final ua h;
    private final bs[] i;
    private final int[] j;
    private final int[] k;
    private rq l;
    private float m;
    private int n;
    private int o;
    private long p;

    private re(mx mxVar, int[] iArr, rf rfVar, long j2, long j3, long j4, float f2, long j5, ua uaVar) {
        super(mxVar, iArr);
        this.b = rfVar;
        this.c = j2 * 1000;
        this.d = j3 * 1000;
        this.e = j4 * 1000;
        this.f = f2;
        this.g = j5;
        this.h = uaVar;
        this.m = 1.0f;
        this.o = 0;
        this.p = -9223372036854775807L;
        this.l = rq.a;
        this.i = new bs[this.a];
        this.j = new int[this.a];
        this.k = new int[this.a];
        for (int i2 = 0; i2 < this.a; i2++) {
            bs a = a(i2);
            bs[] bsVarArr = this.i;
            bsVarArr[i2] = a;
            this.j[i2] = bsVarArr[i2].d;
        }
    }

    public final Object c() {
        return null;
    }

    public final void a(rq rqVar) {
        this.l = rqVar;
    }

    public final void d() {
        this.p = -9223372036854775807L;
    }

    public final void a(float f2) {
        this.m = f2;
    }

    public final void a(long j2, long j3, long j4, List<? extends ns> list, nt[] ntVarArr) {
        long j5;
        long a = this.h.a();
        this.l.a(this.i, list, ntVarArr, this.k);
        boolean z = true;
        if (this.o == 0) {
            this.o = 1;
            this.n = a(a, this.k);
            return;
        }
        int i2 = this.n;
        this.n = a(a, this.k);
        if (this.n != i2) {
            if (!b(i2, a)) {
                bs a2 = a(i2);
                bs a3 = a(this.n);
                if (a3.d > a2.d) {
                    if (j4 == -9223372036854775807L || j4 > this.c) {
                        z = false;
                    }
                    if (z) {
                        j5 = (long) (((float) j4) * this.f);
                    } else {
                        j5 = this.c;
                    }
                    if (j3 < j5) {
                        this.n = i2;
                    }
                }
                if (a3.d < a2.d && j3 >= this.d) {
                    this.n = i2;
                }
            }
            if (this.n != i2) {
                this.o = 3;
            }
        }
    }

    public final int a() {
        return this.n;
    }

    public final int b() {
        return this.o;
    }

    public final int a(long j2, List<? extends ns> list) {
        long a = this.h.a();
        long j3 = this.p;
        if (!(j3 == -9223372036854775807L || a - j3 >= this.g)) {
            return list.size();
        }
        this.p = a;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long b2 = vf.b(((ns) list.get(size - 1)).h - j2, this.m);
        long j4 = this.e;
        if (b2 < j4) {
            return size;
        }
        bs a2 = a(a(a, this.j));
        for (int i2 = 0; i2 < size; i2++) {
            ns nsVar = (ns) list.get(i2);
            bs bsVar = nsVar.e;
            if (vf.b(nsVar.h - j2, this.m) >= j4 && bsVar.d < a2.d && bsVar.n != -1 && bsVar.n < 720 && bsVar.m != -1 && bsVar.m < 1280 && bsVar.n < a2.n) {
                return i2;
            }
        }
        return size;
    }

    private final int a(long j2, int[] iArr) {
        long a = this.b.a();
        int i2 = 0;
        for (int i3 = 0; i3 < this.a; i3++) {
            if (j2 == Long.MIN_VALUE || !b(i3, j2)) {
                if (((long) Math.round(((float) iArr[i3]) * this.m)) <= a) {
                    return i3;
                }
                i2 = i3;
            }
        }
        return i2;
    }

    /* synthetic */ re(mx mxVar, int[] iArr, rf rfVar, long j2, long j3, long j4, float f2, long j5, ua uaVar, byte b2) {
        this(mxVar, iArr, rfVar, j2, j3, j4, f2, j5, uaVar);
    }
}
