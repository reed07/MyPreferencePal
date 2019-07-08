package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

final /* synthetic */ class lx implements Runnable {
    private final lr a;
    private final lq b;
    private final mc c;
    private final md d;
    private final IOException e;
    private final boolean f;

    lx(lr lrVar, lq lqVar, mc mcVar, md mdVar, IOException iOException, boolean z) {
        this.a = lrVar;
        this.b = lqVar;
        this.c = mcVar;
        this.d = mdVar;
        this.e = iOException;
        this.f = z;
    }

    public final void run() {
        lr lrVar = this.a;
        this.b.a(lrVar.a, lrVar.b, this.c, this.d, this.e, this.f);
    }
}
