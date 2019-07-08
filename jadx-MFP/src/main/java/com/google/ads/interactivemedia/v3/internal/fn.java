package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public final class fn implements fy {
    public final int a;
    public final int[] b;
    public final long[] c;
    public final long[] d;
    public final long[] e;
    private final long f;

    public fn(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.b = iArr;
        this.c = jArr;
        this.d = jArr2;
        this.e = jArr3;
        this.a = iArr.length;
        int i = this.a;
        if (i > 0) {
            this.f = jArr2[i - 1] + jArr3[i - 1];
        } else {
            this.f = 0;
        }
    }

    public final boolean a() {
        return true;
    }

    public final int b(long j) {
        return vf.a(this.e, j, true, true);
    }

    public final long b() {
        return this.f;
    }

    public final fz a(long j) {
        int b2 = b(j);
        gb gbVar = new gb(this.e[b2], this.c[b2]);
        if (gbVar.b >= j || b2 == this.a - 1) {
            return new fz(gbVar);
        }
        int i = b2 + 1;
        return new fz(gbVar, new gb(this.e[i], this.c[i]));
    }

    public final String toString() {
        int i = this.a;
        String arrays = Arrays.toString(this.b);
        String arrays2 = Arrays.toString(this.c);
        String arrays3 = Arrays.toString(this.e);
        String arrays4 = Arrays.toString(this.d);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 71 + String.valueOf(arrays2).length() + String.valueOf(arrays3).length() + String.valueOf(arrays4).length());
        sb.append("ChunkIndex(length=");
        sb.append(i);
        sb.append(", sizes=");
        sb.append(arrays);
        sb.append(", offsets=");
        sb.append(arrays2);
        sb.append(", timeUs=");
        sb.append(arrays3);
        sb.append(", durationsUs=");
        sb.append(arrays4);
        sb.append(")");
        return sb.toString();
    }
}
