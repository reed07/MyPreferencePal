package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesPremiumAnalyticsHelperFactory implements Factory<PremiumAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesPremiumAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public PremiumAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static PremiumAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvidesPremiumAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesPremiumAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvidesPremiumAnalyticsHelperFactory(applicationModule, provider);
    }

    public static PremiumAnalyticsHelper proxyProvidesPremiumAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (PremiumAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesPremiumAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
