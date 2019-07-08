package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class AvailableAchievementProgress {
    @Expose
    private String availableAchievementCriteriaId;
    @Expose
    private int value;

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public String getAvailableAchievementCriteriaId() {
        return this.availableAchievementCriteriaId;
    }

    public void setAvailableAchievementCriteriaId(String str) {
        this.availableAchievementCriteriaId = str;
    }
}
