package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class nv extends ne {
    private final int l;
    private final bs m;
    private long n;
    private boolean o;

    public nv(sn snVar, sr srVar, bs bsVar, int i, Object obj, long j, long j2, long j3, int i2, bs bsVar2) {
        super(snVar, srVar, bsVar, i, obj, j, j2, -9223372036854775807L, -9223372036854775807L, j3);
        this.l = i2;
        this.m = bsVar2;
    }

    public final void a() {
    }

    public final boolean h() {
        return this.o;
    }

    /* JADX INFO: finally extract failed */
    public final void b() throws IOException, InterruptedException {
        try {
            long a = this.j.a(this.c.a(this.n));
            fr frVar = new fr(this.j, this.n, a != -1 ? a + this.n : a);
            nj c = c();
            c.a(0);
            gc a2 = c.a(this.l);
            a2.a(this.m);
            for (int i = 0; i != -1; i = a2.a(frVar, Integer.MAX_VALUE, true)) {
                this.n += (long) i;
            }
            a2.a(this.h, 1, (int) this.n, 0, null);
            vf.a((sn) this.j);
            this.o = true;
        } catch (Throwable th) {
            vf.a((sn) this.j);
            throw th;
        }
    }
}
