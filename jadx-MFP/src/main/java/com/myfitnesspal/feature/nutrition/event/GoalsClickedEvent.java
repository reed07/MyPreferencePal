package com.myfitnesspal.feature.nutrition.event;

public class GoalsClickedEvent {
    final int nutrientIndex;

    public GoalsClickedEvent(int i) {
        this.nutrientIndex = i;
    }

    public int getNutrientIndex() {
        return this.nutrientIndex;
    }
}
