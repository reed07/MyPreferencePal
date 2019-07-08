package com.google.android.gms.ads.internal.gmsg;

import com.brightcove.player.event.EventType;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

final class zzs implements zzu<zzbgg> {
    zzs() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        if (map.keySet().contains(TtmlNode.START)) {
            zzbgg.zzax(true);
        }
        if (map.keySet().contains(EventType.STOP)) {
            zzbgg.zzax(false);
        }
    }
}
