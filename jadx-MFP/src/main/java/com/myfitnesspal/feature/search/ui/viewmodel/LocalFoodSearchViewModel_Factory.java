package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import com.myfitnesspal.feature.search.repository.LocalFoodSearchRepository;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LocalFoodSearchViewModel_Factory implements Factory<LocalFoodSearchViewModel> {
    private final Provider<Application> appContextProvider;
    private final Provider<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelperProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MultiAddFoodHelper> multiAddFoodHelperProvider;
    private final Provider<LocalFoodSearchRepository> searchRepoProvider;
    private final Provider<SortOrderHelper> sortOrderHelperProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public LocalFoodSearchViewModel_Factory(Provider<Application> provider, Provider<LocalFoodSearchRepository> provider2, Provider<SortOrderHelper> provider3, Provider<MultiAddFoodHelper> provider4, Provider<FoodSearchAnalyticsHelper> provider5, Provider<UserEnergyService> provider6, Provider<LocalSettingsService> provider7) {
        this.appContextProvider = provider;
        this.searchRepoProvider = provider2;
        this.sortOrderHelperProvider = provider3;
        this.multiAddFoodHelperProvider = provider4;
        this.foodSearchAnalyticsHelperProvider = provider5;
        this.userEnergyServiceProvider = provider6;
        this.localSettingsServiceProvider = provider7;
    }

    public LocalFoodSearchViewModel get() {
        return provideInstance(this.appContextProvider, this.searchRepoProvider, this.sortOrderHelperProvider, this.multiAddFoodHelperProvider, this.foodSearchAnalyticsHelperProvider, this.userEnergyServiceProvider, this.localSettingsServiceProvider);
    }

    public static LocalFoodSearchViewModel provideInstance(Provider<Application> provider, Provider<LocalFoodSearchRepository> provider2, Provider<SortOrderHelper> provider3, Provider<MultiAddFoodHelper> provider4, Provider<FoodSearchAnalyticsHelper> provider5, Provider<UserEnergyService> provider6, Provider<LocalSettingsService> provider7) {
        LocalFoodSearchViewModel localFoodSearchViewModel = new LocalFoodSearchViewModel((Application) provider.get(), (LocalFoodSearchRepository) provider2.get(), (SortOrderHelper) provider3.get(), (MultiAddFoodHelper) provider4.get(), (FoodSearchAnalyticsHelper) provider5.get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
        return localFoodSearchViewModel;
    }

    public static LocalFoodSearchViewModel_Factory create(Provider<Application> provider, Provider<LocalFoodSearchRepository> provider2, Provider<SortOrderHelper> provider3, Provider<MultiAddFoodHelper> provider4, Provider<FoodSearchAnalyticsHelper> provider5, Provider<UserEnergyService> provider6, Provider<LocalSettingsService> provider7) {
        LocalFoodSearchViewModel_Factory localFoodSearchViewModel_Factory = new LocalFoodSearchViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return localFoodSearchViewModel_Factory;
    }

    public static LocalFoodSearchViewModel newLocalFoodSearchViewModel(Application application, LocalFoodSearchRepository localFoodSearchRepository, SortOrderHelper sortOrderHelper, MultiAddFoodHelper multiAddFoodHelper, FoodSearchAnalyticsHelper foodSearchAnalyticsHelper, Lazy<UserEnergyService> lazy, Lazy<LocalSettingsService> lazy2) {
        LocalFoodSearchViewModel localFoodSearchViewModel = new LocalFoodSearchViewModel(application, localFoodSearchRepository, sortOrderHelper, multiAddFoodHelper, foodSearchAnalyticsHelper, lazy, lazy2);
        return localFoodSearchViewModel;
    }
}
