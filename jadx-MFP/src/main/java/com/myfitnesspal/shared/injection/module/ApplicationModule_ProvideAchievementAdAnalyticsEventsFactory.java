package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdAnalyticsEvents;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAchievementAdAnalyticsEventsFactory implements Factory<AchievementAdAnalyticsEvents> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAchievementAdAnalyticsEventsFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public AchievementAdAnalyticsEvents get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static AchievementAdAnalyticsEvents provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideAchievementAdAnalyticsEvents(applicationModule, (AnalyticsService) provider.get());
    }

    public static ApplicationModule_ProvideAchievementAdAnalyticsEventsFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideAchievementAdAnalyticsEventsFactory(applicationModule, provider);
    }

    public static AchievementAdAnalyticsEvents proxyProvideAchievementAdAnalyticsEvents(ApplicationModule applicationModule, AnalyticsService analyticsService) {
        return (AchievementAdAnalyticsEvents) Preconditions.checkNotNull(applicationModule.provideAchievementAdAnalyticsEvents(analyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
