package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

enum VideoTrackingEvent {
    START(TtmlNode.START),
    FIRST_QUARTILE("firstQuartile"),
    MIDPOINT("midpoint"),
    THIRD_QUARTILE("thirdQuartile"),
    COMPLETE("complete"),
    COMPANION_AD_VIEW("companionAdView"),
    COMPANION_AD_CLICK("companionAdClick"),
    UNKNOWN("");
    
    private final String name;

    private VideoTrackingEvent(String str) {
        this.name = str;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public static VideoTrackingEvent fromString(@Nullable String str) {
        VideoTrackingEvent[] values;
        if (str == null) {
            return UNKNOWN;
        }
        for (VideoTrackingEvent videoTrackingEvent : values()) {
            if (str.equals(videoTrackingEvent.getName())) {
                return videoTrackingEvent;
            }
        }
        return UNKNOWN;
    }
}
