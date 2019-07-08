package com.myfitnesspal.feature.achievementinterstitialad.ui;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.myfitnesspal.feature.achievementinterstitialad.constants.AchievementAdDefines;
import com.myfitnesspal.feature.achievementinterstitialad.dto.AdTargetingDTO;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdAnalyticsEvents;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;
import com.uacf.core.util.Ln;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0019J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcom/myfitnesspal/feature/achievementinterstitialad/ui/AchievementInterstitialAd;", "", "context", "Landroid/content/Context;", "achievementAdAnalyticsEvents", "Lcom/myfitnesspal/feature/achievementinterstitialad/service/AchievementAdAnalyticsEvents;", "(Landroid/content/Context;Lcom/myfitnesspal/feature/achievementinterstitialad/service/AchievementAdAnalyticsEvents;)V", "interstitialAd", "Lcom/google/android/gms/ads/doubleclick/PublisherInterstitialAd;", "lastAdRequestedAtTime", "", "getLastAdRequestedAtTime", "()J", "setLastAdRequestedAtTime", "(J)V", "lastAdRequestedDelay", "getLastAdRequestedDelay", "setLastAdRequestedDelay", "onCloseAdListener", "Lcom/myfitnesspal/feature/achievementinterstitialad/ui/OnCloseAdListener;", "getOnCloseAdListener", "()Lcom/myfitnesspal/feature/achievementinterstitialad/ui/OnCloseAdListener;", "setOnCloseAdListener", "(Lcom/myfitnesspal/feature/achievementinterstitialad/ui/OnCloseAdListener;)V", "preloadInterstitialAd", "", "adTargeting", "Lcom/myfitnesspal/feature/achievementinterstitialad/dto/AdTargetingDTO;", "listener", "Lcom/myfitnesspal/feature/achievementinterstitialad/ui/AchievementInterstitialAdListener;", "resetLoadedAd", "showInterstitialAd", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AchievementInterstitialAd.kt */
public class AchievementInterstitialAd {
    /* access modifiers changed from: private */
    public final AchievementAdAnalyticsEvents achievementAdAnalyticsEvents;
    private final Context context;
    private PublisherInterstitialAd interstitialAd = new PublisherInterstitialAd(this.context);
    private long lastAdRequestedAtTime;
    private long lastAdRequestedDelay;
    @Nullable
    private OnCloseAdListener onCloseAdListener;

    public AchievementInterstitialAd(@NotNull Context context2, @NotNull AchievementAdAnalyticsEvents achievementAdAnalyticsEvents2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(achievementAdAnalyticsEvents2, "achievementAdAnalyticsEvents");
        this.context = context2;
        this.achievementAdAnalyticsEvents = achievementAdAnalyticsEvents2;
    }

    public final long getLastAdRequestedAtTime() {
        return this.lastAdRequestedAtTime;
    }

    public final void setLastAdRequestedAtTime(long j) {
        this.lastAdRequestedAtTime = j;
    }

    public final long getLastAdRequestedDelay() {
        return this.lastAdRequestedDelay;
    }

    public final void setLastAdRequestedDelay(long j) {
        this.lastAdRequestedDelay = j;
    }

    @Nullable
    public final OnCloseAdListener getOnCloseAdListener() {
        return this.onCloseAdListener;
    }

    public final void setOnCloseAdListener(@Nullable OnCloseAdListener onCloseAdListener2) {
        this.onCloseAdListener = onCloseAdListener2;
    }

    public static /* synthetic */ void preloadInterstitialAd$default(AchievementInterstitialAd achievementInterstitialAd, AdTargetingDTO adTargetingDTO, AchievementInterstitialAdListener achievementInterstitialAdListener, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                achievementInterstitialAdListener = null;
            }
            achievementInterstitialAd.preloadInterstitialAd(adTargetingDTO, achievementInterstitialAdListener);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: preloadInterstitialAd");
    }

    public final void preloadInterstitialAd(@NotNull AdTargetingDTO adTargetingDTO, @Nullable AchievementInterstitialAdListener achievementInterstitialAdListener) {
        Intrinsics.checkParameterIsNotNull(adTargetingDTO, "adTargeting");
        Builder builder = new Builder();
        builder.addCustomTargeting(AchievementAdDefines.TARGETING_ACHIEVEMENT, adTargetingDTO.getAchievementDay());
        builder.addCustomTargeting(AchievementAdDefines.TARGETING_PREMIUM, adTargetingDTO.getPremiumUser());
        builder.addCustomTargeting("dkw", adTargetingDTO.getGender());
        builder.addCustomTargeting("oex", adTargetingDTO.getAge());
        builder.addCustomTargeting("kuid", adTargetingDTO.getKrux());
        builder.addCustomTargeting("did", adTargetingDTO.getDeviceId());
        builder.addCustomTargeting(Keywords.LIMIT_AD_TRACKING, adTargetingDTO.getLadtr());
        PublisherAdRequest build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "PublisherAdRequest.Build…\n                .build()");
        Ln.d("DFP AchievementInterstitialAd.preloadInterstitialAd %s", adTargetingDTO.toString());
        this.lastAdRequestedAtTime = System.currentTimeMillis();
        if (this.interstitialAd.getAdUnitId() == null) {
            this.interstitialAd.setAdUnitId(adTargetingDTO.getInterstitialAdId());
            this.interstitialAd.setAdListener(new AchievementInterstitialAd$preloadInterstitialAd$1(this, achievementInterstitialAdListener));
            this.interstitialAd.loadAd(build);
        }
    }

    public final void showInterstitialAd(@NotNull OnCloseAdListener onCloseAdListener2) {
        Intrinsics.checkParameterIsNotNull(onCloseAdListener2, "onCloseAdListener");
        this.onCloseAdListener = onCloseAdListener2;
        if (this.interstitialAd.isLoaded()) {
            this.interstitialAd.show();
        }
    }

    public final void resetLoadedAd() {
        this.interstitialAd = new PublisherInterstitialAd(this.context);
        this.lastAdRequestedDelay = 0;
        this.lastAdRequestedAtTime = 0;
    }
}
