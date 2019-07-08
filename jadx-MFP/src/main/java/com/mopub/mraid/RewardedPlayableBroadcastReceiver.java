package com.mopub.mraid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.BaseBroadcastReceiver;
import com.mopub.mraid.RewardedMraidInterstitial.RewardedMraidInterstitialListener;

public class RewardedPlayableBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter sIntentFilter;
    @Nullable
    private RewardedMraidInterstitialListener mRewardedMraidListener;

    public RewardedPlayableBroadcastReceiver(@Nullable RewardedMraidInterstitialListener rewardedMraidInterstitialListener, long j) {
        super(j);
        this.mRewardedMraidListener = rewardedMraidInterstitialListener;
        getIntentFilter();
    }

    @NonNull
    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE);
        }
        return sIntentFilter;
    }

    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (this.mRewardedMraidListener != null && shouldConsumeBroadcast(intent)) {
            if (IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE.equals(intent.getAction())) {
                this.mRewardedMraidListener.onMraidComplete();
                unregister(this);
            }
        }
    }
}
