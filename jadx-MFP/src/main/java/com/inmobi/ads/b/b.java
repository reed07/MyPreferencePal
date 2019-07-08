package com.inmobi.ads.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.a;
import com.inmobi.ads.f;
import com.inmobi.ads.r;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.configs.g;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GMARequest */
public final class b {
    @NonNull
    public f a;
    @Nullable
    private List<a> b;

    b(@NonNull f fVar, @Nullable List<a> list) {
        this.a = fVar;
        this.b = list;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] a() throws com.inmobi.ads.a.b, JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put("h-user-agent", com.inmobi.commons.a.a.f());
        JSONArray jSONArray = new JSONArray();
        if (this.b != null) {
            g gVar = new g();
            com.inmobi.commons.core.configs.b.a().a((com.inmobi.commons.core.configs.a) gVar, (c) null);
            for (a aVar : this.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("impressionId", aVar.g);
                r rVar = this.a.k;
                float f = aVar.k;
                String a2 = com.inmobi.commons.core.utilities.a.b.a(String.valueOf(f), rVar.b, rVar.a, rVar.c, gVar.b, gVar.a);
                String str = "bid";
                if (a2 == null) {
                    a2 = "";
                }
                jSONObject.put(str, a2);
                JSONObject f2 = aVar.f();
                String str2 = "cachedAdData";
                if (f2 == null) {
                    f2 = new JSONObject();
                }
                jSONObject.put(str2, f2);
                jSONArray.put(jSONObject);
            }
        }
        hashMap.put("cachedAdInfos", jSONArray.toString());
        this.a.c(hashMap);
        this.a.a();
        if (this.a.x == 1) {
            return this.a.f().getBytes();
        }
        throw new com.inmobi.ads.a.b();
    }
}
