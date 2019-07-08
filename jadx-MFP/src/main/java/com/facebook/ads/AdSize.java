package com.facebook.ads;

import com.facebook.ads.internal.protocol.d;
import java.io.Serializable;

public class AdSize implements Serializable {
    @Deprecated
    public static final AdSize BANNER_320_50 = new AdSize(d.BANNER_320_50);
    public static final AdSize BANNER_HEIGHT_50 = new AdSize(d.BANNER_HEIGHT_50);
    public static final AdSize BANNER_HEIGHT_90 = new AdSize(d.BANNER_HEIGHT_90);
    public static final AdSize INTERSTITIAL = new AdSize(d.INTERSTITIAL);
    public static final AdSize RECTANGLE_HEIGHT_250 = new AdSize(d.RECTANGLE_HEIGHT_250);
    private final int a;
    private final int b;

    public AdSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private AdSize(d dVar) {
        this.a = dVar.a();
        this.b = dVar.b();
    }

    public static AdSize fromWidthAndHeight(int i, int i2) {
        AdSize adSize = INTERSTITIAL;
        if (adSize.b == i2 && adSize.a == i) {
            return adSize;
        }
        AdSize adSize2 = BANNER_320_50;
        if (adSize2.b == i2 && adSize2.a == i) {
            return adSize2;
        }
        AdSize adSize3 = BANNER_HEIGHT_50;
        if (adSize3.b == i2 && adSize3.a == i) {
            return adSize3;
        }
        AdSize adSize4 = BANNER_HEIGHT_90;
        if (adSize4.b == i2 && adSize4.a == i) {
            return adSize4;
        }
        AdSize adSize5 = RECTANGLE_HEIGHT_250;
        if (adSize5.b == i2 && adSize5.a == i) {
            return adSize5;
        }
        return null;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        if (this.a != adSize.a) {
            return false;
        }
        if (this.b != adSize.b) {
            z = false;
        }
        return z;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return (this.a * 31) + this.b;
    }

    public d toInternalAdSize() {
        return d.a(this.a, this.b);
    }
}
