package com.google.ads.interactivemedia.v3.api;

import java.util.List;
import java.util.Set;

/* compiled from: IMASDK */
public interface AdsRenderingSettings {
    int getBitrateKbps();

    boolean getDisableUi();

    boolean getEnablePreloading();

    boolean getFocusSkipButtonWhenAvailable();

    List<String> getMimeTypes();

    boolean isRenderCompanions();

    void setBitrateKbps(int i);

    void setDisableUi(boolean z);

    void setEnablePreloading(boolean z);

    void setFocusSkipButtonWhenAvailable(boolean z);

    void setLoadVideoTimeout(int i);

    void setMimeTypes(List<String> list);

    void setPlayAdsAfterTime(double d);

    void setRenderCompanions(boolean z);

    void setUiElements(Set<UiElement> set);
}
