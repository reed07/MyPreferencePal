package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNewsFeedCacheFactory implements Factory<Cache<MfpNewsFeedActivityEntryListContainer>> {
    private final Provider<SharedPreferences> cacheStoreProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesNewsFeedCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.cacheStoreProvider = provider;
    }

    public Cache<MfpNewsFeedActivityEntryListContainer> get() {
        return provideInstance(this.module, this.cacheStoreProvider);
    }

    public static Cache<MfpNewsFeedActivityEntryListContainer> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesNewsFeedCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesNewsFeedCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesNewsFeedCacheFactory(applicationModule, provider);
    }

    public static Cache<MfpNewsFeedActivityEntryListContainer> proxyProvidesNewsFeedCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.providesNewsFeedCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
