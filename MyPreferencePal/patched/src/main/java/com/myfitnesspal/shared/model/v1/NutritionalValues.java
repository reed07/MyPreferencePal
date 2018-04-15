package com.myfitnesspal.shared.model.v1;
import lanchon.dexpatcher.annotation.*;
import android.os.*;
import com.myfitnesspal.shared.model.v2.*;
import android.content.*;
import android.preference.PreferenceManager;
import com.myfitnesspal.shared.service.localsettings.*;

import dagger.*;
import com.myfitnesspal.feature.goals.service.*;
import com.uacf.core.preferences.KeyedSharedPreferences;

import java.util.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class NutritionalValues implements Parcelable, Cloneable
{
    /*
    private static final int[] ADJUSTABLE_NUTRIENTS;

    public static final Parcelable$Creator<NutritionalValues> CREATOR;

    public static final int NO_NUTRIENT = -1;
    public static final int NUTRIENT_CALCIUM = 15;
    public static final int NUTRIENT_CALORIES = 0;
    public static final int NUTRIENT_CARBOHYDRATES = 9;
    public static final int NUTRIENT_CHOLESTEROL = 6;
    public static final int NUTRIENT_FAT = 1;
    public static final int NUTRIENT_FIBER = 10;
    public static final int NUTRIENT_FIRST = 0;
    private static final String[] NUTRIENT_INDEX_IDENTIFIERS;
    private static final boolean[] NUTRIENT_INDEX_IS_PERCENTAGE;
    private static final int[] NUTRIENT_INDEX_UNIT_LABEL;
    public static final int NUTRIENT_IRON = 16;
    private static final int[] NUTRIENT_LABELS;
    public static final int NUTRIENT_MAX = 17;
    public static final int NUTRIENT_MONO_UNSATURATED_FAT = 4;
    public static final int NUTRIENT_POLY_UNSATURATED_FAT = 3;
    public static final int NUTRIENT_POTASSIUM = 8;
    public static final int NUTRIENT_PROTEIN = 12;
    public static final int NUTRIENT_SATURATED_FAT = 2;
    public static final int NUTRIENT_SODIUM = 7;
    public static final int NUTRIENT_SUGAR = 11;
    public static final int NUTRIENT_TRANS_FAT = 5;
    public static final int NUTRIENT_VITAMIN_A = 13;
    public static final int NUTRIENT_VITAMIN_C = 14;
    private static final boolean[] SUBORDINATE_NUTRIENT_INDEX;
    */
    private float[] values;

    //The following were automatically generated so that the class was still implementing its interfaces

    protected NutritionalValues(Parcel in) {
        values = in.createFloatArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloatArray(values);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static class PlaceHolderCreator implements Creator<NutritionalValues>
    {
        @Override
        public NutritionalValues createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public NutritionalValues[] newArray(int size) {
            return new NutritionalValues[0];
        }
    }

    public static final Creator<NutritionalValues> CREATOR = new PlaceHolderCreator();

    /*
        static {
            NUTRIENT_LABELS = new int[] { 2131231099, 2131233619, 2131233357, 2131233033, 2131232771, 2131233644, 2131231158, 2131233512, 2131233038, 2131231119, 2131232282, 2131233563, 2131233183, 2131233802, 2131233803, 2131231087, 2131232482 };
            NUTRIENT_INDEX_IDENTIFIERS = new String[] { "calories", "fat", "saturated_fat", "polyunsaturated_fat", "monounsaturated_fat", "trans_fat", "cholesterol", "sodium", "potassium", "carbs", "fiber", "sugar", "protein", "vitamin_a", "vitamin_c", "calcium", "iron" };
            SUBORDINATE_NUTRIENT_INDEX = new boolean[] { false, false, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false };
            NUTRIENT_INDEX_IS_PERCENTAGE = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true };
            NUTRIENT_INDEX_UNIT_LABEL = new int[] { 2131231603, 2131232378, 2131232378, 2131232378, 2131232378, 2131232378, 2131232754, 2131232754, 2131232754, 2131232378, 2131232378, 2131232378, 2131232378, 2131232372, 2131232372, 2131232372, 2131232372 };
            ADJUSTABLE_NUTRIENTS = new int[] { 1, 9, 12, 10, 2, 11 };
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<NutritionalValues>() {
                public NutritionalValues createFromParcel(final Parcel parcel) {
                    return new NutritionalValues(parcel, null);
                }

                public NutritionalValues[] newArray(final int n) {
                    return new NutritionalValues[n];
                }
            };
        }

        public NutritionalValues() {
            this.values = new float[17];
        }

        private NutritionalValues(final Parcel parcel) {
            this.values = parcel.createFloatArray();
        }

        public NutritionalValues(final float[] values) {
            this.values = values;
        }

        private void adjustCaloriesBasedOnDailyGoal(final MfpDailyGoal mfpDailyGoal, final float n) {
            final int[] array2;
            final int[] array = array2 = new int[5];
            array2[0] = 9;
            array2[1] = 11;
            array2[2] = 12;
            array2[3] = 1;
            array2[4] = 2;
            final float[] values = this.values;
            final int n2 = array[0];
            values[n2] += mfpDailyGoal.getExerciseCarbohydratesPercentage() * n / 400.0f;
            final float[] values2 = this.values;
            final int n3 = array[1];
            values2[n3] += mfpDailyGoal.getExerciseSugarPercentage() * n / 400.0f;
            final float[] values3 = this.values;
            final int n4 = array[2];
            values3[n4] += mfpDailyGoal.getExerciseProteinPercentage() * n / 400.0f;
            final float[] values4 = this.values;
            final int n5 = array[3];
            values4[n5] += mfpDailyGoal.getExerciseFatPercentage() * n / 900.0f;
            final float[] values5 = this.values;
            final int n6 = array[4];
            values5[n6] += mfpDailyGoal.getExerciseSaturatedFatPercentage() * n / 900.0f;
        }

        private void adjustForCaloriesEarned(float n) {
            if (this.calories() >= 100.0f) {
                n /= this.calories();
                for (int i = 0; i < NutritionalValues.ADJUSTABLE_NUTRIENTS.length; ++i) {
                    final float[] values = this.values;
                    final int n2 = NutritionalValues.ADJUSTABLE_NUTRIENTS[i];
                    values[n2] *= 1.0f + n;
                }
            }
        }

        public static NutritionalValues fromNutritionalContents(final MfpNutritionalContents mfpNutritionalContents, final float n) {
            return new NutritionalValues(valuesFromMfpNutritionalContents(mfpNutritionalContents, n));
        }

        public static String getIdentifierForNutrientIndex(final int n) {
            return NutritionalValues.NUTRIENT_INDEX_IDENTIFIERS[n];
        }

        public static boolean isMacroOrCalorieIndex(final int n) {
            boolean b2;
            final boolean b = b2 = true;
            if (n != 0) {
                b2 = b;
                if (n != 9) {
                    b2 = b;
                    if (n != 1) {
                        b2 = (n == 12 && b);
                    }
                }
            }
            return b2;
        }

        public static int simplifiedLabelForNutrientIndex(int n, final boolean b) {
            if (n == 0) {
                if (b) {
                    n = 2131231099;
                }
                else {
                    n = 2131232506;
                }
            }
            else if (n >= 0 && n <= NutritionalValues.NUTRIENT_LABELS.length - 1) {
                n = NutritionalValues.NUTRIENT_LABELS[n];
            }
            else {
                n = 2131231657;
            }
            return n;
        }

        public static String simplifiedLabelForNutrientIndex(final Context context, final int n, final boolean b) {
            return context.getString(simplifiedLabelForNutrientIndex(n, b));
        }

        public static String unitForNutrientIndex(final Context context, final int n) {
            return context.getString(NutritionalValues.NUTRIENT_INDEX_UNIT_LABEL[n]);
        }

        public static float[] valuesFromMfpNutritionalContents(final MfpNutritionalContents mfpNutritionalContents, final float n) {
            return new float[] { (float)(Object)mfpNutritionalContents.getCalories() * n, (float)(Object)mfpNutritionalContents.getFat() * n, (float)(Object)mfpNutritionalContents.getSaturatedFat() * n, (float)(Object)mfpNutritionalContents.getPolyunsaturatedFat() * n, (float)(Object)mfpNutritionalContents.getMonounsaturatedFat() * n, (float)(Object)mfpNutritionalContents.getTransFat() * n, (float)(Object)mfpNutritionalContents.getCholesterol() * n, (float)(Object)mfpNutritionalContents.getSodium() * n, (float)(Object)mfpNutritionalContents.getPotassium() * n, (float)(Object)mfpNutritionalContents.getCarbohydrates() * n, (float)(Object)mfpNutritionalContents.getFiber() * n, (float)(Object)mfpNutritionalContents.getSugar() * n, (float)(Object)mfpNutritionalContents.getProtein() * n, (float)(Object)mfpNutritionalContents.getVitaminA() * n, (float)(Object)mfpNutritionalContents.getVitaminC() * n, (float)(Object)mfpNutritionalContents.getCalcium() * n, (float)(Object)mfpNutritionalContents.getIron() * n };
        }

        public void addNutritionalValues(final NutritionalValues nutritionalValues, final float n) {
            this.clampNegativesToZero();
            for (int i = 0; i < 17; ++i) {
                final float[] values = this.values;
                values[i] += nutritionalValues.values[i] * n;
            }
        }

        public NutritionalValues adjustForCaloriesEarnedBasedOnPreference(final Lazy<NutrientGoalService> lazy, final float n, final Date date) {
            if (lazy == null) {
                this.adjustForCaloriesEarned(n);
            }
            else {
                final MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService)lazy.get()).getDailyGoalForDayOfWeekSync(date);
                if (dailyGoalForDayOfWeekSync == null) {
                    this.adjustForCaloriesEarned(n);
                }
                else {
                    this.setMacros(dailyGoalForDayOfWeekSync.getEnergy().getValue(), dailyGoalForDayOfWeekSync.getCarbohydrates(), dailyGoalForDayOfWeekSync.getProtein(), dailyGoalForDayOfWeekSync.getFat());
                    if (dailyGoalForDayOfWeekSync.isAssignExerciseEnergyOn()) {
                        this.adjustCaloriesBasedOnDailyGoal(dailyGoalForDayOfWeekSync, n);
                    }
                }
            }
            return this;
        }

        public float calories() {
            return this.values[0];
        }

        public void clampNegativesToZero() {
            for (int i = 0; i < 17; ++i) {
                if (this.values[i] < 0.0f) {
                    this.values[i] = 0.0f;
                }
            }
        }

        public Object clone() {
            final NutritionalValues nutritionalValues = new NutritionalValues();
            nutritionalValues.values = this.values.clone();
            return nutritionalValues;
        }

        public int describeContents() {
            return 0;
        }

        @Override
        public boolean equals(final Object o) {
            return o == this || (o instanceof NutritionalValues && Arrays.equals(this.values, ((NutritionalValues)o).values));
        }

        public float getCaloriesValue(float n) {
            if ((n *= this.values[0]) < 0.0) {
                n = -1.0f;
            }
            return n;
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

        public int getUnits(final int n) {
            return NutritionalValues.NUTRIENT_INDEX_UNIT_LABEL[n];
        }
        */
    @DexReplace
    public float[] getValues() {
        float[] result = this.values.clone();
        KeyedSharedPreferences prefs = LocalSettingsServiceImpl.prefs2;
        if (prefs.getBoolean("show_net_carbs", false))
        {
            if (prefs.getBoolean("show_smart_carbs", false))
            {
                result[9] = Math.min(result[9], Math.max(result[9] - result[10], (result[0] - 9.0f * result[1] - 4.0f*result[12])/ 4.0f));
            }
            else
            {
                result[9] = Math.min(result[9], result[9] - result[10]);
            }
        }
        return result;
    }
    /*
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.values) + 527;
    }
    
    public NutritionalValues initAsBlank() {
        for (int i = 0; i < 17; ++i) {
            this.values[i] = -1.0f;
        }
        return this;
    }
    
    public boolean isSubordinateNutrientIndex(final int n) {
        return NutritionalValues.SUBORDINATE_NUTRIENT_INDEX[n];
    }
    
    public boolean nutrientIndexIsPercentage(final int n) {
        return NutritionalValues.NUTRIENT_INDEX_IS_PERCENTAGE[n];
    }
    
    public void resetToZero() {
        for (int i = 0; i < 17; ++i) {
            this.values[i] = 0.0f;
        }
    }
    
    public void setMacros(final float n, final float n2, final float n3, final float n4) {
        this.values[0] = n;
        this.values[9] = n2;
        this.values[12] = n3;
        this.values[1] = n4;
    }
    
    public void setNutrientIndex(final int n, final float n2) {
        this.values[n] = n2;
    }
    
    public void setValues(final float[] values) {
        this.values = values;
    }
    
    public float valueForNutrientIndex(final int n) {
        return this.values[n];
    }
    
    public boolean valueIsNullForNutrientIndex(final int n) {
        return this.values[n] < 0.0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeFloatArray(this.values);
    }
    */
}
