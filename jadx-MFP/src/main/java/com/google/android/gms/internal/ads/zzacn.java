package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

final class zzacn implements zzu<Object> {
    private final /* synthetic */ zzaqp zzdcn;
    private final /* synthetic */ zzacj zzdco;

    zzacn(zzacj zzacj, zzaqp zzaqp) {
        this.zzdco = zzacj;
        this.zzdcn = zzaqp;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzbgg zzbgg = (zzbgg) this.zzdco.zzdcm.get();
        if (zzbgg == null) {
            this.zzdcn.zzb("/hideOverlay", this);
        } else {
            zzbgg.getView().setVisibility(8);
        }
    }
}
