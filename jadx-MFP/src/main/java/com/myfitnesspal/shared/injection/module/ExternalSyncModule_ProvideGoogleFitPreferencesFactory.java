package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvideGoogleFitPreferencesFactory implements Factory<KeyedSharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ExternalSyncModule module;
    private final Provider<Session> sessionProvider;

    public ExternalSyncModule_ProvideGoogleFitPreferencesFactory(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<Session> provider2) {
        this.module = externalSyncModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
    }

    public KeyedSharedPreferences get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider);
    }

    public static KeyedSharedPreferences provideInstance(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<Session> provider2) {
        return proxyProvideGoogleFitPreferences(externalSyncModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ExternalSyncModule_ProvideGoogleFitPreferencesFactory create(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<Session> provider2) {
        return new ExternalSyncModule_ProvideGoogleFitPreferencesFactory(externalSyncModule, provider, provider2);
    }

    public static KeyedSharedPreferences proxyProvideGoogleFitPreferences(ExternalSyncModule externalSyncModule, Context context, Lazy<Session> lazy) {
        return (KeyedSharedPreferences) Preconditions.checkNotNull(externalSyncModule.provideGoogleFitPreferences(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
