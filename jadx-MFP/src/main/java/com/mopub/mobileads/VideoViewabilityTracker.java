package com.mopub.mobileads;

import android.support.annotation.NonNull;

public class VideoViewabilityTracker extends VastTracker {
    private final int mPercentViewable;
    private final int mViewablePlaytimeMS;

    public VideoViewabilityTracker(int i, int i2, @NonNull String str) {
        super(str);
        this.mViewablePlaytimeMS = i;
        this.mPercentViewable = i2;
    }

    public int getViewablePlaytimeMS() {
        return this.mViewablePlaytimeMS;
    }

    public int getPercentViewable() {
        return this.mPercentViewable;
    }
}
