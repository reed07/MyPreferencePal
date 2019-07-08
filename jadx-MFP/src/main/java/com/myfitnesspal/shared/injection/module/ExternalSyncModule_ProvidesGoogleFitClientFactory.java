package com.myfitnesspal.shared.injection.module;

import android.content.SharedPreferences;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitSubscriptionService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.squareup.otto.Bus;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesGoogleFitClientFactory implements Factory<GoogleFitClient> {
    private final Provider<SharedPreferences> globalPrefsProvider;
    private final Provider<GoogleFitSubscriptionService> googleFitSubscriptionServiceProvider;
    private final Provider<Bus> messageBusProvider;
    private final ExternalSyncModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;
    private final Provider<KeyedSharedPreferences> userPrefsProvider;

    public ExternalSyncModule_ProvidesGoogleFitClientFactory(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<SharedPreferences> provider2, Provider<KeyedSharedPreferences> provider3, Provider<GoogleFitSubscriptionService> provider4, Provider<StepService> provider5, Provider<Bus> provider6) {
        this.module = externalSyncModule;
        this.sessionProvider = provider;
        this.globalPrefsProvider = provider2;
        this.userPrefsProvider = provider3;
        this.googleFitSubscriptionServiceProvider = provider4;
        this.stepServiceProvider = provider5;
        this.messageBusProvider = provider6;
    }

    public GoogleFitClient get() {
        return provideInstance(this.module, this.sessionProvider, this.globalPrefsProvider, this.userPrefsProvider, this.googleFitSubscriptionServiceProvider, this.stepServiceProvider, this.messageBusProvider);
    }

    public static GoogleFitClient provideInstance(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<SharedPreferences> provider2, Provider<KeyedSharedPreferences> provider3, Provider<GoogleFitSubscriptionService> provider4, Provider<StepService> provider5, Provider<Bus> provider6) {
        return proxyProvidesGoogleFitClient(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ExternalSyncModule_ProvidesGoogleFitClientFactory create(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<SharedPreferences> provider2, Provider<KeyedSharedPreferences> provider3, Provider<GoogleFitSubscriptionService> provider4, Provider<StepService> provider5, Provider<Bus> provider6) {
        ExternalSyncModule_ProvidesGoogleFitClientFactory externalSyncModule_ProvidesGoogleFitClientFactory = new ExternalSyncModule_ProvidesGoogleFitClientFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesGoogleFitClientFactory;
    }

    public static GoogleFitClient proxyProvidesGoogleFitClient(ExternalSyncModule externalSyncModule, Lazy<Session> lazy, Lazy<SharedPreferences> lazy2, Lazy<KeyedSharedPreferences> lazy3, Lazy<GoogleFitSubscriptionService> lazy4, Lazy<StepService> lazy5, Lazy<Bus> lazy6) {
        return (GoogleFitClient) Preconditions.checkNotNull(externalSyncModule.providesGoogleFitClient(lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
