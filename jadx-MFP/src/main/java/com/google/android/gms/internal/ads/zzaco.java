package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class zzaco implements zzu<Object> {
    private final /* synthetic */ zzaqp zzdcn;
    private final /* synthetic */ zzacj zzdco;

    zzaco(zzacj zzacj, zzaqp zzaqp) {
        this.zzdco = zzacj;
        this.zzdcn = zzaqp;
    }

    public final void zza(Object obj, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            jSONObject.put("id", this.zzdco.mId);
            this.zzdcn.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzaxz.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
