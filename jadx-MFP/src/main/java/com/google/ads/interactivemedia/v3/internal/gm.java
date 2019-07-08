package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class gm {
    private static final long[] a = {128, 64, 32, 16, 8, 4, 2, 1};
    private final byte[] b = new byte[8];
    private int c;
    private int d;

    public final void a() {
        this.c = 0;
        this.d = 0;
    }

    public final long a(fr frVar, boolean z, boolean z2, int i) throws IOException, InterruptedException {
        if (this.c == 0) {
            if (!frVar.a(this.b, 0, 1, z)) {
                return -1;
            }
            this.d = a(this.b[0] & 255);
            if (this.d != -1) {
                this.c = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i2 = this.d;
        if (i2 > i) {
            this.c = 0;
            return -2;
        }
        if (i2 != 1) {
            frVar.b(this.b, 1, i2 - 1);
        }
        this.c = 0;
        return a(this.b, this.d, z2);
    }

    public final int b() {
        return this.d;
    }

    public static int a(int i) {
        int i2 = 0;
        while (true) {
            long[] jArr = a;
            if (i2 >= jArr.length) {
                return -1;
            }
            if ((jArr[i2] & ((long) i)) != 0) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public static long a(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= ~a[i - 1];
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }
}
