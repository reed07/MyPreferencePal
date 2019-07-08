package com.google.android.gms.internal.ads;

import com.mopub.common.AdType;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzapv implements zzaqe<zzabq> {
    public final /* synthetic */ zzacf zza(zzapw zzapw, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        zzbcb zzbcb;
        JSONObject zza = zzbac.zza(jSONObject, "html_containers", "instream");
        if (zza == null) {
            zzbcb = zzapw.zzc(jSONObject, "video");
        } else {
            zzbcb = zzapw.zza(zza.optString("base_url"), zza.optString(AdType.HTML), true);
        }
        zzbgg zzc = zzapw.zzc(zzbcb);
        if (zzc != null) {
            return new zzabq(zzc);
        }
        zzaxz.zzeo("Can not get video view for instream ad.");
        return null;
    }
}
