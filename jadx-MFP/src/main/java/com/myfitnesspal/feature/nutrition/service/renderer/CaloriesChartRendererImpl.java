package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.ProgressReport;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;

public class CaloriesChartRendererImpl extends CaloriesOrSingleNutrientsChartRendererBase {
    /* access modifiers changed from: protected */
    public String getChartType() {
        return Types.CALORIES;
    }

    /* access modifiers changed from: protected */
    public NutrientEntry getNutrientEntry(String str, float f, float f2, String str2) {
        return null;
    }

    public CaloriesChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public String getUnitString(int i) {
        return getUserEnergyService().getDisplayableShortUnitString();
    }

    /* access modifiers changed from: protected */
    public CalorieValues getWeeklyValues(ProgressReport progressReport) {
        return progressReport.getWeeklyCalorieValues();
    }

    /* access modifiers changed from: protected */
    public List<CalorieValues> getDailyValues(ProgressReport progressReport) {
        return progressReport.getResultDailyCalorieValuesList();
    }

    /* access modifiers changed from: protected */
    public float getTotalNutrientsAndSetMealNutrients(DiaryDay diaryDay, float[] fArr, List<String> list, int i) {
        for (int i2 = 0; i2 < CollectionUtils.size((Collection<?>) list); i2++) {
            fArr[i2] = (float) diaryDay.totalCaloriesForMealName((String) list.get(i2));
        }
        return NutritionUtils.getTotalNutrientInFoodEntries(getSession(), diaryDay.getFoodEntries(), 0);
    }
}
