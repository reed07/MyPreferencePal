package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class ds implements Runnable {
    private final dm a;
    private final int b;

    ds(dm dmVar, int i) {
        this.a = dmVar;
        this.b = i;
    }

    public final void run() {
        this.a.b(this.b);
    }
}
