package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitNutritionService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesGoogleFitNutrientServiceFactory implements Factory<GoogleFitNutritionService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final ExternalSyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<SyncService> syncServiceProvider;

    public ExternalSyncModule_ProvidesGoogleFitNutrientServiceFactory(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<GoogleFitClient> provider2, Provider<Session> provider3, Provider<ConfigService> provider4, Provider<AnalyticsService> provider5, Provider<SyncService> provider6) {
        this.module = externalSyncModule;
        this.contextProvider = provider;
        this.googleFitClientProvider = provider2;
        this.sessionProvider = provider3;
        this.configServiceProvider = provider4;
        this.analyticsServiceProvider = provider5;
        this.syncServiceProvider = provider6;
    }

    public GoogleFitNutritionService get() {
        return provideInstance(this.module, this.contextProvider, this.googleFitClientProvider, this.sessionProvider, this.configServiceProvider, this.analyticsServiceProvider, this.syncServiceProvider);
    }

    public static GoogleFitNutritionService provideInstance(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<GoogleFitClient> provider2, Provider<Session> provider3, Provider<ConfigService> provider4, Provider<AnalyticsService> provider5, Provider<SyncService> provider6) {
        return proxyProvidesGoogleFitNutrientService(externalSyncModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ExternalSyncModule_ProvidesGoogleFitNutrientServiceFactory create(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<GoogleFitClient> provider2, Provider<Session> provider3, Provider<ConfigService> provider4, Provider<AnalyticsService> provider5, Provider<SyncService> provider6) {
        ExternalSyncModule_ProvidesGoogleFitNutrientServiceFactory externalSyncModule_ProvidesGoogleFitNutrientServiceFactory = new ExternalSyncModule_ProvidesGoogleFitNutrientServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesGoogleFitNutrientServiceFactory;
    }

    public static GoogleFitNutritionService proxyProvidesGoogleFitNutrientService(ExternalSyncModule externalSyncModule, Context context, Lazy<GoogleFitClient> lazy, Lazy<Session> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<SyncService> lazy5) {
        return (GoogleFitNutritionService) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitNutrientService(context, lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
