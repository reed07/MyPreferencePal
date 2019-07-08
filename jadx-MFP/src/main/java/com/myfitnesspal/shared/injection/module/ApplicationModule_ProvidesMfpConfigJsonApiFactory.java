package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApi;
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

public final class ApplicationModule_ProvidesMfpConfigJsonApiFactory implements Factory<MfpV2ConfigApi> {
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

    public ApplicationModule_ProvidesMfpConfigJsonApiFactory(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<ApiJsonMapper> provider6, Provider<Bus> provider7, Provider<AuthTokenProvider> provider8, Provider<CountryService> provider9, Provider<DeviceInfo> provider10) {
        this.module = applicationModule;
        this.apiUrlProvider = provider;
        this.userAgentProvider = provider2;
        this.deviceIdProvider = provider3;
        this.versionCodeProvider = provider4;
        this.mockInterceptorProvider = provider5;
        this.mapperProvider = provider6;
        this.messageBusProvider = provider7;
        this.authTokensProvider = provider8;
        this.countryServiceProvider = provider9;
        this.deviceInfoProvider = provider10;
    }

    public MfpV2ConfigApi get() {
        return provideInstance(this.module, this.apiUrlProvider, this.userAgentProvider, this.deviceIdProvider, this.versionCodeProvider, this.mockInterceptorProvider, this.mapperProvider, this.messageBusProvider, this.authTokensProvider, this.countryServiceProvider, this.deviceInfoProvider);
    }

    public static MfpV2ConfigApi provideInstance(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<ApiJsonMapper> provider6, Provider<Bus> provider7, Provider<AuthTokenProvider> provider8, Provider<CountryService> provider9, Provider<DeviceInfo> provider10) {
        return proxyProvidesMfpConfigJsonApi(applicationModule, DoubleCheck.lazy(provider), (UserAgentProvider) provider2.get(), (UUID) provider3.get(), ((Long) provider4.get()).longValue(), DoubleCheck.lazy(provider5), (ApiJsonMapper) provider6.get(), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static ApplicationModule_ProvidesMfpConfigJsonApiFactory create(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<ApiJsonMapper> provider6, Provider<Bus> provider7, Provider<AuthTokenProvider> provider8, Provider<CountryService> provider9, Provider<DeviceInfo> provider10) {
        ApplicationModule_ProvidesMfpConfigJsonApiFactory applicationModule_ProvidesMfpConfigJsonApiFactory = new ApplicationModule_ProvidesMfpConfigJsonApiFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
        return applicationModule_ProvidesMfpConfigJsonApiFactory;
    }

    public static MfpV2ConfigApi proxyProvidesMfpConfigJsonApi(ApplicationModule applicationModule, Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider2, UUID uuid, long j, Lazy<RequestInterceptor> lazy2, ApiJsonMapper apiJsonMapper, Lazy<Bus> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        return (MfpV2ConfigApi) Preconditions.checkNotNull(applicationModule.providesMfpConfigJsonApi(lazy, userAgentProvider2, uuid, j, lazy2, apiJsonMapper, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
