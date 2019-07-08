package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.model.mapper.ApiBinaryMapperBase;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.squareup.otto.Bus;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApplicationModule_ProvideBinaryCtorArgsFactory implements Factory<ApiBinaryConstructorArgs> {
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<UUID> deviceIdProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<BinaryEncoder> encoderProvider;
    private final Provider<ApiBinaryMapperBase> mapperProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<RequestInterceptor> mockInterceptorProvider;
    private final ApplicationModule module;
    private final Provider<UserAgentProvider> userAgentProvider;
    private final Provider<Long> versionCodeProvider;

    public ApplicationModule_ProvideBinaryCtorArgsFactory(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<Bus> provider6, Provider<BinaryEncoder> provider7, Provider<ApiBinaryMapperBase> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        this.module = applicationModule;
        this.apiUrlProvider = provider;
        this.userAgentProvider = provider2;
        this.deviceIdProvider = provider3;
        this.versionCodeProvider = provider4;
        this.mockInterceptorProvider = provider5;
        this.messageBusProvider = provider6;
        this.encoderProvider = provider7;
        this.mapperProvider = provider8;
        this.authTokensProvider = provider9;
        this.countryServiceProvider = provider10;
        this.deviceInfoProvider = provider11;
    }

    public ApiBinaryConstructorArgs get() {
        return provideInstance(this.module, this.apiUrlProvider, this.userAgentProvider, this.deviceIdProvider, this.versionCodeProvider, this.mockInterceptorProvider, this.messageBusProvider, this.encoderProvider, this.mapperProvider, this.authTokensProvider, this.countryServiceProvider, this.deviceInfoProvider);
    }

    public static ApiBinaryConstructorArgs provideInstance(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<Bus> provider6, Provider<BinaryEncoder> provider7, Provider<ApiBinaryMapperBase> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        return proxyProvideBinaryCtorArgs(applicationModule, DoubleCheck.lazy(provider), (UserAgentProvider) provider2.get(), (UUID) provider3.get(), ((Long) provider4.get()).longValue(), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), provider7, (ApiBinaryMapperBase) provider8.get(), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10), DoubleCheck.lazy(provider11));
    }

    public static ApplicationModule_ProvideBinaryCtorArgsFactory create(ApplicationModule applicationModule, Provider<ApiUrlProvider> provider, Provider<UserAgentProvider> provider2, Provider<UUID> provider3, Provider<Long> provider4, Provider<RequestInterceptor> provider5, Provider<Bus> provider6, Provider<BinaryEncoder> provider7, Provider<ApiBinaryMapperBase> provider8, Provider<AuthTokenProvider> provider9, Provider<CountryService> provider10, Provider<DeviceInfo> provider11) {
        ApplicationModule_ProvideBinaryCtorArgsFactory applicationModule_ProvideBinaryCtorArgsFactory = new ApplicationModule_ProvideBinaryCtorArgsFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return applicationModule_ProvideBinaryCtorArgsFactory;
    }

    public static ApiBinaryConstructorArgs proxyProvideBinaryCtorArgs(ApplicationModule applicationModule, Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider2, UUID uuid, long j, Lazy<RequestInterceptor> lazy2, Lazy<Bus> lazy3, Provider<BinaryEncoder> provider, ApiBinaryMapperBase apiBinaryMapperBase, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        return (ApiBinaryConstructorArgs) Preconditions.checkNotNull(applicationModule.provideBinaryCtorArgs(lazy, userAgentProvider2, uuid, j, lazy2, lazy3, provider, apiBinaryMapperBase, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
