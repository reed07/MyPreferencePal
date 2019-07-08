package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.C;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: IMASDK */
public class mq implements gc {
    private final sf a;
    private final int b;
    private final mo c = new mo();
    private final mp d = new mp();
    private final ut e = new ut(32);
    private mr f = new mr(0, this.b);
    private mr g;
    private mr h;
    private bs i;
    private boolean j;
    private bs k;
    private long l;
    private long m;
    private boolean n;
    private ms o;

    public mq(sf sfVar) {
        this.a = sfVar;
        this.b = sfVar.c();
        mr mrVar = this.f;
        this.g = mrVar;
        this.h = mrVar;
    }

    public final void a() {
        a(false);
    }

    public final void a(boolean z) {
        this.c.a(z);
        a(this.f);
        this.f = new mr(0, this.b);
        mr mrVar = this.f;
        this.g = mrVar;
        this.h = mrVar;
        this.m = 0;
        this.a.b();
    }

    public final void a(int i2) {
        this.c.b(i2);
    }

    public final void b() {
        this.n = true;
    }

    public final int c() {
        return this.c.a();
    }

    public final void b(int i2) {
        this.m = this.c.a(i2);
        long j2 = this.m;
        if (j2 == 0 || j2 == this.f.a) {
            a(this.f);
            this.f = new mr(this.m, this.b);
            mr mrVar = this.f;
            this.g = mrVar;
            this.h = mrVar;
            return;
        }
        mr mrVar2 = this.f;
        while (this.m > mrVar2.b) {
            mrVar2 = mrVar2.e;
        }
        mr mrVar3 = mrVar2.e;
        a(mrVar3);
        mrVar2.e = new mr(mrVar2.b, this.b);
        this.h = this.m == mrVar2.b ? mrVar2.e : mrVar2;
        if (this.g == mrVar3) {
            this.g = mrVar2.e;
        }
    }

    public final boolean d() {
        return this.c.e();
    }

    public final int e() {
        return this.c.b();
    }

    public final int f() {
        return this.c.c();
    }

    public final int g() {
        return this.c.d();
    }

    public final bs h() {
        return this.c.f();
    }

    public final long i() {
        return this.c.g();
    }

    public final boolean j() {
        return this.c.h();
    }

    public final long k() {
        return this.c.i();
    }

    public final void l() {
        this.c.j();
        this.g = this.f;
    }

    public final void a(long j2, boolean z, boolean z2) {
        c(this.c.b(j2, z, z2));
    }

    public final void m() {
        c(this.c.l());
    }

    public final void n() {
        c(this.c.m());
    }

    public final int o() {
        return this.c.k();
    }

    public final int b(long j2, boolean z, boolean z2) {
        return this.c.a(j2, true, z2);
    }

    public final boolean c(int i2) {
        return this.c.c(i2);
    }

    public final int a(bu buVar, ex exVar, boolean z, boolean z2, long j2) {
        int i2;
        int[] iArr;
        int[] iArr2;
        ex exVar2 = exVar;
        switch (this.c.a(buVar, exVar, z, z2, this.i, this.d)) {
            case C.RESULT_FORMAT_READ /*-5*/:
                this.i = buVar.a;
                return -5;
            case -4:
                if (!exVar.c()) {
                    if (exVar2.c < j2) {
                        exVar2.b(Integer.MIN_VALUE);
                    }
                    if (!exVar.e()) {
                        if (exVar.f()) {
                            mp mpVar = this.d;
                            long j3 = mpVar.b;
                            this.e.a(1);
                            a(j3, this.e.a, 1);
                            long j4 = j3 + 1;
                            byte b2 = this.e.a[0];
                            boolean z3 = (b2 & 128) != 0;
                            byte b3 = b2 & Byte.MAX_VALUE;
                            if (exVar2.a.a == null) {
                                exVar2.a.a = new byte[16];
                            }
                            a(j4, exVar2.a.a, (int) b3);
                            long j5 = j4 + ((long) b3);
                            if (z3) {
                                this.e.a(2);
                                a(j5, this.e.a, 2);
                                j5 += 2;
                                i2 = this.e.f();
                            } else {
                                i2 = 1;
                            }
                            int[] iArr3 = exVar2.a.b;
                            if (iArr3 == null || iArr3.length < i2) {
                                iArr = new int[i2];
                            } else {
                                iArr = iArr3;
                            }
                            int[] iArr4 = exVar2.a.c;
                            if (iArr4 == null || iArr4.length < i2) {
                                iArr2 = new int[i2];
                            } else {
                                iArr2 = iArr4;
                            }
                            if (z3) {
                                int i3 = i2 * 6;
                                this.e.a(i3);
                                a(j5, this.e.a, i3);
                                j5 += (long) i3;
                                this.e.c(0);
                                for (int i4 = 0; i4 < i2; i4++) {
                                    iArr[i4] = this.e.f();
                                    iArr2[i4] = this.e.p();
                                }
                            } else {
                                iArr[0] = 0;
                                iArr2[0] = mpVar.a - ((int) (j5 - mpVar.b));
                            }
                            gd gdVar = mpVar.c;
                            exVar2.a.a(i2, iArr, iArr2, gdVar.b, exVar2.a.a, gdVar.a, gdVar.c, gdVar.d);
                            int i5 = (int) (j5 - mpVar.b);
                            mpVar.b += (long) i5;
                            mpVar.a -= i5;
                        }
                        exVar2.d(this.d.a);
                        long j6 = this.d.b;
                        ByteBuffer byteBuffer = exVar2.b;
                        int i6 = this.d.a;
                        b(j6);
                        while (i6 > 0) {
                            int min = Math.min(i6, (int) (this.g.b - j6));
                            byteBuffer.put(this.g.d.a, this.g.a(j6), min);
                            i6 -= min;
                            j6 += (long) min;
                            if (j6 == this.g.b) {
                                this.g = this.g.e;
                            }
                        }
                    }
                }
                return -4;
            case -3:
                return -3;
            default:
                throw new IllegalStateException();
        }
    }

    private final void a(long j2, byte[] bArr, int i2) {
        b(j2);
        long j3 = j2;
        int i3 = i2;
        while (i3 > 0) {
            int min = Math.min(i3, (int) (this.g.b - j3));
            System.arraycopy(this.g.d.a, this.g.a(j3), bArr, i2 - i3, min);
            i3 -= min;
            j3 += (long) min;
            if (j3 == this.g.b) {
                this.g = this.g.e;
            }
        }
    }

    private final void b(long j2) {
        while (j2 >= this.g.b) {
            this.g = this.g.e;
        }
    }

    private final void c(long j2) {
        if (j2 != -1) {
            while (j2 >= this.f.b) {
                this.a.a(this.f.d);
                this.f = this.f.a();
            }
            if (this.g.a < this.f.a) {
                this.g = this.f;
            }
        }
    }

    public final void a(ms msVar) {
        this.o = msVar;
    }

    public final void a(long j2) {
        if (this.l != j2) {
            this.l = j2;
            this.j = true;
        }
    }

    public void a(bs bsVar) {
        long j2 = this.l;
        bs bsVar2 = bsVar == null ? null : (j2 == 0 || bsVar.l == Long.MAX_VALUE) ? bsVar : bsVar.a(bsVar.l + j2);
        boolean a2 = this.c.a(bsVar2);
        this.k = bsVar;
        this.j = false;
        ms msVar = this.o;
        if (msVar != null && a2) {
            msVar.j();
        }
    }

    public final int a(fr frVar, int i2, boolean z) throws IOException, InterruptedException {
        int a2 = frVar.a(this.h.d.a, this.h.a(this.m), d(i2));
        if (a2 != -1) {
            e(a2);
            return a2;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public final void a(ut utVar, int i2) {
        while (i2 > 0) {
            int d2 = d(i2);
            utVar.a(this.h.d.a, this.h.a(this.m), d2);
            i2 -= d2;
            e(d2);
        }
    }

    public final void a(long j2, int i2, int i3, int i4, gd gdVar) {
        if (this.j) {
            a(this.k);
        }
        long j3 = j2 + this.l;
        if (this.n) {
            if ((i2 & 1) != 0 && this.c.a(j3)) {
                this.n = false;
            } else {
                return;
            }
        }
        int i5 = i3;
        this.c.a(j3, i2, (this.m - ((long) i5)) - ((long) i4), i5, gdVar);
    }

    private final void a(mr mrVar) {
        if (mrVar.c) {
            int i2 = ((int) (this.h.a - mrVar.a)) / this.b;
            se[] seVarArr = new se[((this.h.c ? 1 : 0) + i2)];
            for (int i3 = 0; i3 < seVarArr.length; i3++) {
                seVarArr[i3] = mrVar.d;
                mrVar = mrVar.a();
            }
            this.a.a(seVarArr);
        }
    }

    private final int d(int i2) {
        if (!this.h.c) {
            mr mrVar = this.h;
            se a2 = this.a.a();
            mr mrVar2 = new mr(this.h.b, this.b);
            mrVar.d = a2;
            mrVar.e = mrVar2;
            mrVar.c = true;
        }
        return Math.min(i2, (int) (this.h.b - this.m));
    }

    private final void e(int i2) {
        this.m += (long) i2;
        if (this.m == this.h.b) {
            this.h = this.h.e;
        }
    }
}
