package com.myfitnesspal.shared.model.v1;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.shared.constants.Constants.Goals.Nutrient;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Date;

public class NutritionalValues implements Parcelable, Cloneable {
    private static final int[] ADJUSTABLE_NUTRIENTS = {1, 9, 12, 10, 2, 11};
    public static final float BLANK_NUTRIENT_VALUE = -1.0f;
    public static final Creator<NutritionalValues> CREATOR = new Creator<NutritionalValues>() {
        public NutritionalValues createFromParcel(Parcel parcel) {
            return new NutritionalValues(parcel);
        }

        public NutritionalValues[] newArray(int i) {
            return new NutritionalValues[i];
        }
    };
    public static final int NO_NUTRIENT = -1;
    public static final int NUTRIENT_ADDED_SUGARS = 17;
    public static final int NUTRIENT_CALCIUM = 15;
    public static final int NUTRIENT_CALORIES = 0;
    public static final int NUTRIENT_CARBOHYDRATES = 9;
    public static final int NUTRIENT_CHOLESTEROL = 6;
    public static final int NUTRIENT_FAT = 1;
    public static final int NUTRIENT_FIBER = 10;
    public static final int NUTRIENT_FIRST = 0;
    private static final String[] NUTRIENT_INDEX_IDENTIFIERS = {"calories", "fat", "saturated_fat", Nutrient.POLYUNSATURATED_FAT, Nutrient.MONOUNSATURATED_FAT, "trans_fat", "cholesterol", "sodium", "potassium", "carbs", Nutrient.FIBER, "sugar", "protein", "vitamin_a", "vitamin_c", "calcium", "iron", "added_sugars", "vitamin_d", "sugar_alcohols"};
    private static final boolean[] NUTRIENT_INDEX_IS_PERCENTAGE = {false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false};
    private static final int[] NUTRIENT_INDEX_UNIT_LABEL = {R.string.empty_string, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.milligram_abbreviation, R.string.milligram_abbreviation, R.string.milligram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.goals_percent, R.string.goals_percent, R.string.goals_percent, R.string.goals_percent, R.string.gram_abbreviation, R.string.goals_percent, R.string.gram_abbreviation};
    public static final int NUTRIENT_IRON = 16;
    private static final int[] NUTRIENT_LABELS = {R.string.calories, R.string.totalFat_nutrient, R.string.saturated_nutrient, R.string.polyunsaturated_nutrient, R.string.monounsaturated_nutrient, R.string.trans_nutrient, R.string.cholesterol_nutrient, R.string.sodium_nutrient, R.string.potassium_nutrient, R.string.carbohydrates_nutrient, R.string.fiber, R.string.sugars_nutrient, R.string.protein, R.string.vitamin_a, R.string.vitamin_c, R.string.calcium, R.string.iron, R.string.added_sugars_nutrient, R.string.vitamin_d, R.string.sugar_alcohols_nutrient};
    public static final int NUTRIENT_MAX = 20;
    public static final int NUTRIENT_MAX_WITHOUT_NEW_NUTRIENTS = 17;
    public static final int NUTRIENT_MONO_UNSATURATED_FAT = 4;
    public static final int NUTRIENT_POLY_UNSATURATED_FAT = 3;
    public static final int NUTRIENT_POTASSIUM = 8;
    public static final int NUTRIENT_PROTEIN = 12;
    public static final int NUTRIENT_SATURATED_FAT = 2;
    public static final int NUTRIENT_SODIUM = 7;
    public static final int NUTRIENT_SUGAR = 11;
    public static final int NUTRIENT_SUGAR_ALCOHOLS = 19;
    public static final int NUTRIENT_TRANS_FAT = 5;
    public static final int NUTRIENT_VITAMIN_A = 13;
    public static final int NUTRIENT_VITAMIN_C = 14;
    public static final int NUTRIENT_VITAMIN_D = 18;
    private static final boolean[] SUBORDINATE_NUTRIENT_INDEX = {false, false, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false, true, false, true};
    public static final float ZERO_NUTRIENT_VALUE = 0.0f;
    private float[] values;

    private static float defaultValueWhenEmpty(float f, float f2) {
        if (f < BitmapDescriptorFactory.HUE_RED) {
            return -1.0f;
        }
        return f * f2;
    }

    public static boolean isMacroOrCalorieIndex(int i) {
        return i == 0 || i == 9 || i == 1 || i == 12;
    }

    public int describeContents() {
        return 0;
    }

    public static String getIdentifierForNutrientIndex(int i) {
        return NUTRIENT_INDEX_IDENTIFIERS[i];
    }

    public NutritionalValues() {
        this.values = new float[20];
    }

    public NutritionalValues(float[] fArr) {
        this.values = fArr;
    }

    private NutritionalValues(Parcel parcel) {
        this.values = parcel.createFloatArray();
    }

    public void resetToZero() {
        for (int i = 0; i < 20; i++) {
            this.values[i] = 0.0f;
        }
    }

    public static int simplifiedLabelForNutrientIndex(int i, boolean z) {
        int i2;
        if (i == 0) {
            return z ? R.string.calories : R.string.kilojoules;
        }
        if (i >= 0) {
            int[] iArr = NUTRIENT_LABELS;
            if (i <= iArr.length - 1) {
                i2 = iArr[i];
                return i2;
            }
        }
        i2 = R.string.error;
        return i2;
    }

    public NutritionalValues initAsBlank() {
        for (int i = 0; i < 20; i++) {
            this.values[i] = -1.0f;
        }
        return this;
    }

    public float[] getValues() {
        return this.values;
    }

    public void setValues(float[] fArr) {
        this.values = fArr;
    }

    public void setNutrientIndex(int i, float f) {
        this.values[i] = f;
    }

    public float valueForNutrientIndex(int i) {
        return this.values[i];
    }

    public boolean valueIsNullForNutrientIndex(int i) {
        return ((double) this.values[i]) < 0.0d;
    }

    public float calories() {
        return this.values[0];
    }

    public float getCaloriesValue(float f) {
        float f2 = f * this.values[0];
        if (((double) f2) < 0.0d) {
            return -1.0f;
        }
        return f2;
    }

    public static String simplifiedLabelForNutrientIndex(Context context, int i, boolean z) {
        return context.getString(simplifiedLabelForNutrientIndex(i, z));
    }

    public static String unitForNutrientIndex(Context context, int i) {
        return context.getString(NUTRIENT_INDEX_UNIT_LABEL[i]);
    }

    public boolean isSubordinateNutrientIndex(int i) {
        return SUBORDINATE_NUTRIENT_INDEX[i];
    }

    public boolean nutrientIndexIsPercentage(int i) {
        return NUTRIENT_INDEX_IS_PERCENTAGE[i];
    }

    public int getUnits(int i) {
        return NUTRIENT_INDEX_UNIT_LABEL[i];
    }

    private void adjustForCaloriesEarned(float f) {
        if (calories() >= 100.0f) {
            float calories = (f / calories()) + 1.0f;
            int i = 0;
            while (true) {
                int[] iArr = ADJUSTABLE_NUTRIENTS;
                if (i < iArr.length) {
                    float[] fArr = this.values;
                    int i2 = iArr[i];
                    fArr[i2] = fArr[i2] * calories;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public NutritionalValues adjustForCaloriesEarnedBasedOnPreference(Lazy<NutrientGoalService> lazy, float f, Date date) {
        if (lazy == null) {
            adjustForCaloriesEarned(f);
        } else {
            MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService) lazy.get()).getDailyGoalForDayOfWeekSync(date);
            if (dailyGoalForDayOfWeekSync == null) {
                adjustForCaloriesEarned(f);
            } else {
                setMacros(dailyGoalForDayOfWeekSync.getEnergy().getValue(), dailyGoalForDayOfWeekSync.getCarbohydrates(), dailyGoalForDayOfWeekSync.getProtein(), dailyGoalForDayOfWeekSync.getFat());
                if (dailyGoalForDayOfWeekSync.isAssignExerciseEnergyOn()) {
                    adjustCaloriesBasedOnDailyGoal(dailyGoalForDayOfWeekSync, f);
                }
            }
        }
        return this;
    }

    private void adjustCaloriesBasedOnDailyGoal(MfpDailyGoal mfpDailyGoal, float f) {
        int[] iArr = {9, 11, 12, 1, 2};
        float[] fArr = this.values;
        int i = iArr[0];
        fArr[i] = fArr[i] + ((((float) mfpDailyGoal.getExerciseCarbohydratesPercentage()) * f) / 400.0f);
        float[] fArr2 = this.values;
        int i2 = iArr[1];
        fArr2[i2] = fArr2[i2] + ((((float) mfpDailyGoal.getExerciseSugarPercentage()) * f) / 400.0f);
        float[] fArr3 = this.values;
        int i3 = iArr[2];
        fArr3[i3] = fArr3[i3] + ((((float) mfpDailyGoal.getExerciseProteinPercentage()) * f) / 400.0f);
        float[] fArr4 = this.values;
        int i4 = iArr[3];
        fArr4[i4] = fArr4[i4] + ((((float) mfpDailyGoal.getExerciseFatPercentage()) * f) / 900.0f);
        float[] fArr5 = this.values;
        int i5 = iArr[4];
        fArr5[i5] = fArr5[i5] + ((f * ((float) mfpDailyGoal.getExerciseSaturatedFatPercentage())) / 900.0f);
    }

    public void clampNegativesToZero() {
        for (int i = 0; i < 20; i++) {
            float[] fArr = this.values;
            if (fArr[i] < BitmapDescriptorFactory.HUE_RED) {
                fArr[i] = 0.0f;
            }
        }
    }

    public void addNutritionalValues(NutritionalValues nutritionalValues, float f) {
        clampNegativesToZero();
        for (int i = 0; i < 20; i++) {
            float[] fArr = this.values;
            fArr[i] = fArr[i] + (nutritionalValues.values[i] * f);
        }
    }

    public Object clone() {
        NutritionalValues nutritionalValues = new NutritionalValues();
        nutritionalValues.values = (float[]) this.values.clone();
        return nutritionalValues;
    }

    public void setMacros(float f, float f2, float f3, float f4) {
        float[] fArr = this.values;
        fArr[0] = f;
        fArr[9] = f2;
        fArr[12] = f3;
        fArr[1] = f4;
    }

    public float getCarbohydrate() {
        return this.values[9];
    }

    public float getFat() {
        return this.values[1];
    }

    public float getProtein() {
        return this.values[12];
    }

    public static float[] valuesFromMfpNutritionalContents(MfpNutritionalContents mfpNutritionalContents, float f) {
        float[] fArr = new float[20];
        fArr[0] = mfpNutritionalContents.getCalories().floatValue() * f;
        fArr[1] = mfpNutritionalContents.getFat().floatValue() * f;
        fArr[2] = mfpNutritionalContents.getSaturatedFat().floatValue() * f;
        fArr[3] = mfpNutritionalContents.getPolyunsaturatedFat().floatValue() * f;
        fArr[4] = mfpNutritionalContents.getMonounsaturatedFat().floatValue() * f;
        fArr[5] = mfpNutritionalContents.getTransFat().floatValue() * f;
        fArr[6] = mfpNutritionalContents.getCholesterol().floatValue() * f;
        fArr[7] = mfpNutritionalContents.getSodium().floatValue() * f;
        fArr[8] = mfpNutritionalContents.getPotassium().floatValue() * f;
        fArr[9] = mfpNutritionalContents.getCarbohydrates().floatValue() * f;
        fArr[10] = mfpNutritionalContents.getFiber().floatValue() * f;
        fArr[11] = mfpNutritionalContents.getSugar().floatValue() * f;
        fArr[17] = mfpNutritionalContents.getAddedSugars().floatValue() * f;
        fArr[19] = mfpNutritionalContents.getSugarAlcohols().floatValue() * f;
        fArr[12] = mfpNutritionalContents.getProtein().floatValue() * f;
        fArr[13] = mfpNutritionalContents.getVitaminA().floatValue() * f;
        fArr[14] = mfpNutritionalContents.getVitaminC().floatValue() * f;
        fArr[18] = mfpNutritionalContents.getVitaminD().floatValue() * f;
        fArr[15] = mfpNutritionalContents.getCalcium().floatValue() * f;
        fArr[16] = mfpNutritionalContents.getIron().floatValue() * f;
        return fArr;
    }

    public static float[] valuesWithDefault(MfpNutritionalContents mfpNutritionalContents, float f) {
        float[] fArr = new float[20];
        fArr[0] = defaultValueWhenEmpty(mfpNutritionalContents.getCalories().floatValue(), f);
        fArr[1] = defaultValueWhenEmpty(mfpNutritionalContents.getFat().floatValue(), f);
        fArr[2] = defaultValueWhenEmpty(mfpNutritionalContents.getSaturatedFat().floatValue(), f);
        fArr[3] = defaultValueWhenEmpty(mfpNutritionalContents.getPolyunsaturatedFat().floatValue(), f);
        fArr[4] = defaultValueWhenEmpty(mfpNutritionalContents.getMonounsaturatedFat().floatValue(), f);
        fArr[5] = defaultValueWhenEmpty(mfpNutritionalContents.getTransFat().floatValue(), f);
        fArr[6] = defaultValueWhenEmpty(mfpNutritionalContents.getCholesterol().floatValue(), f);
        fArr[7] = defaultValueWhenEmpty(mfpNutritionalContents.getSodium().floatValue(), f);
        fArr[8] = defaultValueWhenEmpty(mfpNutritionalContents.getPotassium().floatValue(), f);
        fArr[9] = defaultValueWhenEmpty(mfpNutritionalContents.getCarbohydrates().floatValue(), f);
        fArr[10] = defaultValueWhenEmpty(mfpNutritionalContents.getFiber().floatValue(), f);
        fArr[11] = defaultValueWhenEmpty(mfpNutritionalContents.getSugar().floatValue(), f);
        fArr[17] = defaultValueWhenEmpty(mfpNutritionalContents.getAddedSugars().floatValue(), f);
        fArr[19] = defaultValueWhenEmpty(mfpNutritionalContents.getSugarAlcohols().floatValue(), f);
        fArr[12] = defaultValueWhenEmpty(mfpNutritionalContents.getProtein().floatValue(), f);
        fArr[13] = defaultValueWhenEmpty(mfpNutritionalContents.getVitaminA().floatValue(), f);
        fArr[14] = defaultValueWhenEmpty(mfpNutritionalContents.getVitaminC().floatValue(), f);
        fArr[18] = defaultValueWhenEmpty(mfpNutritionalContents.getVitaminD().floatValue(), f);
        fArr[15] = defaultValueWhenEmpty(mfpNutritionalContents.getCalcium().floatValue(), f);
        fArr[16] = defaultValueWhenEmpty(mfpNutritionalContents.getIron().floatValue(), f);
        return fArr;
    }

    public static NutritionalValues fromNutritionalContents(MfpNutritionalContents mfpNutritionalContents, float f) {
        return new NutritionalValues(valuesFromMfpNutritionalContents(mfpNutritionalContents, f));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloatArray(this.values);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NutritionalValues)) {
            return false;
        }
        return Arrays.equals(this.values, ((NutritionalValues) obj).values);
    }

    public int hashCode() {
        return 620 + Arrays.hashCode(this.values);
    }
}
