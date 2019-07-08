package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutritionGraphServiceFactory implements Factory<NutritionGraphService> {
    private final ApplicationModule module;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidesNutritionGraphServiceFactory(ApplicationModule applicationModule, Provider<NutrientGoalService> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<NutrientGoalsUtil> provider4) {
        this.module = applicationModule;
        this.nutrientGoalServiceProvider = provider;
        this.userEnergyServiceProvider = provider2;
        this.sessionProvider = provider3;
        this.nutrientGoalsUtilProvider = provider4;
    }

    public NutritionGraphService get() {
        return provideInstance(this.module, this.nutrientGoalServiceProvider, this.userEnergyServiceProvider, this.sessionProvider, this.nutrientGoalsUtilProvider);
    }

    public static NutritionGraphService provideInstance(ApplicationModule applicationModule, Provider<NutrientGoalService> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<NutrientGoalsUtil> provider4) {
        return proxyProvidesNutritionGraphService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesNutritionGraphServiceFactory create(ApplicationModule applicationModule, Provider<NutrientGoalService> provider, Provider<UserEnergyService> provider2, Provider<Session> provider3, Provider<NutrientGoalsUtil> provider4) {
        ApplicationModule_ProvidesNutritionGraphServiceFactory applicationModule_ProvidesNutritionGraphServiceFactory = new ApplicationModule_ProvidesNutritionGraphServiceFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesNutritionGraphServiceFactory;
    }

    public static NutritionGraphService proxyProvidesNutritionGraphService(ApplicationModule applicationModule, Lazy<NutrientGoalService> lazy, Lazy<UserEnergyService> lazy2, Lazy<Session> lazy3, Lazy<NutrientGoalsUtil> lazy4) {
        return (NutritionGraphService) Preconditions.checkNotNull(applicationModule.providesNutritionGraphService(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
