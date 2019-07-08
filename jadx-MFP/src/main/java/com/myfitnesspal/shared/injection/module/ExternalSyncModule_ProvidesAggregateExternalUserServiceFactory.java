package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitUserService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesAggregateExternalUserServiceFactory implements Factory<ExternalUserService> {
    private final Provider<GoogleFitUserService> googleFitProvider;
    private final ExternalSyncModule module;
    private final Provider<SHealthUserService> samsungHealthProvider;

    public ExternalSyncModule_ProvidesAggregateExternalUserServiceFactory(ExternalSyncModule externalSyncModule, Provider<GoogleFitUserService> provider, Provider<SHealthUserService> provider2) {
        this.module = externalSyncModule;
        this.googleFitProvider = provider;
        this.samsungHealthProvider = provider2;
    }

    public ExternalUserService get() {
        return provideInstance(this.module, this.googleFitProvider, this.samsungHealthProvider);
    }

    public static ExternalUserService provideInstance(ExternalSyncModule externalSyncModule, Provider<GoogleFitUserService> provider, Provider<SHealthUserService> provider2) {
        return proxyProvidesAggregateExternalUserService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ExternalSyncModule_ProvidesAggregateExternalUserServiceFactory create(ExternalSyncModule externalSyncModule, Provider<GoogleFitUserService> provider, Provider<SHealthUserService> provider2) {
        return new ExternalSyncModule_ProvidesAggregateExternalUserServiceFactory(externalSyncModule, provider, provider2);
    }

    public static ExternalUserService proxyProvidesAggregateExternalUserService(ExternalSyncModule externalSyncModule, Lazy<GoogleFitUserService> lazy, Lazy<SHealthUserService> lazy2) {
        return (ExternalUserService) Preconditions.checkNotNull(externalSyncModule.providesAggregateExternalUserService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
