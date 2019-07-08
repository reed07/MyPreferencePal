package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mraid.RewardedMraidInterstitial;
import com.mopub.mraid.RewardedMraidInterstitial.RewardedMraidInterstitialListener;
import java.util.Map;

public class MoPubRewardedPlayable extends MoPubRewardedAd {
    @Nullable
    private RewardedMraidInterstitial mRewardedMraidInterstitial = new RewardedMraidInterstitial();

    private class MoPubRewardedPlayableListener extends MoPubRewardedAdListener implements RewardedMraidInterstitialListener {
        public MoPubRewardedPlayableListener() {
            super(MoPubRewardedPlayable.class);
        }

        public void onMraidComplete() {
            if (MoPubRewardedPlayable.this.getRewardedAdCurrencyName() == null) {
                MoPubLog.d("No rewarded video was loaded, so no reward is possible");
            } else {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedPlayable.this.getAdNetworkId(), MoPubReward.success(MoPubRewardedPlayable.this.getRewardedAdCurrencyName(), MoPubRewardedPlayable.this.getRewardedAdCurrencyAmount()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedMraidInterstitial rewardedMraidInterstitial = this.mRewardedMraidInterstitial;
        if (rewardedMraidInterstitial == null) {
            MoPubLog.w("mRewardedMraidInterstitial is null. Has this class been invalidated?");
        } else {
            rewardedMraidInterstitial.loadInterstitial(activity, new MoPubRewardedPlayableListener(), map, map2);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : "mopub_rewarded_playable_id";
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedMraidInterstitial rewardedMraidInterstitial = this.mRewardedMraidInterstitial;
        if (rewardedMraidInterstitial != null) {
            rewardedMraidInterstitial.onInvalidate();
        }
        this.mRewardedMraidInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.mRewardedMraidInterstitial == null) {
            MoPubLog.d("MoPub rewarded playable not loaded. Unable to show playable.");
            return;
        }
        MoPubLog.d("Showing MoPub rewarded playable.");
        this.mRewardedMraidInterstitial.showInterstitial();
    }
}
