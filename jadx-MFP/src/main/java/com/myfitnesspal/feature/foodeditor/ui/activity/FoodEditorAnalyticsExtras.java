package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FoodEditorAnalyticsExtras implements Parcelable {
    public static final Creator<FoodEditorAnalyticsExtras> CREATOR = new Creator<FoodEditorAnalyticsExtras>() {
        public FoodEditorAnalyticsExtras createFromParcel(Parcel parcel) {
            return new FoodEditorAnalyticsExtras(parcel);
        }

        public FoodEditorAnalyticsExtras[] newArray(int i) {
            return new FoodEditorAnalyticsExtras[i];
        }
    };
    private String listType;
    private int positionWithAd = -1;
    private int resultsListPosition;
    private String searchQuery;

    public int describeContents() {
        return 0;
    }

    public FoodEditorAnalyticsExtras() {
    }

    public FoodEditorAnalyticsExtras(Parcel parcel) {
        this.searchQuery = parcel.readString();
        this.resultsListPosition = parcel.readInt();
        this.listType = parcel.readString();
        this.positionWithAd = parcel.readInt();
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public FoodEditorAnalyticsExtras setSearchQuery(String str) {
        this.searchQuery = str;
        return this;
    }

    public int getResultsListPosition() {
        return this.resultsListPosition;
    }

    public FoodEditorAnalyticsExtras setResultsListPosition(int i) {
        this.resultsListPosition = i;
        return this;
    }

    public String getListType() {
        return this.listType;
    }

    public FoodEditorAnalyticsExtras setListType(String str) {
        this.listType = str;
        return this;
    }

    public int getPositionWithAd() {
        int i = this.positionWithAd;
        return i == -1 ? this.resultsListPosition : i;
    }

    public FoodEditorAnalyticsExtras setPositionWithAd(int i) {
        this.positionWithAd = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.searchQuery);
        parcel.writeInt(this.resultsListPosition);
        parcel.writeString(this.listType);
        parcel.writeInt(this.positionWithAd);
    }
}
