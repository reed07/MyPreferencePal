package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeUser {
    @Expose
    private String id;
    @Expose
    private ChallengeImageOutput mainImage;
    @Expose
    private String username;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public ChallengeImageOutput getMainImage() {
        return this.mainImage;
    }

    public void setMainImage(ChallengeImageOutput challengeImageOutput) {
        this.mainImage = challengeImageOutput;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
