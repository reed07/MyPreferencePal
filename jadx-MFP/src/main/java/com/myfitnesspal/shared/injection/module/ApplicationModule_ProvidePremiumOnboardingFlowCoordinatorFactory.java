package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.onboarding.navigation.Navigator;
import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePremiumOnboardingFlowCoordinatorFactory implements Factory<PremiumOnboardingFlowCoordinator> {
    private final ApplicationModule module;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidePremiumOnboardingFlowCoordinatorFactory(ApplicationModule applicationModule, Provider<Navigator> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        this.module = applicationModule;
        this.navigatorProvider = provider;
        this.userEnergyServiceProvider = provider2;
        this.nutrientGoalServiceProvider = provider3;
    }

    public PremiumOnboardingFlowCoordinator get() {
        return provideInstance(this.module, this.navigatorProvider, this.userEnergyServiceProvider, this.nutrientGoalServiceProvider);
    }

    public static PremiumOnboardingFlowCoordinator provideInstance(ApplicationModule applicationModule, Provider<Navigator> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        return proxyProvidePremiumOnboardingFlowCoordinator(applicationModule, (Navigator) provider.get(), (UserEnergyService) provider2.get(), (NutrientGoalService) provider3.get());
    }

    public static ApplicationModule_ProvidePremiumOnboardingFlowCoordinatorFactory create(ApplicationModule applicationModule, Provider<Navigator> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        return new ApplicationModule_ProvidePremiumOnboardingFlowCoordinatorFactory(applicationModule, provider, provider2, provider3);
    }

    public static PremiumOnboardingFlowCoordinator proxyProvidePremiumOnboardingFlowCoordinator(ApplicationModule applicationModule, Navigator navigator, UserEnergyService userEnergyService, NutrientGoalService nutrientGoalService) {
        return (PremiumOnboardingFlowCoordinator) Preconditions.checkNotNull(applicationModule.providePremiumOnboardingFlowCoordinator(navigator, userEnergyService, nutrientGoalService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
