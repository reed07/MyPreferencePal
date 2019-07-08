package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class lz implements Runnable {
    private final lr a;
    private final lq b;
    private final lo c;
    private final md d;

    lz(lr lrVar, lq lqVar, lo loVar, md mdVar) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = loVar;
        this.d = mdVar;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.a(lrVar.a, this.c, this.d);
    }
}
