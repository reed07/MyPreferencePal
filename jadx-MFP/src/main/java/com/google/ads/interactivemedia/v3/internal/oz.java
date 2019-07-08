package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public final class oz extends oy implements ok {
    private final pc e;

    public oz(long j, bs bsVar, String str, pc pcVar, List<ou> list) {
        super(j, bsVar, str, pcVar, list, 0);
        this.e = pcVar;
    }

    public final ox d() {
        return null;
    }

    public final ok e() {
        return this;
    }

    public final String f() {
        return null;
    }

    public final ox b(long j) {
        return this.e.a(this, j);
    }

    public final long a(long j, long j2) {
        long j3;
        pc pcVar = this.e;
        long j4 = pcVar.d;
        long b = (long) pcVar.b(j2);
        if (b == 0) {
            return j4;
        }
        if (pcVar.f == null) {
            j3 = (j / ((pcVar.e * 1000000) / pcVar.b)) + pcVar.d;
            if (j3 < j4) {
                j3 = j4;
            } else if (b != -1) {
                return Math.min(j3, (j4 + b) - 1);
            }
        } else {
            j3 = (b + j4) - 1;
            long j5 = j4;
            while (j5 <= j3) {
                long j6 = ((j3 - j5) / 2) + j5;
                int i = (pcVar.a(j6) > j ? 1 : (pcVar.a(j6) == j ? 0 : -1));
                if (i < 0) {
                    j5 = j6 + 1;
                } else if (i <= 0) {
                    return j6;
                } else {
                    j3 = j6 - 1;
                }
            }
            if (j5 == j4) {
                return j5;
            }
        }
        return j3;
    }

    public final long a(long j) {
        return this.e.a(j);
    }

    public final long b(long j, long j2) {
        pc pcVar = this.e;
        if (pcVar.f != null) {
            return (((pf) pcVar.f.get((int) (j - pcVar.d))).b * 1000000) / pcVar.b;
        }
        int b = pcVar.b(j2);
        if (b == -1 || j != (pcVar.d + ((long) b)) - 1) {
            return (pcVar.e * 1000000) / pcVar.b;
        }
        return j2 - pcVar.a(j);
    }

    public final long b_() {
        return this.e.d;
    }

    public final int c(long j) {
        return this.e.b(j);
    }

    public final boolean b() {
        return this.e.a();
    }
}
