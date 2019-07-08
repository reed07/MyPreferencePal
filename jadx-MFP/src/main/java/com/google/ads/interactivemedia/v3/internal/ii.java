package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;

/* compiled from: IMASDK */
public final class ii implements ic {
    private final ir a;
    private String b;
    private gc c;
    private ij d;
    private boolean e;
    private final boolean[] f = new boolean[3];
    private final in g = new in(32, 128);
    private final in h = new in(33, 128);
    private final in i = new in(34, 128);
    private final in j = new in(39, 128);
    private final in k = new in(40, 128);
    private long l;
    private long m;
    private final ut n = new ut();

    public ii(ir irVar) {
        this.a = irVar;
    }

    public final void b() {
    }

    public final void a() {
        up.a(this.f);
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.d.a();
        this.l = 0;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.b = jdVar.c();
        this.c = fsVar.a(jdVar.b(), 2);
        this.d = new ij(this.c);
        this.a.a(fsVar, jdVar);
    }

    public final void a(long j2, int i2) {
        this.m = j2;
    }

    public final void a(ut utVar) {
        int i2;
        int i3;
        int i4;
        byte[] bArr;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        float f2;
        int i11;
        int i12;
        while (true) {
            ut utVar2 = utVar;
            if (utVar.b() > 0) {
                int d2 = utVar.d();
                int c2 = utVar.c();
                byte[] bArr2 = utVar2.a;
                this.l += (long) utVar.b();
                this.c.a(utVar2, utVar.b());
                while (d2 < c2) {
                    int a2 = up.a(bArr2, d2, c2, this.f);
                    if (a2 == c2) {
                        a(bArr2, d2, c2);
                        return;
                    }
                    int c3 = up.c(bArr2, a2);
                    int i13 = a2 - d2;
                    if (i13 > 0) {
                        a(bArr2, d2, a2);
                    }
                    int i14 = c2 - a2;
                    long j2 = this.l - ((long) i14);
                    int i15 = i13 < 0 ? -i13 : 0;
                    long j3 = this.m;
                    if (this.e) {
                        this.d.a(j2, i14);
                        i5 = c2;
                        bArr = bArr2;
                        i4 = a2;
                        i2 = i14;
                        i3 = c3;
                    } else {
                        this.g.b(i15);
                        this.h.b(i15);
                        this.i.b(i15);
                        if (!this.g.b() || !this.h.b() || !this.i.b()) {
                            i5 = c2;
                            bArr = bArr2;
                            i4 = a2;
                            i2 = i14;
                            i3 = c3;
                        } else {
                            gc gcVar = this.c;
                            String str = this.b;
                            in inVar = this.g;
                            in inVar2 = this.h;
                            in inVar3 = this.i;
                            i5 = c2;
                            bArr = bArr2;
                            byte[] bArr3 = new byte[(inVar.b + inVar2.b + inVar3.b)];
                            i4 = a2;
                            i3 = c3;
                            System.arraycopy(inVar.a, 0, bArr3, 0, inVar.b);
                            i2 = i14;
                            System.arraycopy(inVar2.a, 0, bArr3, inVar.b, inVar2.b);
                            System.arraycopy(inVar3.a, 0, bArr3, inVar.b + inVar2.b, inVar3.b);
                            uu uuVar = new uu(inVar2.a, 0, inVar2.b);
                            uuVar.a(44);
                            int c4 = uuVar.c(3);
                            uuVar.a();
                            uuVar.a(88);
                            uuVar.a(8);
                            int i16 = 0;
                            for (int i17 = 0; i17 < c4; i17++) {
                                if (uuVar.b()) {
                                    i16 += 89;
                                }
                                if (uuVar.b()) {
                                    i16 += 8;
                                }
                            }
                            uuVar.a(i16);
                            if (c4 > 0) {
                                uuVar.a((8 - c4) * 2);
                            }
                            uuVar.d();
                            int d3 = uuVar.d();
                            if (d3 == 3) {
                                uuVar.a();
                            }
                            int d4 = uuVar.d();
                            int d5 = uuVar.d();
                            if (uuVar.b()) {
                                i8 = d4 - (((d3 == 1 || d3 == 2) ? 2 : 1) * (uuVar.d() + uuVar.d()));
                                i7 = d5 - ((d3 == 1 ? 2 : 1) * (uuVar.d() + uuVar.d()));
                            } else {
                                i8 = d4;
                                i7 = d5;
                            }
                            uuVar.d();
                            uuVar.d();
                            int d6 = uuVar.d();
                            for (int i18 = uuVar.b() ? 0 : c4; i18 <= c4; i18++) {
                                uuVar.d();
                                uuVar.d();
                                uuVar.d();
                            }
                            uuVar.d();
                            uuVar.d();
                            uuVar.d();
                            uuVar.d();
                            uuVar.d();
                            uuVar.d();
                            if (!uuVar.b() || !uuVar.b()) {
                                i9 = 2;
                            } else {
                                int i19 = 0;
                                for (int i20 = 4; i19 < i20; i20 = 4) {
                                    for (int i21 = 0; i21 < 6; i21 += i19 == i12 ? 3 : 1) {
                                        if (!uuVar.b()) {
                                            uuVar.d();
                                            i12 = 3;
                                        } else {
                                            int min = Math.min(64, 1 << ((i19 << 1) + 4));
                                            if (i19 > 1) {
                                                uuVar.e();
                                            }
                                            for (int i22 = 0; i22 < min; i22++) {
                                                uuVar.e();
                                            }
                                            i12 = 3;
                                        }
                                    }
                                    i19++;
                                }
                                i9 = 2;
                            }
                            uuVar.a(i9);
                            if (uuVar.b()) {
                                uuVar.a(8);
                                uuVar.d();
                                uuVar.d();
                                uuVar.a();
                            }
                            int d7 = uuVar.d();
                            int i23 = 0;
                            boolean z = false;
                            int i24 = 0;
                            while (i23 < d7) {
                                if (i23 != 0) {
                                    z = uuVar.b();
                                }
                                if (z) {
                                    uuVar.a();
                                    uuVar.d();
                                    for (int i25 = 0; i25 <= i24; i25++) {
                                        if (uuVar.b()) {
                                            uuVar.a();
                                        }
                                    }
                                    i11 = d7;
                                } else {
                                    int d8 = uuVar.d();
                                    int d9 = uuVar.d();
                                    int i26 = d8 + d9;
                                    i11 = d7;
                                    for (int i27 = 0; i27 < d8; i27++) {
                                        uuVar.d();
                                        uuVar.a();
                                    }
                                    for (int i28 = 0; i28 < d9; i28++) {
                                        uuVar.d();
                                        uuVar.a();
                                    }
                                    i24 = i26;
                                }
                                i23++;
                                d7 = i11;
                            }
                            if (uuVar.b()) {
                                for (int i29 = 0; i29 < uuVar.d(); i29++) {
                                    uuVar.a(d6 + 4 + 1);
                                }
                                i10 = 2;
                            } else {
                                i10 = 2;
                            }
                            uuVar.a(i10);
                            float f3 = 1.0f;
                            if (uuVar.b() && uuVar.b()) {
                                int c5 = uuVar.c(8);
                                if (c5 == 255) {
                                    int c6 = uuVar.c(16);
                                    int c7 = uuVar.c(16);
                                    if (!(c6 == 0 || c7 == 0)) {
                                        f3 = ((float) c6) / ((float) c7);
                                    }
                                    f2 = f3;
                                } else if (c5 < up.b.length) {
                                    f2 = up.b[c5];
                                } else {
                                    StringBuilder sb = new StringBuilder(46);
                                    sb.append("Unexpected aspect_ratio_idc value: ");
                                    sb.append(c5);
                                    Log.w("H265Reader", sb.toString());
                                }
                                gcVar.a(bs.a(str, MimeTypes.VIDEO_H265, (String) null, -1, -1, i8, i7, -1.0f, Collections.singletonList(bArr3), -1, f2, (fa) null));
                                this.e = true;
                            }
                            f2 = 1.0f;
                            gcVar.a(bs.a(str, MimeTypes.VIDEO_H265, (String) null, -1, -1, i8, i7, -1.0f, Collections.singletonList(bArr3), -1, f2, (fa) null));
                            this.e = true;
                        }
                    }
                    if (this.j.b(i15)) {
                        this.n.a(this.j.a, up.a(this.j.a, this.j.b));
                        this.n.d(5);
                        this.a.a(j3, this.n);
                    }
                    if (this.k.b(i15)) {
                        this.n.a(this.k.a, up.a(this.k.a, this.k.b));
                        this.n.d(5);
                        this.a.a(j3, this.n);
                    }
                    long j4 = this.m;
                    if (this.e) {
                        this.d.a(j2, i2, i3, j4);
                        i6 = i3;
                    } else {
                        i6 = i3;
                        this.g.a(i6);
                        this.h.a(i6);
                        this.i.a(i6);
                    }
                    this.j.a(i6);
                    this.k.a(i6);
                    d2 = i4 + 3;
                    c2 = i5;
                    bArr2 = bArr;
                    ut utVar3 = utVar;
                }
            } else {
                return;
            }
        }
    }

    private final void a(byte[] bArr, int i2, int i3) {
        if (this.e) {
            this.d.a(bArr, i2, i3);
        } else {
            this.g.a(bArr, i2, i3);
            this.h.a(bArr, i2, i3);
            this.i.a(bArr, i2, i3);
        }
        this.j.a(bArr, i2, i3);
        this.k.a(bArr, i2, i3);
    }
}
