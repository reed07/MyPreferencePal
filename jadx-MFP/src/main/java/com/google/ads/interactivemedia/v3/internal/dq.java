package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class dq implements Runnable {
    private final dm a;
    private final int b;
    private final long c;
    private final long d;

    dq(dm dmVar, int i, long j, long j2) {
        this.a = dmVar;
        this.b = i;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        this.a.b(this.b, this.c, this.d);
    }
}
