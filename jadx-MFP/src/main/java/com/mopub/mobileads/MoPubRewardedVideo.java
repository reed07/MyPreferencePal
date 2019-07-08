package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class MoPubRewardedVideo extends MoPubRewardedAd {
    @Nullable
    private RewardedVastVideoInterstitial mRewardedVastVideoInterstitial = new RewardedVastVideoInterstitial();

    private class MoPubRewardedVideoListener extends MoPubRewardedAdListener implements RewardedVideoInterstitialListener {
        public MoPubRewardedVideoListener() {
            super(MoPubRewardedVideo.class);
        }

        public void onVideoComplete() {
            if (MoPubRewardedVideo.this.getRewardedAdCurrencyName() == null) {
                MoPubLog.d("No rewarded video was loaded, so no reward is possible");
            } else {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedVideo.this.getAdNetworkId(), MoPubReward.success(MoPubRewardedVideo.this.getRewardedAdCurrencyName(), MoPubRewardedVideo.this.getRewardedAdCurrencyAmount()));
            }
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : "mopub_rewarded_video_id";
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVastVideoInterstitial rewardedVastVideoInterstitial = this.mRewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitial != null) {
            rewardedVastVideoInterstitial.onInvalidate();
        }
        this.mRewardedVastVideoInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedVastVideoInterstitial rewardedVastVideoInterstitial = this.mRewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitial == null) {
            MoPubLog.w("mRewardedVastVideoInterstitial is null. Has this class been invalidated?");
        } else {
            rewardedVastVideoInterstitial.loadInterstitial(activity, new MoPubRewardedVideoListener(), map, map2);
        }
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.mRewardedVastVideoInterstitial == null) {
            MoPubLog.d("Unable to show MoPub rewarded video");
            return;
        }
        MoPubLog.d("Showing MoPub rewarded video.");
        this.mRewardedVastVideoInterstitial.showInterstitial();
    }
}
