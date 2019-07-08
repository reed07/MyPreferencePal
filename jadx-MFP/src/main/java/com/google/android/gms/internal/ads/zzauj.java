package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;

@zzark
public final class zzauj implements zzatu {
    private zzajv<JSONObject, JSONObject> zzedu;
    private zzajv<JSONObject, JSONObject> zzedw;

    public zzauj(Context context) {
        this.zzedw = zzbv.zzlu().zzb(context, zzbbi.zzaav()).zza("google.afma.request.getAdDictionary", zzaka.zzdkb, zzaka.zzdkb);
        this.zzedu = zzbv.zzlu().zzb(context, zzbbi.zzaav()).zza("google.afma.sdkConstants.getSdkConstants", zzaka.zzdkb, zzaka.zzdkb);
    }

    public final zzajv<JSONObject, JSONObject> zzwo() {
        return this.zzedw;
    }

    public final zzajv<JSONObject, JSONObject> zzwp() {
        return this.zzedu;
    }
}
