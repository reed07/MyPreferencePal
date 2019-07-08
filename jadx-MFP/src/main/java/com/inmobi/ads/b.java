package com.inmobi.ads;

import com.facebook.internal.AnalyticsEvents;
import com.inmobi.commons.core.configs.a;
import com.inmobi.commons.core.configs.b.c;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdCacheStateReporter */
public class b {
    public static void a() {
        c cVar = new c();
        com.inmobi.commons.core.configs.b.a().a((a) cVar, (c) null);
        a(cVar);
        JSONArray jSONArray = new JSONArray();
        d.a();
        a(cVar, jSONArray, d.b());
        if (jSONArray.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("adsArray", jSONArray.toString());
            com.inmobi.commons.core.e.b.a().a("ads", cVar.l);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdCacheCachedAds", hashMap);
            return;
        }
        b.class.getName();
    }

    public static void b() {
        c cVar = new c();
        com.inmobi.commons.core.configs.b.a().a((a) cVar, (c) null);
        a(cVar);
    }

    private static void a(c cVar) {
        String[] strArr = {"banner", "int", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE};
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            d.a();
            a(cVar, jSONArray, d.a(str, cVar.a(str).d));
        }
        if (jSONArray.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("adsArray", jSONArray.toString());
            com.inmobi.commons.core.e.b.a().a("ads", cVar.l);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdCacheAdExpired", hashMap);
            return;
        }
        b.class.getName();
    }

    private static void a(c cVar, JSONArray jSONArray, List<a> list) {
        for (a aVar : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put("impId", aVar.g);
                jSONObject.put("plId", aVar.d);
                jSONObject.put("insTs", aVar.e);
                jSONObject.put("expTs", aVar.c());
                jSONObject.put("expiryDuration", aVar.f);
                jSONObject.put("configTTL", TimeUnit.SECONDS.toMillis(cVar.a(aVar.b).d));
                jSONObject.put("adType", aVar.b);
                jSONArray.put(jSONObject);
            } catch (JSONException unused) {
                b.class.getName();
            }
        }
    }

    public static void c() {
        c cVar = new c();
        com.inmobi.commons.core.configs.b.a().a((a) cVar, (c) null);
        if (cVar.o) {
            d.a();
            d.c();
        }
    }
}
