package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideProgressCongratsSharedPreferencesFactory implements Factory<KeyedSharedPreferences> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvideProgressCongratsSharedPreferencesFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.sessionProvider = provider2;
    }

    public KeyedSharedPreferences get() {
        return provideInstance(this.module, this.contextProvider, this.sessionProvider);
    }

    public static KeyedSharedPreferences provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2) {
        return proxyProvideProgressCongratsSharedPreferences(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvideProgressCongratsSharedPreferencesFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Session> provider2) {
        return new ApplicationModule_ProvideProgressCongratsSharedPreferencesFactory(applicationModule, provider, provider2);
    }

    public static KeyedSharedPreferences proxyProvideProgressCongratsSharedPreferences(ApplicationModule applicationModule, Context context, Lazy<Session> lazy) {
        return (KeyedSharedPreferences) Preconditions.checkNotNull(applicationModule.provideProgressCongratsSharedPreferences(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
