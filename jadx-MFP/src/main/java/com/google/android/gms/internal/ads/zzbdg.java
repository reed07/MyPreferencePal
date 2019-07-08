package com.google.android.gms.internal.ads;

final class zzbdg implements Runnable {
    private final /* synthetic */ zzbcx zzeqv;

    zzbdg(zzbcx zzbcx) {
        this.zzeqv = zzbcx;
    }

    public final void run() {
        if (this.zzeqv.zzeqt != null) {
            this.zzeqv.zzeqt.onPaused();
        }
    }
}
