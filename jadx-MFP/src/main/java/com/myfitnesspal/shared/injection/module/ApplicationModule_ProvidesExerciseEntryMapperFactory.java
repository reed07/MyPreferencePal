package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesExerciseEntryMapperFactory implements Factory<ExerciseEntryMapper> {
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesExerciseEntryMapperFactory(ApplicationModule applicationModule, Provider<ExerciseMapper> provider) {
        this.module = applicationModule;
        this.exerciseMapperProvider = provider;
    }

    public ExerciseEntryMapper get() {
        return provideInstance(this.module, this.exerciseMapperProvider);
    }

    public static ExerciseEntryMapper provideInstance(ApplicationModule applicationModule, Provider<ExerciseMapper> provider) {
        return proxyProvidesExerciseEntryMapper(applicationModule, (ExerciseMapper) provider.get());
    }

    public static ApplicationModule_ProvidesExerciseEntryMapperFactory create(ApplicationModule applicationModule, Provider<ExerciseMapper> provider) {
        return new ApplicationModule_ProvidesExerciseEntryMapperFactory(applicationModule, provider);
    }

    public static ExerciseEntryMapper proxyProvidesExerciseEntryMapper(ApplicationModule applicationModule, ExerciseMapper exerciseMapper) {
        return (ExerciseEntryMapper) Preconditions.checkNotNull(applicationModule.providesExerciseEntryMapper(exerciseMapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
