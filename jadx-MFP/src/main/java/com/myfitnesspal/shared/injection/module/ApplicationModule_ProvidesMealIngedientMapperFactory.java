package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesMealIngedientMapperFactory implements Factory<MealIngredientMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMealIngedientMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public MealIngredientMapper get() {
        return provideInstance(this.module);
    }

    public static MealIngredientMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesMealIngedientMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesMealIngedientMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesMealIngedientMapperFactory(applicationModule);
    }

    public static MealIngredientMapper proxyProvidesMealIngedientMapper(ApplicationModule applicationModule) {
        return (MealIngredientMapper) Preconditions.checkNotNull(applicationModule.providesMealIngedientMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
