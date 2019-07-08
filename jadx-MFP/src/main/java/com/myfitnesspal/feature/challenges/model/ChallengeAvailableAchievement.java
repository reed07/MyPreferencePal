package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class ChallengeAvailableAchievement {
    @Expose
    private List<AvailableAchievementCriteria> criteria;
    @Expose
    private String description;
    @Expose
    private String detailsUrl;
    @Expose
    private ChallengeImageOutput earnedIconImage;
    @Expose
    private String emailBody;
    @Expose
    private String emailBodyShare;
    @Expose
    private String emailHeader;
    @Expose
    private String emailSubject;
    @Expose
    private String emailSubjectShare;
    @Expose
    private String endAt;
    @Expose
    private ChallengeImageOutput iconImage;
    @Expose
    private String id;
    @Expose
    private boolean isMain;
    @Expose
    private String startAt;
    @Expose
    private String title;
    @Expose
    private String twitterShare;
    @Expose
    private ChallengeImageOutput unearnedIconImage;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public List<AvailableAchievementCriteria> getCriteria() {
        return this.criteria;
    }

    public void setCriteria(List<AvailableAchievementCriteria> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.criteria = list;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public ChallengeImageOutput getEarnedIconImage() {
        return this.earnedIconImage;
    }

    public void setEarnedIconImage(ChallengeImageOutput challengeImageOutput) {
        this.earnedIconImage = challengeImageOutput;
    }

    public ChallengeImageOutput getUnearnedIconImage() {
        return this.unearnedIconImage;
    }

    public void setUnearnedIconImage(ChallengeImageOutput challengeImageOutput) {
        this.unearnedIconImage = challengeImageOutput;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public void setIsMain(boolean z) {
        this.isMain = z;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public ChallengeImageOutput getIconImage() {
        return this.iconImage;
    }

    public String getEmailBodyShare() {
        return this.emailBodyShare;
    }

    public String getEmailBody() {
        return this.emailBody;
    }

    public String getEmailHeader() {
        return this.emailHeader;
    }

    public String getEmailSubjectShare() {
        return this.emailSubjectShare;
    }

    public String getEmailSubject() {
        return this.emailSubject;
    }

    public String getTwitterShare() {
        return this.twitterShare;
    }

    public String getDetailsUrl() {
        return this.detailsUrl;
    }
}
