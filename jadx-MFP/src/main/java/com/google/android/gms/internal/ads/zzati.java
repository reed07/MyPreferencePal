package com.google.android.gms.internal.ads;

final class zzati implements Runnable {
    private final /* synthetic */ zzatd zzdzy;

    zzati(zzatd zzatd) {
        this.zzdzy = zzatd;
    }

    public final void run() {
        if (this.zzdzy.zzdzx != null) {
            this.zzdzy.zzdzx.release();
            this.zzdzy.zzdzx = null;
        }
    }
}
