package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class lu implements Runnable {
    private final lr a;
    private final lq b;
    private final mc c;
    private final md d;

    lu(lr lrVar, lq lqVar, mc mcVar, md mdVar) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = mcVar;
        this.d = mdVar;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.a(lrVar.a, lrVar.b, this.c, this.d);
    }
}