package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import java.util.Arrays;

/* compiled from: IMASDK */
final class ig {
    private final gc a;
    private final boolean b;
    private final boolean c;
    private final SparseArray<ur> d = new SparseArray<>();
    private final SparseArray<uq> e = new SparseArray<>();
    private final uu f = new uu(this.g, 0, 0);
    private byte[] g = new byte[128];
    private int h;
    private int i;
    private long j;
    private boolean k;
    private long l;
    private ih m = new ih(0);
    private ih n = new ih(0);
    private boolean o;
    private long p;
    private long q;
    private boolean r;

    public ig(gc gcVar, boolean z, boolean z2) {
        this.a = gcVar;
        this.b = z;
        this.c = z2;
        b();
    }

    public final boolean a() {
        return this.c;
    }

    public final void a(ur urVar) {
        this.d.append(urVar.d, urVar);
    }

    public final void a(uq uqVar) {
        this.e.append(uqVar.a, uqVar);
    }

    public final void b() {
        this.k = false;
        this.o = false;
        this.n.a();
    }

    public final void a(long j2, int i2, long j3) {
        this.i = i2;
        this.l = j3;
        this.j = j2;
        if (!this.b || this.i != 1) {
            if (this.c) {
                int i3 = this.i;
                if (!(i3 == 5 || i3 == 1 || i3 == 2)) {
                    return;
                }
            } else {
                return;
            }
        }
        ih ihVar = this.m;
        this.m = this.n;
        this.n = ihVar;
        this.n.a();
        this.h = 0;
        this.k = true;
    }

    public final void a(byte[] bArr, int i2, int i3) {
        boolean z;
        boolean z2;
        boolean z3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i2;
        if (this.k) {
            int i10 = i3 - i9;
            byte[] bArr2 = this.g;
            int length = bArr2.length;
            int i11 = this.h;
            if (length < i11 + i10) {
                this.g = Arrays.copyOf(bArr2, (i11 + i10) << 1);
            }
            System.arraycopy(bArr, i9, this.g, this.h, i10);
            this.h += i10;
            this.f.a(this.g, 0, this.h);
            if (this.f.b(8)) {
                this.f.a();
                int c2 = this.f.c(2);
                this.f.a(5);
                if (this.f.c()) {
                    this.f.d();
                    if (this.f.c()) {
                        int d2 = this.f.d();
                        if (!this.c) {
                            this.k = false;
                            this.n.a(d2);
                        } else if (this.f.c()) {
                            int d3 = this.f.d();
                            if (this.e.indexOfKey(d3) < 0) {
                                this.k = false;
                                return;
                            }
                            uq uqVar = (uq) this.e.get(d3);
                            ur urVar = (ur) this.d.get(uqVar.b);
                            if (urVar.h) {
                                if (this.f.b(2)) {
                                    this.f.a(2);
                                } else {
                                    return;
                                }
                            }
                            if (this.f.b(urVar.j)) {
                                int c3 = this.f.c(urVar.j);
                                if (urVar.i) {
                                    z3 = false;
                                    z2 = false;
                                    z = false;
                                } else if (this.f.b(1)) {
                                    boolean b2 = this.f.b();
                                    if (!b2) {
                                        z3 = b2;
                                        z2 = false;
                                        z = false;
                                    } else if (this.f.b(1)) {
                                        z3 = b2;
                                        z = this.f.b();
                                        z2 = true;
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                                boolean z4 = this.i == 5;
                                if (!z4) {
                                    i4 = 0;
                                } else if (this.f.c()) {
                                    i4 = this.f.d();
                                } else {
                                    return;
                                }
                                if (urVar.k == 0) {
                                    if (this.f.b(urVar.l)) {
                                        int c4 = this.f.c(urVar.l);
                                        if (!uqVar.c || z3) {
                                            i8 = c4;
                                        } else if (this.f.c()) {
                                            i7 = this.f.e();
                                            i8 = c4;
                                            i6 = 0;
                                            i5 = 0;
                                            this.n.a(urVar, c2, d2, c3, d3, z3, z2, z, z4, i4, i8, i7, i6, i5);
                                            this.k = false;
                                        } else {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } else if (urVar.k != 1 || urVar.m) {
                                    i8 = 0;
                                } else if (this.f.c()) {
                                    int e2 = this.f.e();
                                    if (!uqVar.c || z3) {
                                        i6 = e2;
                                        i8 = 0;
                                        i7 = 0;
                                        i5 = 0;
                                        this.n.a(urVar, c2, d2, c3, d3, z3, z2, z, z4, i4, i8, i7, i6, i5);
                                        this.k = false;
                                    } else if (this.f.c()) {
                                        i5 = this.f.e();
                                        i6 = e2;
                                        i8 = 0;
                                        i7 = 0;
                                        this.n.a(urVar, c2, d2, c3, d3, z3, z2, z, z4, i4, i8, i7, i6, i5);
                                        this.k = false;
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                                i7 = 0;
                                i6 = 0;
                                i5 = 0;
                                this.n.a(urVar, c2, d2, c3, d3, z3, z2, z, z4, i4, i8, i7, i6, i5);
                                this.k = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean a(long j2, int i2, boolean z, boolean z2) {
        boolean z3 = false;
        if (this.i == 9 || (this.c && this.n.a(this.m))) {
            if (z && this.o) {
                long j3 = this.j;
                this.a.a(this.q, this.r ? 1 : 0, (int) (j3 - this.p), i2 + ((int) (j2 - j3)), null);
            }
            this.p = this.j;
            this.q = this.l;
            this.r = false;
            this.o = true;
        }
        boolean b2 = this.b ? this.n.b() : z2;
        boolean z4 = this.r;
        int i3 = this.i;
        if (i3 == 5 || (b2 && i3 == 1)) {
            z3 = true;
        }
        this.r = z4 | z3;
        return this.r;
    }
}
