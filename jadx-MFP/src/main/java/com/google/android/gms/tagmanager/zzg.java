package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

final class zzg implements zzb {
    private final Context zzri;

    public zzg(Context context) {
        this.zzri = context;
    }

    public final void zzd(Map<String, Object> map) {
        Object obj = map.get("gtm.url");
        if (obj == null) {
            Object obj2 = map.get("gtm");
            if (obj2 != null && (obj2 instanceof Map)) {
                obj = ((Map) obj2).get("url");
            }
        }
        if (obj != null && (obj instanceof String)) {
            String queryParameter = Uri.parse((String) obj).getQueryParameter("referrer");
            if (queryParameter != null) {
                zzcw.zzh(this.zzri, queryParameter);
            }
        }
    }
}
