package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutritionGraphAnalyticsHelperFactory implements Factory<NutritionGraphAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceLazyProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesNutritionGraphAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceLazyProvider = provider;
    }

    public NutritionGraphAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceLazyProvider);
    }

    public static NutritionGraphAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesNutritionGraphAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesNutritionGraphAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesNutritionGraphAnalyticsHelperFactory(applicationModule, provider);
    }

    public static NutritionGraphAnalyticsHelper proxyProvidesNutritionGraphAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (NutritionGraphAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesNutritionGraphAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
