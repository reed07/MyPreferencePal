package com.myfitnesspal.feature.nutrition.event;

public class LegendClickedEvent {
    int nutrientIndex;

    public LegendClickedEvent(int i) {
        this.nutrientIndex = i;
    }

    public int getNutrientIndex() {
        return this.nutrientIndex;
    }
}
