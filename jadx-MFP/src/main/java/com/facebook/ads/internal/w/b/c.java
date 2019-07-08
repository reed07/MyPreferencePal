package com.facebook.ads.internal.w.b;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;

public class c {
    public static void a() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, String str) {
        if (AdInternalSettings.isTestMode(context)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" (displayed for test ads only)");
            Log.d("FBAudienceNetworkLog", sb.toString());
        }
    }
}
