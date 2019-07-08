package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideNutritionAnalyticsHelperFactory implements Factory<NutritionAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideNutritionAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public NutritionAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static NutritionAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideNutritionAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideNutritionAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideNutritionAnalyticsHelperFactory(applicationModule, provider);
    }

    public static NutritionAnalyticsHelper proxyProvideNutritionAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (NutritionAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideNutritionAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
