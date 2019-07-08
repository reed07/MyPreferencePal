package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

public class FoodAnalyzerRequestPostData {
    @Expose
    private float caloriesBurnedByExercise;
    @Expose
    private ArrayList<FoodEntryRequestPostData> foodEntries;
    @Expose
    private FoodEntryRequestPostData justAddedFoodEntry;
    @Expose
    private String type;

    public FoodAnalyzerRequestPostData() {
    }

    public FoodAnalyzerRequestPostData(ArrayList<FoodEntryRequestPostData> arrayList, FoodEntryRequestPostData foodEntryRequestPostData, float f, String str) {
        this.foodEntries = arrayList;
        this.justAddedFoodEntry = foodEntryRequestPostData;
        this.caloriesBurnedByExercise = f;
        this.type = str;
    }

    public ArrayList<FoodEntryRequestPostData> getFoodEntries() {
        return this.foodEntries;
    }

    public void setFoodEntries(ArrayList<FoodEntryRequestPostData> arrayList) {
        this.foodEntries = arrayList;
    }

    public FoodEntryRequestPostData getJustAddedFoodEntry() {
        return this.justAddedFoodEntry;
    }

    public void setJustAddedFoodEntry(FoodEntryRequestPostData foodEntryRequestPostData) {
        this.justAddedFoodEntry = foodEntryRequestPostData;
    }

    public float getCaloriesBurnedByExercise() {
        return this.caloriesBurnedByExercise;
    }

    public void setCaloriesBurnedByExercise(float f) {
        this.caloriesBurnedByExercise = f;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
