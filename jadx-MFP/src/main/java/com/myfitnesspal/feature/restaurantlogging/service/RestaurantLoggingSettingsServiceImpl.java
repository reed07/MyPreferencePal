package com.myfitnesspal.feature.restaurantlogging.service;

import com.uacf.core.preferences.KeyedSharedPreferences;

public class RestaurantLoggingSettingsServiceImpl implements RestaurantLoggingSettingsService {
    private static final String INACCURATE_MATCH_DIALOG_DISPLAYED = "inaccurate_match_dialog_displayed";
    private static final String MENU_ESTIMATES_DIALOG_DISPLAYED = "menu_estimates_dialog_displayed";
    private static final String NO_MATCH_DIALOG_DISPLAYED = "no_match_dialog_displayed";
    private static final String RESTAURANT_LOGGING_INTERSTITIAL_DISPLAYED = "restaurant_logging_interstitial_displayed";
    private final KeyedSharedPreferences prefs;

    public RestaurantLoggingSettingsServiceImpl(KeyedSharedPreferences keyedSharedPreferences) {
        this.prefs = keyedSharedPreferences;
    }

    public void setRestaurantLoggingInterstitialDisplayed(boolean z) {
        writeBoolean(RESTAURANT_LOGGING_INTERSTITIAL_DISPLAYED, z);
    }

    public boolean wasRestaurantLoggingInterstitialDisplayed() {
        return this.prefs.getBoolean(RESTAURANT_LOGGING_INTERSTITIAL_DISPLAYED, false);
    }

    public void setMenuEstimatesDialogDisplayed(boolean z) {
        writeBoolean(MENU_ESTIMATES_DIALOG_DISPLAYED, z);
    }

    public boolean wasMenuEstimatedDialogDisplayed() {
        return this.prefs.getBoolean(MENU_ESTIMATES_DIALOG_DISPLAYED, false);
    }

    public void setInaccurateMatchDialogDisplayed(boolean z) {
        writeBoolean(INACCURATE_MATCH_DIALOG_DISPLAYED, z);
    }

    public boolean wasInaccurateMatchDialogDisplayed() {
        return this.prefs.getBoolean(INACCURATE_MATCH_DIALOG_DISPLAYED, false);
    }

    public void setNoMatchDialogDisplayed(boolean z) {
        writeBoolean(NO_MATCH_DIALOG_DISPLAYED, z);
    }

    public boolean wasNoMatchDialogDisplayed() {
        return this.prefs.getBoolean(NO_MATCH_DIALOG_DISPLAYED, false);
    }

    private void writeBoolean(String str, boolean z) {
        this.prefs.edit().putBoolean(str, z).apply();
    }
}
