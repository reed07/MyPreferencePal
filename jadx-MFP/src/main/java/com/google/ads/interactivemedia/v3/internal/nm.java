package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class nm implements mt {
    public final nl<T> a;
    private final mq b;
    private final int c;
    private boolean d;
    private final /* synthetic */ nl e;

    public nm(nl nlVar, nl<T> nlVar2, mq mqVar, int i) {
        this.e = nlVar;
        this.a = nlVar2;
        this.b = mqVar;
        this.c = i;
    }

    public final void c() throws IOException {
    }

    public final boolean b() {
        return this.e.c || (!this.e.f() && this.b.d());
    }

    public final int a_(long j) {
        int i = 0;
        if (this.e.f()) {
            return 0;
        }
        d();
        if (!this.e.c || j <= this.b.i()) {
            int b2 = this.b.b(j, true, true);
            if (b2 != -1) {
                i = b2;
            }
        } else {
            i = this.b.o();
        }
        return i;
    }

    public final int a(bu buVar, ex exVar, boolean z) {
        if (this.e.f()) {
            return -3;
        }
        d();
        return this.b.a(buVar, exVar, z, this.e.c, this.e.b);
    }

    public final void a() {
        qi.c(this.e.f[this.c]);
        this.e.f[this.c] = false;
    }

    private final void d() {
        if (!this.d) {
            this.e.i.a(this.e.d[this.c], this.e.e[this.c], 0, null, this.e.u);
            this.d = true;
        }
    }
}
