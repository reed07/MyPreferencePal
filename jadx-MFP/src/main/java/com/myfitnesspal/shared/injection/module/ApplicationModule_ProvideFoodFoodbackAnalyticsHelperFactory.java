package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideFoodFoodbackAnalyticsHelperFactory implements Factory<FoodFeedbackAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideFoodFoodbackAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public FoodFeedbackAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static FoodFeedbackAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideFoodFoodbackAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideFoodFoodbackAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideFoodFoodbackAnalyticsHelperFactory(applicationModule, provider);
    }

    public static FoodFeedbackAnalyticsHelper proxyProvideFoodFoodbackAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (FoodFeedbackAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideFoodFoodbackAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
