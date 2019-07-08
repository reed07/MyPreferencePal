package com.myfitnesspal.shared.model.v15;

import java.util.Date;

public abstract class FoodEntryBaseObject extends BaseObject {
    private Date date;
    private String description;
    private Date entryTime;
    private FoodObject food;
    private Date loggedAt;
    private long mealFoodMasterId;
    private String mealFoodUid;
    private String mealFoodVersion;
    private String mealName;
    private float quantity;

    public FoodObject getFood() {
        return this.food;
    }

    public void setFood(FoodObject foodObject) {
        this.food = foodObject;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public long getMealFoodMasterId() {
        return this.mealFoodMasterId;
    }

    public void setMealFoodMasterId(long j) {
        this.mealFoodMasterId = j;
    }

    public String getMealFoodVersion() {
        return this.mealFoodVersion;
    }

    public void setMealFoodVersion(String str) {
        this.mealFoodVersion = str;
    }

    public String getMealFoodUid() {
        return this.mealFoodUid;
    }

    public void setMealFoodUid(String str) {
        this.mealFoodUid = str;
    }

    public Date getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(Date date2) {
        this.entryTime = date2;
    }

    public Date getLoggedAt() {
        return this.loggedAt;
    }

    public void setLoggedAt(Date date2) {
        this.loggedAt = date2;
    }
}
