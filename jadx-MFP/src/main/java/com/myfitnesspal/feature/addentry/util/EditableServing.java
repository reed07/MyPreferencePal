package com.myfitnesspal.feature.addentry.util;

import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPortion;

public interface EditableServing {
    Food getFood();

    FoodPortion getFoodPortion();

    float getServings();

    void hideSoftInput();

    void initFoodData(float f);

    void setFoodPortion(FoodPortion foodPortion);

    void showDialogFragment(int i);
}
