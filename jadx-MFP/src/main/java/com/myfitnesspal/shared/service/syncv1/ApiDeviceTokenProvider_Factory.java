package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.shared.factory.DeviceUuidFactory;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ApiDeviceTokenProvider_Factory implements Factory<ApiDeviceTokenProvider> {
    private final Provider<DeviceUuidFactory> deviceUuidFactoryProvider;
    private final Provider<GlobalSettingsService> globalSettingsServiceProvider;

    public ApiDeviceTokenProvider_Factory(Provider<GlobalSettingsService> provider, Provider<DeviceUuidFactory> provider2) {
        this.globalSettingsServiceProvider = provider;
        this.deviceUuidFactoryProvider = provider2;
    }

    public ApiDeviceTokenProvider get() {
        return provideInstance(this.globalSettingsServiceProvider, this.deviceUuidFactoryProvider);
    }

    public static ApiDeviceTokenProvider provideInstance(Provider<GlobalSettingsService> provider, Provider<DeviceUuidFactory> provider2) {
        return new ApiDeviceTokenProvider(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApiDeviceTokenProvider_Factory create(Provider<GlobalSettingsService> provider, Provider<DeviceUuidFactory> provider2) {
        return new ApiDeviceTokenProvider_Factory(provider, provider2);
    }

    public static ApiDeviceTokenProvider newApiDeviceTokenProvider(Lazy<GlobalSettingsService> lazy, Lazy<DeviceUuidFactory> lazy2) {
        return new ApiDeviceTokenProvider(lazy, lazy2);
    }
}
