package com.google.android.gms.ads.internal.gmsg;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Map;

@zzark
public final class zzx implements zzu<Object> {
    private final zzy zzdfz;

    public zzx(zzy zzy) {
        this.zzdfz = zzy;
    }

    public final void zza(Object obj, Map<String, String> map) {
        boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground"));
        boolean equals2 = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("blur"));
        float f = BitmapDescriptorFactory.HUE_RED;
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat((String) map.get("blurRadius"));
            }
        } catch (NumberFormatException e) {
            zzaxz.zzb("Fail to parse float", e);
        }
        this.zzdfz.zzo(equals);
        this.zzdfz.zza(equals2, f);
    }
}
