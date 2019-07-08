package com.myfitnesspal.shared.model.v1;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.tizen.service.MfpGearMessageBridge;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.db.adapter.WaterEntriesDBAdapter;
import com.myfitnesspal.shared.event.InsightsChangedEvent;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.DiaryNoteMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.WaterEntryMapper;
import com.myfitnesspal.shared.model.unitconv.LocalizedFluid;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.DiaryDaySummaryObject;
import com.myfitnesspal.shared.model.v15.DiaryNoteObject;
import com.myfitnesspal.shared.model.v15.ExerciseEntryFromServer;
import com.myfitnesspal.shared.model.v15.FoodEntryFromServer;
import com.myfitnesspal.shared.model.v15.StepsEntryObject;
import com.myfitnesspal.shared.model.v15.WaterEntryObject;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpExerciseEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Inject;

public class DiaryDay extends DatabaseObject {
    private static final int FOOD_INSIGHT_DEBOUNCE_MILLIS = 1000;
    private NutritionalValues adjustedNutrientGoals;
    private List<String> allUserMealNames;
    @Inject
    Lazy<Bus> bus;
    private ArrayList<ExerciseEntry> cardioExerciseEntries;
    private WaterEntry currentWaterEntry;
    private Date date;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    Lazy<DiaryNoteMapper> diaryNoteMapper;
    @Inject
    Lazy<DiaryService> diaryService;
    private List<ExerciseEntry> exerciseEntries;
    @Inject
    Lazy<ExerciseEntryMapper> exerciseEntryMapper;
    private DiaryNote exerciseNote;
    @Inject
    Lazy<ExerciseEntryFromServerMapper> exerciseV1EntryMapper;
    @Inject
    Lazy<ExternalNutritionService> externalNutritionService;
    private ArrayList<FoodEntry> foodEntries;
    private Map<String, ArrayList<FoodEntry>> foodEntriesByMealName;
    @Inject
    Lazy<FoodEntryFromServerMapper> foodEntryMapper;
    private Debouncer<FoodEntry> foodInsightDebouncer = new Debouncer<FoodEntry>(1000) {
        private List<FoodEntry> entries = new ArrayList();
        private Random random = new Random();

        /* access modifiers changed from: protected */
        public void onCalled(FoodEntry foodEntry) {
            super.onCalled(foodEntry);
            this.entries.add(foodEntry);
        }

        /* access modifiers changed from: protected */
        public void onDebounced(FoodEntry foodEntry) {
            DiaryDay.this.updateFoodInsights((FoodEntry) this.entries.get(this.random.nextInt(this.entries.size())));
            this.entries.clear();
        }
    };
    /* access modifiers changed from: private */
    public ArrayList<FoodAnalyzerResponseData> foodInsights = new ArrayList<>();
    private DiaryNote foodNote;
    @Inject
    Lazy<FoodService> foodService;
    private float friendCaloriesBurned;
    @Inject
    Lazy<InsightSettings> insightSettings;
    private boolean isPerformingMultiAdd;
    private ExerciseEntry justAddedExerciseEntry;
    private boolean justAddedExerciseNote;
    private FoodEntry justAddedFoodEntry;
    private boolean justAddedFoodNote;
    private boolean justAddedMultipleItems;
    private String justAddedMultipleItemsMealName;
    private String justAddedPrimaryText;
    private WaterEntry justAddedWaterEntry;
    private boolean justCopiedFromDate;
    /* access modifiers changed from: private */
    public int lastInsightRequestId;
    private LocalizedFluid localizedWater;
    private Map<String, FoodEntry> mealNameToLatestEntry;
    private MealNames mealNames;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<Session> session;
    private StepsEntryObject stepsEntry;
    private ArrayList<ExerciseEntry> strengthExerciseEntries;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @Inject
    UserDistanceService userDistanceService;
    @Inject
    UserEnergyService userEnergyService;
    @Inject
    UserWeightService userWeightService;
    @Inject
    Lazy<WaterEntryMapper> waterEntryMapper;

    public DiaryDay() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public ArrayList<FoodEntry> getFoodEntries() {
        return this.foodEntries;
    }

    public void setFoodEntries(ArrayList<FoodEntry> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        this.foodEntries = arrayList;
    }

    public List<ExerciseEntry> getExerciseEntries() {
        return this.exerciseEntries;
    }

    public void setExerciseEntries(List<ExerciseEntry> list) {
        this.exerciseEntries = list;
    }

    public Map<String, ArrayList<FoodEntry>> getFoodEntriesByMealName() {
        return this.foodEntriesByMealName;
    }

    public List<FoodEntry> getFoodEntriesForMealName(String str) {
        if (CollectionUtils.isEmpty(this.foodEntriesByMealName)) {
            return null;
        }
        return (List) this.foodEntriesByMealName.get(str);
    }

    public void setFoodEntriesByMealName(Map<String, ArrayList<FoodEntry>> map) {
        this.foodEntriesByMealName = map;
    }

    public List<String> getMealNames() {
        MealNames mealNames2 = this.mealNames;
        if (mealNames2 != null) {
            return mealNames2.getNames();
        }
        return null;
    }

    public ArrayList<ExerciseEntry> getStrengthExerciseEntries() {
        return this.strengthExerciseEntries;
    }

    public void setStrengthExerciseEntries(ArrayList<ExerciseEntry> arrayList) {
        this.strengthExerciseEntries = arrayList;
    }

    public ArrayList<ExerciseEntry> getCardioExerciseEntries() {
        return this.cardioExerciseEntries;
    }

    public void setCardioExerciseEntries(ArrayList<ExerciseEntry> arrayList) {
        this.cardioExerciseEntries = arrayList;
    }

    public int getWaterCups() {
        return (int) this.localizedWater.getValue(Water.CUPS);
    }

    public void setWaterCups(float f) {
        LocalizedFluid localizedFluid = this.localizedWater;
        LocalizedFluid.fromCups(f);
    }

    public LocalizedFluid getLocalizedWater() {
        return this.localizedWater;
    }

    public void setLocalizedWater(LocalizedFluid localizedFluid) {
        this.localizedWater = localizedFluid;
    }

    public DiaryNote getFoodNote() {
        return this.foodNote;
    }

    public void setFoodNote(DiaryNote diaryNote) {
        this.foodNote = diaryNote;
    }

    public DiaryNote getExerciseNote() {
        return this.exerciseNote;
    }

    public void setExerciseNote(DiaryNote diaryNote) {
        this.exerciseNote = diaryNote;
    }

    public WaterEntry justAddedWaterEntry() {
        return this.justAddedWaterEntry;
    }

    public WaterEntry getCurrentWaterEntry() {
        return this.currentWaterEntry;
    }

    public void setCurrentWaterEntry(WaterEntry waterEntry) {
        this.currentWaterEntry = waterEntry;
    }

    public void setJustAddedWaterEntry(WaterEntry waterEntry) {
        this.justAddedWaterEntry = waterEntry;
    }

    public boolean justAddedMultipleItems() {
        return this.justAddedMultipleItems;
    }

    public boolean justCopiedFromDate() {
        return this.justCopiedFromDate;
    }

    public void setJustCopiedFromDate(boolean z) {
        this.justCopiedFromDate = z;
    }

    public void setJustAddedMultipleItems(boolean z) {
        this.justAddedMultipleItems = z;
    }

    public void setJustAddedMultipleItemsMealName(String str) {
        this.justAddedMultipleItemsMealName = str;
    }

    public String getJustAddedMultipleItemsMealName() {
        return this.justAddedMultipleItemsMealName;
    }

    public boolean justAddedFoodNote() {
        return this.justAddedFoodNote;
    }

    public void setJustAddedFoodNote(boolean z) {
        this.justAddedFoodNote = z;
    }

    public boolean justAddedExerciseNote() {
        return this.justAddedExerciseNote;
    }

    public void setJustAddedExerciseNote(boolean z) {
        this.justAddedExerciseNote = z;
    }

    public FoodEntry getJustAddedFoodEntry() {
        return this.justAddedFoodEntry;
    }

    public void setJustAddedFoodEntry(FoodEntry foodEntry) {
        this.justAddedFoodEntry = foodEntry;
    }

    public ExerciseEntry getJustAddedExerciseEntry() {
        return this.justAddedExerciseEntry;
    }

    public void setJustAddedExerciseEntry(ExerciseEntry exerciseEntry) {
        this.justAddedExerciseEntry = exerciseEntry;
    }

    public void setJustAddedPrimaryText(String str) {
        this.justAddedPrimaryText = str;
    }

    private List<String> getAllUserMealNames(User user) {
        List<String> list = this.allUserMealNames;
        return list == null ? user.getMealNames().getNames() : list;
    }

    public void setAllUserMealNames(List<String> list) {
        this.allUserMealNames = list;
    }

    public String JustAddedPrimaryText() {
        return this.justAddedPrimaryText;
    }

    public DiaryDay initFromDatabaseForDate(Date date2) {
        this.date = date2;
        loadFromDatabase();
        return this;
    }

    public void loadFromDatabase() {
        try {
            Context context = getContext();
            setFoodEntries(new FoodEntriesDBAdapter(context, (DbConnectionManager) this.dbConnectionManager.get()).fetchFoodEntriesOnDate(this.date));
            this.exerciseEntries = ((DiaryService) this.diaryService.get()).getV1ExerciseEntriesForDate(this.date);
            this.currentWaterEntry = new WaterEntriesDBAdapter(context).fetchWaterEntryOnDate(this.date);
            if (this.currentWaterEntry == null) {
                this.localizedWater = LocalizedFluid.fromMilliliters(BitmapDescriptorFactory.HUE_RED);
            } else {
                float cups = this.currentWaterEntry.getCups();
                float milliliters = this.currentWaterEntry.getMilliliters();
                if (cups <= BitmapDescriptorFactory.HUE_RED || milliliters != BitmapDescriptorFactory.HUE_RED) {
                    this.localizedWater = LocalizedFluid.fromMilliliters(milliliters);
                } else {
                    this.localizedWater = LocalizedFluid.fromCups(cups);
                }
            }
            this.justAddedFoodEntry = null;
            this.justAddedExerciseEntry = null;
            this.justAddedExerciseEntry = null;
            this.justAddedMultipleItems = false;
            this.justAddedWaterEntry = null;
            this.justCopiedFromDate = false;
            groupExercisesByType();
            groupFoodEntriesByMealName(((Session) this.session.get()).getUser());
            createAdjustedNutrientGoals();
            this.foodNote = null;
            this.exerciseNote = null;
            loadNotes();
        } catch (SQLiteException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception occured: ");
            sb.append(e.getMessage());
            Ln.e(sb.toString(), new Object[0]);
        }
    }

    public void groupExercisesByType() {
        try {
            ArrayList<ExerciseEntry> arrayList = new ArrayList<>(10);
            ArrayList<ExerciseEntry> arrayList2 = new ArrayList<>(10);
            for (ExerciseEntry exerciseEntry : this.exerciseEntries) {
                switch (exerciseEntry.exerciseType()) {
                    case 0:
                        arrayList.add(exerciseEntry);
                        break;
                    case 1:
                        arrayList2.add(exerciseEntry);
                        break;
                }
            }
            this.cardioExerciseEntries = arrayList;
            this.strengthExerciseEntries = arrayList2;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception occured: ");
            sb.append(e.getMessage());
            Ln.e(sb.toString(), new Object[0]);
        }
    }

    private void groupFoodEntriesByMealName(User user) {
        boolean z;
        try {
            Hashtable hashtable = new Hashtable(10);
            ArrayList arrayList = new ArrayList(10);
            this.mealNameToLatestEntry = new HashMap();
            Iterator it = this.foodEntries.iterator();
            while (it.hasNext()) {
                FoodEntry foodEntry = (FoodEntry) it.next();
                String mealName = foodEntry.getMealName();
                ArrayList arrayList2 = (ArrayList) hashtable.get(mealName);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(4);
                    hashtable.put(mealName, arrayList2);
                }
                arrayList2.add(foodEntry);
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (((String) it2.next()).equals(mealName)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(mealName);
                }
                FoodEntry foodEntry2 = (FoodEntry) this.mealNameToLatestEntry.get(mealName);
                if (foodEntry2 != null) {
                    Date loggedAt = foodEntry2.getLoggedAt();
                    Date loggedAt2 = foodEntry.getLoggedAt();
                    if ((loggedAt == null && loggedAt2 != null) || (loggedAt2 != null && loggedAt2.compareTo(loggedAt) > 0)) {
                        this.mealNameToLatestEntry.put(mealName, foodEntry);
                    }
                } else {
                    this.mealNameToLatestEntry.put(mealName, foodEntry);
                }
            }
            this.mealNames = sortMealNames(arrayList, user);
            this.foodEntriesByMealName = hashtable;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception occured: ");
            sb.append(e.getMessage());
            Ln.e(sb.toString(), new Object[0]);
        }
    }

    private MealNames sortMealNames(List<String> list, User user) {
        List<String> allUserMealNames2 = getAllUserMealNames(user);
        ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) list));
        if (!CollectionUtils.notEmpty((Collection<?>) allUserMealNames2)) {
            return new MealNames(list);
        }
        for (String str : allUserMealNames2) {
            boolean z = false;
            Iterator it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Strings.equals(str, (String) it.next())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                arrayList.add(str);
            }
        }
        return new MealNames((List<String>) arrayList);
    }

    public void createAdjustedNutrientGoals() {
        try {
            User user = ((Session) this.session.get()).getUser();
            NutritionalValues nutritionalValues = ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal().toNutritionalValues(this.userEnergyService);
            if (nutritionalValues != null) {
                nutritionalValues.clampNegativesToZero();
                this.adjustedNutrientGoals = nutritionalValues.adjustForCaloriesEarnedBasedOnPreference(((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AssignExerciseCalories) ? this.nutrientGoalService : null, caloriesBurnedByExercise(false), user.getActiveDate());
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    private boolean isDateToday() {
        return DateTimeUtils.isSameDay(getDate(), new Date());
    }

    /* access modifiers changed from: private */
    public void updateFoodInsights(FoodEntry foodEntry) {
        if (((InsightSettings) this.insightSettings.get()).areInsightsEnabled()) {
            FoodEntry foodEntry2 = foodEntry;
            this.lastInsightRequestId = ((FoodService) this.foodService.get()).getFoodInsightAsync(this.adjustedNutrientGoals, this.foodEntries, foodEntry2, this.userEnergyService, this.userWeightService, this.userDistanceService, caloriesBurnedByExercise(false, ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AssignExerciseCalories)), new Function2<List<FoodAnalyzerResponseData>, Integer>() {
                public void execute(List<FoodAnalyzerResponseData> list, Integer num) {
                    if (num.intValue() == DiaryDay.this.lastInsightRequestId) {
                        if (CollectionUtils.notEmpty((Collection<?>) list)) {
                            DiaryDay.this.foodInsights.add(list.get(0));
                        }
                        ((Bus) DiaryDay.this.bus.get()).post(new InsightsChangedEvent());
                    }
                }
            });
        }
    }

    public void addV2Food(MfpFood mfpFood, String str) {
        addFoodEntry(mfpFood.toFoodEntry(((Session) this.session.get()).getUser(), str, this.date));
    }

    public void addFoodEntry(FoodEntry foodEntry) {
        Context context = getContext();
        new FoodDBAdapter(context, (DbConnectionManager) this.dbConnectionManager.get()).insertFoodIfMissing(foodEntry.getFood(), (DbConnectionManager) this.dbConnectionManager.get());
        new FoodEntriesDBAdapter(context, (DbConnectionManager) this.dbConnectionManager.get()).insertFoodEntry(foodEntry, (DbConnectionManager) this.dbConnectionManager.get());
        this.foodEntries.add(foodEntry);
        groupFoodEntriesByMealName(((Session) this.session.get()).getUser());
        createAdjustedNutrientGoals();
        this.justAddedFoodEntry = foodEntry;
        this.foodInsightDebouncer.call(foodEntry);
        setJustAddedPrimaryText(foodEntry.getFood().getDescription());
        scheduleSync();
    }

    public void addExerciseEntry(MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null) {
            addExerciseEntry((ExerciseEntry) ((ExerciseEntryMapper) this.exerciseEntryMapper.get()).reverseMap(mfpExerciseEntry));
        }
    }

    public void addExerciseEntry(ExerciseEntry exerciseEntry) {
        ExerciseEntry createNewV1ExerciseEntryLocally = ((DiaryService) this.diaryService.get()).createNewV1ExerciseEntryLocally(exerciseEntry);
        this.exerciseEntries.add(createNewV1ExerciseEntryLocally);
        groupExercisesByType();
        createAdjustedNutrientGoals();
        setJustAddedExerciseEntry(createNewV1ExerciseEntryLocally);
        scheduleSync();
    }

    public void updateExerciseEntry(ExerciseEntry exerciseEntry) {
        ExerciseEntry updateV1ExerciseEntryLocally = ((DiaryService) this.diaryService.get()).updateV1ExerciseEntryLocally(exerciseEntry);
        int indexOf = Enumerable.indexOf(this.exerciseEntries, Long.valueOf(exerciseEntry.getLocalId()), new ReturningFunction1<Long, ExerciseEntry>() {
            public Long execute(ExerciseEntry exerciseEntry) {
                return Long.valueOf(exerciseEntry.getLocalId());
            }
        });
        if (indexOf >= 0) {
            this.exerciseEntries.set(indexOf, updateV1ExerciseEntryLocally);
        }
        createAdjustedNutrientGoals();
        scheduleSync();
    }

    public float caloriesConsumed(boolean z) {
        Iterator it = this.foodEntries.iterator();
        float f = BitmapDescriptorFactory.HUE_RED;
        while (it.hasNext()) {
            FoodEntry foodEntry = (FoodEntry) it.next();
            f += (float) (z ? this.userEnergyService.getRoundedCurrentEnergy((double) foodEntry.getCaloriesValue()) : foodEntry.roundedCalories());
        }
        return f;
    }

    public float amountOfNutrientConsumed(int i) {
        Iterator it = this.foodEntries.iterator();
        float f = BitmapDescriptorFactory.HUE_RED;
        while (it.hasNext()) {
            f += NutritionUtils.getNutritionValueFromFoodEntry((Session) this.session.get(), (FoodEntry) it.next(), i);
        }
        return f;
    }

    public float caloriesBurnedByExercise(boolean z) {
        return caloriesBurnedByExercise(z, false);
    }

    public float caloriesBurnedByExercise(boolean z, boolean z2) {
        if (!z2) {
            return calculateCaloriesBurnedByExercise(z);
        }
        MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService) this.nutrientGoalService.get()).getDailyGoalForDayOfWeekSync(((Session) this.session.get()).getUser().getActiveDate());
        if (dailyGoalForDayOfWeekSync == null || dailyGoalForDayOfWeekSync.isAssignExerciseEnergyOn()) {
            return calculateCaloriesBurnedByExercise(z);
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    private float calculateCaloriesBurnedByExercise(boolean z) {
        float f = BitmapDescriptorFactory.HUE_RED;
        for (ExerciseEntry exerciseEntry : this.exerciseEntries) {
            f += z ? (float) this.userEnergyService.getRoundedCurrentEnergy((double) exerciseEntry.getCalories()) : exerciseEntry.getCalories();
        }
        return f;
    }

    public void setWaterEntry(int i) {
        setLocalizedWaterEntry((float) i, Water.CUPS);
    }

    public void setLocalizedWaterEntry(float f, Water water) {
        this.localizedWater = LocalizedFluid.from(water, Float.valueOf(f));
        WaterEntry waterEntry = new WaterEntry(this.localizedWater);
        waterEntry.setEntryDate(this.date);
        new WaterEntriesDBAdapter(getContext()).insertOrUpdateWaterEntry(waterEntry);
        setJustAddedWaterEntry(waterEntry);
        setCurrentWaterEntry(waterEntry);
        scheduleSync();
    }

    public int totalCaloriesForMealName(String str) {
        return totalCaloriesForMealName(str, true);
    }

    public int totalCaloriesForMealName(String str, boolean z) {
        ArrayList arrayList = (ArrayList) this.foodEntriesByMealName.get(str);
        int i = 0;
        if (CollectionUtils.isEmpty((Collection<?>) arrayList)) {
            return 0;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FoodEntry foodEntry = (FoodEntry) it.next();
            i = (int) (((float) i) + (z ? (float) this.userEnergyService.getRoundedCurrentEnergy((double) foodEntry.getCaloriesValue()) : foodEntry.getCaloriesValue()));
        }
        return i;
    }

    public int totalCaloriesForExercises() {
        int i = 0;
        if (CollectionUtils.isEmpty((Collection<?>) this.exerciseEntries)) {
            return 0;
        }
        for (ExerciseEntry calories : this.exerciseEntries) {
            i += this.userEnergyService.getRoundedCurrentEnergy((double) calories.getCalories());
        }
        return i;
    }

    public float totalNutrientValueForMealName(String str, int i) {
        ArrayList arrayList = (ArrayList) this.foodEntriesByMealName.get(str);
        if (CollectionUtils.isEmpty((Collection<?>) arrayList)) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        Iterator it = arrayList.iterator();
        float f = BitmapDescriptorFactory.HUE_RED;
        while (it.hasNext()) {
            float amountOfNutrientIndex = ((FoodEntry) it.next()).amountOfNutrientIndex(i);
            if (amountOfNutrientIndex > BitmapDescriptorFactory.HUE_RED) {
                f += amountOfNutrientIndex;
            }
        }
        return f;
    }

    public void updateFoodEntry(FoodEntry foodEntry) {
        ((DbConnectionManager) this.dbConnectionManager.get()).foodEntriesDbAdapter().deleteFoodEntry(foodEntry, this.externalNutritionService);
        foodEntry.setMasterDatabaseId(0);
        ((DbConnectionManager) this.dbConnectionManager.get()).foodEntriesDbAdapter().insertFoodEntry(foodEntry, (DbConnectionManager) this.dbConnectionManager.get());
        createAdjustedNutrientGoals();
        scheduleSync();
    }

    public boolean isEmpty() {
        return this.foodEntries.size() == 0 && this.exerciseEntries.size() == 0 && this.localizedWater.isZero() && !hasAnyNotes();
    }

    public void deleteExerciseEntry(ExerciseEntry exerciseEntry) {
        ((DiaryService) this.diaryService.get()).deleteExerciseEntryLocally(exerciseEntry);
        this.exerciseEntries.remove(exerciseEntry);
        groupExercisesByType();
        createAdjustedNutrientGoals();
        scheduleSync();
    }

    public void deleteFoodEntry(FoodEntry foodEntry) {
        new FoodEntriesDBAdapter(getContext(), (DbConnectionManager) this.dbConnectionManager.get()).deleteFoodEntry(foodEntry, this.externalNutritionService);
        this.foodEntries.remove(foodEntry);
        groupFoodEntriesByMealName(((Session) this.session.get()).getUser());
        createAdjustedNutrientGoals();
        scheduleSync();
    }

    public NutritionalValues getAdjustedNutrientGoals() {
        return this.adjustedNutrientGoals;
    }

    public void setAdjustedNutrientGoals(NutritionalValues nutritionalValues) {
        this.adjustedNutrientGoals = nutritionalValues;
    }

    public void loadNotes() {
        try {
            ArrayList fetchDiaryNotesOnDate = ((DbConnectionManager) this.dbConnectionManager.get()).diaryNoteDbAdapter().fetchDiaryNotesOnDate(this.date);
            this.foodNote = null;
            this.exerciseNote = null;
            if (CollectionUtils.notEmpty((Collection<?>) fetchDiaryNotesOnDate)) {
                Iterator it = fetchDiaryNotesOnDate.iterator();
                while (it.hasNext()) {
                    DiaryNote diaryNote = (DiaryNote) it.next();
                    if (diaryNote.getBody() == null || diaryNote.getBody().length() != 0) {
                        switch (diaryNote.getNoteType()) {
                            case 0:
                                this.foodNote = diaryNote;
                                break;
                            case 1:
                                this.exerciseNote = diaryNote;
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public boolean hasAnyNotes() {
        return (this.foodNote == null && this.exerciseNote == null) ? false : true;
    }

    public boolean isForToday() {
        return DateTimeUtils.formatBrief(new Date()).equals(DateTimeUtils.formatBrief(this.date));
    }

    public List<FoodEntry> foodEntriesForMealName(String str) {
        return (List) this.foodEntriesByMealName.get(str);
    }

    public Date getLatestEntryTimeForMealName(String str) {
        Date date2 = null;
        if (str == null) {
            return null;
        }
        FoodEntry foodEntry = (FoodEntry) this.mealNameToLatestEntry.get(str);
        if (foodEntry != null) {
            date2 = foodEntry.getEntryTime();
        }
        return date2;
    }

    public void copyFoodEntriesFromDiaryDay(DiaryDay diaryDay, ArrayList<Long> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            processFoodEntry(diaryDay.getFoodEntry((Long) it.next()));
        }
    }

    public void copyFoodEntriesFromMealOfDiaryDayInDifferentMeal(DiaryDay diaryDay, String str, String str2) {
        List<FoodEntry> foodEntriesForMealName = diaryDay.foodEntriesForMealName(str);
        if (foodEntriesForMealName != null) {
            for (FoodEntry processFoodEntry : foodEntriesForMealName) {
                processFoodEntry(processFoodEntry, str2);
            }
        }
    }

    private void processFoodEntry(FoodEntry foodEntry) {
        processFoodEntry(foodEntry, null);
    }

    private void processFoodEntry(FoodEntry foodEntry, String str) {
        if (foodEntry != null) {
            FoodEntry foodEntry2 = new FoodEntry(foodEntry);
            foodEntry2.setDate(this.date);
            foodEntry2.setLocalId(0);
            foodEntry2.setMasterDatabaseId(0);
            if (Strings.notEmpty(str)) {
                foodEntry2.setMealName(str);
            }
            addFoodEntry(foodEntry2);
        }
    }

    public void copyExerciseEntriesFromDiaryDay(DiaryDay diaryDay, ArrayList<Long> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ExerciseEntry exerciseEntry = diaryDay.getExerciseEntry((Long) it.next());
            if (exerciseEntry != null && !exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                processExerciseEntry(exerciseEntry);
            }
        }
    }

    public void copyExerciseEntriesFromDiaryDay(DiaryDay diaryDay, int i) {
        List<ExerciseEntry> list;
        switch (i) {
            case 0:
                list = diaryDay.getCardioExerciseEntries();
                break;
            case 1:
                list = diaryDay.getStrengthExerciseEntries();
                break;
            default:
                list = diaryDay.getExerciseEntries();
                break;
        }
        if (list != null) {
            for (ExerciseEntry exerciseEntry : list) {
                if (exerciseEntry != null && !exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                    processExerciseEntry(exerciseEntry);
                }
            }
        }
    }

    private void processExerciseEntry(ExerciseEntry exerciseEntry) {
        ExerciseEntry createNewExerciseEntryFromExisting = ((DiaryService) this.diaryService.get()).createNewExerciseEntryFromExisting(exerciseEntry, this.date);
        if (createNewExerciseEntryFromExisting != null) {
            this.exerciseEntries.add(createNewExerciseEntryFromExisting);
            groupExercisesByType();
            createAdjustedNutrientGoals();
            setJustAddedExerciseEntry(createNewExerciseEntryFromExisting);
            scheduleSync();
        }
    }

    public DiaryDay initFromListOfObjects(List<BinaryApiSerializable> list) {
        setAdjustedNutrientGoals(new NutritionalValues());
        setFoodEntries(new ArrayList());
        setExerciseEntries(new ArrayList());
        setStepEntry(this.stepsEntry);
        for (BinaryApiSerializable binaryApiSerializable : list) {
            if (binaryApiSerializable instanceof DiaryDaySummaryObject) {
                DiaryDaySummaryObject diaryDaySummaryObject = (DiaryDaySummaryObject) binaryApiSerializable;
                setDate(diaryDaySummaryObject.getDate());
                this.adjustedNutrientGoals.setNutrientIndex(0, diaryDaySummaryObject.getGoalCalories());
                setFriendCaloriesBurned(diaryDaySummaryObject.getCaloriesBurned());
            } else if (binaryApiSerializable instanceof FoodEntryFromServer) {
                FoodEntry foodEntry = (FoodEntry) ((FoodEntryFromServerMapper) this.foodEntryMapper.get()).reverseMap((FoodEntryFromServer) binaryApiSerializable);
                if (foodEntry != null) {
                    this.foodEntries.add(foodEntry);
                } else {
                    Ln.e("Food entry could not be reverse mapped!", new Object[0]);
                }
            } else if (binaryApiSerializable instanceof ExerciseEntryFromServer) {
                ExerciseEntry exerciseEntry = (ExerciseEntry) ((ExerciseEntryFromServerMapper) this.exerciseV1EntryMapper.get()).reverseMap((ExerciseEntryFromServer) binaryApiSerializable);
                if (exerciseEntry != null) {
                    this.exerciseEntries.add(exerciseEntry);
                } else {
                    Ln.e("Exercise entry could not be reverse mapped!", new Object[0]);
                }
            } else if (binaryApiSerializable instanceof StepsEntryObject) {
                this.stepsEntry = (StepsEntryObject) binaryApiSerializable;
            } else if (binaryApiSerializable instanceof WaterEntryObject) {
                WaterEntry waterEntry = (WaterEntry) ((WaterEntryMapper) this.waterEntryMapper.get()).reverseMap((WaterEntryObject) binaryApiSerializable);
                if (waterEntry != null) {
                    setWaterCups(waterEntry.getCups());
                    setCurrentWaterEntry(waterEntry);
                } else {
                    Ln.e("Water entry could not be reverse mapped!", new Object[0]);
                }
            } else if (binaryApiSerializable instanceof DiaryNoteObject) {
                DiaryNoteObject diaryNoteObject = (DiaryNoteObject) binaryApiSerializable;
                DiaryNote diaryNote = (DiaryNote) ((DiaryNoteMapper) this.diaryNoteMapper.get()).reverseMap(diaryNoteObject);
                if (diaryNote != null) {
                    switch (diaryNoteObject.getNoteType()) {
                        case 0:
                            setFoodNote(diaryNote);
                            break;
                        case 1:
                            setExerciseNote(diaryNote);
                            break;
                    }
                } else {
                    Ln.e("Diary Note entry could not be reverse mapped!", new Object[0]);
                }
            }
        }
        groupExercisesByType();
        groupFoodEntriesByMealName(((Session) this.session.get()).getUser());
        return this;
    }

    public boolean hasWater() {
        LocalizedFluid localizedFluid = this.localizedWater;
        return localizedFluid != null && !localizedFluid.isZero();
    }

    public boolean hasAnyExerciseEntries() {
        return this.exerciseEntries.size() > 0;
    }

    public boolean hasAnyFoodEntries() {
        return this.foodEntries.size() > 0;
    }

    public float goalCalories() {
        return this.adjustedNutrientGoals.valueForNutrientIndex(0);
    }

    public float netCalories(boolean z) {
        return caloriesConsumed(z) - caloriesBurnedByExercise(z);
    }

    public float getNutrientGoal(int i) {
        return this.adjustedNutrientGoals.valueForNutrientIndex(i);
    }

    public float minutesOfCardioExercises() {
        Iterator it = this.cardioExerciseEntries.iterator();
        int i = 0;
        while (it.hasNext()) {
            ExerciseEntry exerciseEntry = (ExerciseEntry) it.next();
            if (!exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                i += exerciseEntry.getQuantity();
            }
        }
        return (float) i;
    }

    private void updateWidgetIfDateIsToday() {
        if (!this.isPerformingMultiAdd && isDateToday()) {
            MPFAppWidgetProvider.update(getContext());
        }
    }

    public ArrayList<FoodAnalyzerResponseData> getFoodInsights() {
        return this.foodInsights;
    }

    public FoodEntry getFoodEntry(Long l) {
        Iterator it = this.foodEntries.iterator();
        while (it.hasNext()) {
            FoodEntry foodEntry = (FoodEntry) it.next();
            if (foodEntry.localId == l.longValue()) {
                return foodEntry;
            }
        }
        return null;
    }

    public ExerciseEntry getExerciseEntry(Long l) {
        for (ExerciseEntry exerciseEntry : this.exerciseEntries) {
            if (exerciseEntry.localId == l.longValue()) {
                return exerciseEntry;
            }
        }
        return null;
    }

    public void setStepEntry(StepsEntryObject stepsEntryObject) {
        this.stepsEntry = stepsEntryObject;
    }

    public void setIsPerformingMultiAdd(boolean z) {
        this.isPerformingMultiAdd = z;
    }

    public float[] getMealPercentages(List<String> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        float[] fArr = new float[size];
        float caloriesConsumed = caloriesConsumed(false);
        for (int i = 0; i < size; i++) {
            fArr[i] = NumberUtils.getPercentage((float) totalCaloriesForMealName((String) list.get(i), false), caloriesConsumed);
        }
        return fArr;
    }

    public float getTotalNutrientConsumedForMealNameAndNutrientIndex(String str, int i) {
        List<FoodEntry> list = (List) this.foodEntriesByMealName.get(str);
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        int i2 = 0;
        for (FoodEntry nutritionValueFromFoodEntry : list) {
            i2 = (int) (((float) i2) + NutritionUtils.getNutritionValueFromFoodEntry((Session) this.session.get(), nutritionValueFromFoodEntry, i));
        }
        return (float) i2;
    }

    public void clearInsights() {
        ArrayList<FoodAnalyzerResponseData> arrayList = this.foodInsights;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public boolean isFriendExerciseCaloriesOn() {
        return this.friendCaloriesBurned != BitmapDescriptorFactory.HUE_RED;
    }

    public void setFriendCaloriesBurned(float f) {
        this.friendCaloriesBurned = f;
    }

    private void scheduleSync() {
        ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        MfpGearMessageBridge.notifyDiaryContentsUpdated(getContext());
        updateWidgetIfDateIsToday();
    }
}
