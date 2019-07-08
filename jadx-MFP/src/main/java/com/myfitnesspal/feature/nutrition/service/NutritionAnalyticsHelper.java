package com.myfitnesspal.feature.nutrition.service;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/nutrition/service/NutritionAnalyticsHelper;", "", "reportFoodAnalysisViewed", "", "listType", "", "reportViewMore", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: NutritionAnalyticsHelper.kt */
public interface NutritionAnalyticsHelper {
    void reportFoodAnalysisViewed(@NotNull String str);

    void reportViewMore(@NotNull String str);
}
