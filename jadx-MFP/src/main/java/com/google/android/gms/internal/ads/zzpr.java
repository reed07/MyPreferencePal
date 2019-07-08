package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzpr {
    private int size;
    private long[] zzbhl;

    public zzpr() {
        this(32);
    }

    private zzpr(int i) {
        this.zzbhl = new long[32];
    }

    public final void add(long j) {
        int i = this.size;
        long[] jArr = this.zzbhl;
        if (i == jArr.length) {
            this.zzbhl = Arrays.copyOf(jArr, i << 1);
        }
        long[] jArr2 = this.zzbhl;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr2[i2] = j;
    }

    public final long get(int i) {
        if (i >= 0 && i < this.size) {
            return this.zzbhl[i];
        }
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Invalid index ");
        sb.append(i);
        sb.append(", size is ");
        sb.append(i2);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final int size() {
        return this.size;
    }
}
