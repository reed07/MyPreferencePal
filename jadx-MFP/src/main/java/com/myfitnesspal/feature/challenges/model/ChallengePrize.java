package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengePrize {
    @Expose
    private String description;
    @Expose
    private ChallengeImageOutput iconImage;
    @Expose
    private boolean isMain;
    @Expose
    private String title;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public ChallengeImageOutput getIconImage() {
        return this.iconImage;
    }

    public void setIconImage(ChallengeImageOutput challengeImageOutput) {
        this.iconImage = challengeImageOutput;
    }

    public boolean isMain() {
        return this.isMain;
    }
}
