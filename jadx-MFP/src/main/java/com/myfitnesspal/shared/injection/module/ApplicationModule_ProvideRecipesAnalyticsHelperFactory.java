package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideRecipesAnalyticsHelperFactory implements Factory<RecipesAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideRecipesAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public RecipesAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static RecipesAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideRecipesAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideRecipesAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideRecipesAnalyticsHelperFactory(applicationModule, provider);
    }

    public static RecipesAnalyticsHelper proxyProvideRecipesAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (RecipesAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideRecipesAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
