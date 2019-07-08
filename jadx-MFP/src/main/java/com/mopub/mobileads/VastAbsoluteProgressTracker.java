package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Locale;

public class VastAbsoluteProgressTracker extends VastTracker implements Serializable, Comparable<VastAbsoluteProgressTracker> {
    private static final long serialVersionUID = 0;
    private final int mTrackingMilliseconds;

    public VastAbsoluteProgressTracker(@NonNull MessageType messageType, @NonNull String str, int i) {
        super(messageType, str);
        Preconditions.checkArgument(i >= 0);
        this.mTrackingMilliseconds = i;
    }

    public VastAbsoluteProgressTracker(@NonNull String str, int i) {
        this(MessageType.TRACKING_URL, str, i);
    }

    public int getTrackingMilliseconds() {
        return this.mTrackingMilliseconds;
    }

    public int compareTo(@NonNull VastAbsoluteProgressTracker vastAbsoluteProgressTracker) {
        return getTrackingMilliseconds() - vastAbsoluteProgressTracker.getTrackingMilliseconds();
    }

    public String toString() {
        return String.format(Locale.US, "%dms: %s", new Object[]{Integer.valueOf(this.mTrackingMilliseconds), getContent()});
    }
}
