package com.google.android.gms.ads.internal.gmsg;

import com.brightcove.player.event.EventType;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

final class zzq implements zzu<zzbgg> {
    zzq() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        String str = (String) map.get("action");
        if (EventType.PAUSE.equals(str)) {
            zzbgg.zzjf();
            return;
        }
        if ("resume".equals(str)) {
            zzbgg.zzjg();
        }
    }
}
