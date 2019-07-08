package com.google.android.gms.internal.ads;

final class zzbde implements Runnable {
    private final /* synthetic */ zzbcx zzeqv;

    zzbde(zzbcx zzbcx) {
        this.zzeqv = zzbcx;
    }

    public final void run() {
        if (this.zzeqv.zzeqt != null) {
            this.zzeqv.zzeqt.onPaused();
            this.zzeqv.zzeqt.zzabh();
        }
    }
}
