package com.google.android.gms.internal.ads;

import android.support.v4.util.SimpleArrayMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaqs implements zzaqe<zzabw> {
    private final boolean zzdut;

    public zzaqs(boolean z) {
        this.zzdut = z;
    }

    public final /* synthetic */ zzacf zza(zzapw zzapw, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzbcb zzg = zzapw.zzg(jSONObject);
        zzbcb zzc = zzapw.zzc(jSONObject, "video");
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                simpleArrayMap2.put(jSONObject2.getString("name"), jSONObject2.getString("string_value"));
            } else if ("image".equals(string)) {
                simpleArrayMap.put(jSONObject2.getString("name"), zzapw.zza(jSONObject2, "image_value", this.zzdut));
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                zzaxz.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        zzbgg zzc2 = zzapw.zzc(zzc);
        String string2 = jSONObject.getString("custom_template_id");
        SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
        for (int i2 = 0; i2 < simpleArrayMap.size(); i2++) {
            simpleArrayMap3.put(simpleArrayMap.keyAt(i2), ((Future) simpleArrayMap.valueAt(i2)).get());
        }
        zzabw zzabw = new zzabw(string2, simpleArrayMap3, simpleArrayMap2, (zzabm) zzg.get(), zzc2 != null ? zzc2.zzabu() : null, zzc2 != null ? zzc2.getView() : null);
        return zzabw;
    }
}
