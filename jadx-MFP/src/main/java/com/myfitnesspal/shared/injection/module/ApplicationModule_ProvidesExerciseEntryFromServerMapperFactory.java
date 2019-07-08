package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseFromServerMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesExerciseEntryFromServerMapperFactory implements Factory<ExerciseEntryFromServerMapper> {
    private final Provider<ExerciseFromServerMapper> exerciseMapperProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesExerciseEntryFromServerMapperFactory(ApplicationModule applicationModule, Provider<ExerciseFromServerMapper> provider) {
        this.module = applicationModule;
        this.exerciseMapperProvider = provider;
    }

    public ExerciseEntryFromServerMapper get() {
        return provideInstance(this.module, this.exerciseMapperProvider);
    }

    public static ExerciseEntryFromServerMapper provideInstance(ApplicationModule applicationModule, Provider<ExerciseFromServerMapper> provider) {
        return proxyProvidesExerciseEntryFromServerMapper(applicationModule, (ExerciseFromServerMapper) provider.get());
    }

    public static ApplicationModule_ProvidesExerciseEntryFromServerMapperFactory create(ApplicationModule applicationModule, Provider<ExerciseFromServerMapper> provider) {
        return new ApplicationModule_ProvidesExerciseEntryFromServerMapperFactory(applicationModule, provider);
    }

    public static ExerciseEntryFromServerMapper proxyProvidesExerciseEntryFromServerMapper(ApplicationModule applicationModule, ExerciseFromServerMapper exerciseFromServerMapper) {
        return (ExerciseEntryFromServerMapper) Preconditions.checkNotNull(applicationModule.providesExerciseEntryFromServerMapper(exerciseFromServerMapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
