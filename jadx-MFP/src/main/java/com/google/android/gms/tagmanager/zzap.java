package com.google.android.gms.tagmanager;

import java.util.List;

final class zzap implements zzaq {
    private final /* synthetic */ DataLayer zzbbj;

    zzap(DataLayer dataLayer) {
        this.zzbbj = dataLayer;
    }

    public final void zze(List<zza> list) {
        for (zza zza : list) {
            this.zzbbj.zzf(DataLayer.zzk(zza.mKey, zza.mValue));
        }
        this.zzbbj.zzbbi.countDown();
    }
}
