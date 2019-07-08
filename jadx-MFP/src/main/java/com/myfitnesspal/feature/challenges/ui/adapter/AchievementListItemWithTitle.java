package com.myfitnesspal.feature.challenges.ui.adapter;

public class AchievementListItemWithTitle implements AchievementListItem {
    private String challengeTitle;
    private boolean isChallengeActive;
    private String timeStatus;

    public AchievementListItemWithTitle(String str, String str2, boolean z) {
        this.challengeTitle = str;
        this.timeStatus = str2;
        this.isChallengeActive = z;
    }

    public String getChallengeTitle() {
        return this.challengeTitle;
    }

    public String getTimeStatus() {
        return this.timeStatus;
    }

    public boolean isChallengeActive() {
        return this.isChallengeActive;
    }
}
