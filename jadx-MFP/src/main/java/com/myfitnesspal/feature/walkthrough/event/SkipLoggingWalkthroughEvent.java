package com.myfitnesspal.feature.walkthrough.event;

public class SkipLoggingWalkthroughEvent {
    private String skipAnalyticsEventName;

    public SkipLoggingWalkthroughEvent(String str) {
        this.skipAnalyticsEventName = str;
    }

    public String getSkipAnalyticsEventName() {
        return this.skipAnalyticsEventName;
    }
}
