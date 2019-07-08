package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class MfpDailyGoal implements Parcelable {
    public static final Creator<MfpDailyGoal> CREATOR = new Creator<MfpDailyGoal>() {
        public MfpDailyGoal createFromParcel(Parcel parcel) {
            return new MfpDailyGoal(parcel);
        }

        public MfpDailyGoal[] newArray(int i) {
            return new MfpDailyGoal[i];
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
    private String dayOfWeek;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private int exerciseCarbohydratesPercentage;
    @Expose
    private int exerciseFatPercentage;
    @Expose
    private int exerciseProteinPercentage;
    @Expose
    private int exerciseSaturatedFatPercentage;
    @Expose
    private int exerciseSugarPercentage;
    @Expose
    private float fat;
    @Expose
    private float fiber;
    @Expose
    private int groupId;
    @Expose
    private int iron;
    @Expose
    private List<MealGoal> mealGoals;
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

    public static class LIST_MAPPER extends ArrayList<MfpDailyGoal> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpDailyGoal() {
    }

    public MfpDailyGoal(Parcel parcel) {
        readFromParcel(parcel);
    }

    public static float getCalories(MfpDailyGoal mfpDailyGoal, UserEnergyService userEnergyService) {
        return userEnergyService.getEnergy(Energy.CALORIES, mfpDailyGoal.getEnergy());
    }

    public static float getKilojoules(MfpDailyGoal mfpDailyGoal, UserEnergyService userEnergyService) {
        return userEnergyService.getEnergy(Energy.KILOJOULES, mfpDailyGoal.getEnergy());
    }

    public static float getLocalizedEnergy(MfpDailyGoal mfpDailyGoal, UserEnergyService userEnergyService) {
        return userEnergyService.getCurrentEnergy(mfpDailyGoal.getEnergy());
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public void setGroupId(int i) {
        this.groupId = i;
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

    public void setExerciseCarbohydratesPercentage(int i) {
        this.exerciseCarbohydratesPercentage = i;
    }

    public void setExerciseFatPercentage(int i) {
        this.exerciseFatPercentage = i;
    }

    public void setExerciseProteinPercentage(int i) {
        this.exerciseProteinPercentage = i;
    }

    public void setExerciseSaturatedFatPercentage(int i) {
        this.exerciseSaturatedFatPercentage = i;
    }

    public void setExerciseSugarPercentage(int i) {
        this.exerciseSugarPercentage = i;
    }

    public void setMealGoals(List<MealGoal> list) {
        this.mealGoals = list;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public int getGroupId() {
        return this.groupId;
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

    public int getExerciseCarbohydratesPercentage() {
        return this.exerciseCarbohydratesPercentage;
    }

    public int getExerciseFatPercentage() {
        return this.exerciseFatPercentage;
    }

    public int getExerciseProteinPercentage() {
        return this.exerciseProteinPercentage;
    }

    public int getExerciseSaturatedFatPercentage() {
        return this.exerciseSaturatedFatPercentage;
    }

    public int getExerciseSugarPercentage() {
        return this.exerciseSugarPercentage;
    }

    public List<MealGoal> getMealGoals() {
        return this.mealGoals;
    }

    public boolean isAssignExerciseEnergyOn() {
        return !Strings.equals(this.assignExerciseEnergy, "off");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.dayOfWeek);
        parcel.writeInt(this.groupId);
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
        parcel.writeInt(this.exerciseCarbohydratesPercentage);
        parcel.writeInt(this.exerciseFatPercentage);
        parcel.writeInt(this.exerciseProteinPercentage);
        parcel.writeInt(this.exerciseSaturatedFatPercentage);
        parcel.writeInt(this.exerciseSugarPercentage);
        ParcelableUtil.writeList(parcel, this.mealGoals);
    }

    private void readFromParcel(Parcel parcel) {
        this.dayOfWeek = parcel.readString();
        this.groupId = parcel.readInt();
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
        this.exerciseCarbohydratesPercentage = parcel.readInt();
        this.exerciseFatPercentage = parcel.readInt();
        this.exerciseProteinPercentage = parcel.readInt();
        this.exerciseSaturatedFatPercentage = parcel.readInt();
        this.exerciseSugarPercentage = parcel.readInt();
        this.mealGoals = ParcelableUtil.readList(parcel, MealGoal.class);
    }

    public NutritionalValues toNutritionalValues(UserEnergyService userEnergyService) {
        float[] fArr = new float[20];
        fArr[0] = getCalories(this, userEnergyService);
        fArr[1] = this.fat;
        fArr[2] = this.saturatedFat;
        fArr[3] = this.polyunsaturatedFat;
        fArr[4] = this.monounsaturatedFat;
        fArr[5] = this.transFat;
        fArr[6] = this.cholesterol;
        fArr[7] = this.sodium;
        fArr[8] = this.potassium;
        fArr[9] = this.carbohydrates;
        fArr[10] = this.fiber;
        fArr[11] = this.sugar;
        fArr[12] = this.protein;
        fArr[13] = (float) this.vitaminA;
        fArr[14] = (float) this.vitaminC;
        fArr[15] = (float) this.calcium;
        fArr[16] = (float) this.iron;
        return new NutritionalValues(fArr);
    }

    public float getValueForNutritionalValuesIndex(int i) {
        switch (i) {
            case 0:
                return getEnergy().getValue();
            case 1:
                return getFat();
            case 2:
                return getSaturatedFat();
            case 3:
                return getPolyunsaturatedFat();
            case 4:
                return getMonounsaturatedFat();
            case 5:
                return getTransFat();
            case 6:
                return getCholesterol();
            case 7:
                return getSodium();
            case 8:
                return getPotassium();
            case 9:
                return getCarbohydrates();
            case 10:
                return getFiber();
            case 11:
                return getSugar();
            case 12:
                return getProtein();
            case 13:
                return (float) getVitaminA();
            case 14:
                return (float) getVitaminC();
            case 15:
                return (float) getCalcium();
            case 16:
                return (float) getIron();
            default:
                throw new IllegalArgumentException("specified nutrientIndex is unrecognized");
        }
    }

    public String toHashKey() {
        double value = (double) getEnergy().getValue();
        if (!"calories".equals(getEnergy().getUnit())) {
            value = UnitsUtils.getCalories(value);
        }
        return String.format("%d_%.2f_%.2f_%.2f", new Object[]{Long.valueOf((long) value), Float.valueOf(getCarbohydrates()), Float.valueOf(getProtein()), Float.valueOf(getFat())});
    }
}
