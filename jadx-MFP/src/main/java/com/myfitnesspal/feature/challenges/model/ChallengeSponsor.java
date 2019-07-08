package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeSponsor {
    @Expose
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
