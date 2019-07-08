package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesGeoLocationServiceFactory implements Factory<GeoLocationService> {
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<SharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesGeoLocationServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<SharedPreferences> provider2) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.prefsProvider = provider2;
    }

    public GeoLocationService get() {
        return provideInstance(this.module, this.contextProvider, this.prefsProvider);
    }

    public static GeoLocationService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<SharedPreferences> provider2) {
        return proxyProvidesGeoLocationService(applicationModule, (Context) provider.get(), (SharedPreferences) provider2.get());
    }

    public static ApplicationModule_ProvidesGeoLocationServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<SharedPreferences> provider2) {
        return new ApplicationModule_ProvidesGeoLocationServiceFactory(applicationModule, provider, provider2);
    }

    public static GeoLocationService proxyProvidesGeoLocationService(ApplicationModule applicationModule, Context context, SharedPreferences sharedPreferences) {
        return (GeoLocationService) Preconditions.checkNotNull(applicationModule.providesGeoLocationService(context, sharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
