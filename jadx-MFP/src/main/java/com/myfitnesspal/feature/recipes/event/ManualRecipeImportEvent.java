package com.myfitnesspal.feature.recipes.event;

import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import java.util.ArrayList;
import java.util.List;

public class ManualRecipeImportEvent {
    private List<MfpIngredientItem> matchedIngredients = new ArrayList();
    private String recipeName;
    private double servingSize;
    private List<String> unmatchedIngredients;

    public ManualRecipeImportEvent(List<String> list, List<MfpIngredientItem> list2, String str, double d) {
        this.unmatchedIngredients = list;
        this.recipeName = str;
        this.servingSize = d;
        if (list2 != null) {
            this.matchedIngredients.addAll(list2);
        }
    }

    public List<String> getUnmatchedIngredients() {
        return this.unmatchedIngredients;
    }

    public void setUnmatchedIngredients(List<String> list) {
        this.unmatchedIngredients = list;
    }

    public List<MfpIngredientItem> getMatchedIngredients() {
        return this.matchedIngredients;
    }

    public void setMatchedIngredients(List<MfpIngredientItem> list) {
        this.matchedIngredients = list;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public void setRecipeName(String str) {
        this.recipeName = str;
    }

    public double getServingSize() {
        return this.servingSize;
    }

    public void setServingSize(double d) {
        this.servingSize = d;
    }
}
