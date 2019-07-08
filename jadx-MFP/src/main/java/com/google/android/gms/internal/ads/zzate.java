package com.google.android.gms.internal.ads;

final class zzate implements Runnable {
    private final /* synthetic */ zzaxg zzblz;
    private final /* synthetic */ zzatd zzdzy;

    zzate(zzatd zzatd, zzaxg zzaxg) {
        this.zzdzy = zzatd;
        this.zzblz = zzaxg;
    }

    public final void run() {
        this.zzdzy.zzdvp.zza(this.zzblz);
        if (this.zzdzy.zzdzx != null) {
            this.zzdzy.zzdzx.release();
            this.zzdzy.zzdzx = null;
        }
    }
}
