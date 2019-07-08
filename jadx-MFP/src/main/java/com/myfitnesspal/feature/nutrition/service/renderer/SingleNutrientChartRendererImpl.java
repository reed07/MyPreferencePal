package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.ProgressReport;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SingleNutrientChartRendererImpl extends CaloriesOrSingleNutrientsChartRendererBase {
    private NutritionalValues nutritionalValues = new NutritionalValues();

    /* access modifiers changed from: protected */
    public String getChartType() {
        return Types.SINGLE_NUTRIENT;
    }

    public SingleNutrientChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public String getUnitString(int i) {
        return i == -1 ? "" : this.context.getString(this.nutritionalValues.getUnits(i));
    }

    /* access modifiers changed from: protected */
    public CalorieValues getWeeklyValues(ProgressReport progressReport) {
        return progressReport.getWeeklyNutrientValues();
    }

    /* access modifiers changed from: protected */
    public List<CalorieValues> getDailyValues(ProgressReport progressReport) {
        return progressReport.getResultMealNutrientValuesList();
    }

    /* access modifiers changed from: protected */
    public NutrientEntry getNutrientEntry(String str, float f, float f2, String str2) {
        return new NutrientEntry(str, NumberUtils.localeStringFromFloat((float) Math.round(f)), NumberUtils.localeStringFromFloat((float) Math.round(f2)), str2);
    }

    /* access modifiers changed from: protected */
    public float getTotalNutrientsAndSetMealNutrients(DiaryDay diaryDay, float[] fArr, List<String> list, int i) {
        Map foodEntriesByMealName = diaryDay.getFoodEntriesByMealName();
        for (int i2 = 0; i2 < CollectionUtils.size((Collection<?>) list); i2++) {
            ArrayList arrayList = (ArrayList) foodEntriesByMealName.get(list.get(i2));
            if (!CollectionUtils.isEmpty((Collection<?>) arrayList)) {
                float totalNutrientInFoodEntries = NutritionUtils.getTotalNutrientInFoodEntries(getSession(), arrayList, i);
                if (totalNutrientInFoodEntries < BitmapDescriptorFactory.HUE_RED) {
                    totalNutrientInFoodEntries = BitmapDescriptorFactory.HUE_RED;
                }
                fArr[i2] = totalNutrientInFoodEntries;
            }
        }
        return NutritionUtils.getTotalNutrientInFoodEntries(getSession(), diaryDay.getFoodEntries(), i);
    }
}
