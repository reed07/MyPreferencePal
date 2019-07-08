package com.inmobi.a.b;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WifiInfo */
public class a {
    private static final String e = "a";
    public long a;
    String b;
    int c;
    int d;

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", this.a);
            jSONObject.put("essid", this.b);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
