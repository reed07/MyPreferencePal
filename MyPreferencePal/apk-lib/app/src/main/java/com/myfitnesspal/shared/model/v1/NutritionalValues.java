package com.myfitnesspal.shared.model.v1;

import android.content.Context;

import com.btothefifth.patched.R;

import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class NutritionalValues {
    private static final String[] NUTRIENT_INDEX_IDENTIFIERS = {"calories", "fat", "saturated_fat", "polyunsaturated_fat", "monounsaturated_fat", "trans_fat", "cholesterol", "sodium", "potassium", "carbs", "fiber", "sugar", "protein", "vitamin_a", "vitamin_c", "calcium", "iron", "added_sugars", "vitamin_d", "sugar_alcohols", "net_carbs"};
    private static final int[] NUTRIENT_LABELS = {R.string.calories, R.string.totalFat_nutrient, R.string.saturated_nutrient, R.string.polyunsaturated_nutrient, R.string.monounsaturated_nutrient, R.string.trans_nutrient, R.string.cholesterol_nutrient, R.string.sodium_nutrient, R.string.potassium_nutrient, R.string.carbohydrates_nutrient, R.string.fiber, R.string.sugars_nutrient, R.string.protein, R.string.vitamin_a, R.string.vitamin_c, R.string.calcium, R.string.iron, R.string.added_sugars_nutrient, R.string.vitamin_d, R.string.sugar_alcohols_nutrient, R.string.net_carbs};
    private static final boolean[] SUBORDINATE_NUTRIENT_INDEX = {false, false, true, true, true, true, false, false, false, false, true, true, false, false, false, false, false, true, false, true, true};
    private static final boolean[] NUTRIENT_INDEX_IS_PERCENTAGE = {false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, true};
    private static final int[] NUTRIENT_INDEX_UNIT_LABEL = {R.string.empty_string, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.milligram_abbreviation, R.string.milligram_abbreviation, R.string.milligram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.gram_abbreviation, R.string.goals_percent, R.string.goals_percent, R.string.goals_percent, R.string.goals_percent, R.string.gram_abbreviation, R.string.goals_percent, R.string.gram_abbreviation, R.string.gram_abbreviation};

    @DexReplace
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

    @DexReplace
    public boolean isSubordinateNutrientIndex(int i) {
        return SUBORDINATE_NUTRIENT_INDEX[i];
    }
    @DexReplace
    public boolean nutrientIndexIsPercentage(int i) {
        return NUTRIENT_INDEX_IS_PERCENTAGE[i];
    }

    @DexReplace
    public static String getIdentifierForNutrientIndex(int i) {
        return NUTRIENT_INDEX_IDENTIFIERS[i];
    }

    @DexReplace
    public static String unitForNutrientIndex(Context context, int i) {
        return context.getString(NUTRIENT_INDEX_UNIT_LABEL[i]);
    }

    @DexReplace
    public int getUnits(int i) {
        return NUTRIENT_INDEX_UNIT_LABEL[i];
    }

    @DexWrap
    public float[] getValues() {
        float[] original = getValues();
        float[] returnValues = new float[21];
        System.arraycopy(original,0,returnValues,0,20);
        returnValues[20] = calculateNetCarbs(original);
        return returnValues;
    }

    @DexReplace
    public float valueForNutrientIndex(int i) {
        return getValues()[i];
    }

    @DexAdd
    public float calculateNetCarbs()
    {
        return calculateNetCarbs(getValues());
    }

    @DexAdd
    public float calculateNetCarbs(float[] values)
    {
        float netCarbs = values[10];
        if (((double) values[11]) < 0.0d)
            netCarbs -= values[11];
        if (((double) values[19]) < 0.0d)
            netCarbs -= values[19];
        return netCarbs;
    }

    @DexReplace
    public boolean valueIsNullForNutrientIndex(int i) {
        return ((double) this.getValues()[i]) < 0.0d;
    }

    @DexWrap
    public void setNutrientIndex(int i, float f) {
        if (i < 20)
            setNutrientIndex(i,f);
    }


}
