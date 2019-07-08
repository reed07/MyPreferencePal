package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitExerciseService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesAggregateExternalExerciseServiceFactory implements Factory<ExternalExerciseService> {
    private final Provider<GoogleFitExerciseService> googleFitProvider;
    private final ExternalSyncModule module;
    private final Provider<SHealthExerciseService> samsungHealthProvider;

    public ExternalSyncModule_ProvidesAggregateExternalExerciseServiceFactory(ExternalSyncModule externalSyncModule, Provider<GoogleFitExerciseService> provider, Provider<SHealthExerciseService> provider2) {
        this.module = externalSyncModule;
        this.googleFitProvider = provider;
        this.samsungHealthProvider = provider2;
    }

    public ExternalExerciseService get() {
        return provideInstance(this.module, this.googleFitProvider, this.samsungHealthProvider);
    }

    public static ExternalExerciseService provideInstance(ExternalSyncModule externalSyncModule, Provider<GoogleFitExerciseService> provider, Provider<SHealthExerciseService> provider2) {
        return proxyProvidesAggregateExternalExerciseService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ExternalSyncModule_ProvidesAggregateExternalExerciseServiceFactory create(ExternalSyncModule externalSyncModule, Provider<GoogleFitExerciseService> provider, Provider<SHealthExerciseService> provider2) {
        return new ExternalSyncModule_ProvidesAggregateExternalExerciseServiceFactory(externalSyncModule, provider, provider2);
    }

    public static ExternalExerciseService proxyProvidesAggregateExternalExerciseService(ExternalSyncModule externalSyncModule, Lazy<GoogleFitExerciseService> lazy, Lazy<SHealthExerciseService> lazy2) {
        return (ExternalExerciseService) Preconditions.checkNotNull(externalSyncModule.providesAggregateExternalExerciseService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
