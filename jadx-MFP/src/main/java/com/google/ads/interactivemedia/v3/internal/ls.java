package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class ls implements Runnable {
    private final lr a;
    private final lq b;
    private final lo c;

    ls(lr lrVar, lq lqVar, lo loVar) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = loVar;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.a(lrVar.a, this.c);
    }
}
