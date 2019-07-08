package com.myfitnesspal.shared.api.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MfpDailyGoalPost implements Parcelable {
    public static final Creator<MfpDailyGoalPost> CREATOR = new Creator<MfpDailyGoalPost>() {
        public MfpDailyGoalPost createFromParcel(Parcel parcel) {
            return new MfpDailyGoalPost(parcel);
        }

        public MfpDailyGoalPost[] newArray(int i) {
            return new MfpDailyGoalPost[i];
        }
    };
    @Expose
    private String assignExerciseEnergy;
    @Expose
    private int calcium;
    @Expose
    private float carbohydrates;
    @Expose
    private float cholesterol;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private float fat;
    @Expose
    private float fiber;
    @Expose
    private int iron;
    @Expose
    private List<MealGoalPost> mealGoals;
    @Expose
    private float monounsaturatedFat;
    @Expose
    private float polyunsaturatedFat;
    @Expose
    private float potassium;
    @Expose
    private float protein;
    @Expose
    private float saturatedFat;
    @Expose
    private float sodium;
    @Expose
    private float sugar;
    @Expose
    private float transFat;
    @Expose
    private int vitaminA;
    @Expose
    private int vitaminC;

    public static class LIST_MAPPER extends ArrayList<MfpDailyGoalPost> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpDailyGoalPost() {
    }

    public MfpDailyGoalPost(MfpDailyGoal mfpDailyGoal) {
        this.energy = mfpDailyGoal.getEnergy();
        this.carbohydrates = mfpDailyGoal.getCarbohydrates();
        this.protein = mfpDailyGoal.getProtein();
        this.fat = mfpDailyGoal.getFat();
        this.saturatedFat = mfpDailyGoal.getSaturatedFat();
        this.polyunsaturatedFat = mfpDailyGoal.getPolyunsaturatedFat();
        this.monounsaturatedFat = mfpDailyGoal.getMonounsaturatedFat();
        this.transFat = mfpDailyGoal.getTransFat();
        this.fiber = mfpDailyGoal.getFiber();
        this.sugar = mfpDailyGoal.getSugar();
        this.cholesterol = mfpDailyGoal.getCholesterol();
        this.sodium = mfpDailyGoal.getSodium();
        this.potassium = mfpDailyGoal.getPotassium();
        this.vitaminA = mfpDailyGoal.getVitaminA();
        this.vitaminC = mfpDailyGoal.getVitaminC();
        this.calcium = mfpDailyGoal.getCalcium();
        this.iron = mfpDailyGoal.getIron();
        this.assignExerciseEnergy = mfpDailyGoal.getAssignExerciseEnergy();
        this.mealGoals = getMealGoalPostFromMealGoals(mfpDailyGoal.getMealGoals());
    }

    public MfpDailyGoalPost(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public void setCarbohydrates(float f) {
        this.carbohydrates = f;
    }

    public void setProtein(float f) {
        this.protein = f;
    }

    public void setFat(float f) {
        this.fat = f;
    }

    public void setSaturatedFat(float f) {
        this.saturatedFat = f;
    }

    public void setPolyunsaturatedFat(float f) {
        this.polyunsaturatedFat = f;
    }

    public void setMonounsaturatedFat(float f) {
        this.monounsaturatedFat = f;
    }

    public void setTransFat(float f) {
        this.transFat = f;
    }

    public void setFiber(float f) {
        this.fiber = f;
    }

    public void setSugar(float f) {
        this.sugar = f;
    }

    public void setCholesterol(float f) {
        this.cholesterol = f;
    }

    public void setSodium(float f) {
        this.sodium = f;
    }

    public void setPotassium(float f) {
        this.potassium = f;
    }

    public void setVitaminA(int i) {
        this.vitaminA = i;
    }

    public void setVitaminC(int i) {
        this.vitaminC = i;
    }

    public void setCalcium(int i) {
        this.calcium = i;
    }

    public void setIron(int i) {
        this.iron = i;
    }

    public void setAssignExerciseEnergy(String str) {
        this.assignExerciseEnergy = str;
    }

    public void setMealGoals(List<MealGoalPost> list) {
        this.mealGoals = list;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public float getCarbohydrates() {
        return this.carbohydrates;
    }

    public float getProtein() {
        return this.protein;
    }

    public float getFat() {
        return this.fat;
    }

    public float getSaturatedFat() {
        return this.saturatedFat;
    }

    public float getPolyunsaturatedFat() {
        return this.polyunsaturatedFat;
    }

    public float getMonounsaturatedFat() {
        return this.monounsaturatedFat;
    }

    public float getTransFat() {
        return this.transFat;
    }

    public float getFiber() {
        return this.fiber;
    }

    public float getSugar() {
        return this.sugar;
    }

    public float getCholesterol() {
        return this.cholesterol;
    }

    public float getSodium() {
        return this.sodium;
    }

    public float getPotassium() {
        return this.potassium;
    }

    public int getVitaminA() {
        return this.vitaminA;
    }

    public int getVitaminC() {
        return this.vitaminC;
    }

    public int getCalcium() {
        return this.calcium;
    }

    public int getIron() {
        return this.iron;
    }

    public String getAssignExerciseEnergy() {
        return this.assignExerciseEnergy;
    }

    public List<MealGoalPost> getMealGoals() {
        return this.mealGoals;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.energy != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.energy, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeFloat(this.carbohydrates);
        parcel.writeFloat(this.protein);
        parcel.writeFloat(this.fat);
        parcel.writeFloat(this.saturatedFat);
        parcel.writeFloat(this.polyunsaturatedFat);
        parcel.writeFloat(this.monounsaturatedFat);
        parcel.writeFloat(this.transFat);
        parcel.writeFloat(this.fiber);
        parcel.writeFloat(this.sugar);
        parcel.writeFloat(this.cholesterol);
        parcel.writeFloat(this.sodium);
        parcel.writeFloat(this.potassium);
        parcel.writeInt(this.vitaminA);
        parcel.writeInt(this.vitaminC);
        parcel.writeInt(this.calcium);
        parcel.writeInt(this.iron);
        parcel.writeString(this.assignExerciseEnergy);
        ParcelableUtil.writeList(parcel, this.mealGoals);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.energy = (MfpMeasuredValue) parcel.readParcelable(MfpMeasuredValue.class.getClassLoader());
        }
        this.carbohydrates = parcel.readFloat();
        this.protein = parcel.readFloat();
        this.fat = parcel.readFloat();
        this.saturatedFat = parcel.readFloat();
        this.polyunsaturatedFat = parcel.readFloat();
        this.monounsaturatedFat = parcel.readFloat();
        this.transFat = parcel.readFloat();
        this.fiber = parcel.readFloat();
        this.sugar = parcel.readFloat();
        this.cholesterol = parcel.readFloat();
        this.sodium = parcel.readFloat();
        this.potassium = parcel.readFloat();
        this.vitaminA = parcel.readInt();
        this.vitaminC = parcel.readInt();
        this.calcium = parcel.readInt();
        this.iron = parcel.readInt();
        this.assignExerciseEnergy = parcel.readString();
        this.mealGoals = ParcelableUtil.readList(parcel, MealGoalPost.class);
    }

    private List<MealGoalPost> getMealGoalPostFromMealGoals(List<MealGoal> list) {
        final ArrayList arrayList = new ArrayList();
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<MealGoal>() {
            public void execute(MealGoal mealGoal) {
                if (mealGoal != null) {
                    arrayList.add(new MealGoalPost(mealGoal));
                }
            }
        });
        return arrayList;
    }
}
