package com.myfitnesspal.shared.service.globalsettings;

import android.support.annotation.Nullable;
import java.util.Map;

public interface GlobalSettingsService {
    void clearFCMToken();

    void clearGCMToken();

    String getAppVersion();

    String getCurrentLoggedInUserName();

    boolean getDontShowOfflineNotificationForCompleteDiaryDay();

    String getEncodedFCMToken();

    String getEncodedGCMToken();

    @Nullable
    String getFCMToken();

    boolean getHasSeenUpgradeNotification();

    String getLastLoggedInUser();

    String getLastPremiumAvailability(String str);

    String getRequestLocaleOverride();

    boolean getRequiresPinCodeOnAppEntry();

    boolean getShowInvitationPromotinalScreen(String str, boolean z);

    Map<String, String> getUpgradeDetails();

    boolean getUserHasReviewedApp();

    boolean isGlobalPrefMigrationComplete();

    void setAppVersion(String str);

    void setCurrentLoggedInUserName(String str);

    void setDontShowOfflineNotificationForCompleteDiaryDay(boolean z);

    void setFCMToken(String str);

    void setGlobalPrefMigrationComplete();

    void setHasSeenUpgradeNotification(boolean z);

    void setLastLoggedInUser(String str);

    void setLastPremiumAvailability(String str);

    void setRequestLocaleOverride(String str);

    void setRequiresPinCodeOnAppEntry(boolean z);

    void setShowInvitationPromotinalScreen(String str, boolean z);

    void setUpgradeDetails(Map<String, String> map);

    void setUserHasReviewedApp(boolean z);
}
