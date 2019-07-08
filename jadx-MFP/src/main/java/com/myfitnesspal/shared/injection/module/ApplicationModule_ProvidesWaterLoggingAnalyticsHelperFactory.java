package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesWaterLoggingAnalyticsHelperFactory implements Factory<WaterLoggingAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesWaterLoggingAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
        this.configServiceProvider = provider2;
    }

    public WaterLoggingAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider, this.configServiceProvider);
    }

    public static WaterLoggingAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2) {
        return proxyProvidesWaterLoggingAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesWaterLoggingAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2) {
        return new ApplicationModule_ProvidesWaterLoggingAnalyticsHelperFactory(applicationModule, provider, provider2);
    }

    public static WaterLoggingAnalyticsHelper proxyProvidesWaterLoggingAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2) {
        return (WaterLoggingAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesWaterLoggingAnalyticsHelper(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
