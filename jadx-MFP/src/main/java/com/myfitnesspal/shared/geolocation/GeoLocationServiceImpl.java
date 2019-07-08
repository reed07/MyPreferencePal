package com.myfitnesspal.shared.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import java.util.Locale;

public class GeoLocationServiceImpl extends SimpleAsyncServiceBase implements GeoLocationService {
    private static final String DEFAULT_COUNTRY_CODE = "UNKNOWN_COUNTRY_CODE";
    private static final String DEFAULT_LOCALE_CODE = "UNKNOWN_LOCALE_CODE";
    private static final String KEY_LAST_COUNTRY_CODE = "last_country_code";
    private static final String KEY_LAST_LOCALE_CODE = "last_locale_code";
    private final Context context;
    private String countryCode;
    private final LocaleChangeReceiver localeChangeReceiver;
    private String localeCode;
    private final SharedPreferences prefs;

    private class LocaleChangeReceiver extends BroadcastReceiver {
        public LocaleChangeReceiver(Context context) {
            context.registerReceiver(this, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
        }

        public void onReceive(Context context, Intent intent) {
            GeoLocationServiceImpl.this.refreshSync();
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "GeoLocationServiceImpl";
    }

    public GeoLocationServiceImpl(Context context2, SharedPreferences sharedPreferences) {
        this.context = context2.getApplicationContext();
        this.prefs = sharedPreferences;
        loadFromCache();
        this.localeChangeReceiver = new LocaleChangeReceiver(context2);
    }

    public void reset() {
        this.countryCode = DEFAULT_COUNTRY_CODE;
        this.localeCode = DEFAULT_LOCALE_CODE;
        saveToCache();
    }

    public void refresh(Function0 function0) {
        async(new Runnable(function0) {
            private final /* synthetic */ Function0 f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                GeoLocationServiceImpl.lambda$refresh$0(GeoLocationServiceImpl.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$refresh$0(GeoLocationServiceImpl geoLocationServiceImpl, Function0 function0) {
        try {
            geoLocationServiceImpl.refreshSync();
        } finally {
            geoLocationServiceImpl.postToMainThread(function0);
        }
    }

    public void refreshSync() {
        Locale locale = this.context.getResources().getConfiguration().locale;
        if (locale != null && Strings.notEmpty(locale.getCountry())) {
            this.countryCode = locale.getCountry();
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage());
            sb.append("_");
            sb.append(this.countryCode);
            this.localeCode = sb.toString();
            saveToCache();
        }
    }

    public String getCountryCode() {
        return Strings.toString(this.countryCode, DEFAULT_COUNTRY_CODE);
    }

    public String getLocaleCode() {
        return Strings.toString(this.localeCode, DEFAULT_LOCALE_CODE);
    }

    private void loadFromCache() {
        this.countryCode = this.prefs.getString(KEY_LAST_COUNTRY_CODE, DEFAULT_COUNTRY_CODE);
        this.localeCode = this.prefs.getString(KEY_LAST_LOCALE_CODE, DEFAULT_LOCALE_CODE);
    }

    private void saveToCache() {
        this.prefs.edit().putString(KEY_LAST_COUNTRY_CODE, this.countryCode).putString(KEY_LAST_LOCALE_CODE, this.localeCode).apply();
    }
}
