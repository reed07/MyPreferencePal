package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class ma implements Runnable {
    private final lr a;
    private final lq b;
    private final md c;

    ma(lr lrVar, lq lqVar, md mdVar) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = mdVar;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.b(lrVar.a, lrVar.b, this.c);
    }
}
