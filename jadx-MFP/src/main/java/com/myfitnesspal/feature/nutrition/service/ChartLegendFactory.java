package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import android.view.ViewGroup;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.CustomCalorieGoalLegend;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.ui.view.CaloriePieLegend;
import com.myfitnesspal.feature.nutrition.ui.view.CustomPieLegend;
import com.myfitnesspal.feature.nutrition.ui.view.MacrosPieLegend;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.uacf.core.util.Strings;
import java.util.List;

public class ChartLegendFactory {
    public CustomPieLegend getPieChartRenderer(Context context, String str, int i, boolean z, NutrientEntry nutrientEntry, CalorieValues calorieValues, List<CustomCalorieGoalLegend> list, int i2, ViewGroup viewGroup) {
        String str2 = str;
        if (Strings.equals(str, Types.MACROS)) {
            Context context2 = context;
            boolean z2 = z;
            return new MacrosPieLegend(context, z, viewGroup);
        }
        CaloriePieLegend caloriePieLegend = new CaloriePieLegend(context, str, i, z, nutrientEntry, calorieValues, list, i2, viewGroup);
        return caloriePieLegend;
    }
}
