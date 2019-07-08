package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitStepsService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthStepsService;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesAggregateExternalStepsServiceFactory implements Factory<ExternalStepsService> {
    private final Provider<GoogleFitStepsService> googleFitProvider;
    private final ExternalSyncModule module;
    private final Provider<SHealthStepsService> samsungHealthProvider;

    public ExternalSyncModule_ProvidesAggregateExternalStepsServiceFactory(ExternalSyncModule externalSyncModule, Provider<GoogleFitStepsService> provider, Provider<SHealthStepsService> provider2) {
        this.module = externalSyncModule;
        this.googleFitProvider = provider;
        this.samsungHealthProvider = provider2;
    }

    public ExternalStepsService get() {
        return provideInstance(this.module, this.googleFitProvider, this.samsungHealthProvider);
    }

    public static ExternalStepsService provideInstance(ExternalSyncModule externalSyncModule, Provider<GoogleFitStepsService> provider, Provider<SHealthStepsService> provider2) {
        return proxyProvidesAggregateExternalStepsService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ExternalSyncModule_ProvidesAggregateExternalStepsServiceFactory create(ExternalSyncModule externalSyncModule, Provider<GoogleFitStepsService> provider, Provider<SHealthStepsService> provider2) {
        return new ExternalSyncModule_ProvidesAggregateExternalStepsServiceFactory(externalSyncModule, provider, provider2);
    }

    public static ExternalStepsService proxyProvidesAggregateExternalStepsService(ExternalSyncModule externalSyncModule, Lazy<GoogleFitStepsService> lazy, Lazy<SHealthStepsService> lazy2) {
        return (ExternalStepsService) Preconditions.checkNotNull(externalSyncModule.providesAggregateExternalStepsService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
