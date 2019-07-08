package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public abstract class ne extends ns {
    public final long a;
    public final long b;
    private nj l;
    private int[] m;

    public ne(sn snVar, sr srVar, bs bsVar, int i, Object obj, long j, long j2, long j3, long j4, long j5) {
        super(snVar, srVar, bsVar, i, obj, j, j2, j5);
        this.a = j3;
        this.b = j4;
    }

    public final void a(nj njVar) {
        this.l = njVar;
        this.m = njVar.a();
    }

    public final int a(int i) {
        return this.m[i];
    }

    /* access modifiers changed from: protected */
    public final nj c() {
        return this.l;
    }
}
