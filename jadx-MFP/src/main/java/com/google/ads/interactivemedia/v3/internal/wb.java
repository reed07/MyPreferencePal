package com.google.ads.interactivemedia.v3.internal;

import android.view.Surface;

final /* synthetic */ class wb implements Runnable {
    private final vv a;
    private final Surface b;

    wb(vv vvVar, Surface surface) {
        this.a = vvVar;
        this.b = surface;
    }

    public final void run() {
        this.a.b(this.b);
    }
}
