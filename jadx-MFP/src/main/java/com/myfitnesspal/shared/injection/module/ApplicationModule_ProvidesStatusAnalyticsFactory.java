package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.friends.service.StatusAnalytics;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesStatusAnalyticsFactory implements Factory<StatusAnalytics> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesStatusAnalyticsFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsProvider = provider;
    }

    public StatusAnalytics get() {
        return provideInstance(this.module, this.analyticsProvider);
    }

    public static StatusAnalytics provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesStatusAnalytics(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesStatusAnalyticsFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesStatusAnalyticsFactory(applicationModule, provider);
    }

    public static StatusAnalytics proxyProvidesStatusAnalytics(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (StatusAnalytics) Preconditions.checkNotNull(applicationModule.providesStatusAnalytics(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
