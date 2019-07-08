package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class rp {
    private final int a;
    private final int[] b;
    private final mz[] c;

    rp(int[] iArr, mz[] mzVarArr, int[] iArr2, int[][][] iArr3, mz mzVar) {
        this.b = iArr;
        this.c = mzVarArr;
        this.a = iArr.length;
    }

    public final int a() {
        return this.a;
    }

    public final int a(int i) {
        return this.b[i];
    }

    public final mz b(int i) {
        return this.c[i];
    }
}
