package com.myfitnesspal.feature.search.repository;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LocalFoodSearchRepository_Factory implements Factory<LocalFoodSearchRepository> {
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MealUtil> mealUtilProvider;
    private final Provider<Session> sessionProvider;

    public LocalFoodSearchRepository_Factory(Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        this.dbConnectionManagerProvider = provider;
        this.localSettingsServiceProvider = provider2;
        this.countryServiceProvider = provider3;
        this.sessionProvider = provider4;
        this.mealUtilProvider = provider5;
    }

    public LocalFoodSearchRepository get() {
        return provideInstance(this.dbConnectionManagerProvider, this.localSettingsServiceProvider, this.countryServiceProvider, this.sessionProvider, this.mealUtilProvider);
    }

    public static LocalFoodSearchRepository provideInstance(Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        LocalFoodSearchRepository localFoodSearchRepository = new LocalFoodSearchRepository((DbConnectionManager) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
        return localFoodSearchRepository;
    }

    public static LocalFoodSearchRepository_Factory create(Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        LocalFoodSearchRepository_Factory localFoodSearchRepository_Factory = new LocalFoodSearchRepository_Factory(provider, provider2, provider3, provider4, provider5);
        return localFoodSearchRepository_Factory;
    }

    public static LocalFoodSearchRepository newLocalFoodSearchRepository(DbConnectionManager dbConnectionManager, Lazy<LocalSettingsService> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3, Lazy<MealUtil> lazy4) {
        LocalFoodSearchRepository localFoodSearchRepository = new LocalFoodSearchRepository(dbConnectionManager, lazy, lazy2, lazy3, lazy4);
        return localFoodSearchRepository;
    }
}
