package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class hr {
    public final int a;
    public final int b;
    public final long c;
    public final long d;
    public final long e;
    public final bs f;
    public final int g;
    public final long[] h;
    public final long[] i;
    public final int j;
    private final hs[] k;

    public hr(int i2, int i3, long j2, long j3, long j4, bs bsVar, int i4, hs[] hsVarArr, int i5, long[] jArr, long[] jArr2) {
        this.a = i2;
        this.b = i3;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = bsVar;
        this.g = i4;
        this.k = hsVarArr;
        this.j = i5;
        this.h = jArr;
        this.i = jArr2;
    }

    public final hs a(int i2) {
        hs[] hsVarArr = this.k;
        if (hsVarArr == null) {
            return null;
        }
        return hsVarArr[i2];
    }
}
