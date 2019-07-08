package com.facebook.ads.internal.w.i;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.util.Log;
import com.facebook.ads.internal.w.h.b;

public class a {
    private static final String a = "a";

    public static boolean a(Context context) {
        return b(context) && b.b(context);
    }

    public static boolean b(Context context) {
        if (context == null) {
            Log.v(a, "Invalid context in screen interactive check, assuming interactive.");
            return true;
        }
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Exception e) {
            Log.e(a, "Exception in screen interactive check, assuming interactive.", e);
            com.facebook.ads.internal.w.h.a.b(context, "risky", b.G, e);
            return true;
        }
    }
}
