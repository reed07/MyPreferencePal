package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.internal.js.a;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: IMASDK */
final class pw extends ns {
    private static final AtomicInteger b = new AtomicInteger();
    private final boolean A;
    private fq B;
    private boolean C;
    private qa D;
    private int E;
    private boolean F;
    private volatile boolean G;
    private boolean H;
    public final int a;
    private final int l;
    private final qo m;
    private final sn n;
    private final sr o;
    private final boolean p;
    private final boolean q;
    private final ve r;
    private final boolean s;
    private final pt t;
    private final List<bs> u;
    private final fa v;
    private final fq w;
    private final km x;
    private final ut y;
    private final boolean z;

    public static pw a(pt ptVar, sn snVar, long j, qp qpVar, int i, qo qoVar, List<bs> list, int i2, Object obj, boolean z2, qg qgVar, pw pwVar, byte[] bArr, byte[] bArr2) {
        boolean z3;
        sr srVar;
        sn snVar2;
        boolean z4;
        boolean z5;
        ut utVar;
        km kmVar;
        fq fqVar;
        sn snVar3 = snVar;
        qp qpVar2 = qpVar;
        int i3 = i;
        pw pwVar2 = pwVar;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        qq qqVar = (qq) qpVar2.l.get(i3);
        sr srVar2 = new sr(qi.a(qpVar2.n, qqVar.a), qqVar.i, qqVar.j, null);
        boolean z6 = bArr3 != null;
        sn a2 = a(snVar3, bArr3, z6 ? a(qqVar.h) : null);
        qq qqVar2 = qqVar.b;
        if (qqVar2 != null) {
            boolean z7 = bArr4 != null;
            byte[] a3 = z7 ? a(qqVar2.h) : null;
            z3 = z6;
            sr srVar3 = new sr(qi.a(qpVar2.n, qqVar2.a), qqVar2.i, qqVar2.j, null);
            sn a4 = a(snVar3, bArr4, a3);
            srVar = srVar3;
            boolean z8 = z7;
            snVar2 = a4;
            z4 = z8;
        } else {
            z3 = z6;
            z4 = false;
            snVar2 = null;
            srVar = null;
        }
        long j2 = j + qqVar.e;
        long j3 = j2 + qqVar.c;
        int i4 = qpVar2.e + qqVar.d;
        if (pwVar2 != null) {
            km kmVar2 = pwVar2.x;
            ut utVar2 = pwVar2.y;
            boolean z9 = pwVar2.m != qoVar || !pwVar2.H;
            kmVar = kmVar2;
            utVar = utVar2;
            z5 = z9;
            fqVar = (!pwVar2.C || pwVar2.l != i4 || z9) ? null : pwVar2.B;
        } else {
            qo qoVar2 = qoVar;
            kmVar = new km();
            utVar = new ut(10);
            fqVar = null;
            z5 = false;
        }
        pw pwVar3 = new pw(ptVar, a2, srVar2, z3, snVar2, srVar, z4, qoVar, list, i2, obj, j2, j3, qpVar2.f + ((long) i3), i4, qqVar.k, z2, qgVar.a(i4), qqVar.f, fqVar, kmVar, utVar, z5);
        return pwVar3;
    }

    private pw(pt ptVar, sn snVar, sr srVar, boolean z2, sn snVar2, sr srVar2, boolean z3, qo qoVar, List<bs> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z4, boolean z5, ve veVar, fa faVar, fq fqVar, km kmVar, ut utVar, boolean z6) {
        sr srVar3 = srVar2;
        qo qoVar2 = qoVar;
        super(snVar, srVar, qoVar2.b, i, obj, j, j2, j3);
        this.z = z2;
        this.l = i2;
        this.n = snVar2;
        this.o = srVar3;
        this.A = z3;
        this.m = qoVar2;
        this.p = z5;
        this.r = veVar;
        this.q = z4;
        this.t = ptVar;
        this.u = list;
        this.v = faVar;
        this.w = fqVar;
        this.x = kmVar;
        this.y = utVar;
        this.s = z6;
        this.F = srVar3 != null;
        this.a = b.getAndIncrement();
    }

    public final void a(qa qaVar) {
        this.D = qaVar;
    }

    public final boolean h() {
        return this.H;
    }

    public final void a() {
        this.G = true;
    }

    public final void b() throws IOException, InterruptedException {
        if (this.B == null) {
            fq fqVar = this.w;
            if (fqVar != null) {
                this.B = fqVar;
                this.C = true;
                this.F = false;
                this.D.a(this.a, this.s, true);
            }
        }
        if (this.F) {
            a(this.n, this.o, this.A);
            this.E = 0;
            this.F = false;
        }
        if (!this.G) {
            if (!this.q) {
                if (!this.p) {
                    this.r.e();
                } else if (this.r.a() == Long.MAX_VALUE) {
                    this.r.a(this.h);
                }
                a((sn) this.j, this.c, this.z);
            }
            this.H = true;
        }
    }

    private final void a(sn snVar, sr srVar, boolean z2) throws IOException, InterruptedException {
        boolean z3;
        sr srVar2;
        sn snVar2;
        fr frVar;
        long j;
        sr srVar3 = srVar;
        if (z2) {
            srVar2 = srVar3;
            z3 = this.E != 0;
            snVar2 = snVar;
        } else {
            srVar2 = srVar3.a((long) this.E);
            z3 = false;
            snVar2 = snVar;
        }
        try {
            fr frVar2 = new fr(snVar, srVar2.d, snVar2.a(srVar2));
            if (this.B == null) {
                long a2 = a(frVar2);
                frVar2.a();
                pt ptVar = this.t;
                fq fqVar = this.w;
                Uri uri = srVar2.a;
                bs bsVar = this.e;
                List<bs> list = this.u;
                fa faVar = this.v;
                ve veVar = this.r;
                snVar.b();
                frVar = frVar2;
                pu a3 = ptVar.a(fqVar, uri, bsVar, list, faVar, veVar, frVar);
                this.B = a3.a;
                this.C = a3.c;
                if (a3.b) {
                    qa qaVar = this.D;
                    if (a2 != -9223372036854775807L) {
                        j = this.r.b(a2);
                    } else {
                        j = this.h;
                    }
                    qaVar.b(j);
                }
                this.D.a(this.a, this.s, false);
                this.B.a((fs) this.D);
            } else {
                frVar = frVar2;
            }
            if (z3) {
                frVar.b(this.E);
            }
            for (int i = 0; i == 0; i = this.B.a(frVar, (fx) null)) {
                if (this.G) {
                    break;
                }
            }
            this.E = (int) (frVar.c() - srVar3.d);
            vf.a(snVar);
        } catch (Throwable th) {
            vf.a(snVar);
            throw th;
        }
    }

    private final long a(fr frVar) throws IOException, InterruptedException {
        frVar.a();
        try {
            frVar.c(this.y.a, 0, 10);
            this.y.a(10);
            if (this.y.i() != km.a) {
                return -9223372036854775807L;
            }
            this.y.d(3);
            int o2 = this.y.o();
            int i = o2 + 10;
            if (i > this.y.a.length) {
                byte[] bArr = this.y.a;
                this.y.a(i);
                System.arraycopy(bArr, 0, this.y.a, 0, 10);
            }
            frVar.c(this.y.a, 10, o2);
            js a2 = this.x.a(this.y.a, o2);
            if (a2 == null) {
                return -9223372036854775807L;
            }
            int a3 = a2.a();
            for (int i2 = 0; i2 < a3; i2++) {
                a a4 = a2.a(i2);
                if (a4 instanceof ku) {
                    ku kuVar = (ku) a4;
                    if (HlsMediaChunk.PRIV_TIMESTAMP_FRAME_OWNER.equals(kuVar.a)) {
                        System.arraycopy(kuVar.b, 0, this.y.a, 0, 8);
                        this.y.a(8);
                        return this.y.m() & 8589934591L;
                    }
                }
            }
            return -9223372036854775807L;
        } catch (EOFException unused) {
            return -9223372036854775807L;
        }
    }

    private static byte[] a(String str) {
        if (vf.d(str).startsWith("0x")) {
            str = str.substring(2);
        }
        byte[] byteArray = new BigInteger(str, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (16 - byteArray.length) + length, byteArray.length - length);
        return bArr;
    }

    private static sn a(sn snVar, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new pk(snVar, bArr, bArr2) : snVar;
    }
}
