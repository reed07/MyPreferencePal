package com.google.android.gms.ads.internal.gmsg;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zza implements zzu<Object> {
    private final zzb zzder;

    public zza(zzb zzb) {
        this.zzder = zzb;
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzder != null) {
            String str = (String) map.get("name");
            if (str == null) {
                zzaxz.zzen("Ad metadata with no name parameter.");
                str = "";
            }
            Bundle zzbt = zzbt((String) map.get("info"));
            if (zzbt == null) {
                zzaxz.e("Failed to convert ad metadata to Bundle.");
            } else {
                this.zzder.zza(str, zzbt);
            }
        }
    }

    private static Bundle zzbt(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object obj = jSONObject.get(str2);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str2, ((Double) obj).doubleValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str2, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str2, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str2, (String) obj);
                    } else {
                        String str3 = "Unsupported type for key:";
                        String valueOf = String.valueOf(str2);
                        zzaxz.zzeo(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                }
            }
            return bundle;
        } catch (JSONException e) {
            zzaxz.zzb("Failed to convert ad metadata to JSON.", e);
            return null;
        }
    }
}
