package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.WaterEntryMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesWaterEntryMapperFactory implements Factory<WaterEntryMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesWaterEntryMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public WaterEntryMapper get() {
        return provideInstance(this.module);
    }

    public static WaterEntryMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesWaterEntryMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesWaterEntryMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesWaterEntryMapperFactory(applicationModule);
    }

    public static WaterEntryMapper proxyProvidesWaterEntryMapper(ApplicationModule applicationModule) {
        return (WaterEntryMapper) Preconditions.checkNotNull(applicationModule.providesWaterEntryMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
