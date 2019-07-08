package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzaaz {
    @Nullable
    private final zzaba zzbln;
    private final Map<String, zzaay> zzczi = new HashMap();

    public zzaaz(@Nullable zzaba zzaba) {
        this.zzbln = zzaba;
    }

    public final void zza(String str, zzaay zzaay) {
        this.zzczi.put(str, zzaay);
    }

    public final void zzb(String str, String str2, long j) {
        zzaay zzaay;
        zzaba zzaba = this.zzbln;
        zzaay zzaay2 = (zzaay) this.zzczi.get(str2);
        String[] strArr = {str};
        if (!(zzaba == null || zzaay2 == null)) {
            zzaba.zza(zzaay2, j, strArr);
        }
        Map<String, zzaay> map = this.zzczi;
        zzaba zzaba2 = this.zzbln;
        if (zzaba2 == null) {
            zzaay = null;
        } else {
            zzaay = zzaba2.zzao(j);
        }
        map.put(str, zzaay);
    }

    @Nullable
    public final zzaba zzrf() {
        return this.zzbln;
    }
}
