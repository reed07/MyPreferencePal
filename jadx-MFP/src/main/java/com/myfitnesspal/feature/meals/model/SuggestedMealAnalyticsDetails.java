package com.myfitnesspal.feature.meals.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SuggestedMealAnalyticsDetails implements Parcelable {
    public static final Creator<SuggestedMealAnalyticsDetails> CREATOR = new Creator<SuggestedMealAnalyticsDetails>() {
        public SuggestedMealAnalyticsDetails createFromParcel(Parcel parcel) {
            return new SuggestedMealAnalyticsDetails(parcel);
        }

        public SuggestedMealAnalyticsDetails[] newArray(int i) {
            return new SuggestedMealAnalyticsDetails[i];
        }
    };
    private final String mealId;
    private final String mealVersion;
    private final int originalRank;
    private final Double originalScore;
    private final String trackingId;

    public int describeContents() {
        return 0;
    }

    public SuggestedMealAnalyticsDetails(String str, String str2, String str3, int i, Double d) {
        this.trackingId = str;
        this.mealId = str2;
        this.mealVersion = str3;
        this.originalRank = i;
        this.originalScore = d;
    }

    private SuggestedMealAnalyticsDetails(Parcel parcel) {
        this.trackingId = parcel.readString();
        this.mealId = parcel.readString();
        this.mealVersion = parcel.readString();
        this.originalRank = parcel.readInt();
        this.originalScore = Double.valueOf(parcel.readDouble());
    }

    public String getTrackingId() {
        return this.trackingId;
    }

    public String getMealId() {
        return this.mealId;
    }

    public String getMealVersion() {
        return this.mealVersion;
    }

    public int getOriginalRank() {
        return this.originalRank;
    }

    public Double getOriginalScore() {
        return this.originalScore;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.trackingId);
        parcel.writeString(this.mealId);
        parcel.writeString(this.mealVersion);
        parcel.writeInt(this.originalRank);
        parcel.writeDouble(this.originalScore.doubleValue());
    }
}
