package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeAdmin {
    @Expose
    private boolean emailOptInOption;
    @Expose
    private String emailOptInText;
    @Expose
    private ChallengeSocial social;

    public boolean isEmailOptInOption() {
        return this.emailOptInOption;
    }

    public String getEmailOptInText() {
        return this.emailOptInText;
    }

    public ChallengeSocial getSocial() {
        return this.social;
    }
}
