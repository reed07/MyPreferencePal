package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class lf implements mt {
    public final mt a;
    private boolean b;
    private final /* synthetic */ le c;

    public lf(le leVar, mt mtVar) {
        this.c = leVar;
        this.a = mtVar;
    }

    public final void a() {
        this.b = false;
    }

    public final boolean b() {
        return !this.c.f() && this.a.b();
    }

    public final void c() throws IOException {
        this.a.c();
    }

    public final int a(bu buVar, ex exVar, boolean z) {
        if (this.c.f()) {
            return -3;
        }
        if (this.b) {
            exVar.a(4);
            return -4;
        }
        int a2 = this.a.a(buVar, exVar, z);
        if (a2 == -5) {
            bs bsVar = buVar.a;
            if (!(bsVar.v == 0 && bsVar.w == 0)) {
                int i = 0;
                int i2 = this.c.b != 0 ? 0 : bsVar.v;
                if (this.c.c == Long.MIN_VALUE) {
                    i = bsVar.w;
                }
                buVar.a = bsVar.a(i2, i);
            }
            return -5;
        } else if (this.c.c == Long.MIN_VALUE || ((a2 != -4 || exVar.c < this.c.c) && (a2 != -3 || this.c.d() != Long.MIN_VALUE))) {
            return a2;
        } else {
            exVar.a();
            exVar.a(4);
            this.b = true;
            return -4;
        }
    }

    public final int a_(long j) {
        if (this.c.f()) {
            return -3;
        }
        return this.a.a_(j);
    }
}
