package io.uacf.consentservices.internal.service;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import java.util.Locale;

class ConsentLocaleProvider {
    ConsentLocaleProvider() {
    }

    static Locale getLocale(Context context, Locale locale) {
        if (locale != null) {
            return locale;
        }
        Configuration configuration = context.getResources().getConfiguration();
        if (VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    static String getFormattedLocale(Context context, Locale locale) {
        Locale locale2 = getLocale(context, locale);
        StringBuilder sb = new StringBuilder();
        sb.append(locale2.getLanguage());
        sb.append("_");
        sb.append(locale2.getCountry());
        return sb.toString();
    }
}
