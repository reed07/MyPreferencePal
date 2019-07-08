package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.ArrayList;
import java.util.List;

public class Challenge {
    @Expose
    private ChallengeAdmin admin;
    @Expose
    private List<ChallengeAvailableAchievement> availableAchievements;
    @Expose
    private ChallengeImageOutput bannerImage;
    @Expose
    private String challengeGoal;
    @Expose
    private String descriptionUrl;
    @Expose
    private List<ChallengeDynamicTab> dynamicTabs;
    @Expose
    private String endAt;
    @Expose
    private String headline;
    @Expose
    private String id;
    @Expose
    private boolean isPrivate;
    @Expose
    private boolean isPromoted;
    @Expose
    private List<ChallengeMetric> metrics;
    @Expose
    private ChallengeParticipant participant;
    @Expose
    private int participantCount;
    @Expose
    private ChallengeImageOutput previewImage;
    @Expose
    private List<ChallengePrize> prizes;
    @Expose
    private ChallengeImageOutput promotedImage;
    @Expose
    private String rulesUrl;
    @Expose
    private ChallengeSponsor sponsor;
    @Expose
    private String startAt;
    @Expose
    private String status;
    @Expose
    private String title;

    public static class API_RESPONSE_MAPPER extends ApiResponse<Challenge> {
    }

    public ChallengeImageOutput getBannerImage() {
        return this.bannerImage;
    }

    public void setBannerImage(ChallengeImageOutput challengeImageOutput) {
        this.bannerImage = challengeImageOutput;
    }

    public String getDescriptionUrl() {
        return this.descriptionUrl;
    }

    public void setDescriptionUrl(String str) {
        this.descriptionUrl = str;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setHeadline(String str) {
        this.headline = str;
    }

    public List<ChallengeMetric> getMetrics() {
        return this.metrics;
    }

    public void setMetrics(List<ChallengeMetric> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.metrics = list;
    }

    public List<ChallengePrize> getPrizes() {
        return this.prizes;
    }

    public void setPrizes(List<ChallengePrize> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.prizes = list;
    }

    public String getRulesUrl() {
        return this.rulesUrl;
    }

    public void setRulesUrl(String str) {
        this.rulesUrl = str;
    }

    public List<ChallengeDynamicTab> getDynamicTabs() {
        return this.dynamicTabs;
    }

    public void setDynamicTabs(List<ChallengeDynamicTab> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.dynamicTabs = list;
    }

    public List<ChallengeAvailableAchievement> getAvailableAchievements() {
        return this.availableAchievements;
    }

    public void setAvailableAchievements(List<ChallengeAvailableAchievement> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.availableAchievements = list;
    }

    public String getStartAt() {
        return this.startAt;
    }

    public void setStartAt(String str) {
        this.startAt = str;
    }

    public String getEndAt() {
        return this.endAt;
    }

    public void setEndAt(String str) {
        this.endAt = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getParticipantCount() {
        return this.participantCount;
    }

    public void setParticipantCount(int i) {
        this.participantCount = i;
    }

    public boolean isPromoted() {
        return this.isPromoted;
    }

    public void setIsPromoted(boolean z) {
        this.isPromoted = z;
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

    public ChallengeParticipant getParticipant() {
        return this.participant;
    }

    public ChallengeAdmin getAdmin() {
        return this.admin;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public String getChallengeGoal() {
        return this.challengeGoal;
    }
}
