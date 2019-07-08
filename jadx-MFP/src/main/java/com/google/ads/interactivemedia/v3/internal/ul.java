package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public final class ul {
    private int a;
    private long[] b;

    public ul() {
        this(32);
    }

    private ul(int i) {
        this.b = new long[32];
    }

    public final void a(long j) {
        int i = this.a;
        long[] jArr = this.b;
        if (i == jArr.length) {
            this.b = Arrays.copyOf(jArr, i << 1);
        }
        long[] jArr2 = this.b;
        int i2 = this.a;
        this.a = i2 + 1;
        jArr2[i2] = j;
    }

    public final long a(int i) {
        if (i >= 0 && i < this.a) {
            return this.b[i];
        }
        int i2 = this.a;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Invalid index ");
        sb.append(i);
        sb.append(", size is ");
        sb.append(i2);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final int a() {
        return this.a;
    }
}
