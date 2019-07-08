package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;

public class MfpLogEntry implements Parcelable {
    public static final Creator<MfpLogEntry> CREATOR = new Creator<MfpLogEntry>() {
        public MfpLogEntry createFromParcel(Parcel parcel) {
            return new MfpLogEntry(parcel);
        }

        public MfpLogEntry[] newArray(int i) {
            return new MfpLogEntry[i];
        }
    };
    @Expose
    private String date;
    @Expose
    private MfpFood food;
    @Expose
    private String id;
    @Expose
    private String mealName;
    @Expose
    private MfpNutritionalContents nutritionalContents;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpLogEntry> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpLogEntry() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public MfpFood getFood() {
        return this.food;
    }

    public void setFood(MfpFood mfpFood) {
        this.food = mfpFood;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    protected MfpLogEntry(Parcel parcel) {
        this.id = parcel.readString();
        this.type = parcel.readString();
        this.date = parcel.readString();
        this.mealName = parcel.readString();
        this.food = (MfpFood) parcel.readValue(MfpFood.class.getClassLoader());
        this.nutritionalContents = (MfpNutritionalContents) parcel.readValue(MfpNutritionalContents.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.type);
        parcel.writeString(this.date);
        parcel.writeString(this.mealName);
        parcel.writeValue(this.food);
        parcel.writeValue(this.nutritionalContents);
    }
}
