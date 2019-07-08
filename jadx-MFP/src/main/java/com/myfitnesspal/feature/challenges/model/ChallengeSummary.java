package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.List;

public class ChallengeSummary {
    @Expose
    private List<ChallengeAvailableAchievement> availableAchievements;
    @Expose
    private String challengeGoal;
    @Expose
    private String endAt;
    @Expose
    private String id;
    @Expose
    private boolean isPromoted;
    @Expose
    private ChallengeParticipant participant;
    @Expose
    private int participantCount;
    @Expose
    private ChallengeImageOutput previewImage;
    @Expose
    private ChallengeImageOutput promotedImage;
    @Expose
    private ChallengeSponsor sponsor;
    @Expose
    private String startAt;
    @Expose
    private String status;
    @Expose
    private String title;

    public static class API_RESPONSE_MAPPER extends ApiResponse<ChallengeSummary> {
    }

    public List<ChallengeAvailableAchievement> getAvailableAchievements() {
        return this.availableAchievements;
    }

    public String getStartAt() {
        return this.startAt;
    }

    public String getEndAt() {
        return this.endAt;
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTitle() {
        return this.title;
    }

    public int getParticipantCount() {
        return this.participantCount;
    }

    public ChallengeImageOutput getPreviewImage() {
        return this.previewImage;
    }

    public ChallengeImageOutput getPromotedImage() {
        return this.promotedImage;
    }

    public ChallengeSponsor getSponsor() {
        return this.sponsor;
    }

    public void setSponsor(ChallengeSponsor challengeSponsor) {
        this.sponsor = challengeSponsor;
    }

    public boolean isPromoted() {
        return this.isPromoted;
    }

    public ChallengeParticipant getParticipant() {
        return this.participant;
    }

    public String getChallengeGoal() {
        return this.challengeGoal;
    }
}
