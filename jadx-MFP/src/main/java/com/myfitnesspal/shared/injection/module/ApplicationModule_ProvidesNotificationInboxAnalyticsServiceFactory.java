package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNotificationInboxAnalyticsServiceFactory implements Factory<NotificationInboxAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesNotificationInboxAnalyticsServiceFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public NotificationInboxAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static NotificationInboxAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesNotificationInboxAnalyticsService(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesNotificationInboxAnalyticsServiceFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesNotificationInboxAnalyticsServiceFactory(applicationModule, provider);
    }

    public static NotificationInboxAnalyticsHelper proxyProvidesNotificationInboxAnalyticsService(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (NotificationInboxAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesNotificationInboxAnalyticsService(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
