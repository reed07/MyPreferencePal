package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public final class pd extends pc {
    final List<ox> g;

    public pd(ox oxVar, long j, long j2, long j3, long j4, List<pf> list, List<ox> list2) {
        super(oxVar, j, j2, j3, j4, list);
        this.g = list2;
    }

    public final boolean a() {
        return true;
    }

    public final ox a(oy oyVar, long j) {
        return (ox) this.g.get((int) (j - this.d));
    }

    public final int b(long j) {
        return this.g.size();
    }
}
