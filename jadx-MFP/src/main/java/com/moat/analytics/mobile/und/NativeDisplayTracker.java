package com.moat.analytics.mobile.und;

import android.app.Activity;

public interface NativeDisplayTracker {

    public enum MoatUserInteractionType {
        TOUCH,
        CLICK
    }

    void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType);

    @Deprecated
    void setActivity(Activity activity);

    void startTracking();

    void stopTracking();
}
