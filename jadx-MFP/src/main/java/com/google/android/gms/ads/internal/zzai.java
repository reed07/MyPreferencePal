package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzwb;

final class zzai implements Runnable {
    private final /* synthetic */ zzwb zzbod;
    private final /* synthetic */ zzah zzboe;

    zzai(zzah zzah, zzwb zzwb) {
        this.zzboe = zzah;
        this.zzbod = zzwb;
    }

    public final void run() {
        synchronized (this.zzboe.mLock) {
            if (this.zzboe.zzka()) {
                this.zzboe.zze(this.zzbod);
            } else {
                this.zzboe.zzb(this.zzbod, 1);
            }
        }
    }
}
