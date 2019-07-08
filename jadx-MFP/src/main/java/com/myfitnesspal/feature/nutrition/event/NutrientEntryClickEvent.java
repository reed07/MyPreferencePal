package com.myfitnesspal.feature.nutrition.event;

import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.event.MfpEventBase;

public class NutrientEntryClickEvent extends MfpEventBase {
    private NutrientEntry nutrientEntry;

    public NutrientEntryClickEvent(NutrientEntry nutrientEntry2) {
        this.nutrientEntry = nutrientEntry2;
    }

    public NutrientEntry getNutrientEntry() {
        return this.nutrientEntry;
    }
}
