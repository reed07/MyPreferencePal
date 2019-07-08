package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class hw implements fq {
    private static final int a = vf.h("ID3");
    private final long b;
    private final hx c;
    private final ut d;
    private boolean e;

    public hw() {
        this(0);
    }

    public final void c() {
    }

    private hw(long j) {
        this.b = 0;
        this.c = new hx();
        this.d = new ut(2786);
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        ut utVar = new ut(10);
        int i = 0;
        while (true) {
            frVar.c(utVar.a, 0, 10);
            utVar.c(0);
            if (utVar.i() != a) {
                break;
            }
            utVar.d(3);
            int o = utVar.o();
            i += o + 10;
            frVar.c(o);
        }
        frVar.a();
        frVar.c(i);
        int i2 = i;
        int i3 = 0;
        while (true) {
            frVar.c(utVar.a, 0, 6);
            utVar.c(0);
            if (utVar.f() != 2935) {
                frVar.a();
                i2++;
                if (i2 - i >= 8192) {
                    return false;
                }
                frVar.c(i2);
                i3 = 0;
            } else {
                i3++;
                if (i3 >= 4) {
                    return true;
                }
                int a2 = da.a(utVar.a);
                if (a2 == -1) {
                    return false;
                }
                frVar.c(a2 - 6);
            }
        }
    }

    public final void a(fs fsVar) {
        this.c.a(fsVar, new jd(0, 1));
        fsVar.a();
        fsVar.a(new ga(-9223372036854775807L));
    }

    public final void a(long j, long j2) {
        this.e = false;
        this.c.a();
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        int a2 = frVar.a(this.d.a, 0, 2786);
        if (a2 == -1) {
            return -1;
        }
        this.d.c(0);
        this.d.b(a2);
        if (!this.e) {
            this.c.a(this.b, 4);
            this.e = true;
        }
        this.c.a(this.d);
        return 0;
    }
}
