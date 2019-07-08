package com.myfitnesspal.feature.goals.service;

import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.ReturningFunction2;
import java.util.Date;
import java.util.List;

public interface NutrientGoalsUtil {
    int dayOfWeekNameToIndex(String str);

    String formatDailyValuePercent(float f);

    String formatGrams(float f);

    String formatMilligrams(float f);

    float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i);

    float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i, boolean z);

    float getBase5Carbohydrates();

    float getBase5Carbohydrates(float f, float f2, float f3);

    String getBase5CarbohydratesForDisplay();

    String getBase5CarbohydratesForDisplay(float f, float f2, float f3);

    float getBase5Fat();

    float getBase5Fat(float f, float f2, float f3);

    String getBase5FatForDisplay();

    String getBase5FatForDisplay(float f, float f2, float f3);

    int getBase5MacroCarbohydratesPercentage();

    int getBase5MacroCarbohydratesPercentage(float f, float f2, float f3);

    int getBase5MacroFatPercentage();

    int getBase5MacroFatPercentage(float f, float f2, float f3);

    int getBase5MacroProteinPercentage();

    int getBase5MacroProteinPercentage(float f, float f2, float f3);

    float getBase5Protein();

    float getBase5Protein(float f, float f2, float f3);

    String getBase5ProteinForDisplay();

    String getBase5ProteinForDisplay(float f, float f2, float f3);

    float getCaloriesBurnedPerWeek();

    float getCarbohydrates();

    String getCarbohydratesForDisplay(float f);

    MfpDailyGoal getDailyGoalForDayOfWeek(MfpNutrientGoal mfpNutrientGoal, String str);

    MfpMeasuredValue getDailyGoalMeasuredValue(MfpDailyGoal mfpDailyGoal);

    MfpDailyGoal getDailyGoalWithNewEnergyValue(MfpDailyGoal mfpDailyGoal, float f);

    MfpDailyGoal getDailyGoalWithNewEnergyValue(MfpDailyGoal mfpDailyGoal, MfpMeasuredValue mfpMeasuredValue);

    MfpDailyGoal getDefaultDailyGoal(MfpNutrientGoal mfpNutrientGoal);

    float getDefaultEnergyGoal();

    float getDefaultEnergyGoal(Energy energy);

    String getDefaultEnergyGoalForDisplay();

    List<MealGoal> getDefaultMealGoals(MfpDailyGoal mfpDailyGoal, MealNames mealNames);

    String getEnergyBurnedPerWeekForDisplay();

    float getFat();

    String getFatForDisplay(float f);

    MfpGoalPreferences getGoalPreferences();

    float getMacroCarbohydratesPercentage();

    float getMacroCarbohydratesPercentage(float f, float f2, float f3);

    float getMacroFatPercentage();

    float getMacroFatPercentage(float f, float f2, float f3);

    float getMacroProteinPercentage();

    float getMacroProteinPercentage(float f, float f2, float f3);

    int getMacroProteinPercentage(int i, int i2);

    MealGoal getMealGoalForMeal(MfpDailyGoal mfpDailyGoal, int i);

    MealGoal getMealGoalForMeal(MfpNutrientGoal mfpNutrientGoal, Date date, int i);

    float getMinutesPerWorkout();

    String getMinutesPerWorkoutForDisplay();

    float getProtein();

    String getProteinForDisplay(float f);

    float getTotalMacronutrientIntake();

    float getTotalMacronutrientIntake(float f, float f2, float f3);

    boolean isCustomGoalsPresent(MfpNutrientGoal mfpNutrientGoal);

    float mealGoalsEnergySum(List<MealGoal> list);

    float mealGoalsEnergySumInCurrentUnits(List<MealGoal> list);

    void normalizeMealGoalsForTotal(MfpDailyGoal mfpDailyGoal, MfpMeasuredValue mfpMeasuredValue, MfpMeasuredValue mfpMeasuredValue2);

    MfpNutrientGoal recalculateGoals(float f, MealNames mealNames);

    MfpNutrientGoal saveMealGoalsForDailyGoal(MfpNutrientGoal mfpNutrientGoal, MfpDailyGoal mfpDailyGoal);

    MfpNutrientGoal setWeekDailyGoalsToDefaultDailyGoalIfNeeded(MfpNutrientGoal mfpNutrientGoal);

    MfpNutrientGoal transformDailyGoals(MfpNutrientGoal mfpNutrientGoal, ReturningFunction2<MfpDailyGoal, MfpDailyGoal, MfpDailyGoal> returningFunction2);

    MfpNutrientGoal updateAllDailyGoalsWithNewNonMacroValues(MfpNutrientGoal mfpNutrientGoal);

    MfpDailyGoal updateDailyGoal(MfpDailyGoal mfpDailyGoal, String str, float f);
}
