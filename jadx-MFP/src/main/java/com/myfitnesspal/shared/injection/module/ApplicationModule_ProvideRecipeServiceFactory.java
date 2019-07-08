package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideRecipeServiceFactory implements Factory<RecipeService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Cache<MfpRecipeListContainer>> cacheProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DbConnectionManager> currentProvider;
    private final Provider<IdService> idServiceProvider;
    private final ApplicationModule module;
    private final Provider<RecipesTable> recipesTableProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public ApplicationModule_ProvideRecipeServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<IdService> provider2, Provider<DbConnectionManager> provider3, Provider<CountryService> provider4, Provider<Cache<MfpRecipeListContainer>> provider5, Provider<RecipesTable> provider6, Provider<SyncService> provider7, Provider<Session> provider8) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.idServiceProvider = provider2;
        this.currentProvider = provider3;
        this.countryServiceProvider = provider4;
        this.cacheProvider = provider5;
        this.recipesTableProvider = provider6;
        this.syncServiceProvider = provider7;
        this.sessionProvider = provider8;
    }

    public RecipeService get() {
        return provideInstance(this.module, this.apiProvider, this.idServiceProvider, this.currentProvider, this.countryServiceProvider, this.cacheProvider, this.recipesTableProvider, this.syncServiceProvider, this.sessionProvider);
    }

    public static RecipeService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<IdService> provider2, Provider<DbConnectionManager> provider3, Provider<CountryService> provider4, Provider<Cache<MfpRecipeListContainer>> provider5, Provider<RecipesTable> provider6, Provider<SyncService> provider7, Provider<Session> provider8) {
        return proxyProvideRecipeService(applicationModule, provider, DoubleCheck.lazy(provider2), (DbConnectionManager) provider3.get(), DoubleCheck.lazy(provider4), (Cache) provider5.get(), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8));
    }

    public static ApplicationModule_ProvideRecipeServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<IdService> provider2, Provider<DbConnectionManager> provider3, Provider<CountryService> provider4, Provider<Cache<MfpRecipeListContainer>> provider5, Provider<RecipesTable> provider6, Provider<SyncService> provider7, Provider<Session> provider8) {
        ApplicationModule_ProvideRecipeServiceFactory applicationModule_ProvideRecipeServiceFactory = new ApplicationModule_ProvideRecipeServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return applicationModule_ProvideRecipeServiceFactory;
    }

    public static RecipeService proxyProvideRecipeService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<IdService> lazy, DbConnectionManager dbConnectionManager, Lazy<CountryService> lazy2, Cache<MfpRecipeListContainer> cache, Lazy<RecipesTable> lazy3, Lazy<SyncService> lazy4, Lazy<Session> lazy5) {
        return (RecipeService) Preconditions.checkNotNull(applicationModule.provideRecipeService(provider, lazy, dbConnectionManager, lazy2, cache, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
