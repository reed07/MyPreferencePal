package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.shared.api.v1.MfpSearchApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesSearchServiceFactory implements Factory<SearchService> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<MealUtil> mealHelperUtilProvider;
    private final ApplicationModule module;
    private final Provider<MfpSearchApi> searchApiProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SortOrderHelper> sortOrderHelperProvider;
    private final Provider<MfpV2Api> v2ApiProvider;

    public ApplicationModule_ProvidesSearchServiceFactory(ApplicationModule applicationModule, Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Provider<ActionTrackingService> provider3, Provider<SortOrderHelper> provider4, Provider<MealUtil> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<CountryService> provider8, Provider<DbConnectionManager> provider9) {
        this.module = applicationModule;
        this.searchApiProvider = provider;
        this.v2ApiProvider = provider2;
        this.actionTrackingServiceProvider = provider3;
        this.sortOrderHelperProvider = provider4;
        this.mealHelperUtilProvider = provider5;
        this.sessionProvider = provider6;
        this.localSettingsServiceProvider = provider7;
        this.countryServiceProvider = provider8;
        this.dbConnectionManagerProvider = provider9;
    }

    public SearchService get() {
        return provideInstance(this.module, this.searchApiProvider, this.v2ApiProvider, this.actionTrackingServiceProvider, this.sortOrderHelperProvider, this.mealHelperUtilProvider, this.sessionProvider, this.localSettingsServiceProvider, this.countryServiceProvider, this.dbConnectionManagerProvider);
    }

    public static SearchService provideInstance(ApplicationModule applicationModule, Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Provider<ActionTrackingService> provider3, Provider<SortOrderHelper> provider4, Provider<MealUtil> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<CountryService> provider8, Provider<DbConnectionManager> provider9) {
        return proxyProvidesSearchService(applicationModule, provider, provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static ApplicationModule_ProvidesSearchServiceFactory create(ApplicationModule applicationModule, Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Provider<ActionTrackingService> provider3, Provider<SortOrderHelper> provider4, Provider<MealUtil> provider5, Provider<Session> provider6, Provider<LocalSettingsService> provider7, Provider<CountryService> provider8, Provider<DbConnectionManager> provider9) {
        ApplicationModule_ProvidesSearchServiceFactory applicationModule_ProvidesSearchServiceFactory = new ApplicationModule_ProvidesSearchServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return applicationModule_ProvidesSearchServiceFactory;
    }

    public static SearchService proxyProvidesSearchService(ApplicationModule applicationModule, Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Lazy<ActionTrackingService> lazy, Lazy<SortOrderHelper> lazy2, Lazy<MealUtil> lazy3, Lazy<Session> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<CountryService> lazy6, Lazy<DbConnectionManager> lazy7) {
        return (SearchService) Preconditions.checkNotNull(applicationModule.providesSearchService(provider, provider2, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}
