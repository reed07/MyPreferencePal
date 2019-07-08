package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.WebViewCacheService.Config;
import com.mopub.mobileads.factories.CustomEventInterstitialFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventInterstitialAdapter implements CustomEventInterstitialListener {
    public static final int DEFAULT_INTERSTITIAL_TIMEOUT_DELAY = 30000;
    private long mBroadcastIdentifier;
    private Context mContext;
    private CustomEventInterstitial mCustomEventInterstitial;
    private CustomEventInterstitialAdapterListener mCustomEventInterstitialAdapterListener;
    private final Handler mHandler = new Handler();
    private boolean mInvalidated;
    private Map<String, Object> mLocalExtras;
    private final MoPubInterstitial mMoPubInterstitial;
    private Map<String, String> mServerExtras;
    private final Runnable mTimeout;

    interface CustomEventInterstitialAdapterListener {
        void onCustomEventInterstitialClicked();

        void onCustomEventInterstitialDismissed();

        void onCustomEventInterstitialFailed(MoPubErrorCode moPubErrorCode);

        void onCustomEventInterstitialImpression();

        void onCustomEventInterstitialLoaded();

        void onCustomEventInterstitialShown();
    }

    public CustomEventInterstitialAdapter(@NonNull MoPubInterstitial moPubInterstitial, @NonNull String str, @NonNull Map<String, String> map, long j, @Nullable AdReport adReport) {
        Preconditions.checkNotNull(map);
        this.mMoPubInterstitial = moPubInterstitial;
        this.mBroadcastIdentifier = j;
        this.mContext = this.mMoPubInterstitial.getActivity();
        this.mTimeout = new Runnable() {
            public void run() {
                MoPubLog.d("Third-party network timed out.");
                CustomEventInterstitialAdapter.this.onInterstitialFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventInterstitialAdapter.this.invalidate();
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to invoke custom event: ");
        sb.append(str);
        MoPubLog.d(sb.toString());
        try {
            this.mCustomEventInterstitial = CustomEventInterstitialFactory.create(str);
            this.mServerExtras = new TreeMap(map);
            this.mLocalExtras = this.mMoPubInterstitial.getLocalExtras();
            if (this.mMoPubInterstitial.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubInterstitial.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(j));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
        } catch (Exception unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Couldn't locate or instantiate custom event: ");
            sb2.append(str);
            sb2.append(".");
            MoPubLog.d(sb2.toString());
            this.mMoPubInterstitial.onCustomEventInterstitialFailed(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    /* access modifiers changed from: 0000 */
    public void loadInterstitial() {
        if (!isInvalidated() && this.mCustomEventInterstitial != null) {
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventInterstitial.loadInterstitial(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception e) {
                MoPubLog.d("Loading a custom event interstitial threw an exception.", e);
                onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void showInterstitial() {
        if (!isInvalidated()) {
            CustomEventInterstitial customEventInterstitial = this.mCustomEventInterstitial;
            if (customEventInterstitial != null) {
                try {
                    customEventInterstitial.showInterstitial();
                } catch (Exception e) {
                    MoPubLog.d("Showing a custom event interstitial threw an exception.", e);
                    onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void invalidate() {
        CustomEventInterstitial customEventInterstitial = this.mCustomEventInterstitial;
        if (customEventInterstitial != null) {
            try {
                customEventInterstitial.onInvalidate();
            } catch (Exception e) {
                MoPubLog.d("Invalidating a custom event interstitial threw an exception.", e);
            }
        }
        this.mCustomEventInterstitial = null;
        this.mContext = null;
        this.mServerExtras = null;
        this.mLocalExtras = null;
        this.mCustomEventInterstitialAdapterListener = null;
        Config popWebViewConfig = WebViewCacheService.popWebViewConfig(Long.valueOf(this.mBroadcastIdentifier));
        if (popWebViewConfig != null) {
            popWebViewConfig.getWebView().destroy();
        }
        this.mInvalidated = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isInvalidated() {
        return this.mInvalidated;
    }

    /* access modifiers changed from: 0000 */
    public void setAdapterListener(CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener) {
        this.mCustomEventInterstitialAdapterListener = customEventInterstitialAdapterListener;
    }

    /* access modifiers changed from: 0000 */
    public boolean isAutomaticImpressionAndClickTrackingEnabled() {
        CustomEventInterstitial customEventInterstitial = this.mCustomEventInterstitial;
        if (customEventInterstitial == null) {
            return true;
        }
        return customEventInterstitial.isAutomaticImpressionAndClickTrackingEnabled();
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        MoPubInterstitial moPubInterstitial = this.mMoPubInterstitial;
        if (moPubInterstitial == null) {
            return 30000;
        }
        return moPubInterstitial.getAdTimeoutDelay(30000).intValue();
    }

    public void onInterstitialLoaded() {
        if (!isInvalidated()) {
            cancelTimeout();
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialLoaded();
            }
        }
    }

    public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
        if (!isInvalidated() && this.mCustomEventInterstitialAdapterListener != null) {
            if (moPubErrorCode == null) {
                moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
            }
            cancelTimeout();
            this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialFailed(moPubErrorCode);
        }
    }

    public void onInterstitialShown() {
        if (!isInvalidated()) {
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialShown();
            }
        }
    }

    public void onInterstitialClicked() {
        if (!isInvalidated()) {
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialClicked();
            }
        }
    }

    public void onInterstitialImpression() {
        if (!isInvalidated()) {
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialImpression();
            }
        }
    }

    public void onLeaveApplication() {
        onInterstitialClicked();
    }

    public void onInterstitialDismissed() {
        if (!isInvalidated()) {
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialDismissed();
            }
        }
    }
}
