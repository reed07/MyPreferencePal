package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.settings.model.ABTestSettings;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApi;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.config.Configuration;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ServiceModule_ProvidesConfigServiceFactory implements Factory<ConfigService> {
    private final Provider<ABTestSettings> abTestSettingsProvider;
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<MfpV2ConfigApi> apiProvider;
    private final Provider<Cache<Configuration>> configCacheProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<UUID> deviceIdProvider;
    private final ServiceModule module;
    private final Provider<Session> sessionProvider;
    private final Provider<Long> versionCodeProvider;
    private final Provider<String> versionNameProvider;

    public ServiceModule_ProvidesConfigServiceFactory(ServiceModule serviceModule, Provider<MfpV2ConfigApi> provider, Provider<Cache<Configuration>> provider2, Provider<Session> provider3, Provider<ABTestSettings> provider4, Provider<UUID> provider5, Provider<String> provider6, Provider<Long> provider7, Provider<AnalyticsService> provider8, Provider<CountryService> provider9) {
        this.module = serviceModule;
        this.apiProvider = provider;
        this.configCacheProvider = provider2;
        this.sessionProvider = provider3;
        this.abTestSettingsProvider = provider4;
        this.deviceIdProvider = provider5;
        this.versionNameProvider = provider6;
        this.versionCodeProvider = provider7;
        this.analyticsServiceProvider = provider8;
        this.countryServiceProvider = provider9;
    }

    public ConfigService get() {
        return provideInstance(this.module, this.apiProvider, this.configCacheProvider, this.sessionProvider, this.abTestSettingsProvider, this.deviceIdProvider, this.versionNameProvider, this.versionCodeProvider, this.analyticsServiceProvider, this.countryServiceProvider);
    }

    public static ConfigService provideInstance(ServiceModule serviceModule, Provider<MfpV2ConfigApi> provider, Provider<Cache<Configuration>> provider2, Provider<Session> provider3, Provider<ABTestSettings> provider4, Provider<UUID> provider5, Provider<String> provider6, Provider<Long> provider7, Provider<AnalyticsService> provider8, Provider<CountryService> provider9) {
        return proxyProvidesConfigService(serviceModule, provider, (Cache) provider2.get(), DoubleCheck.lazy(provider3), (ABTestSettings) provider4.get(), (UUID) provider5.get(), (String) provider6.get(), ((Long) provider7.get()).longValue(), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9));
    }

    public static ServiceModule_ProvidesConfigServiceFactory create(ServiceModule serviceModule, Provider<MfpV2ConfigApi> provider, Provider<Cache<Configuration>> provider2, Provider<Session> provider3, Provider<ABTestSettings> provider4, Provider<UUID> provider5, Provider<String> provider6, Provider<Long> provider7, Provider<AnalyticsService> provider8, Provider<CountryService> provider9) {
        ServiceModule_ProvidesConfigServiceFactory serviceModule_ProvidesConfigServiceFactory = new ServiceModule_ProvidesConfigServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
        return serviceModule_ProvidesConfigServiceFactory;
    }

    public static ConfigService proxyProvidesConfigService(ServiceModule serviceModule, Provider<MfpV2ConfigApi> provider, Cache<Configuration> cache, Lazy<Session> lazy, ABTestSettings aBTestSettings, UUID uuid, String str, long j, Lazy<AnalyticsService> lazy2, Lazy<CountryService> lazy3) {
        return (ConfigService) Preconditions.checkNotNull(serviceModule.providesConfigService(provider, cache, lazy, aBTestSettings, uuid, str, j, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
