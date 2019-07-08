package com.brightcove.player.drm;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class OfflineLicenseManagerFactory_ProvideLicenseManagerFactoryFactory implements Factory<LicenseManagerFactory> {
    private static final OfflineLicenseManagerFactory_ProvideLicenseManagerFactoryFactory INSTANCE = new OfflineLicenseManagerFactory_ProvideLicenseManagerFactoryFactory();

    public LicenseManagerFactory get() {
        return (LicenseManagerFactory) Preconditions.checkNotNull(OfflineLicenseManagerFactory.provideLicenseManagerFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<LicenseManagerFactory> create() {
        return INSTANCE;
    }
}
