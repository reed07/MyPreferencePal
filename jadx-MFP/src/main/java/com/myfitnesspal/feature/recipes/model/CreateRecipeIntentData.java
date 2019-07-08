package com.myfitnesspal.feature.recipes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.Strings;

public class CreateRecipeIntentData implements Parcelable {
    public static final Creator<CreateRecipeIntentData> CREATOR = new Creator<CreateRecipeIntentData>() {
        public CreateRecipeIntentData createFromParcel(Parcel parcel) {
            return new CreateRecipeIntentData(parcel);
        }

        public CreateRecipeIntentData[] newArray(int i) {
            return new CreateRecipeIntentData[i];
        }
    };
    private final String hash;
    private final String ingredientsString;
    private final String name;
    private final String pictureUrl;
    private final double servings;
    private final String sourceUrl;

    public int describeContents() {
        return 0;
    }

    public CreateRecipeIntentData(String str, double d, String str2) {
        this(str, d, str2, null, null, null);
    }

    public CreateRecipeIntentData(String str, double d, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.servings = d;
        this.ingredientsString = str2;
        this.sourceUrl = str3;
        this.pictureUrl = str4;
        this.hash = str5;
    }

    private CreateRecipeIntentData(Parcel parcel) {
        this.name = parcel.readString();
        this.servings = parcel.readDouble();
        this.ingredientsString = parcel.readString();
        this.sourceUrl = parcel.readString();
        this.pictureUrl = parcel.readString();
        this.hash = parcel.readString();
    }

    public String getName() {
        return this.name;
    }

    public double getServings() {
        return this.servings;
    }

    public String getIngredientsString() {
        return this.ingredientsString;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public String getHash() {
        return this.hash;
    }

    public boolean isImportingIngredients() {
        return Strings.notEmpty(this.ingredientsString);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeDouble(this.servings);
        parcel.writeString(this.ingredientsString);
        parcel.writeString(this.sourceUrl);
        parcel.writeString(this.pictureUrl);
        parcel.writeString(this.hash);
    }
}
