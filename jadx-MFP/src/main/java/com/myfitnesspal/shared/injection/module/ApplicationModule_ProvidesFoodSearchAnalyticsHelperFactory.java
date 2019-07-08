package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodSearchAnalyticsHelperFactory implements Factory<FoodSearchAnalyticsHelper> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final ApplicationModule module;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;

    public ApplicationModule_ProvidesFoodSearchAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<ActionTrackingService> provider, Provider<AnalyticsService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<CountryService> provider4, Provider<DiaryService> provider5) {
        this.module = applicationModule;
        this.actionTrackingServiceProvider = provider;
        this.analyticsServiceProvider = provider2;
        this.multiAddFoodHelperProvider = provider3;
        this.countryServiceProvider = provider4;
        this.diaryServiceProvider = provider5;
    }

    public FoodSearchAnalyticsHelper get() {
        return provideInstance(this.module, this.actionTrackingServiceProvider, this.analyticsServiceProvider, this.multiAddFoodHelperProvider, this.countryServiceProvider, this.diaryServiceProvider);
    }

    public static FoodSearchAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<ActionTrackingService> provider, Provider<AnalyticsService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<CountryService> provider4, Provider<DiaryService> provider5) {
        return proxyProvidesFoodSearchAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvidesFoodSearchAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<ActionTrackingService> provider, Provider<AnalyticsService> provider2, Provider<MultiAddFoodHelper> provider3, Provider<CountryService> provider4, Provider<DiaryService> provider5) {
        ApplicationModule_ProvidesFoodSearchAnalyticsHelperFactory applicationModule_ProvidesFoodSearchAnalyticsHelperFactory = new ApplicationModule_ProvidesFoodSearchAnalyticsHelperFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvidesFoodSearchAnalyticsHelperFactory;
    }

    public static FoodSearchAnalyticsHelper proxyProvidesFoodSearchAnalyticsHelper(ApplicationModule applicationModule, Lazy<ActionTrackingService> lazy, Lazy<AnalyticsService> lazy2, Lazy<MultiAddFoodHelper> lazy3, Lazy<CountryService> lazy4, Lazy<DiaryService> lazy5) {
        return (FoodSearchAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesFoodSearchAnalyticsHelper(lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
