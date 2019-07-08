package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SyncFirstRequestPacket_MembersInjector implements MembersInjector<SyncFirstRequestPacket> {
    private final Provider<ApiDeviceTokenProvider> apiDeviceTokenProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<SyncPointerService> syncPointerServiceProvider;
    private final Provider<byte[]> uuidBytesProvider;
    private final Provider<Long> versionCodeProvider;

    public SyncFirstRequestPacket_MembersInjector(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4, Provider<SyncPointerService> provider5) {
        this.versionCodeProvider = provider;
        this.apiDeviceTokenProvider = provider2;
        this.uuidBytesProvider = provider3;
        this.appSettingsProvider = provider4;
        this.syncPointerServiceProvider = provider5;
    }

    public static MembersInjector<SyncFirstRequestPacket> create(Provider<Long> provider, Provider<ApiDeviceTokenProvider> provider2, Provider<byte[]> provider3, Provider<AppSettings> provider4, Provider<SyncPointerService> provider5) {
        SyncFirstRequestPacket_MembersInjector syncFirstRequestPacket_MembersInjector = new SyncFirstRequestPacket_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return syncFirstRequestPacket_MembersInjector;
    }

    public void injectMembers(SyncFirstRequestPacket syncFirstRequestPacket) {
        ApiRequestPacketImpl_MembersInjector.injectVersionCode(syncFirstRequestPacket, ((Long) this.versionCodeProvider.get()).longValue());
        ApiRequestPacketImpl_MembersInjector.injectApiDeviceTokenProvider(syncFirstRequestPacket, (ApiDeviceTokenProvider) this.apiDeviceTokenProvider.get());
        ApiRequestPacketImpl_MembersInjector.injectUuidBytes(syncFirstRequestPacket, (byte[]) this.uuidBytesProvider.get());
        ApiRequestPacketImpl_MembersInjector.injectAppSettings(syncFirstRequestPacket, (AppSettings) this.appSettingsProvider.get());
        injectSyncPointerService(syncFirstRequestPacket, (SyncPointerService) this.syncPointerServiceProvider.get());
    }

    public static void injectSyncPointerService(SyncFirstRequestPacket syncFirstRequestPacket, SyncPointerService syncPointerService) {
        syncFirstRequestPacket.syncPointerService = syncPointerService;
    }
}
