package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxg;

final class zzj implements Runnable {
    private final /* synthetic */ zzaxg zzblz;
    private final /* synthetic */ zzh zzbmf;

    zzj(zzh zzh, zzaxg zzaxg) {
        this.zzbmf = zzh;
        this.zzblz = zzaxg;
    }

    public final void run() {
        zzh zzh = this.zzbmf;
        zzaxf zzaxf = new zzaxf(this.zzblz, null, null, null, null, null, null, null);
        zzh.zzb(zzaxf);
    }
}
