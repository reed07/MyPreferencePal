package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.repository.LocalFoodSearchRepository;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideLocalFoodSearchRepositoryFactory implements Factory<LocalFoodSearchRepository> {
    private final Provider<CountryService> countryServiceLazyProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceLazyProvider;
    private final Provider<MealUtil> mealUtilLazyProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionLazyProvider;

    public ApplicationModule_ProvideLocalFoodSearchRepositoryFactory(ApplicationModule applicationModule, Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        this.module = applicationModule;
        this.dbConnectionManagerProvider = provider;
        this.localSettingsServiceLazyProvider = provider2;
        this.countryServiceLazyProvider = provider3;
        this.sessionLazyProvider = provider4;
        this.mealUtilLazyProvider = provider5;
    }

    public LocalFoodSearchRepository get() {
        return provideInstance(this.module, this.dbConnectionManagerProvider, this.localSettingsServiceLazyProvider, this.countryServiceLazyProvider, this.sessionLazyProvider, this.mealUtilLazyProvider);
    }

    public static LocalFoodSearchRepository provideInstance(ApplicationModule applicationModule, Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        return proxyProvideLocalFoodSearchRepository(applicationModule, (DbConnectionManager) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvideLocalFoodSearchRepositoryFactory create(ApplicationModule applicationModule, Provider<DbConnectionManager> provider, Provider<LocalSettingsService> provider2, Provider<CountryService> provider3, Provider<Session> provider4, Provider<MealUtil> provider5) {
        ApplicationModule_ProvideLocalFoodSearchRepositoryFactory applicationModule_ProvideLocalFoodSearchRepositoryFactory = new ApplicationModule_ProvideLocalFoodSearchRepositoryFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvideLocalFoodSearchRepositoryFactory;
    }

    public static LocalFoodSearchRepository proxyProvideLocalFoodSearchRepository(ApplicationModule applicationModule, DbConnectionManager dbConnectionManager, Lazy<LocalSettingsService> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3, Lazy<MealUtil> lazy4) {
        return (LocalFoodSearchRepository) Preconditions.checkNotNull(applicationModule.provideLocalFoodSearchRepository(dbConnectionManager, lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
