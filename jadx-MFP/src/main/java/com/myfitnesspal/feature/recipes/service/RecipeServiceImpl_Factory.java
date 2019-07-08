package com.myfitnesspal.feature.recipes.service;

import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipePropertiesDBAdapter;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecipeServiceImpl_Factory implements Factory<RecipeServiceImpl> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Cache<MfpRecipeListContainer>> cacheProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<FoodDBAdapter> foodDBAdapterProvider;
    private final Provider<IdService> idServiceProvider;
    private final Provider<RecipePropertiesDBAdapter> recipePropertiesDBAdapterProvider;
    private final Provider<RecipesTable> recipesTableProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public RecipeServiceImpl_Factory(Provider<RecipePropertiesDBAdapter> provider, Provider<FoodDBAdapter> provider2, Provider<MfpV2Api> provider3, Provider<IdService> provider4, Provider<CountryService> provider5, Provider<Cache<MfpRecipeListContainer>> provider6, Provider<RecipesTable> provider7, Provider<SyncService> provider8, Provider<Session> provider9) {
        this.recipePropertiesDBAdapterProvider = provider;
        this.foodDBAdapterProvider = provider2;
        this.apiProvider = provider3;
        this.idServiceProvider = provider4;
        this.countryServiceProvider = provider5;
        this.cacheProvider = provider6;
        this.recipesTableProvider = provider7;
        this.syncServiceProvider = provider8;
        this.sessionProvider = provider9;
    }

    public RecipeServiceImpl get() {
        return provideInstance(this.recipePropertiesDBAdapterProvider, this.foodDBAdapterProvider, this.apiProvider, this.idServiceProvider, this.countryServiceProvider, this.cacheProvider, this.recipesTableProvider, this.syncServiceProvider, this.sessionProvider);
    }

    public static RecipeServiceImpl provideInstance(Provider<RecipePropertiesDBAdapter> provider, Provider<FoodDBAdapter> provider2, Provider<MfpV2Api> provider3, Provider<IdService> provider4, Provider<CountryService> provider5, Provider<Cache<MfpRecipeListContainer>> provider6, Provider<RecipesTable> provider7, Provider<SyncService> provider8, Provider<Session> provider9) {
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl((RecipePropertiesDBAdapter) provider.get(), (FoodDBAdapter) provider2.get(), provider3, DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), (Cache) provider6.get(), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
        return recipeServiceImpl;
    }

    public static RecipeServiceImpl_Factory create(Provider<RecipePropertiesDBAdapter> provider, Provider<FoodDBAdapter> provider2, Provider<MfpV2Api> provider3, Provider<IdService> provider4, Provider<CountryService> provider5, Provider<Cache<MfpRecipeListContainer>> provider6, Provider<RecipesTable> provider7, Provider<SyncService> provider8, Provider<Session> provider9) {
        RecipeServiceImpl_Factory recipeServiceImpl_Factory = new RecipeServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return recipeServiceImpl_Factory;
    }

    public static RecipeServiceImpl newRecipeServiceImpl(RecipePropertiesDBAdapter recipePropertiesDBAdapter, FoodDBAdapter foodDBAdapter, Provider<MfpV2Api> provider, Lazy<IdService> lazy, Lazy<CountryService> lazy2, Cache<MfpRecipeListContainer> cache, Lazy<RecipesTable> lazy3, Lazy<SyncService> lazy4, Lazy<Session> lazy5) {
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(recipePropertiesDBAdapter, foodDBAdapter, provider, lazy, lazy2, cache, lazy3, lazy4, lazy5);
        return recipeServiceImpl;
    }
}
