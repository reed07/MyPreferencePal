package com.myfitnesspal.feature.meals.util;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealEntries;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import java.util.List;

public interface MealUtil {
    FoodEntry createFoodEntryForDiaryToast(Food food, String str);

    FoodEntry foodEntryFromMfpFood(MfpFood mfpFood);

    List<FoodEntry> getFoodEntriesFromIngredients(MealFood mealFood, float f);

    MealFood getMealFoodFromMealEntries(MealEntries mealEntries);

    List<MealIngredient> getMealIngredientsFromFoodEntries(List<FoodEntry> list);

    MfpFood getMfpFoodFromFoodEntry(FoodEntry foodEntry);

    MfpNutritionalContents getNutritionalContents(MealFood mealFood);

    MfpNutritionalContents getNutritionalContents(List<FoodEntry> list);

    MealFood loadMealFoodLazy(MealFood mealFood);
}
