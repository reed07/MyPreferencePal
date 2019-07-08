package com.mopub.mraid;

import android.content.Context;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.RewardedMraidActivity;
import java.util.Map;

public class RewardedMraidInterstitial extends MraidInterstitial {
    private int mRewardedDuration;
    @Nullable
    private RewardedPlayableBroadcastReceiver mRewardedPlayableBroadcastReceiver;
    private boolean mShouldRewardOnClick;

    public interface RewardedMraidInterstitialListener extends CustomEventInterstitialListener {
        void onMraidComplete();
    }

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        if (customEventInterstitialListener instanceof RewardedMraidInterstitialListener) {
            this.mRewardedPlayableBroadcastReceiver = new RewardedPlayableBroadcastReceiver((RewardedMraidInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            RewardedPlayableBroadcastReceiver rewardedPlayableBroadcastReceiver = this.mRewardedPlayableBroadcastReceiver;
            rewardedPlayableBroadcastReceiver.register(rewardedPlayableBroadcastReceiver, context);
        }
        Object obj = map.get(DataKeys.REWARDED_AD_DURATION_KEY);
        this.mRewardedDuration = obj instanceof Integer ? ((Integer) obj).intValue() : 30;
        Object obj2 = map.get(DataKeys.SHOULD_REWARD_ON_CLICK_KEY);
        this.mShouldRewardOnClick = obj2 instanceof Boolean ? ((Boolean) obj2).booleanValue() : false;
    }

    public void showInterstitial() {
        RewardedMraidActivity.start(this.mContext, this.mAdReport, this.mHtmlData, this.mBroadcastIdentifier, this.mRewardedDuration, this.mShouldRewardOnClick);
    }

    public void onInvalidate() {
        super.onInvalidate();
        RewardedPlayableBroadcastReceiver rewardedPlayableBroadcastReceiver = this.mRewardedPlayableBroadcastReceiver;
        if (rewardedPlayableBroadcastReceiver != null) {
            rewardedPlayableBroadcastReceiver.unregister(rewardedPlayableBroadcastReceiver);
        }
    }
}
