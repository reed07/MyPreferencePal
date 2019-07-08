package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzri;
import com.google.android.gms.internal.measurement.zzrk;
import com.google.android.gms.internal.measurement.zzrl;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzda {
    public static zzrk zzdv(String str) throws JSONException {
        zzp zzj = zzgj.zzj(zzh(new JSONObject(str)));
        zzrl zztb = zzrk.zztb();
        for (int i = 0; i < zzj.zzqk.length; i++) {
            zztb.zzc(zzri.zzsz().zzb(zzb.INSTANCE_NAME.toString(), zzj.zzqk[i]).zzb(zzb.FUNCTION.toString(), zzgj.zzef(zzt.zznm())).zzb(zzt.zznn(), zzj.zzql[i]).zzta());
        }
        return zztb.zztd();
    }

    @VisibleForTesting
    private static Object zzh(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, zzh(jSONObject.get(str)));
            }
            return hashMap;
        }
    }
}
