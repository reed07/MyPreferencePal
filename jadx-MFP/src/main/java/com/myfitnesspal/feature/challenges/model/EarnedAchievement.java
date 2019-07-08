package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class EarnedAchievement {
    @Expose
    private String availableAchievementId;
    @Expose
    private String earnedAt;

    public String getAvailableAchievementId() {
        return this.availableAchievementId;
    }

    public void setAvailableAchievementId(String str) {
        this.availableAchievementId = str;
    }

    public String getEarnedAt() {
        return this.earnedAt;
    }

    public void setEarnedAt(String str) {
        this.earnedAt = str;
    }
}
