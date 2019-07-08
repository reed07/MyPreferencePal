package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface NutritionGraphService {
    void renderDailyChart(Date date, Function2<DiaryDay, MfpDailyGoal> function2);

    void renderFoodList(Date date, int i, boolean z, Function1<Map<FoodListItem, Integer>> function1);

    void renderNutritionSummary(Date date, NutritionDetailsDelegate nutritionDetailsDelegate, boolean z, Function1<List<NutrientEntry>> function1);

    void renderWeeklyChart(Context context, Date date, String str, int i, Function1<ProgressReport> function1);
}
