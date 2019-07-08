package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public final class pe extends pc {
    final pi g;
    final pi h;

    public pe(ox oxVar, long j, long j2, long j3, long j4, List<pf> list, pi piVar, pi piVar2) {
        super(oxVar, j, j2, j3, j4, list);
        this.g = piVar;
        this.h = piVar2;
    }

    public final ox a(oy oyVar) {
        pi piVar = this.g;
        if (piVar == null) {
            return super.a(oyVar);
        }
        ox oxVar = new ox(piVar.a(oyVar.a.a, 0, oyVar.a.d, 0), 0, -1);
        return oxVar;
    }

    public final ox a(oy oyVar, long j) {
        long j2;
        oy oyVar2 = oyVar;
        if (this.f != null) {
            j2 = ((pf) this.f.get((int) (j - this.d))).a;
        } else {
            j2 = (j - this.d) * this.e;
        }
        ox oxVar = new ox(this.h.a(oyVar2.a.a, j, oyVar2.a.d, j2), 0, -1);
        return oxVar;
    }

    public final int b(long j) {
        if (this.f != null) {
            return this.f.size();
        }
        if (j != -9223372036854775807L) {
            return (int) vf.a(j, (this.e * 1000000) / this.b);
        }
        return -1;
    }
}
