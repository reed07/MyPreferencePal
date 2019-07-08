package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphService;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory implements Factory<CoreChartRendererBaseConstructorArgs> {
    private final Provider<LocalSettingsService> localSettingsServiceLazyProvider;
    private final ApplicationModule module;
    private final Provider<NutrientGoalService> nutrientGoalServiceLazyProvider;
    private final Provider<NutritionGraphService> nutritionGraphServiceLazyProvider;
    private final Provider<NutrientGoalsUtil> nutritionalGoalsUtilLazyProvider;
    private final Provider<Session> sessionLazyProvider;
    private final Provider<UserEnergyService> userEnergyServiceLazyProvider;

    public ApplicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<Session> provider2, Provider<UserEnergyService> provider3, Provider<NutrientGoalService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<NutritionGraphService> provider6) {
        this.module = applicationModule;
        this.localSettingsServiceLazyProvider = provider;
        this.sessionLazyProvider = provider2;
        this.userEnergyServiceLazyProvider = provider3;
        this.nutrientGoalServiceLazyProvider = provider4;
        this.nutritionalGoalsUtilLazyProvider = provider5;
        this.nutritionGraphServiceLazyProvider = provider6;
    }

    public CoreChartRendererBaseConstructorArgs get() {
        return provideInstance(this.module, this.localSettingsServiceLazyProvider, this.sessionLazyProvider, this.userEnergyServiceLazyProvider, this.nutrientGoalServiceLazyProvider, this.nutritionalGoalsUtilLazyProvider, this.nutritionGraphServiceLazyProvider);
    }

    public static CoreChartRendererBaseConstructorArgs provideInstance(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<Session> provider2, Provider<UserEnergyService> provider3, Provider<NutrientGoalService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<NutritionGraphService> provider6) {
        return proxyProvidesCoreChartRendererBaseConstructorArgs(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ApplicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory create(ApplicationModule applicationModule, Provider<LocalSettingsService> provider, Provider<Session> provider2, Provider<UserEnergyService> provider3, Provider<NutrientGoalService> provider4, Provider<NutrientGoalsUtil> provider5, Provider<NutritionGraphService> provider6) {
        ApplicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory applicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory = new ApplicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory(applicationModule, provider, provider2, provider3, provider4, provider5, provider6);
        return applicationModule_ProvidesCoreChartRendererBaseConstructorArgsFactory;
    }

    public static CoreChartRendererBaseConstructorArgs proxyProvidesCoreChartRendererBaseConstructorArgs(ApplicationModule applicationModule, Lazy<LocalSettingsService> lazy, Lazy<Session> lazy2, Lazy<UserEnergyService> lazy3, Lazy<NutrientGoalService> lazy4, Lazy<NutrientGoalsUtil> lazy5, Lazy<NutritionGraphService> lazy6) {
        return (CoreChartRendererBaseConstructorArgs) Preconditions.checkNotNull(applicationModule.providesCoreChartRendererBaseConstructorArgs(lazy, lazy2, lazy3, lazy4, lazy5, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }
}
