package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class hu {
    public final hr a;
    public final int b;
    public final long[] c;
    public final int[] d;
    public final int e;
    public final long[] f;
    public final int[] g;
    public final long h;

    public hu(hr hrVar, long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2, long j) {
        boolean z = false;
        qi.b(iArr.length == jArr2.length);
        qi.b(jArr.length == jArr2.length);
        if (iArr2.length == jArr2.length) {
            z = true;
        }
        qi.b(z);
        this.a = hrVar;
        this.c = jArr;
        this.d = iArr;
        this.e = i;
        this.f = jArr2;
        this.g = iArr2;
        this.h = j;
        this.b = jArr.length;
        if (iArr2.length > 0) {
            int length = iArr2.length - 1;
            iArr2[length] = iArr2[length] | 536870912;
        }
    }

    public final int a(long j) {
        for (int a2 = vf.a(this.f, j, true, false); a2 >= 0; a2--) {
            if ((this.g[a2] & 1) != 0) {
                return a2;
            }
        }
        return -1;
    }

    public final int b(long j) {
        for (int b2 = vf.b(this.f, j, true, false); b2 < this.f.length; b2++) {
            if ((this.g[b2] & 1) != 0) {
                return b2;
            }
        }
        return -1;
    }
}
