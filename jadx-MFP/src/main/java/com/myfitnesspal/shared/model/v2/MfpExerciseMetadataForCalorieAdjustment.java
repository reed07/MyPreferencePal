package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.date.MfpIso8601Date;

public class MfpExerciseMetadataForCalorieAdjustment implements MfpExerciseMetadata {
    @Expose
    private boolean allowNegativeCalorieAdjustment;
    @Expose
    private boolean calorieAdjustmentReduced;
    @Expose
    private double calorieBurnedAmount;
    @Expose
    private double calorieBurnedProjectionAmount;
    @Expose
    private double mfpCalorieProjection;
    @Expose
    private double partnerExerciseCalories;
    @Expose
    private String partnerName;
    @Expose
    private MfpIso8601Date projectionTimestamp;

    public double getCalorieBurnedProjectionAmount() {
        return this.calorieBurnedProjectionAmount;
    }

    public void setCalorieBurnedProjectionAmount(double d) {
        this.calorieBurnedProjectionAmount = d;
    }

    public double getCalorieBurnedAmount() {
        return this.calorieBurnedAmount;
    }

    public void setCalorieBurnedAmount(double d) {
        this.calorieBurnedAmount = d;
    }

    public MfpIso8601Date getProjectionTimestamp() {
        return this.projectionTimestamp;
    }

    public void setProjectionTimestamp(MfpIso8601Date mfpIso8601Date) {
        this.projectionTimestamp = mfpIso8601Date;
    }

    public double getMfpCalorieProjection() {
        return this.mfpCalorieProjection;
    }

    public void setMfpCalorieProjection(double d) {
        this.mfpCalorieProjection = d;
    }

    public double getPartnerExerciseCalories() {
        return this.partnerExerciseCalories;
    }

    public void setPartnerExerciseCalories(double d) {
        this.partnerExerciseCalories = d;
    }

    public String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(String str) {
        this.partnerName = str;
    }

    public boolean getCalorieAdjustmentReduced() {
        return this.calorieAdjustmentReduced;
    }

    public void setCalorieAdjustmentReduced(boolean z) {
        this.calorieAdjustmentReduced = z;
    }

    public boolean getAllowNegativeCalorieAdjustment() {
        return this.allowNegativeCalorieAdjustment;
    }

    public void setAllowNegativeCalorieAdjustment(boolean z) {
        this.allowNegativeCalorieAdjustment = z;
    }
}
