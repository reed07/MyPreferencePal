package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ApiRequestPacketImpl_MembersInjector implements MembersInjector<ApiRequestPacketImpl> {
    private final Provider<ApiDeviceTokenProvider> apiDeviceTokenProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<byte[]> uuidBytesProvider;
    private final Provider<Long> versionCodeProvider;

    public ApiRequestPacketImpl_MembersInjector(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4) {
        this.versionCodeProvider = provider;
        this.apiDeviceTokenProvider = provider2;
        this.uuidBytesProvider = provider3;
        this.appSettingsProvider = provider4;
    }

    public static MembersInjector<ApiRequestPacketImpl> create(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4) {
        return new ApiRequestPacketImpl_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(ApiRequestPacketImpl apiRequestPacketImpl) {
        injectVersionCode(apiRequestPacketImpl, ((Long) this.versionCodeProvider.get()).longValue());
        injectApiDeviceTokenProvider(apiRequestPacketImpl, (ApiDeviceTokenProvider) this.apiDeviceTokenProvider.get());
        injectUuidBytes(apiRequestPacketImpl, (byte[]) this.uuidBytesProvider.get());
        injectAppSettings(apiRequestPacketImpl, (AppSettings) this.appSettingsProvider.get());
    }

    public static void injectVersionCode(ApiRequestPacketImpl apiRequestPacketImpl, long j) {
        apiRequestPacketImpl.versionCode = j;
    }

    public static void injectApiDeviceTokenProvider(ApiRequestPacketImpl apiRequestPacketImpl, ApiDeviceTokenProvider apiDeviceTokenProvider2) {
        apiRequestPacketImpl.apiDeviceTokenProvider = apiDeviceTokenProvider2;
    }

    public static void injectUuidBytes(ApiRequestPacketImpl apiRequestPacketImpl, byte[] bArr) {
        apiRequestPacketImpl.uuidBytes = bArr;
    }

    public static void injectAppSettings(ApiRequestPacketImpl apiRequestPacketImpl, AppSettings appSettings) {
        apiRequestPacketImpl.appSettings = appSettings;
    }
}
