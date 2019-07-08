package com.facebook.ads.internal.adapters.b;

import android.content.Context;
import com.facebook.ads.internal.adapters.b.m.a;
import com.facebook.ads.internal.w.h.b;
import com.facebook.appevents.UserDataStore;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k implements Serializable {
    private static final long serialVersionUID = 8751287062553772011L;
    private final m a;
    private final b b;
    private final List<l> c;
    private final int d;
    private final int e;
    private final String f;
    private final String g;
    private int h = 200;
    private String i;

    private k(m mVar, b bVar, List<l> list, String str, String str2, int i2, int i3) {
        this.a = mVar;
        this.b = bVar;
        this.c = list;
        this.f = str;
        this.g = str2;
        this.d = i2;
        this.e = i3;
    }

    public static k a(JSONObject jSONObject, Context context) {
        a c2 = new a().a(jSONObject.optString("title")).b(jSONObject.optJSONObject(InMobiNetworkValues.ICON) != null ? jSONObject.optJSONObject(InMobiNetworkValues.ICON).optString("url") : "").c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        m a2 = c2.d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).a();
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        JSONObject jSONObject2 = null;
        h a3 = h.a(optJSONObject2 != null ? optJSONObject2.optJSONObject(Extras.ORIENTATION_PORTRAIT) : null);
        if (optJSONObject2 != null) {
            jSONObject2 = optJSONObject2.optJSONObject(Extras.ORIENTATION_LANDSCAPE);
        }
        b bVar = new b(a3, h.a(jSONObject2));
        int optInt = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt2 = jSONObject.optInt("viewability_check_interval", 1000);
        String optString = jSONObject.optString(UserDataStore.CITY);
        String optString2 = jSONObject.optString("request_id", "");
        JSONArray optJSONArray = jSONObject.optJSONArray("carousel");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            arrayList.add(l.a(jSONObject));
        } else {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    arrayList.add(l.a(optJSONArray.getJSONObject(i2)));
                } catch (JSONException e2) {
                    com.facebook.ads.internal.w.h.a.b(context, "parsing", b.K, e2);
                    e2.printStackTrace();
                }
            }
        }
        k kVar = new k(a2, bVar, arrayList, optString, optString2, optInt, optInt2);
        return kVar;
    }

    public m a() {
        return this.a;
    }

    public void a(int i2) {
        this.h = i2;
    }

    public void a(String str) {
        this.i = str;
    }

    public b b() {
        return this.b;
    }

    public String c() {
        return this.f;
    }

    public List<l> d() {
        return Collections.unmodifiableList(this.c);
    }

    public String e() {
        return this.g;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }
}
