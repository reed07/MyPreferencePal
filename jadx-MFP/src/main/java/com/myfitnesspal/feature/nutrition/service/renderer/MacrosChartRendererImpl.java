package com.myfitnesspal.feature.nutrition.service.renderer;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.CaloriesAndMacroValues;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.myfitnesspal.feature.nutrition.service.ProgressReport;
import com.myfitnesspal.feature.nutrition.ui.view.CustomBarChart;
import com.myfitnesspal.feature.nutrition.ui.view.CustomPieChart;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.LegendData;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MacrosChartRendererImpl extends GraphAndPieChartRendererBase {
    private static final int EXTRA_TOP_PADDING = 100;
    private static final int MINIMUM_CALORIE_VALUE_FOR_Y_INCREMENT = 1000;

    /* access modifiers changed from: protected */
    public String getChartType() {
        return Types.MACROS;
    }

    public MacrosChartRendererImpl(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public Function2<DiaryDay, MfpDailyGoal> getDailyRenderChartLogic(final ViewGroup viewGroup, String str, int i) {
        return new Function2<DiaryDay, MfpDailyGoal>() {
            public void execute(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) throws RuntimeException {
                MacrosChartRendererImpl.this.hideSpinnerContainer(viewGroup);
                float[] access$000 = MacrosChartRendererImpl.this.getConsumedMacrosPercentageFromDiaryDay(diaryDay);
                List access$100 = MacrosChartRendererImpl.this.getLegendData(diaryDay, mfpDailyGoal, access$000);
                new CustomPieChart(MacrosChartRendererImpl.this.activity, new CalorieValues(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, access$000), Types.MACROS, access$100, viewGroup);
            }
        };
    }

    /* access modifiers changed from: protected */
    public Function1<ProgressReport> getWeeklyRenderChartLogic(final ViewGroup viewGroup, final Date date, int i) {
        return new Function1<ProgressReport>() {
            public void execute(ProgressReport progressReport) throws RuntimeException {
                MacrosChartRendererImpl.this.hideSpinnerContainer(viewGroup);
                String[] labelsForDays = MacrosChartRendererImpl.this.getLabelsForDays(date);
                CaloriesAndMacroValues averageCaloriesAndMacroValues = progressReport.getAverageCaloriesAndMacroValues();
                MacroValues goalMacroValues = progressReport.getGoalMacroValues();
                float f = BitmapDescriptorFactory.HUE_RED;
                for (CaloriesAndMacroValues caloriesFromDiary : progressReport.getResultMacroValuesList()) {
                    float caloriesFromDiary2 = caloriesFromDiary.getCaloriesFromDiary();
                    if (caloriesFromDiary2 > f) {
                        f = caloriesFromDiary2;
                    }
                }
                double yAxisIncrement = MacrosChartRendererImpl.this.getYAxisIncrement(Math.max(f, 1000.0f));
                new CustomBarChart(MacrosChartRendererImpl.this.activity, labelsForDays, progressReport.getResultMacroValuesList(), averageCaloriesAndMacroValues, MacrosChartRendererImpl.this.getLegendData(averageCaloriesAndMacroValues, NutritionUtils.getPercentagesForMacroValues(averageCaloriesAndMacroValues.getMacroValues()), NutritionUtils.getPercentagesForMacroValues(goalMacroValues)), yAxisIncrement, 100.0d + (f == BitmapDescriptorFactory.HUE_RED ? 1000.0d + yAxisIncrement : yAxisIncrement), viewGroup);
            }
        };
    }

    /* access modifiers changed from: private */
    public List<LegendData> getLegendData(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, float[] fArr) {
        return getLegendData(getCalorieAndMacroValuesFromDiaryDay(diaryDay), fArr, getGoalMacrosPercentage(diaryDay, mfpDailyGoal));
    }

    /* access modifiers changed from: private */
    public List<LegendData> getLegendData(CaloriesAndMacroValues caloriesAndMacroValues, float[] fArr, float[] fArr2) {
        int i;
        float f;
        int i2;
        ArrayList arrayList = new ArrayList(3);
        for (int i3 = 0; i3 < 3; i3++) {
            switch (i3) {
                case 0:
                    i2 = R.string.carbohydrates;
                    f = caloriesAndMacroValues.getMacroValues().getCarbsValue();
                    i = getColor(R.color.pie_legend_carbs_label);
                    break;
                case 1:
                    i2 = R.string.fat;
                    f = caloriesAndMacroValues.getMacroValues().getFatValue();
                    i = getColor(R.color.pie_legend_fat_label);
                    break;
                case 2:
                    i2 = R.string.protein;
                    f = caloriesAndMacroValues.getMacroValues().getProteinValue();
                    i = getColor(R.color.pie_legend_protein_label);
                    break;
                default:
                    i2 = 0;
                    f = BitmapDescriptorFactory.HUE_RED;
                    i = 0;
                    break;
            }
            int round = Math.round(fArr[i3]);
            int round2 = Math.round(fArr2[i3]);
            LegendData legendData = new LegendData(this.context.getString(i2), i, round, this.context.getString(R.string.grams_in_brackets, new Object[]{NumberUtils.localeStringFromInt(Math.round(f))}), round2);
            arrayList.add(legendData);
        }
        return arrayList;
    }

    private CaloriesAndMacroValues getCalorieAndMacroValuesFromDiaryDay(DiaryDay diaryDay) {
        return new CaloriesAndMacroValues(MacroValues.fromDiaryDay(diaryDay), diaryDay.caloriesConsumed(false));
    }

    /* access modifiers changed from: private */
    public float[] getConsumedMacrosPercentageFromDiaryDay(DiaryDay diaryDay) {
        return NutritionUtils.getPercentagesForMacroValues(MacroValues.fromDiaryDay(diaryDay));
    }

    private float[] getGoalMacrosPercentage(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) {
        return NutritionUtils.getPercentagesForMacroValues(MacroValues.fromDailyGoal(getNutritionalGoalsUtil(), diaryDay, mfpDailyGoal));
    }
}
