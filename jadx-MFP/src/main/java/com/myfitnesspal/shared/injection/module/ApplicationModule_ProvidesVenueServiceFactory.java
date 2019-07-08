package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.location.LocationService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesVenueServiceFactory implements Factory<VenueService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesVenueServiceFactory(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<LocationService> provider2) {
        this.module = applicationModule;
        this.apiProvider = provider;
        this.locationServiceProvider = provider2;
    }

    public VenueService get() {
        return provideInstance(this.module, this.apiProvider, this.locationServiceProvider);
    }

    public static VenueService provideInstance(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<LocationService> provider2) {
        return proxyProvidesVenueService(applicationModule, provider, DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidesVenueServiceFactory create(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Provider<LocationService> provider2) {
        return new ApplicationModule_ProvidesVenueServiceFactory(applicationModule, provider, provider2);
    }

    public static VenueService proxyProvidesVenueService(ApplicationModule applicationModule, Provider<MfpV2Api> provider, Lazy<LocationService> lazy) {
        return (VenueService) Preconditions.checkNotNull(applicationModule.providesVenueService(provider, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
