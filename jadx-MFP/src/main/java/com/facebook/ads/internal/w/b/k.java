package com.facebook.ads.internal.w.b;

import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class k {
    public static String a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject.toString();
    }

    public static String a(JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(str, null);
        if ("null".equals(optString)) {
            return null;
        }
        return optString;
    }
}
