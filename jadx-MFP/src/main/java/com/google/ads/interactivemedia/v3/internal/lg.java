package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class lg implements mu {
    private final mu[] a;

    public lg(mu[] muVarArr) {
        this.a = muVarArr;
    }

    public final long d() {
        long j = Long.MAX_VALUE;
        for (mu d : this.a) {
            long d2 = d.d();
            if (d2 != Long.MIN_VALUE) {
                j = Math.min(j, d2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final long e() {
        long j = Long.MAX_VALUE;
        for (mu e : this.a) {
            long e2 = e.e();
            if (e2 != Long.MIN_VALUE) {
                j = Math.min(j, e2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final void a(long j) {
        for (mu a2 : this.a) {
            a2.a(j);
        }
    }

    public final boolean c(long j) {
        mu[] muVarArr;
        long j2 = j;
        boolean z = false;
        while (true) {
            long e = e();
            if (e == Long.MIN_VALUE) {
                break;
            }
            boolean z2 = false;
            for (mu muVar : this.a) {
                long e2 = muVar.e();
                boolean z3 = e2 != Long.MIN_VALUE && e2 <= j2;
                if (e2 == e || z3) {
                    z2 |= muVar.c(j2);
                }
            }
            z |= z2;
            if (!z2) {
                break;
            }
        }
        return z;
    }
}
