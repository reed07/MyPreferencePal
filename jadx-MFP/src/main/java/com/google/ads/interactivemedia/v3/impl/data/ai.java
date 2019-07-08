package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
public abstract class ai {
    public abstract ah build();

    public abstract ai volume(float f);

    public ai volumePercentage(int i) {
        return volume(((float) i) / 100.0f);
    }
}
