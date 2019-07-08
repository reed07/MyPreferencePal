package com.myfitnesspal.feature.recipes.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import java.util.List;

public class MfpRecipeListContainer {
    @Expose
    private String link;
    @Expose
    private List<MfpRecipe> recipes;

    public MfpRecipeListContainer(List<MfpRecipe> list, String str) {
        this.recipes = list;
        this.link = str;
    }

    public List<MfpRecipe> getRecipes() {
        return this.recipes;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
