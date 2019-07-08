package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class bx {
    public final ll a;
    public final Object b;
    public final mt[] c;
    public boolean d;
    public boolean e;
    public by f;
    private final boolean[] g;
    private final cj[] h;
    private final rz i;
    private final ln j;
    private bx k;
    private mz l;
    private sb m;
    private long n;

    public bx(cj[] cjVarArr, long j2, rz rzVar, sf sfVar, ln lnVar, by byVar) {
        le leVar;
        this.h = cjVarArr;
        this.n = j2 - byVar.b;
        this.i = rzVar;
        this.j = lnVar;
        this.b = byVar.a.a;
        this.f = byVar;
        this.c = new mt[cjVarArr.length];
        this.g = new boolean[cjVarArr.length];
        lo loVar = byVar.a;
        long j3 = byVar.b;
        long j4 = byVar.d;
        ll a2 = lnVar.a(loVar, sfVar);
        if (j4 == -9223372036854775807L || j4 == Long.MIN_VALUE) {
            leVar = a2;
        } else {
            le leVar2 = new le(a2, true, 0, j4);
            leVar = leVar2;
        }
        this.a = leVar;
    }

    public final long a(long j2) {
        return j2 + this.n;
    }

    public final long b(long j2) {
        return j2 - this.n;
    }

    public final long a() {
        return this.n;
    }

    public final long b() {
        return this.f.b + this.n;
    }

    public final boolean c() {
        return this.d && (!this.e || this.a.d() == Long.MIN_VALUE);
    }

    public final long d() {
        if (!this.d) {
            return this.f.b;
        }
        long d2 = this.e ? this.a.d() : Long.MIN_VALUE;
        return d2 == Long.MIN_VALUE ? this.f.e : d2;
    }

    public final void a(float f2, cq cqVar) throws aw {
        this.d = true;
        this.l = this.a.b();
        long a2 = a((sb) qi.a(b(f2, cqVar)), this.f.b, false);
        this.n += this.f.b - a2;
        by byVar = this.f;
        if (a2 != byVar.b) {
            by byVar2 = new by(byVar.a, a2, byVar.c, byVar.d, byVar.e, byVar.f, byVar.g);
            byVar = byVar2;
        }
        this.f = byVar;
    }

    public final void c(long j2) {
        qi.c(k());
        if (this.d) {
            this.a.a(b(j2));
        }
    }

    public final void d(long j2) {
        qi.c(k());
        this.a.c(b(j2));
    }

    public final sb b(float f2, cq cqVar) throws aw {
        boolean z;
        rt[] a2;
        sb a3 = this.i.a(this.h, g());
        sb sbVar = this.m;
        if (sbVar != null && sbVar.c.a == a3.c.a) {
            int i2 = 0;
            while (true) {
                if (i2 >= a3.c.a) {
                    z = true;
                    break;
                } else if (!a3.a(sbVar, i2)) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return null;
        }
        for (rt rtVar : a3.c.a()) {
            if (rtVar != null) {
                rtVar.a(f2);
            }
        }
        return a3;
    }

    public final long a(sb sbVar, long j2, boolean z) {
        return a(sbVar, j2, false, new boolean[this.h.length]);
    }

    public final long a(sb sbVar, long j2, boolean z, boolean[] zArr) {
        sb sbVar2 = sbVar;
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= sbVar2.a) {
                break;
            }
            boolean[] zArr2 = this.g;
            if (z || !sbVar2.a(this.m, i2)) {
                z2 = false;
            }
            zArr2[i2] = z2;
            i2++;
        }
        mt[] mtVarArr = this.c;
        int i3 = 0;
        while (true) {
            cj[] cjVarArr = this.h;
            if (i3 >= cjVarArr.length) {
                break;
            }
            if (cjVarArr[i3].a() == 6) {
                mtVarArr[i3] = null;
            }
            i3++;
        }
        j();
        this.m = sbVar2;
        i();
        rw rwVar = sbVar2.c;
        long a2 = this.a.a(rwVar.a(), this.g, this.c, zArr, j2);
        mt[] mtVarArr2 = this.c;
        sb sbVar3 = (sb) qi.a(this.m);
        int i4 = 0;
        while (true) {
            cj[] cjVarArr2 = this.h;
            if (i4 >= cjVarArr2.length) {
                break;
            }
            if (cjVarArr2[i4].a() == 6 && sbVar3.a(i4)) {
                mtVarArr2[i4] = new li();
            }
            i4++;
        }
        this.e = false;
        int i5 = 0;
        while (true) {
            mt[] mtVarArr3 = this.c;
            if (i5 >= mtVarArr3.length) {
                return a2;
            }
            if (mtVarArr3[i5] != null) {
                qi.c(sbVar2.a(i5));
                if (this.h[i5].a() != 6) {
                    this.e = true;
                }
            } else {
                qi.c(rwVar.a(i5) == null);
            }
            i5++;
        }
    }

    public final void e() {
        j();
        this.m = null;
        long j2 = this.f.d;
        ln lnVar = this.j;
        ll llVar = this.a;
        if (j2 == -9223372036854775807L || j2 == Long.MIN_VALUE) {
            lnVar.a(llVar);
            return;
        }
        try {
            lnVar.a(((le) llVar).a);
        } catch (RuntimeException e2) {
            uk.b("MediaPeriodHolder", "Period release failed.", e2);
        }
    }

    public final void a(bx bxVar) {
        if (bxVar != this.k) {
            j();
            this.k = bxVar;
            i();
        }
    }

    public final bx f() {
        return this.k;
    }

    public final mz g() {
        return (mz) qi.a(this.l);
    }

    public final sb h() {
        return (sb) qi.a(this.m);
    }

    private final void i() {
        sb sbVar = this.m;
        if (k() && sbVar != null) {
            for (int i2 = 0; i2 < sbVar.a; i2++) {
                boolean a2 = sbVar.a(i2);
                rt a3 = sbVar.c.a(i2);
                if (a2 && a3 != null) {
                    a3.d();
                }
            }
        }
    }

    private final void j() {
        sb sbVar = this.m;
        if (k() && sbVar != null) {
            for (int i2 = 0; i2 < sbVar.a; i2++) {
                boolean a2 = sbVar.a(i2);
                rt a3 = sbVar.c.a(i2);
                if (a2 && a3 != null) {
                    a3.e();
                }
            }
        }
    }

    private final boolean k() {
        return this.k == null;
    }
}
