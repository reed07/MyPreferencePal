package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

final class zzl implements zzu<zzbgg> {
    zzl() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        zzd zzadh = zzbgg.zzadh();
        if (zzadh != null) {
            zzadh.close();
            return;
        }
        zzd zzadi = zzbgg.zzadi();
        if (zzadi != null) {
            zzadi.close();
        } else {
            zzaxz.zzeo("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
