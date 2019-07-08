package com.myfitnesspal.feature.nutrition.model;

public class CustomCalorieGoalLegend {
    String goalDays;
    String goalValue;

    public CustomCalorieGoalLegend(String str, String str2) {
        this.goalDays = str;
        this.goalValue = str2;
    }

    public String getGoalDays() {
        return this.goalDays;
    }

    public String getGoalValue() {
        return this.goalValue;
    }
}
