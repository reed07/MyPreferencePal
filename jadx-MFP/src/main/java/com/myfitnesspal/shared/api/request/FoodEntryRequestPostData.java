package com.myfitnesspal.shared.api.request;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class FoodEntryRequestPostData {
    @Expose
    public Date date;
    @Expose
    public String description;
    @Expose
    public FoodRequestPostData food;
    @Expose
    public FoodPortion foodPortion;
    @Expose
    public boolean isFraction;
    @Expose
    public String mealName;
    @Expose
    public float quantity;
    @Expose
    public int weightIndex;

    public FoodEntryRequestPostData(Date date2, FoodRequestPostData foodRequestPostData, String str, float f, FoodPortion foodPortion2, boolean z, int i, String str2) {
        this.date = date2;
        this.food = foodRequestPostData;
        this.mealName = str;
        this.quantity = f;
        this.isFraction = z;
        this.weightIndex = i;
        this.description = str2;
        this.foodPortion = foodPortion2;
        if (str2 == null) {
            this.description = "";
        }
        removeGramsAndAddNutritionalMultiplier();
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public FoodRequestPostData getFood() {
        return this.food;
    }

    public void setFood(FoodRequestPostData foodRequestPostData) {
        this.food = foodRequestPostData;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float f) {
        this.quantity = f;
    }

    public FoodPortion getFoodPortion() {
        return this.foodPortion;
    }

    public void setFoodPortion(FoodPortion foodPortion2) {
        this.foodPortion = foodPortion2;
    }

    public boolean isFraction() {
        return this.isFraction;
    }

    public void setFraction(boolean z) {
        this.isFraction = z;
    }

    public int getWeightIndex() {
        return this.weightIndex;
    }

    public void setWeightIndex(int i) {
        this.weightIndex = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    private void removeGramsAndAddNutritionalMultiplier() {
        this.foodPortion = (FoodPortion) ParcelableUtil.clone(this.foodPortion, FoodPortion.CREATOR);
        double doubleValue = this.foodPortion.getNutritionMultiplier().doubleValue();
        if (doubleValue == 0.0d || doubleValue == FoodPortion.DEFAULT_NUTRITION_MULTIPLIER.doubleValue()) {
            this.foodPortion.setNutritionMultiplier(Double.valueOf((double) (this.foodPortion.getGramWeight() / this.food.getGrams())));
        }
        this.foodPortion.setGramWeight(BitmapDescriptorFactory.HUE_RED);
        this.food.setGrams(BitmapDescriptorFactory.HUE_RED);
    }
}
