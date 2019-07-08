package com.myfitnesspal.feature.goals.service;

import android.os.Parcelable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.constants.Constants.Goals.Nutrient;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction2;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NutrientGoalsUtilImpl implements NutrientGoalsUtil {
    private static final float CHOLESTEROL_MG_PER_DAY = 300.0f;
    private static final int DAYS_PER_WEEK = 7;
    private static final int FIBER_PER_DAY_FEMALE_50_OR_UNDER = 25;
    private static final int FIBER_PER_DAY_FEMALE_OLDER_THAN_50 = 21;
    private static final int FIBER_PER_DAY_MALE_50_OR_UNDER = 38;
    private static final int FIBER_PER_DAY_MALE_OLDER_THAN_50 = 30;
    private static final float FRACTION_CARBS = 0.5f;
    private static final float FRACTION_FAT = 0.3f;
    private static final float FRACTION_PROTEIN = 0.2f;
    private static final float FRACTION_SATURATED_FAT = 0.1f;
    private static final float FRACTION_SUGAR = 0.15f;
    private static final float MONOSAT_GRAMS_PER_DAY = 0.0f;
    private static final int PERCENT_CALCIUM = 100;
    private static final int PERCENT_IRON = 100;
    private static final int PERCENT_VITAMIN_A = 100;
    private static final int PERCENT_VITAMIN_C = 100;
    private static final float POLYSAT_GRAMS_PER_DAY = 0.0f;
    private static final float POTASSIUM_MG_PER_DAY = 3500.0f;
    private static final float SODIUM_MG_PER_DAY = 2300.0f;
    private static final float THIRTY_PERCENT = 0.3f;
    private static final float TRANS_FAT_GRAMS_PER_DAY = 0.0f;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<Session> session;
    private final Lazy<UserEnergyService> userEnergyService;

    public String getCarbohydratesId() {
        return Goals.GOAL_CARBS_PER_DAY;
    }

    public String getFatId() {
        return Goals.GOAL_FAT_PER_DAY;
    }

    public int getMacroProteinPercentage(int i, int i2) {
        return 100 - (i + i2);
    }

    public String getProteinId() {
        return Goals.GOAL_PROTEIN_PER_DAY;
    }

    public NutrientGoalsUtilImpl(Lazy<Session> lazy, Lazy<UserEnergyService> lazy2, Lazy<NutrientGoalService> lazy3) {
        this.session = lazy;
        this.userEnergyService = lazy2;
        this.nutrientGoalService = lazy3;
    }

    public float getTotalMacronutrientIntake(float f, float f2, float f3) {
        return NutritionUtils.gramsProteinToCalories(f2) + NutritionUtils.gramsCarbsToCalories(f) + NutritionUtils.gramsFatToCalories(f3);
    }

    public float getTotalMacronutrientIntake() {
        return NutritionUtils.gramsProteinToCalories(getProtein()) + NutritionUtils.gramsCarbsToCalories(getCarbohydrates()) + NutritionUtils.gramsFatToCalories(getFat());
    }

    public String getCarbohydratesForDisplay(float f) {
        return formatGrams(f);
    }

    public float getBase5Carbohydrates(float f, float f2, float f3) {
        return NutritionUtils.caloriesToGramsCarbs((((float) getBase5MacroCarbohydratesPercentage(f, f2, f3)) / 100.0f) * getTotalMacronutrientIntake(f, f2, f3));
    }

    public float getBase5Carbohydrates() {
        return NutritionUtils.caloriesToGramsCarbs((((float) getBase5MacroCarbohydratesPercentage()) / 100.0f) * getTotalMacronutrientIntake());
    }

    public String getBase5CarbohydratesForDisplay(float f, float f2, float f3) {
        return formatGrams(getBase5Carbohydrates(f, f2, f3));
    }

    public String getBase5CarbohydratesForDisplay() {
        return formatGrams(getBase5Carbohydrates());
    }

    public float getMacroCarbohydratesPercentage(float f, float f2, float f3) {
        return getMacroPercentage(f, f2, f3, NutritionUtils.gramsCarbsToCalories(f));
    }

    public float getMacroCarbohydratesPercentage() {
        return (NutritionUtils.gramsCarbsToCalories(getCarbohydrates()) / getTotalMacronutrientIntake()) * 100.0f;
    }

    public int getBase5MacroCarbohydratesPercentage(float f, float f2, float f3) {
        return NumberUtils.getBase5Value(getMacroCarbohydratesPercentage(f, f2, f3));
    }

    public int getBase5MacroCarbohydratesPercentage() {
        return NumberUtils.getBase5Value(getMacroCarbohydratesPercentage());
    }

    public String getProteinForDisplay(float f) {
        return formatGrams(f);
    }

    public float getBase5Protein(float f, float f2, float f3) {
        return NutritionUtils.caloriesToGramsProtein((((float) getBase5MacroProteinPercentage(f, f2, f3)) / 100.0f) * getTotalMacronutrientIntake(f, f2, f3));
    }

    public float getBase5Protein() {
        return NutritionUtils.caloriesToGramsProtein((((float) getBase5MacroProteinPercentage()) / 100.0f) * getTotalMacronutrientIntake());
    }

    public String getBase5ProteinForDisplay(float f, float f2, float f3) {
        return formatGrams(getBase5Protein(f, f2, f3));
    }

    public String getBase5ProteinForDisplay() {
        return formatGrams(getBase5Protein());
    }

    public float getMacroProteinPercentage(float f, float f2, float f3) {
        return getMacroPercentage(f, f2, f3, NutritionUtils.gramsProteinToCalories(f2));
    }

    private float getMacroPercentage(float f, float f2, float f3, float f4) {
        float totalMacronutrientIntake = getTotalMacronutrientIntake(f, f2, f3);
        return NumberUtils.isEffectivelyZero((double) totalMacronutrientIntake) ? BitmapDescriptorFactory.HUE_RED : 100.0f * (f4 / totalMacronutrientIntake);
    }

    public float getMacroProteinPercentage() {
        return (NutritionUtils.gramsProteinToCalories(getProtein()) / getTotalMacronutrientIntake()) * 100.0f;
    }

    public int getBase5MacroProteinPercentage(float f, float f2, float f3) {
        return NumberUtils.getBase5Value(getMacroProteinPercentage(f, f2, f3));
    }

    public int getBase5MacroProteinPercentage() {
        return NumberUtils.getBase5Value(getMacroProteinPercentage());
    }

    public String getFatForDisplay(float f) {
        return formatGrams(f);
    }

    public float getBase5Fat(float f, float f2, float f3) {
        return NutritionUtils.caloriesToGramsFat((((float) getBase5MacroFatPercentage(f, f2, f3)) / 100.0f) * getTotalMacronutrientIntake(f, f2, f3));
    }

    public float getBase5Fat() {
        return NutritionUtils.caloriesToGramsFat((((float) getBase5MacroFatPercentage()) / 100.0f) * getTotalMacronutrientIntake());
    }

    public String getBase5FatForDisplay(float f, float f2, float f3) {
        return formatGrams(getBase5Fat(f, f2, f3));
    }

    public String getBase5FatForDisplay() {
        return formatGrams(getBase5Fat());
    }

    public float getMacroFatPercentage(float f, float f2, float f3) {
        return getMacroPercentage(f, f2, f3, NutritionUtils.gramsFatToCalories(f3));
    }

    public float getMacroFatPercentage() {
        return (NutritionUtils.gramsFatToCalories(getFat()) / getTotalMacronutrientIntake()) * 100.0f;
    }

    public int getBase5MacroFatPercentage(float f, float f2, float f3) {
        return NumberUtils.getBase5Value(getMacroFatPercentage(f, f2, f3));
    }

    public int getBase5MacroFatPercentage() {
        return NumberUtils.getBase5Value(getMacroFatPercentage());
    }

    public String getMinutesPerWorkoutForDisplay() {
        return formatString(getMinutesPerWorkout());
    }

    public float getMinutesPerWorkout() {
        return (float) ((Session) this.session.get()).getUser().getUserV1GoalPreferences().getMinutesPerWorkout();
    }

    public float getCaloriesBurnedPerWeek() {
        return (float) ((Session) this.session.get()).getUser().getUserV1GoalPreferences().getWorkoutsPerWeek();
    }

    public String getEnergyBurnedPerWeekForDisplay() {
        return formatString(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) getCaloriesBurnedPerWeek()));
    }

    public float getDefaultEnergyGoal() {
        return MfpDailyGoal.getLocalizedEnergy(((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal(), (UserEnergyService) this.userEnergyService.get());
    }

    public float getDefaultEnergyGoal(Energy energy) {
        return ((UserEnergyService) this.userEnergyService.get()).getEnergy(energy, ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal().getEnergy());
    }

    public String getDefaultEnergyGoalForDisplay() {
        return NumberUtils.localeStringFromDoubleNoDecimal((double) Math.abs(getDefaultEnergyGoal()));
    }

    private String formatString(float f) {
        return convertFloatToDecimalString(f);
    }

    private String convertFloatToDecimalString(float f) {
        return NumberUtils.localeStringFromFloat((float) Math.round(f), true);
    }

    public String formatDailyValuePercent(float f) {
        return String.format("%s%% DV", new Object[]{convertFloatToDecimalString(f)});
    }

    public String formatMilligrams(float f) {
        return String.format("%smg", new Object[]{convertFloatToDecimalString(f)});
    }

    public String formatGrams(float f) {
        return String.format("%sg", new Object[]{convertFloatToDecimalString(f)});
    }

    public MfpGoalPreferences getGoalPreferences() {
        return ((Session) this.session.get()).getUser().getGoalPreferences();
    }

    public MfpNutrientGoal recalculateGoals(float f, MealNames mealNames) {
        float caloriesToGramsCarbs = NutritionUtils.caloriesToGramsCarbs(f);
        float caloriesToGramsProtein = NutritionUtils.caloriesToGramsProtein(f);
        float caloriesToGramsFat = NutritionUtils.caloriesToGramsFat(f);
        MfpDailyGoal cachedDefaultGoal = ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal();
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) cachedDefaultGoal.getMealGoals());
        String assignExerciseEnergy = cachedDefaultGoal != null ? cachedDefaultGoal.getAssignExerciseEnergy() : "nutrient_goal";
        MfpDailyGoal mfpDailyGoal = new MfpDailyGoal();
        mfpDailyGoal.setEnergy(new MfpMeasuredValue("calories", f));
        mfpDailyGoal.setCarbohydrates((float) Math.round(0.5f * caloriesToGramsCarbs));
        mfpDailyGoal.setProtein((float) Math.round(caloriesToGramsProtein * FRACTION_PROTEIN));
        mfpDailyGoal.setFat((float) Math.round(0.3f * caloriesToGramsFat));
        mfpDailyGoal.setSaturatedFat((float) Math.round(caloriesToGramsFat * 0.1f));
        mfpDailyGoal.setCholesterol(300.0f);
        mfpDailyGoal.setSodium(SODIUM_MG_PER_DAY);
        mfpDailyGoal.setPotassium(POTASSIUM_MG_PER_DAY);
        mfpDailyGoal.setFiber(calculateFiberForUser((Session) this.session.get()));
        mfpDailyGoal.setTransFat(BitmapDescriptorFactory.HUE_RED);
        mfpDailyGoal.setSugar((float) Math.round(caloriesToGramsCarbs * FRACTION_SUGAR));
        mfpDailyGoal.setVitaminA(100);
        mfpDailyGoal.setVitaminC(100);
        mfpDailyGoal.setIron(100);
        mfpDailyGoal.setCalcium(100);
        mfpDailyGoal.setPolyunsaturatedFat(BitmapDescriptorFactory.HUE_RED);
        mfpDailyGoal.setMonounsaturatedFat(BitmapDescriptorFactory.HUE_RED);
        mfpDailyGoal.setAssignExerciseEnergy(assignExerciseEnergy);
        mfpDailyGoal.setExerciseCarbohydratesPercentage(cachedDefaultGoal.getExerciseCarbohydratesPercentage());
        mfpDailyGoal.setExerciseProteinPercentage(cachedDefaultGoal.getExerciseProteinPercentage());
        mfpDailyGoal.setExerciseFatPercentage(cachedDefaultGoal.getExerciseFatPercentage());
        mfpDailyGoal.setMealGoals(notEmpty ? getDefaultMealGoals(mfpDailyGoal, mealNames) : null);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 7) {
            MfpDailyGoal mfpDailyGoal2 = (MfpDailyGoal) ParcelableUtil.clone(mfpDailyGoal, MfpDailyGoal.CREATOR);
            i++;
            mfpDailyGoal2.setDayOfWeek(DateTimeUtils.getDayString(Integer.valueOf(i)));
            arrayList.add(mfpDailyGoal2);
        }
        MfpNutrientGoal mfpNutrientGoal = new MfpNutrientGoal();
        mfpNutrientGoal.setUserId(((Session) this.session.get()).getUser().getUserId());
        mfpNutrientGoal.setDailyGoals(arrayList);
        mfpNutrientGoal.setDefaultGoal(mfpDailyGoal);
        return mfpNutrientGoal;
    }

    private static float calculateFiberForUser(Session session2) {
        UserV1 userV1 = session2.getUser().getUserV1();
        boolean z = DateTimeUtils.getAgeInYears(userV1.getProfile().getDateOfBirth()) > 50;
        int i = userV1.getProfile().isFemale().booleanValue() ? z ? 21 : 25 : z ? 30 : 38;
        return (float) i;
    }

    /* access modifiers changed from: protected */
    public float getGoalValue(String str) {
        return ((Session) this.session.get()).getUser().getUserV1NutrientGoals().get(str);
    }

    public float getProtein() {
        return getGoalValue(getProteinId());
    }

    public float getFat() {
        return getGoalValue(getFatId());
    }

    public float getCarbohydrates() {
        return getGoalValue(getCarbohydratesId());
    }

    public MfpDailyGoal getDailyGoalWithNewEnergyValue(MfpDailyGoal mfpDailyGoal, MfpMeasuredValue mfpMeasuredValue) {
        float carbohydrates = mfpDailyGoal.getCarbohydrates();
        float protein = mfpDailyGoal.getProtein();
        float fat = mfpDailyGoal.getFat();
        float macroCarbohydratesPercentage = getMacroCarbohydratesPercentage(carbohydrates, protein, fat);
        float macroProteinPercentage = getMacroProteinPercentage(carbohydrates, protein, fat);
        float macroFatPercentage = getMacroFatPercentage(carbohydrates, protein, fat);
        float energy = ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, mfpMeasuredValue);
        float valueFromPercentage = NumberUtils.getValueFromPercentage(macroCarbohydratesPercentage, energy) / 4.0f;
        float valueFromPercentage2 = NumberUtils.getValueFromPercentage(macroProteinPercentage, energy) / 4.0f;
        float valueFromPercentage3 = NumberUtils.getValueFromPercentage(macroFatPercentage, energy) / 9.0f;
        MfpDailyGoal mfpDailyGoal2 = (MfpDailyGoal) ParcelableUtil.clone(mfpDailyGoal, MfpDailyGoal.CREATOR);
        MfpMeasuredValue mfpMeasuredValue2 = new MfpMeasuredValue("calories", energy);
        normalizeMealGoalsForTotal(mfpDailyGoal2, mfpDailyGoal2.getEnergy(), mfpMeasuredValue2);
        mfpDailyGoal2.setEnergy(mfpMeasuredValue2);
        mfpDailyGoal2.setCarbohydrates(valueFromPercentage);
        mfpDailyGoal2.setProtein(valueFromPercentage2);
        mfpDailyGoal2.setFat(valueFromPercentage3);
        return mfpDailyGoal2;
    }

    public MfpDailyGoal getDailyGoalWithNewEnergyValue(MfpDailyGoal mfpDailyGoal, float f) {
        return getDailyGoalWithNewEnergyValue(mfpDailyGoal, new MfpMeasuredValue(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit(), f));
    }

    public int dayOfWeekNameToIndex(String str) {
        try {
            return DateTimeUtils.getDayOfWeekIndex(str, 2);
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException("dayOfWeek");
        }
    }

    public float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i) {
        return getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, i, true);
    }

    public float getAdjustedNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i, boolean z) {
        float f;
        float f2;
        if (mfpDailyGoal != null) {
            f = getGoalValueAfterConversion(mfpDailyGoal, i);
            if (z && mfpDailyGoal.isAssignExerciseEnergyOn()) {
                int i2 = 4;
                if (i != 9) {
                    switch (i) {
                        case 0:
                            f += diaryDay.caloriesBurnedByExercise(true);
                            f2 = -1.0f;
                            i2 = 0;
                            break;
                        case 1:
                            f2 = (float) mfpDailyGoal.getExerciseFatPercentage();
                            i2 = 9;
                            break;
                        case 2:
                            f2 = (float) mfpDailyGoal.getExerciseSaturatedFatPercentage();
                            i2 = 9;
                            break;
                        default:
                            switch (i) {
                                case 11:
                                    f2 = (float) mfpDailyGoal.getExerciseSugarPercentage();
                                    break;
                                case 12:
                                    f2 = (float) mfpDailyGoal.getExerciseProteinPercentage();
                                    break;
                                default:
                                    f2 = -1.0f;
                                    i2 = 0;
                                    break;
                            }
                    }
                } else {
                    f2 = (float) mfpDailyGoal.getExerciseCarbohydratesPercentage();
                }
                if (f2 != -1.0f) {
                    f += getExerciseValueAfterCalculations(diaryDay.caloriesBurnedByExercise(false), f2, i2);
                }
                return f;
            }
        } else {
            f = diaryDay.getNutrientGoal(i);
            if (i == 0) {
                f = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) f);
            }
        }
        return f;
    }

    public MfpNutrientGoal transformDailyGoals(MfpNutrientGoal mfpNutrientGoal, ReturningFunction2<MfpDailyGoal, MfpDailyGoal, MfpDailyGoal> returningFunction2) {
        MfpNutrientGoal mfpNutrientGoal2 = (MfpNutrientGoal) ParcelableUtil.clone(mfpNutrientGoal, MfpNutrientGoal.CREATOR);
        MfpDailyGoal defaultGoal = mfpNutrientGoal2.getDefaultGoal();
        List dailyGoals = mfpNutrientGoal2.getDailyGoals();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dailyGoals.size(); i++) {
            arrayList.add(returningFunction2.execute(defaultGoal, (MfpDailyGoal) ParcelableUtil.clone((Parcelable) dailyGoals.get(i), MfpDailyGoal.CREATOR)));
        }
        mfpNutrientGoal2.setDailyGoals(arrayList);
        return mfpNutrientGoal2;
    }

    private float getExerciseValueAfterCalculations(float f, float f2, int i) {
        return NumberUtils.getValueFromPercentage(f2, f) / ((float) i);
    }

    private float getGoalValueAfterConversion(MfpDailyGoal mfpDailyGoal, int i) {
        float valueForNutritionalValuesIndex = mfpDailyGoal.getValueForNutritionalValuesIndex(i);
        return i == 0 ? ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) valueForNutritionalValuesIndex) : valueForNutritionalValuesIndex;
    }

    public MfpDailyGoal updateDailyGoal(MfpDailyGoal mfpDailyGoal, String str, float f) {
        if (Strings.isEmpty(str)) {
            return mfpDailyGoal;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1930010315:
                if (str.equals("potassium")) {
                    c = 9;
                    break;
                }
                break;
            case -1762597548:
                if (str.equals("vitamin_a")) {
                    c = 12;
                    break;
                }
                break;
            case -1762597546:
                if (str.equals("vitamin_c")) {
                    c = 13;
                    break;
                }
                break;
            case -1734926706:
                if (str.equals("cholesterol")) {
                    c = 7;
                    break;
                }
                break;
            case -1675598797:
                if (str.equals(Nutrient.MONOUNSATURATED_FAT)) {
                    c = 5;
                    break;
                }
                break;
            case -897020359:
                if (str.equals("sodium")) {
                    c = 8;
                    break;
                }
                break;
            case -309012605:
                if (str.equals("protein")) {
                    c = 1;
                    break;
                }
                break;
            case 101145:
                if (str.equals("fat")) {
                    c = 2;
                    break;
                }
                break;
            case 3241160:
                if (str.equals("iron")) {
                    c = 15;
                    break;
                }
                break;
            case 97424620:
                if (str.equals(Nutrient.FIBER)) {
                    c = 10;
                    break;
                }
                break;
            case 109792566:
                if (str.equals("sugar")) {
                    c = 11;
                    break;
                }
                break;
            case 548373068:
                if (str.equals("calcium")) {
                    c = 14;
                    break;
                }
                break;
            case 671178785:
                if (str.equals(Nutrient.CARBOHYDRATES)) {
                    c = 0;
                    break;
                }
                break;
            case 863926314:
                if (str.equals(Nutrient.POLYUNSATURATED_FAT)) {
                    c = 4;
                    break;
                }
                break;
            case 964882287:
                if (str.equals("saturated_fat")) {
                    c = 3;
                    break;
                }
                break;
            case 1052449026:
                if (str.equals("trans_fat")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                mfpDailyGoal.setCarbohydrates(f);
                break;
            case 1:
                mfpDailyGoal.setProtein(f);
                break;
            case 2:
                mfpDailyGoal.setFat(f);
                break;
            case 3:
                mfpDailyGoal.setSaturatedFat(f);
                break;
            case 4:
                mfpDailyGoal.setPolyunsaturatedFat(f);
                break;
            case 5:
                mfpDailyGoal.setMonounsaturatedFat(f);
                break;
            case 6:
                mfpDailyGoal.setTransFat(f);
                break;
            case 7:
                mfpDailyGoal.setCholesterol(f);
                break;
            case 8:
                mfpDailyGoal.setSodium(f);
                break;
            case 9:
                mfpDailyGoal.setPotassium(f);
                break;
            case 10:
                mfpDailyGoal.setFiber(f);
                break;
            case 11:
                mfpDailyGoal.setSugar(f);
                break;
            case 12:
                mfpDailyGoal.setVitaminA((int) f);
                break;
            case 13:
                mfpDailyGoal.setVitaminC((int) f);
                break;
            case 14:
                mfpDailyGoal.setCalcium((int) f);
                break;
            case 15:
                mfpDailyGoal.setIron((int) f);
                break;
            default:
                return mfpDailyGoal;
        }
        return mfpDailyGoal;
    }

    public MfpNutrientGoal updateAllDailyGoalsWithNewNonMacroValues(MfpNutrientGoal mfpNutrientGoal) {
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
            mfpDailyGoal.setSaturatedFat(defaultGoal.getSaturatedFat());
            mfpDailyGoal.setPolyunsaturatedFat(defaultGoal.getPolyunsaturatedFat());
            mfpDailyGoal.setMonounsaturatedFat(defaultGoal.getMonounsaturatedFat());
            mfpDailyGoal.setTransFat(defaultGoal.getTransFat());
            mfpDailyGoal.setCholesterol(defaultGoal.getCholesterol());
            mfpDailyGoal.setSodium(defaultGoal.getSodium());
            mfpDailyGoal.setPotassium(defaultGoal.getPotassium());
            mfpDailyGoal.setFiber(defaultGoal.getFiber());
            mfpDailyGoal.setSugar(defaultGoal.getSugar());
            mfpDailyGoal.setVitaminA(defaultGoal.getVitaminA());
            mfpDailyGoal.setVitaminC(defaultGoal.getVitaminC());
            mfpDailyGoal.setCalcium(defaultGoal.getCalcium());
            mfpDailyGoal.setIron(defaultGoal.getIron());
        }
        return mfpNutrientGoal;
    }

    public boolean isCustomGoalsPresent(MfpNutrientGoal mfpNutrientGoal) {
        if (mfpNutrientGoal == null) {
            return false;
        }
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        List dailyGoals = mfpNutrientGoal.getDailyGoals();
        if (defaultGoal != null && CollectionUtils.notEmpty((Collection<?>) dailyGoals)) {
            String hashKey = mfpNutrientGoal.getDefaultGoal().toHashKey();
            for (MfpDailyGoal hashKey2 : mfpNutrientGoal.getDailyGoals()) {
                if (!Strings.equals(hashKey, hashKey2.toHashKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    public MfpDailyGoal getDefaultDailyGoal(MfpNutrientGoal mfpNutrientGoal) {
        if (mfpNutrientGoal != null) {
            return mfpNutrientGoal.getDefaultGoal();
        }
        return null;
    }

    public MfpDailyGoal getDailyGoalForDayOfWeek(MfpNutrientGoal mfpNutrientGoal, String str) {
        MfpDailyGoal mfpDailyGoal = null;
        if (mfpNutrientGoal == null) {
            return null;
        }
        for (MfpDailyGoal mfpDailyGoal2 : mfpNutrientGoal.getDailyGoals()) {
            if (Strings.equalsIgnoreCase(mfpDailyGoal2.getDayOfWeek(), str)) {
                mfpDailyGoal = mfpDailyGoal2;
            }
        }
        return mfpDailyGoal;
    }

    public void normalizeMealGoalsForTotal(MfpDailyGoal mfpDailyGoal, MfpMeasuredValue mfpMeasuredValue, MfpMeasuredValue mfpMeasuredValue2) {
        if (mfpDailyGoal != null && mfpMeasuredValue != null && mfpMeasuredValue2 != null) {
            List<MealGoal> mealGoals = mfpDailyGoal.getMealGoals();
            if (CollectionUtils.notEmpty((Collection<?>) mealGoals)) {
                String unit = mfpDailyGoal.getEnergy().getUnit();
                Energy energy = Strings.equals(unit, "calories") ? Energy.CALORIES : Energy.KILOJOULES;
                float value = (float) LocalizedEnergy.fromMeasuredValue(mfpMeasuredValue).getValue(Energy.CALORIES);
                float value2 = (float) LocalizedEnergy.fromMeasuredValue(mfpMeasuredValue2).getValue(Energy.CALORIES);
                float f = BitmapDescriptorFactory.HUE_RED;
                for (MealGoal mealGoal : mealGoals) {
                    if (!(mealGoal == null || mealGoal.getEnergy() == null)) {
                        float roundToNearestPlace = NumberUtils.roundToNearestPlace(NumberUtils.getValueFromPercentage((float) NumberUtils.getRoundedPercentage((float) LocalizedEnergy.fromMeasuredValue(mealGoal.getEnergy()).getValue(Energy.CALORIES), value), value2), 1.0f);
                        f += roundToNearestPlace;
                        if (f > value2) {
                            roundToNearestPlace -= f - value2;
                            f = value2;
                        }
                        mealGoal.setEnergy(new MfpMeasuredValue(unit, (float) LocalizedEnergy.fromCalories(roundToNearestPlace < BitmapDescriptorFactory.HUE_RED ? 0.0d : (double) roundToNearestPlace).getValue(energy)));
                    }
                }
                MealGoal mealGoal2 = (MealGoal) mealGoals.get(mealGoals.size() - 1);
                if (mealGoal2 != null && mealGoal2.getEnergy() != null) {
                    mealGoal2.setEnergy(new MfpMeasuredValue(unit, (float) LocalizedEnergy.fromCalories((double) (((float) LocalizedEnergy.fromMeasuredValue(mealGoal2.getEnergy()).getValue(Energy.CALORIES)) + (value2 - f))).getValue(energy)));
                }
            }
        }
    }

    public MealGoal getMealGoalForMeal(MfpDailyGoal mfpDailyGoal, int i) {
        if (mfpDailyGoal == null) {
            return null;
        }
        List<MealGoal> mealGoals = mfpDailyGoal.getMealGoals();
        if (CollectionUtils.notEmpty((Collection<?>) mealGoals)) {
            for (MealGoal mealGoal : mealGoals) {
                if (mealGoal.getMealIndex() == i) {
                    return mealGoal;
                }
            }
        }
        return null;
    }

    public MealGoal getMealGoalForMeal(MfpNutrientGoal mfpNutrientGoal, Date date, int i) {
        if (mfpNutrientGoal == null || date == null) {
            return null;
        }
        return getMealGoalForMeal(getDailyGoalForDayOfWeek(mfpNutrientGoal, DateTimeUtils.getDayOfTheWeek(date)), i);
    }

    public List<MealGoal> getDefaultMealGoals(MfpDailyGoal mfpDailyGoal, MealNames mealNames) {
        LinkedList linkedList = new LinkedList();
        int size = mealNames.size();
        MfpMeasuredValue dailyGoalMeasuredValue = getDailyGoalMeasuredValue(mfpDailyGoal);
        float value = dailyGoalMeasuredValue.getValue();
        String unit = dailyGoalMeasuredValue.getUnit();
        if (size == 4) {
            float roundToNearestPlace = NumberUtils.roundToNearestPlace(0.3f * value, 1.0f);
            float f = value - (3.0f * roundToNearestPlace);
            for (String mealIndexForName : mealNames.getNames()) {
                linkedList.add(new MealGoal(mealNames.mealIndexForName(mealIndexForName), new MfpMeasuredValue(unit, linkedList.size() < 3 ? roundToNearestPlace : f)));
            }
        } else {
            float f2 = BitmapDescriptorFactory.HUE_RED;
            float roundToNearestPlace2 = size > 0 ? NumberUtils.roundToNearestPlace(value, 1.0f) % ((float) size) : BitmapDescriptorFactory.HUE_RED;
            if (size > 0) {
                f2 = (value - roundToNearestPlace2) / ((float) size);
            }
            float f3 = f2 + roundToNearestPlace2;
            for (String mealIndexForName2 : mealNames.getNames()) {
                linkedList.add(new MealGoal(mealNames.mealIndexForName(mealIndexForName2), new MfpMeasuredValue(unit, linkedList.size() < mealNames.size() + -1 ? f2 : f3)));
            }
        }
        return linkedList;
    }

    public float mealGoalsEnergySum(List<MealGoal> list) {
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) list);
        float f = BitmapDescriptorFactory.HUE_RED;
        if (notEmpty) {
            for (MealGoal mealGoal : list) {
                if (!(mealGoal == null || mealGoal.getEnergy() == null)) {
                    f += mealGoal.getEnergy().getValue();
                }
            }
        }
        return f;
    }

    public float mealGoalsEnergySumInCurrentUnits(List<MealGoal> list) {
        boolean isCalories = ((UserEnergyService) this.userEnergyService.get()).isCalories();
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) list);
        float f = BitmapDescriptorFactory.HUE_RED;
        if (notEmpty) {
            for (MealGoal mealGoal : list) {
                if (!(mealGoal == null || mealGoal.getEnergy() == null)) {
                    f += (float) LocalizedEnergy.getValueInUserCurrentUnitsFromMeasuredValue(mealGoal.getEnergy(), isCalories);
                }
            }
        }
        return f;
    }

    public MfpNutrientGoal saveMealGoalsForDailyGoal(MfpNutrientGoal mfpNutrientGoal, MfpDailyGoal mfpDailyGoal) {
        if (mfpDailyGoal == null) {
            return mfpNutrientGoal;
        }
        List mealGoals = mfpDailyGoal.getMealGoals();
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        List<MfpDailyGoal> dailyGoals = mfpNutrientGoal.getDailyGoals();
        if (!isCustomGoalsPresent(mfpNutrientGoal)) {
            defaultGoal.setMealGoals(mealGoals);
            for (MfpDailyGoal mfpDailyGoal2 : dailyGoals) {
                if (CollectionUtils.isEmpty((Collection<?>) mfpDailyGoal2.getMealGoals())) {
                    mfpDailyGoal2.setMealGoals(mealGoals);
                }
            }
        } else {
            String dayOfWeek = mfpDailyGoal.getDayOfWeek();
            for (MfpDailyGoal mfpDailyGoal3 : dailyGoals) {
                if (CollectionUtils.isEmpty((Collection<?>) mfpDailyGoal3.getMealGoals())) {
                    mfpDailyGoal3.setMealGoals(mealGoals);
                } else if (Strings.equals(mfpDailyGoal3.getDayOfWeek(), dayOfWeek)) {
                    mfpDailyGoal3.setMealGoals(mealGoals);
                }
            }
        }
        return mfpNutrientGoal;
    }

    public MfpNutrientGoal setWeekDailyGoalsToDefaultDailyGoalIfNeeded(MfpNutrientGoal mfpNutrientGoal) {
        if (mfpNutrientGoal != null && !isCustomGoalsPresent(mfpNutrientGoal)) {
            MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
            List mealGoals = defaultGoal.getMealGoals();
            for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
                if (defaultGoal.getEnergy().getValue() == mfpDailyGoal.getEnergy().getValue()) {
                    mfpDailyGoal.setMealGoals(mealGoals);
                }
            }
        }
        return mfpNutrientGoal;
    }

    public MfpMeasuredValue getDailyGoalMeasuredValue(MfpDailyGoal mfpDailyGoal) {
        return mfpDailyGoal == null ? new MfpMeasuredValue(Strings.toString("calories"), BitmapDescriptorFactory.HUE_RED) : mfpDailyGoal.getEnergy();
    }
}
