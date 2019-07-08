package com.google.ads.interactivemedia.v3.api;

import java.util.List;

/* compiled from: IMASDK */
public interface AdsManager extends BaseManager {
    void clicked();

    void discardAdBreak();

    void focusSkipButton();

    List<Float> getAdCuePoints();

    void pause();

    @Deprecated
    void requestNextAdBreak();

    void resume();

    void skip();

    void start();
}
