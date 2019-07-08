package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class pz implements mt {
    private final int a;
    private final qa b;
    private int c = -1;

    public pz(qa qaVar, int i) {
        this.b = qaVar;
        this.a = i;
    }

    public final void a() {
        qi.b(this.c == -1);
        this.c = this.b.a(this.a);
    }

    public final void d() {
        if (this.c != -1) {
            this.b.b(this.a);
            this.c = -1;
        }
    }

    public final boolean b() {
        return this.c == -3 || (e() && this.b.c(this.c));
    }

    public final void c() throws IOException {
        if (this.c != -2) {
            this.b.i();
            return;
        }
        throw new ahd(this.b.f().a(this.a).a(0).h);
    }

    public final int a(bu buVar, ex exVar, boolean z) {
        if (this.c == -3) {
            exVar.b(4);
            return -4;
        } else if (e()) {
            return this.b.a(this.c, buVar, exVar, z);
        } else {
            return -3;
        }
    }

    public final int a_(long j) {
        if (e()) {
            return this.b.a(this.c, j);
        }
        return 0;
    }

    private final boolean e() {
        int i = this.c;
        return (i == -1 || i == -3 || i == -2) ? false : true;
    }
}
