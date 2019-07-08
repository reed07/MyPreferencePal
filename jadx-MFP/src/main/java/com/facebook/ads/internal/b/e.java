package com.facebook.ads.internal.b;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.facebook.ads.AdSettings.IntegrationErrorMode;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.h.b;
import java.util.Locale;

public class e {
    public static IntegrationErrorMode a(Context context) {
        IntegrationErrorMode integrationErrorMode = (IntegrationErrorMode) AdInternalSettings.a.getSerializable("SRL_INTEGRATION_ERROR_MODE_KEY");
        if (integrationErrorMode == null) {
            integrationErrorMode = IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;
        }
        if (integrationErrorMode != IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE) {
            return integrationErrorMode;
        }
        return !((context.getApplicationInfo().flags & 2) != 0) ? IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE : integrationErrorMode;
    }

    @Nullable
    public static a a(Context context, Integer... numArr) {
        String str;
        Class cls;
        if (com.facebook.ads.internal.r.a.aa(context)) {
            return null;
        }
        IntegrationErrorMode a = a(context);
        String format = String.format(Locale.US, AdErrorType.MISSING_DEPENDENCIES_ERROR.getDefaultErrorMessage(), new Object[]{AudienceNetworkAds.TAG});
        switch (a) {
            case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
            case INTEGRATION_ERROR_CALLBACK_MODE:
                boolean z = true;
                for (Integer intValue : numArr) {
                    switch (intValue.intValue()) {
                        case 0:
                            str = "android.support.v4.content.LocalBroadcastManager";
                            cls = LocalBroadcastManager.class;
                            break;
                        case 1:
                            str = "android.support.v7.widget.RecyclerView";
                            cls = RecyclerView.class;
                            break;
                    }
                    try {
                        Class.forName(cls.getName());
                    } catch (Throwable unused) {
                        String str2 = AudienceNetworkAds.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Dependency not found: ");
                        sb.append(str);
                        Log.e(str2, sb.toString());
                        int i = b.r;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Missing dependency class: ");
                        sb2.append(str);
                        com.facebook.ads.internal.w.h.a.a(context, "api", i, new Exception(sb2.toString()));
                        z = false;
                    }
                }
                if (z) {
                    return null;
                }
                switch (a) {
                    case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(format);
                        sb3.append(". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                        throw new RuntimeException(sb3.toString());
                    case INTEGRATION_ERROR_CALLBACK_MODE:
                        a a2 = a.a(AdErrorType.MISSING_DEPENDENCIES_ERROR, format);
                        Log.e(AudienceNetworkAds.TAG, format);
                        com.facebook.ads.internal.w.h.a.a(context, "api", b.r, new Exception(format));
                        return a2;
                    default:
                        return null;
                }
            default:
                Log.e(AudienceNetworkAds.TAG, format);
                return null;
        }
    }
}
