package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthExerciseService;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesSHealthExerciseServiceFactory implements Factory<SHealthExerciseService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<SHealthConnection> connectionProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseStringService> exerciseStringServiceProvider;
    private final ExternalSyncModule module;

    public ExternalSyncModule_ProvidesSHealthExerciseServiceFactory(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<DiaryService> provider3, Provider<AppGalleryService> provider4, Provider<ExerciseStringService> provider5) {
        this.module = externalSyncModule;
        this.connectionProvider = provider;
        this.configServiceProvider = provider2;
        this.diaryServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
        this.exerciseStringServiceProvider = provider5;
    }

    public SHealthExerciseService get() {
        return provideInstance(this.module, this.connectionProvider, this.configServiceProvider, this.diaryServiceProvider, this.appGalleryServiceProvider, this.exerciseStringServiceProvider);
    }

    public static SHealthExerciseService provideInstance(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<DiaryService> provider3, Provider<AppGalleryService> provider4, Provider<ExerciseStringService> provider5) {
        return proxyProvidesSHealthExerciseService(externalSyncModule, (SHealthConnection) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ExternalSyncModule_ProvidesSHealthExerciseServiceFactory create(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<ConfigService> provider2, Provider<DiaryService> provider3, Provider<AppGalleryService> provider4, Provider<ExerciseStringService> provider5) {
        ExternalSyncModule_ProvidesSHealthExerciseServiceFactory externalSyncModule_ProvidesSHealthExerciseServiceFactory = new ExternalSyncModule_ProvidesSHealthExerciseServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5);
        return externalSyncModule_ProvidesSHealthExerciseServiceFactory;
    }

    public static SHealthExerciseService proxyProvidesSHealthExerciseService(ExternalSyncModule externalSyncModule, SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<DiaryService> lazy2, Lazy<AppGalleryService> lazy3, Lazy<ExerciseStringService> lazy4) {
        return (SHealthExerciseService) Preconditions.checkNotNull(externalSyncModule.providesSHealthExerciseService(sHealthConnection, lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
