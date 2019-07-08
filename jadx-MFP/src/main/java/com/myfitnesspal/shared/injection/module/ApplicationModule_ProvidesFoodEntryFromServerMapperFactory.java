package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.FoodEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesFoodEntryFromServerMapperFactory implements Factory<FoodEntryFromServerMapper> {
    private final Provider<FoodMapper> foodMapperProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesFoodEntryFromServerMapperFactory(ApplicationModule applicationModule, Provider<FoodMapper> provider) {
        this.module = applicationModule;
        this.foodMapperProvider = provider;
    }

    public FoodEntryFromServerMapper get() {
        return provideInstance(this.module, this.foodMapperProvider);
    }

    public static FoodEntryFromServerMapper provideInstance(ApplicationModule applicationModule, Provider<FoodMapper> provider) {
        return proxyProvidesFoodEntryFromServerMapper(applicationModule, (FoodMapper) provider.get());
    }

    public static ApplicationModule_ProvidesFoodEntryFromServerMapperFactory create(ApplicationModule applicationModule, Provider<FoodMapper> provider) {
        return new ApplicationModule_ProvidesFoodEntryFromServerMapperFactory(applicationModule, provider);
    }

    public static FoodEntryFromServerMapper proxyProvidesFoodEntryFromServerMapper(ApplicationModule applicationModule, FoodMapper foodMapper) {
        return (FoodEntryFromServerMapper) Preconditions.checkNotNull(applicationModule.providesFoodEntryFromServerMapper(foodMapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
