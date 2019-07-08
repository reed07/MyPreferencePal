package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFriendsCacheFactory implements Factory<Cache<List<UserSummaryObject>>> {
    private final Provider<SharedPreferences> cacheStoreProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFriendsCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.cacheStoreProvider = provider;
    }

    public Cache<List<UserSummaryObject>> get() {
        return provideInstance(this.module, this.cacheStoreProvider);
    }

    public static Cache<List<UserSummaryObject>> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesFriendsCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesFriendsCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesFriendsCacheFactory(applicationModule, provider);
    }

    public static Cache<List<UserSummaryObject>> proxyProvidesFriendsCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.providesFriendsCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
