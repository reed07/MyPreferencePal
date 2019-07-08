package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMfpPlatformAppDetailsCacheFactory implements Factory<Cache<ApiResponse<MfpPlatformApp>>> {
    private final Provider<SharedPreferences> cacheStoreProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMfpPlatformAppDetailsCacheFactory(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        this.module = applicationModule;
        this.cacheStoreProvider = provider;
    }

    public Cache<ApiResponse<MfpPlatformApp>> get() {
        return provideInstance(this.module, this.cacheStoreProvider);
    }

    public static Cache<ApiResponse<MfpPlatformApp>> provideInstance(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return proxyProvidesMfpPlatformAppDetailsCache(applicationModule, (SharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesMfpPlatformAppDetailsCacheFactory create(ApplicationModule applicationModule, Provider<SharedPreferences> provider) {
        return new ApplicationModule_ProvidesMfpPlatformAppDetailsCacheFactory(applicationModule, provider);
    }

    public static Cache<ApiResponse<MfpPlatformApp>> proxyProvidesMfpPlatformAppDetailsCache(ApplicationModule applicationModule, SharedPreferences sharedPreferences) {
        return (Cache) Preconditions.checkNotNull(applicationModule.providesMfpPlatformAppDetailsCache(sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
