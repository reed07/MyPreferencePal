package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpEnergySummary {
    @Expose
    private MfpMeasuredValue exercise;
    @Expose
    private MfpMeasuredValue food;
    @Expose
    private MfpMeasuredValue goal;
    @Expose
    private MfpMeasuredValue netConsumed;
    @Expose
    private MfpMeasuredValue remaining;

    public MfpMeasuredValue getGoal() {
        return this.goal;
    }

    public MfpMeasuredValue getRemaining() {
        return this.remaining;
    }

    public MfpMeasuredValue getFood() {
        return this.food;
    }

    public MfpMeasuredValue getExercise() {
        return this.exercise;
    }

    public MfpMeasuredValue getNetConsumed() {
        return this.netConsumed;
    }

    public void setGoal(MfpMeasuredValue mfpMeasuredValue) {
        this.goal = mfpMeasuredValue;
    }

    public void setRemaining(MfpMeasuredValue mfpMeasuredValue) {
        this.remaining = mfpMeasuredValue;
    }

    public void setFood(MfpMeasuredValue mfpMeasuredValue) {
        this.food = mfpMeasuredValue;
    }

    public void setExercise(MfpMeasuredValue mfpMeasuredValue) {
        this.exercise = mfpMeasuredValue;
    }

    public void setNetConsumed(MfpMeasuredValue mfpMeasuredValue) {
        this.netConsumed = mfpMeasuredValue;
    }
}
