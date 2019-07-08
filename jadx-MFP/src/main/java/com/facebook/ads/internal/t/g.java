package com.facebook.ads.internal.t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class g {
    private final String a;
    private final int b;
    private final int c;

    public g(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    @Nullable
    public static g a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("url");
        if (optString == null) {
            return null;
        }
        return new g(optString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}
