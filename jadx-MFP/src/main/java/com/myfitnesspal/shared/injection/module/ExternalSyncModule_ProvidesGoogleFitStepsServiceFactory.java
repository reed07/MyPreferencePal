package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitStepsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesGoogleFitStepsServiceFactory implements Factory<GoogleFitStepsService> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<SharedPreferences> fitClientStoreProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final Provider<KeyedSharedPreferences> googleFitStoreProvider;
    private final ExternalSyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;

    public ExternalSyncModule_ProvidesGoogleFitStepsServiceFactory(ExternalSyncModule externalSyncModule, Provider<SharedPreferences> provider, Provider<KeyedSharedPreferences> provider2, Provider<Session> provider3, Provider<GoogleFitClient> provider4, Provider<ConfigService> provider5, Provider<DiaryService> provider6, Provider<StepService> provider7) {
        this.module = externalSyncModule;
        this.fitClientStoreProvider = provider;
        this.googleFitStoreProvider = provider2;
        this.sessionProvider = provider3;
        this.googleFitClientProvider = provider4;
        this.configServiceProvider = provider5;
        this.diaryServiceProvider = provider6;
        this.stepServiceProvider = provider7;
    }

    public GoogleFitStepsService get() {
        return provideInstance(this.module, this.fitClientStoreProvider, this.googleFitStoreProvider, this.sessionProvider, this.googleFitClientProvider, this.configServiceProvider, this.diaryServiceProvider, this.stepServiceProvider);
    }

    public static GoogleFitStepsService provideInstance(ExternalSyncModule externalSyncModule, Provider<SharedPreferences> provider, Provider<KeyedSharedPreferences> provider2, Provider<Session> provider3, Provider<GoogleFitClient> provider4, Provider<ConfigService> provider5, Provider<DiaryService> provider6, Provider<StepService> provider7) {
        return proxyProvidesGoogleFitStepsService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7));
    }

    public static ExternalSyncModule_ProvidesGoogleFitStepsServiceFactory create(ExternalSyncModule externalSyncModule, Provider<SharedPreferences> provider, Provider<KeyedSharedPreferences> provider2, Provider<Session> provider3, Provider<GoogleFitClient> provider4, Provider<ConfigService> provider5, Provider<DiaryService> provider6, Provider<StepService> provider7) {
        ExternalSyncModule_ProvidesGoogleFitStepsServiceFactory externalSyncModule_ProvidesGoogleFitStepsServiceFactory = new ExternalSyncModule_ProvidesGoogleFitStepsServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return externalSyncModule_ProvidesGoogleFitStepsServiceFactory;
    }

    public static GoogleFitStepsService proxyProvidesGoogleFitStepsService(ExternalSyncModule externalSyncModule, Lazy<SharedPreferences> lazy, Lazy<KeyedSharedPreferences> lazy2, Lazy<Session> lazy3, Lazy<GoogleFitClient> lazy4, Lazy<ConfigService> lazy5, Lazy<DiaryService> lazy6, Lazy<StepService> lazy7) {
        return (GoogleFitStepsService) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitStepsService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}
