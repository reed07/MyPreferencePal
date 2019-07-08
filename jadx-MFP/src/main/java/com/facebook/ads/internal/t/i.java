package com.facebook.ads.internal.t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class i {
    private final double a;
    private final double b;

    public i(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    @Nullable
    public static i a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double optDouble = jSONObject.optDouble("value", 0.0d);
        double optDouble2 = jSONObject.optDouble("scale", 0.0d);
        if (optDouble == 0.0d || optDouble2 == 0.0d) {
            return null;
        }
        return new i(optDouble, optDouble2);
    }

    public double a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }
}
