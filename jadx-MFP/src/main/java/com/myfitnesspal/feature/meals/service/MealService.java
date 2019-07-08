package com.myfitnesspal.feature.meals.service;

import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import java.util.List;

public interface MealService {

    public enum CreateMode {
        Create,
        Update,
        Copy
    }

    public enum ImageMode {
        Associate,
        Disassociate
    }

    void associateMealImages(MealFood mealFood, MealFood mealFood2, String str);

    MealFood createCustomMeal(CreateMode createMode, String str, List<FoodEntry> list, MealFood mealFood, Permission permission, ImageMode imageMode, String str2, long j, String str3);

    void deleteMealFood(MealFood mealFood);

    void disassociateMealImage(MealFood mealFood);

    MealFood getExistingMealFoodWithDescription(String str);

    List<MealIngredient> getMealIngredientsFromFoodEntries(List<FoodEntry> list, long j);

    void saveMealFoodToDiary(List<FoodEntry> list, String str, MealFoodLoggedInfo mealFoodLoggedInfo, MealFood mealFood, TimeValue timeValue, int i);

    void syncAndUpdateIdsIfNeeded(MealFood mealFood);
}
