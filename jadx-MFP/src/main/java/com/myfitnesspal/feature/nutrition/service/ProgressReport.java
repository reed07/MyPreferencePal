package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.CaloriesAndMacroValues;
import com.myfitnesspal.feature.nutrition.model.CustomCalorieGoalLegend;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class ProgressReport {
    public static final int DAYS_IN_WEEK_INDEX = 6;
    private static final int MINIMUM_NUMBER_OF_CUSTOM_GOALS = 2;
    private CaloriesAndMacroValues averageCaloriesAndMacroValues;
    private Context context;
    private List<CustomCalorieGoalLegend> customCalorieGoalLegendList;
    private MacroValues goalMacroValues;
    private MealNames mealNames;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    private List<CaloriesAndMacroValues> resultCaloriesAndMacroValuesList = new ArrayList();
    private List<CalorieValues> resultDailyCalorieValuesList = new ArrayList();
    private List<CalorieValues> resultMealNutrientValuesList = new ArrayList();
    private final Lazy<UserEnergyService> userEnergyService;
    private CalorieValues weeklyCalorieValues;
    private CalorieValues weeklyNutrientValues;

    private float getAverageValue(float f, int i) {
        return i > 0 ? f / ((float) i) : BitmapDescriptorFactory.HUE_RED;
    }

    private boolean isValidValue(double d) {
        return d > 0.0d;
    }

    public ProgressReport(Context context2, @Nonnull Lazy<Session> lazy, @Nonnull Lazy<UserEnergyService> lazy2, @Nonnull Lazy<NutrientGoalService> lazy3, @Nonnull Lazy<NutrientGoalsUtil> lazy4) {
        this.context = context2;
        this.mealNames = ((Session) lazy.get()).getUser().getMealNames();
        this.userEnergyService = lazy2;
        this.nutrientGoalService = lazy3;
        this.nutritionalGoalsUtil = lazy4;
    }

    public void weeklyNetCalorieReportForDate(Date date) {
        DiaryDay diaryDay = new DiaryDay();
        Date offsetDate = DateTimeUtils.offsetDate(date, 6);
        MfpNutrientGoal mfpNutrientGoalFromDB = ((NutrientGoalService) this.nutrientGoalService.get()).getMfpNutrientGoalFromDB(date);
        ArrayList arrayList = new ArrayList(7);
        this.resultDailyCalorieValuesList.clear();
        for (int i = 6; i >= 0; i--) {
            Date offsetDate2 = DateTimeUtils.offsetDate((Date) offsetDate.clone(), -i);
            diaryDay = diaryDay.initFromDatabaseForDate(offsetDate2);
            MfpDailyGoal mfpDailyGoalForDate = getMfpDailyGoalForDate(offsetDate2);
            arrayList.add(mfpDailyGoalForDate);
            this.resultDailyCalorieValuesList.add(getCalorieValuesForDay(diaryDay, mfpDailyGoalForDate));
        }
        this.customCalorieGoalLegendList = setupCustomCalorieGoalLegendList(arrayList, mfpNutrientGoalFromDB);
        this.weeklyCalorieValues = calculateWeeklyNutrientOrCalorieValues(this.resultDailyCalorieValuesList);
    }

    private List<CustomCalorieGoalLegend> setupCustomCalorieGoalLegendList(List<MfpDailyGoal> list, MfpNutrientGoal mfpNutrientGoal) {
        MfpDailyGoal mfpDailyGoal;
        Map customGoalToDaysMapping = getCustomGoalToDaysMapping(list);
        int size = CollectionUtils.size(customGoalToDaysMapping);
        if (size < 2) {
            return null;
        }
        if (mfpNutrientGoal != null) {
            mfpDailyGoal = mfpNutrientGoal.getDefaultGoal();
        } else {
            mfpDailyGoal = ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal();
        }
        float localizedEnergy = MfpDailyGoal.getLocalizedEnergy(mfpDailyGoal, (UserEnergyService) this.userEnergyService.get());
        ArrayList arrayList = new ArrayList(size);
        for (Entry entry : customGoalToDaysMapping.entrySet()) {
            float floatValue = ((Float) entry.getKey()).floatValue();
            if (NumberUtils.areEffectivelyEqual(floatValue, localizedEnergy)) {
                arrayList.add(0, new CustomCalorieGoalLegend(this.context.getString(R.string.goal_default), NumberUtils.localeStringFromFloat(floatValue)));
            } else {
                arrayList.add(new CustomCalorieGoalLegend(this.context.getString(R.string.goal_days, new Object[]{getGoalDays((List) entry.getValue())}), NumberUtils.localeStringFromFloat(floatValue)));
            }
        }
        return arrayList;
    }

    private String getGoalDays(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        int size = CollectionUtils.size((Collection<?>) list);
        for (int i = 0; i < size; i++) {
            sb.append(this.context.getString(DateTimeUtils.getFormattedDayOFWeek(DateTimeUtils.getDayStringForDayIndex(Integer.valueOf(((Integer) list.get(i)).intValue())))));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private Map<Float, List<Integer>> getCustomGoalToDaysMapping(List<MfpDailyGoal> list) {
        HashMap hashMap = new HashMap(CollectionUtils.size((Collection<?>) list));
        for (MfpDailyGoal mfpDailyGoal : list) {
            if (mfpDailyGoal != null) {
                float currentEnergy = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) mfpDailyGoal.getEnergy().getValue());
                int dayOfWeekNameToIndex = getNutritionalGoalsUtil().dayOfWeekNameToIndex(mfpDailyGoal.getDayOfWeek());
                List list2 = (List) hashMap.get(Float.valueOf(currentEnergy));
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(Float.valueOf(currentEnergy), list2);
                }
                list2.add(Integer.valueOf(dayOfWeekNameToIndex));
            }
        }
        return hashMap;
    }

    private CalorieValues getCalorieValuesForDay(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) {
        return new CalorieValues(diaryDay.caloriesConsumed(true), diaryDay.netCalories(true), getNutritionalGoal(diaryDay, mfpDailyGoal, 0, false), diaryDay.getMealPercentages(this.mealNames.getNames()));
    }

    public void weeklyMacroReportForDate(Date date) {
        DiaryDay diaryDay = new DiaryDay();
        Date offsetDate = DateTimeUtils.offsetDate(date, 6);
        this.resultCaloriesAndMacroValuesList.clear();
        float f = BitmapDescriptorFactory.HUE_RED;
        DiaryDay diaryDay2 = diaryDay;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        for (int i = 6; i >= 0; i--) {
            Date offsetDate2 = DateTimeUtils.offsetDate((Date) offsetDate.clone(), -i);
            diaryDay2 = diaryDay2.initFromDatabaseForDate(offsetDate2);
            MfpDailyGoal mfpDailyGoalForDate = getMfpDailyGoalForDate(offsetDate2);
            float[] fArr = new float[20];
            for (int i2 = 0; i2 < 20; i2++) {
                fArr[i2] = diaryDay2.amountOfNutrientConsumed(i2);
            }
            this.resultCaloriesAndMacroValuesList.add(new CaloriesAndMacroValues(new MacroValues(fArr[9], fArr[1], fArr[12]), diaryDay2.caloriesConsumed(false)));
            f += getNutritionalGoal(diaryDay2, mfpDailyGoalForDate, 9, true);
            f3 += getNutritionalGoal(diaryDay2, mfpDailyGoalForDate, 12, true);
            f2 += getNutritionalGoal(diaryDay2, mfpDailyGoalForDate, 1, true);
        }
        calculateTotalAndAverageMacroValue();
        this.goalMacroValues = new MacroValues(f, f2, f3);
    }

    public void weeklySpecificNutrientReport(Date date, int i) {
        DiaryDay diaryDay = new DiaryDay();
        Date offsetDate = DateTimeUtils.offsetDate(date, 6);
        for (int i2 = 6; i2 >= 0; i2--) {
            Date offsetDate2 = DateTimeUtils.offsetDate((Date) offsetDate.clone(), -i2);
            diaryDay = diaryDay.initFromDatabaseForDate(offsetDate2);
            this.resultMealNutrientValuesList.add(getNutrientValuesForDay(diaryDay, getMfpDailyGoalForDate(offsetDate2), i));
        }
        this.weeklyNutrientValues = calculateWeeklyNutrientOrCalorieValues(this.resultMealNutrientValuesList);
    }

    private CalorieValues getNutrientValuesForDay(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i) {
        int size = this.mealNames.size();
        float[] fArr = new float[size];
        float f = BitmapDescriptorFactory.HUE_RED;
        for (int i2 = 0; i2 < size; i2++) {
            float totalNutrientConsumedForMealNameAndNutrientIndex = diaryDay.getTotalNutrientConsumedForMealNameAndNutrientIndex((String) this.mealNames.getNames().get(i2), i);
            fArr[i2] = totalNutrientConsumedForMealNameAndNutrientIndex;
            f += totalNutrientConsumedForMealNameAndNutrientIndex;
        }
        for (int i3 = 0; i3 < size; i3++) {
            fArr[i3] = NumberUtils.getPercentage(fArr[i3], f);
        }
        return new CalorieValues(f, f, getNutritionalGoal(diaryDay, mfpDailyGoal, i, true), fArr);
    }

    private CalorieValues calculateWeeklyNutrientOrCalorieValues(List<CalorieValues> list) {
        int size = this.mealNames.size();
        float[] fArr = new float[size];
        int i = 0;
        float f = BitmapDescriptorFactory.HUE_RED;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        for (CalorieValues calorieValues : list) {
            f3 += calorieValues.getGoal();
            float total = calorieValues.getTotal();
            if (total != BitmapDescriptorFactory.HUE_RED) {
                i++;
                f += total;
                f2 += calorieValues.getNet();
                for (int i2 = 0; i2 < size; i2++) {
                    fArr[i2] = fArr[i2] + calorieValues.getMealPercentages()[i2];
                }
            }
        }
        float[] fArr2 = new float[size];
        for (int i3 = 0; i3 < size; i3++) {
            fArr2[i3] = fArr[i3] / ((float) i);
        }
        CalorieValues calorieValues2 = new CalorieValues(f, f2, f3, fArr2, getAverageValue(f, i), getAverageValue(f2, i));
        return calorieValues2;
    }

    private void calculateTotalAndAverageMacroValue() {
        if (CollectionUtils.isEmpty((Collection<?>) this.resultCaloriesAndMacroValuesList)) {
            this.averageCaloriesAndMacroValues = new CaloriesAndMacroValues();
            return;
        }
        float f = BitmapDescriptorFactory.HUE_RED;
        int i = 0;
        float f2 = BitmapDescriptorFactory.HUE_RED;
        int i2 = 0;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        int i3 = 0;
        float f4 = BitmapDescriptorFactory.HUE_RED;
        int i4 = 0;
        for (CaloriesAndMacroValues caloriesAndMacroValues : this.resultCaloriesAndMacroValuesList) {
            float carbsValue = caloriesAndMacroValues.getMacroValues().getCarbsValue();
            float fatValue = caloriesAndMacroValues.getMacroValues().getFatValue();
            float proteinValue = caloriesAndMacroValues.getMacroValues().getProteinValue();
            float caloriesFromDiary = caloriesAndMacroValues.getCaloriesFromDiary();
            if (isValidValue((double) carbsValue)) {
                i++;
                f += carbsValue;
            }
            if (isValidValue((double) fatValue)) {
                i2++;
                f2 += fatValue;
            }
            if (isValidValue((double) proteinValue)) {
                i3++;
                f3 += proteinValue;
            }
            if (isValidValue((double) caloriesFromDiary)) {
                i4++;
                f4 += caloriesFromDiary;
            }
        }
        this.averageCaloriesAndMacroValues = new CaloriesAndMacroValues(new MacroValues(getAverageValue(f, i), getAverageValue(f2, i2), getAverageValue(f3, i3)), getAverageValue(f4, i4));
    }

    private MfpDailyGoal getMfpDailyGoalForDate(Date date) {
        return ((NutrientGoalService) this.nutrientGoalService.get()).getMfpDailyGoalFromDB(date);
    }

    private NutrientGoalsUtil getNutritionalGoalsUtil() {
        return (NutrientGoalsUtil) this.nutritionalGoalsUtil.get();
    }

    private float getNutritionalGoal(DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal, int i, boolean z) {
        return getNutritionalGoalsUtil().getAdjustedNutritionalGoal(diaryDay, mfpDailyGoal, i, z);
    }

    public MacroValues getGoalMacroValues() {
        return this.goalMacroValues;
    }

    public CaloriesAndMacroValues getAverageCaloriesAndMacroValues() {
        return this.averageCaloriesAndMacroValues;
    }

    public List<CaloriesAndMacroValues> getResultMacroValuesList() {
        return this.resultCaloriesAndMacroValuesList;
    }

    public List<CalorieValues> getResultDailyCalorieValuesList() {
        return this.resultDailyCalorieValuesList;
    }

    public CalorieValues getWeeklyCalorieValues() {
        return this.weeklyCalorieValues;
    }

    public List<CalorieValues> getResultMealNutrientValuesList() {
        return this.resultMealNutrientValuesList;
    }

    public CalorieValues getWeeklyNutrientValues() {
        return this.weeklyNutrientValues;
    }

    public List<CustomCalorieGoalLegend> getCustomCalorieGoalLegendList() {
        return this.customCalorieGoalLegendList;
    }
}
