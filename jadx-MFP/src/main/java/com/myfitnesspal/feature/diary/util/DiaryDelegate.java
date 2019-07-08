package com.myfitnesspal.feature.diary.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView;
import com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity;
import com.myfitnesspal.feature.addentry.ui.activity.WaterEntryActivity.Mode;
import com.myfitnesspal.feature.addentry.util.WaterSponsorshipUtil;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.event.DiaryItemCheckedEvent;
import com.myfitnesspal.feature.diary.model.DiarySectionNutritionTotals;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.EditDiaryNoteView;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter;
import com.myfitnesspal.feature.diary.ui.item.DiaryAdapterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryButtonsFooterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryLandscapeFooterItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryPromoItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryRowActionItem;
import com.myfitnesspal.feature.diary.ui.item.DiaryStepsAppFooterItem;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.exercise.ui.activity.EditCardio;
import com.myfitnesspal.feature.exercise.ui.activity.EditStrength;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.CalorieAdjustmentExplanationView;
import com.myfitnesspal.feature.goals.ui.activity.CalorieAdjustmentIntro;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Exercise.Source;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.constants.Constants.Report;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.ExerciseEntryPropertiesTable.Keys;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.DiaryPromoItemDismissedEvent;
import com.myfitnesspal.shared.event.ShowDialogFragmentEvent;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryEntryItem;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpExerciseMetadataForSteps;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.impl.QuickAddCaloriesDialogFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.FoodMapperUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddExerciseSelection;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiaryDelegate {
    private static final String FOOD_INSIGHTS = "foodInsights";
    private static final String MEAL_NAME_QUICK_ADD = "meal_name_quick_add";
    public static final String QUICK_ADD_ID = "quick_add";
    private static final String QUICK_CALORIE_EDIT_ENTRY = "quick_calorie_entry_edit";
    private final Lazy<ActionTrackingService> actionTrackingService;
    private Context activityContext;
    private final Lazy<AppGalleryService> appGalleryService;
    private final Lazy<ConfigService> configService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<DiaryAnalyticsHelper> diaryAnalyticsHelper;
    private ArrayList<Long> diaryItemsChecked = new ArrayList<>();
    private final Lazy<DiaryService> diaryService;
    private final DiaryType diaryType;
    private final Lazy<ExerciseStringService> exerciseStringService;
    private ArrayList<FoodAnalyzerResponseData> foodInsights;
    private boolean isEditing;
    private boolean isLandscape;
    private boolean isSponsoredWaterEnabled;
    private boolean isWaterSponsorshipEnabled;
    private Map<Long, DatabaseObject> itemsSelected = new HashMap();
    private final Lazy<LocalSettingsService> localSettingsService;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private String mealNameForQuickAdd;
    private List<String> mostRecentlyAddedEntryIds = new ArrayList();
    private final Lazy<NavigationHelper> navigationHelper;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private final Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private final Lazy<PremiumService> premiumService;
    private FoodEntry quickCalorieEntryToEdit;
    private List<String> selectAllMeals = new ArrayList();
    private final Lazy<Session> session;
    private final Lazy<StepService> stepService;
    private final Lazy<UacfScheduler<SyncType>> syncScheduler;
    private Toast toast;
    private final Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    private final Lazy<UserEnergyService> userEnergyService;
    private boolean walkthroughVisible;

    public enum DiaryType {
        Self,
        Friend
    }

    public DiaryDelegate(Context context, Fragment fragment, Lazy<LocalizedStringsUtil> lazy, Lazy<UserEnergyService> lazy2, Lazy<ExerciseStringService> lazy3, Lazy<NavigationHelper> lazy4, Lazy<PremiumService> lazy5, Lazy<UacfScheduler<SyncType>> lazy6, Lazy<DiaryService> lazy7, Lazy<DiaryAnalyticsHelper> lazy8, Lazy<ActionTrackingService> lazy9, Lazy<AppGalleryService> lazy10, Lazy<StepService> lazy11, Lazy<ConfigService> lazy12, Lazy<Session> lazy13, Lazy<LocalSettingsService> lazy14, Lazy<NutrientGoalsUtil> lazy15, Lazy<NewsFeedAnalyticsHelper> lazy16, DiaryType diaryType2, Lazy<UserApplicationSettingsService> lazy17, Lazy<DbConnectionManager> lazy18, boolean z) {
        Context context2 = context;
        setContext(context);
        this.localizedStringsUtil = lazy;
        this.userEnergyService = lazy2;
        this.exerciseStringService = lazy3;
        this.navigationHelper = lazy4;
        this.premiumService = lazy5;
        this.syncScheduler = lazy6;
        this.diaryService = lazy7;
        this.diaryAnalyticsHelper = lazy8;
        this.actionTrackingService = lazy9;
        this.appGalleryService = lazy10;
        this.stepService = lazy11;
        this.configService = lazy12;
        this.session = lazy13;
        this.localSettingsService = lazy14;
        this.nutrientGoalsUtil = lazy15;
        this.newsFeedAnalyticsHelper = lazy16;
        this.diaryType = diaryType2;
        this.userApplicationSettingsService = lazy17;
        this.dbConnectionManager = lazy18;
        this.isLandscape = ActivityUtils.isLandscape(context) && ActivityUtils.isDefaultPortrait((Activity) context2) && !z;
        this.isSponsoredWaterEnabled = ConfigUtils.isSponsoredWaterEnabled((ConfigService) lazy12.get());
        this.isWaterSponsorshipEnabled = WaterSponsorshipUtil.shouldShowWaterSponsorship((ConfigService) lazy12.get(), (Session) lazy13.get(), (PremiumService) lazy5.get());
        Fragment fragment2 = fragment;
        ((NavigationHelper) this.navigationHelper.get()).fromFragment(fragment).setContext(context);
    }

    public boolean isLandscape() {
        return this.isLandscape;
    }

    public void setContext(Context context) {
        if (context instanceof Activity) {
            this.activityContext = context;
            return;
        }
        throw new IllegalArgumentException("Context must be an instance of Activity!");
    }

    public void setEditing(boolean z) {
        this.isEditing = z;
    }

    public boolean isEditing() {
        return this.isEditing;
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.foodInsights = bundle.getParcelableArrayList(FOOD_INSIGHTS);
            this.quickCalorieEntryToEdit = (FoodEntry) bundle.getParcelable(QUICK_CALORIE_EDIT_ENTRY);
            this.mealNameForQuickAdd = bundle.getString(MEAL_NAME_QUICK_ADD);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList(FOOD_INSIGHTS, this.foodInsights);
        bundle.putParcelable(QUICK_CALORIE_EDIT_ENTRY, this.quickCalorieEntryToEdit);
        bundle.putString(MEAL_NAME_QUICK_ADD, this.mealNameForQuickAdd);
    }

    public void setupDiaryAdapterListAndMap(DiaryDay diaryDay, MfpNutrientGoal mfpNutrientGoal, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, boolean z) {
        setupPromoIfRequired(list);
        setupMealEntries(diaryDay, mfpNutrientGoal, list, map);
        if (!this.isLandscape) {
            setupExerciseEntries(diaryDay, list, map);
            if (!this.isEditing) {
                setupWaterEntries(diaryDay, list, map, z);
                setupNotesEntries(diaryDay, list, map);
                return;
            }
            return;
        }
        list.add(new DiaryLandscapeFooterItem());
    }

    public void resetDiaryAdapterListAndMap(DiaryDay diaryDay, MfpNutrientGoal mfpNutrientGoal, DiaryAdapter diaryAdapter) {
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        setupDiaryAdapterListAndMap(diaryDay, mfpNutrientGoal, arrayList, linkedHashMap, false);
        diaryAdapter.resetListAndMap(arrayList, linkedHashMap);
    }

    private boolean isUserDiaryType() {
        return this.diaryType == DiaryType.Self;
    }

    private void addItemsToDiaryListAndMap(List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, List list2, SectionHeaderItem sectionHeaderItem) {
        addItemsToDiaryListAndMap(list, map, list2, sectionHeaderItem, null);
    }

    private void addItemsToDiaryListAndMap(List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, List list2, SectionHeaderItem sectionHeaderItem, DiaryRowActionItem diaryRowActionItem) {
        if (!this.isLandscape || CollectionUtils.notEmpty((Collection<?>) list2)) {
            list.add(sectionHeaderItem);
            addMealListToDiaryListAndMap(list2, list, map, sectionHeaderItem);
        }
        if (diaryRowActionItem != null && !this.isEditing && !this.isLandscape) {
            list.add(diaryRowActionItem);
        }
    }

    private void addMealListToDiaryListAndMap(List<?> list, List<DiaryAdapterItem> list2, Map<SectionHeaderItem, List<DatabaseObject>> map, SectionHeaderItem sectionHeaderItem) {
        ArrayList arrayList = new ArrayList();
        map.put(sectionHeaderItem, arrayList);
        if (!CollectionUtils.isEmpty((Collection<?>) list)) {
            int size = CollectionUtils.size((Collection<?>) list);
            int i = 0;
            while (i < size) {
                DatabaseObject databaseObject = (DatabaseObject) list.get(i);
                arrayList.add(databaseObject);
                list2.add(new DiaryEntryItem(databaseObject, sectionHeaderItem.getDiarySection(), i == size + -1));
                i++;
            }
        }
    }

    public void addMostRecentlyAddedEntry(DatabaseObject databaseObject) {
        String idFromEntry = getIdFromEntry(databaseObject);
        if (idFromEntry != null) {
            this.mostRecentlyAddedEntryIds.add(idFromEntry);
        }
    }

    public boolean isMostRecentlyAddedEntry(DatabaseObject databaseObject) {
        String idFromEntry = getIdFromEntry(databaseObject);
        return idFromEntry != null && this.mostRecentlyAddedEntryIds.contains(idFromEntry);
    }

    public void removeFromMostRecentlyAddedEntries(DatabaseObject databaseObject) {
        this.mostRecentlyAddedEntryIds.remove(getIdFromEntry(databaseObject));
    }

    private String getIdFromEntry(DatabaseObject databaseObject) {
        if (databaseObject instanceof FoodEntry) {
            return getIdFromFoodEntry((FoodEntry) databaseObject);
        }
        if (databaseObject instanceof ExerciseEntry) {
            return getIdFromExerciseEntry((ExerciseEntry) databaseObject);
        }
        return null;
    }

    private String getIdFromFoodEntry(FoodEntry foodEntry) {
        if (!isValidFoodEntry(foodEntry)) {
            return null;
        }
        Food food = foodEntry.getFood();
        return isQuickAddFood(food) ? getIdFromQuickAddFood(food) : food.getUid();
    }

    private String getIdFromQuickAddFood(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append("quick_add");
        sb.append(food.getNutritionalValues().calories());
        return sb.toString();
    }

    private String getIdFromExerciseEntry(ExerciseEntry exerciseEntry) {
        if (!isValidExerciseEntry(exerciseEntry)) {
            return null;
        }
        return exerciseEntry.getExercise().getUid();
    }

    private boolean isValidFoodEntry(FoodEntry foodEntry) {
        return (foodEntry == null || foodEntry.getFood() == null) ? false : true;
    }

    private boolean isValidExerciseEntry(ExerciseEntry exerciseEntry) {
        return (exerciseEntry == null || exerciseEntry.getExercise() == null) ? false : true;
    }

    private boolean isQuickAddFood(Food food) {
        String description = food.getDescription();
        return Strings.equals(description, MealTypeName.QUICK_ADD) || Strings.equals(description, MealTypeName.LEGACY_QUICK_ADDED_CALORIES);
    }

    private void setupPromoIfRequired(List<DiaryAdapterItem> list) {
        if (shouldShowMealGoalsCard()) {
            final String str = ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealGoals) ? NewsFeedAnalyticsHelper.PREMIUM_MEAL_GOAL_CARD_TYPE : NewsFeedAnalyticsHelper.NON_PREMIUM_MEAL_GOAL_CARD_TYPE;
            DiaryPromoItem diaryPromoItem = new DiaryPromoItem(getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.meal_goals_cal_promo_title : R.string.meal_goals_kj_promo_title, new Object[0]), getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.meal_goals_cal_promo_message : R.string.meal_goals_kj_promo_message, new Object[0]), getString(R.string.meal_goals_promo_action, new Object[0]), R.drawable.ic_goals_by_meal, PremiumFeature.MealGoals, new OnClickListener() {
                public void onClick(View view) {
                    ((NewsFeedAnalyticsHelper) DiaryDelegate.this.newsFeedAnalyticsHelper.get()).reportHeroCardTapped(str, -1);
                    DiaryDelegate.this.navigateToMealGoal(null, DiaryPromoItem.DIARY_MEAL_GOAL_CARD);
                    DiaryDelegate.this.dismissMealGoalsPromoCard();
                }
            }, new OnClickListener() {
                public void onClick(View view) {
                    DiaryDelegate.this.dismissMealGoalsPromoCard();
                    ((NewsFeedAnalyticsHelper) DiaryDelegate.this.newsFeedAnalyticsHelper.get()).reportHeroCardClosed(str);
                }
            });
            list.add(diaryPromoItem);
        }
    }

    private void setupMealEntries(DiaryDay diaryDay, MfpNutrientGoal mfpNutrientGoal, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map) {
        DiaryDay diaryDay2 = diaryDay;
        MfpNutrientGoal mfpNutrientGoal2 = mfpNutrientGoal;
        User user = ((Session) this.session.get()).getUser();
        Map foodEntriesByMealName = diaryDay.getFoodEntriesByMealName();
        List<String> names = isUserDiaryType() ? user.getMealNames().getNames() : diaryDay.getMealNames();
        boolean z = shouldShowMealGoals() && user.isMealGoalsEnabled() && mfpNutrientGoal2 != null;
        if (CollectionUtils.notEmpty((Collection<?>) names)) {
            for (String str : names) {
                String mealGoalEnergyValue = getMealGoalEnergyValue(mfpNutrientGoal2, diaryDay.getDate(), user.getMealNames().mealIndexForName(str));
                DiarySectionNutritionTotals diarySectionNutritionTotals = new DiarySectionNutritionTotals(diaryDay2.totalNutrientValueForMealName(str, 9), diaryDay2.totalNutrientValueForMealName(str, 1), diaryDay2.totalNutrientValueForMealName(str, 12), diaryDay2.totalNutrientValueForMealName(str, 7), diaryDay2.totalNutrientValueForMealName(str, 11));
                SectionHeaderItem sectionHeaderItem = new SectionHeaderItem(str, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(str, (UserEnergyService) this.userEnergyService.get()), 2, diaryDay2.totalCaloriesForMealName(str), mealGoalEnergyValue, diarySectionNutritionTotals, false, true, isSelectAll(str), z, DateTimeUtils.getDayOfTheWeek(diaryDay.getDate()), null);
                DiaryRowActionItem diaryRowActionItem = new DiaryRowActionItem(str, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(str, (UserEnergyService) this.userEnergyService.get()));
                List list2 = (List) foodEntriesByMealName.get(str);
                if (this.diaryType != DiaryType.Friend || !CollectionUtils.isEmpty((Collection<?>) list2)) {
                    if (list2 != null) {
                        Collections.sort(list2, $$Lambda$DiaryDelegate$MUteYnZHWOcmq_C0uBEAQdWUbu0.INSTANCE);
                    }
                    addItemsToDiaryListAndMap(list, map, list2, sectionHeaderItem, diaryRowActionItem);
                }
            }
        }
    }

    static /* synthetic */ int lambda$setupMealEntries$0(FoodEntry foodEntry, FoodEntry foodEntry2) {
        Date entryTime = foodEntry.getEntryTime();
        Date entryTime2 = foodEntry2.getEntryTime();
        if (entryTime == null && entryTime2 == null) {
            return 0;
        }
        if (entryTime == null) {
            return -1;
        }
        if (entryTime2 == null) {
            return 1;
        }
        return entryTime.compareTo(entryTime2);
    }

    private boolean shouldShowMealGoals() {
        return ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealGoals) && ((PremiumService) this.premiumService.get()).isPremiumAvailable() && isUserDiaryType();
    }

    public boolean shouldShowMealGoalsCard() {
        return ((PremiumService) this.premiumService.get()).isPremiumAvailable() && !((UserApplicationSettingsService) this.userApplicationSettingsService.get()).isMealGoalsBuyUpsellDismissed() && !((Session) this.session.get()).getUser().isMealGoalsEnabled() && (shouldShowMealGoals() || isUserDiaryType()) && !this.walkthroughVisible;
    }

    private void setupExerciseEntries(DiaryDay diaryDay, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map) {
        List<DiaryAdapterItem> list2 = list;
        SectionHeaderItem sectionHeaderItem = new SectionHeaderItem("Exercise", getString(R.string.exercise, new Object[0]), 3, diaryDay.totalCaloriesForExercises(), null, null, false, true, isSelectAll("Exercise"), false, null, null);
        DiaryRowActionItem diaryRowActionItem = new DiaryRowActionItem("Exercise", getString(R.string.exercise, new Object[0]));
        List exerciseEntries = diaryDay.getExerciseEntries();
        if (isUserDiaryType() || !CollectionUtils.isEmpty((Collection<?>) exerciseEntries)) {
            List<DiaryAdapterItem> list3 = list;
            addItemsToDiaryListAndMap(list3, map, exerciseEntries, sectionHeaderItem);
            if (ConfigUtils.isAppNavUpdateDiaryEnabled((ConfigService) this.configService.get()) && this.diaryType == DiaryType.Self && !this.isEditing && !this.isLandscape && ((StepService) this.stepService.get()).getPrimaryStepSource() == null) {
                list3.add(new DiaryStepsAppFooterItem());
                ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportyConnectDeviceDisplayed();
            }
            if (!this.isEditing && !this.isLandscape) {
                list3.add(diaryRowActionItem);
            }
        }
    }

    private void setupWaterEntries(DiaryDay diaryDay, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, boolean z) {
        DiaryRowActionItem diaryRowActionItem;
        boolean z2 = !diaryDay.hasWater();
        boolean shouldShowWaterCard = ((LocalSettingsService) this.localSettingsService.get()).shouldShowWaterCard();
        if (!z2 || (shouldShowWaterCard && this.isSponsoredWaterEnabled && !z)) {
            String str = Report.WATER_CONSUMPTION;
            boolean z3 = this.isSponsoredWaterEnabled;
            int i = R.string.water_diary_section_short;
            SectionHeaderItem sectionHeaderItem = new SectionHeaderItem(str, getString(z3 ? R.string.water_diary_section_short : R.string.water_diary_section, new Object[0]), 4, 0, null, null, false, false, false, false, null, null);
            ArrayList arrayList = new ArrayList();
            if (!z2 || this.isWaterSponsorshipEnabled) {
                arrayList.add((!z2 || !this.isSponsoredWaterEnabled) ? diaryDay.getCurrentWaterEntry() : new WaterEntry());
            }
            boolean z4 = this.isSponsoredWaterEnabled;
            if (!z4 || z) {
                diaryRowActionItem = null;
            } else {
                String str2 = Report.WATER_CONSUMPTION;
                if (!z4) {
                    i = R.string.water_diary_section;
                }
                diaryRowActionItem = new DiaryRowActionItem(str2, getString(i, new Object[0]));
            }
            addItemsToDiaryListAndMap(list, map, arrayList, sectionHeaderItem, diaryRowActionItem);
        }
    }

    private void setupNotesEntries(DiaryDay diaryDay, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map) {
        ArrayList arrayList = new ArrayList();
        DiaryNote foodNote = diaryDay.getFoodNote();
        if (foodNote != null) {
            arrayList.add(foodNote);
        }
        DiaryNote exerciseNote = diaryDay.getExerciseNote();
        if (exerciseNote != null) {
            arrayList.add(exerciseNote);
        }
        if (!CollectionUtils.isEmpty((Collection<?>) arrayList)) {
            SectionHeaderItem sectionHeaderItem = new SectionHeaderItem(Report.NOTES, getString(R.string.notes, new Object[0]), 5, 0, null, null, false, false, false, false, null, null);
            addItemsToDiaryListAndMap(list, map, arrayList, sectionHeaderItem);
        }
    }

    public void addFooterToList(List<DiaryAdapterItem> list) {
        if (isUserDiaryType() && !this.isLandscape) {
            list.add(new DiaryButtonsFooterItem());
        }
    }

    public void showToastIfNecessary() {
        CharSequence charSequence;
        boolean z;
        DiaryDay diaryDayForActiveDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForActiveDateSync();
        if (diaryDayForActiveDateSync != null) {
            FoodEntry justAddedFoodEntry = diaryDayForActiveDateSync.getJustAddedFoodEntry();
            int i = R.string.added_toast;
            if (justAddedFoodEntry != null) {
                charSequence = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(diaryDayForActiveDateSync.JustAddedPrimaryText(), (UserEnergyService) this.userEnergyService.get());
                diaryDayForActiveDateSync.setJustAddedFoodEntry(null);
                this.foodInsights = diaryDayForActiveDateSync.getFoodInsights();
                z = false;
            } else if (diaryDayForActiveDateSync.getJustAddedExerciseEntry() != null) {
                charSequence = ((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(diaryDayForActiveDateSync.getJustAddedExerciseEntry().getExercise());
                diaryDayForActiveDateSync.setJustAddedExerciseEntry(null);
                z = true;
            } else if (diaryDayForActiveDateSync.justAddedFoodNote()) {
                charSequence = getString(R.string.food_note_toast, new Object[0]);
                diaryDayForActiveDateSync.setJustAddedFoodNote(false);
                z = true;
                i = R.string.saved_toast;
            } else if (diaryDayForActiveDateSync.justAddedExerciseNote()) {
                charSequence = getString(R.string.exercise_note_toast, new Object[0]);
                diaryDayForActiveDateSync.setJustAddedExerciseNote(false);
                z = true;
                i = R.string.saved_toast;
            } else if (diaryDayForActiveDateSync.justAddedMultipleItems()) {
                charSequence = getString(R.string.checked_toast, new Object[0]);
                diaryDayForActiveDateSync.setJustAddedMultipleItems(false);
                diaryDayForActiveDateSync.setJustAddedMultipleItemsMealName(null);
                MultiAddExerciseSelection.reset();
                this.foodInsights = diaryDayForActiveDateSync.getFoodInsights();
                z = false;
            } else if (diaryDayForActiveDateSync.justAddedWaterEntry() != null && diaryDayForActiveDateSync.justAddedWaterEntry().getMilliliters() > BitmapDescriptorFactory.HUE_RED) {
                charSequence = getString(R.string.water_entry_toast, new Object[0]);
                diaryDayForActiveDateSync.setJustAddedWaterEntry(null);
                z = true;
            } else if (diaryDayForActiveDateSync.justCopiedFromDate()) {
                charSequence = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(diaryDayForActiveDateSync.JustAddedPrimaryText(), (UserEnergyService) this.userEnergyService.get());
                diaryDayForActiveDateSync.setJustCopiedFromDate(false);
                z = true;
            } else {
                charSequence = null;
                z = false;
                i = 0;
            }
            if (z) {
                View inflate = LayoutInflater.from(this.activityContext).inflate(R.layout.diary_toast, null);
                TextView textView = (TextView) inflate.findViewById(R.id.operationTextView);
                ((TextView) inflate.findViewById(R.id.justAddedEntryTextView)).setText(charSequence);
                textView.setText(i);
                if (this.toast == null) {
                    this.toast = new Toast(this.activityContext);
                }
                this.toast.setView(inflate);
                this.toast.setDuration(1);
                this.toast.setGravity(17, 0, 0);
                this.toast.show();
            }
        }
    }

    public boolean handleDiaryEntryClick(DatabaseObject databaseObject, DiaryDay diaryDay) {
        if (databaseObject instanceof FoodEntry) {
            handleFoodEntryClick(databaseObject, this.foodInsights, diaryDay);
        } else if (databaseObject instanceof ExerciseEntry) {
            handleExerciseEntryClick(databaseObject);
        } else if (databaseObject instanceof WaterEntry) {
            handleWaterEntryClick();
        } else if (!(databaseObject instanceof DiaryNote)) {
            return false;
        } else {
            handlerDiaryNoteClick(databaseObject, diaryDay);
        }
        return true;
    }

    public void navigateToMealGoal(String str, String str2) {
        getNavigationHelper().withIntent(MealGoalsActivity.newStartIntent(this.activityContext, str2)).withExtra("day_of_week", str).startActivity();
    }

    private void handlerDiaryNoteClick(DatabaseObject databaseObject, DiaryDay diaryDay) {
        switchToEditDiaryNoteView((DiaryNote) databaseObject, diaryDay);
    }

    private void handleWaterEntryClick() {
        showWaterEntryDialog();
    }

    public void showWaterEntryDialog() {
        getNavigationHelper().withIntent(WaterEntryActivity.newStartIntent(this.activityContext, Mode.Edit)).startActivity(50);
    }

    private void handleExerciseEntryClick(DatabaseObject databaseObject) {
        ExerciseEntry exerciseEntry = (ExerciseEntry) databaseObject;
        if (exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
            if (CalorieAdjustmentExplanationView.displaybleWithExerciseEntry(exerciseEntry)) {
                Tuple2 exerciseEntryPartnerTuple = getExerciseEntryPartnerTuple(exerciseEntry);
                getNavigationHelper().withIntent(CalorieAdjustmentIntro.newStartIntent(this.activityContext, exerciseEntry.getCalories(), Strings.toString(exerciseEntryPartnerTuple.getItem1()), Strings.toString(exerciseEntryPartnerTuple.getItem2()), Strings.equalsIgnoreCase(exerciseEntry.extraPropertyNamed("allow_negative_calorie_adjustment"), "true"), exerciseEntry)).startActivity();
            }
            return;
        }
        switchToEditExerciseEntryView(exerciseEntry);
    }

    private void handleFoodEntryClick(DatabaseObject databaseObject, List<FoodAnalyzerResponseData> list, DiaryDay diaryDay) {
        FoodEntry foodEntry = (FoodEntry) databaseObject;
        if (foodEntry.getFood().isQuickAddOfAnySort()) {
            switchToEditQuickAddedCaloriesView(foodEntry);
        } else {
            switchToEditFoodEntryView(foodEntry, list, diaryDay);
        }
    }

    public void handleItemClickWhileEditing(DatabaseObject databaseObject, View view) {
        CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(view, R.id.edit_checkbox);
        if (compoundButton != null) {
            handleItemClickWhileEditing(databaseObject, !compoundButton.isChecked());
        }
    }

    public void handleItemClickWhileEditing(DatabaseObject databaseObject, boolean z) {
        postEvent(new DiaryItemCheckedEvent(databaseObject, z));
    }

    private void switchToEditDiaryNoteView(DiaryNote diaryNote, DiaryDay diaryDay) {
        if (diaryNote == null) {
            if (diaryDay != null) {
                diaryNote = new DiaryNote();
                diaryNote.setEntryDate(diaryDay.getDate());
                diaryNote.setBody("");
            } else {
                return;
            }
        }
        EditDiaryNoteView.setDiaryNote(diaryNote);
        getNavigationHelper().withExtra(Extras.TITLE_FOR_NOTE, getString(diaryNote.getNoteTitle(), new Object[0])).withExtra(Extras.EDITABLE, true).withIntent(EditDiaryNoteView.newStartIntent(this.activityContext)).startActivity(51);
    }

    private void switchToEditExerciseEntryView(ExerciseEntry exerciseEntry) {
        if (exerciseEntry != null) {
            Exercise exercise = exerciseEntry.getExercise();
            if (exercise == null) {
                return;
            }
            if (exercise.getExerciseType() == 0) {
                getNavigationHelper().withIntent(EditCardio.newStartIntent(this.activityContext, exerciseEntry)).startActivity(100);
            } else if (exercise.getExerciseType() == 1) {
                getNavigationHelper().withIntent(EditStrength.newStartIntent(this.activityContext, exerciseEntry)).startActivity(101);
            }
        }
    }

    private void switchToEditFoodEntryView(final FoodEntry foodEntry, List<FoodAnalyzerResponseData> list, DiaryDay diaryDay) {
        navigateToAddFoodSummaryView(foodEntry, (FoodAnalyzerResponseData) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, FoodAnalyzerResponseData>() {
            public Boolean execute(FoodAnalyzerResponseData foodAnalyzerResponseData) {
                return Boolean.valueOf(foodAnalyzerResponseData.isInsight() && foodAnalyzerResponseData.getAssociatedFoodEntryLocalId() == foodEntry.getLocalId());
            }
        }), diaryDay);
    }

    private void navigateToAddFoodSummaryView(FoodEntry foodEntry, FoodAnalyzerResponseData foodAnalyzerResponseData, DiaryDay diaryDay) {
        FoodAnalyzerResponseData foodAnalyzerResponseData2 = foodAnalyzerResponseData;
        NavigationHelper navigationHelper2 = getNavigationHelper();
        if (foodAnalyzerResponseData2 != null) {
            navigationHelper2.withExtra(Extras.FOOD_ANALYZER_DATA, (Parcelable) foodAnalyzerResponseData2);
        }
        if (ConfigUtils.isNewAddFoodFlowOn((ConfigService) this.configService.get())) {
            MfpFood mapV1FoodToMfpFood = FoodMapperUtil.mapV1FoodToMfpFood(foodEntry.getFood());
            mapV1FoodToMfpFood.setSelectedServingAmount(foodEntry.getQuantity());
            mapV1FoodToMfpFood.setSelectedServingSizeIndex(foodEntry.getWeightIndex());
            FoodEditorExtras foodEditorExtras = new FoodEditorExtras();
            foodEditorExtras.setExistingFoodEntryMasterId(foodEntry.getMasterDatabaseId()).setExistingFoodEntryLocalId(foodEntry.getLocalId()).setActionType(ActionType.Edit).setMealName(foodEntry.getMealName()).setSupportPairedFoods(false).setShowInsightsUi(true).setScreenTitle(getString(R.string.edit_entry, new Object[0])).setFoodAnalyzerResponseData(foodAnalyzerResponseData2).setFoodTimestamp(foodEntry.getEntryTime());
            Context context = this.activityContext;
            context.startActivity(FoodEditorActivity.newDiaryFoodItemEditorIntent(context, null, mapV1FoodToMfpFood, ((Session) this.session.get()).getUser().getActiveDate(), foodEntry.getMealName(), null, null, Extras.REFERRER_DIARY_DELEGATE, false, foodEditorExtras));
            return;
        }
        navigationHelper2.withIntent(AddFoodSummaryView.newStartIntent(this.activityContext, (AddFoodSummaryView.Extras) ((AddFoodSummaryView.Extras) ((AddFoodSummaryView.Extras) new AddFoodSummaryView.Extras().setForEdit(true).setFoodEntry(foodEntry).setMealName(foodEntry.getMealName())).setTitle(getString(R.string.edit_entry, new Object[0]))).setServings(foodEntry.getQuantity()))).startActivity(54);
    }

    private void switchToEditQuickAddedCaloriesView(FoodEntry foodEntry) {
        Ln.i("Quick Add Calories", new Object[0]);
        setQuickFoodAndNavigateToQuickAdd(foodEntry);
    }

    private void setQuickFoodAndNavigateToQuickAdd(FoodEntry foodEntry) {
        setQuickFoodAndNavigateToQuickAdd(foodEntry, null);
    }

    public void setQuickFoodAndNavigateToQuickAdd(FoodEntry foodEntry, String str) {
        QuickAddFood quickAddFood = null;
        if (!Strings.notEmpty(str)) {
            str = foodEntry != null ? foodEntry.getMealName() : null;
        }
        this.mealNameForQuickAdd = str;
        if (this.mealNameForQuickAdd != null) {
            this.quickCalorieEntryToEdit = foodEntry;
            if (foodEntry != null) {
                NutritionalValues nutritionalValues = foodEntry.getFood().getNutritionalValues();
                QuickAddFood quickAddFood2 = new QuickAddFood(foodEntry.getCaloriesValue(), nutritionalValues.getCarbohydrate(), nutritionalValues.getFat(), nutritionalValues.getProtein(), foodEntry.getEntryTime());
                quickAddFood = quickAddFood2;
            }
            if (((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.QuickAddMacros) == Availability.Revoked) {
                postEvent(new AlertEvent(getString(R.string.premium_feature_revoked, new Object[0])));
            } else if (shouldEditUsingPremiumQuickAdd(foodEntry)) {
                getNavigationHelper().withIntent(QuickAddActivity.newStartIntent(this.activityContext, new QuickAddActivity.Extras().setQuickAddFood(quickAddFood).setMealName(this.mealNameForQuickAdd))).startActivity(159);
            } else {
                showQuickAddCaloriesDialog();
            }
        }
    }

    public void showQuickAddCaloriesDialog() {
        String str = "0";
        FoodEntry foodEntry = this.quickCalorieEntryToEdit;
        if (foodEntry != null) {
            try {
                str = Strings.toString(Float.valueOf(foodEntry.getCaloriesValue()));
            } catch (Exception unused) {
                str = "0";
            }
        }
        postEvent(new ShowDialogFragmentEvent(QuickAddCaloriesDialogFragment.newInstance(str, this.mealNameForQuickAdd), Fragments.QUICK_ADD_DIALOG));
    }

    private boolean shouldEditUsingPremiumQuickAdd(FoodEntry foodEntry) {
        return ((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.QuickAddMacros) || (foodEntry != null && foodEntry.getFood().isQuickAddFood());
    }

    public boolean deleteItem(DatabaseObject databaseObject, DiaryDay diaryDay) {
        boolean z;
        if (databaseObject instanceof FoodEntry) {
            diaryDay.deleteFoodEntry((FoodEntry) databaseObject);
            z = true;
        } else {
            z = false;
        }
        if (databaseObject instanceof ExerciseEntry) {
            diaryDay.deleteExerciseEntry((ExerciseEntry) databaseObject);
            z = true;
        }
        if (databaseObject instanceof WaterEntry) {
            diaryDay.setWaterEntry(0);
        }
        if (databaseObject instanceof DiaryNote) {
            DiaryNote diaryNote = (DiaryNote) databaseObject;
            diaryNote.setMasterDatabaseId(0);
            diaryNote.setBody("");
            ((DbConnectionManager) this.dbConnectionManager.get()).diaryNoteDbAdapter().insertOrUpdateDiaryNote(diaryNote);
            diaryDay.loadNotes();
        }
        return z;
    }

    private boolean isSelectAll(String str) {
        return this.selectAllMeals.contains(str);
    }

    public void selectAllFoodEntriesInMeal(DiaryDay diaryDay, String str, boolean z) {
        ArrayList<DatabaseObject> arrayList;
        if (!Strings.isEmpty(str) && diaryDay != null) {
            List exerciseEntries = diaryDay.getExerciseEntries();
            ArrayList cardioExerciseEntries = diaryDay.getCardioExerciseEntries();
            ArrayList strengthExerciseEntries = diaryDay.getStrengthExerciseEntries();
            List list = (List) diaryDay.getFoodEntriesByMealName().get(str);
            if (Strings.equals(str, "Exercise") && CollectionUtils.notEmpty((Collection<?>) exerciseEntries)) {
                arrayList = new ArrayList<>(exerciseEntries);
            } else if (Strings.equals(str, Report.CARDIO_EXERCISE) && CollectionUtils.notEmpty((Collection<?>) cardioExerciseEntries)) {
                arrayList = new ArrayList<>(cardioExerciseEntries);
            } else if (Strings.equals(str, Report.STRENGTH_EXERCISE) && CollectionUtils.notEmpty((Collection<?>) strengthExerciseEntries)) {
                arrayList = new ArrayList<>(strengthExerciseEntries);
            } else if (CollectionUtils.notEmpty((Collection<?>) list)) {
                arrayList = new ArrayList<>(list);
            } else {
                arrayList = new ArrayList<>();
            }
            if (z) {
                this.selectAllMeals.add(str);
                for (DatabaseObject addItemToSelectedList : arrayList) {
                    addItemToSelectedList(addItemToSelectedList);
                }
            } else {
                this.selectAllMeals.remove(str);
                for (DatabaseObject removeItemFromSelectedList : arrayList) {
                    removeItemFromSelectedList(removeItemFromSelectedList);
                }
            }
        }
    }

    private void removeItemFromSelectedList(DatabaseObject databaseObject) {
        if (databaseObject != null) {
            Long valueOf = Long.valueOf(databaseObject.getLocalId());
            if (this.itemsSelected.containsKey(valueOf)) {
                this.itemsSelected.remove(valueOf);
            }
            if (this.diaryItemsChecked.contains(valueOf)) {
                this.diaryItemsChecked.remove(valueOf);
            }
        }
    }

    private void addItemToSelectedList(DatabaseObject databaseObject) {
        if (databaseObject != null) {
            long localId = databaseObject.getLocalId();
            if (!this.itemsSelected.containsKey(Long.valueOf(localId))) {
                this.itemsSelected.put(Long.valueOf(localId), databaseObject);
            }
            if (!this.diaryItemsChecked.contains(Long.valueOf(localId))) {
                this.diaryItemsChecked.add(Long.valueOf(localId));
            }
        }
    }

    private boolean toggleHeaderSection(String str, boolean z) {
        boolean z2 = z && !this.selectAllMeals.contains(str);
        if (z2) {
            this.selectAllMeals.add(str);
        } else {
            this.selectAllMeals.remove(str);
        }
        return z2;
    }

    private boolean areAllItemsInSelectedList(ArrayList<DatabaseObject> arrayList) {
        if (CollectionUtils.isEmpty((Collection<?>) arrayList) || CollectionUtils.isEmpty(this.itemsSelected)) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!this.itemsSelected.containsKey(Long.valueOf(((DatabaseObject) it.next()).getLocalId()))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r6.equals("Exercise") == false) goto L_0x0036;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean areAllItemsOfSectionSelected(com.myfitnesspal.shared.model.v1.DiaryDay r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = -1
            int r2 = r6.hashCode()
            r3 = -828903721(0xffffffffce97eed7, float:-1.27450611E9)
            if (r2 == r3) goto L_0x002c
            r3 = 1439848066(0x55d25682, float:2.89086238E13)
            if (r2 == r3) goto L_0x0022
            r3 = 2120967672(0x7e6b65f8, float:7.822451E37)
            if (r2 == r3) goto L_0x0019
            goto L_0x0036
        L_0x0019:
            java.lang.String r2 = "Exercise"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0022:
            java.lang.String r0 = "Cardio Exercise"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x002c:
            java.lang.String r0 = "Strength Exercise"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0036
            r0 = 2
            goto L_0x0037
        L_0x0036:
            r0 = -1
        L_0x0037:
            switch(r0) {
                case 0: goto L_0x005a;
                case 1: goto L_0x0050;
                case 2: goto L_0x0046;
                default: goto L_0x003a;
            }
        L_0x003a:
            java.util.List r5 = r5.getFoodEntriesForMealName(r6)
            if (r5 == 0) goto L_0x0064
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>(r5)
            goto L_0x006a
        L_0x0046:
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.ArrayList r5 = r5.getStrengthExerciseEntries()
            r6.<init>(r5)
            goto L_0x006a
        L_0x0050:
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.ArrayList r5 = r5.getCardioExerciseEntries()
            r6.<init>(r5)
            goto L_0x006a
        L_0x005a:
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.List r5 = r5.getExerciseEntries()
            r6.<init>(r5)
            goto L_0x006a
        L_0x0064:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = r5
        L_0x006a:
            boolean r5 = r4.areAllItemsInSelectedList(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.diary.util.DiaryDelegate.areAllItemsOfSectionSelected(com.myfitnesspal.shared.model.v1.DiaryDay, java.lang.String):boolean");
    }

    private boolean updateSectionSelectAll(DatabaseObject databaseObject, DiaryDay diaryDay) {
        String sectionNameForListItem = getSectionNameForListItem(databaseObject);
        return Strings.notEmpty(sectionNameForListItem) && toggleHeaderSection(sectionNameForListItem, areAllItemsOfSectionSelected(diaryDay, sectionNameForListItem));
    }

    public String getSectionNameForListItem(DatabaseObject databaseObject) {
        if (databaseObject instanceof FoodEntry) {
            return ((FoodEntry) databaseObject).getMealName();
        }
        if (databaseObject instanceof ExerciseEntry) {
            return "Exercise";
        }
        return null;
    }

    public boolean diaryItemCheckedEvent(DiaryDay diaryDay, DatabaseObject databaseObject, boolean z) {
        if (z) {
            addItemToSelectedList(databaseObject);
        } else {
            removeItemFromSelectedList(databaseObject);
        }
        return updateSectionSelectAll(databaseObject, diaryDay);
    }

    public void copyEntriesToDate(DiaryDay diaryDay, Date date) {
        if (!CollectionUtils.isEmpty((Collection<?>) this.diaryItemsChecked) && diaryDay != null) {
            DiaryDay diaryDayForDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForDateSync(date);
            diaryDayForDateSync.copyFoodEntriesFromDiaryDay(diaryDay, this.diaryItemsChecked);
            diaryDayForDateSync.copyExerciseEntriesFromDiaryDay(diaryDay, this.diaryItemsChecked);
            diaryDay.setJustCopiedFromDate(true);
            FoodEntry foodEntry = diaryDay.getFoodEntry((Long) this.diaryItemsChecked.get(0));
            if (foodEntry != null) {
                reportMealCopyAnalytics(foodEntry.getMealName(), diaryDay.getDate(), date);
            }
            ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
            this.diaryItemsChecked.clear();
        }
    }

    public List<FoodEntry> getCheckedFoodEntryItemsFromDiaryDay(DiaryDay diaryDay) {
        ArrayList arrayList = new ArrayList();
        ArrayList diaryItemsChecked2 = getDiaryItemsChecked();
        if (CollectionUtils.isEmpty((Collection<?>) diaryItemsChecked2) || diaryDay == null) {
            return arrayList;
        }
        Iterator it = diaryItemsChecked2.iterator();
        while (it.hasNext()) {
            FoodEntry foodEntry = diaryDay.getFoodEntry((Long) it.next());
            if (foodEntry != null) {
                arrayList.add(foodEntry);
            }
        }
        return arrayList;
    }

    public void copyEntriesFromSectionToSectionOnDate(DiaryDay diaryDay, Date date, String str, String str2) {
        String str3;
        if (diaryDay != null && !Strings.isEmpty(str2)) {
            DiaryDay diaryDayForDateSync = ((DiaryService) this.diaryService.get()).getDiaryDayForDateSync(date);
            char c = 65535;
            int hashCode = str2.hashCode();
            int i = 2;
            if (hashCode != -828903721) {
                if (hashCode != 1439848066) {
                    if (hashCode == 2120967672 && str2.equals("Exercise")) {
                        c = 2;
                    }
                } else if (str2.equals(Report.CARDIO_EXERCISE)) {
                    c = 0;
                }
            } else if (str2.equals(Report.STRENGTH_EXERCISE)) {
                c = 1;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                    if (Strings.equals(str2, Report.CARDIO_EXERCISE)) {
                        i = 0;
                    } else if (Strings.equals(str2, Report.STRENGTH_EXERCISE)) {
                        i = 1;
                    }
                    diaryDayForDateSync.copyExerciseEntriesFromDiaryDay(diaryDay, i);
                    str3 = getString(R.string.exercises_from, DateTimeUtils.formatExtraBrief(diaryDay.getDate()));
                    break;
                default:
                    diaryDayForDateSync.copyFoodEntriesFromMealOfDiaryDayInDifferentMeal(diaryDay, str2, str);
                    str3 = getString(R.string.record_from_date, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(str2, (UserEnergyService) this.userEnergyService.get()), DateTimeUtils.formatExtraBrief(diaryDay.getDate()));
                    break;
            }
            ((Session) this.session.get()).getUser().setActiveDate(date);
            diaryDayForDateSync.setJustAddedPrimaryText(str3);
            diaryDayForDateSync.setJustCopiedFromDate(true);
            ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        }
    }

    private void reportMealCopyAnalytics(String str, Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        DateTimeUtils.resetTime(instance);
        DateTimeUtils.resetTime(instance2);
        ((DiaryAnalyticsHelper) this.diaryAnalyticsHelper.get()).reportCopyMealEvent(str, Math.abs(DateTimeUtils.getNumberOfDaysBetween(instance, instance2)));
    }

    public void onSelectAllClicked(DiaryDay diaryDay) {
        boolean areAllItemsSelected = areAllItemsSelected(diaryDay);
        this.diaryItemsChecked.clear();
        this.itemsSelected.clear();
        if (!areAllItemsSelected && diaryDay != null) {
            Iterator it = diaryDay.getFoodEntries().iterator();
            while (it.hasNext()) {
                addItemToSelectedList((FoodEntry) it.next());
            }
            for (ExerciseEntry exerciseEntry : diaryDay.getExerciseEntries()) {
                if (!exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
                    addItemToSelectedList(exerciseEntry);
                }
            }
        }
    }

    public boolean areAllItemsSelected(DiaryDay diaryDay) {
        boolean z = false;
        if (diaryDay == null) {
            return false;
        }
        int size = CollectionUtils.size((Collection<?>) diaryDay.getFoodEntries());
        for (ExerciseEntry exercise : diaryDay.getExerciseEntries()) {
            if (!exercise.getExercise().isCalorieAdjustmentExercise()) {
                size++;
            }
        }
        if (size > 0 && CollectionUtils.size((Collection<?>) this.diaryItemsChecked) == size) {
            z = true;
        }
        return z;
    }

    public void clearDiaryItemsChecked() {
        Map<Long, DatabaseObject> map = this.itemsSelected;
        if (map != null) {
            map.clear();
        }
        ArrayList<Long> arrayList = this.diaryItemsChecked;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void clearSelectAllList() {
        this.selectAllMeals.clear();
    }

    public void recreateCheckedItemsList(List<Long> list) {
        this.diaryItemsChecked = new ArrayList<>(list);
    }

    public int getItemsSelectedCount() {
        return CollectionUtils.size(this.itemsSelected);
    }

    public Map<Long, DatabaseObject> getItemsSelected() {
        return this.itemsSelected;
    }

    public ArrayList<Long> getDiaryItemsChecked() {
        return this.diaryItemsChecked;
    }

    public void startLoggingAddFoodFlow() {
        getActionTrackingService().registerEvent("recipe_importer", "channel", Extras.REFERRER_DIARY_ADD_FOOD);
        ((DiaryService) this.diaryService.get()).startLoggingFlow(Extras.REFERRER_DIARY_ADD_FOOD);
    }

    public int onInsightsQuestionAnswered(int i) {
        int i2;
        if (CollectionUtils.notEmpty((Collection<?>) this.foodInsights)) {
            int size = this.foodInsights.size();
            i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                FoodAnalyzerResponseData foodAnalyzerResponseData = (FoodAnalyzerResponseData) this.foodInsights.get(i2);
                if (foodAnalyzerResponseData.isQuestion() && foodAnalyzerResponseData.getFoodQuestion().getId() == i) {
                    break;
                }
                i2++;
            }
        }
        i2 = -1;
        if (i2 >= 0) {
            this.foodInsights.remove(i2);
        }
        return i2;
    }

    public void updateQuickAddEntry(DiaryDay diaryDay, String str, QuickAddFood quickAddFood, User user) {
        FoodEntry foodEntry;
        FoodEntry foodEntry2 = this.quickCalorieEntryToEdit;
        if (foodEntry2 != null) {
            diaryDay.deleteFoodEntry(foodEntry2);
        }
        if (shouldEditUsingPremiumQuickAdd(this.quickCalorieEntryToEdit)) {
            foodEntry = FoodEntry.quickAddedPremiumFoodEntry(user, quickAddFood, str, (DbConnectionManager) this.dbConnectionManager.get());
        } else {
            foodEntry = FoodEntry.quickAddedCaloriesFoodEntry(quickAddFood.getCalories(), str, (Session) this.session.get(), (DbConnectionManager) this.dbConnectionManager.get());
        }
        diaryDay.addFoodEntry(foodEntry);
        this.quickCalorieEntryToEdit = null;
    }

    private void postEvent(Object obj) {
        ((MfpUiComponentInterface) this.activityContext).postEventAfterResume(obj);
    }

    private String getString(int i, Object... objArr) {
        return this.activityContext.getString(i, objArr);
    }

    private NavigationHelper getNavigationHelper() {
        return (NavigationHelper) this.navigationHelper.get();
    }

    private ActionTrackingService getActionTrackingService() {
        return (ActionTrackingService) this.actionTrackingService.get();
    }

    public ArrayList<FoodAnalyzerResponseData> getFoodInsights() {
        return this.foodInsights;
    }

    public String getMealNameForQuickAdd() {
        return this.mealNameForQuickAdd;
    }

    public void setWalkthroughVisible(boolean z) {
        this.walkthroughVisible = z;
    }

    private String getMealGoalEnergyValue(MfpNutrientGoal mfpNutrientGoal, Date date, int i) {
        MealGoal mealGoalForMeal = ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMealGoalForMeal(mfpNutrientGoal, date, i);
        if (mealGoalForMeal == null || mealGoalForMeal.getEnergy() == null) {
            return null;
        }
        return LocalizedEnergy.getDisplayStringWithoutUnits(LocalizedEnergy.fromMeasuredValue(mealGoalForMeal.getEnergy()), ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit());
    }

    /* access modifiers changed from: private */
    public void dismissMealGoalsPromoCard() {
        ((UserApplicationSettingsService) this.userApplicationSettingsService.get()).setMealGoalsBuyUpsellDismissed(true);
        postEvent(new DiaryPromoItemDismissedEvent());
    }

    private Tuple2<String, String> getExerciseEntryPartnerTuple(ExerciseEntry exerciseEntry) {
        MfpExerciseMetadataForSteps stepsData = exerciseEntry.getStepsData();
        String str = null;
        if (exerciseEntry.getExercise().isCalorieAdjustmentExercise()) {
            if (GoogleFitStepsUtils.isGoogleFitStepSource(exerciseEntry)) {
                Tuple.create("mfp-mobile-android-google", "mfp-mobile-android-google");
            } else {
                String str2 = (String) exerciseEntry.getExtraProperties().get(Keys.CLIENT_APP_NAME);
                String str3 = (String) exerciseEntry.getExtraProperties().get("source");
                if (!Strings.notEmpty(str2) || !Strings.notEmpty(str3)) {
                    Tuple.create(Extras.MFP_MOBILE_IOS, Source.IPHONE);
                } else {
                    MfpPlatformApp findByClientId = ((AppGalleryService) this.appGalleryService.get()).findByClientId(str3);
                    if (findByClientId != null) {
                        str = findByClientId.getIconImage().getFilename();
                    }
                    return Tuple.create(str, str2);
                }
            }
        } else if (stepsData != null) {
            MfpPlatformApp findByStepSource = ((AppGalleryService) this.appGalleryService.get()).findByStepSource(((StepService) this.stepService.get()).getStepSource(stepsData.getClientId()));
            if (findByStepSource != null) {
                String name = findByStepSource.getName();
                if (Strings.isEmpty(name)) {
                    name = stepsData.getClientId().toUpperCase();
                }
                if (findByStepSource.getIconImage() != null) {
                    str = findByStepSource.getIconImage().getFilename();
                }
                return Tuple.create(str, name);
            }
        }
        return Tuple.create("", ((ExerciseStringService) this.exerciseStringService.get()).getDescriptionName(exerciseEntry.getExercise()));
    }
}
