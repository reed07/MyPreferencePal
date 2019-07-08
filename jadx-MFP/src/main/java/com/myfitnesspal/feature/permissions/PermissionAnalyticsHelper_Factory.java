package com.myfitnesspal.feature.permissions;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PermissionAnalyticsHelper_Factory implements Factory<PermissionAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;

    public PermissionAnalyticsHelper_Factory(Provider<AnalyticsService> provider) {
        this.analyticsServiceProvider = provider;
    }

    public PermissionAnalyticsHelper get() {
        return provideInstance(this.analyticsServiceProvider);
    }

    public static PermissionAnalyticsHelper provideInstance(Provider<AnalyticsService> provider) {
        return new PermissionAnalyticsHelper(DoubleCheck.lazy(provider));
    }

    public static PermissionAnalyticsHelper_Factory create(Provider<AnalyticsService> provider) {
        return new PermissionAnalyticsHelper_Factory(provider);
    }

    public static PermissionAnalyticsHelper newPermissionAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new PermissionAnalyticsHelper(lazy);
    }
}
