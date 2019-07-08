package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzark;
import java.util.Map;

@zzark
public final class zzaa implements zzu<Object> {
    private final Context zzsp;

    public zzaa(Context context) {
        this.zzsp = context;
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (zzbv.zzmf().zzv(this.zzsp)) {
            zzbv.zzmf().zza(this.zzsp, (String) map.get("eventName"), (String) map.get("eventId"));
        }
    }
}
