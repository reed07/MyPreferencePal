package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideLocationServiceFactory implements Factory<LocationService> {
    private final Provider<Context> contextProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideLocationServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.localSettingsServiceProvider = provider2;
    }

    public LocationService get() {
        return provideInstance(this.module, this.contextProvider, this.localSettingsServiceProvider);
    }

    public static LocationService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2) {
        return proxyProvideLocationService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvideLocationServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2) {
        return new ApplicationModule_ProvideLocationServiceFactory(applicationModule, provider, provider2);
    }

    public static LocationService proxyProvideLocationService(ApplicationModule applicationModule, Context context, Lazy<LocalSettingsService> lazy) {
        return (LocationService) Preconditions.checkNotNull(applicationModule.provideLocationService(context, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
