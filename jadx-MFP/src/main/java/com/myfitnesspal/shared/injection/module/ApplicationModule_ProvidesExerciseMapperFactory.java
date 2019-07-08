package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesExerciseMapperFactory implements Factory<ExerciseMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesExerciseMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public ExerciseMapper get() {
        return provideInstance(this.module);
    }

    public static ExerciseMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesExerciseMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesExerciseMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesExerciseMapperFactory(applicationModule);
    }

    public static ExerciseMapper proxyProvidesExerciseMapper(ApplicationModule applicationModule) {
        return (ExerciseMapper) Preconditions.checkNotNull(applicationModule.providesExerciseMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
