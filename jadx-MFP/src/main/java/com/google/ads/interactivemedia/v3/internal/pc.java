package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public abstract class pc extends pb {
    final long d;
    final long e;
    final List<pf> f;

    public pc(ox oxVar, long j, long j2, long j3, long j4, List<pf> list) {
        super(oxVar, j, j2);
        this.d = j3;
        this.e = j4;
        this.f = list;
    }

    public abstract ox a(oy oyVar, long j);

    public abstract int b(long j);

    public final long a(long j) {
        long j2;
        List<pf> list = this.f;
        if (list != null) {
            j2 = ((pf) list.get((int) (j - this.d))).a - this.c;
        } else {
            j2 = (j - this.d) * this.e;
        }
        return vf.c(j2, 1000000, this.b);
    }

    public boolean a() {
        return this.f != null;
    }
}
