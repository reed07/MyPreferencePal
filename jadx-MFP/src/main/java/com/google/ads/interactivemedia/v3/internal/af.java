package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class af implements Runnable {
    af() {
    }

    public final void run() {
        if (ac.c != null) {
            ac.c.post(ac.j);
            ac.c.postDelayed(ac.k, 200);
        }
    }
}
