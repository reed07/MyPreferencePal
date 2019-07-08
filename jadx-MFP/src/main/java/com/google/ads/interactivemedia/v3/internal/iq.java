package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class iq implements iz {
    private final ip a;
    private final ut b = new ut(32);
    private int c;
    private int d;
    private boolean e;
    private boolean f;

    public iq(ip ipVar) {
        this.a = ipVar;
    }

    public final void a(ve veVar, fs fsVar, jd jdVar) {
        this.a.a(veVar, fsVar, jdVar);
        this.f = true;
    }

    public final void a() {
        this.f = true;
    }

    public final void a(ut utVar, int i) {
        boolean z = (i & 1) != 0;
        int e2 = z ? utVar.e() + utVar.d() : 0;
        if (this.f) {
            if (z) {
                this.f = false;
                utVar.c(e2);
                this.d = 0;
            } else {
                return;
            }
        }
        while (utVar.b() > 0) {
            int i2 = this.d;
            if (i2 < 3) {
                if (i2 == 0) {
                    int e3 = utVar.e();
                    utVar.c(utVar.d() - 1);
                    if (e3 == 255) {
                        this.f = true;
                        return;
                    }
                }
                int min = Math.min(utVar.b(), 3 - this.d);
                utVar.a(this.b.a, this.d, min);
                this.d += min;
                if (this.d == 3) {
                    this.b.a(3);
                    this.b.d(1);
                    int e4 = this.b.e();
                    int e5 = this.b.e();
                    this.e = (e4 & 128) != 0;
                    this.c = (((e4 & 15) << 8) | e5) + 3;
                    if (this.b.a.length < this.c) {
                        byte[] bArr = this.b.a;
                        this.b.a(Math.min(4098, Math.max(this.c, bArr.length << 1)));
                        System.arraycopy(bArr, 0, this.b.a, 0, 3);
                    }
                }
            } else {
                int min2 = Math.min(utVar.b(), this.c - this.d);
                utVar.a(this.b.a, this.d, min2);
                this.d += min2;
                int i3 = this.d;
                int i4 = this.c;
                if (i3 != i4) {
                    continue;
                } else {
                    if (!this.e) {
                        this.b.a(i4);
                    } else if (vf.a(this.b.a, 0, this.c, -1) != 0) {
                        this.f = true;
                        return;
                    } else {
                        this.b.a(this.c - 4);
                    }
                    this.a.a(this.b);
                    this.d = 0;
                }
            }
        }
    }
}
