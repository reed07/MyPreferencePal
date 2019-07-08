package com.facebook.ads.internal.adapters.b;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.c.f;
import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class n implements Serializable {
    private static final f a = f.PORTRAIT;
    private static final p b = p.FILE_PRECACHE;
    private static final long serialVersionUID = -5352540123250859603L;
    private final String c;
    private final String d;
    private final int e;
    private final String f;
    private final String g;
    private final int h;
    private final boolean i;
    private final boolean j;
    private final f k;
    private final p l;
    private String m;

    private n(String str, String str2, int i2, String str3, String str4, f fVar, int i3, boolean z, boolean z2, p pVar) {
        this.c = str;
        this.d = str2;
        this.e = i2;
        this.f = str3;
        this.g = str4;
        this.k = fVar;
        this.h = i3;
        this.i = z;
        this.j = z2;
        this.l = pVar;
    }

    @Nullable
    public static n a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("playable_data");
        if (optJSONObject == null) {
            return null;
        }
        p a2 = p.a(optJSONObject.optString("precaching_method", b.name()));
        String optString = optJSONObject.optString(ShareConstants.MEDIA_URI);
        String optString2 = optJSONObject.optString("intro_card_icon_url");
        int optInt = jSONObject.has("skippable_seconds") ? jSONObject.optInt("skippable_seconds") : jSONObject.optInt("unskippable_seconds", 0);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("generic_text");
        String optString3 = optJSONObject2 == null ? "Rewarded Play" : optJSONObject2.optString("rewarded_play_text", "Rewarded Play");
        JSONObject optJSONObject3 = optJSONObject.optJSONObject("generic_text");
        n nVar = new n(optString, optString2, optInt, optString3, optJSONObject3 == null ? "Play Store will automatically open in [secs]s" : optJSONObject3.optString("delay_click_text", "Play Store will automatically open in [secs]s"), f.a(optJSONObject.optInt("orientation", a.a())), optJSONObject.optInt("web_view_timeout_in_milliseconds", 5000), optJSONObject.optBoolean("enable_intro_card", true), optJSONObject.optBoolean("enable_end_card"), a2);
        return nVar;
    }

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.m = str;
    }

    public String b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public f f() {
        return this.k;
    }

    public int g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public boolean i() {
        return this.j;
    }

    public String j() {
        return this.m;
    }

    public p k() {
        return this.l;
    }
}
