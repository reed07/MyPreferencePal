package com.inmobi.a;

import com.inmobi.commons.core.utilities.b.h;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: IceSample */
public class k {
    private static final String d = "k";
    Map<String, Object> a;
    h b;
    List<l> c;
    private long e = Calendar.getInstance().getTimeInMillis();

    k() {
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.e);
            if (this.a != null && !this.a.isEmpty()) {
                jSONObject.put("l", new JSONObject(this.a));
            }
            if (this.b != null) {
                jSONObject.put("s", this.b.b());
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.c.size(); i++) {
                jSONArray.put(((l) this.c.get(i)).a());
            }
            if (jSONArray.length() > 0) {
                jSONObject.put("w", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
