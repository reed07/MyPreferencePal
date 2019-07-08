package com.myfitnesspal.feature.meals.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.CollectionUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MfpMealIngredient {
    private static ApiJsonMapper MAPPER = new ApiJsonMapper();
    private static final String TYPE_KEY = "type";
    private static final String TYPE_VALUE_FOOD = "food";
    @Expose
    private Map<String, Object> food;
    private MfpFood mappedFood;
    @Expose
    private MfpServingSize servingSize;
    @Expose
    private double servings;

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }

    public double getServings() {
        return this.servings;
    }

    public <T extends MfpFood> T getFood() {
        Map<String, Object> map = this.food;
        if (map != null && map.get("type").equals("food")) {
            ApiJsonMapper apiJsonMapper = MAPPER;
            this.mappedFood = (MfpFood) apiJsonMapper.tryMapFrom(apiJsonMapper.reverseMap((Object) this.food), MfpFood.class);
        }
        if (this.mappedFood != null) {
            this.food = null;
        }
        return this.mappedFood;
    }

    public MealIngredient toMealIngredient(User user) {
        MealIngredient mealIngredient = new MealIngredient();
        MfpFood food2 = getFood();
        mealIngredient.setIngredientFood(food2.toFood(user));
        mealIngredient.setFoodPortion(this.servingSize.toFoodPortion());
        mealIngredient.setIngredientFoodId(food2.getMasterId());
        mealIngredient.setIngredientFoodUid(food2.getVersion());
        mealIngredient.setQuantity((float) this.servings);
        mealIngredient.setWeightIndex(findWeightIndex(this.servingSize, food2.getServingSizes()));
        return mealIngredient;
    }

    private static int findWeightIndex(MfpServingSize mfpServingSize, List<MfpServingSize> list) {
        if (mfpServingSize == null || CollectionUtils.isEmpty((Collection<?>) list)) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (mfpServingSize.equals(list.get(i))) {
                return i;
            }
        }
        return 0;
    }
}
