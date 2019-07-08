package com.myfitnesspal.shared.service.message;

import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.syncv1.ApiDeviceTokenProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MFPFirebaseMessagingService_MembersInjector implements MembersInjector<MFPFirebaseMessagingService> {
    private final Provider<ApiDeviceTokenProvider> apiDeviceTokenProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;

    public MFPFirebaseMessagingService_MembersInjector(Provider<PushNotificationManager> provider, Provider<ApiDeviceTokenProvider> provider2) {
        this.pushNotificationManagerProvider = provider;
        this.apiDeviceTokenProvider = provider2;
    }

    public static MembersInjector<MFPFirebaseMessagingService> create(Provider<PushNotificationManager> provider, Provider<ApiDeviceTokenProvider> provider2) {
        return new MFPFirebaseMessagingService_MembersInjector(provider, provider2);
    }

    public void injectMembers(MFPFirebaseMessagingService mFPFirebaseMessagingService) {
        injectPushNotificationManager(mFPFirebaseMessagingService, (PushNotificationManager) this.pushNotificationManagerProvider.get());
        injectApiDeviceTokenProvider(mFPFirebaseMessagingService, (ApiDeviceTokenProvider) this.apiDeviceTokenProvider.get());
    }

    public static void injectPushNotificationManager(MFPFirebaseMessagingService mFPFirebaseMessagingService, PushNotificationManager pushNotificationManager) {
        mFPFirebaseMessagingService.pushNotificationManager = pushNotificationManager;
    }

    public static void injectApiDeviceTokenProvider(MFPFirebaseMessagingService mFPFirebaseMessagingService, ApiDeviceTokenProvider apiDeviceTokenProvider2) {
        mFPFirebaseMessagingService.apiDeviceTokenProvider = apiDeviceTokenProvider2;
    }
}
