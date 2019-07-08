package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealEntries;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodViewModel;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.model.v2.MfpVersionedDatabaseObjectV2;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Inject;

public class UserEnergyService {
    private static final int MAX_ENERGY_VALUE = 21000;
    private static final int MIN_ENERGY_VALUE = 100;
    private final Context context;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Lazy<MealUtil> mealHelperUtil;
    private final Lazy<Session> session;

    public String getQuickAddedDescription(FoodEntry foodEntry) {
        return foodEntry == null ? null : null;
    }

    @Inject
    public UserEnergyService(Context context2, Lazy<Session> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<MealUtil> lazy3) {
        this.context = context2;
        this.session = lazy;
        this.localizedStringsUtil = lazy2;
        this.mealHelperUtil = lazy3;
    }

    public void setUseCurrentEnergyUnit(Energy energy) {
        ((Session) this.session.get()).getUser().setProperty(Units.ENERGY_UNIT_PREFERENCE, String.valueOf(energy.getValue()));
    }

    public Energy getUserCurrentEnergyUnit() {
        return Energy.fromInt(((Session) this.session.get()).getUser().getEnergyUnitPreference());
    }

    public boolean isGoalEnergyValid(float f) {
        float calories = getCalories(f);
        return calories >= 100.0f && calories <= 21000.0f;
    }

    public float getCalories(float f) {
        return isCalories() ? f : (float) UnitsUtils.getCalories((double) f);
    }

    public float getCalories(String str) {
        return getCalories(NumberUtils.localeFloatFromString(str));
    }

    public String getDisplayableEnergy(double d) {
        return getDisplayableEnergy(getUserCurrentEnergyUnit(), d);
    }

    public String getDisplayableEnergy(Energy energy, double d) {
        return energy == Energy.CALORIES ? NumberUtils.localeStringFromDoubleNoDecimal(d) : UnitsUtils.getLocalizedEnergyString(energy, d);
    }

    public String getDisplayableEnergy(String str) {
        return getDisplayableEnergy(getUserCurrentEnergyUnit(), str);
    }

    public String getDisplayableEnergy(Energy energy, String str) {
        return getDisplayableEnergy(energy, (double) NumberUtils.localeFloatFromString(str));
    }

    public float getEnergy(Energy energy, String str) {
        return (float) (energy == Energy.CALORIES ? (double) NumberUtils.localeFloatFromString(str) : UnitsUtils.getCalories((double) NumberUtils.localeFloatFromString(str)));
    }

    public float getEnergy(Energy energy, double d) {
        if (energy != Energy.CALORIES) {
            d = UnitsUtils.getKilojoules(d);
        }
        return (float) d;
    }

    public float getEnergy(Energy energy, MfpMeasuredValue mfpMeasuredValue) {
        boolean z = energy == Energy.CALORIES;
        boolean equals = Strings.equals(mfpMeasuredValue.getUnit(), "calories");
        float value = mfpMeasuredValue.getValue();
        if (z == equals) {
            return value;
        }
        if (z) {
            return (float) UnitsUtils.getCalories((double) value);
        }
        return UnitsUtils.getKilojoules(value);
    }

    public float getCurrentEnergy(double d) {
        return getEnergy(getUserCurrentEnergyUnit(), d);
    }

    public float getCurrentEnergy(MfpMeasuredValue mfpMeasuredValue) {
        return getEnergy(getUserCurrentEnergyUnit(), mfpMeasuredValue);
    }

    public int getRoundedCurrentEnergy(double d) {
        return Math.round(getCurrentEnergy(d));
    }

    public String getRoundedCurrentEnergyAsString(double d, boolean z) {
        float currentEnergy = getCurrentEnergy(d);
        if (z) {
            currentEnergy = Math.abs(currentEnergy);
        }
        return NumberUtils.localeStringFromDoubleNoDecimal((double) currentEnergy);
    }

    public int getCurrentEnergyStringId() {
        return isCalories() ? R.string.calories : R.string.kilojoules;
    }

    public int getCurrentAbbreviatedEnergyStringId() {
        return isCalories() ? R.string.cal : R.string.kj;
    }

    public String getCurrentEnergyUnit() {
        return isCalories() ? "calories" : "kilojoules";
    }

    public String getCurrentEnergyUnitString() {
        return this.context.getString(getCurrentEnergyStringId());
    }

    public int getCurrentEnergyLowerCaseStringId(float f) {
        boolean areEffectivelyEqual = NumberUtils.areEffectivelyEqual(f, 1.0f);
        return isCalories() ? areEffectivelyEqual ? R.string.calorie_lower_case : R.string.calories_lower_case : areEffectivelyEqual ? R.string.kilojoule_lower_case : R.string.kilojoules_lower_case;
    }

    public String getCurrentEnergyLowerCaseString(float f) {
        return this.context.getString(getCurrentEnergyLowerCaseStringId(f));
    }

    public String getDisplayableShortUnitString() {
        return this.context.getString(isCalories() ? R.string.cal : R.string.kj);
    }

    public boolean isCalories() {
        return getUserCurrentEnergyUnit() == Energy.CALORIES;
    }

    public String getDisplayableEnergy(DiaryEntryCellModel diaryEntryCellModel) {
        if (diaryEntryCellModel instanceof FoodEntry) {
            return getFoodEntryDisplayableCalories((FoodEntry) diaryEntryCellModel);
        }
        if (diaryEntryCellModel instanceof MealFood) {
            return getMealFoodDisplayableCalories((MealFood) diaryEntryCellModel);
        }
        if (diaryEntryCellModel instanceof Food) {
            return getFoodDisplayableCalories((Food) diaryEntryCellModel);
        }
        return null;
    }

    public String getDisplayableEnergy(MfpFood mfpFood) {
        return getDisplayableEnergy(getCaloriesForFoodWithServingSize(mfpFood, mfpFood.getSelectedServingAmount(), (MfpServingSize) mfpFood.getServingSizes().get(mfpFood.getSelectedServingSizeIndex())));
    }

    public String getDescription(DiaryEntryCellModel diaryEntryCellModel) {
        return getDescription(diaryEntryCellModel, true);
    }

    public String getDescription(DiaryEntryCellModel diaryEntryCellModel, boolean z) {
        try {
            if (diaryEntryCellModel instanceof FoodEntry) {
                return getFoodEntryDescription((FoodEntry) diaryEntryCellModel, z);
            }
            if (diaryEntryCellModel instanceof Food) {
                return getFoodDescription((Food) diaryEntryCellModel, z);
            }
            if (diaryEntryCellModel instanceof MfpFoodViewModel) {
                return getFoodDescription(((MfpFoodViewModel) diaryEntryCellModel).getFood(), z);
            }
            if (diaryEntryCellModel instanceof MealEntries) {
                return getMealEntryDescription((MealEntries) diaryEntryCellModel, z);
            }
            return null;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public String getV2ObjectDesctiption(MfpVersionedDatabaseObjectV2 mfpVersionedDatabaseObjectV2) {
        if (mfpVersionedDatabaseObjectV2 instanceof MfpFood) {
            return getFoodDescription((MfpFood) mfpVersionedDatabaseObjectV2, true, false);
        }
        if (mfpVersionedDatabaseObjectV2 instanceof MfpRecipe) {
            return getRecipeDescription((MfpRecipe) mfpVersionedDatabaseObjectV2, true);
        }
        return null;
    }

    public String getDescription(MfpFood mfpFood) {
        if (mfpFood != null) {
            return getFoodDescription(mfpFood, true);
        }
        return null;
    }

    public String getFoodDescription(MfpFood mfpFood, boolean z) {
        return getFoodDescription(mfpFood, z, true);
    }

    public String getMealEntriesTitle(DiaryEntryCellModel diaryEntryCellModel) {
        if (!(diaryEntryCellModel instanceof MealEntries)) {
            return "";
        }
        MealEntries mealEntries = (MealEntries) diaryEntryCellModel;
        String mealName = mealEntries.getMealName();
        String mealNameString = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(mealName != null ? mealName.substring(0, 1).toUpperCase().concat(mealName.substring(1)) : "", this);
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.add(6, -1);
        Calendar calendar2 = (Calendar) Calendar.getInstance().clone();
        calendar2.setTime(mealEntries.getEntryDate());
        if (calendar2.get(1) == calendar.get(1) && calendar2.get(6) == calendar.get(6)) {
            return this.context.getResources().getString(R.string.yesterdays_meal, new Object[]{mealNameString});
        }
        return this.context.getResources().getString(R.string.meal_from_date, new Object[]{mealNameString, DateTimeUtils.getMediumLocaleFormattedDate(this.context, calendar2.getTime())});
    }

    private String getFoodEntryDisplayableCalories(FoodEntry foodEntry) {
        Food food = foodEntry.getFood();
        if (food == null) {
            return null;
        }
        float quantity = foodEntry.getQuantity();
        if (food.isLegacyQuickAddedCalories()) {
            return getDisplayableEnergy((double) quantity);
        }
        float caloriesValueFromNutritionalValues = food.getCaloriesValueFromNutritionalValues(quantity * food.nutrientMultiplierForFoodPortion(foodEntry.getFoodPortion()), -1.0f);
        return caloriesValueFromNutritionalValues == -1.0f ? this.context.getResources().getString(R.string.dash) : getDisplayableEnergy((double) caloriesValueFromNutritionalValues);
    }

    private String getFoodEntryDescription(FoodEntry foodEntry, boolean z) {
        String description = foodEntry.getDescription();
        if (Strings.notEmpty(description)) {
            return description;
        }
        Food food = foodEntry.getFood();
        if (food == null) {
            return null;
        }
        float quantity = foodEntry.getQuantity();
        if (food.isLegacyQuickAddedCalories()) {
            String energyWithUnitString = getEnergyWithUnitString(getDisplayableEnergy((double) quantity), getDisplayableShortUnitString());
            foodEntry.setDescription(energyWithUnitString);
            return energyWithUnitString;
        }
        FoodPortion foodPortion = foodEntry.getFoodPortion();
        StringBuilder sb = new StringBuilder();
        if (food.hasBrand()) {
            sb.append(food.getBrand());
        }
        if (foodPortion != null) {
            addDelimiterIfValid(sb);
            sb.append(foodPortion.newDescriptionStringWithAmount(foodPortion.getAmount() * quantity));
        }
        if (z) {
            addDelimiterIfValid(sb);
            float caloriesValueFromNutritionalValues = food.getCaloriesValueFromNutritionalValues(quantity * food.nutrientMultiplierForFoodPortion(foodPortion), -1.0f);
            sb.append(getEnergyWithUnitString(caloriesValueFromNutritionalValues == -1.0f ? this.context.getResources().getString(R.string.dash) : getDisplayableEnergy((double) caloriesValueFromNutritionalValues), getDisplayableShortUnitString()));
        }
        String sb2 = sb.toString();
        foodEntry.setDescription(sb2);
        return sb2;
    }

    private String getFoodDisplayableCalories(Food food) {
        FoodPortion defaultPortion = food.defaultPortion();
        if (defaultPortion == null) {
            return this.context.getResources().getString(R.string.dash);
        }
        return getDisplayableEnergy((double) food.getCaloriesFromNutritionalValues(food.nutrientMultiplierForFoodPortion(defaultPortion), BitmapDescriptorFactory.HUE_RED));
    }

    private String getMealFoodDisplayableCalories(MealFood mealFood) {
        return LocalizedEnergy.getDisplayStringWithoutUnits(LocalizedEnergy.fromCalories(((MealUtil) this.mealHelperUtil.get()).getNutritionalContents(mealFood).getCalories().doubleValue()), getUserCurrentEnergyUnit());
    }

    private String getFoodDescription(Food food, boolean z) {
        String cachedCompleteDescription = food.getCachedCompleteDescription();
        if (Strings.isEmpty(cachedCompleteDescription)) {
            FoodPortion defaultPortion = food.defaultPortion();
            if (defaultPortion == null) {
                cachedCompleteDescription = this.context.getResources().getString(R.string.invalid_food, new Object[]{Strings.toString(Long.valueOf(food.getLocalId()))});
            } else {
                String descriptionWithAmount = defaultPortion.descriptionWithAmount();
                StringBuilder sb = new StringBuilder();
                if (food.hasBrand()) {
                    sb.append(food.getBrand());
                }
                if (Strings.notEmpty(descriptionWithAmount)) {
                    addDelimiterIfValid(sb);
                    sb.append(descriptionWithAmount);
                }
                if (z) {
                    addDelimiterIfValid(sb);
                    sb.append(getEnergyWithUnitString(getDisplayableEnergy((double) food.getCaloriesFromNutritionalValues(food.nutrientMultiplierForFoodPortion(defaultPortion), BitmapDescriptorFactory.HUE_RED)), getDisplayableShortUnitString()));
                }
                cachedCompleteDescription = sb.toString();
            }
            food.setCachedCompleteDescription(cachedCompleteDescription);
        }
        return cachedCompleteDescription;
    }

    private String getFoodDescription(MfpFood mfpFood, boolean z, boolean z2) {
        int selectedServingSizeIndex = mfpFood.getSelectedServingSizeIndex();
        return getFoodDescription(mfpFood, (MfpServingSize) mfpFood.getServingSizes().get(selectedServingSizeIndex), mfpFood.getSelectedServingAmount(), z, z2);
    }

    private String getFoodDescription(MfpFood mfpFood, MfpServingSize mfpServingSize, float f, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        String descriptionWithAmount = mfpServingSize.descriptionWithAmount(f);
        if (z2 && mfpFood.hasBrand()) {
            sb.append(mfpFood.getBrandName());
        }
        if (Strings.notEmpty(descriptionWithAmount)) {
            addDelimiterIfValid(sb);
            sb.append(descriptionWithAmount);
        }
        if (z) {
            addDelimiterIfValid(sb);
            sb.append(getEnergyWithUnitString(getDisplayableEnergy(getCaloriesForFoodWithServingSize(mfpFood, f, mfpServingSize)), getDisplayableShortUnitString()));
        }
        return sb.toString();
    }

    private double getCaloriesForFoodWithServingSize(MfpFood mfpFood, float f, MfpServingSize mfpServingSize) {
        return ((double) f) * mfpServingSize.getNutritionMultiplier().doubleValue() * mfpFood.getNutritionalContents().getCalories().doubleValue();
    }

    public final String getRecipeDescription(MfpRecipe mfpRecipe, boolean z) {
        double doubleValue = mfpRecipe.getServings().doubleValue();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(NumberUtils.localeStringFromDouble(doubleValue, 2));
        sb2.append(" ");
        sb.append(sb2.toString());
        sb.append(this.context.getString(NumberUtils.areEffectivelyEqual(1.0d, doubleValue) ? R.string.recipe_review_serving : R.string.recipe_review_servings));
        if (z) {
            addDelimiterIfValid(sb);
            sb.append(getEnergyWithUnitString(getDisplayableEnergy(mfpRecipe.getNutritionalContents().getCalories().doubleValue()), getDisplayableShortUnitString()));
        }
        return sb.toString();
    }

    public String getIngredientDescription(MfpIngredient mfpIngredient) {
        return getFoodDescription(mfpIngredient.getFood(), mfpIngredient.getServingSize(), mfpIngredient.getServings().floatValue(), false, false);
    }

    private String getMealEntryDescription(MealEntries mealEntries, boolean z) {
        ArrayList entries = mealEntries.getEntries();
        int size = entries != null ? entries.size() : 0;
        if (size == 1) {
            return this.context.getString(z ? R.string.meal_item : R.string.meal_item_new, new Object[]{Integer.toString(size), Integer.valueOf(getRoundedCurrentEnergy((double) mealEntries.totalCalories())), getCurrentEnergyUnitString()});
        }
        return this.context.getString(z ? R.string.meal_items : R.string.meal_items_new, new Object[]{Integer.toString(size), Integer.valueOf(getRoundedCurrentEnergy((double) mealEntries.totalCalories())), getCurrentEnergyUnitString()});
    }

    private void addDelimiterIfValid(StringBuilder sb) {
        sb.append(Strings.notEmpty((Object) sb) ? ", " : "");
    }

    private String getEnergyWithUnitString(String str, String str2) {
        return String.format("%s %s", new Object[]{str, str2});
    }
}
