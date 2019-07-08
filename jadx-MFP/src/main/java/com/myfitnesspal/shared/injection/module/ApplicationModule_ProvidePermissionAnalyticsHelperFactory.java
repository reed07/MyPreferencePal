package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePermissionAnalyticsHelperFactory implements Factory<PermissionAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidePermissionAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public PermissionAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static PermissionAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidePermissionAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidePermissionAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidePermissionAnalyticsHelperFactory(applicationModule, provider);
    }

    public static PermissionAnalyticsHelper proxyProvidePermissionAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (PermissionAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providePermissionAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
