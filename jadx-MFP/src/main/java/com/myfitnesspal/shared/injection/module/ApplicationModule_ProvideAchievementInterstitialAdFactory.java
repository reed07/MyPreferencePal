package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdAnalyticsEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAchievementInterstitialAdFactory implements Factory<AchievementInterstitialAd> {
    private final Provider<AchievementAdAnalyticsEvents> achievementAdAnalyticsEventsProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAchievementInterstitialAdFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AchievementAdAnalyticsEvents> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.achievementAdAnalyticsEventsProvider = provider2;
    }

    public AchievementInterstitialAd get() {
        return provideInstance(this.module, this.contextProvider, this.achievementAdAnalyticsEventsProvider);
    }

    public static AchievementInterstitialAd provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AchievementAdAnalyticsEvents> provider2) {
        return proxyProvideAchievementInterstitialAd(applicationModule, (Context) provider.get(), (AchievementAdAnalyticsEvents) provider2.get());
    }

    public static ApplicationModule_ProvideAchievementInterstitialAdFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AchievementAdAnalyticsEvents> provider2) {
        return new ApplicationModule_ProvideAchievementInterstitialAdFactory(applicationModule, provider, provider2);
    }

    public static AchievementInterstitialAd proxyProvideAchievementInterstitialAd(ApplicationModule applicationModule, Context context, AchievementAdAnalyticsEvents achievementAdAnalyticsEvents) {
        return (AchievementInterstitialAd) Preconditions.checkNotNull(applicationModule.provideAchievementInterstitialAd(context, achievementAdAnalyticsEvents), "Cannot return null from a non-@Nullable @Provides method");
    }
}
