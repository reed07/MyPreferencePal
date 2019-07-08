package com.myfitnesspal.shared.service;

import android.content.Context;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExerciseStringService_Factory implements Factory<ExerciseStringService> {
    private final Provider<Context> contextProvider;
    private final Provider<ExerciseEntryMapper> exerciseEntryMapperProvider;
    private final Provider<ExerciseMapper> exerciseMapperProvider;
    private final Provider<UserDistanceService> userDistanceServiceProvider;
    private final Provider<UserWeightService> userWeightServiceProvider;

    public ExerciseStringService_Factory(Provider<Context> provider, Provider<UserDistanceService> provider2, Provider<UserWeightService> provider3, Provider<ExerciseMapper> provider4, Provider<ExerciseEntryMapper> provider5) {
        this.contextProvider = provider;
        this.userDistanceServiceProvider = provider2;
        this.userWeightServiceProvider = provider3;
        this.exerciseMapperProvider = provider4;
        this.exerciseEntryMapperProvider = provider5;
    }

    public ExerciseStringService get() {
        return provideInstance(this.contextProvider, this.userDistanceServiceProvider, this.userWeightServiceProvider, this.exerciseMapperProvider, this.exerciseEntryMapperProvider);
    }

    public static ExerciseStringService provideInstance(Provider<Context> provider, Provider<UserDistanceService> provider2, Provider<UserWeightService> provider3, Provider<ExerciseMapper> provider4, Provider<ExerciseEntryMapper> provider5) {
        ExerciseStringService exerciseStringService = new ExerciseStringService((Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
        return exerciseStringService;
    }

    public static ExerciseStringService_Factory create(Provider<Context> provider, Provider<UserDistanceService> provider2, Provider<UserWeightService> provider3, Provider<ExerciseMapper> provider4, Provider<ExerciseEntryMapper> provider5) {
        ExerciseStringService_Factory exerciseStringService_Factory = new ExerciseStringService_Factory(provider, provider2, provider3, provider4, provider5);
        return exerciseStringService_Factory;
    }

    public static ExerciseStringService newExerciseStringService(Context context, Lazy<UserDistanceService> lazy, Lazy<UserWeightService> lazy2, Lazy<ExerciseMapper> lazy3, Lazy<ExerciseEntryMapper> lazy4) {
        ExerciseStringService exerciseStringService = new ExerciseStringService(context, lazy, lazy2, lazy3, lazy4);
        return exerciseStringService;
    }
}
