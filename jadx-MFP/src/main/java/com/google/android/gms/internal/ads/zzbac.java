package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzbac {
    private static final zzbae<Map<String, ?>> zzeni = new zzbad();

    @NonNull
    public static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static JSONObject zza(JSONObject jSONObject, String... strArr) {
        int i = 0;
        while (true) {
            if (i >= strArr.length - 1) {
                break;
            } else if (jSONObject == null) {
                jSONObject = null;
                break;
            } else {
                jSONObject = jSONObject.optJSONObject(strArr[i]);
                i++;
            }
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONObject(strArr[strArr.length - 1]);
    }
}
