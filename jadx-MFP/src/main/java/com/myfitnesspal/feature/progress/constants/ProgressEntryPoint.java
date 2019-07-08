package com.myfitnesspal.feature.progress.constants;

import com.facebook.internal.AnalyticsEvents;

public enum ProgressEntryPoint {
    Fab("FAB"),
    ProgressActivity("Progress Screen"),
    GoalsActivity("Goals Screen"),
    GalleryScreen("Gallery Screen"),
    Widget("Widget"),
    Reminder("Reminder"),
    HeroCard("Hero Card"),
    Interstitial("Interstitial"),
    MyInfo("MyInfo"),
    Unknown(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    
    private String analyticsValue;

    private ProgressEntryPoint(String str) {
        this.analyticsValue = str;
    }

    public String getAnalyticsValue() {
        return this.analyticsValue;
    }
}
