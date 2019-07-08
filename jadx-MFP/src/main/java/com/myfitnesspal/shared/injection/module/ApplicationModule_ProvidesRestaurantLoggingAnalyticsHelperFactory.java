package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesRestaurantLoggingAnalyticsHelperFactory implements Factory<RestaurantLoggingAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpAnalyticsService> mfpAnalyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesRestaurantLoggingAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<MfpAnalyticsService> provider2) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
        this.mfpAnalyticsServiceProvider = provider2;
    }

    public RestaurantLoggingAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider, this.mfpAnalyticsServiceProvider);
    }

    public static RestaurantLoggingAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<MfpAnalyticsService> provider2) {
        return proxyProvidesRestaurantLoggingAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesRestaurantLoggingAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<MfpAnalyticsService> provider2) {
        return new ApplicationModule_ProvidesRestaurantLoggingAnalyticsHelperFactory(applicationModule, provider, provider2);
    }

    public static RestaurantLoggingAnalyticsHelper proxyProvidesRestaurantLoggingAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy, Lazy<MfpAnalyticsService> lazy2) {
        return (RestaurantLoggingAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesRestaurantLoggingAnalyticsHelper(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
