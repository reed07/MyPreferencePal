package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.NumberUtils;

public class MacroValues implements Parcelable {
    public static final Creator<MacroValues> CREATOR = new Creator<MacroValues>() {
        public MacroValues createFromParcel(Parcel parcel) {
            return new MacroValues(parcel);
        }

        public MacroValues[] newArray(int i) {
            return new MacroValues[i];
        }
    };
    public static final int UNDEFINED_VALUE = Integer.MIN_VALUE;
    private float carbsValue;
    private float fatValue;
    private float proteinValue;

    public int describeContents() {
        return 0;
    }

    public static MacroValues fromDiaryDay(DiaryDay diaryDay) {
        return new MacroValues(diaryDay.amountOfNutrientConsumed(9), diaryDay.amountOfNutrientConsumed(1), diaryDay.amountOfNutrientConsumed(12));
    }

    public static MacroValues fromDailyGoal(NutrientGoalsUtil nutrientGoalsUtil, DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) {
        return new MacroValues(nutrientGoalsUtil.getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 9), nutrientGoalsUtil.getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 1), nutrientGoalsUtil.getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 12));
    }

    public static MacroValues fromNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        return new MacroValues(mfpNutritionalContents.getCarbohydrates().floatValue(), mfpNutritionalContents.getFat().floatValue(), mfpNutritionalContents.getProtein().floatValue());
    }

    public MacroValues(float f, float f2, float f3) {
        this.carbsValue = f;
        this.fatValue = f2;
        this.proteinValue = f3;
    }

    private MacroValues(Parcel parcel) {
        this.carbsValue = parcel.readFloat();
        this.fatValue = parcel.readFloat();
        this.proteinValue = parcel.readFloat();
    }

    public MacroValues() {
    }

    public float getCarbsValue() {
        return this.carbsValue;
    }

    public void setCarbsValue(float f) {
        this.carbsValue = f;
    }

    public float getFatValue() {
        return this.fatValue;
    }

    public void setFatValue(float f) {
        this.fatValue = f;
    }

    public float getProteinValue() {
        return this.proteinValue;
    }

    public void setProteinValue(float f) {
        this.proteinValue = f;
    }

    public float getCarbsInCalories() {
        return NutritionUtils.gramsCarbsToCalories(this.carbsValue);
    }

    public float getFatInCalories() {
        return NutritionUtils.gramsFatToCalories(this.fatValue);
    }

    public float getProteinInCalories() {
        return NutritionUtils.gramsProteinToCalories(this.proteinValue);
    }

    public float getTotalCaloriesFromMacros() {
        return NutritionUtils.getCaloriesForMacros(getNonNegativeValue(this.carbsValue), getNonNegativeValue(this.fatValue), getNonNegativeValue(this.proteinValue));
    }

    private float getNonNegativeValue(float f) {
        return Math.max(BitmapDescriptorFactory.HUE_RED, f);
    }

    public int getCarbsPercentage() {
        float f = this.carbsValue;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            return Integer.MIN_VALUE;
        }
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return 0;
        }
        return NumberUtils.getRoundedPercentage(getCarbsInCalories(), getTotalCaloriesFromMacros());
    }

    public int getFatPercentage() {
        float f = this.fatValue;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            return Integer.MIN_VALUE;
        }
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return 0;
        }
        return NumberUtils.getRoundedPercentage(getFatInCalories(), getTotalCaloriesFromMacros());
    }

    public int getProteinPercentage() {
        float f = this.proteinValue;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            return Integer.MIN_VALUE;
        }
        if (f == BitmapDescriptorFactory.HUE_RED) {
            return 0;
        }
        return (100 - getCarbsPercentage()) - getFatPercentage();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.carbsValue);
        parcel.writeFloat(this.fatValue);
        parcel.writeFloat(this.proteinValue);
    }
}
