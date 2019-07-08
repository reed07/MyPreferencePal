package com.myfitnesspal.feature.progress.constants;

import com.myfitnesspal.shared.constants.Constants.Challenges;

public enum ArtifactType {
    WeightChange("weight_delta"),
    Steps("steps_taken"),
    Exercises("exercises_logged"),
    Meals("meals_logged"),
    LoginStreak("login_streak"),
    Blank("blank"),
    Summary(Challenges.CHALLENGE_TAB_SUMMARY);
    
    private String analyticsValue;

    private ArtifactType(String str) {
        this.analyticsValue = str;
    }

    public String getAnalyticsValue() {
        return this.analyticsValue;
    }
}
