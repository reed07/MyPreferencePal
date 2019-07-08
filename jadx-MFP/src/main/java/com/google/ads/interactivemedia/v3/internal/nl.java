package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.ads.interactivemedia.v3.internal.no;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class nl<T extends no> implements mt, mu, tl<ng>, tp {
    public final int a;
    long b;
    boolean c;
    /* access modifiers changed from: private */
    public final int[] d;
    /* access modifiers changed from: private */
    public final bs[] e;
    /* access modifiers changed from: private */
    public final boolean[] f;
    private final T g;
    private final mv<nl<T>> h;
    /* access modifiers changed from: private */
    public final lr i;
    private final ti j;
    private final tj k = new tj("Loader:ChunkSampleStream");
    private final nk l = new nk();
    private final ArrayList<ne> m = new ArrayList<>();
    private final List<ne> n = Collections.unmodifiableList(this.m);
    private final mq o;
    private final mq[] p;
    private final nj q;
    private bs r;
    private nn<T> s;
    private long t;
    /* access modifiers changed from: private */
    public long u;
    private int v;

    public nl(int i2, int[] iArr, bs[] bsVarArr, T t2, mv<nl<T>> mvVar, sf sfVar, long j2, ti tiVar, lr lrVar) {
        int i3;
        this.a = i2;
        this.d = iArr;
        this.e = bsVarArr;
        this.g = t2;
        this.h = mvVar;
        this.i = lrVar;
        this.j = tiVar;
        int i4 = 0;
        if (iArr == null) {
            i3 = 0;
        } else {
            i3 = iArr.length;
        }
        this.p = new mq[i3];
        this.f = new boolean[i3];
        int i5 = i3 + 1;
        int[] iArr2 = new int[i5];
        mq[] mqVarArr = new mq[i5];
        this.o = new mq(sfVar);
        iArr2[0] = i2;
        mqVarArr[0] = this.o;
        while (i4 < i3) {
            mq mqVar = new mq(sfVar);
            this.p[i4] = mqVar;
            int i6 = i4 + 1;
            mqVarArr[i6] = mqVar;
            iArr2[i6] = iArr[i4];
            i4 = i6;
        }
        this.q = new nj(iArr2, mqVarArr);
        this.t = j2;
        this.u = j2;
    }

    public final void a(long j2, boolean z) {
        if (!f()) {
            int e2 = this.o.e();
            this.o.a(j2, z, true);
            int e3 = this.o.e();
            if (e3 > e2) {
                long k2 = this.o.k();
                int i2 = 0;
                while (true) {
                    mq[] mqVarArr = this.p;
                    if (i2 >= mqVarArr.length) {
                        break;
                    }
                    mqVarArr[i2].a(k2, z, this.f[i2]);
                    i2++;
                }
            }
            int min = Math.min(a(e3, 0), this.v);
            if (min > 0) {
                vf.a((List<T>) this.m, 0, min);
                this.v -= min;
            }
        }
    }

    public final nm a(long j2, int i2) {
        for (int i3 = 0; i3 < this.p.length; i3++) {
            if (this.d[i3] == i2) {
                qi.c(!this.f[i3]);
                this.f[i3] = true;
                this.p[i3].l();
                this.p[i3].b(j2, true, true);
                return new nm(this, this, this.p[i3], i3);
            }
        }
        throw new IllegalStateException();
    }

    public final T a() {
        return this.g;
    }

    public final long d() {
        if (this.c) {
            return Long.MIN_VALUE;
        }
        if (f()) {
            return this.t;
        }
        long j2 = this.u;
        ne i2 = i();
        if (!i2.h()) {
            if (this.m.size() > 1) {
                ArrayList<ne> arrayList = this.m;
                i2 = (ne) arrayList.get(arrayList.size() - 2);
            } else {
                i2 = null;
            }
        }
        if (i2 != null) {
            j2 = Math.max(j2, i2.i);
        }
        return Math.max(j2, this.o.i());
    }

    public final long a(long j2, cm cmVar) {
        return this.g.a(j2, cmVar);
    }

    public final void b(long j2) {
        boolean z;
        mq[] mqVarArr;
        this.u = j2;
        if (f()) {
            this.t = j2;
            return;
        }
        ne neVar = null;
        int i2 = 0;
        while (true) {
            if (i2 >= this.m.size()) {
                break;
            }
            ne neVar2 = (ne) this.m.get(i2);
            int i3 = (neVar2.h > j2 ? 1 : (neVar2.h == j2 ? 0 : -1));
            if (i3 != 0 || neVar2.a != -9223372036854775807L) {
                if (i3 > 0) {
                    break;
                }
                i2++;
            } else {
                neVar = neVar2;
                break;
            }
        }
        this.o.l();
        if (neVar != null) {
            z = this.o.c(neVar.a(0));
            this.b = 0;
        } else {
            z = this.o.b(j2, true, (j2 > e() ? 1 : (j2 == e() ? 0 : -1)) < 0) != -1;
            this.b = this.u;
        }
        if (z) {
            this.v = a(this.o.f(), 0);
            for (mq mqVar : this.p) {
                mqVar.l();
                mqVar.b(j2, true, false);
            }
            return;
        }
        this.t = j2;
        this.c = false;
        this.m.clear();
        this.v = 0;
        if (this.k.b()) {
            this.k.c();
            return;
        }
        this.o.a();
        for (mq a2 : this.p) {
            a2.a();
        }
    }

    public final void a(nn<T> nnVar) {
        this.s = nnVar;
        this.o.n();
        for (mq n2 : this.p) {
            n2.n();
        }
        this.k.a((tp) this);
    }

    public final void g() {
        this.o.a();
        for (mq a2 : this.p) {
            a2.a();
        }
        nn<T> nnVar = this.s;
        if (nnVar != null) {
            nnVar.a(this);
        }
    }

    public final boolean b() {
        return this.c || (!f() && this.o.d());
    }

    public final void c() throws IOException {
        this.k.a();
        if (!this.k.b()) {
            this.g.a();
        }
    }

    public final int a(bu buVar, ex exVar, boolean z) {
        if (f()) {
            return -3;
        }
        h();
        return this.o.a(buVar, exVar, z, this.c, this.b);
    }

    public final int a_(long j2) {
        int i2 = 0;
        if (f()) {
            return 0;
        }
        if (!this.c || j2 <= this.o.i()) {
            int b2 = this.o.b(j2, true, true);
            if (b2 != -1) {
                i2 = b2;
            }
        } else {
            i2 = this.o.o();
        }
        h();
        return i2;
    }

    public final boolean c(long j2) {
        List list;
        long j3;
        long j4;
        boolean z = false;
        if (this.c || this.k.b()) {
            return false;
        }
        boolean f2 = f();
        if (f2) {
            list = Collections.emptyList();
            j3 = this.t;
        } else {
            list = this.n;
            j3 = i().i;
        }
        this.g.a(j2, j3, list, this.l);
        boolean z2 = this.l.b;
        ng ngVar = this.l.a;
        nk nkVar = this.l;
        nkVar.a = null;
        nkVar.b = false;
        if (z2) {
            this.t = -9223372036854775807L;
            this.c = true;
            return true;
        } else if (ngVar == null) {
            return false;
        } else {
            if (ngVar instanceof ne) {
                ne neVar = (ne) ngVar;
                if (f2) {
                    if (neVar.h == this.t) {
                        z = true;
                    }
                    if (z) {
                        j4 = 0;
                    } else {
                        j4 = this.t;
                    }
                    this.b = j4;
                    this.t = -9223372036854775807L;
                }
                neVar.a(this.q);
                this.m.add(neVar);
            }
            long a2 = this.k.a(ngVar, this, this.j.a(ngVar.d));
            this.i.a(ngVar.c, ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, a2);
            return true;
        }
    }

    public final long e() {
        if (f()) {
            return this.t;
        }
        if (this.c) {
            return Long.MIN_VALUE;
        }
        return i().i;
    }

    public final void a(long j2) {
        if (!this.k.b() && !f()) {
            int size = this.m.size();
            int a2 = this.g.a(j2, this.n);
            if (size > a2) {
                while (true) {
                    if (a2 >= size) {
                        a2 = size;
                        break;
                    } else if (!a(a2)) {
                        break;
                    } else {
                        a2++;
                    }
                }
                if (a2 != size) {
                    long j3 = i().i;
                    ne b2 = b(a2);
                    if (this.m.isEmpty()) {
                        this.t = this.u;
                    }
                    this.c = false;
                    this.i.a(this.a, b2.h, j3);
                }
            }
        }
    }

    private final boolean a(int i2) {
        int f2;
        ne neVar = (ne) this.m.get(i2);
        if (this.o.f() > neVar.a(0)) {
            return true;
        }
        int i3 = 0;
        do {
            mq[] mqVarArr = this.p;
            if (i3 >= mqVarArr.length) {
                return false;
            }
            f2 = mqVarArr[i3].f();
            i3++;
        } while (f2 <= neVar.a(i3));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return this.t != -9223372036854775807L;
    }

    private final void h() {
        int a2 = a(this.o.f(), this.v - 1);
        while (true) {
            int i2 = this.v;
            if (i2 <= a2) {
                this.v = i2 + 1;
                ne neVar = (ne) this.m.get(i2);
                bs bsVar = neVar.e;
                if (!bsVar.equals(this.r)) {
                    this.i.a(this.a, bsVar, neVar.f, neVar.g, neVar.h);
                }
                this.r = bsVar;
            } else {
                return;
            }
        }
    }

    private final int a(int i2, int i3) {
        while (true) {
            i3++;
            if (i3 >= this.m.size()) {
                return this.m.size() - 1;
            }
            if (((ne) this.m.get(i3)).a(0) > i2) {
                return i3 - 1;
            }
        }
    }

    private final ne i() {
        ArrayList<ne> arrayList = this.m;
        return (ne) arrayList.get(arrayList.size() - 1);
    }

    private final ne b(int i2) {
        ne neVar = (ne) this.m.get(i2);
        ArrayList<ne> arrayList = this.m;
        vf.a((List<T>) arrayList, i2, arrayList.size());
        this.v = Math.max(this.v, this.m.size());
        int i3 = 0;
        this.o.b(neVar.a(0));
        while (true) {
            mq[] mqVarArr = this.p;
            if (i3 >= mqVarArr.length) {
                return neVar;
            }
            mq mqVar = mqVarArr[i3];
            i3++;
            mqVar.b(neVar.a(i3));
        }
    }

    public final /* synthetic */ tm a(to toVar, long j2, long j3, IOException iOException, int i2) {
        tm tmVar;
        tm tmVar2;
        IOException iOException2 = iOException;
        ng ngVar = (ng) toVar;
        long e2 = ngVar.j.e();
        boolean z = ngVar instanceof ne;
        int size = this.m.size() - 1;
        boolean z2 = e2 == 0 || !z || !a(size);
        tm tmVar3 = null;
        if (this.g.a(ngVar, z2, (Exception) iOException, z2 ? this.j.a(iOException2) : -9223372036854775807L)) {
            if (z2) {
                tmVar3 = tj.b;
                if (z) {
                    qi.c(b(size) == ngVar);
                    if (this.m.isEmpty()) {
                        this.t = this.u;
                    }
                }
            } else {
                Log.w("ChunkSampleStream", "Ignoring attempt to cancel non-cancelable load.");
            }
        }
        if (tmVar3 == null) {
            long a2 = this.j.a(iOException2, i2);
            if (a2 != -9223372036854775807L) {
                tmVar2 = tj.a(false, a2);
            } else {
                tmVar2 = tj.c;
            }
            tmVar = tmVar2;
        } else {
            tmVar = tmVar3;
        }
        boolean z3 = !tmVar.a();
        this.i.a(ngVar.c, ngVar.j.f(), ngVar.j.g(), ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, j2, j3, e2, iOException, z3);
        if (z3) {
            this.h.a(this);
        }
        return tmVar;
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3, boolean z) {
        long j4 = j2;
        long j5 = j3;
        ng ngVar = (ng) toVar;
        lr lrVar = this.i;
        lrVar.b(ngVar.c, ngVar.j.f(), ngVar.j.g(), ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, j4, j5, ngVar.j.e());
        if (!z) {
            this.o.a();
            for (mq a2 : this.p) {
                a2.a();
            }
            this.h.a(this);
            return;
        }
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3) {
        long j4 = j2;
        long j5 = j3;
        ng ngVar = (ng) toVar;
        this.g.a(ngVar);
        lr lrVar = this.i;
        sr srVar = ngVar.c;
        lr lrVar2 = lrVar;
        sr srVar2 = srVar;
        lrVar2.a(srVar2, ngVar.j.f(), ngVar.j.g(), ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, j4, j5, ngVar.j.e());
        this.h.a(this);
    }
}
