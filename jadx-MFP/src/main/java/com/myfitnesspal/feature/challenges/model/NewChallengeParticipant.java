package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class NewChallengeParticipant {
    @Expose
    private boolean shareEmail;

    public NewChallengeParticipant(boolean z) {
        this.shareEmail = z;
    }

    public boolean isShareEmail() {
        return this.shareEmail;
    }

    public void setShareEmail(boolean z) {
        this.shareEmail = z;
    }
}
