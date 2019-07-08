package com.google.ads.interactivemedia.v3.api;

/* compiled from: IMASDK */
public interface AdErrorEvent {

    /* compiled from: IMASDK */
    public interface AdErrorListener {
        void onAdError(AdErrorEvent adErrorEvent);
    }

    AdError getError();

    Object getUserRequestContext();
}
