package com.myfitnesspal.feature.achievementinterstitialad.ui;

import com.google.android.gms.ads.AdListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"com/myfitnesspal/feature/achievementinterstitialad/ui/AchievementInterstitialAd$preloadInterstitialAd$1", "Lcom/google/android/gms/ads/AdListener;", "onAdClosed", "", "onAdFailedToLoad", "errorCode", "", "onAdLeftApplication", "onAdLoaded", "onAdOpened", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementInterstitialAd.kt */
public final class AchievementInterstitialAd$preloadInterstitialAd$1 extends AdListener {
    final /* synthetic */ AchievementInterstitialAdListener $listener;
    final /* synthetic */ AchievementInterstitialAd this$0;

    public void onAdOpened() {
    }

    AchievementInterstitialAd$preloadInterstitialAd$1(AchievementInterstitialAd achievementInterstitialAd, AchievementInterstitialAdListener achievementInterstitialAdListener) {
        this.this$0 = achievementInterstitialAd;
        this.$listener = achievementInterstitialAdListener;
    }

    public void onAdLoaded() {
        this.this$0.setLastAdRequestedDelay(System.currentTimeMillis() - this.this$0.getLastAdRequestedAtTime());
        AchievementInterstitialAdListener achievementInterstitialAdListener = this.$listener;
        if (achievementInterstitialAdListener != null) {
            achievementInterstitialAdListener.onInterstitialAdLoaded();
        }
    }

    public void onAdFailedToLoad(int i) {
        AchievementInterstitialAdListener achievementInterstitialAdListener = this.$listener;
        if (achievementInterstitialAdListener != null) {
            achievementInterstitialAdListener.onInterstitialAdLoadFailed(i);
        }
    }

    public void onAdLeftApplication() {
        super.onAdLeftApplication();
        this.this$0.achievementAdAnalyticsEvents.reportAchievementAdClickedEvent(true);
    }

    public void onAdClosed() {
        super.onAdClosed();
        this.this$0.achievementAdAnalyticsEvents.reportAchievementAdClickedEvent(false);
        AchievementInterstitialAdListener achievementInterstitialAdListener = this.$listener;
        if (achievementInterstitialAdListener != null) {
            achievementInterstitialAdListener.onInterstitialAdClosed();
        }
        OnCloseAdListener onCloseAdListener = this.this$0.getOnCloseAdListener();
        if (onCloseAdListener != null) {
            onCloseAdListener.onAdClosed();
        }
    }
}
