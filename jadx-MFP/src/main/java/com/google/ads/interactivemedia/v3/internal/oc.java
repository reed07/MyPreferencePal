package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class oc extends cq {
    private final long b;
    private final long c;
    private final int d;
    private final long e;
    private final long f;
    private final long g;
    private final tc h;
    private final Object i;

    public oc(long j, long j2, int i2, long j3, long j4, long j5, tc tcVar, Object obj) {
        this.b = j;
        this.c = j2;
        this.d = i2;
        this.e = j3;
        this.f = j4;
        this.g = j5;
        this.h = tcVar;
        this.i = obj;
    }

    public final int b() {
        return 1;
    }

    public final int d() {
        return this.h.a();
    }

    public final cs a(int i2, cs csVar, boolean z) {
        qi.a(i2, 0, this.h.a());
        Integer num = null;
        Object obj = z ? this.h.a(i2).a : null;
        if (z) {
            num = Integer.valueOf(this.d + i2);
        }
        return csVar.a(obj, num, 0, this.h.c(i2), at.b(this.h.a(i2).b - this.h.a(0).b) - this.e);
    }

    public final ct a(int i2, ct ctVar, boolean z, long j) {
        long j2;
        qi.a(i2, 0, 1);
        long j3 = this.g;
        if (!this.h.d) {
            j2 = j3;
        } else {
            if (j > 0) {
                j3 += j;
                if (j3 > this.f) {
                    j2 = -9223372036854775807L;
                }
            }
            long j4 = this.e + j3;
            long c2 = this.h.c(0);
            int i3 = 0;
            while (i3 < this.h.a() - 1 && j4 >= c2) {
                j4 -= c2;
                i3++;
                c2 = this.h.c(i3);
            }
            ov a = this.h.a(i3);
            int size = a.c.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    i4 = -1;
                    break;
                } else if (((rr) a.c.get(i4)).b == 2) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 != -1) {
                ok e2 = ((oy) ((rr) a.c.get(i4)).c.get(0)).e();
                if (!(e2 == null || e2.c(c2) == 0)) {
                    j2 = (j3 + e2.a(e2.a(j4, c2))) - j4;
                }
            }
            j2 = j3;
        }
        return ctVar.a(z ? this.i : null, this.b, this.c, true, this.h.d && this.h.e != -9223372036854775807L && this.h.b == -9223372036854775807L, j2, this.f, 0, this.h.a() - 1, this.e);
    }

    public final int a(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue() - this.d;
        if (intValue < 0 || intValue >= this.h.a()) {
            return -1;
        }
        return intValue;
    }

    public final Object a(int i2) {
        qi.a(i2, 0, this.h.a());
        return Integer.valueOf(this.d + i2);
    }
}
