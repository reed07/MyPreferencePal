package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbcl;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzz implements zzu<Object> {
    @VisibleForTesting
    private final HashMap<String, zzbcl<JSONObject>> zzdga = new HashMap<>();

    public final Future<JSONObject> zzbu(String str) {
        zzbcl zzbcl = new zzbcl();
        this.zzdga.put(str, zzbcl);
        return zzbcl;
    }

    public final void zzbv(String str) {
        zzbcl zzbcl = (zzbcl) this.zzdga.get(str);
        if (zzbcl == null) {
            zzaxz.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!zzbcl.isDone()) {
            zzbcl.cancel(true);
        }
        this.zzdga.remove(str);
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = (String) map.get("fetched_ad");
        zzaxz.zzdn("Received ad from the cache.");
        zzbcl zzbcl = (zzbcl) this.zzdga.get(str);
        if (zzbcl == null) {
            zzaxz.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            zzbcl.set(new JSONObject(str2));
        } catch (JSONException e) {
            zzaxz.zzb("Failed constructing JSON object from value passed from javascript", e);
            zzbcl.set(null);
        } finally {
            this.zzdga.remove(str);
        }
    }
}
