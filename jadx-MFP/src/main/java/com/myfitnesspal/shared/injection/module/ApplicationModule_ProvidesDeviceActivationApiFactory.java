package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.DeviceActivationApi;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.squareup.otto.Bus;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesDeviceActivationApiFactory implements Factory<DeviceActivationApi> {
    private final Provider<MfpApiSettings> apiSettingsProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<UUID> deviceIdProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<ApiJsonMapper> mapperProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<RequestInterceptor> mockInterceptorProvider;
    private final ApplicationModule module;
    private final Provider<UserAgentProvider> userAgentProvider;
    private final Provider<Long> versionCodeProvider;

    public ApplicationModule_ProvidesDeviceActivationApiFactory(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<MfpApiSettings> provider2, Provider<UserAgentProvider> provider3, Provider<UUID> provider4, Provider<Long> provider5, Provider<RequestInterceptor> provider6, Provider<ApiJsonMapper> provider7, Provider<Bus> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        this.module = applicationModule;
        this.apiUrlProvider = provider;
        this.apiSettingsProvider = provider2;
        this.userAgentProvider = provider3;
        this.deviceIdProvider = provider4;
        this.versionCodeProvider = provider5;
        this.mockInterceptorProvider = provider6;
        this.mapperProvider = provider7;
        this.messageBusProvider = provider8;
        this.authTokensProvider = provider9;
        this.countryServiceProvider = provider10;
        this.deviceInfoProvider = provider11;
    }

    public DeviceActivationApi get() {
        return provideInstance(this.module, this.apiUrlProvider, this.apiSettingsProvider, this.userAgentProvider, this.deviceIdProvider, this.versionCodeProvider, this.mockInterceptorProvider, this.mapperProvider, this.messageBusProvider, this.authTokensProvider, this.countryServiceProvider, this.deviceInfoProvider);
    }

    public static DeviceActivationApi provideInstance(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<MfpApiSettings> provider2, Provider<UserAgentProvider> provider3, Provider<UUID> provider4, Provider<Long> provider5, Provider<RequestInterceptor> provider6, Provider<ApiJsonMapper> provider7, Provider<Bus> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        return proxyProvidesDeviceActivationApi(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), (UserAgentProvider) provider3.get(), (UUID) provider4.get(), ((Long) provider5.get()).longValue(), DoubleCheck.lazy(provider6), (ApiJsonMapper) provider7.get(), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11));
    }

    public static ApplicationModule_ProvidesDeviceActivationApiFactory create(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<MfpApiSettings> provider2, Provider<UserAgentProvider> provider3, Provider<UUID> provider4, Provider<Long> provider5, Provider<RequestInterceptor> provider6, Provider<ApiJsonMapper> provider7, Provider<Bus> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        ApplicationModule_ProvidesDeviceActivationApiFactory applicationModule_ProvidesDeviceActivationApiFactory = new ApplicationModule_ProvidesDeviceActivationApiFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return applicationModule_ProvidesDeviceActivationApiFactory;
    }

    public static DeviceActivationApi proxyProvidesDeviceActivationApi(ApplicationModule applicationModule, Lazy<ApiUrlProvider> lazy, Lazy<MfpApiSettings> lazy2, UserAgentProvider userAgentProvider2, UUID uuid, long j, Lazy<RequestInterceptor> lazy3, ApiJsonMapper apiJsonMapper, Lazy<Bus> lazy4, Lazy<AuthTokenProvider> lazy5, Lazy<CountryService> lazy6, Lazy<DeviceInfo> lazy7) {
        return (DeviceActivationApi) Preconditions.checkNotNull(applicationModule.providesDeviceActivationApi(lazy, lazy2, userAgentProvider2, uuid, j, lazy3, apiJsonMapper, lazy4, lazy5, lazy6, lazy7), "Cannot return null from a non-@Nullable @Provides method");
    }
}
