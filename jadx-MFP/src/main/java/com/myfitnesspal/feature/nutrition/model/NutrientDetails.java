package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.ParcelableUtil;

public class NutrientDetails implements Parcelable {
    public static final Creator<NutrientDetails> CREATOR = new Creator<NutrientDetails>() {
        public NutrientDetails createFromParcel(Parcel parcel) {
            return new NutrientDetails(parcel);
        }

        public NutrientDetails[] newArray(int i) {
            return new NutrientDetails[i];
        }
    };
    @Expose
    private boolean isPercentage;
    @Expose
    private boolean isSubordinateNutrientIndex;
    @Expose
    private float nutrientsConsumed;
    @Expose
    private float nutritionalGoals;
    @Expose
    private String units;

    public int describeContents() {
        return 0;
    }

    public NutrientDetails() {
    }

    public NutrientDetails(float f, float f2, boolean z, boolean z2, String str) {
        this.nutritionalGoals = f;
        this.nutrientsConsumed = f2;
        this.isSubordinateNutrientIndex = z;
        this.isPercentage = z2;
        this.units = str;
    }

    private NutrientDetails(Parcel parcel) {
        this.nutritionalGoals = parcel.readFloat();
        this.nutrientsConsumed = parcel.readFloat();
        this.isSubordinateNutrientIndex = ParcelableUtil.readBoolean(parcel);
        this.isPercentage = ParcelableUtil.readBoolean(parcel);
        this.units = parcel.readString();
    }

    public float getNutritionalGoals() {
        return this.nutritionalGoals;
    }

    public float getNutrientsConsumed() {
        return this.nutrientsConsumed;
    }

    public boolean isSubordinateNutrientIndex() {
        return this.isSubordinateNutrientIndex;
    }

    public boolean isPercentage() {
        return this.isPercentage;
    }

    public String getUnits() {
        return this.units;
    }

    public void setNutritionalGoals(float f) {
        this.nutritionalGoals = f;
    }

    public void addNutritionalGoals(float f) {
        this.nutritionalGoals += f;
    }

    public void setNutrientsConsumed(float f) {
        this.nutrientsConsumed = f;
    }

    public void addNutrientsConsumed(float f) {
        this.nutrientsConsumed += f;
    }

    public void setIsSubordinateNutrientIndex(boolean z) {
        this.isSubordinateNutrientIndex = z;
    }

    public void setIsPercentage(boolean z) {
        this.isPercentage = z;
    }

    public void setUnits(String str) {
        this.units = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.nutritionalGoals);
        parcel.writeFloat(this.nutrientsConsumed);
        ParcelableUtil.writeBoolean(parcel, this.isSubordinateNutrientIndex);
        ParcelableUtil.writeBoolean(parcel, this.isPercentage);
        parcel.writeString(this.units);
    }
}
