package com.mopub.mobileads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mopub.common.MediationSettings;
import com.mopub.common.util.Views;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.uacf.core.util.Ln;
import java.util.Map;

public class GooglePlayServicesBanner extends CustomEventBanner {
    public static final String AD_HEIGHT_KEY = "adHeight";
    public static final String AD_UNIT_ID_KEY = "adUnitID";
    public static final String AD_WIDTH_KEY = "adWidth";
    /* access modifiers changed from: private */
    public CustomEventBannerListener mBannerListener;
    /* access modifiers changed from: private */
    public AdView mGoogleAdView;

    private class AdViewListener extends AdListener {
        public void onAdClosed() {
        }

        public void onAdLeftApplication() {
        }

        private AdViewListener() {
        }

        public void onAdFailedToLoad(int i) {
            Log.d("MoPub", "Google Play Services banner ad failed to load.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerFailed(getMoPubErrorCode(i));
            }
        }

        public void onAdLoaded() {
            Log.d("MoPub", "Google Play Services banner ad loaded successfully. Showing ad...");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerLoaded(GooglePlayServicesBanner.this.mGoogleAdView);
            }
        }

        public void onAdOpened() {
            Log.d("MoPub", "Google Play Services banner ad clicked.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerClicked();
            }
        }

        private MoPubErrorCode getMoPubErrorCode(int i) {
            switch (i) {
                case 0:
                    return MoPubErrorCode.INTERNAL_ERROR;
                case 1:
                    return MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
                case 2:
                    return MoPubErrorCode.NO_CONNECTION;
                case 3:
                    return MoPubErrorCode.NO_FILL;
                default:
                    return MoPubErrorCode.UNSPECIFIED;
            }
        }
    }

    public static final class GooglePlayServicesMediationSettings implements MediationSettings {
        private static Bundle npaBundle;

        public GooglePlayServicesMediationSettings() {
        }

        public GooglePlayServicesMediationSettings(Bundle bundle) {
            npaBundle = bundle;
        }

        public void setNpaBundle(Bundle bundle) {
            npaBundle = bundle;
        }

        /* access modifiers changed from: private */
        public static Bundle getNpaBundle() {
            return npaBundle;
        }
    }

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(map2)) {
            String str = (String) map2.get(AD_UNIT_ID_KEY);
            int parseInt = Integer.parseInt((String) map2.get(AD_WIDTH_KEY));
            int parseInt2 = Integer.parseInt((String) map2.get(AD_HEIGHT_KEY));
            this.mGoogleAdView = new AdView(context);
            this.mGoogleAdView.setAdListener(new AdViewListener());
            this.mGoogleAdView.setAdUnitId(str);
            AdSize calculateAdSize = calculateAdSize(parseInt, parseInt2);
            if (calculateAdSize == null) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            this.mGoogleAdView.setAdSize(calculateAdSize);
            Builder builder = new Builder();
            builder.setRequestAgent("MoPub");
            forwardNpaIfSet(builder);
            try {
                this.mGoogleAdView.loadAd(builder.build());
            } catch (NoClassDefFoundError unused) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
            } catch (SecurityException e) {
                Ln.e(e);
                this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
            return;
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        Views.removeFromParent(this.mGoogleAdView);
        AdView adView = this.mGoogleAdView;
        if (adView != null) {
            adView.setAdListener(null);
            this.mGoogleAdView.destroy();
        }
    }

    private void forwardNpaIfSet(Builder builder) {
        if (GooglePlayServicesMediationSettings.getNpaBundle() != null && !GooglePlayServicesMediationSettings.getNpaBundle().isEmpty()) {
            builder.addNetworkExtrasBundle(AdMobAdapter.class, GooglePlayServicesMediationSettings.getNpaBundle());
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        try {
            Integer.parseInt((String) map.get(AD_WIDTH_KEY));
            Integer.parseInt((String) map.get(AD_HEIGHT_KEY));
            return map.containsKey(AD_UNIT_ID_KEY);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private AdSize calculateAdSize(int i, int i2) {
        if (i <= AdSize.BANNER.getWidth() && i2 <= AdSize.BANNER.getHeight()) {
            return AdSize.BANNER;
        }
        if (i <= AdSize.MEDIUM_RECTANGLE.getWidth() && i2 <= AdSize.MEDIUM_RECTANGLE.getHeight()) {
            return AdSize.MEDIUM_RECTANGLE;
        }
        if (i <= AdSize.FULL_BANNER.getWidth() && i2 <= AdSize.FULL_BANNER.getHeight()) {
            return AdSize.FULL_BANNER;
        }
        if (i > AdSize.LEADERBOARD.getWidth() || i2 > AdSize.LEADERBOARD.getHeight()) {
            return null;
        }
        return AdSize.LEADERBOARD;
    }
}
