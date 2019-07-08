package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesMfpFoodMapperFactory implements Factory<MfpFoodMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesMfpFoodMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public MfpFoodMapper get() {
        return provideInstance(this.module);
    }

    public static MfpFoodMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesMfpFoodMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesMfpFoodMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesMfpFoodMapperFactory(applicationModule);
    }

    public static MfpFoodMapper proxyProvidesMfpFoodMapper(ApplicationModule applicationModule) {
        return (MfpFoodMapper) Preconditions.checkNotNull(applicationModule.providesMfpFoodMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
