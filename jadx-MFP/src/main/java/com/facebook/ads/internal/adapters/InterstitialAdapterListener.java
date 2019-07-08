package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;

public interface InterstitialAdapterListener {
    void onInterstitialActivityDestroyed();

    void onInterstitialAdClicked(g gVar, String str, boolean z);

    void onInterstitialAdDismissed(g gVar);

    void onInterstitialAdDisplayed(g gVar);

    void onInterstitialAdLoaded(g gVar);

    void onInterstitialError(g gVar, AdError adError);

    void onInterstitialLoggingImpression(g gVar);
}
