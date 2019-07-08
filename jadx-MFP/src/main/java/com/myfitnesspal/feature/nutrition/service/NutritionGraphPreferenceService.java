package com.myfitnesspal.feature.nutrition.service;

public interface NutritionGraphPreferenceService {
    int getGraphSubType();

    String getGraphType();

    boolean isWeekly();

    void setGraphSubType(int i);

    void setGraphType(String str);

    void setIsWeekly(boolean z);
}
