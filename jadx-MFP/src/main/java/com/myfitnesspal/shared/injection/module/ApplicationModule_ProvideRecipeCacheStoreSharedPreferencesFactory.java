package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideRecipeCacheStoreSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideRecipeCacheStoreSharedPreferencesFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public SharedPreferences get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SharedPreferences provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideRecipeCacheStoreSharedPreferences(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideRecipeCacheStoreSharedPreferencesFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideRecipeCacheStoreSharedPreferencesFactory(applicationModule, provider);
    }

    public static SharedPreferences proxyProvideRecipeCacheStoreSharedPreferences(ApplicationModule applicationModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(applicationModule.provideRecipeCacheStoreSharedPreferences(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
