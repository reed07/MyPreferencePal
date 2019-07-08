package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

final class zzbgu implements zzu<zzbgg> {
    private final /* synthetic */ zzbgt zzfah;

    zzbgu(zzbgt zzbgt) {
        this.zzfah = zzbgt;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzfah) {
                        if (this.zzfah.zzezz != parseInt) {
                            this.zzfah.zzezz = parseInt;
                            this.zzfah.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzaxz.zzc("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
