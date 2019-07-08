package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public abstract class ns extends ng {
    public final long k;

    public ns(sn snVar, sr srVar, bs bsVar, int i, Object obj, long j, long j2, long j3) {
        super(snVar, srVar, 1, bsVar, i, obj, j, j2);
        qi.a(bsVar);
        this.k = j3;
    }

    public abstract boolean h();

    public long g() {
        long j = this.k;
        if (j != -1) {
            return j + 1;
        }
        return -1;
    }
}
