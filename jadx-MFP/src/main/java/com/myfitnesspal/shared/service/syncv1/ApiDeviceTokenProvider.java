package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.shared.factory.DeviceUuidFactory;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Provider;

public class ApiDeviceTokenProvider implements Provider<String> {
    private final Lazy<DeviceUuidFactory> deviceUuidFactory;
    private final Lazy<GlobalSettingsService> globalSettingsService;

    @Inject
    public ApiDeviceTokenProvider(Lazy<GlobalSettingsService> lazy, Lazy<DeviceUuidFactory> lazy2) {
        this.globalSettingsService = lazy;
        this.deviceUuidFactory = lazy2;
    }

    public String get() {
        return String.format("%s:%s", new Object[]{((GlobalSettingsService) this.globalSettingsService.get()).getEncodedFCMToken(), ((DeviceUuidFactory) this.deviceUuidFactory.get()).getEncryptedUuid()});
    }
}
