package com.myfitnesspal.feature.challenges.model;

import com.google.gson.annotations.Expose;

public class ChallengeMetric {
    @Expose
    private String type;
    @Expose
    private long value;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long j) {
        this.value = j;
    }
}
