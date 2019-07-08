package com.facebook.ads.internal.n;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.f.a;
import com.facebook.ads.internal.w.h.b;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class c {
    @Nullable
    private static AtomicBoolean a;

    public static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            String a2 = a.a(context);
            if (a2 == null) {
                a2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            jSONObject.put("process_name", a2);
            jSONObject.put("is_ads_process", AdInternalSettings.d);
            jSONObject.put("client_supports", b(context.getApplicationContext()));
        } catch (Exception e) {
            com.facebook.ads.internal.w.h.a.b(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, b.D, e);
        }
        return jSONObject.toString();
    }

    public static boolean b(Context context) {
        AtomicBoolean atomicBoolean;
        if (AdInternalSettings.c) {
            return false;
        }
        AtomicBoolean atomicBoolean2 = a;
        if (atomicBoolean2 != null) {
            return atomicBoolean2.get();
        }
        Context applicationContext = context.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            Log.w(AudienceNetworkAds.TAG, "Multi-process support won't work because application Context is not Application instance.");
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ad, new Exception("ApplicationContext is not Application."));
            atomicBoolean = new AtomicBoolean(false);
        } else if (a.a || ((Application) applicationContext).getClass().equals(Application.class)) {
            a = new AtomicBoolean(true);
            return true;
        } else {
            Log.e(AudienceNetworkAds.TAG, "You are using custom Application class and don't call AudienceNetworkAds.isInAdsProcess(). Multi-process support will be disabled. Please call AudienceNetworkAds.isInAdsProcess() if you want to support multi-process mode.");
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ae, new Exception("No AudienceNetworkAds.isInAdsProcess() call."));
            atomicBoolean = new AtomicBoolean(false);
        }
        a = atomicBoolean;
        return false;
    }
}
