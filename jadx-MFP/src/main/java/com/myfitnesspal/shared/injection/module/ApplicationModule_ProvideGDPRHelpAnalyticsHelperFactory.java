package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideGDPRHelpAnalyticsHelperFactory implements Factory<GDPRHelpAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideGDPRHelpAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public GDPRHelpAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static GDPRHelpAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideGDPRHelpAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideGDPRHelpAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideGDPRHelpAnalyticsHelperFactory(applicationModule, provider);
    }

    public static GDPRHelpAnalyticsHelper proxyProvideGDPRHelpAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (GDPRHelpAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideGDPRHelpAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
