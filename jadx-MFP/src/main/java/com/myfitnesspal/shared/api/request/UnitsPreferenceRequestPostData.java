package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;

public class UnitsPreferenceRequestPostData {
    @Expose
    private String distance;
    @Expose
    private String energy;
    @Expose
    private String weight;

    public UnitsPreferenceRequestPostData(String str, String str2, String str3) {
        this.energy = str;
        this.weight = str2;
        this.distance = str3;
    }

    public String getEnergy() {
        return this.energy;
    }

    public void setEnergy(String str) {
        this.energy = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }
}
