package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class gk {
    private final byte[] a = new byte[10];
    private boolean b;
    private int c;
    private int d;
    private long e;
    private int f;

    public final void a() {
        this.b = false;
    }

    public final void a(fr frVar, int i, int i2) throws IOException, InterruptedException {
        if (!this.b) {
            frVar.c(this.a, 0, 10);
            frVar.a();
            if (da.b(this.a) != 0) {
                this.b = true;
                this.c = 0;
            } else {
                return;
            }
        }
        if (this.c == 0) {
            this.f = i;
            this.d = 0;
        }
        this.d += i2;
    }

    public final void a(gj gjVar, long j) {
        if (this.b) {
            int i = this.c;
            this.c = i + 1;
            if (i == 0) {
                this.e = j;
            }
            if (this.c >= 16) {
                gjVar.T.a(this.e, this.f, this.d, 0, gjVar.h);
                this.c = 0;
            }
        }
    }

    public final void a(gj gjVar) {
        if (this.b && this.c > 0) {
            gjVar.T.a(this.e, this.f, this.d, 0, gjVar.h);
            this.c = 0;
        }
    }
}
