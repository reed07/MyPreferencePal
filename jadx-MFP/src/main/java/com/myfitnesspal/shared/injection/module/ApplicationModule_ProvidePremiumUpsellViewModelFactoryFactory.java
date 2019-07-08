package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator;
import com.myfitnesspal.feature.premium.ui.viewmodel.PremiumUpsellViewModelFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePremiumUpsellViewModelFactoryFactory implements Factory<PremiumUpsellViewModelFactory> {
    private final Provider<PremiumUpsellCoordinator> coordinatorProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidePremiumUpsellViewModelFactoryFactory(ApplicationModule applicationModule, Provider<PremiumUpsellCoordinator> provider) {
        this.module = applicationModule;
        this.coordinatorProvider = provider;
    }

    public PremiumUpsellViewModelFactory get() {
        return provideInstance(this.module, this.coordinatorProvider);
    }

    public static PremiumUpsellViewModelFactory provideInstance(ApplicationModule applicationModule, Provider<PremiumUpsellCoordinator> provider) {
        return proxyProvidePremiumUpsellViewModelFactory(applicationModule, (PremiumUpsellCoordinator) provider.get());
    }

    public static ApplicationModule_ProvidePremiumUpsellViewModelFactoryFactory create(ApplicationModule applicationModule, Provider<PremiumUpsellCoordinator> provider) {
        return new ApplicationModule_ProvidePremiumUpsellViewModelFactoryFactory(applicationModule, provider);
    }

    public static PremiumUpsellViewModelFactory proxyProvidePremiumUpsellViewModelFactory(ApplicationModule applicationModule, PremiumUpsellCoordinator premiumUpsellCoordinator) {
        return (PremiumUpsellViewModelFactory) Preconditions.checkNotNull(applicationModule.providePremiumUpsellViewModelFactory(premiumUpsellCoordinator), "Cannot return null from a non-@Nullable @Provides method");
    }
}
