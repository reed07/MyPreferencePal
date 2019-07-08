package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.internal.ahh;
import com.google.ads.interactivemedia.v3.internal.ahj;

/* compiled from: IMASDK */
public final class d implements AdPodInfo {
    public int adPosition = 1;
    public boolean isBumper = false;
    public double maxDuration = -1.0d;
    public int podIndex;
    public double timeOffset;
    public int totalAds = 1;

    public final int getTotalAds() {
        return this.totalAds;
    }

    public final int getAdPosition() {
        return this.adPosition;
    }

    public final boolean isBumper() {
        return this.isBumper;
    }

    public final double getMaxDuration() {
        return this.maxDuration;
    }

    public final int getPodIndex() {
        return this.podIndex;
    }

    public final double getTimeOffset() {
        return this.timeOffset;
    }

    public final int hashCode() {
        return ahj.a(this, new String[0]);
    }

    public final boolean equals(Object obj) {
        return ahh.a((Object) this, obj, new String[0]);
    }

    public final String toString() {
        int i = this.totalAds;
        int i2 = this.adPosition;
        boolean z = this.isBumper;
        double d = this.maxDuration;
        int i3 = this.podIndex;
        double d2 = this.timeOffset;
        StringBuilder sb = new StringBuilder(169);
        sb.append("AdPodInfo [totalAds=");
        sb.append(i);
        sb.append(", adPosition=");
        sb.append(i2);
        sb.append(", isBumper=");
        sb.append(z);
        sb.append(", maxDuration=");
        sb.append(d);
        sb.append(", podIndex=");
        sb.append(i3);
        sb.append(", timeOffset=");
        sb.append(d2);
        sb.append("]");
        return sb.toString();
    }
}
