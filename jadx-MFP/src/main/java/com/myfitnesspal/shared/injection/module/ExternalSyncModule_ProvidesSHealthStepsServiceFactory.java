package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthStepsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesSHealthStepsServiceFactory implements Factory<SHealthStepsService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<SHealthConnection> connectionProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final ExternalSyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<SyncService> syncServiceProvider;

    public ExternalSyncModule_ProvidesSHealthStepsServiceFactory(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<SyncService> provider3, Provider<DiaryService> provider4, Provider<StepService> provider5, Provider<ConfigService> provider6, Provider<AppGalleryService> provider7) {
        this.module = externalSyncModule;
        this.connectionProvider = provider;
        this.sessionProvider = provider2;
        this.syncServiceProvider = provider3;
        this.diaryServiceProvider = provider4;
        this.stepServiceProvider = provider5;
        this.configServiceProvider = provider6;
        this.appGalleryServiceProvider = provider7;
    }

    public SHealthStepsService get() {
        return provideInstance(this.module, this.connectionProvider, this.sessionProvider, this.syncServiceProvider, this.diaryServiceProvider, this.stepServiceProvider, this.configServiceProvider, this.appGalleryServiceProvider);
    }

    public static SHealthStepsService provideInstance(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<SyncService> provider3, Provider<DiaryService> provider4, Provider<StepService> provider5, Provider<ConfigService> provider6, Provider<AppGalleryService> provider7) {
        return proxyProvidesSHealthStepsService(externalSyncModule, (SHealthConnection) provider.get(), (Session) provider2.get(), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static ExternalSyncModule_ProvidesSHealthStepsServiceFactory create(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<SyncService> provider3, Provider<DiaryService> provider4, Provider<StepService> provider5, Provider<ConfigService> provider6, Provider<AppGalleryService> provider7) {
        ExternalSyncModule_ProvidesSHealthStepsServiceFactory externalSyncModule_ProvidesSHealthStepsServiceFactory = new ExternalSyncModule_ProvidesSHealthStepsServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return externalSyncModule_ProvidesSHealthStepsServiceFactory;
    }

    public static SHealthStepsService proxyProvidesSHealthStepsService(ExternalSyncModule externalSyncModule, SHealthConnection sHealthConnection, Session session, Lazy<SyncService> lazy, Lazy<DiaryService> lazy2, Lazy<StepService> lazy3, Lazy<ConfigService> lazy4, Lazy<AppGalleryService> lazy5) {
        return (SHealthStepsService) Preconditions.checkNotNull(externalSyncModule.providesSHealthStepsService(sHealthConnection, session, lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
