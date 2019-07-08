package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public class uo {
    public final String a;
    public final String b;
    public final int c;
    private boolean d;

    public synchronized boolean a() {
        if (this.d) {
            return false;
        }
        this.d = true;
        notifyAll();
        return true;
    }

    public synchronized boolean b() {
        boolean z;
        z = this.d;
        this.d = false;
        return z;
    }

    public synchronized void c() throws InterruptedException {
        while (!this.d) {
            wait();
        }
    }
}
