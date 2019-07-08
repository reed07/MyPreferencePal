package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeImageOutput {
    @Expose
    private String url;

    public ChallengeImageOutput(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
