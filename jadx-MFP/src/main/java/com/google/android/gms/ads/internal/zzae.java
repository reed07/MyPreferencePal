package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzbbl;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzbcb;
import org.json.JSONObject;

final /* synthetic */ class zzae implements zzbbl {
    static final zzbbl zzbni = new zzae();

    private zzae() {
    }

    public final zzbcb zzf(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            zzbv.zzlj().zzyq().zzdv(jSONObject.getString("appSettingsJson"));
        }
        return zzbbq.zzm(null);
    }
}
