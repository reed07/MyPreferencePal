package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class vz implements Runnable {
    private final vv a;
    private final int b;
    private final long c;

    vz(vv vvVar, int i, long j) {
        this.a = vvVar;
        this.b = i;
        this.c = j;
    }

    public final void run() {
        this.a.b(this.b, this.c);
    }
}
