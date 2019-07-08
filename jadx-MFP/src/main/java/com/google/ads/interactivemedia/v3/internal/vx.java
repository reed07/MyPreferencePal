package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class vx implements Runnable {
    private final vv a;
    private final String b;
    private final long c;
    private final long d;

    vx(vv vvVar, String str, long j, long j2) {
        this.a = vvVar;
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        this.a.b(this.b, this.c, this.d);
    }
}
