package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.CopyOnWriteArrayList;

final /* synthetic */ class bb implements Runnable {
    private final CopyOnWriteArrayList a;
    private final as b;

    bb(CopyOnWriteArrayList copyOnWriteArrayList, as asVar) {
        this.a = copyOnWriteArrayList;
        this.b = asVar;
    }

    public final void run() {
        ay.c(this.a, this.b);
    }
}
