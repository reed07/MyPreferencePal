package com.myfitnesspal.feature.recipes.api;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import java.util.List;

public class CreateRecipeRequest {
    @Expose
    private List<MfpRecipe> items;

    public List<MfpRecipe> getItems() {
        return this.items;
    }

    public void setItems(List<MfpRecipe> list) {
        this.items = list;
    }
}
