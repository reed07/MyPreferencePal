package com.inmobi.a;

import com.inmobi.a.b.a;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: IceWifiSample */
public class l {
    private static final String d = "l";
    a a;
    List<a> b;
    Map<String, String> c;
    private long e = Calendar.getInstance().getTimeInMillis();

    l() {
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.e);
            if (this.c != null && !this.c.isEmpty()) {
                for (Entry entry : this.c.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            if (this.a != null) {
                jSONObject.put("c-ap", this.a.a());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.b != null) {
                for (int i = 0; i < this.b.size(); i++) {
                    jSONArray.put(((a) this.b.get(i)).a());
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("v-ap", jSONArray);
                }
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
