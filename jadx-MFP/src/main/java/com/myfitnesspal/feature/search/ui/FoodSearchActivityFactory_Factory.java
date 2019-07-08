package com.myfitnesspal.feature.search.ui;

import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodSearchActivityFactory_Factory implements Factory<FoodSearchActivityFactory> {
    private final Provider<ConfigService> configServiceProvider;

    public FoodSearchActivityFactory_Factory(Provider<ConfigService> provider) {
        this.configServiceProvider = provider;
    }

    public FoodSearchActivityFactory get() {
        return provideInstance(this.configServiceProvider);
    }

    public static FoodSearchActivityFactory provideInstance(Provider<ConfigService> provider) {
        return new FoodSearchActivityFactory((ConfigService) provider.get());
    }

    public static FoodSearchActivityFactory_Factory create(Provider<ConfigService> provider) {
        return new FoodSearchActivityFactory_Factory(provider);
    }

    public static FoodSearchActivityFactory newFoodSearchActivityFactory(ConfigService configService) {
        return new FoodSearchActivityFactory(configService);
    }
}
