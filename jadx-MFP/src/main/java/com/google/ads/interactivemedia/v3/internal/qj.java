package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/* compiled from: IMASDK */
public final class qj implements qv, tl<tu<qr>> {
    public static final qw a = qk.a;
    /* access modifiers changed from: private */
    public final ps b;
    private final qu c;
    /* access modifiers changed from: private */
    public final ti d;
    private final IdentityHashMap<qo, ql> e;
    private final List<qx> f;
    /* access modifiers changed from: private */
    public final double g;
    /* access modifiers changed from: private */
    public tv<qr> h;
    /* access modifiers changed from: private */
    public lr i;
    private tj j;
    /* access modifiers changed from: private */
    public Handler k;
    private ra l;
    /* access modifiers changed from: private */
    public qn m;
    /* access modifiers changed from: private */
    public qo n;
    private qp o;
    private boolean p;
    private long q;

    public qj(ps psVar, ti tiVar, qu quVar) {
        this(psVar, tiVar, quVar, 3.5d);
    }

    private qj(ps psVar, ti tiVar, qu quVar, double d2) {
        this.b = psVar;
        this.c = quVar;
        this.d = tiVar;
        this.g = 3.5d;
        this.f = new ArrayList();
        this.e = new IdentityHashMap<>();
        this.q = -9223372036854775807L;
    }

    public final void a(Uri uri, lr lrVar, ra raVar) {
        this.k = new Handler();
        this.i = lrVar;
        this.l = raVar;
        tu tuVar = new tu(this.b.a(), uri, 4, this.c.a());
        qi.c(this.j == null);
        this.j = new tj("DefaultHlsPlaylistTracker:MasterPlaylist");
        lrVar.a(tuVar.a, tuVar.b, this.j.a(tuVar, this, this.d.a(tuVar.b)));
    }

    public final void a() {
        this.n = null;
        this.o = null;
        this.m = null;
        this.q = -9223372036854775807L;
        this.j.a((tp) null);
        this.j = null;
        for (ql c2 : this.e.values()) {
            c2.c();
        }
        this.k.removeCallbacksAndMessages(null);
        this.k = null;
        this.e.clear();
    }

    public final void a(qx qxVar) {
        this.f.add(qxVar);
    }

    public final void b(qx qxVar) {
        this.f.remove(qxVar);
    }

    public final qn b() {
        return this.m;
    }

    public final qp a(qo qoVar, boolean z) {
        qp a2 = ((ql) this.e.get(qoVar)).a();
        if (a2 != null && z && qoVar != this.n && this.m.b.contains(qoVar)) {
            qp qpVar = this.o;
            if (qpVar == null || !qpVar.i) {
                this.n = qoVar;
                ((ql) this.e.get(this.n)).d();
            }
        }
        return a2;
    }

    public final long c() {
        return this.q;
    }

    public final boolean a(qo qoVar) {
        return ((ql) this.e.get(qoVar)).b();
    }

    public final void d() throws IOException {
        tj tjVar = this.j;
        if (tjVar != null) {
            tjVar.a();
        }
        qo qoVar = this.n;
        if (qoVar != null) {
            b(qoVar);
        }
    }

    public final void b(qo qoVar) throws IOException {
        ((ql) this.e.get(qoVar)).e();
    }

    public final void c(qo qoVar) {
        ((ql) this.e.get(qoVar)).d();
    }

    public final boolean e() {
        return this.p;
    }

    /* access modifiers changed from: private */
    public final boolean f() {
        List<qo> list = this.m.b;
        int size = list.size();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (int i2 = 0; i2 < size; i2++) {
            ql qlVar = (ql) this.e.get(list.get(i2));
            if (elapsedRealtime > qlVar.h) {
                this.n = qlVar.a;
                qlVar.d();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final void a(qo qoVar, qp qpVar) {
        if (qoVar == this.n) {
            if (this.o == null) {
                this.p = !qpVar.i;
                this.q = qpVar.c;
            }
            this.o = qpVar;
            this.l.a(qpVar);
        }
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((qx) this.f.get(i2)).i();
        }
    }

    /* access modifiers changed from: private */
    public final boolean a(qo qoVar, long j2) {
        boolean z = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            z |= !((qx) this.f.get(i2)).a(qoVar, j2);
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        if (r1.i != false) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.qp a(com.google.ads.interactivemedia.v3.internal.qp r28, com.google.ads.interactivemedia.v3.internal.qp r29) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0035
            long r5 = r2.f
            long r7 = r1.f
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0013
            goto L_0x0035
        L_0x0013:
            long r5 = r2.f
            long r7 = r1.f
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x0034
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r5 = r2.l
            int r5 = r5.size()
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r6 = r1.l
            int r6 = r6.size()
            if (r5 > r6) goto L_0x0035
            if (r5 != r6) goto L_0x0034
            boolean r5 = r2.i
            if (r5 == 0) goto L_0x0034
            boolean r5 = r1.i
            if (r5 != 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r3 = 0
        L_0x0035:
            if (r3 != 0) goto L_0x0076
            boolean r2 = r2.i
            if (r2 == 0) goto L_0x0075
            boolean r2 = r1.i
            if (r2 == 0) goto L_0x0040
            return r1
        L_0x0040:
            com.google.ads.interactivemedia.v3.internal.qp r2 = new com.google.ads.interactivemedia.v3.internal.qp
            r3 = r2
            int r4 = r1.a
            java.lang.String r5 = r1.n
            java.util.List r6 = r1.o
            long r7 = r1.b
            long r9 = r1.c
            boolean r11 = r1.d
            int r12 = r1.e
            long r13 = r1.f
            int r15 = r1.g
            r29 = r2
            r23 = r3
            long r2 = r1.h
            r16 = r2
            boolean r2 = r1.p
            r18 = r2
            r19 = 1
            boolean r2 = r1.j
            r20 = r2
            com.google.ads.interactivemedia.v3.internal.fa r2 = r1.k
            r21 = r2
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r1 = r1.l
            r22 = r1
            r3 = r23
            r3.<init>(r4, r5, r6, r7, r9, r11, r12, r13, r15, r16, r18, r19, r20, r21, r22)
            return r29
        L_0x0075:
            return r1
        L_0x0076:
            boolean r3 = r2.j
            if (r3 == 0) goto L_0x007e
            long r5 = r2.c
            r13 = r5
            goto L_0x00ad
        L_0x007e:
            com.google.ads.interactivemedia.v3.internal.qp r3 = r0.o
            if (r3 == 0) goto L_0x0085
            long r5 = r3.c
            goto L_0x0087
        L_0x0085:
            r5 = 0
        L_0x0087:
            if (r1 == 0) goto L_0x00ac
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r3 = r1.l
            int r3 = r3.size()
            com.google.ads.interactivemedia.v3.internal.qq r7 = b(r28, r29)
            if (r7 == 0) goto L_0x009c
            long r5 = r1.c
            long r7 = r7.e
            long r5 = r5 + r7
            r13 = r5
            goto L_0x00ad
        L_0x009c:
            long r7 = (long) r3
            long r9 = r2.f
            long r11 = r1.f
            long r9 = r9 - r11
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x00ac
            long r5 = r28.a()
            r13 = r5
            goto L_0x00ad
        L_0x00ac:
            r13 = r5
        L_0x00ad:
            boolean r3 = r2.d
            if (r3 == 0) goto L_0x00b6
            int r1 = r2.e
            r16 = r1
            goto L_0x00db
        L_0x00b6:
            com.google.ads.interactivemedia.v3.internal.qp r3 = r0.o
            if (r3 == 0) goto L_0x00bd
            int r3 = r3.e
            goto L_0x00be
        L_0x00bd:
            r3 = 0
        L_0x00be:
            if (r1 == 0) goto L_0x00d9
            com.google.ads.interactivemedia.v3.internal.qq r5 = b(r28, r29)
            if (r5 == 0) goto L_0x00d9
            int r1 = r1.e
            int r3 = r5.d
            int r1 = r1 + r3
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r3 = r2.l
            java.lang.Object r3 = r3.get(r4)
            com.google.ads.interactivemedia.v3.internal.qq r3 = (com.google.ads.interactivemedia.v3.internal.qq) r3
            int r3 = r3.d
            int r1 = r1 - r3
            r16 = r1
            goto L_0x00db
        L_0x00d9:
            r16 = r3
        L_0x00db:
            com.google.ads.interactivemedia.v3.internal.qp r1 = new com.google.ads.interactivemedia.v3.internal.qp
            r7 = r1
            int r8 = r2.a
            java.lang.String r9 = r2.n
            java.util.List r10 = r2.o
            long r11 = r2.b
            r15 = 1
            long r3 = r2.f
            r17 = r3
            int r3 = r2.g
            r19 = r3
            long r3 = r2.h
            r20 = r3
            boolean r3 = r2.p
            r22 = r3
            boolean r3 = r2.i
            r23 = r3
            boolean r3 = r2.j
            r24 = r3
            com.google.ads.interactivemedia.v3.internal.fa r3 = r2.k
            r25 = r3
            java.util.List<com.google.ads.interactivemedia.v3.internal.qq> r2 = r2.l
            r26 = r2
            r7.<init>(r8, r9, r10, r11, r13, r15, r16, r17, r19, r20, r22, r23, r24, r25, r26)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.qj.a(com.google.ads.interactivemedia.v3.internal.qp, com.google.ads.interactivemedia.v3.internal.qp):com.google.ads.interactivemedia.v3.internal.qp");
    }

    private static qq b(qp qpVar, qp qpVar2) {
        int i2 = (int) (qpVar2.f - qpVar.f);
        List<qq> list = qpVar.l;
        if (i2 < list.size()) {
            return (qq) list.get(i2);
        }
        return null;
    }

    public final /* synthetic */ tm a(to toVar, long j2, long j3, IOException iOException, int i2) {
        tu tuVar = (tu) toVar;
        long a2 = this.d.a(iOException, i2);
        boolean z = a2 == -9223372036854775807L;
        long j4 = a2;
        this.i.a(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j3, tuVar.d(), iOException, z);
        if (z) {
            return tj.c;
        }
        return tj.a(false, j4);
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3, boolean z) {
        tu tuVar = (tu) toVar;
        this.i.b(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j3, tuVar.d());
    }

    public final /* synthetic */ void a(to toVar, long j2, long j3) {
        qn qnVar;
        tu tuVar = (tu) toVar;
        qr qrVar = (qr) tuVar.c();
        boolean z = qrVar instanceof qp;
        if (z) {
            qnVar = qn.a(qrVar.n);
        } else {
            qnVar = (qn) qrVar;
        }
        this.m = qnVar;
        this.h = this.c.a(qnVar);
        this.n = (qo) qnVar.b.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(qnVar.b);
        arrayList.addAll(qnVar.c);
        arrayList.addAll(qnVar.d);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            qo qoVar = (qo) arrayList.get(i2);
            this.e.put(qoVar, new ql(this, qoVar));
        }
        ql qlVar = (ql) this.e.get(this.n);
        if (z) {
            qlVar.a((qp) qrVar, j3);
        } else {
            long j4 = j3;
            qlVar.d();
        }
        this.i.a(tuVar.a, tuVar.e(), tuVar.f(), 4, j2, j3, tuVar.d());
    }
}
