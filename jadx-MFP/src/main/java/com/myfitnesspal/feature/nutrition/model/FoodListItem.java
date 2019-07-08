package com.myfitnesspal.feature.nutrition.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class FoodListItem implements Parcelable, Comparable<FoodListItem> {
    public static final Creator<FoodListItem> CREATOR = new Creator<FoodListItem>() {
        public FoodListItem createFromParcel(Parcel parcel) {
            return new FoodListItem(parcel);
        }

        public FoodListItem[] newArray(int i) {
            return new FoodListItem[i];
        }
    };
    private float amountConsumed;
    private String foodName;
    private String id;
    private int timesEaten;

    public int describeContents() {
        return 0;
    }

    public FoodListItem(String str, String str2, float f) {
        this.id = str;
        this.foodName = str2;
        this.amountConsumed = f;
    }

    private FoodListItem(Parcel parcel) {
        this.id = parcel.readString();
        this.foodName = parcel.readString();
        this.amountConsumed = parcel.readFloat();
        this.timesEaten = parcel.readInt();
    }

    public String getFoodName() {
        return this.foodName;
    }

    public float getAmountConsumed() {
        return this.amountConsumed;
    }

    public int getTimesEaten() {
        return this.timesEaten;
    }

    public void setTimesEaten(int i) {
        this.timesEaten = i;
    }

    public void setAmountConsumed(float f) {
        this.amountConsumed = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FoodListItem)) {
            return false;
        }
        FoodListItem foodListItem = (FoodListItem) obj;
        if (Float.compare(foodListItem.amountConsumed, this.amountConsumed) != 0) {
            return false;
        }
        String str = this.foodName;
        if (str == null ? foodListItem.foodName != null : !str.equals(foodListItem.foodName)) {
            return false;
        }
        String str2 = this.id;
        return str2 == null ? foodListItem.id == null : str2.equals(foodListItem.id);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.foodName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        float f = this.amountConsumed;
        if (f != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(f);
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.foodName);
        parcel.writeFloat(this.amountConsumed);
        parcel.writeInt(this.timesEaten);
    }

    public int compareTo(FoodListItem foodListItem) {
        float f = this.amountConsumed;
        float f2 = foodListItem.amountConsumed;
        if (f > f2) {
            return -1;
        }
        if (f < f2) {
            return 1;
        }
        int i = this.timesEaten;
        int i2 = foodListItem.timesEaten;
        if (i < i2) {
            return 1;
        }
        if (i > i2) {
            return -1;
        }
        return this.foodName.compareTo(foodListItem.foodName);
    }
}
