package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.feature.externalsync.service.ops.ExternalSyncOp;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesExternalSyncOpFactory implements Factory<ExternalSyncOp> {
    private final Provider<ExternalExerciseService> exerciseProvider;
    private final ExternalSyncModule module;
    private final Provider<ExternalNutritionService> nutritionProvider;
    private final Provider<ExternalStepsService> stepsProvider;
    private final Provider<ExternalUserService> userProvider;

    public ExternalSyncModule_ProvidesExternalSyncOpFactory(ExternalSyncModule externalSyncModule, Provider<ExternalExerciseService> provider, Provider<ExternalNutritionService> provider2, Provider<ExternalStepsService> provider3, Provider<ExternalUserService> provider4) {
        this.module = externalSyncModule;
        this.exerciseProvider = provider;
        this.nutritionProvider = provider2;
        this.stepsProvider = provider3;
        this.userProvider = provider4;
    }

    public ExternalSyncOp get() {
        return provideInstance(this.module, this.exerciseProvider, this.nutritionProvider, this.stepsProvider, this.userProvider);
    }

    public static ExternalSyncOp provideInstance(ExternalSyncModule externalSyncModule, Provider<ExternalExerciseService> provider, Provider<ExternalNutritionService> provider2, Provider<ExternalStepsService> provider3, Provider<ExternalUserService> provider4) {
        return proxyProvidesExternalSyncOp(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ExternalSyncModule_ProvidesExternalSyncOpFactory create(ExternalSyncModule externalSyncModule, Provider<ExternalExerciseService> provider, Provider<ExternalNutritionService> provider2, Provider<ExternalStepsService> provider3, Provider<ExternalUserService> provider4) {
        ExternalSyncModule_ProvidesExternalSyncOpFactory externalSyncModule_ProvidesExternalSyncOpFactory = new ExternalSyncModule_ProvidesExternalSyncOpFactory(externalSyncModule, provider, provider2, provider3, provider4);
        return externalSyncModule_ProvidesExternalSyncOpFactory;
    }

    public static ExternalSyncOp proxyProvidesExternalSyncOp(ExternalSyncModule externalSyncModule, Lazy<ExternalExerciseService> lazy, Lazy<ExternalNutritionService> lazy2, Lazy<ExternalStepsService> lazy3, Lazy<ExternalUserService> lazy4) {
        return (ExternalSyncOp) Preconditions.checkNotNull(externalSyncModule.providesExternalSyncOp(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
