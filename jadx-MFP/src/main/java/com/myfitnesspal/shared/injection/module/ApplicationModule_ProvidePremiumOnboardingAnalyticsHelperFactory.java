package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePremiumOnboardingAnalyticsHelperFactory implements Factory<PremiumOnboardingAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<LocalSettingsService> localSettingsServiceLazyProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidePremiumOnboardingAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AnalyticsService> provider2) {
        this.module = applicationModule;
        this.localSettingsServiceLazyProvider = provider;
        this.analyticsProvider = provider2;
    }

    public PremiumOnboardingAnalyticsHelper get() {
        return provideInstance(this.module, this.localSettingsServiceLazyProvider, this.analyticsProvider);
    }

    public static PremiumOnboardingAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AnalyticsService> provider2) {
        return proxyProvidePremiumOnboardingAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidePremiumOnboardingAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<AnalyticsService> provider2) {
        return new ApplicationModule_ProvidePremiumOnboardingAnalyticsHelperFactory(applicationModule, provider, provider2);
    }

    public static PremiumOnboardingAnalyticsHelper proxyProvidePremiumOnboardingAnalyticsHelper(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<AnalyticsService> lazy2) {
        return (PremiumOnboardingAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providePremiumOnboardingAnalyticsHelper(lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
