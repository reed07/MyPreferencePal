package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutritionalGoalsUtilServiceFactory implements Factory<NutrientGoalsUtil> {
    private final ApplicationModule module;
    private final Provider<NutrientGoalService> nutrientGoalsServiceProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public ApplicationModule_ProvidesNutritionalGoalsUtilServiceFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.userEnergyServiceProvider = provider2;
        this.nutrientGoalsServiceProvider = provider3;
    }

    public NutrientGoalsUtil get() {
        return provideInstance(this.module, this.sessionProvider, this.userEnergyServiceProvider, this.nutrientGoalsServiceProvider);
    }

    public static NutrientGoalsUtil provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        return proxyProvidesNutritionalGoalsUtilService(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ApplicationModule_ProvidesNutritionalGoalsUtilServiceFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<UserEnergyService> provider2, Provider<NutrientGoalService> provider3) {
        return new ApplicationModule_ProvidesNutritionalGoalsUtilServiceFactory(applicationModule, provider, provider2, provider3);
    }

    public static NutrientGoalsUtil proxyProvidesNutritionalGoalsUtilService(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<UserEnergyService> lazy2, Lazy<NutrientGoalService> lazy3) {
        return (NutrientGoalsUtil) Preconditions.checkNotNull(applicationModule.providesNutritionalGoalsUtilService(lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
