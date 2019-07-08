package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class ni implements gc {
    public bs a;
    private final int b;
    private final int c;
    private final bs d;
    private final fp e = new fp();
    private gc f;
    private long g;

    public ni(int i, int i2, bs bsVar) {
        this.b = i;
        this.c = i2;
        this.d = bsVar;
    }

    public final void a(nj njVar, long j) {
        if (njVar == null) {
            this.f = this.e;
            return;
        }
        this.g = j;
        this.f = njVar.a(this.c);
        bs bsVar = this.a;
        if (bsVar != null) {
            this.f.a(bsVar);
        }
    }

    public final void a(bs bsVar) {
        bs bsVar2 = this.d;
        if (bsVar2 != null) {
            bsVar = bsVar.a(bsVar2);
        }
        this.a = bsVar;
        this.f.a(this.a);
    }

    public final int a(fr frVar, int i, boolean z) throws IOException, InterruptedException {
        return this.f.a(frVar, i, z);
    }

    public final void a(ut utVar, int i) {
        this.f.a(utVar, i);
    }

    public final void a(long j, int i, int i2, int i3, gd gdVar) {
        long j2 = this.g;
        if (j2 != -9223372036854775807L && j >= j2) {
            this.f = this.e;
        }
        this.f.a(j, i, i2, i3, gdVar);
    }
}
