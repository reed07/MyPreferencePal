package com.google.ads.interactivemedia.v3.internal;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

/* compiled from: IMASDK */
final class adf implements Callback {
    private final /* synthetic */ ade a;

    adf(ade ade) {
        this.a = ade;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a.k = true;
        if (this.a.j == adh.PLAYING || this.a.j == adh.PAUSED) {
            this.a.a.a(surfaceHolder);
        }
        if (this.a.j == adh.PLAYING) {
            this.a.a.b(true);
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.a.b(surfaceHolder);
        this.a.a.b(false);
        this.a.k = false;
    }
}
