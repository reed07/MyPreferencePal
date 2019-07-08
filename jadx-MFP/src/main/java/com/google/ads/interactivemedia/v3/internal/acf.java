package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdProgressInfo;

/* compiled from: IMASDK */
public final class acf implements AdProgressInfo {
    private final double a;
    private final double b;
    private final int c;
    private final int d;
    private final double e;

    acf(double d2, double d3, int i, int i2, double d4) {
        this.a = d2;
        this.b = d3;
        this.c = i;
        this.d = i2;
        this.e = d4;
    }

    public final double getCurrentTime() {
        return this.a;
    }

    public final double getDuration() {
        return this.b;
    }

    public final int getAdPosition() {
        return this.c;
    }

    public final int getTotalAds() {
        return this.d;
    }

    public final double getAdBreakDuration() {
        return this.e;
    }
}
