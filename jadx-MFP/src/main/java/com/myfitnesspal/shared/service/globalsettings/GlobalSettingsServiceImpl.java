package com.myfitnesspal.shared.service.globalsettings;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.uacf.core.util.Base64;
import com.uacf.core.util.Ln;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class GlobalSettingsServiceImpl implements GlobalSettingsService {
    private static final String APP_VERSION = "app_version";
    private static final String DONT_SHOW_OFFLINE_NOTIFICATION_FOR_COMPLETE_DIARYDAY = "dont_show_offline_notification_for_complete_diary_day";
    private static final String FCM_TOKEN = "fcm_token";
    private static final String GCM_REGISTRATION_ID = "gcm_registration_id";
    private static final String IS_GLOBAL_PREF_MIGRATION_COMPLETE = "is_global_pref_migration_complete";
    private static final String LAST_LOGGED_IN_USER = "last_logged_in_user";
    private static final String LAST_PREMIUM_AVAILABILITY = "last_premium_availability";
    private static final String LOGGED_IN_USERNAME = "logged_in_username";
    private static final String REQUEST_LOCALE_OVERRIDE = "request_locale_override";
    private static final String REQUIRES_PIN_CODE_ON_APP_ENTRY = "requires_pin_code_on_app_entry";
    private static final String SHOW_INVITATION_PROMOTION_VIEW = "show_invitation_promotion_view_";
    private static final String SHOW_NEWS_FEED_ON_HOME_TAB = "show_news_feed_on_home_tab_";
    private static final String USER_HAS_REVIEWED_APP = "user_has_reviewed_app";
    private static Map<String, String> upgradeDetails;
    private SharedPreferences sharedPreferences;

    public GlobalSettingsServiceImpl(SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
    }

    @Nullable
    public String getFCMToken() {
        return this.sharedPreferences.getString(FCM_TOKEN, null);
    }

    public String getEncodedFCMToken() {
        return getEncodedMessageToken(this.sharedPreferences.getString(FCM_TOKEN, null));
    }

    public void setFCMToken(String str) {
        writeString(FCM_TOKEN, str);
    }

    public void clearFCMToken() {
        clearKey(FCM_TOKEN);
    }

    public String getEncodedGCMToken() {
        return getEncodedMessageToken(this.sharedPreferences.getString(GCM_REGISTRATION_ID, null));
    }

    public void clearGCMToken() {
        clearKey(GCM_REGISTRATION_ID);
    }

    public void setAppVersion(String str) {
        writeString("app_version", str);
    }

    public String getAppVersion() {
        return this.sharedPreferences.getString("app_version", "");
    }

    public void setUserHasReviewedApp(boolean z) {
        writeBoolean(USER_HAS_REVIEWED_APP, z);
    }

    public boolean getUserHasReviewedApp() {
        return this.sharedPreferences.getBoolean(USER_HAS_REVIEWED_APP, false);
    }

    public void setDontShowOfflineNotificationForCompleteDiaryDay(boolean z) {
        writeBoolean(DONT_SHOW_OFFLINE_NOTIFICATION_FOR_COMPLETE_DIARYDAY, z);
    }

    public boolean getDontShowOfflineNotificationForCompleteDiaryDay() {
        return this.sharedPreferences.getBoolean(DONT_SHOW_OFFLINE_NOTIFICATION_FOR_COMPLETE_DIARYDAY, false);
    }

    public void setHasSeenUpgradeNotification(boolean z) {
        writeBoolean(upgradeNotificationSeenKey(), z);
    }

    public boolean getHasSeenUpgradeNotification() {
        return this.sharedPreferences.getBoolean(upgradeNotificationSeenKey(), false);
    }

    public void setRequiresPinCodeOnAppEntry(boolean z) {
        writeBoolean(REQUIRES_PIN_CODE_ON_APP_ENTRY, z);
    }

    public boolean getRequiresPinCodeOnAppEntry() {
        return this.sharedPreferences.getBoolean(REQUIRES_PIN_CODE_ON_APP_ENTRY, false);
    }

    public void setShowInvitationPromotinalScreen(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("show_invitation_promotion_view_");
        sb.append(str);
        writeBoolean(sb.toString(), z);
    }

    public boolean getShowInvitationPromotinalScreen(String str, boolean z) {
        if (z) {
            SharedPreferences sharedPreferences2 = this.sharedPreferences;
            StringBuilder sb = new StringBuilder();
            sb.append("show_invitation_promotion_view_");
            sb.append(str);
            if (sharedPreferences2.getBoolean(sb.toString(), true)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGlobalPrefMigrationComplete() {
        return this.sharedPreferences.getBoolean(IS_GLOBAL_PREF_MIGRATION_COMPLETE, false);
    }

    public void setGlobalPrefMigrationComplete() {
        writeBoolean(IS_GLOBAL_PREF_MIGRATION_COMPLETE, true);
    }

    public void setUpgradeDetails(Map<String, String> map) {
        upgradeDetails = map;
    }

    public String getCurrentLoggedInUserName() {
        return this.sharedPreferences.getString(LOGGED_IN_USERNAME, "");
    }

    public void setCurrentLoggedInUserName(String str) {
        writeString(LOGGED_IN_USERNAME, str);
    }

    public String getLastLoggedInUser() {
        return this.sharedPreferences.getString("last_logged_in_user", "");
    }

    public void setLastLoggedInUser(String str) {
        writeString("last_logged_in_user", str);
    }

    public Map<String, String> getUpgradeDetails() {
        return upgradeDetails;
    }

    public void setRequestLocaleOverride(String str) {
        writeString(REQUEST_LOCALE_OVERRIDE, str);
    }

    public String getRequestLocaleOverride() {
        return this.sharedPreferences.getString(REQUEST_LOCALE_OVERRIDE, "");
    }

    public String getLastPremiumAvailability(String str) {
        return this.sharedPreferences.getString(LAST_PREMIUM_AVAILABILITY, str);
    }

    public void setLastPremiumAvailability(String str) {
        writeString(LAST_PREMIUM_AVAILABILITY, str);
    }

    private String upgradeNotificationSeenKey() {
        String str = (String) upgradeDetails.get(GooglePlayConstants.BILLING_JSON_FIELD_TOKEN);
        StringBuilder sb = new StringBuilder();
        sb.append("upgrade_notification_");
        sb.append(str);
        sb.append("_seen");
        return sb.toString();
    }

    private void writeBoolean(String str, boolean z) {
        this.sharedPreferences.edit().putBoolean(str, z).apply();
    }

    private void writeString(String str, String str2) {
        this.sharedPreferences.edit().putString(str, str2).apply();
    }

    private void clearKey(String str) {
        this.sharedPreferences.edit().remove(str).apply();
    }

    @NotNull
    private String getEncodedMessageToken(@Nullable String str) {
        String str2 = "";
        if (str != null) {
            try {
                str2 = Base64.encodeToString(str.getBytes(), 2);
            } catch (Exception e) {
                Ln.e(e);
                return "";
            }
        }
        Ln.d("SYNC: uuid GCM = %s, %s", str, str2);
        return str2;
    }
}
