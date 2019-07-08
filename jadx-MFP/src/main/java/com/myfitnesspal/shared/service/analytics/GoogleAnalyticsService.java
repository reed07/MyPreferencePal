package com.myfitnesspal.shared.service.analytics;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Map;

public class GoogleAnalyticsService implements AnalyticsService {
    private static boolean hasInitialized;
    private Tracker appTracker;
    final Lazy<ConfigService> configService;
    private final Context context;

    public boolean isEnabled() {
        return true;
    }

    public void reportEvent(String str) {
    }

    public void reportEvent(String str, int i) {
    }

    public void reportEvent(String str, Map<String, String> map) {
    }

    public void reportEvent(String str, Map<String, String> map, String str2) {
    }

    public void reportEvent(String str, Map<String, String> map, String str2, int i) {
    }

    public void reportExerciseLogged(String str, int i, String str2, int i2, String str3, int i3) {
    }

    public void reportExperimentStart(String str, String str2) {
    }

    public void reportFoodLookup(Map<String, String> map) {
    }

    public void reportInstall() {
    }

    public void reportLogin(String str) {
    }

    public void reportLogout(String str) {
    }

    public void reportRegistration() {
    }

    public void reportRequiredConsents(String str, int i, String[] strArr) {
    }

    public void reportSessionStart() {
    }

    public void reportUpgrade() {
    }

    public void restartSession() {
    }

    public void updateUserPremiumStatus(@NonNull String str) {
    }

    public GoogleAnalyticsService(Context context2, Lazy<ConfigService> lazy) {
        this.context = context2;
        this.configService = lazy;
    }

    public void initialize(Activity activity) {
        if (isEnabled() && !hasInitialized) {
            Ln.d("GOOGLE ANALYTICS: initialize", new Object[0]);
            initTracker();
        }
    }

    public void reportScreenView(String str) {
        if (isEnabled() && !isScreenViewDisabled() && Strings.notEmpty(str)) {
            Tracker appTracker2 = getAppTracker();
            appTracker2.setScreenName(str);
            appTracker2.send(new ScreenViewBuilder().build());
        }
    }

    public void reportScreenView(String str, Map<String, String> map) {
        reportScreenView(str);
    }

    public void reportUserId(String str) {
        if (isEnabled() && Strings.notEmpty(str)) {
            Tracker appTracker2 = getAppTracker();
            appTracker2.set("&uid", str);
            appTracker2.send(new EventBuilder().setCategory("UX").setAction("User Sign In").build());
        }
    }

    public Tracker getAppTracker() {
        if (this.appTracker == null) {
            initTracker();
        }
        return this.appTracker;
    }

    private void initTracker() {
        GoogleAnalytics instance = GoogleAnalytics.getInstance(this.context.getApplicationContext());
        instance.getLogger().setLogLevel(0);
        this.appTracker = instance.newTracker(this.context.getString(R.string.ga_trackingId));
        hasInitialized = true;
    }

    private boolean isScreenViewDisabled() {
        return ConfigUtils.isGoogleAnalyticsScreenViewDisabled((ConfigService) this.configService.get());
    }
}
