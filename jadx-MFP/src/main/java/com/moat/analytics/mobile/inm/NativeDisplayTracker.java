package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.support.annotation.UiThread;

@UiThread
public interface NativeDisplayTracker {

    public enum MoatUserInteractionType {
        TOUCH,
        CLICK
    }

    void removeListener();

    void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType);

    @Deprecated
    void setActivity(Activity activity);

    void setListener(TrackerListener trackerListener);

    void startTracking();

    void stopTracking();
}
