package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesProgressAnalyticsFactory implements Factory<ProgressAnalytics> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesProgressAnalyticsFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsProvider = provider;
    }

    public ProgressAnalytics get() {
        return provideInstance(this.module, this.analyticsProvider);
    }

    public static ProgressAnalytics provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesProgressAnalytics(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesProgressAnalyticsFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesProgressAnalyticsFactory(applicationModule, provider);
    }

    public static ProgressAnalytics proxyProvidesProgressAnalytics(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (ProgressAnalytics) Preconditions.checkNotNull(applicationModule.providesProgressAnalytics(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
