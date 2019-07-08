package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class oh {
    public final boolean a;
    public final long b;
    public final long c;

    public static oh a(ov ovVar, long j) {
        boolean z;
        int i;
        boolean z2;
        ov ovVar2 = ovVar;
        long j2 = j;
        int size = ovVar2.c.size();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                z = false;
                break;
            }
            int i4 = ((rr) ovVar2.c.get(i3)).b;
            if (i4 == 1 || i4 == 2) {
                z = true;
            } else {
                i3++;
            }
        }
        long j3 = Long.MAX_VALUE;
        int i5 = 0;
        boolean z3 = false;
        boolean z4 = false;
        long j4 = 0;
        while (i5 < size) {
            rr rrVar = (rr) ovVar2.c.get(i5);
            if (!z || rrVar.b != 3) {
                ok e = ((oy) rrVar.c.get(i2)).e();
                if (e == null) {
                    oh ohVar = new oh(true, 0, j);
                    return ohVar;
                }
                boolean b2 = e.b() | z4;
                int c2 = e.c(j2);
                if (c2 == 0) {
                    i = size;
                    z2 = z;
                    z4 = b2;
                    z3 = true;
                    j4 = 0;
                    j3 = 0;
                } else if (!z3) {
                    z2 = z;
                    long b_ = e.b_();
                    i = size;
                    long max = Math.max(j4, e.a(b_));
                    if (c2 != -1) {
                        long j5 = (b_ + ((long) c2)) - 1;
                        j4 = max;
                        j3 = Math.min(j3, e.a(j5) + e.b(j5, j2));
                        z4 = b2;
                    } else {
                        j4 = max;
                        z4 = b2;
                    }
                } else {
                    i = size;
                    z2 = z;
                    z4 = b2;
                }
            } else {
                i = size;
                z2 = z;
            }
            i5++;
            z = z2;
            size = i;
            ovVar2 = ovVar;
            i2 = 0;
        }
        oh ohVar2 = new oh(z4, j4, j3);
        return ohVar2;
    }

    private oh(boolean z, long j, long j2) {
        this.a = z;
        this.b = j;
        this.c = j2;
    }
}
