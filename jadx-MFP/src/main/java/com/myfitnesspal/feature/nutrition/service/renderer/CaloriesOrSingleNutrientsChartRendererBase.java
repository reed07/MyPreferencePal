package com.myfitnesspal.feature.nutrition.service.renderer;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.ProgressReport;
import com.myfitnesspal.feature.nutrition.ui.view.CustomBarChart;
import com.myfitnesspal.feature.nutrition.ui.view.CustomPieChart;
import com.myfitnesspal.shared.constants.Constants.Graphs.Colors;
import com.myfitnesspal.shared.model.LegendData;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.shinobicontrols.charts.NumberRange;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class CaloriesOrSingleNutrientsChartRendererBase extends GraphAndPieChartRendererBase {
    private static final int Y_AXIS_TICK_COUNT = 4;
    /* access modifiers changed from: private */
    public MealNames mealNames = getSession().getUser().getMealNames();

    /* access modifiers changed from: private */
    public int getActualNutrientIndex(int i) {
        if (i == -1) {
            return 0;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public abstract List<CalorieValues> getDailyValues(ProgressReport progressReport);

    /* access modifiers changed from: protected */
    public abstract NutrientEntry getNutrientEntry(String str, float f, float f2, String str2);

    /* access modifiers changed from: protected */
    public abstract float getTotalNutrientsAndSetMealNutrients(DiaryDay diaryDay, float[] fArr, List<String> list, int i);

    /* access modifiers changed from: protected */
    public abstract String getUnitString(int i);

    /* access modifiers changed from: protected */
    public abstract CalorieValues getWeeklyValues(ProgressReport progressReport);

    protected CaloriesOrSingleNutrientsChartRendererBase(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        super(context, lazy);
    }

    /* access modifiers changed from: protected */
    public Function2<DiaryDay, MfpDailyGoal> getDailyRenderChartLogic(final ViewGroup viewGroup, final String str, final int i) {
        return new Function2<DiaryDay, MfpDailyGoal>() {
            public void execute(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) throws RuntimeException {
                NutrientEntry nutrientEntry;
                DiaryDay diaryDay2 = diaryDay;
                MfpDailyGoal mfpDailyGoal2 = mfpDailyGoal;
                CaloriesOrSingleNutrientsChartRendererBase.this.hideSpinnerContainer(viewGroup);
                int size = CaloriesOrSingleNutrientsChartRendererBase.this.mealNames.size();
                float[] fArr = new float[size];
                float[] fArr2 = new float[size];
                ArrayList arrayList = new ArrayList(size);
                CaloriesOrSingleNutrientsChartRendererBase caloriesOrSingleNutrientsChartRendererBase = CaloriesOrSingleNutrientsChartRendererBase.this;
                float totalNutrientsAndSetMealNutrients = caloriesOrSingleNutrientsChartRendererBase.getTotalNutrientsAndSetMealNutrients(diaryDay2, fArr, caloriesOrSingleNutrientsChartRendererBase.mealNames.getNames(), i);
                String unitString = CaloriesOrSingleNutrientsChartRendererBase.this.getUnitString(i);
                CaloriesOrSingleNutrientsChartRendererBase caloriesOrSingleNutrientsChartRendererBase2 = CaloriesOrSingleNutrientsChartRendererBase.this;
                caloriesOrSingleNutrientsChartRendererBase2.calculateMealPercentages(fArr, fArr2, totalNutrientsAndSetMealNutrients, caloriesOrSingleNutrientsChartRendererBase2.mealNames.getNames(), arrayList, CaloriesOrSingleNutrientsChartRendererBase.this.getUnitString(i));
                if (i != -1) {
                    CaloriesOrSingleNutrientsChartRendererBase caloriesOrSingleNutrientsChartRendererBase3 = CaloriesOrSingleNutrientsChartRendererBase.this;
                    nutrientEntry = caloriesOrSingleNutrientsChartRendererBase3.getNutrientEntry(str, totalNutrientsAndSetMealNutrients, caloriesOrSingleNutrientsChartRendererBase3.getNutritionalGoalsUtil().getAdjustedNutritionalGoal(diaryDay2, mfpDailyGoal2, i), unitString);
                } else {
                    nutrientEntry = null;
                }
                CalorieValues access$200 = CaloriesOrSingleNutrientsChartRendererBase.this.getDailyCalorieValuesForDiaryDay(diaryDay2, mfpDailyGoal2, fArr2, totalNutrientsAndSetMealNutrients);
                ArrayList arrayList2 = arrayList;
                new CustomPieChart(CaloriesOrSingleNutrientsChartRendererBase.this.activity, access$200, CaloriesOrSingleNutrientsChartRendererBase.this.getChartType(), arrayList2, CaloriesOrSingleNutrientsChartRendererBase.this.getActualNutrientIndex(i), nutrientEntry, viewGroup);
            }
        };
    }

    /* access modifiers changed from: protected */
    public Function1<ProgressReport> getWeeklyRenderChartLogic(final ViewGroup viewGroup, final Date date, final int i) {
        return new Function1<ProgressReport>() {
            public void execute(ProgressReport progressReport) throws RuntimeException {
                ProgressReport progressReport2 = progressReport;
                CaloriesOrSingleNutrientsChartRendererBase.this.hideSpinnerContainer(viewGroup);
                String[] labelsForDays = CaloriesOrSingleNutrientsChartRendererBase.this.getLabelsForDays(date);
                CalorieValues weeklyValues = CaloriesOrSingleNutrientsChartRendererBase.this.getWeeklyValues(progressReport2);
                float goal = weeklyValues.getGoal() / 7.0f;
                List<CalorieValues> dailyValues = CaloriesOrSingleNutrientsChartRendererBase.this.getDailyValues(progressReport2);
                float f = BitmapDescriptorFactory.HUE_RED;
                for (CalorieValues total : dailyValues) {
                    float total2 = total.getTotal();
                    if (total2 > f) {
                        f = total2;
                    }
                }
                double yAxisIncrement = CaloriesOrSingleNutrientsChartRendererBase.this.getYAxisIncrement(Math.max(f, weeklyValues.getTotalAverage()), 4);
                Activity access$500 = CaloriesOrSingleNutrientsChartRendererBase.this.activity;
                List customCalorieGoalLegendList = progressReport.getCustomCalorieGoalLegendList();
                CaloriesOrSingleNutrientsChartRendererBase caloriesOrSingleNutrientsChartRendererBase = CaloriesOrSingleNutrientsChartRendererBase.this;
                new CustomBarChart(access$500, labelsForDays, dailyValues, customCalorieGoalLegendList, weeklyValues, caloriesOrSingleNutrientsChartRendererBase.getWeeklyLegendData(weeklyValues, caloriesOrSingleNutrientsChartRendererBase.getUnitString(i)), goal, yAxisIncrement, CaloriesOrSingleNutrientsChartRendererBase.this.getChartType(), CaloriesOrSingleNutrientsChartRendererBase.this.getGraphSubType(), new NumberRange(Double.valueOf(0.0d), Double.valueOf(4.5d * yAxisIncrement)), CaloriesOrSingleNutrientsChartRendererBase.this.getActualNutrientIndex(i), viewGroup);
            }
        };
    }

    /* access modifiers changed from: private */
    public List<LegendData> getWeeklyLegendData(CalorieValues calorieValues, String str) {
        int size = this.mealNames.size();
        ArrayList arrayList = new ArrayList(size);
        float[] mealPercentages = calorieValues.getMealPercentages();
        float totalAverage = calorieValues.getTotalAverage();
        for (int i = 0; i < size; i++) {
            int round = Math.round(mealPercentages[i]);
            int round2 = Math.round(NumberUtils.getValueFromPercentage((float) round, totalAverage));
            arrayList.add(new LegendData((String) this.mealNames.getNames().get(i), getColor(Colors.MEAL_COLORS_RES[i]), round, String.format("%s %s", new Object[]{NumberUtils.localeStringFromInt(round2), str})));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public CalorieValues getDailyCalorieValuesForDiaryDay(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, float[] fArr, float f) {
        return new CalorieValues(f, diaryDay.netCalories(true), getNutritionalGoalsUtil().getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, 0, false), fArr);
    }

    /* access modifiers changed from: private */
    public void calculateMealPercentages(float[] fArr, float[] fArr2, float f, List<String> list, List<LegendData> list2, String str) {
        float[] fArr3 = fArr;
        int[] iArr = Colors.MEAL_COLORS_RES;
        for (int i = 0; i < fArr3.length; i++) {
            float percentage = NumberUtils.getPercentage(fArr3[i], f);
            fArr2[i] = percentage;
            list2.add(new LegendData((String) list.get(i), getColor(iArr[i]), Math.round(percentage), String.format("%s %s", new Object[]{NumberUtils.localeStringFromInt(Math.round(fArr3[i])), str})));
        }
    }
}
