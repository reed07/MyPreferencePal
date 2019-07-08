package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.CuePoint;

/* compiled from: IMASDK */
public final class adc implements CuePoint {
    private final double a;
    private final double b;
    private final boolean c;

    adc(double d, double d2, boolean z) {
        this.a = d;
        this.b = d2;
        this.c = z;
    }

    public final double getStartTime() {
        return this.a;
    }

    public final double getEndTime() {
        return this.b;
    }

    public final boolean isPlayed() {
        return this.c;
    }
}
