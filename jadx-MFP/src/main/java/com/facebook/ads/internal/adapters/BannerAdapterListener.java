package com.facebook.ads.internal.adapters;

import android.view.View;
import com.facebook.ads.AdError;

public interface BannerAdapterListener {
    void onBannerAdClicked(e eVar);

    void onBannerAdLoaded(e eVar, View view);

    void onBannerError(e eVar, AdError adError);

    void onBannerLoggingImpression(e eVar);
}
