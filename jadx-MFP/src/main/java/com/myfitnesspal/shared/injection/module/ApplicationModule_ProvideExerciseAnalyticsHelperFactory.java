package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvideExerciseAnalyticsHelperFactory implements Factory<ExerciseAnalyticsHelper> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideExerciseAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        this.module = applicationModule;
        this.analyticsServiceProvider = provider;
    }

    public ExerciseAnalyticsHelper get() {
        return provideInstance(this.module, this.analyticsServiceProvider);
    }

    public static ExerciseAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return proxyProvideExerciseAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvideExerciseAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<AnalyticsService> provider) {
        return new ApplicationModule_ProvideExerciseAnalyticsHelperFactory(applicationModule, provider);
    }

    public static ExerciseAnalyticsHelper proxyProvideExerciseAnalyticsHelper(ApplicationModule applicationModule, Lazy<AnalyticsService> lazy) {
        return (ExerciseAnalyticsHelper) Preconditions.checkNotNull(applicationModule.provideExerciseAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
