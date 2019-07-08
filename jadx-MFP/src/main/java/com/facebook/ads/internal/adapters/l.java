package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.w.b.k;
import com.facebook.appevents.UserDataStore;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l implements a {
    private final String a;
    private final String b;
    private final d c;
    private final Collection<String> d;
    private final Map<String, String> e;
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    private final String j;

    private l(String str, String str2, d dVar, Collection<String> collection, Map<String, String> map, String str3, int i2, int i3, int i4, String str4) {
        this.a = str;
        this.b = str2;
        this.c = dVar;
        this.d = collection;
        this.e = map;
        this.f = str3;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.j = str4;
    }

    public static l a(Bundle bundle) {
        l lVar = new l(c.a(bundle.getByteArray("markup")), null, d.NONE, null, null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString(UserDataStore.CITY));
        return lVar;
    }

    public static l a(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString("request_id");
        String a2 = k.a(jSONObject, UserDataStore.CITY);
        d a3 = d.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Collection a4 = e.a(jSONArray);
        JSONObject optJSONObject = jSONObject.optJSONObject(TtmlNode.TAG_METADATA);
        HashMap hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, optJSONObject.optString(str));
            }
        }
        l lVar = new l(optString, optString2, a3, a4, hashMap, optString3, hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0, hashMap.containsKey("viewability_check_interval") ? Integer.parseInt((String) hashMap.get("viewability_check_interval")) : 1000, hashMap.containsKey("skip_after_seconds") ? Integer.parseInt((String) hashMap.get("skip_after_seconds")) : 0, a2);
        return lVar;
    }

    public static l b(Intent intent) {
        l lVar = new l(c.a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), d.NONE, null, null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, 0), intent.getStringExtra(UserDataStore.CITY));
        return lVar;
    }

    public d a() {
        return this.c;
    }

    public void a(Intent intent) {
        intent.putExtra("markup", c.a(this.a));
        intent.putExtra("activation_command", this.b);
        intent.putExtra("request_id", this.f);
        intent.putExtra("viewability_check_initial_delay", this.g);
        intent.putExtra("viewability_check_interval", this.h);
        intent.putExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, this.i);
        intent.putExtra(UserDataStore.CITY, this.j);
    }

    public Collection<String> b() {
        return this.d;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public Map<String, String> e() {
        return this.e;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }

    public String getClientToken() {
        return this.j;
    }

    public Bundle h() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", c.a(this.a));
        bundle.putString("request_id", this.f);
        bundle.putInt("viewability_check_initial_delay", this.g);
        bundle.putInt("viewability_check_interval", this.h);
        bundle.putInt("skip_after_seconds", this.i);
        bundle.putString(UserDataStore.CITY, this.j);
        return bundle;
    }
}
