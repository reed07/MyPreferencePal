package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class nd {
    public final int a;
    public final int[] b;
    public final long[] c;
    private final Uri[] d;

    public nd() {
        this(-1, new int[0], new Uri[0], new long[0]);
    }

    private nd(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
        qi.b(iArr.length == uriArr.length);
        this.a = -1;
        this.b = iArr;
        this.d = uriArr;
        this.c = jArr;
    }

    public final int a(int i) {
        int i2 = i + 1;
        while (true) {
            int[] iArr = this.b;
            if (i2 >= iArr.length || iArr[i2] == 0 || iArr[i2] == 1) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    public final boolean a() {
        return this.a == -1 || a(-1) < this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        nd ndVar = (nd) obj;
        return this.a == ndVar.a && Arrays.equals(this.d, ndVar.d) && Arrays.equals(this.b, ndVar.b) && Arrays.equals(this.c, ndVar.c);
    }

    public final int hashCode() {
        return (((((this.a * 31) + Arrays.hashCode(this.d)) * 31) + Arrays.hashCode(this.b)) * 31) + Arrays.hashCode(this.c);
    }
}
