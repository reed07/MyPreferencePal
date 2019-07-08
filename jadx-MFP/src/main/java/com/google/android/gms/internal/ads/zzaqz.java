package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.mopub.common.AdType;
import java.util.Map;
import org.json.JSONObject;

final class zzaqz implements zzu<zzbgg> {
    private final /* synthetic */ zzbgg zzduz;
    private final /* synthetic */ zzbcl zzdva;
    private final /* synthetic */ zzaqt zzdvb;

    zzaqz(zzaqt zzaqt, zzbgg zzbgg, zzbcl zzbcl) {
        this.zzdvb = zzaqt;
        this.zzduz = zzbgg;
        this.zzdva = zzbcl;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        boolean z;
        JSONObject jSONObject;
        try {
            String str = (String) map.get("success");
            String str2 = (String) map.get("failure");
            if (!TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str2);
                z = false;
            } else {
                jSONObject = new JSONObject(str);
                z = true;
            }
            if (this.zzdvb.zzbqb.equals(jSONObject.optString("ads_id", ""))) {
                this.zzduz.zzb("/nativeAdPreProcess", this);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("success", z);
                jSONObject2.put(AdType.STATIC_NATIVE, jSONObject);
                this.zzdva.set(jSONObject2);
            }
        } catch (Throwable th) {
            zzaxz.zzb("Error while preprocessing json.", th);
            this.zzdva.setException(th);
        }
    }
}
