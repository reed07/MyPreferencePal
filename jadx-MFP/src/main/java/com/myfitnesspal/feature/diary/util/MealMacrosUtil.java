package com.myfitnesspal.feature.diary.util;

import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple3;

public class MealMacrosUtil {
    public static Tuple3<Float, Float, Float> getMacroValuesBasedOnUserPreference(MealMacrosDisplayUnit mealMacrosDisplayUnit, MacroValues macroValues, boolean z) {
        float f;
        float f2;
        float f3;
        if (mealMacrosDisplayUnit == MealMacrosDisplayUnit.Gram || !z) {
            f3 = macroValues.getCarbsValue();
            f = macroValues.getFatValue();
            f2 = macroValues.getProteinValue();
        } else if (mealMacrosDisplayUnit == MealMacrosDisplayUnit.Percent) {
            f3 = (float) macroValues.getCarbsPercentage();
            f = (float) macroValues.getFatPercentage();
            f2 = (float) macroValues.getProteinPercentage();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unhandled display unit: ");
            sb.append(mealMacrosDisplayUnit);
            throw new IllegalArgumentException(sb.toString());
        }
        return Tuple.create(Float.valueOf(f3), Float.valueOf(f), Float.valueOf(f2));
    }
}
