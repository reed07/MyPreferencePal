package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.UiElement;
import java.util.List;
import java.util.Set;

/* compiled from: IMASDK */
public final class acn implements AdsRenderingSettings {
    @xn(a = "bitrate")
    private int a = -1;
    @xn(a = "mimeTypes")
    private List<String> b = null;
    @xn(a = "uiElements")
    private Set<UiElement> c;
    @xn(a = "enablePreloading")
    private boolean d;
    @xn(a = "enableFocusSkipButton")
    private boolean e = true;
    @xn(a = "playAdsAfterTime")
    private double f = -1.0d;
    @xn(a = "disableUi")
    private boolean g = false;
    private boolean h = true;

    public final void setLoadVideoTimeout(int i) {
    }

    public final int getBitrateKbps() {
        return this.a;
    }

    public final void setBitrateKbps(int i) {
        this.a = i;
    }

    public final List<String> getMimeTypes() {
        return this.b;
    }

    public final void setMimeTypes(List<String> list) {
        this.b = list;
    }

    public final void setUiElements(Set<UiElement> set) {
        this.c = set;
    }

    public final boolean getEnablePreloading() {
        return this.d;
    }

    public final void setEnablePreloading(boolean z) {
        this.d = z;
    }

    public final boolean getFocusSkipButtonWhenAvailable() {
        return this.e;
    }

    public final void setFocusSkipButtonWhenAvailable(boolean z) {
        this.e = z;
    }

    public final String toString() {
        int i = this.a;
        String valueOf = String.valueOf(this.b);
        String valueOf2 = String.valueOf(this.c);
        boolean z = this.d;
        double d2 = this.f;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 134 + String.valueOf(valueOf2).length());
        sb.append("AdsRenderingSettings [bitrate=");
        sb.append(i);
        sb.append(", mimeTypes=");
        sb.append(valueOf);
        sb.append(", uiElements=");
        sb.append(valueOf2);
        sb.append(", enablePreloading=");
        sb.append(z);
        sb.append(", playAdsAfterTime=");
        sb.append(d2);
        sb.append("]");
        return sb.toString();
    }

    public final boolean isRenderCompanions() {
        return this.h;
    }

    public final void setRenderCompanions(boolean z) {
        this.h = z;
    }

    public final void setPlayAdsAfterTime(double d2) {
        this.f = d2;
    }

    public final void setDisableUi(boolean z) {
        this.g = z;
    }

    public final boolean getDisableUi() {
        return this.g;
    }
}
