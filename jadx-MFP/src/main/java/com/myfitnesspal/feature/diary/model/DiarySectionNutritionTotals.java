package com.myfitnesspal.feature.diary.model;

import com.myfitnesspal.feature.nutrition.model.MacroValues;

public class DiarySectionNutritionTotals {
    private final MacroValues macroValues;
    private final float sodium;
    private final float sugars;

    public DiarySectionNutritionTotals(float f, float f2, float f3, float f4, float f5) {
        this.macroValues = new MacroValues(f, f2, f3);
        this.sodium = f4;
        this.sugars = f5;
    }

    public float getCarbs() {
        return this.macroValues.getCarbsValue();
    }

    public float getFat() {
        return this.macroValues.getFatValue();
    }

    public float getProtein() {
        return this.macroValues.getProteinValue();
    }

    public float getSodium() {
        return this.sodium;
    }

    public float getSugars() {
        return this.sugars;
    }

    public MacroValues getMacroValues() {
        return this.macroValues;
    }
}
