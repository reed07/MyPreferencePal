package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNewsFeedAnalyticsHelperFactory implements Factory<NewsFeedAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final ApplicationModule module;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;

    public ApplicationModule_ProvidesNewsFeedAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2, Provider<UserApplicationSettingsService> provider3) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
        this.configServiceProvider = provider2;
        this.userApplicationSettingsServiceProvider = provider3;
    }

    public NewsFeedAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider, this.configServiceProvider, this.userApplicationSettingsServiceProvider);
    }

    public static NewsFeedAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2, Provider<UserApplicationSettingsService> provider3) {
        return proxyProvidesNewsFeedAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesNewsFeedAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider, Provider<ConfigService> provider2, Provider<UserApplicationSettingsService> provider3) {
        return new ApplicationModule_ProvidesNewsFeedAnalyticsHelperFactory(applicationModule, provider, provider2, provider3);
    }

    public static NewsFeedAnalyticsHelper proxyProvidesNewsFeedAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2, Lazy<UserApplicationSettingsService> lazy3) {
        return (NewsFeedAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesNewsFeedAnalyticsHelper(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
