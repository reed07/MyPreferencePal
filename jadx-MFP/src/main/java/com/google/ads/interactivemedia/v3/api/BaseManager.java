package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.player.AdProgressProvider;

/* compiled from: IMASDK */
public interface BaseManager extends AdProgressProvider {
    void addAdErrorListener(AdErrorListener adErrorListener);

    void addAdEventListener(AdEventListener adEventListener);

    void destroy();

    AdProgressInfo getAdProgressInfo();

    Ad getCurrentAd();

    void init();

    void init(AdsRenderingSettings adsRenderingSettings);

    boolean isCustomPlaybackUsed();

    void removeAdErrorListener(AdErrorListener adErrorListener);

    void removeAdEventListener(AdEventListener adEventListener);
}
