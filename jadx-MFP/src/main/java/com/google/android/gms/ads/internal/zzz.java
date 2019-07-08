package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbhr;

final /* synthetic */ class zzz implements zzbhr {
    private final zzaxf zzbnc;
    private final Runnable zzbnd;

    zzz(zzaxf zzaxf, Runnable runnable) {
        this.zzbnc = zzaxf;
        this.zzbnd = runnable;
    }

    public final void zzjw() {
        zzaxf zzaxf = this.zzbnc;
        Runnable runnable = this.zzbnd;
        if (!zzaxf.zzehi) {
            zzayh.zzd(runnable);
        }
    }
}
