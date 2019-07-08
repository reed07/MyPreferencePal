package com.facebook.ads.internal.w.i;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import com.facebook.ads.internal.w.b.z;
import com.facebook.ads.internal.w.h.a;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static final String a = "b";

    public static Map<String, String> a(Context context) {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        if (context == null) {
            Log.v(a, "Null context in window interactive check.");
            return hashMap;
        }
        try {
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            hashMap.put("kgr", String.valueOf(keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode()));
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    int i = window.getAttributes().flags;
                    hashMap.put("wt", Integer.toString(window.getAttributes().type));
                    hashMap.put("wfdkg", (4194304 & i) > 0 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
                    hashMap.put("wfswl", (524288 & i) > 0 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : "0");
                    return hashMap;
                }
                str = a;
                str2 = "Invalid window in window interactive check, assuming interactive.";
            } else {
                str = a;
                str2 = "Invalid Activity context in window interactive check, assuming interactive.";
            }
            Log.v(str, str2);
        } catch (Exception e) {
            Log.e(a, "Exception in window info check", e);
            a.b(context, "risky", com.facebook.ads.internal.w.h.b.H, e);
        }
        return hashMap;
    }

    public static boolean b(Context context) {
        return !z.b(a(context));
    }
}
