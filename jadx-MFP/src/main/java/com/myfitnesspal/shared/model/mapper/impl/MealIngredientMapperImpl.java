package com.myfitnesspal.shared.model.mapper.impl;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.uacf.core.util.Ln;
import java.io.IOException;

public class MealIngredientMapperImpl implements MealIngredientMapper {
    static final String MEAL_NAME = "(Meal)";

    public MealIngredient reverseMap(FoodEntry foodEntry) {
        MealIngredient mealIngredient = new MealIngredient();
        Food food = foodEntry.getFood();
        mealIngredient.setIngredientFood(food);
        mealIngredient.setFoodPortion(foodEntry.getFoodPortion());
        mealIngredient.setIngredientFoodId(food.getLocalId());
        mealIngredient.setIngredientFoodUid(food.getUid());
        mealIngredient.setCreatorUserId(food.getOwnerUserId());
        mealIngredient.setQuantity(foodEntry.getQuantity());
        mealIngredient.setWeightIndex(foodEntry.getWeightIndex());
        mealIngredient.setIsFraction(foodEntry.isFraction());
        return mealIngredient;
    }

    public FoodEntry mapFrom(MealIngredient mealIngredient) throws IOException {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(mealIngredient.getIngredientFood());
        foodEntry.setMealName(MEAL_NAME);
        foodEntry.setQuantity(mealIngredient.getQuantity());
        foodEntry.setFoodPortion(mealIngredient.getFoodPortion());
        foodEntry.setWeightIndex(mealIngredient.getWeightIndex());
        foodEntry.setIsFraction(mealIngredient.isFraction());
        return foodEntry;
    }

    public FoodEntry tryMapFrom(MealIngredient mealIngredient) {
        try {
            return mapFrom(mealIngredient);
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }
}
