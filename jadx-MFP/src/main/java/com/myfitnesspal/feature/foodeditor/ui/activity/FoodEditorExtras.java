package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import java.util.Date;

public class FoodEditorExtras implements Parcelable {
    public static final Creator<FoodEditorExtras> CREATOR = new Creator<FoodEditorExtras>() {
        public FoodEditorExtras createFromParcel(Parcel parcel) {
            return new FoodEditorExtras(parcel);
        }

        public FoodEditorExtras[] newArray(int i) {
            return new FoodEditorExtras[i];
        }
    };
    private ActionType actionType;
    private String barcode;
    private long date;
    private long existingFoodEntryLocalId;
    private long existingFoodEntryMasterId;
    private String flowId;
    private FoodAnalyzerResponseData foodAnalyzerResponseData;
    private FoodEditorAnalyticsExtras foodEditorAnalyticsExtras;
    private Date foodTimestamp;
    private boolean getSuggestedServings;
    private boolean isForRecipe;
    private boolean isMultiAddOn;
    private String mealName;
    private String screenTitle;
    private boolean showInsightsUi;
    private boolean startedFromDeepLink;
    private boolean supportPairedFoods;

    public enum ActionType {
        Create,
        Edit
    }

    public int describeContents() {
        return 0;
    }

    public FoodEditorExtras() {
    }

    public FoodEditorExtras(Parcel parcel) {
        this.actionType = (ActionType) parcel.readSerializable();
        this.barcode = parcel.readString();
        this.date = parcel.readLong();
        this.mealName = parcel.readString();
        boolean z = true;
        this.supportPairedFoods = parcel.readByte() != 0;
        this.showInsightsUi = parcel.readByte() != 0;
        this.existingFoodEntryMasterId = parcel.readLong();
        this.existingFoodEntryLocalId = parcel.readLong();
        this.startedFromDeepLink = parcel.readByte() != 0;
        this.getSuggestedServings = parcel.readByte() != 0;
        this.isMultiAddOn = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.isForRecipe = z;
        this.screenTitle = parcel.readString();
        this.foodEditorAnalyticsExtras = (FoodEditorAnalyticsExtras) parcel.readParcelable(FoodEditorAnalyticsExtras.class.getClassLoader());
        this.foodAnalyzerResponseData = (FoodAnalyzerResponseData) parcel.readParcelable(FoodAnalyzerResponseData.class.getClassLoader());
        this.flowId = parcel.readString();
        this.foodTimestamp = (Date) parcel.readSerializable();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.actionType);
        parcel.writeString(this.barcode);
        parcel.writeLong(this.date);
        parcel.writeString(this.mealName);
        parcel.writeByte(this.supportPairedFoods ? (byte) 1 : 0);
        parcel.writeByte(this.showInsightsUi ? (byte) 1 : 0);
        parcel.writeLong(this.existingFoodEntryMasterId);
        parcel.writeLong(this.existingFoodEntryLocalId);
        parcel.writeByte(this.startedFromDeepLink ? (byte) 1 : 0);
        parcel.writeByte(this.getSuggestedServings ? (byte) 1 : 0);
        parcel.writeByte(this.isMultiAddOn ? (byte) 1 : 0);
        parcel.writeByte(this.isForRecipe ? (byte) 1 : 0);
        parcel.writeString(this.screenTitle);
        parcel.writeParcelable(this.foodEditorAnalyticsExtras, i);
        parcel.writeParcelable(this.foodAnalyzerResponseData, i);
        parcel.writeString(this.flowId);
        parcel.writeSerializable(this.foodTimestamp);
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public FoodEditorExtras setActionType(ActionType actionType2) {
        this.actionType = actionType2;
        return this;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public FoodEditorExtras setBarcode(String str) {
        this.barcode = str;
        return this;
    }

    public long getDate() {
        return this.date;
    }

    public FoodEditorExtras setDate(long j) {
        this.date = j;
        return this;
    }

    public String getMealName() {
        return this.mealName;
    }

    public FoodEditorExtras setMealName(String str) {
        this.mealName = str;
        return this;
    }

    public boolean getSupportPairedFoods() {
        return this.supportPairedFoods;
    }

    public FoodEditorExtras setSupportPairedFoods(boolean z) {
        this.supportPairedFoods = z;
        return this;
    }

    public boolean showInsightsUi() {
        return this.showInsightsUi;
    }

    public FoodEditorExtras setShowInsightsUi(boolean z) {
        this.showInsightsUi = z;
        return this;
    }

    public long getExistingFoodEntryMasterId() {
        return this.existingFoodEntryMasterId;
    }

    public FoodEditorExtras setExistingFoodEntryMasterId(long j) {
        this.existingFoodEntryMasterId = j;
        return this;
    }

    public long getExistingFoodEntryLocalId() {
        return this.existingFoodEntryLocalId;
    }

    public FoodEditorExtras setExistingFoodEntryLocalId(long j) {
        this.existingFoodEntryLocalId = j;
        return this;
    }

    public boolean getStartedFromDeepLink() {
        return this.startedFromDeepLink;
    }

    public FoodEditorExtras setStartedFromDeepLink(boolean z) {
        this.startedFromDeepLink = z;
        return this;
    }

    public boolean getGetSuggestedServings() {
        return this.getSuggestedServings;
    }

    public FoodEditorExtras setGetSuggestedServings(boolean z) {
        this.getSuggestedServings = z;
        return this;
    }

    public boolean isMultiAddOn() {
        return this.isMultiAddOn;
    }

    public FoodEditorExtras setMultiAddOn(boolean z) {
        this.isMultiAddOn = z;
        return this;
    }

    public boolean isForRecipe() {
        return this.isForRecipe;
    }

    public FoodEditorExtras setForRecipe(boolean z) {
        this.isForRecipe = z;
        return this;
    }

    public String getScreenTitle() {
        return this.screenTitle;
    }

    public FoodEditorExtras setScreenTitle(String str) {
        this.screenTitle = str;
        return this;
    }

    public FoodEditorAnalyticsExtras getFoodEditorAnalyticsExtras() {
        return this.foodEditorAnalyticsExtras;
    }

    public FoodEditorExtras setFoodEditorAnalyticsExtras(FoodEditorAnalyticsExtras foodEditorAnalyticsExtras2) {
        this.foodEditorAnalyticsExtras = foodEditorAnalyticsExtras2;
        return this;
    }

    public FoodAnalyzerResponseData getFoodAnalyzerResponseData() {
        return this.foodAnalyzerResponseData;
    }

    public FoodEditorExtras setFoodAnalyzerResponseData(FoodAnalyzerResponseData foodAnalyzerResponseData2) {
        this.foodAnalyzerResponseData = foodAnalyzerResponseData2;
        return this;
    }

    public String getFlowId() {
        return this.flowId;
    }

    public FoodEditorExtras setFlowId(String str) {
        this.flowId = str;
        return this;
    }

    public Date getFoodTimestamp() {
        return this.foodTimestamp;
    }

    public FoodEditorExtras setFoodTimestamp(Date date2) {
        this.foodTimestamp = date2;
        return this;
    }
}
