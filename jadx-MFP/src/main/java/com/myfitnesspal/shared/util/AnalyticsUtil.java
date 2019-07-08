package com.myfitnesspal.shared.util;

import android.content.res.Configuration;
import android.net.Uri;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.User;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsUtil {
    public static String getAnalyticsAttributeValueForBoolean(boolean z) {
        return z ? "yes" : "no";
    }

    public static Map<String, String> getUtmParamsFrom(Uri uri) {
        String[] strArr;
        HashMap hashMap = new HashMap();
        for (String str : new String[]{"utm_campaign", "utm_source", "utm_medium"}) {
            if (Strings.notEmpty(uri.getQueryParameter(str))) {
                hashMap.put(str, uri.getQueryParameter(str));
            }
        }
        return hashMap;
    }

    public static String getAppOpenedEventType(User user) {
        return user.isLoggedIn() ? Events.APP_OPENED_LOGGED_IN : Events.APP_OPENED_LOGGED_OUT;
    }

    public static String getOrientationForAnalytics(Configuration configuration) {
        return configuration.orientation == 1 ? Extras.ORIENTATION_PORTRAIT : Extras.ORIENTATION_LANDSCAPE;
    }
}
