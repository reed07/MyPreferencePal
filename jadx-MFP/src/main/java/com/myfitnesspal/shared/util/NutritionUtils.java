package com.myfitnesspal.shared.util;

import android.content.Context;
import android.support.annotation.StringRes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class NutritionUtils {
    public static final int CALORIE_CARB_MULTIPLIER = 4;
    public static final int CALORIE_FAT_MULTIPLIER = 9;
    public static final int CALORIE_PROTEIN_MULTIPLIER = 4;
    public static final int CARB_EXERCISE_FACTOR = 4;
    public static final int FAT_EXERCISE_FACTOR = 9;
    private static final int KJ_CARB_MULTIPLIER = 16;
    private static final int KJ_FAT_MULTIPLIER = 37;
    private static final int KJ_PROTEIN_MULTIPLIER = 17;
    public static final int MAX_SATURATED_FAT_PERCENT = 10;
    public static final int MAX_SUGAR_PERCENT = 15;
    public static final int PROTEIN_EXERCISE_FACTOR = 4;
    public static final int SATURATED_FAT_EXERCISE_FACTOR = 9;
    public static final double SATURATED_FAT_MULTIPLIER = 0.33d;
    public static final double SUGAR_CARB_MULTIPLIER = 0.3d;
    public static final int SUGAR_EXERCISE_FACTOR = 4;

    public static boolean areFoodValuesInRange(float f, float f2, float f3, float f4) {
        return f > BitmapDescriptorFactory.HUE_RED && f <= 99999.0f && f2 <= 9999.0f && f4 <= 9999.0f && f3 <= 9999.0f;
    }

    public static float caloriesToGramsCarbs(float f) {
        return f / 4.0f;
    }

    public static float caloriesToGramsFat(float f) {
        return f / 9.0f;
    }

    public static float caloriesToGramsProtein(float f) {
        return f / 4.0f;
    }

    public static float gramsCarbsToCalories(float f) {
        return checkForDefaultValue(f) * 4.0f;
    }

    public static float gramsProteinToCalories(float f) {
        return checkForDefaultValue(f) * 4.0f;
    }

    public static float gramsFatToCalories(float f) {
        return checkForDefaultValue(f) * 9.0f;
    }

    public static float getCaloriesForMacros(float f, float f2, float f3) {
        return (checkForDefaultValue(f) * 4.0f) + (checkForDefaultValue(f2) * 9.0f) + (checkForDefaultValue(f3) * 4.0f);
    }

    private static float checkForDefaultValue(float f) {
        return ((double) f) == MfpNutritionalContents.DEFAULT_VALUE.doubleValue() ? BitmapDescriptorFactory.HUE_RED : f;
    }

    public static float getTotalNutrientInFoodEntries(Session session, ArrayList<FoodEntry> arrayList, int i) {
        float f = BitmapDescriptorFactory.HUE_RED;
        if (i == -1) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            f += getNutritionValueFromFoodEntry(session, (FoodEntry) it.next(), i);
        }
        return f;
    }

    public static float[] getPercentagesForMacroValues(MacroValues macroValues) {
        return new float[]{(float) macroValues.getCarbsPercentage(), (float) macroValues.getFatPercentage(), (float) macroValues.getProteinPercentage()};
    }

    public static float getNutritionValueFromFoodEntry(Session session, FoodEntry foodEntry, int i) {
        float amountOfNutrientIndex = foodEntry.amountOfNutrientIndex(i);
        if (i == 0) {
            amountOfNutrientIndex = (float) LocalizedEnergy.fromCalories((double) foodEntry.amountOfNutrientIndex(i)).getValue(Energy.fromInt(session.getUser().getEnergyUnitPreference()));
        }
        return (amountOfNutrientIndex >= BitmapDescriptorFactory.HUE_RED || i == 0) ? amountOfNutrientIndex : BitmapDescriptorFactory.HUE_RED;
    }

    public static String getNutritionalMacrosDetails(Context context, MfpNutritionalContents mfpNutritionalContents) {
        return getNutritionalMacrosDetails(context, mfpNutritionalContents, 1.0d);
    }

    public static String getNutritionalMacrosDetails(Context context, NutritionalValues nutritionalValues, double d) {
        return getNutritionalMacrosDetails(context, MfpNutritionalContents.fromNutritionalValuesArray(nutritionalValues.getValues()), d);
    }

    public static String getNutritionalMacrosDetails(Context context, MfpNutritionalContents mfpNutritionalContents, double d) {
        if (mfpNutritionalContents == null) {
            return "";
        }
        return getNutritionalMacrosDetails(context, mfpNutritionalContents.getCarbohydrates().doubleValue(), mfpNutritionalContents.getFat().doubleValue(), mfpNutritionalContents.getProtein().doubleValue(), d, ", ");
    }

    public static String getNutritionalMacrosDetails(Context context, double d, double d2, double d3, double d4, String str) {
        if (d4 == 0.0d) {
            d4 = 1.0d;
        }
        ArrayList arrayList = new ArrayList();
        String formattedMacro = getFormattedMacro(context, R.string.carbs, d / d4);
        if (Strings.notEmpty(formattedMacro)) {
            arrayList.add(formattedMacro);
        }
        String formattedMacro2 = getFormattedMacro(context, R.string.fat, d2 / d4);
        if (Strings.notEmpty(formattedMacro2)) {
            arrayList.add(formattedMacro2);
        }
        String formattedMacro3 = getFormattedMacro(context, R.string.protein, d3 / d4);
        if (Strings.notEmpty(formattedMacro3)) {
            arrayList.add(formattedMacro3);
        }
        return Strings.join(str, (Collection<T>) arrayList);
    }

    private static String getFormattedMacro(Context context, @StringRes int i, double d) {
        String string = context.getString(R.string.gram_abbreviation);
        String str = "";
        if (d < 0.0d) {
            return str;
        }
        return String.format("%s %s%s", new Object[]{context.getString(i), Strings.initStringWithFormattedFloat((float) d, 0), string});
    }
}
