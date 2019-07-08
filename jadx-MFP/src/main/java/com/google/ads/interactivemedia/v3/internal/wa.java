package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class wa implements Runnable {
    private final vv a;
    private final int b;
    private final int c;
    private final int d;
    private final float e;

    wa(vv vvVar, int i, int i2, int i3, float f) {
        this.a = vvVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f;
    }

    public final void run() {
        this.a.b(this.b, this.c, this.d, this.e);
    }
}
