package com.myfitnesspal.feature.recipes.model;

import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;

public class IngredientsMatchingOverviewItem implements IngredientMatchingAdapterItem {
    private final boolean areAllIngredientsMatched;
    private final LocalizedEnergy localizedEnergyPerServing;
    private final double servings;

    public IngredientsMatchingOverviewItem(double d, double d2, boolean z) {
        this.servings = d;
        this.localizedEnergyPerServing = LocalizedEnergy.fromCalories(d2);
        this.areAllIngredientsMatched = z;
    }

    public double getServings() {
        return this.servings;
    }

    public LocalizedEnergy getLocalizedEnergyPerServing() {
        return this.localizedEnergyPerServing;
    }

    public boolean areAllIngredientsMatched() {
        return this.areAllIngredientsMatched;
    }
}
