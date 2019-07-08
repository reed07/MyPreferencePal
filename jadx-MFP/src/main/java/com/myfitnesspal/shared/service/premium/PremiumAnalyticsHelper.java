package com.myfitnesspal.shared.service.premium;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;

public class PremiumAnalyticsHelper {
    private static final String ATTRIBUTE_SETTING = "setting";
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String EVENT_GOALS_SCREEN_PREMIUM_CTA_VIEWED = "goals_screen_premium_cta_viewed";
    private static final String EVENT_PREMIUM_MEAL_MACRO_SETTING_TOGGLED = "premium_meal_macro_setting_toggled";
    private static final String EVENT_PROFILE_SCREEN_PREMIUM_CTA_VIEWED = "profile_screen_premium_cta_viewed";
    private static final String EVENT_SETTINGS_SCREEN_PREMIUM_CTA_VIEWED = "settings_screen_premium_cta_viewed";
    private static final String VALUE_DIARY_SETTINGS = "diary_settings";
    private static final String VALUE_GOALS_SCREEN = "goals_screen";
    private static final String VALUE_OFF = "off";
    private static final String VALUE_ON = "on";
    private final Lazy<AnalyticsService> analyticsService;

    public PremiumAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportMealMacroSettingToggledFromDiarySettings(boolean z) {
        reportMealMacroSettingToggled(z, "diary_settings");
    }

    public void reportMealMacroSettingToggledFromGoalsScreen(boolean z) {
        reportMealMacroSettingToggled(z, VALUE_GOALS_SCREEN);
    }

    public void reportGoalsScreenPremiumCtaViewed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_GOALS_SCREEN_PREMIUM_CTA_VIEWED);
    }

    public void reportSettingsScreenPremiumCtaViewed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_SETTINGS_SCREEN_PREMIUM_CTA_VIEWED);
    }

    public void reportProfileScreenPremiumCtaViewed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_PROFILE_SCREEN_PREMIUM_CTA_VIEWED);
    }

    private void reportMealMacroSettingToggled(boolean z, String str) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str2 = EVENT_PREMIUM_MEAL_MACRO_SETTING_TOGGLED;
        String[] strArr = new String[4];
        strArr[0] = "setting";
        strArr[1] = z ? "on" : "off";
        strArr[2] = "source";
        strArr[3] = str;
        analyticsService2.reportEvent(str2, MapUtil.createMap(strArr));
    }
}
