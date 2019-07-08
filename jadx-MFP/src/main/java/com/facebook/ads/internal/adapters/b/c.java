package com.facebook.ads.internal.adapters.b;

import com.google.logging.type.LogSeverity;
import com.myfitnesspal.shared.constants.Constants.SearchTabs;
import java.io.Serializable;
import org.json.JSONObject;

public class c implements Serializable {
    private static final long serialVersionUID = -1165646029762217510L;
    private final int a;
    private final int b;
    private final int c;
    private final boolean d;

    private c(int i, int i2, int i3, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = z;
    }

    public static c a(JSONObject jSONObject) {
        return new c(jSONObject.optInt("countdown_time_ms", SearchTabs.TAB_FREQUENT_FOODS), jSONObject.optInt("pulse_animation_duration_ms", LogSeverity.CRITICAL_VALUE), jSONObject.optInt("default_ad_index"), jSONObject.optBoolean("should_show_rating", false));
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public boolean d() {
        return this.d;
    }
}
