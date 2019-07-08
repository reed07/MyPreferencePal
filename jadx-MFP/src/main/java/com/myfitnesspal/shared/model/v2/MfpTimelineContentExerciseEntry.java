package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentExerciseEntry implements MfpTimelineContentData {
    @Expose
    private String description;
    @Expose
    private Integer duration;
    @Expose
    private MfpMeasuredValue energy;

    public String getDescription() {
        return this.description;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
