package com.myfitnesspal.shared.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpVersionedDatabaseObjectV2;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

public class MultiAddFoodHelper {
    private static final String MEAL_NAME = "(Meal)";
    private List<DiaryEntryCellModel> externalItems = new ArrayList();
    private Set<FoodV2Logging> foodV2LoggingSet = new HashSet();
    private Subject<Boolean> isMultiAddEnabledSubject = PublishSubject.create();
    private boolean isMultiAddModeOn;
    private Subject<Boolean> itemsChangedSubject = PublishSubject.create();
    private Map<String, DiaryEntryCellModel> localIdToSelectedItemsMap = new HashMap();
    private Lazy<LocalSettingsService> localSettingsService;
    private Map<String, DiaryEntryCellModel> masterIdToSelectedItemsMap = new HashMap();
    private Map<String, DiaryEntryCellModel> selectedMealEntriesMap = new HashMap();
    private Map<String, DiaryEntryCellModel> uidToSelectedItemsMap = new HashMap();

    public MultiAddFoodHelper(Lazy<LocalSettingsService> lazy) {
        this.localSettingsService = lazy;
    }

    public boolean isMultiAddModeOn() {
        return this.isMultiAddModeOn;
    }

    public void enable() {
        this.isMultiAddModeOn = true;
        this.isMultiAddEnabledSubject.onNext(Boolean.valueOf(true));
    }

    public void disable() {
        this.isMultiAddModeOn = false;
        this.uidToSelectedItemsMap.clear();
        this.localIdToSelectedItemsMap.clear();
        this.masterIdToSelectedItemsMap.clear();
        this.selectedMealEntriesMap.clear();
        this.foodV2LoggingSet.clear();
        this.externalItems.clear();
        this.isMultiAddEnabledSubject.onNext(Boolean.valueOf(false));
    }

    public DiaryEntryCellModel getItemWithSameId(DiaryEntryCellModel diaryEntryCellModel) {
        if (diaryEntryCellModel == null) {
            return null;
        }
        Tuple2 validKeyAndMap = getValidKeyAndMap(diaryEntryCellModel);
        if (validKeyAndMap == null) {
            return null;
        }
        return (DiaryEntryCellModel) ((Map) validKeyAndMap.getItem2()).get((String) validKeyAndMap.getItem1());
    }

    public DiaryEntryCellModel getItemWithSameId(MfpVersionedDatabaseObjectV2 mfpVersionedDatabaseObjectV2) {
        if (mfpVersionedDatabaseObjectV2 == null) {
            return null;
        }
        Tuple2 validKeyAndMap = getValidKeyAndMap(mfpVersionedDatabaseObjectV2.getLocalId(), mfpVersionedDatabaseObjectV2.getMasterId(), mfpVersionedDatabaseObjectV2.getVersion());
        if (validKeyAndMap == null) {
            return null;
        }
        return (DiaryEntryCellModel) ((Map) validKeyAndMap.getItem2()).get((String) validKeyAndMap.getItem1());
    }

    public boolean containsItem(DiaryEntryCellModel diaryEntryCellModel) {
        return checkForItem(diaryEntryCellModel, false);
    }

    private boolean checkForItem(DiaryEntryCellModel diaryEntryCellModel, boolean z) {
        Tuple2 validKeyAndMap = getValidKeyAndMap(diaryEntryCellModel);
        if (validKeyAndMap == null) {
            return false;
        }
        String str = (String) validKeyAndMap.getItem1();
        Map map = (Map) validKeyAndMap.getItem2();
        boolean containsKey = map.containsKey(str);
        if (z) {
            map.remove(str);
        }
        return containsKey;
    }

    public boolean addAndLogItemsFromActivityResultData(Bundle bundle, Lazy<FoodEditorAnalytics> lazy) {
        FoodEntry foodEntry = (FoodEntry) BundleUtils.getParcelable(bundle, "food_entry", FoodEntry.class.getClassLoader());
        FoodV2Logging foodV2Logging = (FoodV2Logging) BundleUtils.getParcelable(bundle, Extras.FOOD_V2_LOGGING, FoodV2Logging.class.getClassLoader());
        ArrayList parcelableArrayList = BundleUtils.getParcelableArrayList(bundle, Extras.PAIRED_FOODS, FoodEntry.class.getClassLoader());
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) parcelableArrayList);
        addAndLogItem(foodEntry, foodV2Logging);
        if (notEmpty) {
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                addItem((FoodEntry) it.next());
            }
            ((FoodEditorAnalytics) lazy.get()).reportPairedFoodsLogged(CollectionUtils.size((Collection<?>) parcelableArrayList));
        }
        return false;
    }

    public boolean addAndLogItem(DiaryEntryCellModel diaryEntryCellModel, FoodV2Logging foodV2Logging) {
        if (!addItem(diaryEntryCellModel)) {
            return false;
        }
        addLogItem(foodV2Logging);
        return true;
    }

    public boolean addItem(DiaryEntryCellModel diaryEntryCellModel) {
        Tuple2 validKeyAndMap = getValidKeyAndMap(diaryEntryCellModel);
        if (validKeyAndMap == null) {
            return false;
        }
        ((Map) validKeyAndMap.getItem2()).put((String) validKeyAndMap.getItem1(), diaryEntryCellModel);
        this.itemsChangedSubject.onNext(Boolean.valueOf(true));
        return true;
    }

    private void addLogItem(FoodV2Logging foodV2Logging) {
        if (foodV2Logging != null) {
            removeLogItem(foodV2Logging);
            this.foodV2LoggingSet.add(foodV2Logging);
        }
    }

    public void removeItemAndLog(DiaryEntryCellModel diaryEntryCellModel, FoodV2Logging foodV2Logging) {
        removeItem(diaryEntryCellModel);
        removeLogItem(foodV2Logging);
    }

    public void addExternalItem(DiaryEntryCellModel diaryEntryCellModel, FoodV2Logging foodV2Logging) {
        if (addAndLogItem(diaryEntryCellModel, foodV2Logging)) {
            this.externalItems.add(0, diaryEntryCellModel);
        }
    }

    public List<DiaryEntryCellModel> getExternalItems() {
        return new ArrayList(this.externalItems);
    }

    public boolean removeItem(DiaryEntryCellModel diaryEntryCellModel) {
        boolean checkForItem = checkForItem(diaryEntryCellModel, true);
        this.itemsChangedSubject.onNext(Boolean.valueOf(true));
        return checkForItem;
    }

    public void removeLogItem(FoodV2Logging foodV2Logging) {
        this.foodV2LoggingSet.remove(foodV2Logging);
    }

    public int totalItemCount() {
        return CollectionUtils.size(this.localIdToSelectedItemsMap) + CollectionUtils.size(this.masterIdToSelectedItemsMap) + CollectionUtils.size(this.uidToSelectedItemsMap) + CollectionUtils.size(this.selectedMealEntriesMap);
    }

    public List<DiaryEntryCellModel> getAllSelectedItems() {
        ArrayList arrayList = new ArrayList(this.localIdToSelectedItemsMap.values());
        arrayList.addAll(this.masterIdToSelectedItemsMap.values());
        arrayList.addAll(this.uidToSelectedItemsMap.values());
        arrayList.addAll(this.selectedMealEntriesMap.values());
        return arrayList;
    }

    public List<FoodV2Logging> getFoodV2LoggingList() {
        return new ArrayList(this.foodV2LoggingSet);
    }

    public void addAllSelectedEntriesToDiaryWithMealName(String str, Lazy<MealIngredientMapper> lazy, DiaryService diaryService, Session session, Date date) {
        FoodEntry foodEntry;
        String str2 = str;
        DiaryDay diaryDayForActiveDateSync = diaryService.getDiaryDayForActiveDateSync();
        diaryDayForActiveDateSync.setIsPerformingMultiAdd(true);
        for (DiaryEntryCellModel diaryEntryCellModel : getAllSelectedItems()) {
            if (diaryEntryCellModel instanceof MealFood) {
                MealFood mealFood = (MealFood) diaryEntryCellModel;
                List<MealIngredient> ingredients = mealFood.getIngredients();
                if (CollectionUtils.notEmpty((Collection<?>) ingredients)) {
                    for (MealIngredient logIngredientToDiary : ingredients) {
                        logIngredientToDiary(lazy, diaryService, logIngredientToDiary, mealFood, str, date);
                    }
                }
                Session session2 = session;
                Date date2 = date;
            } else {
                if (diaryEntryCellModel.isFood()) {
                    foodEntry = ((Food) diaryEntryCellModel).createDefaultFoodEntry(session);
                } else {
                    Session session3 = session;
                    foodEntry = (FoodEntry) diaryEntryCellModel;
                }
                Food food = foodEntry.getFood();
                if (food.isMeal()) {
                    MealFood mealFood2 = (MealFood) food;
                    for (MealIngredient logIngredientToDiary2 : mealFood2.getIngredients()) {
                        logIngredientToDiary(lazy, diaryService, logIngredientToDiary2, mealFood2, str, date);
                    }
                    Date date3 = date;
                } else {
                    foodEntry.setMealName(str);
                    foodEntry.setEntryTimeAndUpdateLoggedAt(date);
                    diaryDayForActiveDateSync.addFoodEntry(foodEntry);
                    ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(foodEntry.getFood().getOriginalUid());
                }
            }
        }
        diaryDayForActiveDateSync.setIsPerformingMultiAdd(false);
        diaryDayForActiveDateSync.setJustAddedFoodEntry(null);
        diaryDayForActiveDateSync.setJustAddedMultipleItems(true);
        diaryDayForActiveDateSync.setJustAddedMultipleItemsMealName(str);
    }

    public Tuple2<Integer, Integer> getMealAndRecipeFoodCount() {
        int i = 0;
        int i2 = 0;
        for (DiaryEntryCellModel diaryEntryCellModel : getAllSelectedItems()) {
            if (diaryEntryCellModel instanceof MealFood) {
                i++;
            } else if (diaryEntryCellModel instanceof RecipeFood) {
                i2++;
            } else if (diaryEntryCellModel instanceof FoodEntry) {
                Food food = ((FoodEntry) diaryEntryCellModel).getFood();
                if (food.isMeal()) {
                    i++;
                } else if (food.isRecipe()) {
                    i2++;
                }
            }
        }
        return Tuple.create(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public boolean containsOnlineItemWithTerm(@Nullable String str) {
        if (str == null) {
            return false;
        }
        return CollectionsKt.any(this.foodV2LoggingSet, new Function1(str) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final Object invoke(Object obj) {
                return MultiAddFoodHelper.lambda$containsOnlineItemWithTerm$0(this.f$0, (FoodV2Logging) obj);
            }
        });
    }

    static /* synthetic */ Boolean lambda$containsOnlineItemWithTerm$0(@Nullable String str, FoodV2Logging foodV2Logging) {
        return Boolean.valueOf(SearchSource.ONLINE.getTitle().equals(foodV2Logging.getSource()) && str.equals(foodV2Logging.getSearchTerm()));
    }

    private void logIngredientToDiary(Lazy<MealIngredientMapper> lazy, DiaryService diaryService, MealIngredient mealIngredient, MealFood mealFood, String str, Date date) {
        FoodEntry foodEntryFromMealIngredient = getFoodEntryFromMealIngredient(lazy, diaryService, mealIngredient, mealFood, str);
        if (foodEntryFromMealIngredient != null) {
            foodEntryFromMealIngredient.setEntryTimeAndUpdateLoggedAt(date);
            diaryService.getDiaryDayForActiveDateSync().addFoodEntry(foodEntryFromMealIngredient);
            ((LocalSettingsService) this.localSettingsService.get()).removeRecentsDeletedFoodOriginalUid(foodEntryFromMealIngredient.getFood().getOriginalUid());
        }
    }

    private Tuple2<String, Map<String, DiaryEntryCellModel>> getValidKeyAndMap(DiaryEntryCellModel diaryEntryCellModel) {
        DatabaseObject databaseObject = (DatabaseObject) diaryEntryCellModel;
        Tuple2<String, Map<String, DiaryEntryCellModel>> validKeyAndMap = getValidKeyAndMap(databaseObject.getLocalId(), databaseObject.getMasterDatabaseId(), databaseObject.getUid());
        if (validKeyAndMap != null) {
            return validKeyAndMap;
        }
        if (diaryEntryCellModel instanceof MealFood) {
            return Tuple.create(Long.toString(databaseObject.getLocalId()), this.selectedMealEntriesMap);
        }
        return diaryEntryCellModel instanceof FoodEntry ? getValidKeyAndMap(((FoodEntry) diaryEntryCellModel).getFood()) : validKeyAndMap;
    }

    private Tuple2<String, Map<String, DiaryEntryCellModel>> getValidKeyAndMap(long j, long j2, String str) {
        if (j != 0) {
            return Tuple.create(Long.toString(j), this.localIdToSelectedItemsMap);
        }
        if (j2 != 0) {
            return Tuple.create(Long.toString(j2), this.masterIdToSelectedItemsMap);
        }
        if (Strings.notEmpty(str)) {
            return Tuple.create(str, this.uidToSelectedItemsMap);
        }
        return null;
    }

    public Subject<Boolean> getItemsChangedSubject() {
        return this.itemsChangedSubject;
    }

    public Subject<Boolean> getMultiAddEnabledSubject() {
        return this.isMultiAddEnabledSubject;
    }

    public List<FoodEntry> getAllSelectedItemsAsFoodEntries(Lazy<MealIngredientMapper> lazy, DiaryService diaryService, Session session) {
        ArrayList arrayList = new ArrayList();
        for (DiaryEntryCellModel diaryEntryCellModel : getAllSelectedItems()) {
            FoodEntry foodEntry = null;
            if (diaryEntryCellModel instanceof Food) {
                foodEntry = ((Food) diaryEntryCellModel).createDefaultFoodEntry(session);
            } else if (diaryEntryCellModel instanceof FoodEntry) {
                foodEntry = (FoodEntry) diaryEntryCellModel;
            }
            if (foodEntry != null) {
                Food food = foodEntry.getFood();
                if (food.isMeal()) {
                    MealFood mealFood = (MealFood) food;
                    for (MealIngredient foodEntryFromMealIngredient : mealFood.getIngredients()) {
                        FoodEntry foodEntryFromMealIngredient2 = getFoodEntryFromMealIngredient(lazy, diaryService, foodEntryFromMealIngredient, mealFood, MEAL_NAME);
                        if (foodEntryFromMealIngredient2 != null) {
                            arrayList.add(foodEntryFromMealIngredient2);
                        }
                    }
                } else {
                    foodEntry.setMealName(MEAL_NAME);
                    arrayList.add(foodEntry);
                }
            }
        }
        return arrayList;
    }

    private FoodEntry getFoodEntryFromMealIngredient(Lazy<MealIngredientMapper> lazy, DiaryService diaryService, MealIngredient mealIngredient, MealFood mealFood, String str) {
        FoodEntry foodEntry = (FoodEntry) ((MealIngredientMapper) lazy.get()).tryMapFrom(mealIngredient);
        if (foodEntry != null) {
            foodEntry.setDate(diaryService.getDiaryDayForActiveDateSync().getDate());
            foodEntry.setMealName(str);
            foodEntry.setMealFood(mealFood);
        }
        return foodEntry;
    }
}
