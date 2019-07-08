package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class hi {
    public final gc a;
    public final ht b = new ht();
    public hr c;
    public hd d;
    public int e;
    public int f;
    public int g;
    public int h;
    private final ut i = new ut(1);
    private final ut j = new ut();

    public hi(gc gcVar) {
        this.a = gcVar;
    }

    public final void a(hr hrVar, hd hdVar) {
        this.c = (hr) qi.a(hrVar);
        this.d = (hd) qi.a(hdVar);
        this.a.a(hrVar.f);
        a();
    }

    public final void a() {
        ht htVar = this.b;
        htVar.d = 0;
        htVar.r = 0;
        htVar.l = false;
        htVar.q = false;
        htVar.n = null;
        this.e = 0;
        this.g = 0;
        this.f = 0;
        this.h = 0;
    }

    public final boolean b() {
        this.e++;
        this.f++;
        int i2 = this.f;
        int[] iArr = this.b.g;
        int i3 = this.g;
        if (i2 != iArr[i3]) {
            return true;
        }
        this.g = i3 + 1;
        this.f = 0;
        return false;
    }

    public final int c() {
        ut utVar;
        int i2;
        hs e2 = e();
        if (e2 == null) {
            return 0;
        }
        if (e2.d != 0) {
            utVar = this.b.p;
            i2 = e2.d;
        } else {
            byte[] bArr = e2.e;
            this.j.a(bArr, bArr.length);
            utVar = this.j;
            i2 = bArr.length;
        }
        boolean c2 = this.b.c(this.e);
        this.i.a[0] = (byte) ((c2 ? 128 : 0) | i2);
        this.i.c(0);
        this.a.a(this.i, 1);
        this.a.a(utVar, i2);
        if (!c2) {
            return i2 + 1;
        }
        ut utVar2 = this.b.p;
        int f2 = utVar2.f();
        utVar2.d(-2);
        int i3 = (f2 * 6) + 2;
        this.a.a(utVar2, i3);
        return i2 + 1 + i3;
    }

    /* access modifiers changed from: private */
    public final void d() {
        hs e2 = e();
        if (e2 != null) {
            ut utVar = this.b.p;
            if (e2.d != 0) {
                utVar.d(e2.d);
            }
            if (this.b.c(this.e)) {
                utVar.d(utVar.f() * 6);
            }
        }
    }

    /* access modifiers changed from: private */
    public final hs e() {
        hs hsVar;
        int i2 = this.b.a.a;
        if (this.b.n != null) {
            hsVar = this.b.n;
        } else {
            hsVar = this.c.a(i2);
        }
        if (hsVar == null || !hsVar.a) {
            return null;
        }
        return hsVar;
    }
}
