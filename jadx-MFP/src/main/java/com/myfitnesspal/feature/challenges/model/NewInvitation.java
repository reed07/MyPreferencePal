package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class NewInvitation {
    @Expose
    private String userId;

    public NewInvitation(String str) {
        this.userId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
