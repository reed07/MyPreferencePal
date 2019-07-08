package com.myfitnesspal.shared.api;

import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.install.CountryService;
import com.squareup.otto.Bus;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import java.util.UUID;

public class ApiConstructorArgs {
    private final Lazy<ApiUrlProvider> apiUrlProvider;
    private final Lazy<AuthTokenProvider> authTokenProvider;
    private final Lazy<CountryService> countryService;
    private final UUID deviceId;
    private final Lazy<DeviceInfo> deviceInfo;
    private final boolean isDebug = BuildConfiguration.getBuildConfiguration().isDebug();
    private final Lazy<Bus> messageBus;
    private final Lazy<RequestInterceptor> mockInterceptor;
    private final UserAgentProvider userAgentProvider;
    private final long versionCode;

    public ApiConstructorArgs(Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider2, UUID uuid, long j, Lazy<RequestInterceptor> lazy2, Lazy<Bus> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        this.apiUrlProvider = lazy;
        this.userAgentProvider = userAgentProvider2;
        this.deviceId = uuid;
        this.versionCode = j;
        this.mockInterceptor = lazy2;
        this.messageBus = lazy3;
        this.authTokenProvider = lazy4;
        this.countryService = lazy5;
        this.deviceInfo = lazy6;
    }

    public Lazy<ApiUrlProvider> getApiUrlProvider() {
        return this.apiUrlProvider;
    }

    public UserAgentProvider getUserAgentProvider() {
        return this.userAgentProvider;
    }

    public UUID getDeviceId() {
        return this.deviceId;
    }

    public long getVersionCode() {
        return this.versionCode;
    }

    public Lazy<RequestInterceptor> getMockInterceptor() {
        return this.mockInterceptor;
    }

    public Lazy<Bus> getMessageBus() {
        return this.messageBus;
    }

    public Lazy<AuthTokenProvider> getAuthTokenProvider() {
        return this.authTokenProvider;
    }

    public Lazy<CountryService> getCountryService() {
        return this.countryService;
    }

    public Lazy<DeviceInfo> getDeviceInfo() {
        return this.deviceInfo;
    }

    public boolean isDebug() {
        return this.isDebug;
    }
}
