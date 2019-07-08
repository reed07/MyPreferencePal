package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.android.R;

public enum Setting {
    Profile(R.string.settings_title_profile),
    DiarySettings(R.string.settings_title_diary),
    PrivacyPrefs(R.string.privacy_pref_title),
    MyExercises(R.string.myExercises),
    WeeklyNutrition(R.string.settings_title_weekly_nutrition),
    NotificationSettings(R.string.notification_settings),
    PremiumSubscription(R.string.premium_subscription),
    Logout(R.string.logout_title),
    PremiumCta(-1);
    
    private final int stringResId;

    private Setting(int i) {
        this.stringResId = i;
    }

    public int getStringResId() {
        return this.stringResId;
    }
}
