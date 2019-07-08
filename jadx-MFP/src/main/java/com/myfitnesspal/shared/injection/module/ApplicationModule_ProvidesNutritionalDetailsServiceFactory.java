package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.nutrition.service.NutritionDetailsService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutritionalDetailsServiceFactory implements Factory<NutritionDetailsService> {
    private final Provider<Context> contextProvider;
    private final Provider<LocalSettingsService> localSettingsServiceLazyProvider;
    private final ApplicationModule module;
    private final Provider<PremiumService> premiumServiceLazyProvider;

    public ApplicationModule_ProvidesNutritionalDetailsServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<PremiumService> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.localSettingsServiceLazyProvider = provider2;
        this.premiumServiceLazyProvider = provider3;
    }

    public NutritionDetailsService get() {
        return provideInstance(this.module, this.contextProvider, this.localSettingsServiceLazyProvider, this.premiumServiceLazyProvider);
    }

    public static NutritionDetailsService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<PremiumService> provider3) {
        return proxyProvidesNutritionalDetailsService(applicationModule, (Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesNutritionalDetailsServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<LocalSettingsService> provider2, Provider<PremiumService> provider3) {
        return new ApplicationModule_ProvidesNutritionalDetailsServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static NutritionDetailsService proxyProvidesNutritionalDetailsService(ApplicationModule applicationModule, Context context, Lazy<LocalSettingsService> lazy, Lazy<PremiumService> lazy2) {
        return (NutritionDetailsService) Preconditions.checkNotNull(applicationModule.providesNutritionalDetailsService(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
