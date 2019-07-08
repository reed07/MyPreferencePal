package com.google.android.gms.internal.ads;

final class zzle implements Runnable {
    private final /* synthetic */ zzlc zzaxn;

    zzle(zzlc zzlc) {
        this.zzaxn = zzlc;
    }

    public final void run() {
        if (!this.zzaxn.zzyb) {
            this.zzaxn.zzawz.zza(this.zzaxn);
        }
    }
}
