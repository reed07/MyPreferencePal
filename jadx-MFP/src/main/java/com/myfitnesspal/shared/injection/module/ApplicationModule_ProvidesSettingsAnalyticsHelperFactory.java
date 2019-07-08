package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.recipes.util.RecipesMealsFoodsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSettingsAnalyticsHelperFactory implements Factory<RecipesMealsFoodsAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesSettingsAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public RecipesMealsFoodsAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static RecipesMealsFoodsAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesSettingsAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesSettingsAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesSettingsAnalyticsHelperFactory(applicationModule, provider);
    }

    public static RecipesMealsFoodsAnalyticsHelper proxyProvidesSettingsAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (RecipesMealsFoodsAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesSettingsAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
