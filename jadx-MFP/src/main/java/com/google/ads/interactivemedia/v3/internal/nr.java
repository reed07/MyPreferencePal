package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class nr extends ng {
    private static final fx a = new fx();
    private final nh b;
    private long k;
    private volatile boolean l;

    public nr(sn snVar, sr srVar, bs bsVar, int i, Object obj, nh nhVar) {
        super(snVar, srVar, 2, bsVar, i, obj, -9223372036854775807L, -9223372036854775807L);
        this.b = nhVar;
    }

    public final void a() {
        this.l = true;
    }

    public final void b() throws IOException, InterruptedException {
        fr frVar;
        sr a2 = this.c.a(this.k);
        try {
            frVar = new fr(this.j, a2.d, this.j.a(a2));
            if (this.k == 0) {
                this.b.a(null, -9223372036854775807L, -9223372036854775807L);
            }
            fq fqVar = this.b.a;
            int i = 0;
            while (i == 0 && !this.l) {
                i = fqVar.a(frVar, a);
            }
            boolean z = true;
            if (i == 1) {
                z = false;
            }
            qi.c(z);
            this.k = frVar.c() - this.c.d;
            vf.a((sn) this.j);
        } catch (Throwable th) {
            vf.a((sn) this.j);
            throw th;
        }
    }
}
