package com.myfitnesspal.feature.exercise.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyExercisesFragment_MembersInjector implements MembersInjector<MyExercisesFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<Glide> glideProvider;

    public MyExercisesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ExerciseService> provider3, Provider<ExerciseMapper> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.exerciseServiceProvider = provider3;
        this.exerciseMapperProvider = provider4;
    }

    public static MembersInjector<MyExercisesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<ExerciseService> provider3, Provider<ExerciseMapper> provider4) {
        return new MyExercisesFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(MyExercisesFragment myExercisesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myExercisesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myExercisesFragment, (Glide) this.glideProvider.get());
        injectExerciseService(myExercisesFragment, DoubleCheck.lazy(this.exerciseServiceProvider));
        injectExerciseMapper(myExercisesFragment, DoubleCheck.lazy(this.exerciseMapperProvider));
    }

    public static void injectExerciseService(MyExercisesFragment myExercisesFragment, Lazy<ExerciseService> lazy) {
        myExercisesFragment.exerciseService = lazy;
    }

    public static void injectExerciseMapper(MyExercisesFragment myExercisesFragment, Lazy<ExerciseMapper> lazy) {
        myExercisesFragment.exerciseMapper = lazy;
    }
}
