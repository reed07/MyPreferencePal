package com.myfitnesspal.feature.search.event;

import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;

public class SelectedFoodSearchTabChangedEvent {
    private final FoodSearchTab foodSearchTab;

    public SelectedFoodSearchTabChangedEvent(FoodSearchTab foodSearchTab2) {
        this.foodSearchTab = foodSearchTab2;
    }

    public FoodSearchTab getFoodSearchTab() {
        return this.foodSearchTab;
    }
}
