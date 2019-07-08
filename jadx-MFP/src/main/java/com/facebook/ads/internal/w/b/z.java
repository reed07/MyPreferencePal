package com.facebook.ads.internal.w.b;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import java.util.Map;

public class z {
    private static final String a = "z";

    public static boolean a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        String str3 = (String) map.get("kgr");
        return str != null && str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) && str2 != null && str2.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) && str3 != null && str3.equals("true");
    }

    public static boolean b(Map<String, String> map) {
        boolean z = false;
        if (map == null || map.isEmpty()) {
            Log.v(a, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        if ((str != null && str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) || (str2 != null && str2.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES))) {
            return false;
        }
        String str3 = (String) map.get("kgr");
        if (str3 != null && str3.equals("true")) {
            z = true;
        }
        return z;
    }
}
