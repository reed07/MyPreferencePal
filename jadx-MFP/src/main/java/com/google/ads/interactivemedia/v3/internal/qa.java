package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
final class qa implements fs, ms, mu, tl<ng>, tp {
    private bs A;
    private bs B;
    private boolean C;
    private mz D;
    private mz E;
    private int[] F;
    private int G;
    private boolean H;
    private boolean[] I = new boolean[0];
    private boolean[] J = new boolean[0];
    private long K;
    private long L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private long Q;
    private int R;
    private final int a;
    private final qe b;
    private final pm c;
    private final sf d;
    private final bs e;
    private final ti f;
    private final tj g = new tj("Loader:HlsSampleStreamWrapper");
    private final lr h;
    private final pp i = new pp();
    private final ArrayList<pw> j = new ArrayList<>();
    private final List<pw> k = Collections.unmodifiableList(this.j);
    private final Runnable l = new qb(this);
    private final Runnable m = new qc(this);
    private final Handler n = new Handler();
    private final ArrayList<pz> o = new ArrayList<>();
    private mq[] p = new mq[0];
    private int[] q = new int[0];
    private boolean r;
    private int s = -1;
    private boolean t;
    private int u = -1;
    private int v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    public qa(int i2, qe qeVar, pm pmVar, sf sfVar, long j2, bs bsVar, ti tiVar, lr lrVar) {
        this.a = i2;
        this.b = qeVar;
        this.c = pmVar;
        this.d = sfVar;
        this.e = bsVar;
        this.f = tiVar;
        this.h = lrVar;
        this.K = j2;
        this.L = j2;
    }

    private static int d(int i2) {
        switch (i2) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    public final void a(long j2) {
    }

    public final void a(fy fyVar) {
    }

    public final void b() {
        if (!this.y) {
            c(this.K);
        }
    }

    public final void a(mz mzVar, int i2, mz mzVar2) {
        this.y = true;
        this.D = mzVar;
        this.E = mzVar2;
        this.G = 0;
        Handler handler = this.n;
        qe qeVar = this.b;
        qeVar.getClass();
        handler.post(qd.a(qeVar));
    }

    public final void c() throws IOException {
        i();
    }

    public final mz f() {
        return this.D;
    }

    public final int a(int i2) {
        int i3 = this.F[i2];
        if (i3 != -1) {
            boolean[] zArr = this.I;
            if (zArr[i3]) {
                return -2;
            }
            zArr[i3] = true;
            return i3;
        } else if (this.E.a(this.D.a(i2)) == -1) {
            return -2;
        } else {
            return -3;
        }
    }

    public final void b(int i2) {
        int i3 = this.F[i2];
        qi.c(this.I[i3]);
        this.I[i3] = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.ads.interactivemedia.v3.internal.rt[] r20, boolean[] r21, com.google.ads.interactivemedia.v3.internal.mt[] r22, boolean[] r23, long r24, boolean r26) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r22
            r12 = r24
            boolean r3 = r0.y
            com.google.ads.interactivemedia.v3.internal.qi.c(r3)
            int r3 = r0.z
            r14 = 0
            r4 = 0
        L_0x0011:
            int r5 = r1.length
            r6 = 0
            r15 = 1
            if (r4 >= r5) goto L_0x0033
            r5 = r2[r4]
            if (r5 == 0) goto L_0x0030
            r5 = r1[r4]
            if (r5 == 0) goto L_0x0022
            boolean r5 = r21[r4]
            if (r5 != 0) goto L_0x0030
        L_0x0022:
            int r5 = r0.z
            int r5 = r5 - r15
            r0.z = r5
            r5 = r2[r4]
            com.google.ads.interactivemedia.v3.internal.pz r5 = (com.google.ads.interactivemedia.v3.internal.pz) r5
            r5.d()
            r2[r4] = r6
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0033:
            if (r26 != 0) goto L_0x0045
            boolean r4 = r0.N
            if (r4 == 0) goto L_0x003c
            if (r3 != 0) goto L_0x0043
            goto L_0x0045
        L_0x003c:
            long r3 = r0.K
            int r5 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r3 = 0
            goto L_0x0046
        L_0x0045:
            r3 = 1
        L_0x0046:
            com.google.ads.interactivemedia.v3.internal.pm r4 = r0.c
            com.google.ads.interactivemedia.v3.internal.rt r4 = r4.c()
            r16 = r3
            r11 = r4
            r3 = 0
        L_0x0050:
            int r5 = r1.length
            if (r3 >= r5) goto L_0x00b0
            r5 = r2[r3]
            if (r5 != 0) goto L_0x00ad
            r5 = r1[r3]
            if (r5 == 0) goto L_0x00ad
            int r5 = r0.z
            int r5 = r5 + r15
            r0.z = r5
            r5 = r1[r3]
            com.google.ads.interactivemedia.v3.internal.mz r7 = r0.D
            com.google.ads.interactivemedia.v3.internal.mx r8 = r5.f()
            int r7 = r7.a(r8)
            int r8 = r0.G
            if (r7 != r8) goto L_0x0076
            com.google.ads.interactivemedia.v3.internal.pm r8 = r0.c
            r8.a(r5)
            r11 = r5
        L_0x0076:
            com.google.ads.interactivemedia.v3.internal.pz r5 = new com.google.ads.interactivemedia.v3.internal.pz
            r5.<init>(r0, r7)
            r2[r3] = r5
            r23[r3] = r15
            int[] r5 = r0.F
            if (r5 == 0) goto L_0x008a
            r5 = r2[r3]
            com.google.ads.interactivemedia.v3.internal.pz r5 = (com.google.ads.interactivemedia.v3.internal.pz) r5
            r5.a()
        L_0x008a:
            boolean r5 = r0.x
            if (r5 == 0) goto L_0x00ad
            if (r16 != 0) goto L_0x00ad
            com.google.ads.interactivemedia.v3.internal.mq[] r5 = r0.p
            int[] r8 = r0.F
            r7 = r8[r7]
            r5 = r5[r7]
            r5.l()
            int r7 = r5.b(r12, r15, r15)
            r8 = -1
            if (r7 != r8) goto L_0x00ab
            int r5 = r5.f()
            if (r5 == 0) goto L_0x00ab
            r16 = 1
            goto L_0x00ad
        L_0x00ab:
            r16 = 0
        L_0x00ad:
            int r3 = r3 + 1
            goto L_0x0050
        L_0x00b0:
            int r1 = r0.z
            if (r1 != 0) goto L_0x00e6
            com.google.ads.interactivemedia.v3.internal.pm r1 = r0.c
            r1.d()
            r0.B = r6
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pw> r1 = r0.j
            r1.clear()
            com.google.ads.interactivemedia.v3.internal.tj r1 = r0.g
            boolean r1 = r1.b()
            if (r1 == 0) goto L_0x00e1
            boolean r1 = r0.x
            if (r1 == 0) goto L_0x00da
            com.google.ads.interactivemedia.v3.internal.mq[] r1 = r0.p
            int r3 = r1.length
            r4 = 0
        L_0x00d0:
            if (r4 >= r3) goto L_0x00da
            r5 = r1[r4]
            r5.n()
            int r4 = r4 + 1
            goto L_0x00d0
        L_0x00da:
            com.google.ads.interactivemedia.v3.internal.tj r1 = r0.g
            r1.c()
            goto L_0x014e
        L_0x00e1:
            r19.m()
            goto L_0x014e
        L_0x00e6:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pw> r1 = r0.j
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x013a
            boolean r1 = com.google.ads.interactivemedia.v3.internal.vf.a(r11, r4)
            if (r1 != 0) goto L_0x013a
            boolean r1 = r0.N
            if (r1 != 0) goto L_0x0131
            r3 = 0
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x00ff
            long r3 = -r12
        L_0x00ff:
            r6 = r3
            com.google.ads.interactivemedia.v3.internal.pw r1 = r19.o()
            com.google.ads.interactivemedia.v3.internal.pm r3 = r0.c
            com.google.ads.interactivemedia.v3.internal.nt[] r17 = r3.a(r1, r12)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List<com.google.ads.interactivemedia.v3.internal.pw> r10 = r0.k
            r3 = r11
            r4 = r24
            r18 = r11
            r11 = r17
            r3.a(r4, r6, r8, r10, r11)
            com.google.ads.interactivemedia.v3.internal.pm r3 = r0.c
            com.google.ads.interactivemedia.v3.internal.mx r3 = r3.b()
            com.google.ads.interactivemedia.v3.internal.bs r1 = r1.e
            int r1 = r3.a(r1)
            int r3 = r18.i()
            if (r3 == r1) goto L_0x012f
            r1 = 1
            goto L_0x0132
        L_0x012f:
            r1 = 0
            goto L_0x0132
        L_0x0131:
            r1 = 1
        L_0x0132:
            if (r1 == 0) goto L_0x013a
            r0.M = r15
            r1 = 1
            r16 = 1
            goto L_0x013c
        L_0x013a:
            r1 = r26
        L_0x013c:
            if (r16 == 0) goto L_0x014e
            r0.b(r12, r1)
            r1 = 0
        L_0x0142:
            int r3 = r2.length
            if (r1 >= r3) goto L_0x014e
            r3 = r2[r1]
            if (r3 == 0) goto L_0x014b
            r23[r1] = r15
        L_0x014b:
            int r1 = r1 + 1
            goto L_0x0142
        L_0x014e:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pz> r1 = r0.o
            r1.clear()
            int r1 = r2.length
        L_0x0154:
            if (r14 >= r1) goto L_0x0164
            r3 = r2[r14]
            if (r3 == 0) goto L_0x0161
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pz> r4 = r0.o
            com.google.ads.interactivemedia.v3.internal.pz r3 = (com.google.ads.interactivemedia.v3.internal.pz) r3
            r4.add(r3)
        L_0x0161:
            int r14 = r14 + 1
            goto L_0x0154
        L_0x0164:
            r0.N = r15
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qa.a(com.google.ads.interactivemedia.v3.internal.rt[], boolean[], com.google.ads.interactivemedia.v3.internal.mt[], boolean[], long, boolean):boolean");
    }

    public final void a(long j2, boolean z2) {
        if (this.x && !p()) {
            int length = this.p.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.p[i2].a(j2, z2, this.I[i2]);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r8 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean b(long r6, boolean r8) {
        /*
            r5 = this;
            r5.K = r6
            boolean r0 = r5.p()
            r1 = 1
            if (r0 == 0) goto L_0x000c
            r5.L = r6
            return r1
        L_0x000c:
            boolean r0 = r5.x
            r2 = 0
            if (r0 == 0) goto L_0x003f
            if (r8 != 0) goto L_0x003f
            com.google.ads.interactivemedia.v3.internal.mq[] r8 = r5.p
            int r8 = r8.length
            r0 = 0
        L_0x0017:
            if (r0 >= r8) goto L_0x003b
            com.google.ads.interactivemedia.v3.internal.mq[] r3 = r5.p
            r3 = r3[r0]
            r3.l()
            int r3 = r3.b(r6, r1, r2)
            r4 = -1
            if (r3 == r4) goto L_0x0029
            r3 = 1
            goto L_0x002a
        L_0x0029:
            r3 = 0
        L_0x002a:
            if (r3 != 0) goto L_0x0038
            boolean[] r3 = r5.J
            boolean r3 = r3[r0]
            if (r3 != 0) goto L_0x0036
            boolean r3 = r5.H
            if (r3 != 0) goto L_0x0038
        L_0x0036:
            r8 = 0
            goto L_0x003c
        L_0x0038:
            int r0 = r0 + 1
            goto L_0x0017
        L_0x003b:
            r8 = 1
        L_0x003c:
            if (r8 == 0) goto L_0x003f
            return r2
        L_0x003f:
            r5.L = r6
            r5.O = r2
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pw> r6 = r5.j
            r6.clear()
            com.google.ads.interactivemedia.v3.internal.tj r6 = r5.g
            boolean r6 = r6.b()
            if (r6 == 0) goto L_0x0056
            com.google.ads.interactivemedia.v3.internal.tj r6 = r5.g
            r6.c()
            goto L_0x0059
        L_0x0056:
            r5.m()
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qa.b(long, boolean):boolean");
    }

    public final void h() {
        if (this.y) {
            for (mq n2 : this.p) {
                n2.n();
            }
        }
        this.g.a((tp) this);
        this.n.removeCallbacksAndMessages(null);
        this.C = true;
        this.o.clear();
    }

    public final void g() {
        m();
    }

    public final void a(boolean z2) {
        this.c.a(z2);
    }

    public final boolean a(qo qoVar, long j2) {
        return this.c.a(qoVar, j2);
    }

    public final boolean c(int i2) {
        return this.O || (!p() && this.p[i2].d());
    }

    public final void i() throws IOException {
        this.g.a();
        this.c.a();
    }

    public final int a(int i2, bu buVar, ex exVar, boolean z2) {
        bs bsVar;
        if (p()) {
            return -3;
        }
        int i3 = 0;
        if (!this.j.isEmpty()) {
            int i4 = 0;
            while (true) {
                boolean z3 = true;
                if (i4 >= this.j.size() - 1) {
                    break;
                }
                int i5 = ((pw) this.j.get(i4)).a;
                int length = this.p.length;
                int i6 = 0;
                while (true) {
                    if (i6 < length) {
                        if (this.I[i6] && this.p[i6].g() == i5) {
                            z3 = false;
                            break;
                        }
                        i6++;
                    } else {
                        break;
                    }
                }
                if (!z3) {
                    break;
                }
                i4++;
            }
            vf.a((List<T>) this.j, 0, i4);
            pw pwVar = (pw) this.j.get(0);
            bs bsVar2 = pwVar.e;
            if (!bsVar2.equals(this.B)) {
                this.h.a(this.a, bsVar2, pwVar.f, pwVar.g, pwVar.h);
            }
            this.B = bsVar2;
        }
        int a2 = this.p[i2].a(buVar, exVar, z2, this.O, this.K);
        if (a2 == -5 && i2 == this.w) {
            int g2 = this.p[i2].g();
            while (i3 < this.j.size() && ((pw) this.j.get(i3)).a != g2) {
                i3++;
            }
            if (i3 < this.j.size()) {
                bsVar = ((pw) this.j.get(i3)).e;
            } else {
                bsVar = this.A;
            }
            buVar.a = buVar.a.a(bsVar);
        }
        return a2;
    }

    public final int a(int i2, long j2) {
        if (p()) {
            return 0;
        }
        mq mqVar = this.p[i2];
        if (this.O && j2 > mqVar.i()) {
            return mqVar.o();
        }
        int b2 = mqVar.b(j2, true, true);
        if (b2 == -1) {
            return 0;
        }
        return b2;
    }

    public final long d() {
        if (this.O) {
            return Long.MIN_VALUE;
        }
        if (p()) {
            return this.L;
        }
        long j2 = this.K;
        pw o2 = o();
        if (!o2.h()) {
            if (this.j.size() > 1) {
                ArrayList<pw> arrayList = this.j;
                o2 = (pw) arrayList.get(arrayList.size() - 2);
            } else {
                o2 = null;
            }
        }
        if (o2 != null) {
            j2 = Math.max(j2, o2.i);
        }
        if (this.x) {
            for (mq i2 : this.p) {
                j2 = Math.max(j2, i2.i());
            }
        }
        return j2;
    }

    public final long e() {
        if (p()) {
            return this.L;
        }
        if (this.O) {
            return Long.MIN_VALUE;
        }
        return o().i;
    }

    public final boolean c(long j2) {
        List list;
        long j3;
        if (this.O || this.g.b()) {
            return false;
        }
        if (p()) {
            list = Collections.emptyList();
            j3 = this.L;
        } else {
            List<pw> list2 = this.k;
            pw o2 = o();
            if (o2.h()) {
                list = list2;
                j3 = o2.i;
            } else {
                list = list2;
                j3 = Math.max(this.K, o2.h);
            }
        }
        this.c.a(j2, j3, list, this.i);
        boolean z2 = this.i.b;
        ng ngVar = this.i.a;
        qo qoVar = this.i.c;
        this.i.a();
        if (z2) {
            this.L = -9223372036854775807L;
            this.O = true;
            return true;
        } else if (ngVar == null) {
            if (qoVar != null) {
                this.b.a(qoVar);
            }
            return false;
        } else {
            if (ngVar instanceof pw) {
                this.L = -9223372036854775807L;
                pw pwVar = (pw) ngVar;
                pwVar.a(this);
                this.j.add(pwVar);
                this.A = pwVar.e;
            }
            long a2 = this.g.a(ngVar, this, this.f.a(ngVar.d));
            this.h.a(ngVar.c, ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, a2);
            return true;
        }
    }

    public final void a(int i2, boolean z2, boolean z3) {
        if (!z3) {
            this.r = false;
            this.t = false;
        }
        this.R = i2;
        for (mq a2 : this.p) {
            a2.a(i2);
        }
        if (z2) {
            for (mq b2 : this.p) {
                b2.b();
            }
        }
    }

    public final gc a(int i2, int i3) {
        mq[] mqVarArr = this.p;
        int length = mqVarArr.length;
        boolean z2 = false;
        if (i3 == 1) {
            int i4 = this.s;
            if (i4 != -1) {
                if (!this.r) {
                    this.r = true;
                    this.q[i4] = i2;
                    return mqVarArr[i4];
                } else if (this.q[i4] == i2) {
                    return mqVarArr[i4];
                } else {
                    return b(i2, i3);
                }
            } else if (this.P) {
                return b(i2, i3);
            }
        } else if (i3 == 2) {
            int i5 = this.u;
            if (i5 != -1) {
                if (!this.t) {
                    this.t = true;
                    this.q[i5] = i2;
                    return mqVarArr[i5];
                } else if (this.q[i5] == i2) {
                    return mqVarArr[i5];
                } else {
                    return b(i2, i3);
                }
            } else if (this.P) {
                return b(i2, i3);
            }
        } else {
            for (int i6 = 0; i6 < length; i6++) {
                if (this.q[i6] == i2) {
                    return this.p[i6];
                }
            }
            if (this.P) {
                return b(i2, i3);
            }
        }
        qf qfVar = new qf(this.d);
        qfVar.a(this.Q);
        qfVar.a(this.R);
        qfVar.a((ms) this);
        int i7 = length + 1;
        this.q = Arrays.copyOf(this.q, i7);
        this.q[length] = i2;
        this.p = (mq[]) Arrays.copyOf(this.p, i7);
        this.p[length] = qfVar;
        this.J = Arrays.copyOf(this.J, i7);
        boolean[] zArr = this.J;
        if (i3 == 1 || i3 == 2) {
            z2 = true;
        }
        zArr[length] = z2;
        this.H |= this.J[length];
        if (i3 == 1) {
            this.r = true;
            this.s = length;
        } else if (i3 == 2) {
            this.t = true;
            this.u = length;
        }
        if (d(i3) > d(this.v)) {
            this.w = length;
            this.v = i3;
        }
        this.I = Arrays.copyOf(this.I, i7);
        return qfVar;
    }

    public final void a() {
        this.P = true;
        this.n.post(this.m);
    }

    public final void j() {
        this.n.post(this.l);
    }

    public final void b(long j2) {
        this.Q = j2;
        for (mq a2 : this.p) {
            a2.a(j2);
        }
    }

    private final void m() {
        for (mq a2 : this.p) {
            a2.a(this.M);
        }
        this.M = false;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3, types: [int] */
    /* JADX WARNING: type inference failed for: r2v4, types: [int] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], int, ?[boolean, int, float, short, byte, char]]
  uses: [boolean, ?[int, byte, short, char], int]
  mth insns count: 159
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k() {
        /*
            r14 = this;
            boolean r0 = r14.C
            if (r0 != 0) goto L_0x0174
            int[] r0 = r14.F
            if (r0 != 0) goto L_0x0174
            boolean r0 = r14.x
            if (r0 != 0) goto L_0x000e
            goto L_0x0174
        L_0x000e:
            com.google.ads.interactivemedia.v3.internal.mq[] r0 = r14.p
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x0013:
            if (r3 >= r1) goto L_0x0021
            r4 = r0[r3]
            com.google.ads.interactivemedia.v3.internal.bs r4 = r4.h()
            if (r4 != 0) goto L_0x001e
            return
        L_0x001e:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0021:
            com.google.ads.interactivemedia.v3.internal.mz r0 = r14.D
            r1 = 3
            r3 = -1
            r4 = 1
            if (r0 == 0) goto L_0x00a8
            int r0 = r0.b
            int[] r5 = new int[r0]
            r14.F = r5
            int[] r5 = r14.F
            java.util.Arrays.fill(r5, r3)
            r3 = 0
        L_0x0034:
            if (r3 >= r0) goto L_0x0091
            r5 = 0
        L_0x0037:
            com.google.ads.interactivemedia.v3.internal.mq[] r6 = r14.p
            int r7 = r6.length
            if (r5 >= r7) goto L_0x008e
            r6 = r6[r5]
            com.google.ads.interactivemedia.v3.internal.bs r6 = r6.h()
            com.google.ads.interactivemedia.v3.internal.mz r7 = r14.D
            com.google.ads.interactivemedia.v3.internal.mx r7 = r7.a(r3)
            com.google.ads.interactivemedia.v3.internal.bs r7 = r7.a(r2)
            java.lang.String r8 = r6.h
            java.lang.String r9 = r7.h
            int r10 = com.google.ads.interactivemedia.v3.internal.un.g(r8)
            if (r10 == r1) goto L_0x0060
            int r6 = com.google.ads.interactivemedia.v3.internal.un.g(r9)
            if (r10 != r6) goto L_0x005e
            r6 = 1
            goto L_0x0084
        L_0x005e:
            r6 = 0
            goto L_0x0084
        L_0x0060:
            boolean r9 = com.google.ads.interactivemedia.v3.internal.vf.a(r8, r9)
            if (r9 != 0) goto L_0x0068
            r6 = 0
            goto L_0x0084
        L_0x0068:
            java.lang.String r9 = "application/cea-608"
            boolean r9 = r9.equals(r8)
            if (r9 != 0) goto L_0x007b
            java.lang.String r9 = "application/cea-708"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0079
            goto L_0x007b
        L_0x0079:
            r6 = 1
            goto L_0x0084
        L_0x007b:
            int r6 = r6.y
            int r7 = r7.y
            if (r6 != r7) goto L_0x0083
            r6 = 1
            goto L_0x0084
        L_0x0083:
            r6 = 0
        L_0x0084:
            if (r6 == 0) goto L_0x008b
            int[] r6 = r14.F
            r6[r3] = r5
            goto L_0x008e
        L_0x008b:
            int r5 = r5 + 1
            goto L_0x0037
        L_0x008e:
            int r3 = r3 + 1
            goto L_0x0034
        L_0x0091:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.pz> r0 = r14.o
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r1 = r0.size()
        L_0x0099:
            if (r2 >= r1) goto L_0x00a7
            java.lang.Object r3 = r0.get(r2)
            int r2 = r2 + 1
            com.google.ads.interactivemedia.v3.internal.pz r3 = (com.google.ads.interactivemedia.v3.internal.pz) r3
            r3.a()
            goto L_0x0099
        L_0x00a7:
            return
        L_0x00a8:
            com.google.ads.interactivemedia.v3.internal.mq[] r0 = r14.p
            int r0 = r0.length
            r5 = 6
            r6 = 0
            r7 = 6
            r8 = -1
        L_0x00af:
            r9 = 2
            if (r6 >= r0) goto L_0x00e9
            com.google.ads.interactivemedia.v3.internal.mq[] r10 = r14.p
            r10 = r10[r6]
            com.google.ads.interactivemedia.v3.internal.bs r10 = r10.h()
            java.lang.String r10 = r10.h
            boolean r11 = com.google.ads.interactivemedia.v3.internal.un.b(r10)
            if (r11 == 0) goto L_0x00c3
            goto L_0x00d4
        L_0x00c3:
            boolean r9 = com.google.ads.interactivemedia.v3.internal.un.a(r10)
            if (r9 == 0) goto L_0x00cb
            r9 = 1
            goto L_0x00d4
        L_0x00cb:
            boolean r9 = com.google.ads.interactivemedia.v3.internal.un.c(r10)
            if (r9 == 0) goto L_0x00d3
            r9 = 3
            goto L_0x00d4
        L_0x00d3:
            r9 = 6
        L_0x00d4:
            int r10 = d(r9)
            int r11 = d(r7)
            if (r10 <= r11) goto L_0x00e1
            r8 = r6
            r7 = r9
            goto L_0x00e6
        L_0x00e1:
            if (r9 != r7) goto L_0x00e6
            if (r8 == r3) goto L_0x00e6
            r8 = -1
        L_0x00e6:
            int r6 = r6 + 1
            goto L_0x00af
        L_0x00e9:
            com.google.ads.interactivemedia.v3.internal.pm r1 = r14.c
            com.google.ads.interactivemedia.v3.internal.mx r1 = r1.b()
            int r5 = r1.a
            r14.G = r3
            int[] r3 = new int[r0]
            r14.F = r3
            r3 = 0
        L_0x00f8:
            if (r3 >= r0) goto L_0x0101
            int[] r6 = r14.F
            r6[r3] = r3
            int r3 = r3 + 1
            goto L_0x00f8
        L_0x0101:
            com.google.ads.interactivemedia.v3.internal.mx[] r3 = new com.google.ads.interactivemedia.v3.internal.mx[r0]
            r6 = 0
        L_0x0104:
            if (r6 >= r0) goto L_0x0159
            com.google.ads.interactivemedia.v3.internal.mq[] r10 = r14.p
            r10 = r10[r6]
            com.google.ads.interactivemedia.v3.internal.bs r10 = r10.h()
            if (r6 != r8) goto L_0x0139
            com.google.ads.interactivemedia.v3.internal.bs[] r11 = new com.google.ads.interactivemedia.v3.internal.bs[r5]
            if (r5 != r4) goto L_0x011f
            com.google.ads.interactivemedia.v3.internal.bs r12 = r1.a(r2)
            com.google.ads.interactivemedia.v3.internal.bs r10 = r10.a(r12)
            r11[r2] = r10
            goto L_0x012f
        L_0x011f:
            r12 = 0
        L_0x0120:
            if (r12 >= r5) goto L_0x012f
            com.google.ads.interactivemedia.v3.internal.bs r13 = r1.a(r12)
            com.google.ads.interactivemedia.v3.internal.bs r13 = a(r13, r10, r4)
            r11[r12] = r13
            int r12 = r12 + 1
            goto L_0x0120
        L_0x012f:
            com.google.ads.interactivemedia.v3.internal.mx r10 = new com.google.ads.interactivemedia.v3.internal.mx
            r10.<init>(r11)
            r3[r6] = r10
            r14.G = r6
            goto L_0x0156
        L_0x0139:
            if (r7 != r9) goto L_0x0146
            java.lang.String r11 = r10.h
            boolean r11 = com.google.ads.interactivemedia.v3.internal.un.a(r11)
            if (r11 == 0) goto L_0x0146
            com.google.ads.interactivemedia.v3.internal.bs r11 = r14.e
            goto L_0x0147
        L_0x0146:
            r11 = 0
        L_0x0147:
            com.google.ads.interactivemedia.v3.internal.mx r12 = new com.google.ads.interactivemedia.v3.internal.mx
            com.google.ads.interactivemedia.v3.internal.bs[] r13 = new com.google.ads.interactivemedia.v3.internal.bs[r4]
            com.google.ads.interactivemedia.v3.internal.bs r10 = a(r11, r10, r2)
            r13[r2] = r10
            r12.<init>(r13)
            r3[r6] = r12
        L_0x0156:
            int r6 = r6 + 1
            goto L_0x0104
        L_0x0159:
            com.google.ads.interactivemedia.v3.internal.mz r0 = new com.google.ads.interactivemedia.v3.internal.mz
            r0.<init>(r3)
            r14.D = r0
            com.google.ads.interactivemedia.v3.internal.mz r0 = r14.E
            if (r0 != 0) goto L_0x0165
            r2 = 1
        L_0x0165:
            com.google.ads.interactivemedia.v3.internal.qi.c(r2)
            com.google.ads.interactivemedia.v3.internal.mz r0 = com.google.ads.interactivemedia.v3.internal.mz.a
            r14.E = r0
            r14.y = r4
            com.google.ads.interactivemedia.v3.internal.qe r0 = r14.b
            r0.f()
            return
        L_0x0174:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qa.k():void");
    }

    private final pw o() {
        ArrayList<pw> arrayList = this.j;
        return (pw) arrayList.get(arrayList.size() - 1);
    }

    private final boolean p() {
        return this.L != -9223372036854775807L;
    }

    private static bs a(bs bsVar, bs bsVar2, boolean z2) {
        int i2;
        if (bsVar == null) {
            return bsVar2;
        }
        int i3 = z2 ? bsVar.d : -1;
        if (bsVar.s != -1) {
            i2 = bsVar.s;
        } else {
            i2 = bsVar2.s;
        }
        String a2 = vf.a(bsVar.e, un.g(bsVar2.h));
        String f2 = un.f(a2);
        return bsVar2.a(bsVar.a, bsVar.b, f2 == null ? bsVar2.h : f2, a2, i3, bsVar.m, bsVar.n, i2, bsVar.c, bsVar.x);
    }

    private static fp b(int i2, int i3) {
        StringBuilder sb = new StringBuilder(54);
        sb.append("Unmapped track with id ");
        sb.append(i2);
        sb.append(" of type ");
        sb.append(i3);
        Log.w("HlsSampleStreamWrapper", sb.toString());
        return new fp();
    }

    public final /* synthetic */ tm a(to toVar, long j2, long j3, IOException iOException, int i2) {
        tm tmVar;
        IOException iOException2 = iOException;
        ng ngVar = (ng) toVar;
        long d2 = ngVar.d();
        boolean z2 = ngVar instanceof pw;
        long a2 = this.f.a(iOException2);
        boolean z3 = false;
        boolean a3 = a2 != -9223372036854775807L ? this.c.a(ngVar, a2) : false;
        if (a3) {
            if (z2 && d2 == 0) {
                ArrayList<pw> arrayList = this.j;
                if (((pw) arrayList.remove(arrayList.size() - 1)) == ngVar) {
                    z3 = true;
                }
                qi.c(z3);
                if (this.j.isEmpty()) {
                    this.L = this.K;
                }
            }
            tmVar = tj.b;
        } else {
            long a4 = this.f.a(iOException2, i2);
            if (a4 != -9223372036854775807L) {
                tmVar = tj.a(false, a4);
            } else {
                tmVar = tj.c;
            }
        }
        lr lrVar = this.h;
        sr srVar = ngVar.c;
        Uri e2 = ngVar.e();
        Map f2 = ngVar.f();
        int i3 = ngVar.d;
        int i4 = this.a;
        bs bsVar = ngVar.e;
        int i5 = ngVar.f;
        Object obj = ngVar.g;
        long j4 = ngVar.h;
        sr srVar2 = srVar;
        long j5 = ngVar.i;
        lrVar.a(srVar2, e2, f2, i3, i4, bsVar, i5, obj, j4, j5, j2, j3, d2, iOException, !tmVar.a());
        if (a3) {
            if (!this.y) {
                c(this.K);
            } else {
                this.b.a((mu) this);
            }
        }
        return tmVar;
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3, boolean z2) {
        long j4 = j2;
        long j5 = j3;
        ng ngVar = (ng) toVar;
        lr lrVar = this.h;
        lrVar.b(ngVar.c, ngVar.e(), ngVar.f(), ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, j4, j5, ngVar.d());
        if (!z2) {
            m();
            if (this.z > 0) {
                this.b.a((mu) this);
                return;
            }
            return;
        }
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3) {
        long j4 = j2;
        long j5 = j3;
        ng ngVar = (ng) toVar;
        this.c.a(ngVar);
        lr lrVar = this.h;
        lrVar.a(ngVar.c, ngVar.e(), ngVar.f(), ngVar.d, this.a, ngVar.e, ngVar.f, ngVar.g, ngVar.h, ngVar.i, j4, j5, ngVar.d());
        if (!this.y) {
            c(this.K);
        } else {
            this.b.a((mu) this);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void l() {
        this.x = true;
        k();
    }
}
