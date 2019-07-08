package com.facebook.ads.internal.adapters.b;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class f extends a {
    private g a;
    private c b;
    private List<q> c;
    private String d;
    private String e;
    private String f;

    private f(g gVar, Map<String, String> map, c cVar, List<q> list) {
        this.a = gVar;
        this.b = cVar;
        this.c = list;
        this.d = map.containsKey("background_color") ? (String) map.get("background_color") : "#FF23272F";
        this.f = map.containsKey("timer_text_color") ? (String) map.get("timer_text_color") : "#FFFFFF";
        this.e = map.containsKey("title_text_color") ? (String) map.get("title_text_color") : "#FFFFFF";
    }

    public static f a(JSONObject jSONObject) {
        g a2 = g.a(jSONObject.optJSONObject("translations"));
        JSONObject optJSONObject = jSONObject.optJSONObject("layout");
        HashMap hashMap = new HashMap();
        if (optJSONObject != null) {
            a(optJSONObject, hashMap, "background_color");
            a(optJSONObject, hashMap, "timer_text_color");
            a(optJSONObject, hashMap, "title_text_color");
        }
        c a3 = c.a(jSONObject.optJSONObject("ad_config"));
        JSONArray optJSONArray = jSONObject.optJSONArray("choosable_ads");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                q a4 = q.a(optJSONArray.optJSONObject(i));
                a4.a(true);
                arrayList.add(a4);
            }
        }
        return new f(a2, hashMap, a3, arrayList);
    }

    private static void a(JSONObject jSONObject, HashMap<String, String> hashMap, String str) {
        String optString = jSONObject.optString(str);
        if (!TextUtils.isEmpty(optString)) {
            hashMap.put(str, optString);
        }
    }

    @Nullable
    public String a() {
        if (this.c.isEmpty()) {
            return null;
        }
        return ((q) this.c.get(0)).a();
    }

    public void b(String str) {
        super.b(str);
        for (q b2 : this.c) {
            b2.b(str);
        }
    }

    public g e() {
        return this.a;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.e;
    }

    public c i() {
        return this.b;
    }

    public List<q> j() {
        return this.c;
    }
}
