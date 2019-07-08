package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutritionGraphPreferenceServiceFactory implements Factory<NutritionGraphPreferenceService> {
    private final ApplicationModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesNutritionGraphPreferenceServiceFactory(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public NutritionGraphPreferenceService get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static NutritionGraphPreferenceService provideInstance(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        return proxyProvidesNutritionGraphPreferenceService(applicationModule, (KeyedSharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesNutritionGraphPreferenceServiceFactory create(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        return new ApplicationModule_ProvidesNutritionGraphPreferenceServiceFactory(applicationModule, provider);
    }

    public static NutritionGraphPreferenceService proxyProvidesNutritionGraphPreferenceService(ApplicationModule applicationModule, KeyedSharedPreferences keyedSharedPreferences) {
        return (NutritionGraphPreferenceService) Preconditions.checkNotNull(applicationModule.providesNutritionGraphPreferenceService(keyedSharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
