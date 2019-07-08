package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

class RewardedVastVideoInterstitial extends VastVideoInterstitial {
    @Nullable
    private RewardedVideoBroadcastReceiver mRewardedVideoBroadcastReceiver;

    interface RewardedVideoInterstitialListener extends CustomEventInterstitialListener {
        void onVideoComplete();
    }

    RewardedVastVideoInterstitial() {
    }

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        if (customEventInterstitialListener instanceof RewardedVideoInterstitialListener) {
            this.mRewardedVideoBroadcastReceiver = new RewardedVideoBroadcastReceiver((RewardedVideoInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            RewardedVideoBroadcastReceiver rewardedVideoBroadcastReceiver = this.mRewardedVideoBroadcastReceiver;
            rewardedVideoBroadcastReceiver.register(rewardedVideoBroadcastReceiver, context);
        }
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig != null) {
            vastVideoConfig.setIsRewardedVideo(true);
        }
        super.onVastVideoConfigurationPrepared(vastVideoConfig);
    }

    public void onInvalidate() {
        super.onInvalidate();
        RewardedVideoBroadcastReceiver rewardedVideoBroadcastReceiver = this.mRewardedVideoBroadcastReceiver;
        if (rewardedVideoBroadcastReceiver != null) {
            rewardedVideoBroadcastReceiver.unregister(rewardedVideoBroadcastReceiver);
        }
    }
}
