package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaxf;

final class zzl implements Runnable {
    private final /* synthetic */ zzk zzbmi;

    zzl(zzk zzk) {
        this.zzbmi = zzk;
    }

    public final void run() {
        zzh zzh = this.zzbmi.zzbmf;
        zzaxf zzaxf = new zzaxf(this.zzbmi.zzblz, null, null, null, null, null, null, null);
        zzh.zzb(zzaxf);
    }
}
