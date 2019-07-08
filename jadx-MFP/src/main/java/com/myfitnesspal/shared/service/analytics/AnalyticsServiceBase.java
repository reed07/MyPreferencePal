package com.myfitnesspal.shared.service.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.feature.payments.model.MfpPaidSubscription;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Analytics.UtmValues;
import com.myfitnesspal.shared.service.install.UtmInformation;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public abstract class AnalyticsServiceBase extends SimpleAsyncServiceBase implements AnalyticsServiceWithHistory {
    private static final String ANALYTICS_VALUE_NO_SUBSCRIPTION = "no_subscription";
    protected final Lazy<AppSettings> appSettings;
    private final String carrierName;
    protected final Context context;
    private List<MfpEventHistoryItem> historyList = new ArrayList();
    private final BuildConfiguration runtimeConfiguration;
    protected final Lazy<Session> session;
    private final String sessionId;
    private final Lazy<SubscriptionService> subscriptionService;
    private final String versionName;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "AnalyticsServiceBase";
    }

    public void initialize(Activity activity) {
    }

    public boolean isEnabled() {
        return true;
    }

    public void reportFoodLookup(Map<String, String> map) {
    }

    public void reportUserId(String str) {
    }

    public void restartSession() {
    }

    public void updateUserPremiumStatus(@NonNull String str) {
    }

    protected AnalyticsServiceBase(Context context2, Lazy<AppSettings> lazy, String str, String str2, Lazy<Session> lazy2, Lazy<SubscriptionService> lazy3) {
        this.context = context2;
        this.appSettings = lazy;
        this.sessionId = str;
        this.carrierName = str2;
        this.runtimeConfiguration = BuildConfiguration.getBuildConfiguration();
        this.versionName = VersionUtils.getAppVersionName(context2);
        this.session = lazy2;
        this.subscriptionService = lazy3;
    }

    public void reportRegistration() {
        reportEvent("sign_up", getBuilderWithUtmInfo().put("user", Strings.toString(Long.valueOf(((Session) this.session.get()).getUser().getMasterDatabaseId()))).build());
    }

    public void reportInstall() {
        reportInstallOrUpgrade(Events.APP_INSTALL);
    }

    public void reportUpgrade() {
        reportInstallOrUpgrade(Events.APP_UPGRADE);
    }

    public void reportEvent(String str) {
        reportEvent(str, null);
    }

    public final void reportEvent(String str, int i) {
        reportEvent(str, null, null, 0);
    }

    public final void reportEvent(String str, Map<String, String> map) {
        reportEvent(str, map, null);
    }

    public final void reportEvent(String str, Map<String, String> map, String str2) {
        reportEvent(str, map, str2, 0);
    }

    public void reportEvent(String str, Map<String, String> map, String str2, int i) {
        if (isEnabled()) {
            if (this.runtimeConfiguration.isDebug() || this.runtimeConfiguration.isQABuild()) {
                List<MfpEventHistoryItem> list = this.historyList;
                MfpEventHistoryItem mfpEventHistoryItem = new MfpEventHistoryItem(str, Calendar.getInstance().getTime(), map, str2, i);
                list.add(0, mfpEventHistoryItem);
            }
        }
    }

    public void reportScreenView(String str, Map<String, String> map) {
        if (Strings.notEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(Screens.SCREEN_PREFIX);
            sb.append(str);
            reportEvent(sb.toString(), map);
        }
    }

    public void reportScreenView(String str) {
        reportScreenView(str, null);
    }

    public void reportSessionStart() {
        reportEvent(Events.SESSION_START, getSessionStartAttributeBuilder().build());
    }

    /* access modifiers changed from: protected */
    public Builder<String, String> getSessionStartAttributeBuilder() {
        String str = ANALYTICS_VALUE_NO_SUBSCRIPTION;
        MfpPaidSubscription mostRecentActiveSubscription = ((SubscriptionService) this.subscriptionService.get()).getMostRecentActiveSubscription();
        if (mostRecentActiveSubscription != null) {
            str = mostRecentActiveSubscription.getPaymentDetails().getProductId();
        }
        return getBuilderWithUtmInfo().put(Attributes.SESSION_ID, Strings.toString(this.sessionId)).put(Attributes.ADVERTISING_ID, getAdvertiserId()).put(Attributes.CARRIER, Strings.toString(this.carrierName)).put(Attributes.DEFAULT_ORIENTATION, AnalyticsUtil.getOrientationForAnalytics(this.context.getResources().getConfiguration())).put(Attributes.SUBSCRIPTION_STATUS, str).put(Attributes.OS_VERSION, VERSION.RELEASE);
    }

    private String getAdvertiserId() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.context.getApplicationContext()).getId();
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public void reportLogin(String str) {
        reportEvent(Events.LOGIN_SUCCESSFUL, getBuilder().put("auth_type", Strings.toString(str)).build());
    }

    public void reportLogout(String str) {
        reportEvent(Events.LOGOUT, getBuilder().put("auth_type", Strings.toString(str)).build());
    }

    public void reportExerciseLogged(String str, int i, String str2, int i2, String str3, int i3) {
        Map build = getBuilder().put("exercise_type", Strings.toString(str)).put("index", Strings.toString(Integer.valueOf(i))).put("list_type", Strings.toString(str2)).put("channel", Strings.toString(str3)).put(Attributes.ITEM_COUNT, Strings.toString(Integer.valueOf(i2))).put(Attributes.MINUTES_LOGGED, str == "cardio" ? Strings.toString(Integer.valueOf(i3 / 60)) : "0").build();
        reportEvent(Events.EXERCISE_LOGGED, build);
        Ln.d("TTL: logging food with attrs %s", Strings.toString(build));
    }

    public void reportExperimentStart(String str, String str2) {
        if (isEnabled() && !Strings.isEmpty(str) && !Strings.isEmpty(str2)) {
            reportEvent(Events.EXPERIMENT_START, getBuilder().put(Attributes.EXPERIMENT_NAME, Strings.toString(str)).put("variant", Strings.toString(str2)).build());
        }
    }

    private Builder<String, String> getBuilder() {
        return new Builder<>();
    }

    /* access modifiers changed from: protected */
    public Builder<String, String> getBuilderWithUtmInfo() {
        UtmInformation mostRecentUtmInformation = ((AppSettings) this.appSettings.get()).getMostRecentUtmInformation();
        return getBuilder().put("utm_source", Strings.notEmpty(mostRecentUtmInformation.getSource()) ? mostRecentUtmInformation.getSource() : UtmValues.ORGANIC).put("utm_medium", Strings.toString(mostRecentUtmInformation.getMedium())).put("utm_campaign", Strings.toString(mostRecentUtmInformation.getCampaign()));
    }

    /* access modifiers changed from: protected */
    public void reportInstallOrUpgrade(String str) {
        reportEvent(str, getBuilderWithUtmInfo().put("device", Build.DEVICE).put("manufacturer", Build.MANUFACTURER).put("app_version", this.versionName).build(), null);
    }

    /* access modifiers changed from: protected */
    public void addNonEmptyValuesTo(Map<String, String> map, Map<String, String> map2) {
        if (map2 != null) {
            for (String str : map2.keySet()) {
                String str2 = (String) map2.get(str);
                if (Strings.notEmpty(str2)) {
                    map.put(str, str2);
                }
            }
        }
    }

    public List<MfpEventHistoryItem> getEventHistory() {
        return this.historyList;
    }

    public void clearHistory() {
        this.historyList.clear();
    }
}
