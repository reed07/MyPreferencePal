package com.myfitnesspal.feature.settings.service;

import android.content.Context;
import android.net.ConnectivityManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TroubleshootingServiceImpl_Factory implements Factory<TroubleshootingServiceImpl> {
    private final Provider<ConnectivityManager> connectivityManagerProvider;
    private final Provider<Context> contextProvider;

    public TroubleshootingServiceImpl_Factory(Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        this.contextProvider = provider;
        this.connectivityManagerProvider = provider2;
    }

    public TroubleshootingServiceImpl get() {
        return provideInstance(this.contextProvider, this.connectivityManagerProvider);
    }

    public static TroubleshootingServiceImpl provideInstance(Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        return new TroubleshootingServiceImpl((Context) provider.get(), (ConnectivityManager) provider2.get());
    }

    public static TroubleshootingServiceImpl_Factory create(Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        return new TroubleshootingServiceImpl_Factory(provider, provider2);
    }

    public static TroubleshootingServiceImpl newTroubleshootingServiceImpl(Context context, ConnectivityManager connectivityManager) {
        return new TroubleshootingServiceImpl(context, connectivityManager);
    }
}
