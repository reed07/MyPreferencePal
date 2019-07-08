package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

/* compiled from: IMASDK */
public final class nc {
    public static final nc a = new nc(new long[0]);
    public final int b;
    public final long[] c;
    public final nd[] d;
    public final long e;
    private final long f;

    private nc(long... jArr) {
        int length = jArr.length;
        this.b = length;
        this.c = Arrays.copyOf(jArr, length);
        this.d = new nd[length];
        for (int i = 0; i < length; i++) {
            this.d[i] = new nd();
        }
        this.e = 0;
        this.f = -9223372036854775807L;
    }

    public final int a(long j) {
        int length = this.c.length - 1;
        while (length >= 0) {
            boolean z = false;
            if (j != Long.MIN_VALUE) {
                long j2 = this.c[length];
                if (j2 == Long.MIN_VALUE) {
                    long j3 = this.f;
                    if (j3 == -9223372036854775807L || j < j3) {
                        z = true;
                    }
                } else if (j < j2) {
                    z = true;
                }
            }
            if (!z) {
                break;
            }
            length--;
        }
        if (length < 0 || !this.d[length].a()) {
            return -1;
        }
        return length;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        nc ncVar = (nc) obj;
        return this.b == ncVar.b && this.f == ncVar.f && Arrays.equals(this.c, ncVar.c) && Arrays.equals(this.d, ncVar.d);
    }

    public final int hashCode() {
        return (((((this.b * 31 * 31) + ((int) this.f)) * 31) + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d);
    }
}
