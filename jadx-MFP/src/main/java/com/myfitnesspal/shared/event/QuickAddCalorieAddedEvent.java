package com.myfitnesspal.shared.event;

public class QuickAddCalorieAddedEvent extends MfpEventBase {
    private final float calories;
    private final String mealName;

    public QuickAddCalorieAddedEvent(float f, String str) {
        this.calories = f;
        this.mealName = str;
    }

    public float getCalories() {
        return this.calories;
    }

    public String getMealName() {
        return this.mealName;
    }
}
