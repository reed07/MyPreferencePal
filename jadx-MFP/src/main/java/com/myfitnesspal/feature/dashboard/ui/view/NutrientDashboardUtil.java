package com.myfitnesspal.feature.dashboard.ui.view;

import com.myfitnesspal.feature.nutrition.model.Nutrient;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NutrientDashboardUtil {
    public static final List<Nutrient> DEFAULT_CUSTOM_GOALS = Collections.unmodifiableList(Arrays.asList(new Nutrient[]{Nutrient.SaturatedFat, Nutrient.Cholesterol, Nutrient.Fat}));
    public static final int REQUIRED_CUSTOM_GOAL_COUNT = 3;

    public static List<Nutrient> filterNutrientsForDisplay(List<Nutrient> list) {
        return (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, Nutrient>() {
            public Boolean execute(Nutrient nutrient) {
                return Boolean.valueOf(nutrient != Nutrient.Energy);
            }
        });
    }

    public static ArrayList<String> filterNutrientStringsForDisplay(List<String> list) {
        return (ArrayList) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, String>() {
            public Boolean execute(String str) {
                return Boolean.valueOf(!Strings.equals(str, Nutrient.Energy.getApiKey()));
            }
        });
    }

    public static List<Nutrient> filterNutrientsForDisplay(MfpGoalDisplay mfpGoalDisplay) {
        return filterNutrientsForDisplay(Nutrient.fromApiKeys(mfpGoalDisplay.getNutrients()));
    }
}
