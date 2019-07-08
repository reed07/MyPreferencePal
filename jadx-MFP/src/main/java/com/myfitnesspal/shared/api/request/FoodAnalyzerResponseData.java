package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.Strings;
import java.util.Date;

public class FoodAnalyzerResponseData implements Parcelable {
    public static final Creator<FoodAnalyzerResponseData> CREATOR = new Creator<FoodAnalyzerResponseData>() {
        public FoodAnalyzerResponseData createFromParcel(Parcel parcel) {
            FoodAnalyzerResponseData foodAnalyzerResponseData = new FoodAnalyzerResponseData();
            foodAnalyzerResponseData.readFromParcel(parcel);
            return foodAnalyzerResponseData;
        }

        public FoodAnalyzerResponseData[] newArray(int i) {
            return new FoodAnalyzerResponseData[i];
        }
    };
    @Expose
    private long associatedFoodEntryLocalId;
    @Expose
    private Date date;
    @Expose
    private String foodId;
    @Expose
    private FoodInsightResponseData foodInsight;
    @Expose
    private FoodQuestionResponseData foodQuestion;
    @Expose
    private String mealName;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<FoodAnalyzerResponseData> {
    }

    public static final class Types {
        public static final String INSIGHT = "insight";
        public static final String QUESTION = "question";
    }

    public int describeContents() {
        return 0;
    }

    public FoodAnalyzerResponseData() {
    }

    public FoodAnalyzerResponseData(String str, Date date2, String str2, String str3, FoodInsightResponseData foodInsightResponseData, FoodQuestionResponseData foodQuestionResponseData) {
        this.type = str;
        this.date = date2;
        this.mealName = str2;
        this.foodId = str3;
        this.foodInsight = foodInsightResponseData;
        this.foodQuestion = foodQuestionResponseData;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public String getFoodId() {
        return this.foodId;
    }

    public void setFoodId(String str) {
        this.foodId = str;
    }

    public FoodInsightResponseData getFoodInsight() {
        return this.foodInsight;
    }

    public void setFoodInsight(FoodInsightResponseData foodInsightResponseData) {
        this.foodInsight = foodInsightResponseData;
    }

    public FoodQuestionResponseData getFoodQuestion() {
        return this.foodQuestion;
    }

    public void setFoodQuestion(FoodQuestionResponseData foodQuestionResponseData) {
        this.foodQuestion = foodQuestionResponseData;
    }

    public long getAssociatedFoodEntryLocalId() {
        return this.associatedFoodEntryLocalId;
    }

    public void setAssociatedFoodEntryLocalId(long j) {
        this.associatedFoodEntryLocalId = j;
    }

    public boolean isInsight() {
        return Strings.equalsIgnoreCase(this.type, Types.INSIGHT);
    }

    public boolean isQuestion() {
        return Strings.equalsIgnoreCase(this.type, Types.QUESTION);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeSerializable(this.date);
        parcel.writeString(this.mealName);
        parcel.writeString(this.foodId);
        parcel.writeParcelable(this.foodInsight, 0);
        parcel.writeParcelable(this.foodQuestion, 0);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        this.type = parcel.readString();
        this.date = (Date) parcel.readSerializable();
        this.mealName = parcel.readString();
        this.foodId = parcel.readString();
        this.foodInsight = (FoodInsightResponseData) parcel.readParcelable(FoodInsightResponseData.class.getClassLoader());
        this.foodQuestion = (FoodQuestionResponseData) parcel.readParcelable(FoodQuestionResponseData.class.getClassLoader());
    }
}
