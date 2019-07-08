package com.myfitnesspal.feature.recipes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;

public class MfpPartialRecipe implements Parcelable {
    public static Creator<MfpPartialRecipe> CREATOR = new Creator<MfpPartialRecipe>() {
        public MfpPartialRecipe createFromParcel(Parcel parcel) {
            return new MfpPartialRecipe(parcel);
        }

        public MfpPartialRecipe[] newArray(int i) {
            return new MfpPartialRecipe[i];
        }
    };
    private MfpFood mfpFood;
    private String name;
    private MfpNutritionalContents nutritionalContents;
    private RecipeBoxItem recipeBoxItem;
    private double servings;

    public int describeContents() {
        return 0;
    }

    public MfpPartialRecipe() {
    }

    private MfpPartialRecipe(Parcel parcel) {
        this.name = parcel.readString();
        this.nutritionalContents = (MfpNutritionalContents) parcel.readParcelable(MfpNutritionalContents.class.getClassLoader());
        this.mfpFood = (MfpFood) parcel.readParcelable(MfpFood.class.getClassLoader());
        this.recipeBoxItem = (RecipeBoxItem) parcel.readParcelable(RecipeBoxItem.class.getClassLoader());
        this.servings = parcel.readDouble();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    public MfpFood getMfpFood() {
        return this.mfpFood;
    }

    public void setMfpFood(MfpFood mfpFood2) {
        this.mfpFood = mfpFood2;
    }

    public RecipeBoxItem getRecipeBoxItem() {
        return this.recipeBoxItem;
    }

    public void setRecipeBoxItem(RecipeBoxItem recipeBoxItem2) {
        this.recipeBoxItem = recipeBoxItem2;
    }

    public double getServings() {
        return this.servings;
    }

    public void setServings(double d) {
        this.servings = d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeParcelable(this.nutritionalContents, i);
        parcel.writeParcelable(this.mfpFood, i);
        parcel.writeParcelable(this.recipeBoxItem, i);
        parcel.writeDouble(this.servings);
    }
}
