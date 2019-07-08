package com.myfitnesspal.feature.home.util;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;

public class HomeAnalyticsHelper {
    private static final String ATTRIBUTE_OPTION = "option";
    private static final String EVENT_FAB_OPTION_SELECTED = "fab_option_selected";
    private static final String EVENT_FAB_TAPPED_CLOSED = "fab_tapped_closed";
    private static final String EVENT_FAB_TAPPED_OPEN = "fab_tapped_open";
    private static final String VALUE_FAB_EXERCISE = "exercise";
    private static final String VALUE_FAB_FOOD = "food";
    private static final String VALUE_FAB_STATUS = "status";
    private static final String VALUE_FAB_WATER = "water";
    private static final String VALUE_FAB_WEIGHT = "weight";
    private final Lazy<AnalyticsService> analyticsService;

    private String getFABOptionValue(int i) {
        switch (i) {
            case 0:
                return "weight";
            case 1:
                return "exercise";
            case 2:
                return "food";
            case 3:
                return "water";
            case 4:
                return "status";
            default:
                return "status";
        }
    }

    public HomeAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportFABTappedOpen() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FAB_TAPPED_OPEN);
    }

    public void reportFABTappedClosed() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FAB_TAPPED_CLOSED);
    }

    public void reportFABOptionSelected(int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_FAB_OPTION_SELECTED, MapUtil.createMap("option", getFABOptionValue(i)));
    }
}
