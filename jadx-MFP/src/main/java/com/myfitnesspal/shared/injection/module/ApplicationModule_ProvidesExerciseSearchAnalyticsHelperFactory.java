package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesExerciseSearchAnalyticsHelperFactory implements Factory<ExerciseSearchAnalyticsHelper> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesExerciseSearchAnalyticsHelperFactory(ApplicationModule applicationModule, Provider<ActionTrackingService> provider) {
        this.module = applicationModule;
        this.actionTrackingServiceProvider = provider;
    }

    public ExerciseSearchAnalyticsHelper get() {
        return provideInstance(this.module, this.actionTrackingServiceProvider);
    }

    public static ExerciseSearchAnalyticsHelper provideInstance(ApplicationModule applicationModule, Provider<ActionTrackingService> provider) {
        return proxyProvidesExerciseSearchAnalyticsHelper(applicationModule, DoubleCheck.lazy(provider));
    }

    public static ApplicationModule_ProvidesExerciseSearchAnalyticsHelperFactory create(ApplicationModule applicationModule, Provider<ActionTrackingService> provider) {
        return new ApplicationModule_ProvidesExerciseSearchAnalyticsHelperFactory(applicationModule, provider);
    }

    public static ExerciseSearchAnalyticsHelper proxyProvidesExerciseSearchAnalyticsHelper(ApplicationModule applicationModule, Lazy<ActionTrackingService> lazy) {
        return (ExerciseSearchAnalyticsHelper) Preconditions.checkNotNull(applicationModule.providesExerciseSearchAnalyticsHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }
}
