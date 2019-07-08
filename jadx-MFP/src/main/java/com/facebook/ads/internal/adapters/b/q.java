package com.facebook.ads.internal.adapters.b;

import android.text.TextUtils;
import com.facebook.ads.internal.adapters.b.e.b;
import com.facebook.ads.internal.adapters.b.m.a;
import com.facebook.ads.internal.o.c;
import com.facebook.appevents.UserDataStore;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class q extends a {
    private final String a;
    private final m b;
    private final e c;
    private final i d;
    private final b e;
    private final d f;
    private final j g;
    private final String h;
    private boolean i;

    private q(String str, m mVar, e eVar, i iVar, b bVar, d dVar, j jVar, String str2) {
        this.a = str;
        this.b = mVar;
        this.c = eVar;
        this.d = iVar;
        this.e = bVar;
        this.f = dVar;
        this.g = jVar;
        this.h = str2;
    }

    public static q a(JSONObject jSONObject) {
        a c2 = new a().a(jSONObject.optString("advertiser_name")).b(jSONObject.optJSONObject(InMobiNetworkValues.ICON) != null ? jSONObject.optJSONObject(InMobiNetworkValues.ICON).optString("url") : "").c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        m a2 = c2.d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).a();
        e a3 = new b().a(jSONObject.optString("title")).b(jSONObject.optString("subtitle")).c(jSONObject.optString("body")).d(jSONObject.optString("rating_value")).e(jSONObject.optString(Attributes.CATEGORY)).f(jSONObject.optString("destination_title")).g(jSONObject.optString("ad_creative_type")).a();
        i iVar = new i(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        JSONObject jSONObject2 = null;
        h a4 = h.a(optJSONObject2 != null ? optJSONObject2.optJSONObject(Extras.ORIENTATION_PORTRAIT) : null);
        if (optJSONObject2 != null) {
            jSONObject2 = optJSONObject2.optJSONObject(Extras.ORIENTATION_LANDSCAPE);
        }
        q qVar = new q(jSONObject.optString("request_id"), a2, a3, iVar, new b(a4, h.a(jSONObject2)), new a().a(jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL)).b(jSONObject.optJSONObject("image") != null ? jSONObject.optJSONObject("image").optString("url") : "").a(jSONObject.optInt("skippable_seconds")).b(jSONObject.optInt("video_duration_sec")).a(n.a(jSONObject)).a(), new j(c.a(jSONObject.optString("end_card_markup")), jSONObject.optString("activation_command"), a(jSONObject.optJSONArray("end_card_images"))), jSONObject.optString(UserDataStore.CITY));
        return qVar;
    }

    private static List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    public String a() {
        return this.h;
    }

    public void a(String str) {
        super.a(str);
        this.g.a(str);
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void c(String str) {
        this.f.a(str);
    }

    public String e() {
        return this.a;
    }

    public m f() {
        return this.b;
    }

    public e g() {
        return this.c;
    }

    public i h() {
        return this.d;
    }

    public b i() {
        return this.e;
    }

    public d j() {
        return this.f;
    }

    public j k() {
        return this.g;
    }

    public boolean l() {
        return this.i;
    }
}
