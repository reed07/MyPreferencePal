package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class gl {
    private final ut a = new ut(8);
    private int b;

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        fr frVar2 = frVar;
        long d = frVar.d();
        long j = 1024;
        int i = (d > -1 ? 1 : (d == -1 ? 0 : -1));
        if (i != 0 && d <= 1024) {
            j = d;
        }
        int i2 = (int) j;
        frVar2.c(this.a.a, 0, 4);
        this.b = 4;
        for (long j2 = this.a.j(); j2 != 440786851; j2 = ((j2 << 8) & -256) | ((long) (this.a.a[0] & 255))) {
            int i3 = this.b + 1;
            this.b = i3;
            if (i3 == i2) {
                return false;
            }
            frVar2.c(this.a.a, 0, 1);
        }
        long b2 = b(frVar);
        long j3 = (long) this.b;
        if (b2 == Long.MIN_VALUE || (i != 0 && j3 + b2 >= d)) {
            return false;
        }
        while (true) {
            int i4 = this.b;
            long j4 = j3 + b2;
            if (((long) i4) < j4) {
                if (b(frVar) == Long.MIN_VALUE) {
                    return false;
                }
                long b3 = b(frVar);
                int i5 = (b3 > 0 ? 1 : (b3 == 0 ? 0 : -1));
                if (i5 < 0 || b3 > 2147483647L) {
                    return false;
                }
                if (i5 != 0) {
                    int i6 = (int) b3;
                    frVar2.c(i6);
                    this.b += i6;
                }
            } else if (((long) i4) == j4) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private final long b(fr frVar) throws IOException, InterruptedException {
        int i = 0;
        frVar.c(this.a.a, 0, 1);
        byte b2 = this.a.a[0] & 255;
        if (b2 == 0) {
            return Long.MIN_VALUE;
        }
        int i2 = 128;
        int i3 = 0;
        while ((b2 & i2) == 0) {
            i2 >>= 1;
            i3++;
        }
        int i4 = b2 & (~i2);
        frVar.c(this.a.a, 1, i3);
        while (i < i3) {
            i++;
            i4 = (this.a.a[i] & 255) + (i4 << 8);
        }
        this.b += i3 + 1;
        return (long) i4;
    }
}
