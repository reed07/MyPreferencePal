package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;

/* compiled from: IMASDK */
public interface AdsLoader {

    /* compiled from: IMASDK */
    public interface AdsLoadedListener {
        void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent);
    }

    void addAdErrorListener(AdErrorListener adErrorListener);

    void addAdsLoadedListener(AdsLoadedListener adsLoadedListener);

    void contentComplete();

    ImaSdkSettings getSettings();

    void removeAdErrorListener(AdErrorListener adErrorListener);

    void removeAdsLoadedListener(AdsLoadedListener adsLoadedListener);

    void requestAds(AdsRequest adsRequest);

    String requestStream(StreamRequest streamRequest);
}
