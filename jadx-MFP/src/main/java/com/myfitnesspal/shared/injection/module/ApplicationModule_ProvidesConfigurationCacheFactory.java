package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.service.config.Configuration;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesConfigurationCacheFactory implements Factory<Cache<Configuration>> {
    private final Provider<SharedPreferences> cacheStoreProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesConfigurationCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.cacheStoreProvider = provider;
    }

    public Cache<Configuration> get() {
        return provideInstance(this.module, this.cacheStoreProvider);
    }

    public static Cache<Configuration> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesConfigurationCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesConfigurationCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesConfigurationCacheFactory(applicationModule, provider);
    }

    public static Cache<Configuration> proxyProvidesConfigurationCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.providesConfigurationCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
