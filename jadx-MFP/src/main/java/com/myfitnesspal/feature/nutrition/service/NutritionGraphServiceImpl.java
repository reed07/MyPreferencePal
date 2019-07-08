package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.nutrition.model.FoodListItem;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutritionGraphServiceImpl extends SimpleAsyncServiceBase implements NutritionGraphService {
    private static final int MAX_THREADS = 2;
    private static final String QUICK_ADD_FOOD_ID = "QUICK_ADD_FOOD_ID";
    private static final String TAG = "NutritionGraphServiceImpl";
    /* access modifiers changed from: private */
    public final Lazy<NutrientGoalService> nutrientGoalService;
    /* access modifiers changed from: private */
    public final Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    /* access modifiers changed from: private */
    public final Lazy<UserEnergyService> userEnergyService;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public NutritionGraphServiceImpl(Lazy<NutrientGoalService> lazy, Lazy<UserEnergyService> lazy2, Lazy<Session> lazy3, Lazy<NutrientGoalsUtil> lazy4) {
        this.nutrientGoalService = lazy;
        this.userEnergyService = lazy2;
        this.session = lazy3;
        this.nutrientGoalsUtil = lazy4;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void renderDailyChart(final Date date, final Function2<DiaryDay, MfpDailyGoal> function2) {
        auto(new Runnable() {
            public void run() {
                NutritionGraphServiceImpl.this.invokeOnMainThread(function2, NutritionGraphServiceImpl.this.getDiaryDayForDate(date), NutritionGraphServiceImpl.this.getMfpDailyGoalForDate(date));
            }
        });
    }

    public void renderWeeklyChart(Context context, Date date, String str, int i, Function1<ProgressReport> function1) {
        final Context context2 = context;
        final String str2 = str;
        final Date date2 = date;
        final int i2 = i;
        final Function1<ProgressReport> function12 = function1;
        AnonymousClass2 r0 = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    com.myfitnesspal.feature.nutrition.service.ProgressReport r6 = new com.myfitnesspal.feature.nutrition.service.ProgressReport
                    android.content.Context r1 = r2
                    com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl r0 = com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.this
                    dagger.Lazy r2 = r0.session
                    com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl r0 = com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.this
                    dagger.Lazy r3 = r0.userEnergyService
                    com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl r0 = com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.this
                    dagger.Lazy r4 = r0.nutrientGoalService
                    com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl r0 = com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.this
                    dagger.Lazy r5 = r0.nutrientGoalsUtil
                    r0 = r6
                    r0.<init>(r1, r2, r3, r4, r5)
                    java.lang.String r0 = r3
                    int r1 = r0.hashCode()
                    r2 = -1997878713(0xffffffff88eaca47, float:-1.4130918E-33)
                    if (r1 == r2) goto L_0x004a
                    r2 = -104321242(0xfffffffff9c82f26, float:-1.2992696E35)
                    if (r1 == r2) goto L_0x0040
                    r2 = 1933126767(0x73392c6f, float:1.4670962E31)
                    if (r1 == r2) goto L_0x0036
                    goto L_0x0054
                L_0x0036:
                    java.lang.String r1 = "SingleNutrient"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0054
                    r0 = 2
                    goto L_0x0055
                L_0x0040:
                    java.lang.String r1 = "Calories"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0054
                    r0 = 0
                    goto L_0x0055
                L_0x004a:
                    java.lang.String r1 = "Macros"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x0054
                    r0 = 1
                    goto L_0x0055
                L_0x0054:
                    r0 = -1
                L_0x0055:
                    switch(r0) {
                        case 0: goto L_0x0067;
                        case 1: goto L_0x0061;
                        case 2: goto L_0x0059;
                        default: goto L_0x0058;
                    }
                L_0x0058:
                    goto L_0x006c
                L_0x0059:
                    java.util.Date r0 = r4
                    int r1 = r5
                    r6.weeklySpecificNutrientReport(r0, r1)
                    goto L_0x006c
                L_0x0061:
                    java.util.Date r0 = r4
                    r6.weeklyMacroReportForDate(r0)
                    goto L_0x006c
                L_0x0067:
                    java.util.Date r0 = r4
                    r6.weeklyNetCalorieReportForDate(r0)
                L_0x006c:
                    com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl r0 = com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.this
                    com.uacf.core.util.Function1 r1 = r6
                    r0.invokeOnMainThread(r1, r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl.AnonymousClass2.run():void");
            }
        };
        auto(r0);
    }

    public void renderFoodList(Date date, int i, boolean z, Function1<Map<FoodListItem, Integer>> function1) {
        final boolean z2 = z;
        final Date date2 = date;
        final int i2 = i;
        final Function1<Map<FoodListItem, Integer>> function12 = function1;
        AnonymousClass3 r0 = new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                if (z2) {
                    Date offsetDate = DateTimeUtils.offsetDate(date2, 6);
                    for (int i = 5; i >= 0; i--) {
                        NutritionGraphServiceImpl.this.calculateFoodListForDate(DateTimeUtils.offsetDate((Date) offsetDate.clone(), -i), i2, hashMap);
                    }
                } else {
                    NutritionGraphServiceImpl.this.calculateFoodListForDate(date2, i2, hashMap);
                }
                NutritionGraphServiceImpl.this.invokeOnMainThread(function12, hashMap);
            }
        };
        auto(r0);
    }

    public void renderNutritionSummary(Date date, NutritionDetailsDelegate nutritionDetailsDelegate, boolean z, Function1<List<NutrientEntry>> function1) {
        final boolean z2 = z;
        final Function1<List<NutrientEntry>> function12 = function1;
        final NutritionDetailsDelegate nutritionDetailsDelegate2 = nutritionDetailsDelegate;
        final Date date2 = date;
        AnonymousClass4 r0 = new Runnable() {
            public void run() {
                if (z2) {
                    NutritionGraphServiceImpl.this.invokeOnMainThread(function12, nutritionDetailsDelegate2.getWeeklyNutrientDetails(date2, 0));
                } else {
                    NutritionGraphServiceImpl.this.invokeOnMainThread(function12, nutritionDetailsDelegate2.getDailyNutrientDetails(date2, 0));
                }
            }
        };
        auto(r0);
    }

    /* access modifiers changed from: private */
    public void calculateFoodListForDate(Date date, int i, Map<FoodListItem, Integer> map) {
        ArrayList<FoodEntry> foodEntries = getDiaryDayForDate(date).getFoodEntries();
        boolean z = i == 0;
        for (FoodEntry foodEntry : foodEntries) {
            float amountOfNutrientIndex = foodEntry.amountOfNutrientIndex(i);
            if (z) {
                amountOfNutrientIndex = ((UserEnergyService) this.userEnergyService.get()).getEnergy(((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit(), new MfpMeasuredValue("calories", amountOfNutrientIndex));
            }
            if (amountOfNutrientIndex > BitmapDescriptorFactory.HUE_RED || z) {
                Food food = foodEntry.getFood();
                FoodListItem foodListItem = new FoodListItem(food.isQuickAddOfAnySort() ? QUICK_ADD_FOOD_ID : food.getUid(), foodEntry.getFood().getDescription(), amountOfNutrientIndex);
                map.put(foodListItem, Integer.valueOf(NumberUtils.intValue((Integer) map.get(foodListItem)) + 1));
            }
        }
    }

    /* access modifiers changed from: private */
    public DiaryDay getDiaryDayForDate(Date date) {
        DiaryDay diaryDay = new DiaryDay();
        diaryDay.initFromDatabaseForDate(date);
        return diaryDay;
    }

    /* access modifiers changed from: private */
    public MfpDailyGoal getMfpDailyGoalForDate(Date date) {
        return ((NutrientGoalService) this.nutrientGoalService.get()).getMfpDailyGoalFromDB(date);
    }
}
