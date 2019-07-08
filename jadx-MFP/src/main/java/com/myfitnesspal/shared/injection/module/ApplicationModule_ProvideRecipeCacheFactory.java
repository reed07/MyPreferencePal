package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideRecipeCacheFactory implements Factory<Cache<MfpRecipeListContainer>> {
    private final ApplicationModule module;
    private final Provider<SharedPreferences> recipeCachedStorePreferencesProvider;

    public ApplicationModule_ProvideRecipeCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.recipeCachedStorePreferencesProvider = provider;
    }

    public Cache<MfpRecipeListContainer> get() {
        return provideInstance(this.module, this.recipeCachedStorePreferencesProvider);
    }

    public static Cache<MfpRecipeListContainer> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvideRecipeCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvideRecipeCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvideRecipeCacheFactory(applicationModule, provider);
    }

    public static Cache<MfpRecipeListContainer> proxyProvideRecipeCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.provideRecipeCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
