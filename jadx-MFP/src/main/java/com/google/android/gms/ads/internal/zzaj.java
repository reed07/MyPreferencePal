package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzwb;

final class zzaj implements Runnable {
    private final /* synthetic */ zzwb zzbod;
    private final /* synthetic */ zzah zzboe;
    private final /* synthetic */ int zzbof;

    zzaj(zzah zzah, zzwb zzwb, int i) {
        this.zzboe = zzah;
        this.zzbod = zzwb;
        this.zzbof = i;
    }

    public final void run() {
        synchronized (this.zzboe.mLock) {
            this.zzboe.zzb(this.zzbod, this.zzbof);
        }
    }
}
