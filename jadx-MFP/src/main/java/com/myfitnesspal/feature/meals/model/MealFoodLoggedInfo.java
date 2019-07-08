package com.myfitnesspal.feature.meals.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MealFoodLoggedInfo implements Parcelable {
    public static final Creator<MealFoodLoggedInfo> CREATOR = new Creator<MealFoodLoggedInfo>() {
        public MealFoodLoggedInfo createFromParcel(Parcel parcel) {
            return new MealFoodLoggedInfo(parcel);
        }

        public MealFoodLoggedInfo[] newArray(int i) {
            return new MealFoodLoggedInfo[i];
        }
    };
    private String listType;
    private String mealFoodUid;
    private int resultsListPosition;
    private String searchQuery;
    private String source;

    public int describeContents() {
        return 0;
    }

    public MealFoodLoggedInfo() {
    }

    public MealFoodLoggedInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public MealFoodLoggedInfo(String str, int i, String str2, String str3, String str4) {
        this.searchQuery = str;
        this.resultsListPosition = i;
        this.mealFoodUid = str2;
        this.source = str3;
        this.listType = str4;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public int getResultsListPosition() {
        return this.resultsListPosition;
    }

    public String getMealFoodUid() {
        return this.mealFoodUid;
    }

    public String getSource() {
        return this.source;
    }

    public String getListType() {
        return this.listType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.searchQuery);
        parcel.writeInt(this.resultsListPosition);
        parcel.writeString(this.mealFoodUid);
        parcel.writeString(this.source);
        parcel.writeString(this.listType);
    }

    private void readFromParcel(Parcel parcel) {
        this.searchQuery = parcel.readString();
        this.resultsListPosition = parcel.readInt();
        this.mealFoodUid = parcel.readString();
        this.source = parcel.readString();
        this.listType = parcel.readString();
    }
}
