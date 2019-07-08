package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.squareup.otto.Bus;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesSHealthConnectionFactory implements Factory<SHealthConnection> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<Bus> busProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final ExternalSyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<StepService> stepServiceProvider;

    public ExternalSyncModule_ProvidesSHealthConnectionFactory(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<AnalyticsService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<StepService> provider4, Provider<ConfigService> provider5, Provider<Bus> provider6) {
        this.module = externalSyncModule;
        this.sessionProvider = provider;
        this.analyticsProvider = provider2;
        this.prefsProvider = provider3;
        this.stepServiceProvider = provider4;
        this.configServiceProvider = provider5;
        this.busProvider = provider6;
    }

    public SHealthConnection get() {
        return provideInstance(this.module, this.sessionProvider, this.analyticsProvider, this.prefsProvider, this.stepServiceProvider, this.configServiceProvider, this.busProvider);
    }

    public static SHealthConnection provideInstance(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<AnalyticsService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<StepService> provider4, Provider<ConfigService> provider5, Provider<Bus> provider6) {
        return proxyProvidesSHealthConnection(externalSyncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), (Bus) provider6.get());
    }

    public static ExternalSyncModule_ProvidesSHealthConnectionFactory create(ExternalSyncModule externalSyncModule, Provider<Session> provider, Provider<AnalyticsService> provider2, Provider<KeyedSharedPreferences> provider3, Provider<StepService> provider4, Provider<ConfigService> provider5, Provider<Bus> provider6) {
        ExternalSyncModule_ProvidesSHealthConnectionFactory externalSyncModule_ProvidesSHealthConnectionFactory = new ExternalSyncModule_ProvidesSHealthConnectionFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesSHealthConnectionFactory;
    }

    public static SHealthConnection proxyProvidesSHealthConnection(ExternalSyncModule externalSyncModule, Lazy<Session> lazy, Lazy<AnalyticsService> lazy2, Lazy<KeyedSharedPreferences> lazy3, Lazy<StepService> lazy4, Lazy<ConfigService> lazy5, Bus bus) {
        return (SHealthConnection) Preconditions.checkNotNull(externalSyncModule.providesSHealthConnection(lazy, lazy2, lazy3, lazy4, lazy5, bus), "Cannot return null from a non-@Nullable @Provides method");
    }
}
