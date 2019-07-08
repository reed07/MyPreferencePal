package com.myfitnesspal.feature.achievementinterstitialad.ui;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/ui/AchievementInterstitialAdListener;", "", "onInterstitialAdClosed", "", "onInterstitialAdLoadFailed", "errorCode", "", "onInterstitialAdLoaded", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementInterstitialAdListener.kt */
public interface AchievementInterstitialAdListener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* compiled from: AchievementInterstitialAdListener.kt */
    public static final class DefaultImpls {
        public static void onInterstitialAdClosed(AchievementInterstitialAdListener achievementInterstitialAdListener) {
        }

        public static void onInterstitialAdLoadFailed(AchievementInterstitialAdListener achievementInterstitialAdListener, int i) {
        }

        public static void onInterstitialAdLoaded(AchievementInterstitialAdListener achievementInterstitialAdListener) {
        }
    }

    void onInterstitialAdClosed();

    void onInterstitialAdLoadFailed(int i);

    void onInterstitialAdLoaded();
}
