package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class hy implements fq {
    private static final int a = vf.h("ID3");
    private final int b;
    private final hz c;
    private final ut d;
    private final ut e;
    private final us f;
    private final long g;
    private fs h;
    private long i;
    private long j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;

    public hy() {
        this(0);
    }

    public final void c() {
    }

    private hy(long j2) {
        this(0, 0);
    }

    private hy(long j2, int i2) {
        this.g = j2;
        this.i = j2;
        this.b = 0;
        this.c = new hz(true);
        this.d = new ut(2048);
        this.k = -1;
        this.j = -1;
        this.e = new ut(10);
        this.f = new us(this.e.a);
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        int b2 = b(frVar);
        int i2 = b2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            frVar.c(this.e.a, 0, 2);
            this.e.c(0);
            if (!hz.a(this.e.f())) {
                frVar.a();
                i2++;
                if (i2 - b2 >= 8192) {
                    return false;
                }
                frVar.c(i2);
                i3 = 0;
                i4 = 0;
            } else {
                i3++;
                if (i3 >= 4 && i4 > 188) {
                    return true;
                }
                frVar.c(this.e.a, 0, 4);
                this.f.a(14);
                int c2 = this.f.c(13);
                if (c2 <= 6) {
                    return false;
                }
                frVar.c(c2 - 6);
                i4 += c2;
            }
        }
    }

    public final void a(fs fsVar) {
        this.h = fsVar;
        this.c.a(fsVar, new jd(0, 1));
        fsVar.a();
    }

    public final void a(long j2, long j3) {
        this.m = false;
        this.c.a();
        this.i = this.g + j3;
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        fr frVar2 = frVar;
        long d2 = frVar.d();
        boolean z = ((this.b & 1) == 0 || d2 == -1) ? false : true;
        if (z && !this.l) {
            this.k = -1;
            frVar.a();
            long j2 = 0;
            if (frVar.c() == 0) {
                b(frVar);
            }
            int i2 = 0;
            while (true) {
                if (!frVar2.b(this.e.a, 0, 2, true)) {
                    break;
                }
                this.e.c(0);
                if (hz.a(this.e.f())) {
                    if (!frVar2.b(this.e.a, 0, 4, true)) {
                        break;
                    }
                    this.f.a(14);
                    int c2 = this.f.c(13);
                    if (c2 > 6) {
                        j2 += (long) c2;
                        i2++;
                        if (i2 != 1000) {
                            if (!frVar2.a(c2 - 6, true)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        this.l = true;
                        throw new ca("Malformed ADTS stream");
                    }
                } else {
                    i2 = 0;
                    break;
                }
            }
            frVar.a();
            if (i2 > 0) {
                this.k = (int) (j2 / ((long) i2));
            } else {
                this.k = -1;
            }
            this.l = true;
        }
        int a2 = frVar2.a(this.d.a, 0, 2048);
        boolean z2 = a2 == -1;
        if (!this.n) {
            boolean z3 = z && this.k > 0;
            if (!z3 || this.c.c() != -9223372036854775807L || z2) {
                fs fsVar = (fs) qi.a(this.h);
                if (!z3 || this.c.c() == -9223372036854775807L) {
                    fsVar.a(new ga(-9223372036854775807L));
                } else {
                    fo foVar = new fo(d2, this.j, (int) ((((long) (this.k << 3)) * 1000000) / this.c.c()), this.k);
                    fsVar.a(foVar);
                }
                this.n = true;
            }
        }
        if (z2) {
            return -1;
        }
        this.d.c(0);
        this.d.b(a2);
        if (!this.m) {
            this.c.a(this.i, 4);
            this.m = true;
        }
        this.c.a(this.d);
        return 0;
    }

    private final int b(fr frVar) throws IOException, InterruptedException {
        int i2 = 0;
        while (true) {
            frVar.c(this.e.a, 0, 10);
            this.e.c(0);
            if (this.e.i() != a) {
                break;
            }
            this.e.d(3);
            int o = this.e.o();
            i2 += o + 10;
            frVar.c(o);
        }
        frVar.a();
        frVar.c(i2);
        if (this.j == -1) {
            this.j = (long) i2;
        }
        return i2;
    }
}
