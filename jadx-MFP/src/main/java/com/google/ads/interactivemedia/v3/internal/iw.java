package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class iw implements fq {
    /* access modifiers changed from: private */
    public static final long a = ((long) vf.h("AC-3"));
    /* access modifiers changed from: private */
    public static final long b = ((long) vf.h("EAC3"));
    /* access modifiers changed from: private */
    public static final long c = ((long) vf.h("HEVC"));
    /* access modifiers changed from: private */
    public final int d;
    /* access modifiers changed from: private */
    public final List<ve> e;
    private final ut f;
    private final SparseIntArray g;
    /* access modifiers changed from: private */
    public final jc h;
    /* access modifiers changed from: private */
    public final SparseArray<iz> i;
    /* access modifiers changed from: private */
    public final SparseBooleanArray j;
    /* access modifiers changed from: private */
    public final SparseBooleanArray k;
    private final iv l;
    private it m;
    /* access modifiers changed from: private */
    public fs n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public boolean p;
    private boolean q;
    private boolean r;
    /* access modifiers changed from: private */
    public iz s;
    private int t;
    /* access modifiers changed from: private */
    public int u;

    public iw() {
        this(0);
    }

    public final void c() {
    }

    private iw(int i2) {
        this(1, 0);
    }

    private iw(int i2, int i3) {
        this(1, new ve(0), new jc(i3));
    }

    public iw(int i2, ve veVar, jc jcVar) {
        this.h = (jc) qi.a(jcVar);
        this.d = i2;
        if (i2 == 1 || i2 == 2) {
            this.e = Collections.singletonList(veVar);
        } else {
            this.e = new ArrayList();
            this.e.add(veVar);
        }
        this.f = new ut(new byte[9400], 0);
        this.j = new SparseBooleanArray();
        this.k = new SparseBooleanArray();
        this.i = new SparseArray<>();
        this.g = new SparseIntArray();
        this.l = new iv();
        this.u = -1;
        this.j.clear();
        this.i.clear();
        SparseArray a2 = this.h.a();
        int size = a2.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.i.put(a2.keyAt(i3), (iz) a2.valueAt(i3));
        }
        this.i.put(0, new iq(new ix(this)));
        this.s = null;
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        boolean z;
        byte[] bArr = this.f.a;
        frVar.c(bArr, 0, 940);
        for (int i2 = 0; i2 < 188; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= 5) {
                    z = true;
                    break;
                } else if (bArr[(i3 * 188) + i2] != 71) {
                    z = false;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                frVar.b(i2);
                return true;
            }
        }
        return false;
    }

    public final void a(fs fsVar) {
        this.n = fsVar;
    }

    public final void a(long j2, long j3) {
        qi.c(this.d != 2);
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            ve veVar = (ve) this.e.get(i2);
            if ((veVar.c() == -9223372036854775807L) || !(veVar.c() == 0 || veVar.a() == j3)) {
                veVar.d();
                veVar.a(j3);
            }
        }
        if (j3 != 0) {
            it itVar = this.m;
            if (itVar != null) {
                itVar.a(j3);
            }
        }
        this.f.a();
        this.g.clear();
        for (int i3 = 0; i3 < this.i.size(); i3++) {
            ((iz) this.i.valueAt(i3)).a();
        }
        this.t = 0;
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        boolean z;
        boolean z2;
        boolean z3;
        fr frVar2 = frVar;
        fx fxVar2 = fxVar;
        long d2 = frVar.d();
        iz izVar = null;
        if (this.p) {
            if (((d2 == -1 || this.d == 2) ? false : true) && !this.l.a()) {
                return this.l.a(frVar2, fxVar2, this.u);
            }
            if (!this.q) {
                this.q = true;
                if (this.l.b() != -9223372036854775807L) {
                    it itVar = r3;
                    z2 = false;
                    z = true;
                    it itVar2 = new it(this.l.c(), this.l.b(), d2, this.u);
                    this.m = itVar;
                    this.n.a(this.m.a());
                } else {
                    z2 = false;
                    z = true;
                    this.n.a(new ga(this.l.b()));
                }
            } else {
                z2 = false;
                z = true;
            }
            if (this.r) {
                this.r = z2;
                a(0, 0);
                if (frVar.c() != 0) {
                    fxVar2.a = 0;
                    return z ? 1 : 0;
                }
            }
            it itVar3 = this.m;
            if (itVar3 != null && itVar3.b()) {
                return this.m.a(frVar2, fxVar2, (ho) null);
            }
        } else {
            z2 = false;
            z = true;
        }
        byte[] bArr = this.f.a;
        if (9400 - this.f.d() < 188) {
            int b2 = this.f.b();
            if (b2 > 0) {
                System.arraycopy(bArr, this.f.d(), bArr, z2, b2);
            }
            this.f.a(bArr, b2);
        }
        while (true) {
            if (this.f.b() >= 188) {
                z3 = true;
                break;
            }
            int c2 = this.f.c();
            int a2 = frVar2.a(bArr, c2, 9400 - c2);
            if (a2 == -1) {
                z3 = false;
                break;
            }
            this.f.b(c2 + a2);
        }
        if (!z3) {
            return -1;
        }
        int d3 = this.f.d();
        int c3 = this.f.c();
        int a3 = ho.a(this.f.a, d3, c3);
        this.f.c(a3);
        int i2 = a3 + 188;
        if (i2 > c3) {
            this.t += a3 - d3;
            if (this.d == 2 && this.t > 376) {
                throw new ca("Cannot find sync byte. Most likely not a Transport Stream.");
            }
        } else {
            this.t = z2;
        }
        int c4 = this.f.c();
        if (i2 > c4) {
            return z2;
        }
        int l2 = this.f.l();
        if ((8388608 & l2) != 0) {
            this.f.c(i2);
            return z2;
        }
        boolean z4 = ((4194304 & l2) != 0) | z2;
        int i3 = (2096896 & l2) >> 8;
        boolean z5 = (l2 & 32) != 0;
        if ((l2 & 16) != 0) {
            izVar = (iz) this.i.get(i3);
        }
        if (izVar == null) {
            this.f.c(i2);
            return z2;
        }
        if (this.d != 2) {
            int i4 = l2 & 15;
            int i5 = this.g.get(i3, i4 - 1);
            this.g.put(i3, i4);
            if (i5 == i4) {
                this.f.c(i2);
                return z2;
            } else if (i4 != ((i5 + z) & 15)) {
                izVar.a();
            }
        }
        if (z5) {
            z4 |= (this.f.e() & 64) != 0 ? (char) 2 : 0;
            this.f.d(this.f.e() - (z ? 1 : 0));
        }
        boolean z6 = this.p;
        if (this.d == 2 || z6 || !this.k.get(i3, z2)) {
            this.f.b(i2);
            izVar.a(this.f, z4 ? 1 : 0);
            this.f.b(c4);
        }
        if (this.d != 2 && !z6 && this.p && d2 != -1) {
            this.r = z;
        }
        this.f.c(i2);
        return z2 ? 1 : 0;
    }
}
