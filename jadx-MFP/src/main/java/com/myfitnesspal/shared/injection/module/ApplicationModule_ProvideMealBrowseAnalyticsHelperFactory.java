package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideMealBrowseAnalyticsHelperFactory implements Factory<MealSharingDirectionsAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideMealBrowseAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public MealSharingDirectionsAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static MealSharingDirectionsAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideMealBrowseAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideMealBrowseAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideMealBrowseAnalyticsHelperFactory(applicationModule, provider);
    }

    public static MealSharingDirectionsAnalyticsHelper proxyProvideMealBrowseAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (MealSharingDirectionsAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideMealBrowseAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
