package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

/* compiled from: IMASDK */
final class gs implements gr {
    private final long[] a;
    private final long[] b;
    private final long c;
    private final long d;

    public static gs a(long j, long j2, fw fwVar, ut utVar) {
        int i;
        long j3 = j;
        fw fwVar2 = fwVar;
        ut utVar2 = utVar;
        utVar2.d(10);
        int l = utVar.l();
        if (l <= 0) {
            return null;
        }
        int i2 = fwVar2.d;
        long c2 = vf.c((long) l, 1000000 * ((long) (i2 >= 32000 ? 1152 : 576)), (long) i2);
        int f = utVar.f();
        int f2 = utVar.f();
        int f3 = utVar.f();
        utVar2.d(2);
        long j4 = j2 + ((long) fwVar2.c);
        long[] jArr = new long[f];
        long[] jArr2 = new long[f];
        int i3 = 0;
        long j5 = j2;
        while (i3 < f) {
            int i4 = f2;
            jArr[i3] = (((long) i3) * c2) / ((long) f);
            jArr2[i3] = Math.max(j5, j4);
            switch (f3) {
                case 1:
                    i = utVar.e();
                    break;
                case 2:
                    i = utVar.f();
                    break;
                case 3:
                    i = utVar.i();
                    break;
                case 4:
                    i = utVar.p();
                    break;
                default:
                    return null;
            }
            j5 += (long) (i * i4);
            i3++;
            f2 = i4;
        }
        if (!(j3 == -1 || j3 == j5)) {
            StringBuilder sb = new StringBuilder(67);
            sb.append("VBRI data size mismatch: ");
            sb.append(j3);
            sb.append(", ");
            sb.append(j5);
            Log.w("VbriSeeker", sb.toString());
        }
        gs gsVar = new gs(jArr, jArr2, c2, j5);
        return gsVar;
    }

    public final boolean a() {
        return true;
    }

    private gs(long[] jArr, long[] jArr2, long j, long j2) {
        this.a = jArr;
        this.b = jArr2;
        this.c = j;
        this.d = j2;
    }

    public final fz a(long j) {
        int a2 = vf.a(this.a, j, true, true);
        gb gbVar = new gb(this.a[a2], this.b[a2]);
        if (gbVar.b < j) {
            long[] jArr = this.a;
            if (a2 != jArr.length - 1) {
                int i = a2 + 1;
                return new fz(gbVar, new gb(jArr[i], this.b[i]));
            }
        }
        return new fz(gbVar);
    }

    public final long c(long j) {
        return this.a[vf.a(this.b, j, true, true)];
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return this.d;
    }
}
