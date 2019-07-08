package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePremiumOnboardingViewFactoryFactory implements Factory<PremiumOnboardingViewModelFactory> {
    private final Provider<PremiumOnboardingAnalyticsHelper> analyticsHelperProvider;
    private final ApplicationModule module;
    private final Provider<PremiumOnboardingFlowCoordinator> premiumOnboardingFlowCoordinatorProvider;

    public ApplicationModule_ProvidePremiumOnboardingViewFactoryFactory(ApplicationModule applicationModule, Provider<PremiumOnboardingFlowCoordinator> provider, Provider<PremiumOnboardingAnalyticsHelper> provider2) {
        this.module = applicationModule;
        this.premiumOnboardingFlowCoordinatorProvider = provider;
        this.analyticsHelperProvider = provider2;
    }

    public PremiumOnboardingViewModelFactory get() {
        return provideInstance(this.module, this.premiumOnboardingFlowCoordinatorProvider, this.analyticsHelperProvider);
    }

    public static PremiumOnboardingViewModelFactory provideInstance(ApplicationModule applicationModule, Provider<PremiumOnboardingFlowCoordinator> provider, Provider<PremiumOnboardingAnalyticsHelper> provider2) {
        return proxyProvidePremiumOnboardingViewFactory(applicationModule, (PremiumOnboardingFlowCoordinator) provider.get(), DoubleCheck.lazy(provider2));
    }

    public static ApplicationModule_ProvidePremiumOnboardingViewFactoryFactory create(ApplicationModule applicationModule, Provider<PremiumOnboardingFlowCoordinator> provider, Provider<PremiumOnboardingAnalyticsHelper> provider2) {
        return new ApplicationModule_ProvidePremiumOnboardingViewFactoryFactory(applicationModule, provider, provider2);
    }

    public static PremiumOnboardingViewModelFactory proxyProvidePremiumOnboardingViewFactory(ApplicationModule applicationModule, PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator, Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        return (PremiumOnboardingViewModelFactory) Preconditions.checkNotNull(applicationModule.providePremiumOnboardingViewFactory(premiumOnboardingFlowCoordinator, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
