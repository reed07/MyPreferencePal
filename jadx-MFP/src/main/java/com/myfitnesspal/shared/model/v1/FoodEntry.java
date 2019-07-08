package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapperImpl;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.Calendar;
import java.util.Date;

public class FoodEntry extends DatabaseObject implements Parcelable {
    public static final Creator<FoodEntry> CREATOR = new Creator<FoodEntry>() {
        public FoodEntry createFromParcel(Parcel parcel) {
            return new FoodEntry(parcel);
        }

        public FoodEntry[] newArray(int i) {
            return new FoodEntry[i];
        }
    };
    private Date date;
    private String description;
    private String descriptionWithoutCalories;
    private Date entryTime;
    private Food food;
    private FoodPortion foodPortion;
    private boolean isFraction;
    private Date loggedAt;
    private MealFood mealFood;
    private String mealName;
    private float quantity;
    private int weightIndex;

    public static class Builder {
        private Date date;
        private String description;
        private String descriptionWithoutCalories;
        private Date entryTime;
        private Food food;
        private FoodPortion foodPortion;
        private boolean isFraction;
        private Date loggedAt;
        private MealFood mealFood;
        private String mealName;
        private float quantity;
        private int weightIndex;

        public static Builder fromFoodEntry(FoodEntry foodEntry) {
            return new Builder().date(foodEntry.getDate()).food(foodEntry.getFood()).mealName(foodEntry.getMealName()).quantity(foodEntry.getQuantity()).foodPortion(foodEntry.getFoodPortion()).isFraction(foodEntry.isFraction()).weightIndex(foodEntry.getWeightIndex()).description(foodEntry.getDescription()).descriptionWithoutCalories(foodEntry.getDescriptionWithoutCalories()).mealFood(foodEntry.getMealFood()).entryTime(foodEntry.getEntryTime()).loggedAt(foodEntry.getLoggedAt());
        }

        public static Builder fromFood(Food food2, int i, float f) {
            FoodPortion foodPortion2 = food2.getFoodPortions()[i];
            return new Builder().food(food2).foodPortion(foodPortion2).weightIndex(foodPortion2.getWeightIndex()).isFraction(foodPortion2.getIsFraction()).quantity(f);
        }

        public static Builder fromMfpFood(MfpFood mfpFood, User user) {
            return fromFood(new FoodMapperImpl(new FoodPortionMapperImpl()).mapFromMfpFood(mfpFood, user), mfpFood.getSelectedServingSizeIndex(), mfpFood.getSelectedServingAmount());
        }

        public Builder date(Date date2) {
            this.date = date2;
            return this;
        }

        public Builder food(Food food2) {
            this.food = food2;
            return this;
        }

        public Builder mealName(String str) {
            this.mealName = str;
            return this;
        }

        public Builder quantity(float f) {
            this.quantity = f;
            return this;
        }

        public Builder foodPortion(FoodPortion foodPortion2) {
            this.foodPortion = foodPortion2;
            return this;
        }

        public Builder isFraction(boolean z) {
            this.isFraction = z;
            return this;
        }

        public Builder weightIndex(int i) {
            this.weightIndex = i;
            return this;
        }

        public Builder description(String str) {
            this.description = str;
            return this;
        }

        public Builder descriptionWithoutCalories(String str) {
            this.descriptionWithoutCalories = str;
            return this;
        }

        public Builder mealFood(MealFood mealFood2) {
            this.mealFood = mealFood2;
            return this;
        }

        public Builder entryTime(Date date2) {
            this.entryTime = date2;
            return this;
        }

        public Builder loggedAt(Date date2) {
            this.loggedAt = date2;
            return this;
        }

        public FoodEntry build() {
            FoodEntry foodEntry = new FoodEntry(this.date, this.food, this.mealName, this.quantity, this.foodPortion, this.isFraction, this.weightIndex, this.description, this.descriptionWithoutCalories, this.mealFood, this.entryTime, this.loggedAt);
            return foodEntry;
        }
    }

    public int describeContents() {
        return 0;
    }

    public int itemType() {
        return 4;
    }

    public FoodEntry() {
    }

    public FoodEntry(FoodEntry foodEntry) {
        this.localId = foodEntry.localId;
        this.masterDatabaseId = foodEntry.masterDatabaseId;
        if (this.date != null) {
            this.date = (Date) foodEntry.date.clone();
        }
        this.food = new Food(foodEntry.food);
        String str = foodEntry.mealName;
        if (str == null) {
            str = "";
        }
        this.mealName = str;
        this.quantity = foodEntry.quantity;
        this.foodPortion = new FoodPortion(foodEntry.foodPortion);
        this.isFraction = foodEntry.isFraction;
        this.weightIndex = foodEntry.weightIndex;
        String str2 = foodEntry.description;
        if (str2 == null) {
            str2 = "";
        }
        this.description = str2;
        String str3 = foodEntry.descriptionWithoutCalories;
        if (str3 == null) {
            str3 = "";
        }
        this.descriptionWithoutCalories = str3;
        this.mealFood = foodEntry.mealFood;
        this.entryTime = foodEntry.entryTime;
        this.loggedAt = foodEntry.loggedAt;
    }

    protected FoodEntry(Parcel parcel) {
        super(parcel);
        this.date = ParcelableUtil.readDate(parcel);
        this.food = (Food) parcel.readParcelable(Food.class.getClassLoader());
        this.mealName = parcel.readString();
        this.quantity = parcel.readFloat();
        this.foodPortion = (FoodPortion) parcel.readParcelable(FoodPortion.class.getClassLoader());
        this.isFraction = ParcelableUtil.readBoolean(parcel);
        this.weightIndex = parcel.readInt();
        this.description = parcel.readString();
        this.descriptionWithoutCalories = parcel.readString();
        this.mealFood = (MealFood) parcel.readParcelable(MealFood.class.getClassLoader());
        this.entryTime = ParcelableUtil.readDate(parcel);
        this.loggedAt = ParcelableUtil.readDate(parcel);
    }

    protected FoodEntry(Date date2, Food food2, String str, float f, FoodPortion foodPortion2, boolean z, int i, String str2, String str3, MealFood mealFood2, Date date3, Date date4) {
        this.date = date2;
        this.food = food2;
        this.mealName = str;
        this.quantity = f;
        this.foodPortion = foodPortion2;
        this.isFraction = z;
        this.weightIndex = i;
        this.description = str2;
        this.descriptionWithoutCalories = str3;
        this.mealFood = mealFood2;
        this.entryTime = date3;
        this.loggedAt = date4;
    }

    public void setEntryTimeAndUpdateLoggedAt(Date date2) {
        setEntryTime(date2);
        setLoggedAt(Calendar.getInstance().getTime());
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food2) {
        this.food = food2;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float f) {
        this.quantity = f;
    }

    public FoodPortion getFoodPortion() {
        return this.foodPortion;
    }

    public void setFoodPortion(FoodPortion foodPortion2) {
        this.foodPortion = foodPortion2;
    }

    public boolean isFraction() {
        return this.isFraction;
    }

    public void setFraction(boolean z) {
        this.isFraction = z;
    }

    public int getWeightIndex() {
        return this.weightIndex;
    }

    public void setWeightIndex(int i) {
        this.weightIndex = i;
    }

    public void setIsFraction(boolean z) {
        this.isFraction = z;
    }

    public float getCaloriesValue() {
        return amountOfNutrientIndex(0);
    }

    public float getCarbsValue() {
        return amountOfNutrientIndex(9);
    }

    public float getFatValue() {
        return amountOfNutrientIndex(1);
    }

    public float getProteinValue() {
        return amountOfNutrientIndex(12);
    }

    public float getSodiumValue() {
        return amountOfNutrientIndex(7);
    }

    public float getSugarsValue() {
        return amountOfNutrientIndex(11);
    }

    public float amountOfNutrientIndex(int i) {
        Food food2 = this.food;
        return (food2 == null || food2.getNutritionalValues() == null) ? BitmapDescriptorFactory.HUE_RED : this.food.nutrientMultiplierForFoodPortion(this.foodPortion) * this.quantity * this.food.getNutritionalValues().valueForNutrientIndex(i);
    }

    public int roundedCalories() {
        return Math.round(getCaloriesValue());
    }

    public void clearCachedData() {
        setDescription(null);
    }

    public void setDescription(String str) {
        if (Strings.isEmpty(this.description) || !Strings.equals(this.description, str)) {
            this.description = str;
        }
    }

    public String getDescriptionWithoutCalories() {
        return this.descriptionWithoutCalories;
    }

    public void setDescriptionWithoutCalories(String str) {
        this.descriptionWithoutCalories = str;
    }

    public String summaryLine1() {
        return getFood().summaryLine1();
    }

    public MealFood getMealFood() {
        return this.mealFood;
    }

    public void setMealFood(MealFood mealFood2) {
        this.mealFood = mealFood2;
    }

    public void multiplyWithAndSetToQuantity(float f) {
        this.quantity *= f;
    }

    public Date getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(Date date2) {
        this.entryTime = date2;
    }

    public Date getLoggedAt() {
        return this.loggedAt;
    }

    public void setLoggedAt(Date date2) {
        this.loggedAt = date2;
    }

    public static FoodEntry quickAddedCaloriesFoodEntry(float f, String str, Session session, DbConnectionManager dbConnectionManager) {
        Food quickAddedCaloriesFood = Food.quickAddedCaloriesFood(dbConnectionManager);
        if (quickAddedCaloriesFood == null) {
            return null;
        }
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(quickAddedCaloriesFood);
        foodEntry.setFoodPortion(quickAddedCaloriesFood.defaultPortion());
        foodEntry.setWeightIndex(foodEntry.getFoodPortion().getWeightIndex());
        foodEntry.setQuantity(f);
        foodEntry.setMealName(str);
        foodEntry.setDate(session.getUser().getActiveDate());
        foodEntry.clearCachedData();
        return foodEntry;
    }

    public static FoodEntry quickAddedPremiumFoodEntry(User user, QuickAddFood quickAddFood, String str, DbConnectionManager dbConnectionManager) {
        Food createQuickAddedMacroFood = Food.createQuickAddedMacroFood(user, quickAddFood, dbConnectionManager);
        if (createQuickAddedMacroFood == null) {
            return null;
        }
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(createQuickAddedMacroFood);
        foodEntry.setFoodPortion(createQuickAddedMacroFood.defaultPortion());
        foodEntry.setWeightIndex(foodEntry.getFoodPortion().getWeightIndex());
        foodEntry.setQuantity(1.0f);
        foodEntry.setMealName(str);
        foodEntry.setDate(user.getActiveDate());
        foodEntry.clearCachedData();
        foodEntry.setEntryTimeAndUpdateLoggedAt(quickAddFood.getEntryTime());
        return foodEntry;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeDate(parcel, this.date);
        parcel.writeParcelable(this.food, i);
        parcel.writeString(this.mealName);
        parcel.writeFloat(this.quantity);
        parcel.writeParcelable(this.foodPortion, i);
        ParcelableUtil.writeBoolean(parcel, this.isFraction);
        parcel.writeInt(this.weightIndex);
        parcel.writeString(this.description);
        parcel.writeString(this.descriptionWithoutCalories);
        parcel.writeParcelable(this.mealFood, i);
        ParcelableUtil.writeDate(parcel, this.entryTime);
        ParcelableUtil.writeDate(parcel, this.loggedAt);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FoodEntry)) {
            return false;
        }
        FoodEntry foodEntry = (FoodEntry) obj;
        if (super.equals(obj)) {
            Date date2 = this.date;
            if (date2 != null ? date2.equals(foodEntry.date) : foodEntry.date == null) {
                Food food2 = this.food;
                if (food2 != null ? food2.equals(foodEntry.food) : foodEntry.food == null) {
                    if (Strings.equals(this.mealName, foodEntry.mealName) && Float.compare(this.quantity, foodEntry.quantity) == 0) {
                        FoodPortion foodPortion2 = this.foodPortion;
                        if (foodPortion2 != null ? foodPortion2.equals(foodEntry.foodPortion) : foodEntry.foodPortion == null) {
                            if (this.isFraction == foodEntry.isFraction && this.weightIndex == foodEntry.weightIndex && Strings.equals(this.description, foodEntry.description) && Strings.equals(this.descriptionWithoutCalories, foodEntry.descriptionWithoutCalories)) {
                                MealFood mealFood2 = this.mealFood;
                                if (mealFood2 != null ? mealFood2.equals(foodEntry.mealFood) : foodEntry.mealFood == null) {
                                    Date date3 = this.entryTime;
                                    if (date3 != null ? date3.equals(foodEntry.entryTime) : foodEntry.entryTime == null) {
                                        Date date4 = this.loggedAt;
                                        if (date4 != null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Date date2 = this.date;
        int i = 0;
        int hashCode2 = (hashCode + (date2 == null ? 0 : date2.hashCode())) * 31;
        Food food2 = this.food;
        int hashCode3 = (((((hashCode2 + (food2 == null ? 0 : food2.hashCode())) * 31) + Strings.toString(this.mealName).hashCode()) * 31) + Float.floatToIntBits(this.quantity)) * 31;
        FoodPortion foodPortion2 = this.foodPortion;
        int hashCode4 = (((((((((hashCode3 + (foodPortion2 == null ? 0 : foodPortion2.hashCode())) * 31) + (this.isFraction ? 1 : 0)) * 31) + this.weightIndex) * 31) + Strings.toString(this.description).hashCode()) * 31) + Strings.toString(this.descriptionWithoutCalories).hashCode()) * 31;
        MealFood mealFood2 = this.mealFood;
        int hashCode5 = (hashCode4 + (mealFood2 == null ? 0 : mealFood2.hashCode())) * 31;
        Date date3 = this.entryTime;
        int hashCode6 = (hashCode5 + (date3 != null ? date3.hashCode() : 0)) * 31;
        Date date4 = this.loggedAt;
        if (date4 != null) {
            i = date4.hashCode();
        }
        return hashCode6 + i;
    }
}
