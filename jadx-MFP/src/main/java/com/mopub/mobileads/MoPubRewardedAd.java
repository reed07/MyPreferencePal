package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.AdTypeTranslator.CustomEventType;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public abstract class MoPubRewardedAd extends CustomEventRewardedAd {
    @Nullable
    protected String mAdUnitId;
    /* access modifiers changed from: private */
    public boolean mIsLoaded;
    private int mRewardedAdCurrencyAmount;
    @Nullable
    private String mRewardedAdCurrencyName;

    /* renamed from: com.mopub.mobileads.MoPubRewardedAd$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubErrorCode = new int[MoPubErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$mopub$mobileads$MoPubErrorCode[MoPubErrorCode.VIDEO_PLAYBACK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    protected class MoPubRewardedAdListener implements CustomEventInterstitialListener {
        @NonNull
        private final Runnable mAdExpiration;
        @NonNull
        final Class<? extends MoPubRewardedAd> mCustomEventClass;
        @NonNull
        private Handler mHandler = new Handler();

        public void onInterstitialImpression() {
        }

        public void onLeaveApplication() {
        }

        public MoPubRewardedAdListener(Class<? extends MoPubRewardedAd> cls) {
            Preconditions.checkNotNull(cls);
            this.mCustomEventClass = cls;
            this.mAdExpiration = new Runnable(MoPubRewardedAd.this) {
                public void run() {
                    MoPubLog.d("Expiring unused Rewarded ad.");
                    MoPubRewardedAdListener.this.onInterstitialFailed(MoPubErrorCode.EXPIRED);
                }
            };
        }

        public void onInterstitialLoaded() {
            MoPubRewardedAd.this.mIsLoaded = true;
            if (CustomEventType.isMoPubSpecific(this.mCustomEventClass.getName())) {
                this.mHandler.postDelayed(this.mAdExpiration, 14400000);
            }
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
            this.mHandler.removeCallbacks(this.mAdExpiration);
            if (AnonymousClass1.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[moPubErrorCode.ordinal()] != 1) {
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), moPubErrorCode);
            } else {
                MoPubRewardedVideoManager.onRewardedVideoPlaybackError(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId(), moPubErrorCode);
            }
        }

        public void onInterstitialShown() {
            this.mHandler.removeCallbacks(this.mAdExpiration);
            MoPubRewardedVideoManager.onRewardedVideoStarted(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialClicked() {
            MoPubRewardedVideoManager.onRewardedVideoClicked(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
        }

        public void onInterstitialDismissed() {
            MoPubRewardedVideoManager.onRewardedVideoClosed(this.mCustomEventClass, MoPubRewardedAd.this.getAdNetworkId());
            MoPubRewardedAd.this.onInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        return false;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LifecycleListener getLifecycleListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        Preconditions.checkNotNull(activity, "activity cannot be null");
        Preconditions.checkNotNull(map, "localExtras cannot be null");
        Preconditions.checkNotNull(map2, "serverExtras cannot be null");
        Object obj = map.get(DataKeys.REWARDED_AD_CURRENCY_NAME_KEY);
        if (obj instanceof String) {
            this.mRewardedAdCurrencyName = (String) obj;
        } else {
            MoPubLog.d("No currency name specified for rewarded video. Using the default name.");
            this.mRewardedAdCurrencyName = "";
        }
        Object obj2 = map.get(DataKeys.REWARDED_AD_CURRENCY_AMOUNT_STRING_KEY);
        if (obj2 instanceof String) {
            try {
                this.mRewardedAdCurrencyAmount = Integer.parseInt((String) obj2);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to convert currency amount: ");
                sb.append(obj2);
                sb.append(". Using the default reward amount: ");
                sb.append(0);
                MoPubLog.d(sb.toString());
                this.mRewardedAdCurrencyAmount = 0;
            }
        } else {
            MoPubLog.d("No currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        if (this.mRewardedAdCurrencyAmount < 0) {
            MoPubLog.d("Negative currency amount specified for rewarded ad. Using the default reward amount: 0");
            this.mRewardedAdCurrencyAmount = 0;
        }
        Object obj3 = map.get(DataKeys.AD_UNIT_ID_KEY);
        if (obj3 instanceof String) {
            this.mAdUnitId = (String) obj3;
        } else {
            MoPubLog.d("Unable to set ad unit for rewarded ad.");
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        this.mIsLoaded = false;
    }

    /* access modifiers changed from: protected */
    public boolean isReady() {
        return this.mIsLoaded;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String getRewardedAdCurrencyName() {
        return this.mRewardedAdCurrencyName;
    }

    /* access modifiers changed from: protected */
    public int getRewardedAdCurrencyAmount() {
        return this.mRewardedAdCurrencyAmount;
    }
}
