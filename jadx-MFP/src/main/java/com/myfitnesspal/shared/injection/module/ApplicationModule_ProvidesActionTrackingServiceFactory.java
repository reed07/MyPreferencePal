package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesActionTrackingServiceFactory implements Factory<ActionTrackingService> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesActionTrackingServiceFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public ActionTrackingService get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static ActionTrackingService provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesActionTrackingService(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesActionTrackingServiceFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesActionTrackingServiceFactory(applicationModule, provider);
    }

    public static ActionTrackingService proxyProvidesActionTrackingService(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (ActionTrackingService) Preconditions.checkNotNull(applicationModule.providesActionTrackingService(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
