package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidePremiumUpsellNavigatorFactory implements Factory<PremiumUpsellNavigator> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidePremiumUpsellNavigatorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public PremiumUpsellNavigator get() {
        return provideInstance(this.module);
    }

    public static PremiumUpsellNavigator provideInstance(ApplicationModule applicationModule) {
        return proxyProvidePremiumUpsellNavigator(applicationModule);
    }

    public static ApplicationModule_ProvidePremiumUpsellNavigatorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidePremiumUpsellNavigatorFactory(applicationModule);
    }

    public static PremiumUpsellNavigator proxyProvidePremiumUpsellNavigator(ApplicationModule applicationModule) {
        return (PremiumUpsellNavigator) Preconditions.checkNotNull(applicationModule.providePremiumUpsellNavigator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
