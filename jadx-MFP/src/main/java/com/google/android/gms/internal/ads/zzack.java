package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

final class zzack implements zzu<Object> {
    private final /* synthetic */ zzaqp zzdcn;
    final /* synthetic */ zzacj zzdco;

    zzack(zzacj zzacj, zzaqp zzaqp) {
        this.zzdco = zzacj;
        this.zzdcn = zzaqp;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzbgg zzbgg = (zzbgg) this.zzdco.zzdcm.get();
        if (zzbgg == null) {
            this.zzdcn.zzb("/loadHtml", this);
            return;
        }
        zzbgg.zzadl().zza((zzbho) new zzacl(this, map, this.zzdcn));
        String str = (String) map.get("overlayHtml");
        String str2 = (String) map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            zzbgg.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
        } else {
            zzbgg.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        }
    }
}
