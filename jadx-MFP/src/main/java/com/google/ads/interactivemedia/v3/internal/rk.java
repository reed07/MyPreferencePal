package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class rk implements Comparable<rk> {
    public final boolean a;
    private final rl b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;

    public rk(bs bsVar, rl rlVar, int i) {
        this.b = rlVar;
        boolean z = false;
        this.c = ri.a(i, false) ? 1 : 0;
        this.d = ri.a(bsVar, rlVar.v);
        this.e = (bsVar.c & 1) != 0 ? 1 : 0;
        this.f = bsVar.s;
        this.g = bsVar.t;
        this.h = bsVar.d;
        if ((bsVar.d == -1 || bsVar.d <= rlVar.m) && (bsVar.s == -1 || bsVar.s <= rlVar.l)) {
            z = true;
        }
        this.a = z;
    }

    /* renamed from: a */
    public final int compareTo(rk rkVar) {
        int i = this.c;
        int i2 = rkVar.c;
        if (i != i2) {
            return ri.d(i, i2);
        }
        int i3 = this.d;
        int i4 = rkVar.d;
        if (i3 != i4) {
            return ri.d(i3, i4);
        }
        boolean z = this.a;
        int i5 = -1;
        if (z != rkVar.a) {
            return z ? 1 : -1;
        }
        if (this.b.q) {
            int b2 = ri.c(this.h, rkVar.h);
            if (b2 != 0) {
                return b2 > 0 ? -1 : 1;
            }
        }
        int i6 = this.e;
        int i7 = rkVar.e;
        if (i6 != i7) {
            return ri.d(i6, i7);
        }
        if (this.a && this.c == 1) {
            i5 = 1;
        }
        int i8 = this.f;
        int i9 = rkVar.f;
        if (i8 != i9) {
            return i5 * ri.d(i8, i9);
        }
        int i10 = this.g;
        int i11 = rkVar.g;
        if (i10 != i11) {
            return i5 * ri.d(i10, i11);
        }
        return i5 * ri.d(this.h, rkVar.h);
    }
}
