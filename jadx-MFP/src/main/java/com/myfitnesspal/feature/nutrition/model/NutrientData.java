package com.myfitnesspal.feature.nutrition.model;

import android.content.Context;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;

public class NutrientData {
    private static final String INDENT = "   ";
    private static final String NA = "N/A";
    private static final String NO_INDENT = "";
    boolean isPremium;
    NutrientDetails[] nutrientDetailsArray;

    public NutrientData(NutrientDetails[] nutrientDetailsArr, boolean z) {
        this.nutrientDetailsArray = nutrientDetailsArr;
        this.isPremium = z;
    }

    public boolean getIsSubordinateNutrient(int i) {
        return getNutrientDetails(i).isSubordinateNutrientIndex();
    }

    public int getTotalFor(int i) {
        return Math.round(getNutrientDetails(i).getNutrientsConsumed());
    }

    public int getGoalFor(int i) {
        return Math.round(getNutrientDetails(i).getNutritionalGoals());
    }

    public int getRemainingFor(int i) {
        return getGoalFor(i) - getTotalFor(i);
    }

    public String getFormattedLabel(Context context, int i, boolean z) {
        String string = context.getString(NutritionalValues.simplifiedLabelForNutrientIndex(i, z));
        if (this.isPremium) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getNutrientDetails(i).isSubordinateNutrientIndex() ? INDENT : "");
        sb.append(string);
        return sb.toString();
    }

    private String percent(int i) {
        return getNutrientDetails(i).isPercentage() ? "%" : "";
    }

    public NutrientDetails getNutrientDetails(int i) {
        return this.nutrientDetailsArray[i];
    }

    public String getFormattedTotal(int i) {
        if (this.isPremium) {
            return localeStringFromInt(getTotalFor(i));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(localeStringFromInt(getTotalFor(i)));
        sb.append(percent(i));
        return sb.toString();
    }

    public String getFormattedGoal(int i) {
        String str;
        StringBuilder sb;
        String formattedGoalNoUnits = getFormattedGoalNoUnits(i);
        if (Strings.equals(formattedGoalNoUnits, NA)) {
            return NA;
        }
        if (this.isPremium) {
            sb = new StringBuilder();
            sb.append(formattedGoalNoUnits);
            str = getUnit(i);
        } else {
            sb = new StringBuilder();
            sb.append(formattedGoalNoUnits);
            str = percent(i);
        }
        sb.append(str);
        return sb.toString();
    }

    public String getFormattedGoalNoUnits(int i) {
        return localeStringFromInt(getGoalFor(i));
    }

    public String getFormattedRemaining(int i) {
        if (getFormattedGoal(i).trim().equalsIgnoreCase(NA)) {
            return NA;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(localeStringFromInt(getRemainingFor(i)));
        sb.append(getUnit(i));
        return sb.toString();
    }

    private String getUnit(int i) {
        return getNutrientDetails(i).getUnits();
    }

    private String localeStringFromInt(int i) {
        return NumberUtils.localeStringFromInt(i);
    }
}
