package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideAdConsentsAnalyticsHelperFactory implements Factory<AdConsentsAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideAdConsentsAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public AdConsentsAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static AdConsentsAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideAdConsentsAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideAdConsentsAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideAdConsentsAnalyticsHelperFactory(applicationModule, provider);
    }

    public static AdConsentsAnalyticsHelper proxyProvideAdConsentsAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (AdConsentsAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideAdConsentsAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
