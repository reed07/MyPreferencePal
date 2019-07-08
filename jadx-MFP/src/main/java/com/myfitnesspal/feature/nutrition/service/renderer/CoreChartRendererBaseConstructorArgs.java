package com.myfitnesspal.feature.nutrition.service.renderer;

import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;

public class CoreChartRendererBaseConstructorArgs {
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<NutritionGraphService> nutritionGraphService;
    private final Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    private final Lazy<Session> session;
    private final Lazy<UserEnergyService> userEnergyService;

    public CoreChartRendererBaseConstructorArgs(Lazy<LocalSettingsService> lazy, Lazy<Session> lazy2, Lazy<UserEnergyService> lazy3, Lazy<NutrientGoalService> lazy4, Lazy<NutrientGoalsUtil> lazy5, Lazy<NutritionGraphService> lazy6) {
        this.localSettingsService = lazy;
        this.session = lazy2;
        this.userEnergyService = lazy3;
        this.nutrientGoalService = lazy4;
        this.nutritionalGoalsUtil = lazy5;
        this.nutritionGraphService = lazy6;
    }

    public Lazy<LocalSettingsService> getLocalSettingsService() {
        return this.localSettingsService;
    }

    public Lazy<Session> getSession() {
        return this.session;
    }

    public Lazy<UserEnergyService> getUserEnergyService() {
        return this.userEnergyService;
    }

    public Lazy<NutrientGoalService> getNutrientGoalService() {
        return this.nutrientGoalService;
    }

    public Lazy<NutrientGoalsUtil> getNutritionalGoalsUtil() {
        return this.nutritionalGoalsUtil;
    }

    public Lazy<NutritionGraphService> getNutritionGraphService() {
        return this.nutritionGraphService;
    }
}
