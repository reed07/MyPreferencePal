package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.search.repository.OnlineFoodSearchRepository;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class OnlineFoodSearchViewModel_Factory implements Factory<OnlineFoodSearchViewModel> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<Application> applicationProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<OnlineFoodSearchRepository> onlineSearchRepoProvider;
    private final Provider<PremiumService> premiumServiceProvider;

    public OnlineFoodSearchViewModel_Factory(Provider<Application> provider, Provider<CountryService> provider2, Provider<OnlineFoodSearchRepository> provider3, Provider<ActionTrackingService> provider4, Provider<ConfigService> provider5, Provider<AnalyticsService> provider6, Provider<PremiumService> provider7, Provider<FoodSearchAnalyticsHelper> provider8) {
        this.applicationProvider = provider;
        this.countryServiceProvider = provider2;
        this.onlineSearchRepoProvider = provider3;
        this.actionTrackingServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.analyticsServiceProvider = provider6;
        this.premiumServiceProvider = provider7;
        this.foodSearchAnalyticsHelperProvider = provider8;
    }

    public OnlineFoodSearchViewModel get() {
        return provideInstance(this.applicationProvider, this.countryServiceProvider, this.onlineSearchRepoProvider, this.actionTrackingServiceProvider, this.configServiceProvider, this.analyticsServiceProvider, this.premiumServiceProvider, this.foodSearchAnalyticsHelperProvider);
    }

    public static OnlineFoodSearchViewModel provideInstance(Provider<Application> provider, Provider<CountryService> provider2, Provider<OnlineFoodSearchRepository> provider3, Provider<ActionTrackingService> provider4, Provider<ConfigService> provider5, Provider<AnalyticsService> provider6, Provider<PremiumService> provider7, Provider<FoodSearchAnalyticsHelper> provider8) {
        OnlineFoodSearchViewModel onlineFoodSearchViewModel = new OnlineFoodSearchViewModel((Application) provider.get(), (CountryService) provider2.get(), (OnlineFoodSearchRepository) provider3.get(), DoubleCheck.lazy(provider4), (ConfigService) provider5.get(), DoubleCheck.lazy(provider6), (PremiumService) provider7.get(), (FoodSearchAnalyticsHelper) provider8.get());
        return onlineFoodSearchViewModel;
    }

    public static OnlineFoodSearchViewModel_Factory create(Provider<Application> provider, Provider<CountryService> provider2, Provider<OnlineFoodSearchRepository> provider3, Provider<ActionTrackingService> provider4, Provider<ConfigService> provider5, Provider<AnalyticsService> provider6, Provider<PremiumService> provider7, Provider<FoodSearchAnalyticsHelper> provider8) {
        OnlineFoodSearchViewModel_Factory onlineFoodSearchViewModel_Factory = new OnlineFoodSearchViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return onlineFoodSearchViewModel_Factory;
    }

    public static OnlineFoodSearchViewModel newOnlineFoodSearchViewModel(Application application, CountryService countryService, OnlineFoodSearchRepository onlineFoodSearchRepository, Lazy<ActionTrackingService> lazy, ConfigService configService, Lazy<AnalyticsService> lazy2, PremiumService premiumService, FoodSearchAnalyticsHelper foodSearchAnalyticsHelper) {
        OnlineFoodSearchViewModel onlineFoodSearchViewModel = new OnlineFoodSearchViewModel(application, countryService, onlineFoodSearchRepository, lazy, configService, lazy2, premiumService, foodSearchAnalyticsHelper);
        return onlineFoodSearchViewModel;
    }
}
