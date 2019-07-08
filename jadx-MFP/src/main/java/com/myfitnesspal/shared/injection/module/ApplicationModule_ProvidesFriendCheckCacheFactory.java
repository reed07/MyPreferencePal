package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFriendCheckCacheFactory implements Factory<Cache<FriendCheckResponseObject>> {
    private final Provider<SharedPreferences> cacheStoreProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFriendCheckCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.cacheStoreProvider = provider;
    }

    public Cache<FriendCheckResponseObject> get() {
        return provideInstance(this.module, this.cacheStoreProvider);
    }

    public static Cache<FriendCheckResponseObject> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesFriendCheckCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesFriendCheckCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesFriendCheckCacheFactory(applicationModule, provider);
    }

    public static Cache<FriendCheckResponseObject> proxyProvidesFriendCheckCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.providesFriendCheckCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
