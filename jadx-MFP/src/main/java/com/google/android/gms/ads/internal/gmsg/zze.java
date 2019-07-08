package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.facebook.ads.internal.j.e;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzaba;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbgg;
import java.util.Map;

@zzark
public final class zze implements zzu<zzbgg> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgg zzbgg = (zzbgg) obj;
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get("label");
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str2)) {
                zzaxz.zzeo("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str4)) {
                zzaxz.zzeo("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = zzbv.zzlm().elapsedRealtime() + (Long.parseLong(str4) - zzbv.zzlm().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    zzbgg.zzaby().zzb(str2, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    zzaxz.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            String str5 = (String) map.get("value");
            if (TextUtils.isEmpty(str5)) {
                zzaxz.zzeo("No value given for CSI experiment.");
                return;
            }
            zzaba zzrf = zzbgg.zzaby().zzrf();
            if (zzrf == null) {
                zzaxz.zzeo("No ticker for WebView, dropping experiment ID.");
            } else {
                zzrf.zzg(e.a, str5);
            }
        } else {
            if ("extra".equals(str)) {
                String str6 = (String) map.get("name");
                String str7 = (String) map.get("value");
                if (TextUtils.isEmpty(str7)) {
                    zzaxz.zzeo("No value given for CSI extra.");
                } else if (TextUtils.isEmpty(str6)) {
                    zzaxz.zzeo("No name given for CSI extra.");
                } else {
                    zzaba zzrf2 = zzbgg.zzaby().zzrf();
                    if (zzrf2 == null) {
                        zzaxz.zzeo("No ticker for WebView, dropping extra parameter.");
                        return;
                    }
                    zzrf2.zzg(str6, str7);
                }
            }
        }
    }
}
