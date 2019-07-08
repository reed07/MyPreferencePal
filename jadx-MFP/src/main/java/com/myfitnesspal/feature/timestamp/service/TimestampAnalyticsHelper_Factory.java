package com.myfitnesspal.feature.timestamp.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TimestampAnalyticsHelper_Factory implements Factory<TimestampAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;

    public TimestampAnalyticsHelper_Factory(Provider<AnalyticsService> provider) {
        this.analyticsServiceProvider = provider;
    }

    public TimestampAnalyticsHelper get() {
        return provideInstance(this.analyticsServiceProvider);
    }

    public static TimestampAnalyticsHelper provideInstance(Provider<AnalyticsService> provider) {
        return new TimestampAnalyticsHelper((AnalyticsService) provider.get());
    }

    public static TimestampAnalyticsHelper_Factory create(Provider<AnalyticsService> provider) {
        return new TimestampAnalyticsHelper_Factory(provider);
    }

    public static TimestampAnalyticsHelper newTimestampAnalyticsHelper(AnalyticsService analyticsService) {
        return new TimestampAnalyticsHelper(analyticsService);
    }
}
