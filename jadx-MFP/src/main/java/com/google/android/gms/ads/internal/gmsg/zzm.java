package com.google.android.gms.ads.internal.gmsg;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

final class zzm implements zzu<zzbgg> {
    zzm() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        ((zzbgg) obj).zzaf(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
    }
}
