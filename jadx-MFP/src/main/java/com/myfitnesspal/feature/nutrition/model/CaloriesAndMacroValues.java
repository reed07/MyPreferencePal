package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class CaloriesAndMacroValues implements Parcelable {
    public static final Creator<CaloriesAndMacroValues> CREATOR = new Creator<CaloriesAndMacroValues>() {
        public CaloriesAndMacroValues createFromParcel(Parcel parcel) {
            return new CaloriesAndMacroValues(parcel);
        }

        public CaloriesAndMacroValues[] newArray(int i) {
            return new CaloriesAndMacroValues[i];
        }
    };
    private float caloriesFromDiary;
    private MacroValues macroValues;

    private static float calculateMacroCalories(float f, float f2, float f3) {
        return (f == BitmapDescriptorFactory.HUE_RED || f2 == BitmapDescriptorFactory.HUE_RED) ? BitmapDescriptorFactory.HUE_RED : f3 * (f / f2);
    }

    public int describeContents() {
        return 0;
    }

    public CaloriesAndMacroValues(MacroValues macroValues2, float f) {
        this.macroValues = macroValues2;
        this.caloriesFromDiary = f;
    }

    private CaloriesAndMacroValues(Parcel parcel) {
        this.macroValues = (MacroValues) parcel.readParcelable(MacroValues.class.getClassLoader());
        this.caloriesFromDiary = parcel.readFloat();
    }

    public CaloriesAndMacroValues() {
    }

    public MacroValues getMacroValues() {
        return this.macroValues;
    }

    public float getCaloriesFromDiary() {
        return this.caloriesFromDiary;
    }

    public float getCarbsCaloriesFromDiary() {
        return calculateMacroCalories(getMacroValues().getCarbsInCalories(), getMacroValues().getTotalCaloriesFromMacros(), getCaloriesFromDiary());
    }

    public float getFatCaloriesFromDiary() {
        return calculateMacroCalories(getMacroValues().getFatInCalories(), getMacroValues().getTotalCaloriesFromMacros(), getCaloriesFromDiary());
    }

    public float getProteinCaloriesFromDiary() {
        return calculateMacroCalories(getMacroValues().getProteinInCalories(), getMacroValues().getTotalCaloriesFromMacros(), getCaloriesFromDiary());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.macroValues, i);
        parcel.writeFloat(this.caloriesFromDiary);
    }
}
