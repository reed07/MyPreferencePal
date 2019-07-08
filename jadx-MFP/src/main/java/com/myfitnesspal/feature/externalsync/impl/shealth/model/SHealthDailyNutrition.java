package com.myfitnesspal.feature.externalsync.impl.shealth.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.samsung.android.sdk.healthdata.HealthConstants.FoodIntake;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SHealthDailyNutrition {
    private static final Map<Integer, Integer> MEAL_ID_TO_STRING_ID = new HashMap();
    private final Context context;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Map<Integer, List<FoodEntry>> mealToEntriesMap = new HashMap();
    private final Lazy<UserEnergyService> userEnergyService;

    static {
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_LUNCH), Integer.valueOf(R.string.lunch));
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_BREAKFAST), Integer.valueOf(R.string.breakfast));
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_MORNING_SNACK), Integer.valueOf(R.string.snacks));
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_AFTERNOON_SNACK), Integer.valueOf(R.string.snacks));
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_DINNER), Integer.valueOf(R.string.dinner));
        MEAL_ID_TO_STRING_ID.put(Integer.valueOf(FoodIntake.MEAL_TYPE_EVENING_SNACK), Integer.valueOf(R.string.snacks));
    }

    public SHealthDailyNutrition(Context context2, List<FoodEntry> list, Lazy<LocalizedStringsUtil> lazy, Lazy<UserEnergyService> lazy2) {
        this.context = context2;
        this.localizedStringsUtil = lazy;
        this.userEnergyService = lazy2;
        groupByMeal(list);
    }

    public List<FoodEntry> getFoodEntriesForMealId(int i) {
        List list = (List) this.mealToEntriesMap.get(Integer.valueOf(i));
        return list != null ? new ArrayList(list) : new ArrayList();
    }

    private void groupByMeal(List<FoodEntry> list) {
        this.mealToEntriesMap.clear();
        for (FoodEntry foodEntry : list) {
            if (Strings.notEmpty(foodEntry.getUid()) && Strings.notEmpty(foodEntry.getFood().getUid())) {
                int samsungMealId = getSamsungMealId(foodEntry);
                if (!this.mealToEntriesMap.containsKey(Integer.valueOf(samsungMealId))) {
                    this.mealToEntriesMap.put(Integer.valueOf(samsungMealId), new ArrayList());
                }
                ((List) this.mealToEntriesMap.get(Integer.valueOf(samsungMealId))).add(foodEntry);
            }
        }
    }

    public static int getStringIdForSamsungMeal(int i) {
        return MEAL_ID_TO_STRING_ID.containsKey(Integer.valueOf(i)) ? ((Integer) MEAL_ID_TO_STRING_ID.get(Integer.valueOf(i))).intValue() : R.string.snacks;
    }

    private int getSamsungMealId(FoodEntry foodEntry) {
        String mealName = foodEntry.getMealName();
        if (mealName.equalsIgnoreCase(MealTypeName.BREAKFAST) || this.context.getString(R.string.breakfast).equalsIgnoreCase(getLocalizedMealName(foodEntry))) {
            return FoodIntake.MEAL_TYPE_BREAKFAST;
        }
        if (mealName.equalsIgnoreCase(MealTypeName.LUNCH) || this.context.getString(R.string.lunch).equalsIgnoreCase(getLocalizedMealName(foodEntry))) {
            return FoodIntake.MEAL_TYPE_LUNCH;
        }
        if (mealName.equalsIgnoreCase(MealTypeName.DINNER) || this.context.getString(R.string.dinner).equalsIgnoreCase(getLocalizedMealName(foodEntry))) {
            return FoodIntake.MEAL_TYPE_DINNER;
        }
        return (mealName.equalsIgnoreCase(MealTypeName.SNACK) || this.context.getString(R.string.snacks).equalsIgnoreCase(getLocalizedMealName(foodEntry))) ? FoodIntake.MEAL_TYPE_AFTERNOON_SNACK : FoodIntake.MEAL_TYPE_AFTERNOON_SNACK;
    }

    private String getLocalizedMealName(FoodEntry foodEntry) {
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(foodEntry.getMealName(), (UserEnergyService) this.userEnergyService.get());
    }
}
