package com.facebook.ads.internal.adapters.b;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class g implements Serializable {
    private static final long serialVersionUID = 4559450202335985006L;
    private final String a;
    private final String b;

    private g() {
        this.a = "";
        this.b = "";
    }

    private g(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    static g a(@Nullable JSONObject jSONObject) {
        return jSONObject == null ? new g() : new g(jSONObject.optString("timer_text"), jSONObject.optString("title_text"));
    }

    public String a() {
        return this.b;
    }

    public String a(String str) {
        return TextUtils.isEmpty(str) ? this.a : this.a.replace("[fb_sec]", str);
    }
}
