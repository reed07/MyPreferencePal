package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public abstract class cj implements ci {
    private final int a;
    private ck b;
    private int c;
    private int d;
    private mt e;
    private bs[] f;
    private long g;
    private long h = Long.MIN_VALUE;
    private boolean i;

    public int a() {
        return this.a;
    }

    public abstract int a(bs bsVar) throws aw;

    public void a(float f2) throws aw {
    }

    public void a(int i2, Object obj) throws aw {
    }

    /* access modifiers changed from: protected */
    public void a(long j, boolean z) throws aw {
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) throws aw {
    }

    /* access modifiers changed from: protected */
    public void a(bs[] bsVarArr, long j) throws aw {
    }

    public cj b() {
        return this;
    }

    public um c() {
        return null;
    }

    public int s() throws aw {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void t() throws aw {
    }

    /* access modifiers changed from: protected */
    public void u() throws aw {
    }

    /* access modifiers changed from: protected */
    public void v() {
    }

    /* access modifiers changed from: protected */
    public void w() {
    }

    public cj(int i2) {
        this.a = i2;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public int f() {
        return this.d;
    }

    public void a(ck ckVar, bs[] bsVarArr, mt mtVar, long j, boolean z, long j2) throws aw {
        qi.c(this.d == 0);
        this.b = ckVar;
        this.d = 1;
        a(z);
        a(bsVarArr, mtVar, j2);
        a(j, z);
    }

    public void g() throws aw {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        qi.c(z);
        this.d = 2;
        t();
    }

    public void a(bs[] bsVarArr, mt mtVar, long j) throws aw {
        qi.c(!this.i);
        this.e = mtVar;
        this.h = j;
        this.f = bsVarArr;
        this.g = j;
        a(bsVarArr, j);
    }

    public mt h() {
        return this.e;
    }

    public boolean i() {
        return this.h == Long.MIN_VALUE;
    }

    public long j() {
        return this.h;
    }

    public void k() {
        this.i = true;
    }

    public boolean l() {
        return this.i;
    }

    public void m() throws IOException {
        this.e.c();
    }

    public void a(long j) throws aw {
        this.i = false;
        this.h = j;
        a(j, false);
    }

    public void p() throws aw {
        qi.c(this.d == 2);
        this.d = 1;
        u();
    }

    public void q() {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        qi.c(z);
        this.d = 0;
        this.e = null;
        this.f = null;
        this.i = false;
        v();
    }

    public void r() {
        qi.c(this.d == 0);
        w();
    }

    /* access modifiers changed from: protected */
    public bs[] x() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public ck y() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public int z() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public int a(bu buVar, ex exVar, boolean z) {
        int a2 = this.e.a(buVar, exVar, z);
        if (a2 == -4) {
            if (exVar.c()) {
                this.h = Long.MIN_VALUE;
                if (this.i) {
                    return -4;
                }
                return -3;
            }
            exVar.c += this.g;
            this.h = Math.max(this.h, exVar.c);
        } else if (a2 == -5) {
            bs bsVar = buVar.a;
            if (bsVar.l != Long.MAX_VALUE) {
                buVar.a = bsVar.a(bsVar.l + this.g);
            }
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public int b(long j) {
        return this.e.a_(j - this.g);
    }

    /* access modifiers changed from: protected */
    public boolean A() {
        return i() ? this.i : this.e.b();
    }

    protected static boolean a(fe<?> feVar, fa faVar) {
        if (faVar == null) {
            return true;
        }
        if (feVar == null) {
            return false;
        }
        return feVar.a();
    }
}
