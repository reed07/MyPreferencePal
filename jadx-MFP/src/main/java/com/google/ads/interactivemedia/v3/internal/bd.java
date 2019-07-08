package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: IMASDK */
final class bd implements Runnable {
    private final cb a;
    private final CopyOnWriteArrayList<ar> b;
    private final rz c;
    private final boolean d;
    private final int e;
    private final int f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;

    public bd(cb cbVar, cb cbVar2, CopyOnWriteArrayList<ar> copyOnWriteArrayList, rz rzVar, boolean z, int i2, int i3, boolean z2, boolean z3) {
        this.a = cbVar;
        this.b = new CopyOnWriteArrayList<>(copyOnWriteArrayList);
        this.c = rzVar;
        this.d = z;
        this.e = i2;
        this.f = i3;
        this.g = z2;
        this.l = z3;
        boolean z4 = true;
        this.h = cbVar2.f != cbVar.f;
        this.i = (cbVar2.a == cbVar.a && cbVar2.b == cbVar.b) ? false : true;
        this.j = cbVar2.g != cbVar.g;
        if (cbVar2.i == cbVar.i) {
            z4 = false;
        }
        this.k = z4;
    }

    public final void run() {
        if (this.i || this.f == 0) {
            ay.c(this.b, new be(this));
        }
        if (this.d) {
            ay.c(this.b, new bf(this));
        }
        if (this.k) {
            this.c.a(this.a.i.d);
            ay.c(this.b, new bg(this));
        }
        if (this.j) {
            ay.c(this.b, new bh(this));
        }
        if (this.h) {
            ay.c(this.b, new bi(this));
        }
        if (this.g) {
            ay.c(this.b, bj.a);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(cf cfVar) {
        cfVar.a(this.l, this.a.f);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(cf cfVar) {
        cfVar.a(this.a.g);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void c(cf cfVar) {
        cfVar.a(this.a.h, this.a.i.c);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d(cf cfVar) {
        cfVar.a_(this.e);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void e(cf cfVar) {
        cfVar.a(this.a.a, this.a.b, this.f);
    }
}
