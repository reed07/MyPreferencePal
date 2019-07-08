package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideDebugSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideDebugSharedPreferencesFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public SharedPreferences get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SharedPreferences provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvideDebugSharedPreferences(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvideDebugSharedPreferencesFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvideDebugSharedPreferencesFactory(applicationModule, provider);
    }

    public static SharedPreferences proxyProvideDebugSharedPreferences(ApplicationModule applicationModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(applicationModule.provideDebugSharedPreferences(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
