package com.myfitnesspal.feature.recipes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import java.util.UUID;

public class RecipeAnalyticsIntentData implements Parcelable {
    public static final Creator<RecipeAnalyticsIntentData> CREATOR = new Creator<RecipeAnalyticsIntentData>() {
        public RecipeAnalyticsIntentData createFromParcel(Parcel parcel) {
            return new RecipeAnalyticsIntentData(parcel);
        }

        public RecipeAnalyticsIntentData[] newArray(int i) {
            return new RecipeAnalyticsIntentData[i];
        }
    };
    private final ActionType actionType;
    private final String flowId;
    private final StartScreen startScreen;

    public enum ActionType {
        Create("new"),
        Edit("edit");
        
        private final String analyticsAttributeValue;

        private ActionType(String str) {
            this.analyticsAttributeValue = str;
        }

        public String getAnalyticsAttributeValue() {
            return this.analyticsAttributeValue;
        }
    }

    public enum StartScreen {
        MealsRecipesFoods(MealAnalyticsHelper.VALUE_MEAL_RECIPES_FOODS),
        RecipeDetailsScreen("recipe_details_screen"),
        FoodSearch("food_search"),
        Deeplink("deeplink");
        
        private final String analyticsAttributeValue;

        private StartScreen(String str) {
            this.analyticsAttributeValue = str;
        }

        public String getAnalyticsAttributeValue() {
            return this.analyticsAttributeValue;
        }
    }

    public int describeContents() {
        return 0;
    }

    public static RecipeAnalyticsIntentData create(StartScreen startScreen2, ActionType actionType2) {
        return new RecipeAnalyticsIntentData(UUID.randomUUID().toString(), startScreen2, actionType2);
    }

    public static RecipeAnalyticsIntentData createWithoutFlowId(StartScreen startScreen2, ActionType actionType2) {
        return new RecipeAnalyticsIntentData(null, startScreen2, actionType2);
    }

    private RecipeAnalyticsIntentData(String str, StartScreen startScreen2, ActionType actionType2) {
        this.flowId = str;
        this.startScreen = startScreen2;
        this.actionType = actionType2;
    }

    private RecipeAnalyticsIntentData(Parcel parcel) {
        this.flowId = parcel.readString();
        this.startScreen = (StartScreen) parcel.readSerializable();
        this.actionType = (ActionType) parcel.readSerializable();
    }

    public String getFlowId() {
        return this.flowId;
    }

    public StartScreen getStartScreen() {
        return this.startScreen;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.flowId);
        parcel.writeSerializable(this.startScreen);
        parcel.writeSerializable(this.actionType);
    }
}
