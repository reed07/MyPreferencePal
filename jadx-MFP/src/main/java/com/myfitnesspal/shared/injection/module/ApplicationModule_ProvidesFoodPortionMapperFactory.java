package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesFoodPortionMapperFactory implements Factory<FoodPortionMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFoodPortionMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public FoodPortionMapper get() {
        return provideInstance(this.module);
    }

    public static FoodPortionMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesFoodPortionMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesFoodPortionMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesFoodPortionMapperFactory(applicationModule);
    }

    public static FoodPortionMapper proxyProvidesFoodPortionMapper(ApplicationModule applicationModule) {
        return (FoodPortionMapper) Preconditions.checkNotNull(applicationModule.providesFoodPortionMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
