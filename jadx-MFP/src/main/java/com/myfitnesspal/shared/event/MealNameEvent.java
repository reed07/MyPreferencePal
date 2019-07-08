package com.myfitnesspal.shared.event;

public class MealNameEvent extends MfpEventBase {
    String mealName;

    public MealNameEvent(String str) {
        this.mealName = str;
    }

    public String getMealName() {
        return this.mealName;
    }
}
