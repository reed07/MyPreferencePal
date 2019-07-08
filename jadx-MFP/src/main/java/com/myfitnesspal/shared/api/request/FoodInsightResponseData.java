package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;

public class FoodInsightResponseData implements Parcelable {
    public static final Creator<FoodInsightResponseData> CREATOR = new Creator<FoodInsightResponseData>() {
        public FoodInsightResponseData createFromParcel(Parcel parcel) {
            FoodInsightResponseData foodInsightResponseData = new FoodInsightResponseData();
            foodInsightResponseData.readFromParcel(parcel);
            return foodInsightResponseData;
        }

        public FoodInsightResponseData[] newArray(int i) {
            return new FoodInsightResponseData[i];
        }
    };
    public static final String NEGATIVE = "negative";
    public static final String NEUTRAL = "neutral";
    public static final String POSITIVE = "positive";
    @Expose
    private String description;
    @Expose
    private String type;

    public int describeContents() {
        return 0;
    }

    public FoodInsightResponseData() {
    }

    public FoodInsightResponseData(String str, String str2) {
        this.description = str;
        this.type = str2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.description);
        parcel.writeString(this.type);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        this.description = parcel.readString();
        this.type = parcel.readString();
    }

    public boolean isPositive() {
        return Strings.equals(getType(), POSITIVE);
    }
}
