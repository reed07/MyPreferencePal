package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitUserService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesGoogleFitUserServiceFactory implements Factory<GoogleFitUserService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<SharedPreferences> fitClientPreferencesProvider;
    private final Provider<GoogleFitClient> googleFitClientProvider;
    private final ExternalSyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ExternalSyncModule_ProvidesGoogleFitUserServiceFactory(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<GoogleFitClient> provider3, Provider<Session> provider4, Provider<ConfigService> provider5, Provider<UserWeightService> provider6, Provider<AppGalleryService> provider7, Provider<AnalyticsService> provider8, Provider<KeyedSharedPreferences> provider9) {
        this.module = externalSyncModule;
        this.contextProvider = provider;
        this.fitClientPreferencesProvider = provider2;
        this.googleFitClientProvider = provider3;
        this.sessionProvider = provider4;
        this.configServiceProvider = provider5;
        this.userWeightServiceProvider = provider6;
        this.appGalleryServiceProvider = provider7;
        this.analyticsServiceProvider = provider8;
        this.prefsProvider = provider9;
    }

    public GoogleFitUserService get() {
        return provideInstance(this.module, this.contextProvider, this.fitClientPreferencesProvider, this.googleFitClientProvider, this.sessionProvider, this.configServiceProvider, this.userWeightServiceProvider, this.appGalleryServiceProvider, this.analyticsServiceProvider, this.prefsProvider);
    }

    public static GoogleFitUserService provideInstance(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<GoogleFitClient> provider3, Provider<Session> provider4, Provider<ConfigService> provider5, Provider<UserWeightService> provider6, Provider<AppGalleryService> provider7, Provider<AnalyticsService> provider8, Provider<KeyedSharedPreferences> provider9) {
        return proxyProvidesGoogleFitUserService(externalSyncModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static ExternalSyncModule_ProvidesGoogleFitUserServiceFactory create(ExternalSyncModule externalSyncModule, Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<GoogleFitClient> provider3, Provider<Session> provider4, Provider<ConfigService> provider5, Provider<UserWeightService> provider6, Provider<AppGalleryService> provider7, Provider<AnalyticsService> provider8, Provider<KeyedSharedPreferences> provider9) {
        ExternalSyncModule_ProvidesGoogleFitUserServiceFactory externalSyncModule_ProvidesGoogleFitUserServiceFactory = new ExternalSyncModule_ProvidesGoogleFitUserServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return externalSyncModule_ProvidesGoogleFitUserServiceFactory;
    }

    public static GoogleFitUserService proxyProvidesGoogleFitUserService(ExternalSyncModule externalSyncModule, Context context, Lazy<SharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, Lazy<UserWeightService> lazy5, Lazy<AppGalleryService> lazy6, Lazy<AnalyticsService> lazy7, Lazy<KeyedSharedPreferences> lazy8) {
        return (GoogleFitUserService) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitUserService(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8), "Cannot return null from a non-@Nullable @Provides method");
    }
}
