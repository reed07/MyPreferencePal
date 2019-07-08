package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
final class go implements gr {
    private final long[] a;
    private final long[] b;
    private final long c;

    public static go a(long j, ks ksVar) {
        int length = ksVar.d.length;
        int i = length + 1;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        jArr[0] = j;
        long j2 = 0;
        jArr2[0] = 0;
        for (int i2 = 1; i2 <= length; i2++) {
            int i3 = i2 - 1;
            j += (long) (ksVar.a + ksVar.d[i3]);
            j2 += (long) (ksVar.b + ksVar.e[i3]);
            jArr[i2] = j;
            jArr2[i2] = j2;
        }
        return new go(jArr, jArr2);
    }

    public final boolean a() {
        return true;
    }

    public final long c() {
        return -1;
    }

    private go(long[] jArr, long[] jArr2) {
        this.a = jArr;
        this.b = jArr2;
        this.c = at.b(jArr2[jArr2.length - 1]);
    }

    public final fz a(long j) {
        Pair a2 = a(at.a(vf.a(j, 0, this.c)), this.b, this.a);
        return new fz(new gb(at.b(((Long) a2.first).longValue()), ((Long) a2.second).longValue()));
    }

    public final long c(long j) {
        return at.b(((Long) a(j, this.a, this.b).second).longValue());
    }

    public final long b() {
        return this.c;
    }

    private static Pair<Long, Long> a(long j, long[] jArr, long[] jArr2) {
        int a2 = vf.a(jArr, j, true, true);
        long j2 = jArr[a2];
        long j3 = jArr2[a2];
        int i = a2 + 1;
        if (i == jArr.length) {
            return Pair.create(Long.valueOf(j2), Long.valueOf(j3));
        }
        long j4 = jArr[i];
        return Pair.create(Long.valueOf(j), Long.valueOf(((long) ((j4 == j2 ? 0.0d : (((double) j) - ((double) j2)) / ((double) (j4 - j2))) * ((double) (jArr2[i] - j3)))) + j3));
    }
}
