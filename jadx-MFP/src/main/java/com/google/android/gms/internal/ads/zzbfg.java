package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Map;

@zzark
public final class zzbfg implements zzu<zzbdz> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbdz zzbdz = (zzbdz) obj;
        zzbgw zzabu = zzbdz.zzabu();
        if (zzabu == null) {
            try {
                zzbgw zzbgw = new zzbgw(zzbdz, Float.parseFloat((String) map.get("duration")), AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("customControlsAllowed")), AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("clickToExpandAllowed")));
                zzbdz.zza(zzbgw);
                zzabu = zzbgw;
            } catch (NullPointerException | NumberFormatException e) {
                zzaxz.zzb("Unable to parse videoMeta message.", e);
                zzbv.zzlj().zza(e, "VideoMetaGmsgHandler.onGmsg");
                return;
            }
        }
        float parseFloat = Float.parseFloat((String) map.get("duration"));
        boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("muted"));
        float parseFloat2 = Float.parseFloat((String) map.get("currentTime"));
        int parseInt = Integer.parseInt((String) map.get("playbackState"));
        if (parseInt < 0 || 3 < parseInt) {
            parseInt = 0;
        }
        String str = (String) map.get(InMobiNetworkValues.ASPECT_RATIO);
        float parseFloat3 = TextUtils.isEmpty(str) ? BitmapDescriptorFactory.HUE_RED : Float.parseFloat(str);
        if (zzaxz.isLoggable(3)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 140);
            sb.append("Video Meta GMSG: currentTime : ");
            sb.append(parseFloat2);
            sb.append(" , duration : ");
            sb.append(parseFloat);
            sb.append(" , isMuted : ");
            sb.append(equals);
            sb.append(" , playbackState : ");
            sb.append(parseInt);
            sb.append(" , aspectRatio : ");
            sb.append(str);
            zzaxz.zzdn(sb.toString());
        }
        zzabu.zza(parseFloat2, parseFloat, parseInt, equals, parseFloat3);
    }
}
