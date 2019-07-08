package com.myfitnesspal.feature.goals.model;

import android.support.annotation.StringRes;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MealGoalsFragmentViewModel extends BaseViewModel {
    private MfpDailyGoal dailyGoal;
    private int errorMessageId;
    private String goalValueOffBy = "0";
    private boolean isMealGoalsValid;
    private List<MealGoal> mealGoals;
    private MealNames mealNames;
    private Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private List<Double> percents;
    private Lazy<UserEnergyService> userEnergyService;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int DAILY_GOAL_DATA = ViewModelPropertyId.next();
        public static final int ERROR_MESSAGE_CHANGED = ViewModelPropertyId.next();
        public static final int MEAL_GOAL_FOCUS_CHANGED = ViewModelPropertyId.next();
    }

    public MealGoalsFragmentViewModel(MfpDailyGoal mfpDailyGoal, MealNames mealNames2, Lazy<UserEnergyService> lazy, Lazy<NutrientGoalsUtil> lazy2) {
        this.dailyGoal = mfpDailyGoal;
        this.mealNames = mealNames2;
        this.userEnergyService = lazy;
        this.nutrientGoalsUtil = lazy2;
        setMealGoals(mfpDailyGoal);
    }

    public void updateModel(MfpDailyGoal mfpDailyGoal, MealNames mealNames2) {
        this.dailyGoal = mfpDailyGoal;
        this.mealNames = mealNames2;
        setMealGoals(mfpDailyGoal);
    }

    public MfpDailyGoal getDailyGoal() {
        return this.dailyGoal;
    }

    public MealNames getMealNames() {
        return this.mealNames;
    }

    public List<MealGoal> getMealGoals() {
        return this.mealGoals;
    }

    public List<Double> getPercents() {
        return this.percents;
    }

    public MfpMeasuredValue getTotalMeasuredValue() {
        return ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDailyGoalMeasuredValue(this.dailyGoal);
    }

    public String getTotalDisplayString() {
        return LocalizedEnergy.getDisplayStringWithoutUnits(LocalizedEnergy.fromMeasuredValue(getTotalMeasuredValue()), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit());
    }

    public void setMealGoalsValid(boolean z) {
        this.isMealGoalsValid = z;
    }

    public boolean isMealGoalsValid() {
        return this.isMealGoalsValid;
    }

    public void setGoalValueOffBy(String str) {
        this.goalValueOffBy = str;
    }

    public String getGoalValueOffBy() {
        return this.goalValueOffBy;
    }

    @StringRes
    public int getErrorMessageId() {
        return this.errorMessageId;
    }

    public void mealGoalsChanged(double d, int i, MealGoalsInputMode mealGoalsInputMode) {
        validateMealGoals(d, i, mealGoalsInputMode);
        this.dailyGoal.setMealGoals(this.mealGoals);
        notifyPropertyChanged(Property.DAILY_GOAL_DATA);
        validateUpdateMealGoalValidFlag(mealGoalsInputMode);
    }

    public LocalizedEnergy getEnergyStringForIndex(int i) {
        MfpMeasuredValue energy = ((MealGoal) getMealGoals().get(i)).getEnergy();
        return energy != null ? LocalizedEnergy.fromMeasuredValue(energy) : LocalizedEnergy.fromCalories(0.0d);
    }

    public String getPercentStringForIndex(int i) {
        return NumberUtils.localeStringFromDouble(((Double) getPercents().get(i)).doubleValue(), 0);
    }

    public void mealGoalRequestedFocus() {
        notifyPropertyChanged(Property.MEAL_GOAL_FOCUS_CHANGED);
    }

    public boolean isUserInCaloriesUnit() {
        return ((UserEnergyService) this.userEnergyService.get()).isCalories();
    }

    private void setMealGoals(MfpDailyGoal mfpDailyGoal) {
        this.mealGoals = mfpDailyGoal != null ? new LinkedList(mfpDailyGoal.getMealGoals()) : new LinkedList();
        Collections.sort(this.mealGoals);
        setPercents();
    }

    private void setPercents() {
        this.percents = new LinkedList();
        double localizedTotalEnergy = getLocalizedTotalEnergy();
        for (MealGoal energy : this.mealGoals) {
            MfpMeasuredValue energy2 = energy.getEnergy();
            double d = 0.0d;
            double value = (energy2 != null ? LocalizedEnergy.fromMeasuredValue(energy2) : LocalizedEnergy.fromCalories(0.0d)).getValue(Energy.CALORIES);
            List<Double> list = this.percents;
            if (localizedTotalEnergy > 0.0d) {
                d = 100.0d * (value / localizedTotalEnergy);
            }
            list.add(Double.valueOf(d));
        }
    }

    private void validateMealGoals(double d, int i, MealGoalsInputMode mealGoalsInputMode) {
        double localizedTotalEnergy = getLocalizedTotalEnergy();
        MfpMeasuredValue energy = ((MealGoal) this.mealGoals.get(i)).getEnergy();
        if (energy == null) {
            energy = getTotalMeasuredValue();
        }
        String unit = energy.getUnit();
        if (mealGoalsInputMode == MealGoalsInputMode.Energy) {
            LocalizedEnergy fromCalories = ((UserEnergyService) this.userEnergyService.get()).isCalories() ? LocalizedEnergy.fromCalories(d) : LocalizedEnergy.fromKilojoules(d);
            double value = fromCalories.getValue(Energy.CALORIES);
            ((MealGoal) this.mealGoals.get(i)).setEnergy(new MfpMeasuredValue(unit, (float) fromCalories.getValue("calories".equals(unit) ? Energy.CALORIES : Energy.KILOJOULES)));
            List<Double> list = this.percents;
            double d2 = 0.0d;
            if (localizedTotalEnergy > 0.0d) {
                d2 = (value / localizedTotalEnergy) * 100.0d;
            }
            list.set(i, Double.valueOf(d2));
            return;
        }
        this.percents.set(i, Double.valueOf(d));
        ((MealGoal) this.mealGoals.get(i)).setEnergy(new MfpMeasuredValue(unit, (float) (localizedTotalEnergy * (d / 100.0d))));
    }

    private double getPercentSum() {
        double d = 0.0d;
        for (Double doubleValue : this.percents) {
            d += doubleValue.doubleValue();
        }
        return d;
    }

    public void validateUpdateMealGoalValidFlag(MealGoalsInputMode mealGoalsInputMode) {
        double d;
        double d2;
        boolean z = mealGoalsInputMode == MealGoalsInputMode.Energy;
        if (z) {
            d2 = (double) ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).mealGoalsEnergySumInCurrentUnits(this.mealGoals);
            d = (double) ((float) LocalizedEnergy.getValueInUserCurrentUnitsFromMeasuredValue(getTotalMeasuredValue(), isUserInCaloriesUnit()));
        } else {
            d2 = getPercentSum();
            d = 100.0d;
        }
        double d3 = d - d2;
        boolean isUserInCaloriesUnit = isUserInCaloriesUnit();
        String localeStringFromFloatWithExactFractionDigits = NumberUtils.localeStringFromFloatWithExactFractionDigits((float) Math.abs(d3), 0);
        setMealGoalsValid(Strings.equals("0", localeStringFromFloatWithExactFractionDigits));
        setGoalValueOffBy(localeStringFromFloatWithExactFractionDigits);
        if (d3 > 0.0d) {
            if (z) {
                this.errorMessageId = isUserInCaloriesUnit ? R.string.meal_goals_under_daily_calorie_goal : R.string.meal_goals_under_daily_kj_goal;
            } else {
                this.errorMessageId = isUserInCaloriesUnit ? R.string.meal_goals_percent_under_daily_calorie_goal : R.string.meal_goals_percent_under_daily_kilojoule_goal;
            }
        } else if (z) {
            this.errorMessageId = isUserInCaloriesUnit ? R.string.meal_goals_over_daily_calorie_goal : R.string.meal_goals_over_daily_kj_goal;
        } else {
            this.errorMessageId = isUserInCaloriesUnit ? R.string.meal_goals_percent_over_daily_calorie_goal : R.string.meal_goals_percent_over_daily_kilojoule_goal;
        }
        notifyPropertyChanged(Property.ERROR_MESSAGE_CHANGED);
    }

    private double getLocalizedTotalEnergy() {
        return (getTotalMeasuredValue() != null ? LocalizedEnergy.fromMeasuredValue(getTotalMeasuredValue()) : LocalizedEnergy.fromCalories(0.0d)).getValue(Energy.CALORIES);
    }
}
