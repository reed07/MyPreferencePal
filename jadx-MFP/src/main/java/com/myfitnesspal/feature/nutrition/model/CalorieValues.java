package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;

public class CalorieValues implements Parcelable {
    public static final Creator<CalorieValues> CREATOR = new Creator<CalorieValues>() {
        public CalorieValues createFromParcel(Parcel parcel) {
            return new CalorieValues(parcel);
        }

        public CalorieValues[] newArray(int i) {
            return new CalorieValues[i];
        }
    };
    @Expose
    private float goal;
    @Expose
    private float[] mealPercentages;
    @Expose

    /* renamed from: net reason: collision with root package name */
    private float f33net;
    private float netAverage;
    @Expose
    private float total;
    private float totalAverage;

    public int describeContents() {
        return 0;
    }

    public CalorieValues(float f, float f2, float f3, float[] fArr) {
        this(f, f2, f3, fArr, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
    }

    public CalorieValues(float f, float f2, float f3, float[] fArr, float f4, float f5) {
        this.total = f;
        this.f33net = f2;
        this.goal = f3;
        this.mealPercentages = fArr;
        this.totalAverage = f4;
        this.netAverage = f5;
    }

    private CalorieValues(Parcel parcel) {
        this.total = parcel.readFloat();
        this.f33net = parcel.readFloat();
        this.goal = parcel.readFloat();
        this.mealPercentages = parcel.createFloatArray();
        this.totalAverage = parcel.readFloat();
        this.netAverage = parcel.readFloat();
    }

    public float getTotal() {
        return this.total;
    }

    public float getNet() {
        return this.f33net;
    }

    public float getGoal() {
        return this.goal;
    }

    public float[] getMealPercentages() {
        return this.mealPercentages;
    }

    public float getTotalAverage() {
        return this.totalAverage;
    }

    public float getNetAverage() {
        return this.netAverage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.total);
        parcel.writeFloat(this.f33net);
        parcel.writeFloat(this.goal);
        parcel.writeFloatArray(this.mealPercentages);
        parcel.writeFloat(this.totalAverage);
        parcel.writeFloat(this.netAverage);
    }
}
