package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.IntentActions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.mraid.MraidController.MraidListener;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.mraid.RewardedMraidController;

public class RewardedMraidActivity extends MraidActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    /* access modifiers changed from: private */
    @Nullable
    public RewardedMraidController mRewardedMraidController;

    public static void start(@NonNull Context context, @Nullable AdReport adReport, @Nullable String str, long j, int i, boolean z) {
        try {
            Intents.startActivity(context, createIntent(context, adReport, str, j, i, z));
        } catch (IntentNotResolvableException unused) {
            Log.d("RewardedMraidActivity", "RewardedMraidActivity.class not found. Did you declare RewardedMraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @Nullable String str, long j, int i, boolean z) {
        Intent intent = new Intent(context, RewardedMraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, str);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.REWARDED_AD_DURATION_KEY, i);
        intent.putExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, z);
        return intent;
    }

    public View getAdView() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (TextUtils.isEmpty(stringExtra)) {
            MoPubLog.w("RewardedMraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        } else if (getBroadcastIdentifier() == null) {
            MoPubLog.w("RewardedMraidActivity received a null broadcast id. Finishing the activity.");
            finish();
            return new View(this);
        } else {
            int intExtra = intent.getIntExtra(DataKeys.REWARDED_AD_DURATION_KEY, 30);
            final boolean booleanExtra = intent.getBooleanExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, false);
            RewardedMraidController rewardedMraidController = new RewardedMraidController(this, this.mAdReport, PlacementType.INTERSTITIAL, intExtra, getBroadcastIdentifier().longValue());
            this.mRewardedMraidController = rewardedMraidController;
            this.mRewardedMraidController.setDebugListener(this.mDebugListener);
            this.mRewardedMraidController.setMraidListener(new MraidListener() {
                public void onExpand() {
                }

                public void onLoaded(View view) {
                    RewardedMraidActivity.this.mRewardedMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
                }

                public void onFailedToLoad() {
                    MoPubLog.d("RewardedMraidActivity failed to load. Finishing the activity");
                    RewardedMraidActivity rewardedMraidActivity = RewardedMraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(rewardedMraidActivity, rewardedMraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
                    RewardedMraidActivity.this.finish();
                }

                public void onClose() {
                    RewardedMraidActivity.this.mRewardedMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
                    RewardedMraidActivity.this.finish();
                }

                public void onOpen() {
                    if (booleanExtra) {
                        RewardedMraidActivity.this.mRewardedMraidController.showPlayableCloseButton();
                    }
                    RewardedMraidActivity rewardedMraidActivity = RewardedMraidActivity.this;
                    EventForwardingBroadcastReceiver.broadcastAction(rewardedMraidActivity, rewardedMraidActivity.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
                }
            });
            this.mRewardedMraidController.fillContent(getBroadcastIdentifier(), stringExtra, null);
            return this.mRewardedMraidController.getAdContainer();
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.create(this, getCloseableLayout());
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.pause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.destroy();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController == null || rewardedMraidController.backButtonEnabled()) {
            super.onBackPressed();
        }
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        RewardedMraidController rewardedMraidController = this.mRewardedMraidController;
        if (rewardedMraidController != null) {
            rewardedMraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    public RewardedMraidController getRewardedMraidController() {
        return this.mRewardedMraidController;
    }
}
