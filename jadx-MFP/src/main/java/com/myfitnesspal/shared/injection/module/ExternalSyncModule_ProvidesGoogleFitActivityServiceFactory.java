package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitExerciseService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesGoogleFitActivityServiceFactory implements Factory<GoogleFitExerciseService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final ExternalSyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;

    public ExternalSyncModule_ProvidesGoogleFitActivityServiceFactory(ExternalSyncModule externalSyncModule, Provider<KeyedSharedPreferences> provider, Provider<GoogleFitClient> provider2, Provider<ConfigService> provider3, Provider<AnalyticsService> provider4, Provider<DiaryService> provider5, Provider<AppGalleryService> provider6) {
        this.module = externalSyncModule;
        this.prefsProvider = provider;
        this.googleFitClientProvider = provider2;
        this.configServiceProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.diaryServiceProvider = provider5;
        this.appGalleryServiceProvider = provider6;
    }

    public GoogleFitExerciseService get() {
        return provideInstance(this.module, this.prefsProvider, this.googleFitClientProvider, this.configServiceProvider, this.analyticsServiceProvider, this.diaryServiceProvider, this.appGalleryServiceProvider);
    }

    public static GoogleFitExerciseService provideInstance(ExternalSyncModule externalSyncModule, Provider<KeyedSharedPreferences> provider, Provider<GoogleFitClient> provider2, Provider<ConfigService> provider3, Provider<AnalyticsService> provider4, Provider<DiaryService> provider5, Provider<AppGalleryService> provider6) {
        return proxyProvidesGoogleFitActivityService(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ExternalSyncModule_ProvidesGoogleFitActivityServiceFactory create(ExternalSyncModule externalSyncModule, Provider<KeyedSharedPreferences> provider, Provider<GoogleFitClient> provider2, Provider<ConfigService> provider3, Provider<AnalyticsService> provider4, Provider<DiaryService> provider5, Provider<AppGalleryService> provider6) {
        ExternalSyncModule_ProvidesGoogleFitActivityServiceFactory externalSyncModule_ProvidesGoogleFitActivityServiceFactory = new ExternalSyncModule_ProvidesGoogleFitActivityServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesGoogleFitActivityServiceFactory;
    }

    public static GoogleFitExerciseService proxyProvidesGoogleFitActivityService(ExternalSyncModule externalSyncModule, Lazy<KeyedSharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DiaryService> lazy5, Lazy<AppGalleryService> lazy6) {
        return (GoogleFitExerciseService) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitActivityService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
