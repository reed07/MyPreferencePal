package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class lt implements Runnable {
    private final lr a;
    private final lq b;
    private final lo c;

    lt(lr lrVar, lq lqVar, lo loVar) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = loVar;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.b(lrVar.a, this.c);
    }
}
