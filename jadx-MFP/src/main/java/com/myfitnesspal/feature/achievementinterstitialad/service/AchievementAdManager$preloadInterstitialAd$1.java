package com.myfitnesspal.feature.achievementinterstitialad.service;

import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAdListener;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAdListener.DefaultImpls;
import com.myfitnesspal.feature.achievementinterstitialad.util.AdErrorConverter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "kotlin.jvm.PlatformType", "adTargeting", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: AchievementAdManager.kt */
final class AchievementAdManager$preloadInterstitialAd$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ AchievementAdManager this$0;

    AchievementAdManager$preloadInterstitialAd$1(AchievementAdManager achievementAdManager) {
        this.this$0 = achievementAdManager;
    }

    public final Single<Boolean> apply(@NotNull final AdTargetingDTO adTargetingDTO) {
        Intrinsics.checkParameterIsNotNull(adTargetingDTO, "adTargeting");
        return Single.create(new SingleOnSubscribe<T>(this) {
            final /* synthetic */ AchievementAdManager$preloadInterstitialAd$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void subscribe(@NotNull final SingleEmitter<Boolean> singleEmitter) {
                Intrinsics.checkParameterIsNotNull(singleEmitter, "it");
                ((AchievementAdAnalyticsEvents) this.this$0.this$0.achievementAdAnalyticsEvents.get()).reportAchievementAdRequestedEvent(AchievementAdDefines.ACHIEVEMENT_SHOWED_ON_NEWS_FEED);
                ((AchievementInterstitialAd) this.this$0.this$0.achievementInterstitialAd.get()).preloadInterstitialAd(adTargetingDTO, new AchievementInterstitialAdListener(this) {
                    final /* synthetic */ AnonymousClass1 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public void onInterstitialAdClosed() {
                        DefaultImpls.onInterstitialAdClosed(this);
                    }

                    public void onInterstitialAdLoaded() {
                        SingleEmitter singleEmitter = singleEmitter;
                        Intrinsics.checkExpressionValueIsNotNull(singleEmitter, "it");
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onSuccess(Boolean.valueOf(true));
                        }
                    }

                    public void onInterstitialAdLoadFailed(int i) {
                        SingleEmitter singleEmitter = singleEmitter;
                        Intrinsics.checkExpressionValueIsNotNull(singleEmitter, "it");
                        if (!singleEmitter.isDisposed()) {
                            ((AchievementAdAnalyticsEvents) this.this$0.this$0.this$0.achievementAdAnalyticsEvents.get()).reportAchievementAdNotLoaded(AdErrorConverter.Companion.errorMessageByErrorCode(i));
                            singleEmitter.onSuccess(Boolean.valueOf(false));
                        }
                    }
                });
            }
        });
    }
}
