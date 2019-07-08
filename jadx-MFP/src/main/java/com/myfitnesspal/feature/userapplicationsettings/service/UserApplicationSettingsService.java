package com.myfitnesspal.feature.userapplicationsettings.service;

import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSettingsResponse;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;

public interface UserApplicationSettingsService extends SyncItemHandler<UserApplicationSettingsResponse> {
    boolean arePremiumTrialsEnabled();

    boolean didSeeAlexaInterstitialForLogWater();

    boolean didSeeAlexaInterstitialForLogWeight();

    boolean isAutoplayNewsfeedAdsEnabled();

    boolean isAutoplayNewsfeedVideoSettingExists();

    boolean isAutoplayNewsfeedVideosEnabled();

    boolean isBottomBarOnboardingDismissed();

    boolean isMealGoalsBuyUpsellDismissed();

    void preCacheValues();

    void setAutoplayNewsfeedAdsEnabled(boolean z);

    void setAutoplayNewsfeedVideosEnabled(boolean z);

    void setBottomBarOnboardingDismissed(boolean z);

    void setDidSeeAlexaInterstitialForLogWater(boolean z);

    void setDidSeeAlexaInterstitialForLogWeight(boolean z);

    void setMealGoalsBuyUpsellDismissed(boolean z);

    void setPremiumTrialsEnabled(boolean z);
}
