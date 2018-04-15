package com.myfitnesspal.shared.model.v1;
import lanchon.dexpatcher.annotation.*;
import android.preference.PreferenceManager;
import dagger.*;
import com.squareup.otto.*;
import javax.inject.*;
import com.myfitnesspal.shared.db.*;
import com.myfitnesspal.feature.diary.service.*;
import com.myfitnesspal.feature.externalsync.service.*;
import com.myfitnesspal.shared.api.request.*;
import com.myfitnesspal.shared.service.foods.*;
import com.myfitnesspal.feature.settings.model.*;
import com.myfitnesspal.shared.model.unitconv.*;
import com.myfitnesspal.feature.goals.service.*;
import com.myfitnesspal.shared.service.session.*;
import com.uacf.sync.engine.*;
import com.myfitnesspal.shared.service.syncv2.*;
import com.myfitnesspal.shared.service.userdata.*;
import com.myfitnesspal.shared.model.mapper.impl.*;
import com.myfitnesspal.app.*;
import com.myfitnesspal.shared.model.*;
import com.myfitnesspal.feature.tizen.service.*;
import java.util.*;
import com.myfitnesspal.feature.premium.service.*;
import com.myfitnesspal.shared.event.*;
import com.myfitnesspal.shared.provider.*;
import android.content.*;
import com.myfitnesspal.shared.model.v2.*;
import com.myfitnesspal.shared.util.*;
import com.myfitnesspal.shared.model.v15.*;
import com.myfitnesspal.shared.db.adapter.*;
import android.database.sqlite.*;
import com.uacf.core.util.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class DiaryDay extends DatabaseObject
{
	
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
    private Debouncer<FoodEntry> foodInsightDebouncer;
    private ArrayList<FoodAnalyzerResponseData> foodInsights;
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
    private int lastInsightRequestId;
    private LocalizedFluid localizedWater;
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
    
	/*
    public DiaryDay() {
        this.foodInsights = new ArrayList<FoodAnalyzerResponseData>();
        this.foodInsightDebouncer = new Debouncer<FoodEntry>(1000) {
            private List<FoodEntry> entries = new ArrayList<FoodEntry>();
            private Random random = new Random();
            
            @Override
            protected void onCalled(final FoodEntry foodEntry) {
                super.onCalled(foodEntry);
                this.entries.add(foodEntry);
            }
            
            @Override
            protected void onDebounced(final FoodEntry foodEntry) {
                DiaryDay.this.updateFoodInsights(this.entries.get(this.random.nextInt(this.entries.size())));
                this.entries.clear();
            }
        };
        MyFitnessPalApp.getInstance().component().inject(this);
    }
    
    private float calculateCaloriesBurnedByExercise(final boolean b) {
        float n = 0.0f;
        for (final ExerciseEntry exerciseEntry : this.exerciseEntries) {
            float calories;
            if (b) {
                calories = this.userEnergyService.getRoundedCurrentEnergy(exerciseEntry.getCalories());
            }
            else {
                calories = exerciseEntry.getCalories();
            }
            n += calories;
        }
        return n;
    }
    
    private List<String> getAllUserMealNames(final User user) {
        List<String> list;
        if (this.allUserMealNames == null) {
            list = user.getMealNames().getNames();
        }
        else {
            list = this.allUserMealNames;
        }
        return list;
    }
    
    private void groupFoodEntriesByMealName(final User user) {
        Hashtable<Object, ArrayList<FoodEntry>> foodEntriesByMealName = null;
        ArrayList<String> list = null;
        Label_0209: {
            try {
                foodEntriesByMealName = new Hashtable<Object, ArrayList<FoodEntry>>(10);
                list = new ArrayList<String>(10);
                new ArrayList(10);
            Label_0162_Outer:
                for (final FoodEntry foodEntry : this.foodEntries) {
                    final String mealName = foodEntry.getMealName();
                    ArrayList<FoodEntry> list2;
                    if ((list2 = foodEntriesByMealName.get(mealName)) == null) {
                        list2 = new ArrayList<FoodEntry>(4);
                        foodEntriesByMealName.put(mealName, list2);
                    }
                    list2.add(foodEntry);
                    final boolean b = false;
                    final Iterator<String> iterator2 = list.iterator();
                    while (true) {
                        do {
                            final boolean b2 = b;
                            if (iterator2.hasNext()) {
                                continue Label_0162_Outer;
                            }
                            if (!b2) {
                                list.add(mealName);
                                continue Label_0162_Outer;
                            }
                            continue Label_0162_Outer;
                        } while (!((String)iterator2.next()).equals(mealName));
                        final boolean b2 = true;
                        continue;
                    }
                }
                break Label_0209;
            }
            catch (Exception ex) {
                Ln.e("Exception occured: " + ex.getMessage(), new Object[0]);
            }
            return;
        }
        this.mealNames = this.sortMealNames(list, user);
        this.foodEntriesByMealName = (Map<String, ArrayList<FoodEntry>>)foodEntriesByMealName;
    }
    
    private boolean isDateToday() {
        return DateTimeUtils.isSameDay(this.getDate(), new Date());
    }
    
    private void processExerciseEntry(ExerciseEntry newExerciseEntryFromExisting) {
        newExerciseEntryFromExisting = ((DiaryService)this.diaryService.get()).createNewExerciseEntryFromExisting(newExerciseEntryFromExisting, this.date);
        if (newExerciseEntryFromExisting != null) {
            this.exerciseEntries.add(newExerciseEntryFromExisting);
            this.groupExercisesByType();
            this.createAdjustedNutrientGoals();
            this.setJustAddedExerciseEntry(newExerciseEntryFromExisting);
            this.scheduleSync();
        }
    }
    
    private void processFoodEntry(final FoodEntry foodEntry) {
        this.processFoodEntry(foodEntry, null);
    }
    
    private void processFoodEntry(FoodEntry foodEntry, final String mealName) {
        if (foodEntry != null) {
            foodEntry = new FoodEntry(foodEntry);
            foodEntry.setDate(this.date);
            foodEntry.setLocalId(0L);
            foodEntry.setMasterDatabaseId(0L);
            if (Strings.notEmpty(mealName)) {
                foodEntry.setMealName(mealName);
            }
            this.addFoodEntry(foodEntry);
        }
    }
    
    private void scheduleSync() {
        ((UacfScheduler<SyncType>)this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        MfpGearMessageBridge.notifyDiaryContentsUpdated(this.getContext());
        this.updateWidgetIfDateIsToday();
    }
    
    private MealNames sortMealNames(final List<String> list, final User user) {
        final List<String> allUserMealNames = this.getAllUserMealNames(user);
        final ArrayList<String> list2 = new ArrayList<String>(CollectionUtils.size(list));
        MealNames mealNames;
        if (CollectionUtils.notEmpty(allUserMealNames)) {
        Label_0099_Outer:
            for (final String s : allUserMealNames) {
                final boolean b = false;
                final Iterator<String> iterator2 = list.iterator();
                while (true) {
                    do {
                        final boolean b2 = b;
                        if (iterator2.hasNext()) {
                            continue Label_0099_Outer;
                        }
                        if (b2) {
                            list2.add(s);
                            continue Label_0099_Outer;
                        }
                        continue Label_0099_Outer;
                    } while (!Strings.equals(s, iterator2.next()));
                    final boolean b2 = true;
                    continue;
                }
            }
            mealNames = new MealNames(list2);
        }
        else {
            mealNames = new MealNames(list);
        }
        return mealNames;
    }
    
    private void updateFoodInsights(final FoodEntry foodEntry) {
        if (((InsightSettings)this.insightSettings.get()).areInsightsEnabled()) {
            this.lastInsightRequestId = ((FoodService)this.foodService.get()).getFoodInsightAsync(this.adjustedNutrientGoals, this.foodEntries, foodEntry, this.userEnergyService, this.userWeightService, this.userDistanceService, this.caloriesBurnedByExercise(false, ((PremiumService)this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AssignExerciseCalories)), new Function2<List<FoodAnalyzerResponseData>, Integer>() {
                @Override
                public void execute(final List<FoodAnalyzerResponseData> list, final Integer n) {
                    if (n == DiaryDay.this.lastInsightRequestId) {
                        if (CollectionUtils.notEmpty(list)) {
                            DiaryDay.this.foodInsights.add(list.get(0));
                        }
                        ((Bus)DiaryDay.this.bus.get()).post(new InsightsChangedEvent());
                    }
                }
            });
        }
    }
    
    private void updateWidgetIfDateIsToday() {
        if (!this.isPerformingMultiAdd && this.isDateToday()) {
            MPFAppWidgetProvider.update(this.getContext());
        }
    }
    
    public String JustAddedPrimaryText() {
        return this.justAddedPrimaryText;
    }
    
    public void addExerciseEntry(ExerciseEntry newV1ExerciseEntryLocally) {
        newV1ExerciseEntryLocally = ((DiaryService)this.diaryService.get()).createNewV1ExerciseEntryLocally(newV1ExerciseEntryLocally);
        this.exerciseEntries.add(newV1ExerciseEntryLocally);
        this.groupExercisesByType();
        this.createAdjustedNutrientGoals();
        this.setJustAddedExerciseEntry(newV1ExerciseEntryLocally);
        this.scheduleSync();
    }
    
    public void addExerciseEntry(final MfpExerciseEntry mfpExerciseEntry) {
        if (mfpExerciseEntry != null) {
            this.addExerciseEntry(((ExerciseEntryMapper)this.exerciseEntryMapper.get()).reverseMap(mfpExerciseEntry));
        }
    }
    
    public void addFoodEntry(final FoodEntry justAddedFoodEntry) {
        final Context context = this.getContext();
        new FoodDBAdapter(context, this.dbConnectionManager.get()).insertFoodIfMissing(justAddedFoodEntry.getFood(), this.dbConnectionManager.get());
        new FoodEntriesDBAdapter(context, this.dbConnectionManager.get()).insertFoodEntry(justAddedFoodEntry, this.dbConnectionManager.get());
        this.foodEntries.add(justAddedFoodEntry);
        this.groupFoodEntriesByMealName(((Session)this.session.get()).getUser());
        this.createAdjustedNutrientGoals();
        this.justAddedFoodEntry = justAddedFoodEntry;
        this.foodInsightDebouncer.call(justAddedFoodEntry);
        this.setJustAddedPrimaryText(justAddedFoodEntry.getFood().getDescription());
        this.scheduleSync();
    }
    
    public void addV2Food(final MfpFood mfpFood, final String s) {
        this.addFoodEntry(mfpFood.toFoodEntry(((Session)this.session.get()).getUser(), s, this.date));
    }
    */
	@DexReplace
    public float amountOfNutrientConsumed(final int n) {
        float n2 = 0.0f;
        final Iterator<FoodEntry> iterator = this.foodEntries.iterator();
        while (iterator.hasNext()) {
            n2 += NutritionUtils.getNutritionValueFromFoodEntry(this.session.get(), iterator.next(), n);
        }
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
		if (n2 == 9 && prefs.getBoolean("show_net_carbs", false))
		{
			float fiber = amountOfNutrientConsumed(10);
			float calories = amountOfNutrientConsumed(0);
			float fat = amountOfNutrientConsumed(1);
			float carbs = amountOfNutrientConsumed(9);
            if (prefs.getBoolean("show_smart_carbs", false))
            {
                n2 = Math.min(n2, Math.max(n2 - fiber, (calories - 9.0f* fat - 4.0f*carbs)/4.0f));
            }
            else
            {
                n2 = Math.min(n2, n2 - fiber);
            }
		}
        return n2;
    }
    /*
    public float caloriesBurnedByExercise(final boolean b) {
        return this.caloriesBurnedByExercise(b, false);
    }
    
    public float caloriesBurnedByExercise(final boolean b, final boolean b2) {
        float n;
        if (!b2) {
            n = this.calculateCaloriesBurnedByExercise(b);
        }
        else {
            final MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService)this.nutrientGoalService.get()).getDailyGoalForDayOfWeekSync(((Session)this.session.get()).getUser().getActiveDate());
            if (dailyGoalForDayOfWeekSync == null || dailyGoalForDayOfWeekSync.isAssignExerciseEnergyOn()) {
                n = this.calculateCaloriesBurnedByExercise(b);
            }
            else {
                n = 0.0f;
            }
        }
        return n;
    }
    
    public float caloriesConsumed(final boolean b) {
        float n = 0.0f;
        for (final FoodEntry foodEntry : this.foodEntries) {
            float n2;
            if (b) {
                n2 = this.userEnergyService.getRoundedCurrentEnergy(foodEntry.getCaloriesValue());
            }
            else {
                n2 = foodEntry.roundedCalories();
            }
            n += n2;
        }
        return n;
    }
    
    public void clearInsights() {
        if (this.foodInsights != null) {
            this.foodInsights.clear();
        }
    }
    
    public void copyExerciseEntriesFromDiaryDay(final DiaryDay diaryDay, final int n) {
        List<ExerciseEntry> list = null;
        switch (n) {
            default: {
                list = diaryDay.getExerciseEntries();
                break;
            }
            case 0: {
                list = diaryDay.getCardioExerciseEntries();
                break;
            }
            case 1: {
                list = diaryDay.getStrengthExerciseEntries();
                break;
            }
        }
        if (list != null) {
            for (final ExerciseEntry exerciseEntry : list) {
                if (exerciseEntry != null && !exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                    this.processExerciseEntry(exerciseEntry);
                }
            }
        }
    }
    
    public void copyExerciseEntriesFromDiaryDay(final DiaryDay diaryDay, final ArrayList<Long> list) {
        final Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()) {
            final ExerciseEntry exerciseEntry = diaryDay.getExerciseEntry(iterator.next());
            if (exerciseEntry != null && !exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                this.processExerciseEntry(exerciseEntry);
            }
        }
    }
    
    public void copyFoodEntriesFromDiaryDay(final DiaryDay diaryDay, final ArrayList<Long> list) {
        final Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.processFoodEntry(diaryDay.getFoodEntry((Long)iterator.next()));
        }
    }
    
    public void copyFoodEntriesFromMealOfDiaryDayInDifferentMeal(final DiaryDay diaryDay, final String s, final String s2) {
        final FoodEntry[] foodEntriesForMealName = diaryDay.foodEntriesForMealName(s);
        if (foodEntriesForMealName != null) {
            for (int length = foodEntriesForMealName.length, i = 0; i < length; ++i) {
                this.processFoodEntry(foodEntriesForMealName[i], s2);
            }
        }
    }
    
    public void createAdjustedNutrientGoals() {
        try {
            final User user = ((Session)this.session.get()).getUser();
            final NutritionalValues nutritionalValues = ((NutrientGoalService)this.nutrientGoalService.get()).getCachedDefaultGoal().toNutritionalValues(this.userEnergyService);
            if (nutritionalValues != null) {
                nutritionalValues.clampNegativesToZero();
                Lazy<NutrientGoalService> nutrientGoalService;
                if (((PremiumService)this.premiumService.get()).isFeatureSubscribed(PremiumFeature.AssignExerciseCalories)) {
                    nutrientGoalService = this.nutrientGoalService;
                }
                else {
                    nutrientGoalService = null;
                }
                this.adjustedNutrientGoals = nutritionalValues.adjustForCaloriesEarnedBasedOnPreference(nutrientGoalService, this.caloriesBurnedByExercise(false), user.getActiveDate());
            }
        }
        catch (Exception ex) {
            Ln.e(ex);
        }
    }
    
    public void deleteExerciseEntry(final ExerciseEntry exerciseEntry) {
        ((DiaryService)this.diaryService.get()).deleteExerciseEntryLocally(exerciseEntry);
        this.exerciseEntries.remove(exerciseEntry);
        this.groupExercisesByType();
        this.createAdjustedNutrientGoals();
        this.scheduleSync();
    }
    
    public void deleteFoodEntry(final FoodEntry foodEntry) {
        new FoodEntriesDBAdapter(this.getContext(), this.dbConnectionManager.get()).deleteFoodEntry(foodEntry, this.externalNutritionService);
        this.foodEntries.remove(foodEntry);
        this.groupFoodEntriesByMealName(((Session)this.session.get()).getUser());
        this.createAdjustedNutrientGoals();
        this.scheduleSync();
    }
    
    public FoodEntry[] foodEntriesForMealName(final String s) {
        return ((ArrayList<FoodEntry>)this.foodEntriesByMealName.get(s)).toArray(new FoodEntry[this.foodEntriesByMealName.size()]);
    }
    
    public NutritionalValues getAdjustedNutrientGoals() {
        return this.adjustedNutrientGoals;
    }
    
    public ArrayList<ExerciseEntry> getCardioExerciseEntries() {
        return this.cardioExerciseEntries;
    }
    
    public WaterEntry getCurrentWaterEntry() {
        return this.currentWaterEntry;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public List<ExerciseEntry> getExerciseEntries() {
        return this.exerciseEntries;
    }
    
    public ExerciseEntry getExerciseEntry(final Long n) {
        for (final ExerciseEntry exerciseEntry : this.exerciseEntries) {
            if (exerciseEntry.localId == n) {
                return exerciseEntry;
            }
        }
        return null;
    }
    
    public DiaryNote getExerciseNote() {
        return this.exerciseNote;
    }
    
    public ArrayList<FoodEntry> getFoodEntries() {
        return this.foodEntries;
    }
    
    public Map<String, ArrayList<FoodEntry>> getFoodEntriesByMealName() {
        return this.foodEntriesByMealName;
    }
    
    public List<FoodEntry> getFoodEntriesForMealName(final String s) {
        List<FoodEntry> list;
        if (CollectionUtils.isEmpty(this.foodEntriesByMealName)) {
            list = null;
        }
        else {
            list = this.foodEntriesByMealName.get(s);
        }
        return list;
    }
    
    public FoodEntry getFoodEntry(final Long n) {
        for (final FoodEntry foodEntry : this.foodEntries) {
            if (foodEntry.localId == n) {
                return foodEntry;
            }
        }
        return null;
    }
    
    public ArrayList<FoodAnalyzerResponseData> getFoodInsights() {
        return this.foodInsights;
    }
    
    public DiaryNote getFoodNote() {
        return this.foodNote;
    }
    
    public ExerciseEntry getJustAddedExerciseEntry() {
        return this.justAddedExerciseEntry;
    }
    
    public FoodEntry getJustAddedFoodEntry() {
        return this.justAddedFoodEntry;
    }
    
    public String getJustAddedMultipleItemsMealName() {
        return this.justAddedMultipleItemsMealName;
    }
    
    public LocalizedFluid getLocalizedWater() {
        return this.localizedWater;
    }
    
    public List<String> getMealNames() {
        List<String> names;
        if (this.mealNames != null) {
            names = this.mealNames.getNames();
        }
        else {
            names = null;
        }
        return names;
    }
    
    public float[] getMealPercentages(final List<String> list) {
        final int size = CollectionUtils.size(list);
        final float[] array = new float[size];
        final float caloriesConsumed = this.caloriesConsumed(false);
        for (int i = 0; i < size; ++i) {
            array[i] = NumberUtils.getPercentage(this.totalCaloriesForMealName(list.get(i), false), caloriesConsumed);
        }
        return array;
    }
    
    public float getNutrientGoal(final int n) {
        return this.adjustedNutrientGoals.valueForNutrientIndex(n);
    }
    
    public ArrayList<ExerciseEntry> getStrengthExerciseEntries() {
        return this.strengthExerciseEntries;
    }
    
    public float getTotalNutrientConsumedForMealNameAndNutrientIndex(final String s, final int n) {
        final ArrayList<FoodEntry> list = (ArrayList<FoodEntry>)this.foodEntriesByMealName.get(s);
        float n2;
        if (CollectionUtils.isEmpty(list)) {
            n2 = 0.0f;
        }
        else {
            int n3 = 0;
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                n3 += NutritionUtils.getNutritionValueFromFoodEntry(this.session.get(), iterator.next(), n);
            }
            n2 = n3;
        }
        return n2;
    }
    
    public int getWaterCups() {
        return (int)this.localizedWater.getValue(UnitsUtils.Water.CUPS);
    }
    
    public float goalCalories() {
        return this.adjustedNutrientGoals.valueForNutrientIndex(0);
    }
    
    public void groupExercisesByType() {
        ArrayList<ExerciseEntry> cardioExerciseEntries = null;
        ArrayList<ExerciseEntry> strengthExerciseEntries = null;
        Label_0131: {
        Label_0030_Outer:
            while (true) {
                while (true) {
                    Label_0121: {
                        try {
                            cardioExerciseEntries = new ArrayList<ExerciseEntry>(10);
                            strengthExerciseEntries = new ArrayList<ExerciseEntry>(10);
                            for (final ExerciseEntry exerciseEntry : this.exerciseEntries) {
                                switch (exerciseEntry.exerciseType()) {
                                    default: {
                                        continue Label_0030_Outer;
                                    }
                                    case 0: {
                                        cardioExerciseEntries.add(exerciseEntry);
                                        continue Label_0030_Outer;
                                    }
                                    case 1: {
                                        break Label_0121;
                                    }
                                }
                            }
                            break Label_0131;
                        }
                        catch (Exception ex) {
                            Ln.e("Exception occured: " + ex.getMessage(), new Object[0]);
                        }
                        break;
                    }
                    final ExerciseEntry exerciseEntry;
                    strengthExerciseEntries.add(exerciseEntry);
                    continue;
                }
            }
            return;
        }
        this.cardioExerciseEntries = cardioExerciseEntries;
        this.strengthExerciseEntries = strengthExerciseEntries;
    }
    
    public boolean hasAnyExerciseEntries() {
        return this.exerciseEntries.size() > 0;
    }
    
    public boolean hasAnyFoodEntries() {
        return this.foodEntries.size() > 0;
    }
    
    public boolean hasAnyNotes() {
        return this.foodNote != null || this.exerciseNote != null;
    }
    
    public boolean hasWater() {
        return this.localizedWater != null && !this.localizedWater.isZero();
    }
    
    public DiaryDay initFromDatabaseForDate(final Date date) {
        this.date = date;
        this.loadFromDatabase();
        return this;
    }
    
    public DiaryDay initFromListOfObjects(final List<BinaryApiSerializable> list) {
        this.setAdjustedNutrientGoals(new NutritionalValues());
        this.setFoodEntries(new ArrayList<FoodEntry>());
        this.setExerciseEntries(new ArrayList<ExerciseEntry>());
        this.setStepEntry(this.stepsEntry);
        for (final BinaryApiSerializable binaryApiSerializable : list) {
            if (binaryApiSerializable instanceof DiaryDaySummaryObject) {
                final DiaryDaySummaryObject diaryDaySummaryObject = (DiaryDaySummaryObject)binaryApiSerializable;
                this.setDate(diaryDaySummaryObject.getDate());
                this.adjustedNutrientGoals.setNutrientIndex(0, diaryDaySummaryObject.getGoalCalories());
                this.setFriendCaloriesBurned(diaryDaySummaryObject.getCaloriesBurned());
            }
            else if (binaryApiSerializable instanceof FoodEntryFromServer) {
                final FoodEntry foodEntry = (FoodEntry)((FoodEntryFromServerMapper)this.foodEntryMapper.get()).reverseMap((FoodEntryFromServer)binaryApiSerializable);
                if (foodEntry != null) {
                    this.foodEntries.add(foodEntry);
                }
                else {
                    Ln.e("Food entry could not be reverse mapped!", new Object[0]);
                }
            }
            else if (binaryApiSerializable instanceof ExerciseEntryFromServer) {
                final ExerciseEntry exerciseEntry = (ExerciseEntry)((ExerciseEntryFromServerMapper)this.exerciseV1EntryMapper.get()).reverseMap((ExerciseEntryFromServer)binaryApiSerializable);
                if (exerciseEntry != null) {
                    this.exerciseEntries.add(exerciseEntry);
                }
                else {
                    Ln.e("Exercise entry could not be reverse mapped!", new Object[0]);
                }
            }
            else if (binaryApiSerializable instanceof StepsEntryObject) {
                this.stepsEntry = (StepsEntryObject)binaryApiSerializable;
            }
            else if (binaryApiSerializable instanceof WaterEntryObject) {
                final WaterEntry currentWaterEntry = (WaterEntry)((WaterEntryMapper)this.waterEntryMapper.get()).reverseMap((WaterEntryObject)binaryApiSerializable);
                if (currentWaterEntry != null) {
                    this.setWaterCups(currentWaterEntry.getCups());
                    this.setCurrentWaterEntry(currentWaterEntry);
                }
                else {
                    Ln.e("Water entry could not be reverse mapped!", new Object[0]);
                }
            }
            else {
                if (!(binaryApiSerializable instanceof DiaryNoteObject)) {
                    continue;
                }
                final DiaryNoteObject diaryNoteObject = (DiaryNoteObject)binaryApiSerializable;
                final DiaryNote diaryNote = (DiaryNote)((DiaryNoteMapper)this.diaryNoteMapper.get()).reverseMap(diaryNoteObject);
                if (diaryNote != null) {
                    switch (diaryNoteObject.getNoteType()) {
                        default: {
                            continue;
                        }
                        case 0: {
                            this.setFoodNote(diaryNote);
                            continue;
                        }
                        case 1: {
                            this.setExerciseNote(diaryNote);
                            continue;
                        }
                    }
                }
                else {
                    Ln.e("Diary Note entry could not be reverse mapped!", new Object[0]);
                }
            }
        }
        this.groupExercisesByType();
        this.groupFoodEntriesByMealName(((Session)this.session.get()).getUser());
        return this;
    }
    
    public boolean isEmpty() {
        return this.foodEntries.size() == 0 && this.exerciseEntries.size() == 0 && this.localizedWater.isZero() && !this.hasAnyNotes();
    }
    
    public boolean isForToday() {
        return DateTimeUtils.formatBrief(new Date()).equals(DateTimeUtils.formatBrief(this.date));
    }
    
    public boolean isFriendExerciseCaloriesOn() {
        return this.friendCaloriesBurned != 0.0f;
    }
    
    public boolean justAddedExerciseNote() {
        return this.justAddedExerciseNote;
    }
    
    public boolean justAddedFoodNote() {
        return this.justAddedFoodNote;
    }
    
    public boolean justAddedMultipleItems() {
        return this.justAddedMultipleItems;
    }
    
    public WaterEntry justAddedWaterEntry() {
        return this.justAddedWaterEntry;
    }
    
    public boolean justCopiedFromDate() {
        return this.justCopiedFromDate;
    }
    
    public void loadFromDatabase() {
        while (true) {
            while (true) {
                float milliliters;
                try {
                    final Context context = this.getContext();
                    this.setFoodEntries(new FoodEntriesDBAdapter(context, this.dbConnectionManager.get()).fetchFoodEntriesOnDate(this.date));
                    this.exerciseEntries = ((DiaryService)this.diaryService.get()).getV1ExerciseEntriesForDate(this.date);
                    this.currentWaterEntry = new WaterEntriesDBAdapter(context).fetchWaterEntryOnDate(this.date);
                    if (this.currentWaterEntry == null) {
                        this.localizedWater = LocalizedFluid.fromMilliliters(0.0f);
                    }
                    else {
                        final float cups = this.currentWaterEntry.getCups();
                        milliliters = this.currentWaterEntry.getMilliliters();
                        if (cups <= 0.0f || milliliters != 0.0f) {}
                        this.localizedWater = LocalizedFluid.fromCups(cups);
                    }
                    this.justAddedFoodEntry = null;
                    this.justAddedExerciseEntry = null;
                    this.justAddedExerciseEntry = null;
                    this.justAddedMultipleItems = false;
                    this.justAddedWaterEntry = null;
                    this.justCopiedFromDate = false;
                    this.groupExercisesByType();
                    this.groupFoodEntriesByMealName(((Session)this.session.get()).getUser());
                    this.createAdjustedNutrientGoals();
                    this.foodNote = null;
                    this.exerciseNote = null;
                    this.loadNotes();
                    return;
                }
                catch (SQLiteException ex) {
                    Ln.e("Exception occured: " + ex.getMessage(), new Object[0]);
                    return;
                }
                this.localizedWater = LocalizedFluid.fromMilliliters(milliliters);
                continue;
            }
        }
    }
    
    public void loadNotes() {
    Label_0045_Outer:
        while (true) {
            while (true) {
                Label_0126: {
                    try {
                        final ArrayList<DiaryNote> fetchDiaryNotesOnDate = ((DbConnectionManager)this.dbConnectionManager.get()).diaryNoteDbAdapter().fetchDiaryNotesOnDate(this.date);
                        this.foodNote = null;
                        this.exerciseNote = null;
                        if (CollectionUtils.notEmpty(fetchDiaryNotesOnDate)) {
                            for (final DiaryNote diaryNote : fetchDiaryNotesOnDate) {
                                if (diaryNote.getBody() == null || diaryNote.getBody().length() != 0) {
                                    switch (diaryNote.getNoteType()) {
                                        default: {
                                            continue Label_0045_Outer;
                                        }
                                        case 0: {
                                            this.foodNote = diaryNote;
                                            continue Label_0045_Outer;
                                        }
                                        case 1: {
                                            break Label_0126;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        Ln.e(ex);
                    }
                    break;
                }
                final DiaryNote diaryNote;
                this.exerciseNote = diaryNote;
                continue;
            }
        }
    }
    
    public float minutesOfCardioExercises() {
        int n = 0;
        for (final ExerciseEntry exerciseEntry : this.cardioExerciseEntries) {
            if (!exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                n += exerciseEntry.getQuantity();
            }
        }
        return n;
    }
    
    public float netCalories(final boolean b) {
        return this.caloriesConsumed(b) - this.caloriesBurnedByExercise(b);
    }
    
    public void setAdjustedNutrientGoals(final NutritionalValues adjustedNutrientGoals) {
        this.adjustedNutrientGoals = adjustedNutrientGoals;
    }
    
    public void setAllUserMealNames(final List<String> allUserMealNames) {
        this.allUserMealNames = allUserMealNames;
    }
    
    public void setCardioExerciseEntries(final ArrayList<ExerciseEntry> cardioExerciseEntries) {
        this.cardioExerciseEntries = cardioExerciseEntries;
    }
    
    public void setCurrentWaterEntry(final WaterEntry currentWaterEntry) {
        this.currentWaterEntry = currentWaterEntry;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
    
    public void setExerciseEntries(final List<ExerciseEntry> exerciseEntries) {
        this.exerciseEntries = exerciseEntries;
    }
    
    public void setExerciseNote(final DiaryNote exerciseNote) {
        this.exerciseNote = exerciseNote;
    }
    
    public void setFoodEntries(ArrayList<FoodEntry> foodEntries) {
        if (foodEntries == null) {
            foodEntries = new ArrayList<FoodEntry>();
        }
        this.foodEntries = foodEntries;
    }
    
    public void setFoodEntriesByMealName(final Map<String, ArrayList<FoodEntry>> foodEntriesByMealName) {
        this.foodEntriesByMealName = foodEntriesByMealName;
    }
    
    public void setFoodNote(final DiaryNote foodNote) {
        this.foodNote = foodNote;
    }
    
    public void setFriendCaloriesBurned(final float friendCaloriesBurned) {
        this.friendCaloriesBurned = friendCaloriesBurned;
    }
    
    public void setIsPerformingMultiAdd(final boolean isPerformingMultiAdd) {
        this.isPerformingMultiAdd = isPerformingMultiAdd;
    }
    
    public void setJustAddedExerciseEntry(final ExerciseEntry justAddedExerciseEntry) {
        this.justAddedExerciseEntry = justAddedExerciseEntry;
    }
    
    public void setJustAddedExerciseNote(final boolean justAddedExerciseNote) {
        this.justAddedExerciseNote = justAddedExerciseNote;
    }
    
    public void setJustAddedFoodEntry(final FoodEntry justAddedFoodEntry) {
        this.justAddedFoodEntry = justAddedFoodEntry;
    }
    
    public void setJustAddedFoodNote(final boolean justAddedFoodNote) {
        this.justAddedFoodNote = justAddedFoodNote;
    }
    
    public void setJustAddedMultipleItems(final boolean justAddedMultipleItems) {
        this.justAddedMultipleItems = justAddedMultipleItems;
    }
    
    public void setJustAddedMultipleItemsMealName(final String justAddedMultipleItemsMealName) {
        this.justAddedMultipleItemsMealName = justAddedMultipleItemsMealName;
    }
    
    public void setJustAddedPrimaryText(final String justAddedPrimaryText) {
        this.justAddedPrimaryText = justAddedPrimaryText;
    }
    
    public void setJustAddedWaterEntry(final WaterEntry justAddedWaterEntry) {
        this.justAddedWaterEntry = justAddedWaterEntry;
    }
    
    public void setJustCopiedFromDate(final boolean justCopiedFromDate) {
        this.justCopiedFromDate = justCopiedFromDate;
    }
    
    public void setLocalizedWater(final LocalizedFluid localizedWater) {
        this.localizedWater = localizedWater;
    }
    
    public void setLocalizedWaterEntry(final float n, final UnitsUtils.Water water) {
        this.localizedWater = LocalizedFluid.from(water, n);
        final WaterEntry waterEntry = new WaterEntry(this.localizedWater);
        waterEntry.setEntryDate(this.date);
        new WaterEntriesDBAdapter(this.getContext()).insertOrUpdateWaterEntry(waterEntry);
        this.setJustAddedWaterEntry(waterEntry);
        this.setCurrentWaterEntry(waterEntry);
        this.scheduleSync();
    }
    
    public void setStepEntry(final StepsEntryObject stepsEntry) {
        this.stepsEntry = stepsEntry;
    }
    
    public void setStrengthExerciseEntries(final ArrayList<ExerciseEntry> strengthExerciseEntries) {
        this.strengthExerciseEntries = strengthExerciseEntries;
    }
    
    public void setWaterCups(final float n) {
        final LocalizedFluid localizedWater = this.localizedWater;
        LocalizedFluid.fromCups(n);
    }
    
    public void setWaterEntry(final int n) {
        this.setLocalizedWaterEntry(n, UnitsUtils.Water.CUPS);
    }
    
    public int totalCaloriesForExercises() {
        int n;
        if (CollectionUtils.isEmpty(this.exerciseEntries)) {
            n = 0;
        }
        else {
            int n2 = 0;
            final Iterator<ExerciseEntry> iterator = this.exerciseEntries.iterator();
            while (true) {
                n = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                n2 += this.userEnergyService.getRoundedCurrentEnergy(((ExerciseEntry)iterator.next()).getCalories());
            }
        }
        return n;
    }
    
    public int totalCaloriesForMealName(final String s) {
        return this.totalCaloriesForMealName(s, true);
    }
    
    public int totalCaloriesForMealName(final String s, final boolean b) {
        final ArrayList<FoodEntry> list = (ArrayList<FoodEntry>)this.foodEntriesByMealName.get(s);
        int n = 0;
        if (CollectionUtils.isEmpty(list)) {
            n = 0;
        }
        else {
            for (final FoodEntry foodEntry : list) {
                final float n2 = (float)n;
                float caloriesValue;
                if (b) {
                    caloriesValue = this.userEnergyService.getRoundedCurrentEnergy(foodEntry.getCaloriesValue());
                }
                else {
                    caloriesValue = foodEntry.getCaloriesValue();
                }
                n = (int)(caloriesValue + n2);
            }
        }
        return n;
    }
    
    public float totalNutrientValueForMealName(final String s, final int n) {
        final float n2 = 0.0f;
        final ArrayList<FoodEntry> list = (ArrayList<FoodEntry>)this.foodEntriesByMealName.get(s);
        float n3 = 0.0f;
        if (CollectionUtils.isEmpty(list)) {
            n3 = n2;
        }
        else {
            final Iterator<FoodEntry> iterator = list.iterator();
            while (iterator.hasNext()) {
                final float amountOfNutrientIndex = ((FoodEntry)iterator.next()).amountOfNutrientIndex(n);
                if (amountOfNutrientIndex > 0.0f) {
                    n3 += amountOfNutrientIndex;
                }
            }
        }
        return n3;
    }
    
    public void updateExerciseEntry(final ExerciseEntry exerciseEntry) {
        final ExerciseEntry updateV1ExerciseEntryLocally = ((DiaryService)this.diaryService.get()).updateV1ExerciseEntryLocally(exerciseEntry);
        final int index = Enumerable.indexOf(this.exerciseEntries, exerciseEntry.getLocalId(), new ReturningFunction1<Long, ExerciseEntry>() {
            @Override
            public Long execute(final ExerciseEntry exerciseEntry) {
                return exerciseEntry.getLocalId();
            }
        });
        if (index >= 0) {
            this.exerciseEntries.set(index, updateV1ExerciseEntryLocally);
        }
        this.createAdjustedNutrientGoals();
        this.scheduleSync();
    }
    
    public void updateFoodEntry(final FoodEntry foodEntry) {
        ((DbConnectionManager)this.dbConnectionManager.get()).foodEntriesDbAdapter().deleteFoodEntry(foodEntry, this.externalNutritionService);
        foodEntry.setMasterDatabaseId(0L);
        ((DbConnectionManager)this.dbConnectionManager.get()).foodEntriesDbAdapter().insertFoodEntry(foodEntry, this.dbConnectionManager.get());
        this.createAdjustedNutrientGoals();
        this.scheduleSync();
    }
	*/
}
