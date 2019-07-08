package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitNutritionService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthNutritionService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesAggregateExternalNutritionServiceFactory implements Factory<ExternalNutritionService> {
    private final Provider<GoogleFitNutritionService> googleFitProvider;
    private final ExternalSyncModule module;
    private final Provider<SHealthNutritionService> samsungHealthProvider;

    public ExternalSyncModule_ProvidesAggregateExternalNutritionServiceFactory(ExternalSyncModule externalSyncModule, Provider<GoogleFitNutritionService> provider, Provider<SHealthNutritionService> provider2) {
        this.module = externalSyncModule;
        this.googleFitProvider = provider;
        this.samsungHealthProvider = provider2;
    }

    public ExternalNutritionService get() {
        return provideInstance(this.module, this.googleFitProvider, this.samsungHealthProvider);
    }

    public static ExternalNutritionService provideInstance(ExternalSyncModule externalSyncModule, Provider<GoogleFitNutritionService> provider, Provider<SHealthNutritionService> provider2) {
        return proxyProvidesAggregateExternalNutritionService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ExternalSyncModule_ProvidesAggregateExternalNutritionServiceFactory create(ExternalSyncModule externalSyncModule, Provider<GoogleFitNutritionService> provider, Provider<SHealthNutritionService> provider2) {
        return new ExternalSyncModule_ProvidesAggregateExternalNutritionServiceFactory(externalSyncModule, provider, provider2);
    }

    public static ExternalNutritionService proxyProvidesAggregateExternalNutritionService(ExternalSyncModule externalSyncModule, Lazy<GoogleFitNutritionService> lazy, Lazy<SHealthNutritionService> lazy2) {
        return (ExternalNutritionService) Preconditions.checkNotNull(externalSyncModule.providesAggregateExternalNutritionService(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
