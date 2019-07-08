package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodMapperFactory implements Factory<FoodMapper> {
    private final Provider<FoodPortionMapper> foodPortionMapperProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFoodMapperFactory(ApplicationModule applicationModule, Provider<FoodPortionMapper> provider) {
        this.module = applicationModule;
        this.foodPortionMapperProvider = provider;
    }

    public FoodMapper get() {
        return provideInstance(this.module, this.foodPortionMapperProvider);
    }

    public static FoodMapper provideInstance(ApplicationModule applicationModule, Provider<FoodPortionMapper> provider) {
        return proxyProvidesFoodMapper(applicationModule, (FoodPortionMapper) provider.get());
    }

    public static ApplicationModule_ProvidesFoodMapperFactory create(ApplicationModule applicationModule, Provider<FoodPortionMapper> provider) {
        return new ApplicationModule_ProvidesFoodMapperFactory(applicationModule, provider);
    }

    public static FoodMapper proxyProvidesFoodMapper(ApplicationModule applicationModule, FoodPortionMapper foodPortionMapper) {
        return (FoodMapper) Preconditions.checkNotNull(applicationModule.providesFoodMapper(foodPortionMapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
