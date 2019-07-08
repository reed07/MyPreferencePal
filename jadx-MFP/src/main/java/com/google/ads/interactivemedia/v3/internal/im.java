package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class im implements ic {
    private final ut a;
    private final fw b;
    private final String c;
    private String d;
    private gc e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private long j;
    private int k;
    private long l;

    public im() {
        this(null);
    }

    public final void b() {
    }

    public im(String str) {
        this.f = 0;
        this.a = new ut(4);
        this.a.a[0] = -1;
        this.b = new fw();
        this.c = str;
    }

    public final void a() {
        this.f = 0;
        this.g = 0;
        this.i = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.d = jdVar.c();
        this.e = fsVar.a(jdVar.b(), 1);
    }

    public final void a(long j2, int i2) {
        this.l = j2;
    }

    public final void a(ut utVar) {
        ut utVar2 = utVar;
        while (utVar.b() > 0) {
            switch (this.f) {
                case 0:
                    byte[] bArr = utVar2.a;
                    int d2 = utVar.d();
                    int c2 = utVar.c();
                    while (true) {
                        if (d2 >= c2) {
                            utVar2.c(c2);
                            break;
                        } else {
                            boolean z = (bArr[d2] & 255) == 255;
                            boolean z2 = this.i && (bArr[d2] & 224) == 224;
                            this.i = z;
                            if (z2) {
                                utVar2.c(d2 + 1);
                                this.i = false;
                                this.a.a[1] = bArr[d2];
                                this.g = 2;
                                this.f = 1;
                                break;
                            } else {
                                d2++;
                            }
                        }
                    }
                    break;
                case 1:
                    int min = Math.min(utVar.b(), 4 - this.g);
                    utVar2.a(this.a.a, this.g, min);
                    this.g += min;
                    if (this.g < 4) {
                        break;
                    } else {
                        this.a.c(0);
                        if (fw.a(this.a.l(), this.b)) {
                            this.k = this.b.c;
                            if (!this.h) {
                                this.j = (((long) this.b.g) * 1000000) / ((long) this.b.d);
                                this.e.a(bs.a(this.d, this.b.b, null, -1, 4096, this.b.e, this.b.d, null, null, 0, this.c));
                                this.h = true;
                            }
                            this.a.c(0);
                            this.e.a(this.a, 4);
                            this.f = 2;
                            break;
                        } else {
                            this.g = 0;
                            this.f = 1;
                            break;
                        }
                    }
                case 2:
                    int min2 = Math.min(utVar.b(), this.k - this.g);
                    this.e.a(utVar2, min2);
                    this.g += min2;
                    int i2 = this.g;
                    int i3 = this.k;
                    if (i2 < i3) {
                        break;
                    } else {
                        this.e.a(this.l, 1, i3, 0, null);
                        this.l += this.j;
                        this.g = 0;
                        this.f = 0;
                        break;
                    }
                default:
                    throw new IllegalStateException();
            }
        }
    }
}
