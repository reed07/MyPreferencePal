package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;

public class FoodQuestionResponseData implements Parcelable {
    public static final Creator<FoodQuestionResponseData> CREATOR = new Creator<FoodQuestionResponseData>() {
        public FoodQuestionResponseData createFromParcel(Parcel parcel) {
            FoodQuestionResponseData foodQuestionResponseData = new FoodQuestionResponseData();
            foodQuestionResponseData.readFromParcel(parcel);
            return foodQuestionResponseData;
        }

        public FoodQuestionResponseData[] newArray(int i) {
            return new FoodQuestionResponseData[i];
        }
    };
    @Expose
    private int id;
    @Expose
    private String question;

    public int describeContents() {
        return 0;
    }

    public FoodQuestionResponseData() {
    }

    public FoodQuestionResponseData(String str, int i) {
        this.question = str;
        this.id = i;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.question);
        parcel.writeInt(this.id);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        this.question = parcel.readString();
        this.id = parcel.readInt();
    }
}
