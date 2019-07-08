package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Locale;

public class VastFractionalProgressTracker extends VastTracker implements Serializable, Comparable<VastFractionalProgressTracker> {
    private static final long serialVersionUID = 0;
    private final float mFraction;

    public VastFractionalProgressTracker(@NonNull MessageType messageType, @NonNull String str, float f) {
        super(messageType, str);
        Preconditions.checkArgument(f >= BitmapDescriptorFactory.HUE_RED);
        this.mFraction = f;
    }

    public VastFractionalProgressTracker(@NonNull String str, float f) {
        this(MessageType.TRACKING_URL, str, f);
    }

    public float trackingFraction() {
        return this.mFraction;
    }

    public int compareTo(@NonNull VastFractionalProgressTracker vastFractionalProgressTracker) {
        return Double.compare((double) trackingFraction(), (double) vastFractionalProgressTracker.trackingFraction());
    }

    public String toString() {
        return String.format(Locale.US, "%2f: %s", new Object[]{Float.valueOf(this.mFraction), getContent()});
    }
}
