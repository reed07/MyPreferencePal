package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class sd implements Runnable {
    private float a;
    private float b;
    private boolean c;
    private boolean d;
    private final /* synthetic */ sc e;

    private sd(sc scVar) {
        this.e = scVar;
    }

    public final void a(float f, float f2, boolean z) {
        this.a = f;
        this.b = f2;
        this.c = z;
        if (!this.d) {
            this.d = true;
            this.e.post(this);
        }
    }

    public final void run() {
        this.d = false;
        sc scVar = this.e;
    }

    /* synthetic */ sd(sc scVar, byte b2) {
        this(scVar);
    }
}
