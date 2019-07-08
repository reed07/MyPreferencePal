package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: IMASDK */
public final class id implements ic {
    private static final double[] c = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String a;
    private gc b;
    private boolean d;
    private long e;
    private final je f;
    private final ut g;
    private final boolean[] h;
    private final ie i;
    private final in j;
    private long k;
    private boolean l;
    private long m;
    private long n;
    private long o;
    private boolean p;
    private boolean q;

    public id() {
        this(null);
    }

    public final void b() {
    }

    public id(je jeVar) {
        this.f = jeVar;
        this.h = new boolean[4];
        this.i = new ie(128);
        if (jeVar != null) {
            this.j = new in(RequestCodes.PROGRESS_PHOTOS_GALLERY, 128);
            this.g = new ut();
            return;
        }
        this.j = null;
        this.g = null;
    }

    public final void a() {
        up.a(this.h);
        this.i.a();
        if (this.f != null) {
            this.j.a();
        }
        this.k = 0;
        this.l = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.a = jdVar.c();
        this.b = fsVar.a(jdVar.b(), 2);
        je jeVar = this.f;
        if (jeVar != null) {
            jeVar.a(fsVar, jdVar);
        }
    }

    public final void a(long j2, int i2) {
        this.m = j2;
    }

    public final void a(ut utVar) {
        boolean z;
        int i2;
        float f2;
        long j2;
        ut utVar2 = utVar;
        int d2 = utVar.d();
        int c2 = utVar.c();
        byte[] bArr = utVar2.a;
        this.k += (long) utVar.b();
        this.b.a(utVar2, utVar.b());
        while (true) {
            int a2 = up.a(bArr, d2, c2, this.h);
            if (a2 == c2) {
                if (!this.d) {
                    this.i.a(bArr, d2, c2);
                }
                if (this.f != null) {
                    this.j.a(bArr, d2, c2);
                    return;
                }
                return;
            }
            int i3 = a2 + 3;
            byte b2 = utVar2.a[i3] & 255;
            int i4 = a2 - d2;
            if (!this.d) {
                if (i4 > 0) {
                    this.i.a(bArr, d2, a2);
                }
                if (this.i.a(b2, i4 < 0 ? -i4 : 0)) {
                    ie ieVar = this.i;
                    String str = this.a;
                    byte[] copyOf = Arrays.copyOf(ieVar.c, ieVar.a);
                    byte b3 = copyOf[5] & 255;
                    int i5 = ((copyOf[4] & 255) << 4) | (b3 >> 4);
                    byte b4 = ((b3 & Ascii.SI) << 8) | (copyOf[6] & 255);
                    switch ((copyOf[7] & 240) >> 4) {
                        case 2:
                            f2 = ((float) (b4 * 4)) / ((float) (i5 * 3));
                            break;
                        case 3:
                            f2 = ((float) (b4 * Ascii.DLE)) / ((float) (i5 * 9));
                            break;
                        case 4:
                            f2 = ((float) (b4 * 121)) / ((float) (i5 * 100));
                            break;
                        default:
                            f2 = 1.0f;
                            break;
                    }
                    bs a3 = bs.a(str, MimeTypes.VIDEO_MPEG2, (String) null, -1, -1, i5, (int) b4, -1.0f, Collections.singletonList(copyOf), -1, f2, (fa) null);
                    int i6 = (copyOf[7] & Ascii.SI) - 1;
                    if (i6 >= 0) {
                        double[] dArr = c;
                        if (i6 < dArr.length) {
                            double d3 = dArr[i6];
                            int i7 = ieVar.b + 9;
                            int i8 = (copyOf[i7] & 96) >> 5;
                            byte b5 = copyOf[i7] & Ascii.US;
                            if (i8 != b5) {
                                d3 *= (((double) i8) + 1.0d) / ((double) (b5 + 1));
                            }
                            j2 = (long) (1000000.0d / d3);
                            Pair create = Pair.create(a3, Long.valueOf(j2));
                            this.b.a((bs) create.first);
                            this.e = ((Long) create.second).longValue();
                            this.d = true;
                        }
                    }
                    j2 = 0;
                    Pair create2 = Pair.create(a3, Long.valueOf(j2));
                    this.b.a((bs) create2.first);
                    this.e = ((Long) create2.second).longValue();
                    this.d = true;
                }
            }
            if (this.f != null) {
                if (i4 > 0) {
                    this.j.a(bArr, d2, a2);
                    i2 = 0;
                } else {
                    i2 = -i4;
                }
                if (this.j.b(i2)) {
                    this.g.a(this.j.a, up.a(this.j.a, this.j.b));
                    this.f.a(this.o, this.g);
                }
                if (b2 == 178 && utVar2.a[a2 + 2] == 1) {
                    this.j.a(b2);
                }
            }
            if (b2 == 0 || b2 == 179) {
                int i9 = c2 - a2;
                if (this.l && this.q && this.d) {
                    this.b.a(this.o, this.p ? 1 : 0, ((int) (this.k - this.n)) - i9, i9, null);
                }
                if (!this.l || this.q) {
                    this.n = this.k - ((long) i9);
                    long j3 = this.m;
                    if (j3 == -9223372036854775807L) {
                        j3 = this.l ? this.o + this.e : 0;
                    }
                    this.o = j3;
                    z = false;
                    this.p = false;
                    this.m = -9223372036854775807L;
                    this.l = true;
                } else {
                    z = false;
                }
                if (b2 == 0) {
                    z = true;
                }
                this.q = z;
            } else if (b2 == 184) {
                this.p = true;
            }
            d2 = i3;
        }
    }
}
