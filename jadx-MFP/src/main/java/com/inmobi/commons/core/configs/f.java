package com.inmobi.commons.core.configs;

import com.inmobi.commons.a.a;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.uid.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConfigNetworkRequest */
final class f extends c {
    private static final String d = "f";
    int a;
    int b;
    Map<String, a> c;

    f(Map<String, a> map, d dVar, String str, int i, int i2, int i3) {
        this(map, dVar, str, i, i2, false, i3);
    }

    f(Map<String, a> map, d dVar, String str, int i, int i2, boolean z, int i3) {
        String str2 = HttpConstants.METHOD_POST;
        if (str == null || str.trim().length() == 0) {
            str = z ? "https://config.inmobi.cn/config-server/v1/config/secure.cfg" : "https://config.inmobi.com/config-server/v1/config/secure.cfg";
        }
        super(str2, str, dVar, i3);
        this.c = map;
        this.a = i;
        this.b = i2;
    }

    public final void a() {
        super.a();
        this.o.put("p", c());
        this.o.put("im-accid", a.e());
    }

    private String c() {
        c cVar = new c();
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.c.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("n", entry.getKey());
                jSONObject.put("t", cVar.b((String) entry.getKey()));
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
