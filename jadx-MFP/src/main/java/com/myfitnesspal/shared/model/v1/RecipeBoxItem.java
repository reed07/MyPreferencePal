package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.db.DbConnectionManager;
import javax.annotation.Nonnull;

public class RecipeBoxItem extends DatabaseObject implements Parcelable {
    public static final Creator<RecipeBoxItem> CREATOR = new Creator<RecipeBoxItem>() {
        public RecipeBoxItem createFromParcel(Parcel parcel) {
            return new RecipeBoxItem(parcel);
        }

        public RecipeBoxItem[] newArray(int i) {
            return new RecipeBoxItem[i];
        }
    };
    private String foodDescription;
    private RecipeFood recipeFood;
    private long recipeFoodId;

    public int describeContents() {
        return 0;
    }

    public int itemType() {
        return 12;
    }

    public RecipeBoxItem() {
    }

    private RecipeBoxItem(Parcel parcel) {
        super(parcel);
        this.recipeFoodId = parcel.readLong();
        this.foodDescription = parcel.readString();
    }

    public synchronized RecipeFood recipeFood(@Nonnull DbConnectionManager dbConnectionManager) {
        if (this.recipeFood == null) {
            Food fetchFoodById = dbConnectionManager.foodDbAdapter().fetchFoodById(this.recipeFoodId);
            if (fetchFoodById == null || fetchFoodById.isRecipe()) {
                this.recipeFood = (RecipeFood) fetchFoodById;
            } else {
                dbConnectionManager.foodDbAdapter().updateFoodTypeForLocalId(this.recipeFoodId, 11);
                this.recipeFood = (RecipeFood) dbConnectionManager.foodDbAdapter().fetchFoodById(this.recipeFoodId);
            }
        }
        return this.recipeFood;
    }

    public long getRecipeFoodId() {
        return this.recipeFoodId;
    }

    public String getFoodDescription() {
        String str = this.foodDescription;
        return str != null ? str : "";
    }

    public void setFoodDescription(String str) {
        this.foodDescription = str;
    }

    public void setRecipeFoodId(int i) {
        this.recipeFoodId = (long) i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.recipeFoodId);
        parcel.writeString(this.foodDescription);
    }
}
