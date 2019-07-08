package com.myfitnesspal.feature.restaurantlogging.service;

public interface RestaurantLoggingSettingsService {
    void setInaccurateMatchDialogDisplayed(boolean z);

    void setMenuEstimatesDialogDisplayed(boolean z);

    void setNoMatchDialogDisplayed(boolean z);

    void setRestaurantLoggingInterstitialDisplayed(boolean z);

    boolean wasInaccurateMatchDialogDisplayed();

    boolean wasMenuEstimatedDialogDisplayed();

    boolean wasNoMatchDialogDisplayed();

    boolean wasRestaurantLoggingInterstitialDisplayed();
}
