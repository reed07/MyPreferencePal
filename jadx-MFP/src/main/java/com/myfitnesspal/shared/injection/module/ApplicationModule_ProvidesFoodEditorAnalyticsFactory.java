package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodEditorAnalyticsFactory implements Factory<FoodEditorAnalytics> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFoodEditorAnalyticsFactory(ApplicationModule applicationModule, Provider<DiaryService> provider, Provider<AnalyticsService> provider2, Provider<ActionTrackingService> provider3, Provider<CountryService> provider4) {
        this.module = applicationModule;
        this.diaryServiceProvider = provider;
        this.analyticsServiceProvider = provider2;
        this.actionTrackingServiceProvider = provider3;
        this.countryServiceProvider = provider4;
    }

    public FoodEditorAnalytics get() {
        return provideInstance(this.module, this.diaryServiceProvider, this.analyticsServiceProvider, this.actionTrackingServiceProvider, this.countryServiceProvider);
    }

    public static FoodEditorAnalytics provideInstance(ApplicationModule applicationModule, Provider<DiaryService> provider, Provider<AnalyticsService> provider2, Provider<ActionTrackingService> provider3, Provider<CountryService> provider4) {
        return proxyProvidesFoodEditorAnalytics(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesFoodEditorAnalyticsFactory create(ApplicationModule applicationModule, Provider<DiaryService> provider, Provider<AnalyticsService> provider2, Provider<ActionTrackingService> provider3, Provider<CountryService> provider4) {
        ApplicationModule_ProvidesFoodEditorAnalyticsFactory applicationModule_ProvidesFoodEditorAnalyticsFactory = new ApplicationModule_ProvidesFoodEditorAnalyticsFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesFoodEditorAnalyticsFactory;
    }

    public static FoodEditorAnalytics proxyProvidesFoodEditorAnalytics(ApplicationModule applicationModule, Lazy<DiaryService> lazy, Lazy<AnalyticsService> lazy2, Lazy<ActionTrackingService> lazy3, Lazy<CountryService> lazy4) {
        return (FoodEditorAnalytics) Preconditions.checkNotNull(applicationModule.providesFoodEditorAnalytics(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
