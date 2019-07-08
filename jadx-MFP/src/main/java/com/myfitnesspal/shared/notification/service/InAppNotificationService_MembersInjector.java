package com.myfitnesspal.shared.notification.service;

import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class InAppNotificationService_MembersInjector implements MembersInjector<InAppNotificationService> {
    private final Provider<Bus> busProvider;
    private final Provider<InAppNotificationManager> inAppNotificationManagerProvider;
    private final Provider<UserPropertiesService> userPropertiesServiceProvider;

    public InAppNotificationService_MembersInjector(Provider<UserPropertiesService> provider, Provider<Bus> provider2, Provider<InAppNotificationManager> provider3) {
        this.userPropertiesServiceProvider = provider;
        this.busProvider = provider2;
        this.inAppNotificationManagerProvider = provider3;
    }

    public static MembersInjector<InAppNotificationService> create(Provider<UserPropertiesService> provider, Provider<Bus> provider2, Provider<InAppNotificationManager> provider3) {
        return new InAppNotificationService_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(InAppNotificationService inAppNotificationService) {
        injectUserPropertiesService(inAppNotificationService, DoubleCheck.lazy(this.userPropertiesServiceProvider));
        injectBus(inAppNotificationService, DoubleCheck.lazy(this.busProvider));
        injectInAppNotificationManager(inAppNotificationService, DoubleCheck.lazy(this.inAppNotificationManagerProvider));
    }

    public static void injectUserPropertiesService(InAppNotificationService inAppNotificationService, Lazy<UserPropertiesService> lazy) {
        inAppNotificationService.userPropertiesService = lazy;
    }

    public static void injectBus(InAppNotificationService inAppNotificationService, Lazy<Bus> lazy) {
        inAppNotificationService.bus = lazy;
    }

    public static void injectInAppNotificationManager(InAppNotificationService inAppNotificationService, Lazy<InAppNotificationManager> lazy) {
        inAppNotificationService.inAppNotificationManager = lazy;
    }
}
