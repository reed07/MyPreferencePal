package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpNutritionalValues;

public class FoodAnalyzerUserRequestPostData {
    @Expose
    private String id;
    @Expose
    private MfpNutritionalValues nutrientGoals;
    @Expose
    private UnitsPreferenceRequestPostData unit_preferences;

    public FoodAnalyzerUserRequestPostData(String str, UnitsPreferenceRequestPostData unitsPreferenceRequestPostData, MfpNutritionalValues mfpNutritionalValues) {
        this.id = str;
        this.unit_preferences = unitsPreferenceRequestPostData;
        this.nutrientGoals = mfpNutritionalValues;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public UnitsPreferenceRequestPostData getUnit_preferences() {
        return this.unit_preferences;
    }

    public void setUnit_preferences(UnitsPreferenceRequestPostData unitsPreferenceRequestPostData) {
        this.unit_preferences = unitsPreferenceRequestPostData;
    }

    public MfpNutritionalValues getNutrientGoals() {
        return this.nutrientGoals;
    }

    public void setNutrientGoals(MfpNutritionalValues mfpNutritionalValues) {
        this.nutrientGoals = mfpNutritionalValues;
    }
}
