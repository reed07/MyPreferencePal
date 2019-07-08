package com.myfitnesspal.feature.meals.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.util.ParcelableUtil;
import java.util.Date;

public class MealIngredientEditorBundleData implements Parcelable {
    public static final Creator<MealIngredientEditorBundleData> CREATOR = new Creator<MealIngredientEditorBundleData>() {
        public MealIngredientEditorBundleData createFromParcel(Parcel parcel) {
            return new MealIngredientEditorBundleData(parcel);
        }

        public MealIngredientEditorBundleData[] newArray(int i) {
            return new MealIngredientEditorBundleData[i];
        }
    };
    private String barcode;
    private Date date;
    private MfpFood food;
    private int index;
    private boolean isEditingMealIngredient;
    private String mealName;
    private String referrer;

    public int describeContents() {
        return 0;
    }

    public MealIngredientEditorBundleData(MfpFood mfpFood, Date date2, String str, String str2, String str3) {
        this(mfpFood, date2, str, str2, str3, Integer.MIN_VALUE, false);
    }

    public MealIngredientEditorBundleData(MfpFood mfpFood, Date date2, String str, String str2, String str3, int i, boolean z) {
        this.index = Integer.MIN_VALUE;
        this.food = mfpFood;
        this.date = date2;
        this.mealName = str;
        this.barcode = str2;
        this.referrer = str3;
        this.index = i;
        this.isEditingMealIngredient = z;
    }

    protected MealIngredientEditorBundleData(Parcel parcel) {
        this.index = Integer.MIN_VALUE;
        this.food = (MfpFood) parcel.readParcelable(MfpFood.class.getClassLoader());
        this.date = ParcelableUtil.readDate(parcel);
        this.mealName = parcel.readString();
        this.barcode = parcel.readString();
        this.referrer = parcel.readString();
        this.index = parcel.readInt();
        this.isEditingMealIngredient = ParcelableUtil.readBoolean(parcel);
    }

    public void setFood(MfpFood mfpFood) {
        this.food = (MfpFood) ParcelableUtil.clone(mfpFood, MfpFood.CREATOR);
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public void setMealName(String str) {
        this.mealName = str;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    public void setReferrer(String str) {
        this.referrer = str;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public MfpFood getFood() {
        return this.food;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMealName() {
        return this.mealName;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getReferrer() {
        return this.referrer;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isEditingMealIngredient() {
        return this.isEditingMealIngredient;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.food, i);
        ParcelableUtil.writeDate(parcel, this.date);
        parcel.writeString(this.mealName);
        parcel.writeString(this.barcode);
        parcel.writeString(this.referrer);
        parcel.writeInt(this.index);
        ParcelableUtil.writeBoolean(parcel, this.isEditingMealIngredient);
    }
}
