package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final /* synthetic */ class zzacl implements zzbho {
    private final zzack zzdcp;
    private final Map zzdcq;
    private final zzaqp zzdcr;

    zzacl(zzack zzack, Map map, zzaqp zzaqp) {
        this.zzdcp = zzack;
        this.zzdcq = map;
        this.zzdcr = zzaqp;
    }

    public final void zzp(boolean z) {
        zzack zzack = this.zzdcp;
        Map map = this.zzdcq;
        zzaqp zzaqp = this.zzdcr;
        zzack.zzdco.mId = (String) map.get("id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put("id", zzack.zzdco.mId);
            zzaqp.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzaxz.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}
