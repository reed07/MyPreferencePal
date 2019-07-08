package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;
import java.util.List;

/* compiled from: IMASDK */
final class pm {
    private final pt a;
    private final sn b;
    private final sn c;
    private final qg d;
    private final qo[] e;
    private final qv f;
    private final mx g;
    private final List<bs> h;
    private final po i = new po();
    private boolean j;
    private byte[] k;
    private IOException l;
    private qo m;
    private boolean n;
    private rt o;
    private long p = -9223372036854775807L;
    private boolean q;

    public pm(pt ptVar, qv qvVar, qo[] qoVarArr, ps psVar, tz tzVar, qg qgVar, List<bs> list) {
        this.a = ptVar;
        this.f = qvVar;
        this.e = qoVarArr;
        this.d = qgVar;
        this.h = list;
        bs[] bsVarArr = new bs[qoVarArr.length];
        int[] iArr = new int[qoVarArr.length];
        for (int i2 = 0; i2 < qoVarArr.length; i2++) {
            bsVarArr[i2] = qoVarArr[i2].b;
            iArr[i2] = i2;
        }
        this.b = psVar.a();
        if (tzVar != null) {
            this.b.a(tzVar);
        }
        this.c = psVar.a();
        this.g = new mx(bsVarArr);
        this.o = new pr(this.g, iArr);
    }

    public final void a() throws IOException {
        IOException iOException = this.l;
        if (iOException == null) {
            qo qoVar = this.m;
            if (qoVar != null && this.q) {
                this.f.b(qoVar);
                return;
            }
            return;
        }
        throw iOException;
    }

    public final mx b() {
        return this.g;
    }

    public final void a(rt rtVar) {
        this.o = rtVar;
    }

    public final rt c() {
        return this.o;
    }

    public final void d() {
        this.l = null;
    }

    public final void a(boolean z) {
        this.j = z;
    }

    public final void a(long j2, long j3, List<pw> list, pp ppVar) {
        pw pwVar;
        int i2;
        long j4;
        long j5;
        long j6;
        qo qoVar;
        long j7;
        int i3;
        long j8 = j3;
        pp ppVar2 = ppVar;
        if (list.isEmpty()) {
            List<pw> list2 = list;
            pwVar = null;
        } else {
            pwVar = (pw) list.get(list.size() - 1);
        }
        if (pwVar == null) {
            i2 = -1;
        } else {
            i2 = this.g.a(pwVar.e);
        }
        long j9 = j8 - j2;
        long j10 = (this.p > -9223372036854775807L ? 1 : (this.p == -9223372036854775807L ? 0 : -1)) != 0 ? this.p - j2 : -9223372036854775807L;
        if (pwVar == null || this.n) {
            j4 = j10;
            j5 = j9;
        } else {
            long j11 = pwVar.i - pwVar.h;
            long max = Math.max(0, j9 - j11);
            if (j10 != -9223372036854775807L) {
                j4 = Math.max(0, j10 - j11);
                j5 = max;
            } else {
                j4 = j10;
                j5 = max;
            }
        }
        boolean z = false;
        this.o.a(j2, j5, j4, list, a(pwVar, j8));
        int i4 = this.o.i();
        boolean z2 = i2 != i4;
        qo qoVar2 = this.e[i4];
        if (!this.f.a(qoVar2)) {
            ppVar.c = qoVar2;
            boolean z3 = this.q;
            if (this.m == qoVar2) {
                z = true;
            }
            this.q = z3 & z;
            this.m = qoVar2;
            return;
        }
        pp ppVar3 = ppVar;
        qp a2 = this.f.a(qoVar2, true);
        this.n = a2.p;
        if (a2.i) {
            j6 = -9223372036854775807L;
        } else {
            j6 = a2.a() - this.f.c();
        }
        this.p = j6;
        long c2 = a2.c - this.f.c();
        int i5 = i2;
        pw pwVar2 = pwVar;
        int i6 = i4;
        boolean z4 = true;
        long a3 = a(pwVar, z2, a2, c2, j3);
        if (a3 >= a2.f) {
            i3 = i6;
            j7 = c2;
            qoVar = qoVar2;
        } else if (pwVar2 == null || !z2) {
            this.l = new ld();
            return;
        } else {
            qo qoVar3 = this.e[i5];
            qp a4 = this.f.a(qoVar3, true);
            long c3 = a4.c - this.f.c();
            qoVar = qoVar3;
            a2 = a4;
            a3 = pwVar2.g();
            j7 = c3;
            i3 = i5;
        }
        int i7 = (int) (a3 - a2.f);
        if (i7 < a2.l.size()) {
            this.q = false;
            this.m = null;
            qq qqVar = (qq) a2.l.get(i7);
            Uri a5 = a(a2, qqVar.b);
            ppVar3.a = a(a5, i3);
            if (ppVar3.a == null) {
                Uri a6 = a(a2, qqVar);
                ppVar3.a = a(a6, i3);
                if (ppVar3.a == null) {
                    ppVar3.a = pw.a(this.a, this.b, j7, a2, i7, qoVar, this.h, this.o.b(), this.o.c(), this.j, this.d, pwVar2, this.i.get(a6), this.i.get(a5));
                }
            }
        } else if (a2.i) {
            ppVar3.b = true;
        } else {
            ppVar3.c = qoVar;
            boolean z5 = this.q;
            if (this.m != qoVar) {
                z4 = false;
            }
            this.q = z5 & z4;
            this.m = qoVar;
        }
    }

    public final void a(ng ngVar) {
        if (ngVar instanceof pn) {
            pn pnVar = (pn) ngVar;
            this.k = pnVar.c();
            this.i.put(pnVar.c.a, pnVar.g());
        }
    }

    public final boolean a(ng ngVar, long j2) {
        rt rtVar = this.o;
        return rtVar.a(rtVar.c(this.g.a(ngVar.e)), j2);
    }

    public final boolean a(qo qoVar, long j2) {
        int a2 = this.g.a(qoVar.b);
        if (a2 == -1) {
            return true;
        }
        int c2 = this.o.c(a2);
        if (c2 == -1) {
            return true;
        }
        this.q = (this.m == qoVar) | this.q;
        if (j2 == -9223372036854775807L || this.o.a(c2, j2)) {
            return true;
        }
        return false;
    }

    public final nt[] a(pw pwVar, long j2) {
        pw pwVar2 = pwVar;
        int a2 = pwVar2 == null ? -1 : this.g.a(pwVar2.e);
        nt[] ntVarArr = new nt[this.o.g()];
        for (int i2 = 0; i2 < ntVarArr.length; i2++) {
            int b2 = this.o.b(i2);
            qo qoVar = this.e[b2];
            if (!this.f.a(qoVar)) {
                ntVarArr[i2] = nt.a;
            } else {
                qp a3 = this.f.a(qoVar, false);
                long c2 = a3.c - this.f.c();
                long j3 = c2;
                long a4 = a(pwVar, b2 != a2, a3, c2, j2);
                if (a4 < a3.f) {
                    ntVarArr[i2] = nt.a;
                } else {
                    ntVarArr[i2] = new pq(a3, j3, (int) (a4 - a3.f));
                }
            }
        }
        return ntVarArr;
    }

    private final long a(pw pwVar, boolean z, qp qpVar, long j2, long j3) {
        if (pwVar != null && !z) {
            return pwVar.g();
        }
        long j4 = qpVar.m + j2;
        if (pwVar != null && !this.n) {
            j3 = pwVar.h;
        }
        if (!qpVar.i && j3 >= j4) {
            return qpVar.f + ((long) qpVar.l.size());
        }
        return ((long) vf.a(qpVar.l, Long.valueOf(j3 - j2), true, !this.f.e() || pwVar == null)) + qpVar.f;
    }

    private final ng a(Uri uri, int i2) {
        if (uri == null) {
            return null;
        }
        if (this.i.containsKey(uri)) {
            po poVar = this.i;
            poVar.put(uri, (byte[]) poVar.remove(uri));
            return null;
        }
        sr srVar = new sr(uri, 0, -1, null, 1);
        pn pnVar = new pn(this.c, srVar, this.e[i2].b, this.o.b(), this.o.c(), this.k);
        return pnVar;
    }

    private static Uri a(qp qpVar, qq qqVar) {
        if (qqVar == null || qqVar.g == null) {
            return null;
        }
        return qi.a(qpVar.n, qqVar.g);
    }
}
