package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.ExerciseFromServerMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvidesExerciseFromServerMapperFactory implements Factory<ExerciseFromServerMapper> {
    private final ApplicationModule module;

    public ApplicationModule_ProvidesExerciseFromServerMapperFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public ExerciseFromServerMapper get() {
        return provideInstance(this.module);
    }

    public static ExerciseFromServerMapper provideInstance(ApplicationModule applicationModule) {
        return proxyProvidesExerciseFromServerMapper(applicationModule);
    }

    public static ApplicationModule_ProvidesExerciseFromServerMapperFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvidesExerciseFromServerMapperFactory(applicationModule);
    }

    public static ExerciseFromServerMapper proxyProvidesExerciseFromServerMapper(ApplicationModule applicationModule) {
        return (ExerciseFromServerMapper) Preconditions.checkNotNull(applicationModule.providesExerciseFromServerMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
