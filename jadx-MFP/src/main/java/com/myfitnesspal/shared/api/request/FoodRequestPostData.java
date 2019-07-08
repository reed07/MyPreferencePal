package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpNutritionalValues;

public class FoodRequestPostData {
    @Expose
    public String brand;
    @Expose
    public float grams;
    @Expose
    public String id;
    @Expose
    private MfpNutritionalValues nutritionalValues;
    @Expose
    public int type;

    public FoodRequestPostData() {
    }

    public FoodRequestPostData(String str, int i, MfpNutritionalValues mfpNutritionalValues, float f, String str2) {
        this.id = str;
        this.type = i;
        this.nutritionalValues = mfpNutritionalValues;
        this.grams = f;
        this.brand = str2;
        if (str2 == null) {
            this.brand = "";
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public MfpNutritionalValues getNutritionalValues() {
        return this.nutritionalValues;
    }

    public void setNutritionalValues(MfpNutritionalValues mfpNutritionalValues) {
        this.nutritionalValues = mfpNutritionalValues;
    }

    public float getGrams() {
        return this.grams;
    }

    public void setGrams(float f) {
        this.grams = f;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }
}
