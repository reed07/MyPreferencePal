package com.myfitnesspal.feature.nutrition.event;

import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.event.MfpEventBase;

public class NavigateToFoodListEvent extends MfpEventBase {
    private int macroIndex;
    private NutrientEntry nutrientEntry;

    public NavigateToFoodListEvent(NutrientEntry nutrientEntry2, int i) {
        this.nutrientEntry = nutrientEntry2;
        this.macroIndex = i;
    }

    public NutrientEntry getNutrientEntry() {
        return this.nutrientEntry;
    }

    public int getMacroIndex() {
        return this.macroIndex;
    }
}
