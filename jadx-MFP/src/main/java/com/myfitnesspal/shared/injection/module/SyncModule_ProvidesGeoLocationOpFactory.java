package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.service.syncv2.ops.GeoLocationOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesGeoLocationOpFactory implements Factory<GeoLocationOp> {
    private final Provider<GeoLocationService> geoLocationServiceProvider;
    private final SyncModule module;

    public SyncModule_ProvidesGeoLocationOpFactory(SyncModule syncModule, Provider<GeoLocationService> provider) {
        this.module = syncModule;
        this.geoLocationServiceProvider = provider;
    }

    public GeoLocationOp get() {
        return provideInstance(this.module, this.geoLocationServiceProvider);
    }

    public static GeoLocationOp provideInstance(SyncModule syncModule, Provider<GeoLocationService> provider) {
        return proxyProvidesGeoLocationOp(syncModule, DoubleCheck.lazy(provider));
    }

    public static SyncModule_ProvidesGeoLocationOpFactory create(SyncModule syncModule, Provider<GeoLocationService> provider) {
        return new SyncModule_ProvidesGeoLocationOpFactory(syncModule, provider);
    }

    public static GeoLocationOp proxyProvidesGeoLocationOp(SyncModule syncModule, Lazy<GeoLocationService> lazy) {
        return (GeoLocationOp) Preconditions.checkNotNull(syncModule.providesGeoLocationOp(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
