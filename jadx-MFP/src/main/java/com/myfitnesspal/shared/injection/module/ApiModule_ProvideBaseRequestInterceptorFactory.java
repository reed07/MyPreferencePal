package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v2.interceptor.BaseHeaderRequestInterceptor;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.DeviceInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.UUID;
import javax.inject.Provider;

public final class ApiModule_ProvideBaseRequestInterceptorFactory implements Factory<BaseHeaderRequestInterceptor> {
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AuthTokenProvider> authTokenProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<UUID> deviceUuidProvider;
    private final ApiModule module;
    private final Provider<UserAgentProvider> userAgentProvider;

    public ApiModule_ProvideBaseRequestInterceptorFactory(ApiModule apiModule, Provider<CountryService> provider, Provider<DeviceInfo> provider2, Provider<UUID> provider3, Provider<UserAgentProvider> provider4, Provider<AuthTokenProvider> provider5, Provider<ApiUrlProvider> provider6) {
        this.module = apiModule;
        this.countryServiceProvider = provider;
        this.deviceInfoProvider = provider2;
        this.deviceUuidProvider = provider3;
        this.userAgentProvider = provider4;
        this.authTokenProvider = provider5;
        this.apiUrlProvider = provider6;
    }

    public BaseHeaderRequestInterceptor get() {
        return provideInstance(this.module, this.countryServiceProvider, this.deviceInfoProvider, this.deviceUuidProvider, this.userAgentProvider, this.authTokenProvider, this.apiUrlProvider);
    }

    public static BaseHeaderRequestInterceptor provideInstance(ApiModule apiModule, Provider<CountryService> provider, Provider<DeviceInfo> provider2, Provider<UUID> provider3, Provider<UserAgentProvider> provider4, Provider<AuthTokenProvider> provider5, Provider<ApiUrlProvider> provider6) {
        return proxyProvideBaseRequestInterceptor(apiModule, (CountryService) provider.get(), (DeviceInfo) provider2.get(), (UUID) provider3.get(), (UserAgentProvider) provider4.get(), (AuthTokenProvider) provider5.get(), (ApiUrlProvider) provider6.get());
    }

    public static ApiModule_ProvideBaseRequestInterceptorFactory create(ApiModule apiModule, Provider<CountryService> provider, Provider<DeviceInfo> provider2, Provider<UUID> provider3, Provider<UserAgentProvider> provider4, Provider<AuthTokenProvider> provider5, Provider<ApiUrlProvider> provider6) {
        ApiModule_ProvideBaseRequestInterceptorFactory apiModule_ProvideBaseRequestInterceptorFactory = new ApiModule_ProvideBaseRequestInterceptorFactory(apiModule, provider, provider2, provider3, provider4, provider5, provider6);
        return apiModule_ProvideBaseRequestInterceptorFactory;
    }

    public static BaseHeaderRequestInterceptor proxyProvideBaseRequestInterceptor(ApiModule apiModule, CountryService countryService, DeviceInfo deviceInfo, UUID uuid, UserAgentProvider userAgentProvider2, AuthTokenProvider authTokenProvider2, ApiUrlProvider apiUrlProvider2) {
        return (BaseHeaderRequestInterceptor) Preconditions.checkNotNull(apiModule.provideBaseRequestInterceptor(countryService, deviceInfo, uuid, userAgentProvider2, authTokenProvider2, apiUrlProvider2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
