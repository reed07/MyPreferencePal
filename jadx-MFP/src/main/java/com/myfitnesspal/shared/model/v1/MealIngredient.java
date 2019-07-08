package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.ArrayList;

public class MealIngredient extends DatabaseObject implements Parcelable {
    public static final Creator<MealIngredient> CREATOR = new Creator<MealIngredient>() {
        public MealIngredient createFromParcel(Parcel parcel) {
            return new MealIngredient(parcel);
        }

        public MealIngredient[] newArray(int i) {
            return new MealIngredient[i];
        }
    };
    private long creatorUserId;
    private FoodPortion foodPortion;
    private Food ingredientFood;
    private long ingredientFoodId;
    private String ingredientFoodUid;
    private boolean isFraction;
    private long mealFoodId;
    private float quantity;
    private int weightIndex;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MealIngredient> {
    }

    public static class LIST_MAPPER extends ArrayList<MealIngredient> {
    }

    public int describeContents() {
        return 0;
    }

    public int itemType() {
        return 9;
    }

    public MealIngredient() {
    }

    private MealIngredient(Parcel parcel) {
        super(parcel);
        readFromParcel(parcel);
    }

    public void setIngredientFood(Food food) {
        this.ingredientFood = food;
    }

    public Food getIngredientFood() {
        return this.ingredientFood;
    }

    public void setIngredientFoodId(long j) {
        this.ingredientFoodId = j;
    }

    public long getIngredientFoodId() {
        return this.ingredientFoodId;
    }

    public String getIngredientFoodUid() {
        return this.ingredientFoodUid;
    }

    public void setIngredientFoodUid(String str) {
        this.ingredientFoodUid = str;
    }

    public void setFoodPortion(FoodPortion foodPortion2) {
        this.foodPortion = foodPortion2;
    }

    public FoodPortion getFoodPortion() {
        return this.foodPortion;
    }

    public void setCreatorUserId(long j) {
        this.creatorUserId = j;
    }

    public long getCreatorUserId() {
        return this.creatorUserId;
    }

    public void setMealFoodId(long j) {
        this.mealFoodId = j;
    }

    public long getMealFoodId() {
        return this.mealFoodId;
    }

    public void setQuantity(float f) {
        this.quantity = f;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setIsFraction(boolean z) {
        this.isFraction = z;
    }

    public boolean isFraction() {
        return this.isFraction;
    }

    public void setWeightIndex(int i) {
        this.weightIndex = i;
    }

    public int getWeightIndex() {
        return this.weightIndex;
    }

    private void readFromParcel(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.ingredientFood = (Food) parcel.readParcelable(Food.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.foodPortion = (FoodPortion) parcel.readParcelable(FoodPortion.class.getClassLoader());
        }
        this.ingredientFoodId = parcel.readLong();
        this.ingredientFoodUid = parcel.readString();
        this.creatorUserId = parcel.readLong();
        this.mealFoodId = parcel.readLong();
        this.quantity = parcel.readFloat();
        this.weightIndex = parcel.readInt();
        this.isFraction = ParcelableUtil.readBoolean(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (this.ingredientFood != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.ingredientFood, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.foodPortion != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.foodPortion, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeLong(this.ingredientFoodId);
        parcel.writeString(this.ingredientFoodUid);
        parcel.writeLong(this.creatorUserId);
        parcel.writeLong(this.mealFoodId);
        parcel.writeFloat(this.quantity);
        parcel.writeInt(this.weightIndex);
        ParcelableUtil.writeBoolean(parcel, this.isFraction);
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Food food = this.ingredientFood;
        int i = 0;
        int hashCode2 = (hashCode + (food != null ? food.hashCode() : 0)) * 31;
        FoodPortion foodPortion2 = this.foodPortion;
        if (foodPortion2 != null) {
            i = foodPortion2.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        long j = this.ingredientFoodId;
        int hashCode3 = (((i2 + ((int) (j ^ (j >>> 32)))) * 31) + Strings.toString(this.ingredientFoodUid).hashCode()) * 31;
        long j2 = this.creatorUserId;
        int i3 = (hashCode3 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.mealFoodId;
        return ((((((i3 + ((int) (j3 ^ (j3 >>> 32)))) * 31) + Float.floatToIntBits(this.quantity)) * 31) + this.weightIndex) * 31) + (this.isFraction ? 1 : 0);
    }

    public boolean equals(Object obj) {
        return equalsWithIds(obj);
    }

    private boolean equalsWithIds(Object obj) {
        return super.equals(obj) && equalsIgnoreId(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r7.isFraction == r8.isFraction) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equalsIgnoreId(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.myfitnesspal.shared.model.v1.MealIngredient
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.myfitnesspal.shared.model.v1.MealIngredient r8 = (com.myfitnesspal.shared.model.v1.MealIngredient) r8
            com.myfitnesspal.shared.model.v1.Food r1 = r7.ingredientFood
            if (r1 != 0) goto L_0x0015
            com.myfitnesspal.shared.model.v1.Food r1 = r8.ingredientFood
            if (r1 != 0) goto L_0x0067
            goto L_0x001d
        L_0x0015:
            com.myfitnesspal.shared.model.v1.Food r3 = r8.ingredientFood
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0067
        L_0x001d:
            com.myfitnesspal.shared.model.v1.FoodPortion r1 = r7.foodPortion
            if (r1 != 0) goto L_0x0026
            com.myfitnesspal.shared.model.v1.FoodPortion r1 = r8.foodPortion
            if (r1 != 0) goto L_0x0067
            goto L_0x002e
        L_0x0026:
            com.myfitnesspal.shared.model.v1.FoodPortion r3 = r8.foodPortion
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0067
        L_0x002e:
            long r3 = r7.ingredientFoodId
            long r5 = r8.ingredientFoodId
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0067
            java.lang.String r1 = r7.ingredientFoodUid
            java.lang.String r3 = r8.ingredientFoodUid
            boolean r1 = com.uacf.core.util.Strings.equals(r1, r3)
            if (r1 == 0) goto L_0x0067
            long r3 = r7.creatorUserId
            long r5 = r8.creatorUserId
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0067
            long r3 = r7.mealFoodId
            long r5 = r8.mealFoodId
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0067
            float r1 = r7.quantity
            float r3 = r8.quantity
            int r1 = java.lang.Float.compare(r1, r3)
            if (r1 != 0) goto L_0x0067
            int r1 = r7.weightIndex
            int r3 = r8.weightIndex
            if (r1 != r3) goto L_0x0067
            boolean r1 = r7.isFraction
            boolean r8 = r8.isFraction
            if (r1 != r8) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r0 = 0
        L_0x0068:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v1.MealIngredient.equalsIgnoreId(java.lang.Object):boolean");
    }
}
