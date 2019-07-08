package com.facebook.ads.internal.n;

import android.content.Context;
import android.os.Build;
import com.facebook.ads.internal.w.h.a;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

class b {
    private static final String a = "b";
    private static final AtomicBoolean b = new AtomicBoolean();

    b() {
    }

    static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "is_emu", String.valueOf(Build.FINGERPRINT.contains(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT)));
        a(jSONObject, "apk_size", String.valueOf(b(context)));
        return jSONObject.toString();
    }

    private static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    private static long b(Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir).length();
        } catch (Exception e) {
            if (b.compareAndSet(false, true)) {
                a.b(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, com.facebook.ads.internal.w.h.b.C, e);
            }
            return -1;
        }
    }
}
