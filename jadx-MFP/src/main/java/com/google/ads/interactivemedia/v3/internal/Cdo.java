package com.google.ads.interactivemedia.v3.internal;

/* renamed from: com.google.ads.interactivemedia.v3.internal.do reason: invalid class name */
final /* synthetic */ class Cdo implements Runnable {
    private final dm a;
    private final String b;
    private final long c;
    private final long d;

    Cdo(dm dmVar, String str, long j, long j2) {
        this.a = dmVar;
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        this.a.b(this.b, this.c, this.d);
    }
}
