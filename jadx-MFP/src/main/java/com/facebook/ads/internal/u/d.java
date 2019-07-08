package com.facebook.ads.internal.u;

import android.text.TextUtils;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private static d a = new d();

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            dVar = a;
        }
        return dVar;
    }

    private f a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        c cVar = new c(com.facebook.ads.internal.m.d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                cVar.a(new a(jSONObject3.optString("adapter"), jSONObject3.optString("data_model_type"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
            }
        }
        return new f(cVar, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    private g b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), new c(com.facebook.ads.internal.m.d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config")));
        } catch (JSONException unused) {
            return c(jSONObject);
        }
    }

    private g c(JSONObject jSONObject) {
        return new g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), null);
    }

    public e a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            char c = 65535;
            int hashCode = optString.hashCode();
            if (hashCode != 96432) {
                if (hashCode == 96784904 && optString.equals("error")) {
                    c = 1;
                }
            } else if (optString.equals("ads")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    return a(jSONObject);
                case 1:
                    return b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return c(optJSONObject);
                    }
                    break;
            }
        }
        return new e(a.UNKNOWN);
    }
}
