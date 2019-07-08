package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: IMASDK */
final class me implements fs, ll, ms, tl<lk>, tp {
    /* access modifiers changed from: private */
    public static final bs a = bs.a("icy", "application/x-icy", Long.MAX_VALUE);
    private boolean A;
    private boolean B;
    private int C;
    private long D;
    private long E;
    private long F;
    private long G;
    private boolean H;
    private int I;
    private boolean J;
    private boolean K;
    private final Uri b;
    private final sn c;
    private final ti d;
    private final lr e;
    private final mi f;
    private final sf g;
    /* access modifiers changed from: private */
    public final String h;
    /* access modifiers changed from: private */
    public final long i;
    private final tj j = new tj("Loader:ProgressiveMediaPeriod");
    private final mh k;
    private final uo l;
    private final Runnable m;
    /* access modifiers changed from: private */
    public final Runnable n;
    /* access modifiers changed from: private */
    public final Handler o;
    private lm p;
    private fy q;
    /* access modifiers changed from: private */
    public jy r;
    private mq[] s;
    private ml[] t;
    private boolean u;
    private boolean v;
    private mj w;
    private boolean x;
    private int y;
    private boolean z;

    public me(Uri uri, sn snVar, fq[] fqVarArr, ti tiVar, lr lrVar, mi miVar, sf sfVar, String str, int i2) {
        this.b = uri;
        this.c = snVar;
        this.d = tiVar;
        this.e = lrVar;
        this.f = miVar;
        this.g = sfVar;
        this.h = str;
        this.i = (long) i2;
        this.k = new mh(fqVarArr);
        this.l = new uo();
        this.m = new mf(this);
        this.n = new mg(this);
        this.o = new Handler();
        this.t = new ml[0];
        this.s = new mq[0];
        this.G = -9223372036854775807L;
        this.E = -1;
        this.D = -9223372036854775807L;
        this.y = 1;
        lrVar.a();
    }

    public final void a(long j2) {
    }

    public final void f() {
        if (this.v) {
            for (mq n2 : this.s) {
                n2.n();
            }
        }
        this.j.a((tp) this);
        this.o.removeCallbacksAndMessages(null);
        this.p = null;
        this.K = true;
        this.e.b();
    }

    public final void g() {
        for (mq a2 : this.s) {
            a2.a(false);
        }
        this.k.a();
    }

    public final void a(lm lmVar, long j2) {
        this.p = lmVar;
        this.l.a();
        p();
    }

    public final void a_() throws IOException {
        h();
        if (this.J && !this.v) {
            throw new ca("Loading finished before preparation is complete.");
        }
    }

    public final mz b() {
        return o().b;
    }

    public final long a(rt[] rtVarArr, boolean[] zArr, mt[] mtVarArr, boolean[] zArr2, long j2) {
        mj o2 = o();
        mz mzVar = o2.b;
        boolean[] zArr3 = o2.d;
        int i2 = this.C;
        int i3 = 0;
        for (int i4 = 0; i4 < rtVarArr.length; i4++) {
            if (mtVarArr[i4] != null && (rtVarArr[i4] == null || !zArr[i4])) {
                int a2 = mtVarArr[i4].a;
                qi.c(zArr3[a2]);
                this.C--;
                zArr3[a2] = false;
                mtVarArr[i4] = null;
            }
        }
        boolean z2 = !this.z ? j2 != 0 : i2 == 0;
        for (int i5 = 0; i5 < rtVarArr.length; i5++) {
            if (mtVarArr[i5] == null && rtVarArr[i5] != null) {
                rt rtVar = rtVarArr[i5];
                qi.c(rtVar.g() == 1);
                qi.c(rtVar.b(0) == 0);
                int a3 = mzVar.a(rtVar.f());
                qi.c(!zArr3[a3]);
                this.C++;
                zArr3[a3] = true;
                mtVarArr[i5] = new mk(this, a3);
                zArr2[i5] = true;
                if (!z2) {
                    mq mqVar = this.s[a3];
                    mqVar.l();
                    z2 = mqVar.b(j2, true, true) == -1 && mqVar.f() != 0;
                }
            }
        }
        if (this.C == 0) {
            this.H = false;
            this.A = false;
            if (this.j.b()) {
                mq[] mqVarArr = this.s;
                int length = mqVarArr.length;
                while (i3 < length) {
                    mqVarArr[i3].n();
                    i3++;
                }
                this.j.c();
            } else {
                for (mq a4 : this.s) {
                    a4.a(false);
                }
            }
        } else if (z2) {
            j2 = b(j2);
            while (i3 < mtVarArr.length) {
                if (mtVarArr[i3] != null) {
                    zArr2[i3] = true;
                }
                i3++;
            }
        }
        this.z = true;
        return j2;
    }

    public final void a(long j2, boolean z2) {
        if (!s()) {
            boolean[] zArr = o().d;
            int length = this.s.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.s[i2].a(j2, z2, zArr[i2]);
            }
        }
    }

    public final boolean c(long j2) {
        if (this.J || this.H || (this.v && this.C == 0)) {
            return false;
        }
        boolean a2 = this.l.a();
        if (!this.j.b()) {
            p();
            a2 = true;
        }
        return a2;
    }

    public final long e() {
        if (this.C == 0) {
            return Long.MIN_VALUE;
        }
        return d();
    }

    public final long c() {
        if (!this.B) {
            this.e.c();
            this.B = true;
        }
        if (!this.A || (!this.J && q() <= this.I)) {
            return -9223372036854775807L;
        }
        this.A = false;
        return this.F;
    }

    public final long d() {
        long j2;
        boolean[] zArr = o().c;
        if (this.J) {
            return Long.MIN_VALUE;
        }
        if (s()) {
            return this.G;
        }
        if (this.x) {
            int length = this.s.length;
            j2 = Long.MAX_VALUE;
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i2] && !this.s[i2].j()) {
                    j2 = Math.min(j2, this.s[i2].i());
                }
            }
        } else {
            j2 = Long.MAX_VALUE;
        }
        if (j2 == Long.MAX_VALUE) {
            j2 = r();
        }
        return j2 == Long.MIN_VALUE ? this.F : j2;
    }

    public final long b(long j2) {
        boolean z2;
        mj o2 = o();
        fy fyVar = o2.a;
        boolean[] zArr = o2.c;
        if (!fyVar.a()) {
            j2 = 0;
        }
        this.A = false;
        this.F = j2;
        if (s()) {
            this.G = j2;
            return j2;
        }
        if (this.y != 7) {
            int length = this.s.length;
            int i2 = 0;
            while (true) {
                z2 = true;
                if (i2 >= length) {
                    break;
                }
                mq mqVar = this.s[i2];
                mqVar.l();
                if (mqVar.b(j2, true, false) == -1) {
                    z2 = false;
                }
                if (z2 || (!zArr[i2] && this.x)) {
                    i2++;
                }
            }
            z2 = false;
            if (z2) {
                return j2;
            }
        }
        this.H = false;
        this.G = j2;
        this.J = false;
        if (this.j.b()) {
            this.j.c();
        } else {
            for (mq a2 : this.s) {
                a2.a(false);
            }
        }
        return j2;
    }

    public final long a(long j2, cm cmVar) {
        fy fyVar = o().a;
        if (!fyVar.a()) {
            return 0;
        }
        fz a2 = fyVar.a(j2);
        return vf.a(j2, cmVar, a2.a.b, a2.b.b);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(int i2) {
        return !n() && (this.J || this.s[i2].d());
    }

    /* access modifiers changed from: 0000 */
    public final void h() throws IOException {
        this.j.a(this.d.a(this.y));
    }

    /* access modifiers changed from: 0000 */
    public final int a(int i2, bu buVar, ex exVar, boolean z2) {
        if (n()) {
            return -3;
        }
        b(i2);
        int a2 = this.s[i2].a(buVar, exVar, z2, this.J, this.F);
        if (a2 == -3) {
            c(i2);
        }
        return a2;
    }

    /* access modifiers changed from: 0000 */
    public final int a(int i2, long j2) {
        int i3 = 0;
        if (n()) {
            return 0;
        }
        b(i2);
        mq mqVar = this.s[i2];
        if (!this.J || j2 <= mqVar.i()) {
            int b2 = mqVar.b(j2, true, true);
            if (b2 != -1) {
                i3 = b2;
            }
        } else {
            i3 = mqVar.o();
        }
        if (i3 == 0) {
            c(i2);
        }
        return i3;
    }

    private final void b(int i2) {
        mj o2 = o();
        boolean[] zArr = o2.e;
        if (!zArr[i2]) {
            bs a2 = o2.b.a(i2).a(0);
            this.e.a(un.g(a2.h), a2, 0, null, this.F);
            zArr[i2] = true;
        }
    }

    private final void c(int i2) {
        boolean[] zArr = o().c;
        if (this.H && zArr[i2] && !this.s[i2].d()) {
            this.G = 0;
            this.H = false;
            this.A = true;
            this.F = 0;
            this.I = 0;
            for (mq a2 : this.s) {
                a2.a(false);
            }
            ((lm) qi.a(this.p)).a(this);
        }
    }

    private final boolean n() {
        return this.A || s();
    }

    public final gc a(int i2, int i3) {
        return a(new ml(i2, false));
    }

    public final void a() {
        this.u = true;
        this.o.post(this.m);
    }

    public final void a(fy fyVar) {
        if (this.r != null) {
            fyVar = new ga(-9223372036854775807L);
        }
        this.q = fyVar;
        this.o.post(this.m);
    }

    /* access modifiers changed from: 0000 */
    public final gc i() {
        return a(new ml(0, true));
    }

    public final void j() {
        this.o.post(this.m);
    }

    private final gc a(ml mlVar) {
        int length = this.s.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (mlVar.equals(this.t[i2])) {
                return this.s[i2];
            }
        }
        mq mqVar = new mq(this.g);
        mqVar.a((ms) this);
        int i3 = length + 1;
        ml[] mlVarArr = (ml[]) Arrays.copyOf(this.t, i3);
        mlVarArr[length] = mlVar;
        this.t = (ml[]) vf.a((T[]) mlVarArr);
        mq[] mqVarArr = (mq[]) Arrays.copyOf(this.s, i3);
        mqVarArr[length] = mqVar;
        this.s = (mq[]) vf.a((T[]) mqVarArr);
        return mqVar;
    }

    private final mj o() {
        return (mj) qi.a(this.w);
    }

    private final void a(lk lkVar) {
        if (this.E == -1) {
            this.E = lkVar.l;
        }
    }

    private final void p() {
        lk lkVar = new lk(this, this.b, this.c, this.k, this, this.l);
        if (this.v) {
            fy fyVar = o().a;
            qi.c(s());
            long j2 = this.D;
            if (j2 == -9223372036854775807L || this.G < j2) {
                lkVar.a(fyVar.a(this.G).a.c, this.G);
                this.G = -9223372036854775807L;
            } else {
                this.J = true;
                this.G = -9223372036854775807L;
                return;
            }
        }
        this.I = q();
        this.e.a(lkVar.k, 1, -1, (bs) null, 0, (Object) null, lkVar.j, this.D, this.j.a(lkVar, this, this.d.a(this.y)));
    }

    private final int q() {
        int i2 = 0;
        for (mq c2 : this.s) {
            i2 += c2.c();
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public final long r() {
        long j2 = Long.MIN_VALUE;
        for (mq i2 : this.s) {
            j2 = Math.max(j2, i2.i());
        }
        return j2;
    }

    private final boolean s() {
        return this.G != -9223372036854775807L;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.ads.interactivemedia.v3.internal.tm a(com.google.ads.interactivemedia.v3.internal.to r25, long r26, long r28, java.io.IOException r30, int r31) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            com.google.ads.interactivemedia.v3.internal.lk r1 = (com.google.ads.interactivemedia.v3.internal.lk) r1
            r0.a(r1)
            com.google.ads.interactivemedia.v3.internal.ti r2 = r0.d
            r14 = r30
            r3 = r31
            long r2 = r2.a(r14, r3)
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 1
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0020
            com.google.ads.interactivemedia.v3.internal.tm r2 = com.google.ads.interactivemedia.v3.internal.tj.c
            goto L_0x0077
        L_0x0020:
            int r7 = r24.q()
            int r8 = r0.I
            r9 = 0
            if (r7 <= r8) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            long r10 = r0.E
            r12 = -1
            int r15 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r15 != 0) goto L_0x006b
            com.google.ads.interactivemedia.v3.internal.fy r10 = r0.q
            if (r10 == 0) goto L_0x0041
            long r10 = r10.b()
            int r12 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0041
            goto L_0x006b
        L_0x0041:
            boolean r4 = r0.v
            if (r4 == 0) goto L_0x004e
            boolean r4 = r24.n()
            if (r4 != 0) goto L_0x004e
            r0.H = r6
            goto L_0x006e
        L_0x004e:
            boolean r4 = r0.v
            r0.A = r4
            r4 = 0
            r0.F = r4
            r0.I = r9
            com.google.ads.interactivemedia.v3.internal.mq[] r7 = r0.s
            int r10 = r7.length
            r11 = 0
        L_0x005c:
            if (r11 >= r10) goto L_0x0066
            r12 = r7[r11]
            r12.a(r9)
            int r11 = r11 + 1
            goto L_0x005c
        L_0x0066:
            r1.a(r4, r4)
            r9 = 1
            goto L_0x006e
        L_0x006b:
            r0.I = r7
            r9 = 1
        L_0x006e:
            if (r9 == 0) goto L_0x0075
            com.google.ads.interactivemedia.v3.internal.tm r2 = com.google.ads.interactivemedia.v3.internal.tj.a(r8, r2)
            goto L_0x0077
        L_0x0075:
            com.google.ads.interactivemedia.v3.internal.tm r2 = com.google.ads.interactivemedia.v3.internal.tj.b
        L_0x0077:
            com.google.ads.interactivemedia.v3.internal.lr r3 = r0.e
            com.google.ads.interactivemedia.v3.internal.sr r4 = r1.k
            com.google.ads.interactivemedia.v3.internal.ty r5 = r1.c
            android.net.Uri r5 = r5.f()
            com.google.ads.interactivemedia.v3.internal.ty r7 = r1.c
            java.util.Map r7 = r7.g()
            r8 = 1
            r9 = -1
            r10 = 0
            r11 = 0
            long r15 = r1.j
            long r12 = r0.D
            com.google.ads.interactivemedia.v3.internal.ty r1 = r1.c
            long r20 = r1.e()
            boolean r1 = r2.a()
            r23 = r1 ^ 1
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r1 = 0
            r11 = r1
            r17 = r12
            r12 = r15
            r14 = r17
            r16 = r26
            r18 = r28
            r22 = r30
            r3.a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r16, r18, r20, r22, r23)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.me.a(com.google.ads.interactivemedia.v3.internal.to, long, long, java.io.IOException, int):com.google.ads.interactivemedia.v3.internal.tm");
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3, boolean z2) {
        lk lkVar = (lk) toVar;
        lk lkVar2 = lkVar;
        this.e.b(lkVar.k, lkVar.c.f(), lkVar.c.g(), 1, -1, null, 0, null, lkVar.j, this.D, j2, j3, lkVar.c.e());
        if (!z2) {
            lk lkVar3 = lkVar2;
            a(lkVar3);
            for (mq a2 : this.s) {
                a2.a(false);
            }
            if (this.C > 0) {
                ((lm) qi.a(this.p)).a(this);
                return;
            }
            return;
        }
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3) {
        lk lkVar = (lk) toVar;
        if (this.D == -9223372036854775807L) {
            fy fyVar = this.q;
            if (fyVar != null) {
                boolean a2 = fyVar.a();
                long r2 = r();
                this.D = r2 == Long.MIN_VALUE ? 0 : r2 + 10000;
                this.f.a(this.D, a2);
            }
        }
        this.e.a(lkVar.k, lkVar.c.f(), lkVar.c.g(), 1, -1, null, 0, null, lkVar.j, this.D, j2, j3, lkVar.c.e());
        a(lkVar);
        this.J = true;
        ((lm) qi.a(this.p)).a(this);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void k() {
        if (!this.K) {
            ((lm) qi.a(this.p)).a(this);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void m() {
        js jsVar;
        fy fyVar = this.q;
        if (!this.K && !this.v && this.u && fyVar != null) {
            mq[] mqVarArr = this.s;
            int length = mqVarArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (mqVarArr[i2].h() == null) {
                        break;
                    }
                    i2++;
                } else {
                    this.l.b();
                    int length2 = this.s.length;
                    mx[] mxVarArr = new mx[length2];
                    boolean[] zArr = new boolean[length2];
                    this.D = fyVar.b();
                    for (int i3 = 0; i3 < length2; i3++) {
                        bs h2 = this.s[i3].h();
                        String str = h2.h;
                        boolean a2 = un.a(str);
                        boolean z2 = a2 || un.b(str);
                        zArr[i3] = z2;
                        this.x = z2 | this.x;
                        jy jyVar = this.r;
                        if (jyVar != null) {
                            if (a2 || this.t[i3].a) {
                                js jsVar2 = h2.f;
                                if (jsVar2 == null) {
                                    jsVar = new js(jyVar);
                                } else {
                                    jsVar = jsVar2.a(jyVar);
                                }
                                h2 = h2.a(jsVar);
                            }
                            if (a2 && h2.d == -1 && jyVar.a != -1) {
                                h2 = h2.b(jyVar.a);
                            }
                        }
                        mxVarArr[i3] = new mx(h2);
                    }
                    this.y = (this.E == -1 && fyVar.b() == -9223372036854775807L) ? 7 : 1;
                    this.w = new mj(fyVar, new mz(mxVarArr), zArr);
                    this.v = true;
                    this.f.a(this.D, fyVar.a());
                    ((lm) qi.a(this.p)).a(this);
                }
            }
        }
    }
}
