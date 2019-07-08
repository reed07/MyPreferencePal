package com.google.android.gms.internal.ads;

final class zzbdd implements Runnable {
    private final /* synthetic */ zzbcx zzeqv;
    private final /* synthetic */ int zzeqy;
    private final /* synthetic */ int zzeqz;

    zzbdd(zzbcx zzbcx, int i, int i2) {
        this.zzeqv = zzbcx;
        this.zzeqy = i;
        this.zzeqz = i2;
    }

    public final void run() {
        if (this.zzeqv.zzeqt != null) {
            this.zzeqv.zzeqt.zzm(this.zzeqy, this.zzeqz);
        }
    }
}
