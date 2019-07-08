package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.List;

public class ChallengeParticipant {
    @Expose
    private List<EarnedAchievement> earnedAchievements;
    @Expose
    private String id;
    @Expose
    private List<AvailableAchievementProgress> progressions;
    @Expose
    private String status;
    @Expose
    private ChallengeUser user;

    public static class API_RESPONSE_MAPPER extends ApiResponse<ChallengeParticipant> {
    }

    public List<EarnedAchievement> getEarnedAchievements() {
        return this.earnedAchievements;
    }

    public void setEarnedAchievements(List<EarnedAchievement> list) {
        this.earnedAchievements = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public List<AvailableAchievementProgress> getProgressions() {
        return this.progressions;
    }

    public void setProgressions(List<AvailableAchievementProgress> list) {
        this.progressions = list;
    }

    public ChallengeUser getUser() {
        return this.user;
    }

    public void setUser(ChallengeUser challengeUser) {
        this.user = challengeUser;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
