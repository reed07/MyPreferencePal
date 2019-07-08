package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.net.ConnectivityManager;
import com.myfitnesspal.feature.settings.service.TroubleshootingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesTroubleshootingServiceFactory implements Factory<TroubleshootingService> {
    private final Provider<ConnectivityManager> connectivityManagerProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesTroubleshootingServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.connectivityManagerProvider = provider2;
    }

    public TroubleshootingService get() {
        return provideInstance(this.module, this.contextProvider, this.connectivityManagerProvider);
    }

    public static TroubleshootingService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        return proxyProvidesTroubleshootingService(applicationModule, (Context) provider.get(), (ConnectivityManager) provider2.get());
    }

    public static ApplicationModule_ProvidesTroubleshootingServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<ConnectivityManager> provider2) {
        return new ApplicationModule_ProvidesTroubleshootingServiceFactory(applicationModule, provider, provider2);
    }

    public static TroubleshootingService proxyProvidesTroubleshootingService(ApplicationModule applicationModule, Context context, ConnectivityManager connectivityManager) {
        return (TroubleshootingService) Preconditions.checkNotNull(applicationModule.providesTroubleshootingService(context, connectivityManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
