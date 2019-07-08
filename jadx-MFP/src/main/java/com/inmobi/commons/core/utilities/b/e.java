package com.inmobi.commons.core.utilities.b;

import android.support.annotation.Nullable;
import com.inmobi.commons.core.configs.a;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.b.c;
import com.inmobi.commons.core.configs.h;
import com.inmobi.sdk.InMobiSdk;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GDPRInfo */
public final class e {
    private static final String a = "com.inmobi.commons.core.utilities.b.e";
    private static JSONObject b;
    /* access modifiers changed from: private */
    public static h c = new h();
    private static c d = new c() {
        public final void a(a aVar) {
            e.c = (h) aVar;
        }
    };

    private e() {
    }

    @Nullable
    public static JSONObject a() {
        return b;
    }

    public static void a(@Nullable JSONObject jSONObject) {
        b = jSONObject;
    }

    public static void c() {
        b.a().a((a) c, d);
    }

    public static int d() {
        return a(false);
    }

    public static int a(boolean z) {
        int i = c.d;
        int f = f();
        if (f == 1 || i == 1 || z) {
            return 1;
        }
        return (i == 0 || i != -1 || f == 0) ? 0 : 1;
    }

    private static int f() {
        JSONObject jSONObject = b;
        if (jSONObject == null || !jSONObject.has(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE)) {
            return -1;
        }
        try {
            return jSONObject.getBoolean(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE) ? 1 : 0;
        } catch (JSONException unused) {
            return -1;
        }
    }

    public static boolean b() {
        JSONObject jSONObject = b;
        if (jSONObject != null) {
            try {
                if (jSONObject.has(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE) && !jSONObject.getBoolean(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE)) {
                    return true;
                }
            } catch (JSONException unused) {
                return false;
            }
        }
        return false;
    }

    public static boolean e() {
        return a(false) != 0;
    }
}
