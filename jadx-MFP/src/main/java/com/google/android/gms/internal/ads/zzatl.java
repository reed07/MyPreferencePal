package com.google.android.gms.internal.ads;

import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

public final class zzatl implements zzu<Object> {
    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = "Invalid request: ";
        String valueOf = String.valueOf((String) map.get(AbstractEvent.ERRORS));
        zzaxz.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        zzatd.zzdzv.zzbv(str);
    }
}
