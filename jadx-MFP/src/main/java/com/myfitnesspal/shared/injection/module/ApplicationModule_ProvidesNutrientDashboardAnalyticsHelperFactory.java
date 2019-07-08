package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutrientDashboardAnalyticsHelperFactory implements Factory<NutrientDashboardAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesNutrientDashboardAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public NutrientDashboardAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static NutrientDashboardAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesNutrientDashboardAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesNutrientDashboardAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesNutrientDashboardAnalyticsHelperFactory(applicationModule, provider);
    }

    public static NutrientDashboardAnalyticsHelper proxyProvidesNutrientDashboardAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (NutrientDashboardAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesNutrientDashboardAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
