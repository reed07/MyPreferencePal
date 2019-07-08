package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesFitClientPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ExternalSyncModule module;

    public ExternalSyncModule_ProvidesFitClientPreferencesFactory(ExternalSyncModule externalSyncModule, Provider<Context> provider) {
        this.module = externalSyncModule;
        this.contextProvider = provider;
    }

    public SharedPreferences get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static SharedPreferences provideInstance(ExternalSyncModule externalSyncModule, Provider<Context> provider) {
        return proxyProvidesFitClientPreferences(externalSyncModule, (Context) provider.get());
    }

    public static ExternalSyncModule_ProvidesFitClientPreferencesFactory create(ExternalSyncModule externalSyncModule, Provider<Context> provider) {
        return new ExternalSyncModule_ProvidesFitClientPreferencesFactory(externalSyncModule, provider);
    }

    public static SharedPreferences proxyProvidesFitClientPreferences(ExternalSyncModule externalSyncModule, Context context) {
        return (SharedPreferences) Preconditions.checkNotNull(externalSyncModule.providesFitClientPreferences(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
