package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzanx;
import com.google.android.gms.internal.ads.zzaoa;
import com.google.android.gms.internal.ads.zzaoc;
import com.google.android.gms.internal.ads.zzaod;
import com.google.android.gms.internal.ads.zzaol;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzwu;
import java.util.Map;

@zzark
public final class zzab implements zzu<zzbgg> {
    private static final Map<String, Integer> zzdge = CollectionUtils.mapOfKeyValueArrays(new String[]{"resize", "playVideo", "storePicture", "createCalendarEvent", "setOrientationProperties", "closeResizedAd", "unload"}, new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7)});
    private final zzw zzdgb;
    private final zzaoa zzdgc;
    private final zzaol zzdgd;

    public zzab(zzw zzw, zzaoa zzaoa, zzaol zzaol) {
        this.zzdgb = zzw;
        this.zzdgc = zzaoa;
        this.zzdgd = zzaol;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        int intValue = ((Integer) zzdge.get((String) map.get("a"))).intValue();
        if (!(intValue == 5 || intValue == 7)) {
            zzw zzw = this.zzdgb;
            if (zzw != null && !zzw.zzju()) {
                this.zzdgb.zzas(null);
                return;
            }
        }
        if (intValue != 1) {
            switch (intValue) {
                case 3:
                    new zzaod(zzbgg, map).execute();
                    return;
                case 4:
                    new zzanx(zzbgg, map).execute();
                    return;
                case 5:
                    new zzaoc(zzbgg, map).execute();
                    return;
                case 6:
                    this.zzdgc.zzx(true);
                    return;
                case 7:
                    if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcpv)).booleanValue()) {
                        this.zzdgd.zzjv();
                        return;
                    }
                    break;
                default:
                    zzaxz.zzen("Unknown MRAID command called.");
                    break;
            }
            return;
        }
        this.zzdgc.zzk(map);
    }
}
