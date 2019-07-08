package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import java.util.ArrayList;

public class MealGoal implements Parcelable, Comparable<MealGoal> {
    public static final Creator<MealGoal> CREATOR = new Creator<MealGoal>() {
        public MealGoal createFromParcel(Parcel parcel) {
            return new MealGoal(parcel);
        }

        public MealGoal[] newArray(int i) {
            return new MealGoal[i];
        }
    };
    @Expose
    private float carbohydrates;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private float fat;
    @Expose
    private int mealIndex;
    @Expose
    private float protein;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MealGoal> {
    }

    public static class LIST_MAPPER extends ArrayList<MealGoal> {
    }

    public int describeContents() {
        return 0;
    }

    public MealGoal() {
    }

    public MealGoal(int i, MfpMeasuredValue mfpMeasuredValue) {
        this.mealIndex = i;
        this.energy = mfpMeasuredValue;
    }

    public MealGoal(Parcel parcel) {
        readFromParcel(parcel);
    }

    public int getMealIndex() {
        return this.mealIndex;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public float getCarbohydrates() {
        return this.carbohydrates;
    }

    public float getProtein() {
        return this.protein;
    }

    public float getFat() {
        return this.fat;
    }

    public void setMealIndex(int i) {
        this.mealIndex = i;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public void setCarbohydrates(float f) {
        this.carbohydrates = f;
    }

    public void setProtein(float f) {
        this.protein = f;
    }

    public void setFat(float f) {
        this.fat = f;
    }

    private void readFromParcel(Parcel parcel) {
        this.mealIndex = parcel.readInt();
        if (parcel.readByte() == 1) {
            this.energy = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        this.carbohydrates = parcel.readFloat();
        this.protein = parcel.readFloat();
        this.fat = parcel.readFloat();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mealIndex);
        if (this.energy != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.energy, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeFloat(this.carbohydrates);
        parcel.writeFloat(this.protein);
        parcel.writeFloat(this.fat);
    }

    public int compareTo(MealGoal mealGoal) {
        return new Integer(this.mealIndex).compareTo(new Integer(mealGoal.mealIndex));
    }
}
