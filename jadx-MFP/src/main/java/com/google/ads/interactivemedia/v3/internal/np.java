package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class np extends ne {
    private static final fx l = new fx();
    private final int m;
    private final long n;
    private final nh o;
    private long p;
    private volatile boolean q;
    private boolean r;

    public np(sn snVar, sr srVar, bs bsVar, int i, Object obj, long j, long j2, long j3, long j4, long j5, int i2, long j6, nh nhVar) {
        super(snVar, srVar, bsVar, i, obj, j, j2, j3, j4, j5);
        this.m = i2;
        this.n = j6;
        this.o = nhVar;
    }

    public final long g() {
        return this.k + ((long) this.m);
    }

    public final boolean h() {
        return this.r;
    }

    public final void a() {
        this.q = true;
    }

    public final void b() throws IOException, InterruptedException {
        fr frVar;
        long j;
        sr a = this.c.a(this.p);
        try {
            frVar = new fr(this.j, a.d, this.j.a(a));
            if (this.p == 0) {
                nj c = c();
                c.a(this.n);
                nh nhVar = this.o;
                if (this.a == -9223372036854775807L) {
                    j = -9223372036854775807L;
                } else {
                    j = this.a - this.n;
                }
                nhVar.a(c, j, this.b == -9223372036854775807L ? -9223372036854775807L : this.b - this.n);
            }
            fq fqVar = this.o.a;
            boolean z = false;
            int i = 0;
            while (i == 0 && !this.q) {
                i = fqVar.a(frVar, l);
            }
            if (i != 1) {
                z = true;
            }
            qi.c(z);
            this.p = frVar.c() - this.c.d;
            vf.a((sn) this.j);
            this.r = true;
        } catch (Throwable th) {
            vf.a((sn) this.j);
            throw th;
        }
    }
}
