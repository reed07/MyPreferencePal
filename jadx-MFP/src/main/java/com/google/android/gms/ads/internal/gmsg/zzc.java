package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import java.util.Map;

@zzark
public final class zzc implements zzu<Object> {
    private final zzd zzdes;

    public zzc(zzd zzd) {
        this.zzdes = zzd;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("name");
        if (str == null) {
            zzaxz.zzeo("App event with no name parameter.");
        } else {
            this.zzdes.onAppEvent(str, (String) map.get("info"));
        }
    }
}
