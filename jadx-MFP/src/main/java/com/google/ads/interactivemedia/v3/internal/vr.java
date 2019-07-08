package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;

@TargetApi(17)
/* compiled from: IMASDK */
final class vr implements DisplayListener {
    private final DisplayManager a;
    private final /* synthetic */ vq b;

    public vr(vq vqVar, DisplayManager displayManager) {
        this.b = vqVar;
        this.a = displayManager;
    }

    public final void onDisplayAdded(int i) {
    }

    public final void onDisplayRemoved(int i) {
    }

    public final void a() {
        this.a.registerDisplayListener(this, null);
    }

    public final void b() {
        this.a.unregisterDisplayListener(this);
    }

    public final void onDisplayChanged(int i) {
        if (i == 0) {
            this.b.c();
        }
    }
}
