package com.myfitnesspal.feature.nutrition.service;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NutritionDetailsDelegate_MembersInjector implements MembersInjector<NutritionDetailsDelegate> {
    private final Provider<Bus> messageBusProvider;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<NutrientGoalsUtil> nutritionalGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public NutritionDetailsDelegate_MembersInjector(Provider<NutrientGoalService> provider, Provider<NutrientGoalsUtil> provider2, Provider<UserEnergyService> provider3, Provider<Bus> provider4) {
        this.nutrientGoalServiceProvider = provider;
        this.nutritionalGoalsUtilProvider = provider2;
        this.userEnergyServiceProvider = provider3;
        this.messageBusProvider = provider4;
    }

    public static MembersInjector<NutritionDetailsDelegate> create(Provider<NutrientGoalService> provider, Provider<NutrientGoalsUtil> provider2, Provider<UserEnergyService> provider3, Provider<Bus> provider4) {
        return new NutritionDetailsDelegate_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(NutritionDetailsDelegate nutritionDetailsDelegate) {
        injectNutrientGoalService(nutritionDetailsDelegate, DoubleCheck.lazy(this.nutrientGoalServiceProvider));
        injectNutritionalGoalsUtil(nutritionDetailsDelegate, DoubleCheck.lazy(this.nutritionalGoalsUtilProvider));
        injectUserEnergyService(nutritionDetailsDelegate, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectMessageBus(nutritionDetailsDelegate, DoubleCheck.lazy(this.messageBusProvider));
    }

    public static void injectNutrientGoalService(NutritionDetailsDelegate nutritionDetailsDelegate, Lazy<NutrientGoalService> lazy) {
        nutritionDetailsDelegate.nutrientGoalService = lazy;
    }

    public static void injectNutritionalGoalsUtil(NutritionDetailsDelegate nutritionDetailsDelegate, Lazy<NutrientGoalsUtil> lazy) {
        nutritionDetailsDelegate.nutritionalGoalsUtil = lazy;
    }

    public static void injectUserEnergyService(NutritionDetailsDelegate nutritionDetailsDelegate, Lazy<UserEnergyService> lazy) {
        nutritionDetailsDelegate.userEnergyService = lazy;
    }

    public static void injectMessageBus(NutritionDetailsDelegate nutritionDetailsDelegate, Lazy<Bus> lazy) {
        nutritionDetailsDelegate.messageBus = lazy;
    }
}
