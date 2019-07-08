package com.facebook.ads.internal.m;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private final String a;
    private final String b;
    private final JSONObject c;
    private final Map<e, List<String>> d = new HashMap();

    public a(String str, String str2, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        this.a = str;
        this.b = str2;
        this.c = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            for (e put : e.values()) {
                this.d.put(put, new LinkedList());
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("type");
                    String string2 = jSONObject2.getString("url");
                    e valueOf = e.valueOf(string.toUpperCase(Locale.US));
                    if (valueOf != null && !TextUtils.isEmpty(string2)) {
                        ((List) this.d.get(valueOf)).add(string2);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public String a() {
        return this.a;
    }

    public List<String> a(e eVar) {
        return (List) this.d.get(eVar);
    }

    public String b() {
        return this.b;
    }

    public JSONObject c() {
        return this.c;
    }
}
