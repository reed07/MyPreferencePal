package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesConnectivityManagerFactory implements Factory<ConnectivityManager> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesConnectivityManagerFactory(ApplicationModule applicationModule, Provider<Context> provider) {
        this.module = applicationModule;
        this.contextProvider = provider;
    }

    public ConnectivityManager get() {
        return provideInstance(this.module, this.contextProvider);
    }

    public static ConnectivityManager provideInstance(ApplicationModule applicationModule, Provider<Context> provider) {
        return proxyProvidesConnectivityManager(applicationModule, (Context) provider.get());
    }

    public static ApplicationModule_ProvidesConnectivityManagerFactory create(ApplicationModule applicationModule, Provider<Context> provider) {
        return new ApplicationModule_ProvidesConnectivityManagerFactory(applicationModule, provider);
    }

    public static ConnectivityManager proxyProvidesConnectivityManager(ApplicationModule applicationModule, Context context) {
        return (ConnectivityManager) Preconditions.checkNotNull(applicationModule.providesConnectivityManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
