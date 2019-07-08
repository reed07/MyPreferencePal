package com.google.ads.interactivemedia.v3.internal;

final /* synthetic */ class qd implements Runnable {
    private final qe a;

    private qd(qe qeVar) {
        this.a = qeVar;
    }

    static Runnable a(qe qeVar) {
        return new qd(qeVar);
    }

    public final void run() {
        this.a.f();
    }
}
