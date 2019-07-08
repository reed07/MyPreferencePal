package com.myfitnesspal.feature.dashboard.service;

import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class NutrientDashboardAnalyticsHelper {
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String EVENT_CUSTOM_NUTRIENT_DASHBOARD_SCREEN_VIEWED = "custom_nutrient_dashboard_screen_viewed";
    private static final String EVENT_NUTRIENT_DASHBOARD_CLICKED = "nutrient_dashboard_clicked";
    private static final String EVENT_NUTRIENT_DASHBOARD_OVERFLOW_CLICKED = "nutrient_dashboard_overflow_clicked";
    public static final String VALUE_DIARY = "diary";
    public static final String VALUE_HOME = "home";
    public static final String VALUE_SETTINGS = "settings";
    private Lazy<AnalyticsService> analyticsService;

    public NutrientDashboardAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportNutrientDashboardOverflowClicked() {
        HashMap hashMap = new HashMap();
        hashMap.put("source", Screens.HOME.toLowerCase());
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_NUTRIENT_DASHBOARD_OVERFLOW_CLICKED, (Map<String, String>) hashMap);
    }

    public void reportCustomNutrientDashboardScreenViewed(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_CUSTOM_NUTRIENT_DASHBOARD_SCREEN_VIEWED, (Map<String, String>) hashMap);
    }

    public void reportNutrientDashboardClicked(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("source", str);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_NUTRIENT_DASHBOARD_CLICKED, (Map<String, String>) hashMap);
    }
}
