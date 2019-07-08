package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class RecipeIngredient extends FoodEntry {
    public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
        public RecipeIngredient createFromParcel(Parcel parcel) {
            return new RecipeIngredient(parcel);
        }

        public RecipeIngredient[] newArray(int i) {
            return new RecipeIngredient[i];
        }
    };
    private long ingredientFoodId;
    private String ingredientFoodOriginalUid;
    private String ingredientFoodUid;
    private long recipeFoodId;

    public int itemType() {
        return 13;
    }

    public RecipeIngredient() {
    }

    private RecipeIngredient(Parcel parcel) {
        super(parcel);
        this.ingredientFoodId = parcel.readLong();
        this.ingredientFoodUid = parcel.readString();
        this.ingredientFoodOriginalUid = parcel.readString();
        this.recipeFoodId = parcel.readLong();
    }

    public void setIngredientFoodId(long j) {
        this.ingredientFoodId = j;
    }

    public void setRecipeFoodId(long j) {
        this.recipeFoodId = j;
    }

    public long getRecipeFoodId() {
        return this.recipeFoodId;
    }

    public long getIngredientFoodId() {
        return this.ingredientFoodId;
    }

    public String getIngredientFoodOriginalUid() {
        return this.ingredientFoodOriginalUid;
    }

    public void setIngredientFoodOriginalUid(String str) {
        this.ingredientFoodOriginalUid = str;
    }

    public String getIngredientFoodUid() {
        return this.ingredientFoodUid;
    }

    public void setIngredientFoodUid(String str) {
        this.ingredientFoodUid = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.ingredientFoodId);
        parcel.writeString(this.ingredientFoodUid);
        parcel.writeString(this.ingredientFoodOriginalUid);
        parcel.writeLong(this.recipeFoodId);
    }
}
