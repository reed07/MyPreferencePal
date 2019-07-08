package com.myfitnesspal.shared.notification.service;

import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NotificationInvokerService_MembersInjector implements MembersInjector<NotificationInvokerService> {
    private final Provider<ConfigService> configServiceProvider;

    public NotificationInvokerService_MembersInjector(Provider<ConfigService> provider) {
        this.configServiceProvider = provider;
    }

    public static MembersInjector<NotificationInvokerService> create(Provider<ConfigService> provider) {
        return new NotificationInvokerService_MembersInjector(provider);
    }

    public void injectMembers(NotificationInvokerService notificationInvokerService) {
        injectConfigService(notificationInvokerService, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectConfigService(NotificationInvokerService notificationInvokerService, Lazy<ConfigService> lazy) {
        notificationInvokerService.configService = lazy;
    }
}
