package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidePremiumUpsellCoordinatorFactory implements Factory<PremiumUpsellCoordinator> {
    private final Provider<ConfigService> configServiceProvider;
    private final ApplicationModule module;
    private final Provider<PremiumUpsellNavigator> navigatorProvider;
    private final Provider<PremiumService> premiumServiceProvider;

    public ApplicationModule_ProvidePremiumUpsellCoordinatorFactory(ApplicationModule applicationModule, Provider<PremiumUpsellNavigator> provider, Provider<ConfigService> provider2, Provider<PremiumService> provider3) {
        this.module = applicationModule;
        this.navigatorProvider = provider;
        this.configServiceProvider = provider2;
        this.premiumServiceProvider = provider3;
    }

    public PremiumUpsellCoordinator get() {
        return provideInstance(this.module, this.navigatorProvider, this.configServiceProvider, this.premiumServiceProvider);
    }

    public static PremiumUpsellCoordinator provideInstance(ApplicationModule applicationModule, Provider<PremiumUpsellNavigator> provider, Provider<ConfigService> provider2, Provider<PremiumService> provider3) {
        return proxyProvidePremiumUpsellCoordinator(applicationModule, (PremiumUpsellNavigator) provider.get(), (ConfigService) provider2.get(), (PremiumService) provider3.get());
    }

    public static ApplicationModule_ProvidePremiumUpsellCoordinatorFactory create(ApplicationModule applicationModule, Provider<PremiumUpsellNavigator> provider, Provider<ConfigService> provider2, Provider<PremiumService> provider3) {
        return new ApplicationModule_ProvidePremiumUpsellCoordinatorFactory(applicationModule, provider, provider2, provider3);
    }

    public static PremiumUpsellCoordinator proxyProvidePremiumUpsellCoordinator(ApplicationModule applicationModule, PremiumUpsellNavigator premiumUpsellNavigator, ConfigService configService, PremiumService premiumService) {
        return (PremiumUpsellCoordinator) Preconditions.checkNotNull(applicationModule.providePremiumUpsellCoordinator(premiumUpsellNavigator, configService, premiumService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
