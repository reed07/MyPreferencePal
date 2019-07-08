package com.myfitnesspal.shared.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.uacf.core.util.NumberUtils;
import java.util.Date;

public class QuickAddFood implements Parcelable {
    public static final Creator<QuickAddFood> CREATOR = new Creator<QuickAddFood>() {
        public QuickAddFood createFromParcel(Parcel parcel) {
            return new QuickAddFood(parcel);
        }

        public QuickAddFood[] newArray(int i) {
            return new QuickAddFood[i];
        }
    };
    private float calories;
    private float carbohydrate;
    private Date entryTime;
    private float fat;
    private float protein;

    public int describeContents() {
        return 0;
    }

    public QuickAddFood(float f) {
        this(f, NumberUtils.getFloatValue(MfpNutritionalContents.DEFAULT_VALUE), NumberUtils.getFloatValue(MfpNutritionalContents.DEFAULT_VALUE), NumberUtils.getFloatValue(MfpNutritionalContents.DEFAULT_VALUE), null);
    }

    public QuickAddFood(float f, float f2, float f3, float f4, Date date) {
        this.calories = f;
        this.carbohydrate = f2;
        this.fat = f3;
        this.protein = f4;
        this.entryTime = date;
    }

    private QuickAddFood(Parcel parcel) {
        this.calories = parcel.readFloat();
        this.carbohydrate = parcel.readFloat();
        this.fat = parcel.readFloat();
        this.protein = parcel.readFloat();
        this.entryTime = (Date) parcel.readSerializable();
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float f) {
        this.calories = f;
    }

    public float getCarbohydrate() {
        return this.carbohydrate;
    }

    public void setCarbohydrate(float f) {
        this.carbohydrate = f;
    }

    public float getFat() {
        return this.fat;
    }

    public void setFat(float f) {
        this.fat = f;
    }

    public float getProtein() {
        return this.protein;
    }

    public void setProtein(float f) {
        this.protein = f;
    }

    public Date getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(Date date) {
        this.entryTime = date;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.calories);
        parcel.writeFloat(this.carbohydrate);
        parcel.writeFloat(this.fat);
        parcel.writeFloat(this.protein);
        parcel.writeSerializable(this.entryTime);
    }
}
