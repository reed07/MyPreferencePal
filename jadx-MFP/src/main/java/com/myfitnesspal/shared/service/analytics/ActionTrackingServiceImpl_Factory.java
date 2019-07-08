package com.myfitnesspal.shared.service.analytics;

import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ActionTrackingServiceImpl_Factory implements Factory<ActionTrackingServiceImpl> {
    private final Provider<AnalyticsService> analyticsServiceProvider;

    public ActionTrackingServiceImpl_Factory(Provider<AnalyticsService> provider) {
        this.analyticsServiceProvider = provider;
    }

    public ActionTrackingServiceImpl get() {
        return provideInstance(this.analyticsServiceProvider);
    }

    public static ActionTrackingServiceImpl provideInstance(Provider<AnalyticsService> provider) {
        return new ActionTrackingServiceImpl(DoubleCheck.lazy(provider));
    }

    public static ActionTrackingServiceImpl_Factory create(Provider<AnalyticsService> provider) {
        return new ActionTrackingServiceImpl_Factory(provider);
    }

    public static ActionTrackingServiceImpl newActionTrackingServiceImpl(Lazy<AnalyticsService> lazy) {
        return new ActionTrackingServiceImpl(lazy);
    }
}
