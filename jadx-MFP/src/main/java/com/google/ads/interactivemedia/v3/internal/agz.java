package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
final class agz extends agw {
    private final agx a = new agx();

    agz() {
    }

    public final void a(Throwable th) {
        th.printStackTrace();
        List<Throwable> a2 = this.a.a(th, false);
        if (a2 != null) {
            synchronized (a2) {
                for (Throwable th2 : a2) {
                    System.err.print("Suppressed: ");
                    th2.printStackTrace();
                }
            }
        }
    }
}
