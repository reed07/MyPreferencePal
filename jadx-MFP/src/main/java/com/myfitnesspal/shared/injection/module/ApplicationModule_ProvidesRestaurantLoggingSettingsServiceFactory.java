package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesRestaurantLoggingSettingsServiceFactory implements Factory<RestaurantLoggingSettingsService> {
    private final ApplicationModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;

    public ApplicationModule_ProvidesRestaurantLoggingSettingsServiceFactory(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        this.module = applicationModule;
        this.prefsProvider = provider;
    }

    public RestaurantLoggingSettingsService get() {
        return provideInstance(this.module, this.prefsProvider);
    }

    public static RestaurantLoggingSettingsService provideInstance(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        return proxyProvidesRestaurantLoggingSettingsService(applicationModule, (KeyedSharedPreferences) provider.get());
    }

    public static ApplicationModule_ProvidesRestaurantLoggingSettingsServiceFactory create(ApplicationModule applicationModule, Provider<KeyedSharedPreferences> provider) {
        return new ApplicationModule_ProvidesRestaurantLoggingSettingsServiceFactory(applicationModule, provider);
    }

    public static RestaurantLoggingSettingsService proxyProvidesRestaurantLoggingSettingsService(ApplicationModule applicationModule, KeyedSharedPreferences keyedSharedPreferences) {
        return (RestaurantLoggingSettingsService) Preconditions.checkNotNull(applicationModule.providesRestaurantLoggingSettingsService(keyedSharedPreferences), "Cannot return null from a non-@Nullable @Provides method");
    }
}
