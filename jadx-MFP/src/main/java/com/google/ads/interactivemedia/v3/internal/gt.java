package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

/* compiled from: IMASDK */
final class gt implements gr {
    private final long a;
    private final int b;
    private final long c;
    private final long d;
    private final long e;
    private final long[] f;

    public static gt a(long j, long j2, fw fwVar, ut utVar) {
        long j3 = j;
        fw fwVar2 = fwVar;
        int i = fwVar2.g;
        int i2 = fwVar2.d;
        int l = utVar.l();
        if ((l & 1) == 1) {
            int p = utVar.p();
            if (p != 0) {
                long c2 = vf.c((long) p, ((long) i) * 1000000, (long) i2);
                if ((l & 6) != 6) {
                    gt gtVar = new gt(j2, fwVar2.c, c2);
                    return gtVar;
                }
                long p2 = (long) utVar.p();
                long[] jArr = new long[100];
                for (int i3 = 0; i3 < 100; i3++) {
                    jArr[i3] = (long) utVar.e();
                }
                if (j3 != -1) {
                    long j4 = j2 + p2;
                    if (j3 != j4) {
                        StringBuilder sb = new StringBuilder(67);
                        sb.append("XING data size mismatch: ");
                        sb.append(j3);
                        sb.append(", ");
                        sb.append(j4);
                        Log.w("XingSeeker", sb.toString());
                    }
                }
                gt gtVar2 = new gt(j2, fwVar2.c, c2, p2, jArr);
                return gtVar2;
            }
        }
        return null;
    }

    private gt(long j, int i, long j2) {
        this(j, i, j2, -1, null);
    }

    private gt(long j, int i, long j2, long j3, long[] jArr) {
        this.a = j;
        this.b = i;
        this.c = j2;
        this.f = jArr;
        this.d = j3;
        long j4 = -1;
        if (j3 != -1) {
            j4 = j + j3;
        }
        this.e = j4;
    }

    public final boolean a() {
        return this.f != null;
    }

    public final fz a(long j) {
        double d2;
        if (!a()) {
            return new fz(new gb(0, this.a + ((long) this.b)));
        }
        long a2 = vf.a(j, 0, this.c);
        double d3 = (((double) a2) * 100.0d) / ((double) this.c);
        double d4 = 0.0d;
        if (d3 > 0.0d) {
            if (d3 >= 100.0d) {
                d4 = 256.0d;
            } else {
                int i = (int) d3;
                long[] jArr = (long[]) qi.a(this.f);
                double d5 = (double) jArr[i];
                if (i == 99) {
                    d2 = 256.0d;
                } else {
                    d2 = (double) jArr[i + 1];
                }
                d4 = d5 + ((d3 - ((double) i)) * (d2 - d5));
            }
        }
        return new fz(new gb(a2, this.a + vf.a(Math.round((d4 / 256.0d) * ((double) this.d)), (long) this.b, this.d - 1)));
    }

    public final long c(long j) {
        long j2;
        long j3 = j - this.a;
        if (!a() || j3 <= ((long) this.b)) {
            return 0;
        }
        long[] jArr = (long[]) qi.a(this.f);
        double d2 = (((double) j3) * 256.0d) / ((double) this.d);
        int a2 = vf.a(jArr, (long) d2, true, true);
        long a3 = a(a2);
        long j4 = jArr[a2];
        int i = a2 + 1;
        long a4 = a(i);
        if (a2 == 99) {
            j2 = 256;
        } else {
            j2 = jArr[i];
        }
        return a3 + Math.round((j4 == j2 ? 0.0d : (d2 - ((double) j4)) / ((double) (j2 - j4))) * ((double) (a4 - a3)));
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return this.e;
    }

    private final long a(int i) {
        return (this.c * ((long) i)) / 100;
    }
}
