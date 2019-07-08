package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesAdsAnalyticHelperFactory implements Factory<AdsAnalyticsHelper> {
    private final Provider<MfpAnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesAdsAnalyticHelperFactory(ApplicationModule applicationModule, Provider<MfpAnalyticsService> provider, Provider<ConfigService> provider2) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
        this.configServiceProvider = provider2;
    }

    public AdsAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider, this.configServiceProvider);
    }

    public static AdsAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<MfpAnalyticsService> provider, Provider<ConfigService> provider2) {
        return proxyProvidesAdsAnalyticHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesAdsAnalyticHelperFactory create(ApplicationModule applicationModule, Provider<MfpAnalyticsService> provider, Provider<ConfigService> provider2) {
        return new ApplicationModule_ProvidesAdsAnalyticHelperFactory(applicationModule, provider, provider2);
    }

    public static AdsAnalyticsHelper proxyProvidesAdsAnalyticHelper(ApplicationModule applicationModule, Lazy<MfpAnalyticsService> lazy, Lazy<ConfigService> lazy2) {
        return (AdsAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesAdsAnalyticHelper(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
