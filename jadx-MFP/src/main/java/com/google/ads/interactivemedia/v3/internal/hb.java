package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;

/* compiled from: IMASDK */
final class hb implements gy {
    private final ut a;
    private final int b = this.a.p();
    private final int c = (this.a.p() & 255);
    private int d;
    private int e;

    public hb(gw gwVar) {
        this.a = gwVar.be;
        this.a.c(12);
    }

    public final boolean c() {
        return false;
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        int i = this.c;
        if (i == 8) {
            return this.a.e();
        }
        if (i == 16) {
            return this.a.f();
        }
        int i2 = this.d;
        this.d = i2 + 1;
        if (i2 % 2 != 0) {
            return this.e & 15;
        }
        this.e = this.a.e();
        return (this.e & PsExtractor.VIDEO_STREAM_MASK) >> 4;
    }
}
