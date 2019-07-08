package com.myfitnesspal.feature.goals.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.event.GoalPreferencesUpdatedEvent;
import com.myfitnesspal.feature.goals.event.WeightLossGoalDialogEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.AdditionalNutrientGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.EditCustomMacroGoalsActivity;
import com.myfitnesspal.feature.goals.ui.activity.ExerciseCaloriesActivity;
import com.myfitnesspal.feature.goals.ui.activity.MealGoalsActivity;
import com.myfitnesspal.feature.goals.ui.adapter.ActivityItem;
import com.myfitnesspal.feature.goals.ui.adapter.GoalListItem;
import com.myfitnesspal.feature.goals.ui.adapter.PremiumCtaItem;
import com.myfitnesspal.feature.goals.ui.dialog.ActivityLevelDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.ExerciseGoalsDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.WeightGoalDialogFragment;
import com.myfitnesspal.feature.goals.util.EatingDisorderMaleCalorieGoalAlertUtil;
import com.myfitnesspal.feature.goals.util.GoalPreferenceUtil;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.progress.ui.fragment.LegacyWeightPickerFragment;
import com.myfitnesspal.shared.constants.Constants.ABTest.GoalsScreenPremiumCta;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.event.ActivityLevelDialogEvent;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.event.GoalsRecalculatedEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask;
import com.myfitnesspal.shared.task.UpdateUserV2StartingWeightTask;
import com.myfitnesspal.shared.task.UpdateUserV2StartingWeightTask.CompletedEvent;
import com.myfitnesspal.shared.ui.adapter.HeaderListItem;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.myfitnesspal.shared.ui.adapter.SimpleSectionedAdapter;
import com.myfitnesspal.shared.ui.dialog.impl.WeightDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.myfitnesspal.shared.util.UpdateWeightProxy.FinishMode;
import com.myfitnesspal.shared.util.UpdateWeightProxy.UpdateMode;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class GoalsFragment extends MfpFragment {
    private static final int ADDITIONAL_NUTRIENT_GOAL = 6;
    private static final int CALORIE_MACRO_NUTRIENT_GOAL = 3;
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    private static final int EDIT_ACTIVITY_LEVEL = 2;
    private static final int EDIT_CURRENT_WEIGHT = 1;
    private static final int EDIT_EXERCISE_GOALS = 5;
    private static final int EDIT_GOAL_WEIGHT = 0;
    private static final int EDIT_STARTING_WEIGHT = 8;
    private static final int EDIT_WEIGHT_LOSS_GOAL = 4;
    private static final int EXERCISE_CALORIES = 7;
    private static final boolean GOAL_UPDATE_FROM_SETTINGS = true;
    private static final int MEAL_GOALS = 9;
    private static final int MEAL_MACROS = 10;
    private static final String PREMIUM_FEATURE_ID_GOALS_SCREEN_CTA = "goals_screen_cta";
    public static final double TEN_POUNDS = 10.0d;
    private static final String WEIGHT_PICKER = "weight_picker";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public boolean isExerciseCaloriesOn;
    private ListView listView;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<SyncService> syncService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserHeightService> userHeightService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        setupList();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        fetchExerciseCaloriesStateForDailyGoal();
        refresh();
        this.listView.setEnabled(true);
    }

    public void onPause() {
        getMessageBus().post(new StartSyncEvent());
        super.onPause();
    }

    public static GoalsFragment newInstance() {
        return new GoalsFragment();
    }

    /* access modifiers changed from: protected */
    public List<ListItem> getItems() {
        UserProfile profile = ((Session) this.session.get()).getUser().getProfile();
        UserV1GoalPreferences userV1GoalPreferences = ((Session) this.session.get()).getUser().getUserV1GoalPreferences();
        int i = 0;
        ArrayList arrayList = new ArrayList(Arrays.asList(new GoalListItem[]{GoalListItem.newInstance(1, getString(R.string.currentWeightTxt), ((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.CURRENT, BitmapDescriptorFactory.HUE_RED)), GoalListItem.newInstance(0, getString(R.string.goalWeightTxt), ((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.GOAL, BitmapDescriptorFactory.HUE_RED)), GoalListItem.newInstance(4, getString(R.string.weekly_goal), GoalPreferenceUtil.getStringForWeeklyGoal(getActivity(), (UserWeightService) this.userWeightService.get())), GoalListItem.newInstance(2, getString(R.string.activityLevelTxt), ActivityItem.getActivityLabelFromName(getActivity(), profile.getLifestyleName()))}));
        String startingWeightDate = ((UserWeightService) this.userWeightService.get()).getStartingWeightDate();
        if (ConfigUtils.isNewStartingWeightOn(getConfigService()) && Strings.notEmpty(startingWeightDate)) {
            arrayList.add(0, GoalListItem.newInstance(8, getString(R.string.startingWeightTxt), getStartingWeightString(startingWeightDate)));
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(new ListItem[]{new HeaderListItem(getString(R.string.fitness_goals)), GoalListItem.newInstance(5, getString(R.string.workouts_week), Strings.toString(Integer.valueOf(userV1GoalPreferences.getWorkoutsPerWeek()))), GoalListItem.newInstance(5, getString(R.string.minutes_workout), Strings.toString(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getMinutesPerWorkoutForDisplay()))}));
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.AssignExerciseCalories);
        if (featureAvailability != Availability.Hidden) {
            if (featureAvailability == Availability.Locked) {
                i = R.drawable.ic_premium_lock;
            }
            arrayList2.add(GoalListItem.newInstance(7, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.TITLE_ACTIVITY_EXERCISE, this.userEnergyService), getString(this.isExerciseCaloriesOn ? R.string.on : R.string.off)).setSubtitle(getString(R.string.subtitle_activity_exercise_calories)).setIconResId(i).setShowIconOnRight(true));
        }
        arrayList.addAll(getNutritionGoalsList());
        arrayList.addAll(arrayList2);
        if (getConfigService().isVariantEnabled(GoalsScreenPremiumCta.NAME) && ((PremiumService) this.premiumService.get()).isPremiumAvailable() && !((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            arrayList.add(new PremiumCtaItem(getContext(), this.premiumAnalyticsHelper, this.userWeightService));
        }
        return arrayList;
    }

    private String getStartingWeightString(String str) {
        Date parse = DateTimeUtils.parse("yyyy-MM-dd", str);
        return getResources().getString(R.string.sw_on_date_goals_screen, new Object[]{((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.STARTING, BitmapDescriptorFactory.HUE_RED), DATE_FORMAT.format(parse)});
    }

    private void fetchExerciseCaloriesStateForDailyGoal() {
        ((NutrientGoalService) this.nutrientGoalService.get()).getDailyGoalForDayOfWeek(new Function1<MfpDailyGoal>() {
            public void execute(MfpDailyGoal mfpDailyGoal) {
                GoalsFragment.this.isExerciseCaloriesOn = mfpDailyGoal.isAssignExerciseEnergyOn();
                GoalsFragment.this.refresh();
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
            }
        }, new Date());
    }

    private void setupList() {
        this.listView.setAdapter(createAdapter());
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Ln.d("Item Clicked", new Object[0]);
                ListItem listItem = (ListItem) adapterView.getItemAtPosition(i);
                if (listItem instanceof GoalListItem) {
                    GoalsFragment.this.handleClickedGoalItem((GoalListItem) listItem, view);
                } else if (listItem instanceof PremiumCtaItem) {
                    GoalsFragment.this.handlePremiumCtaItemClick();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refresh() {
        if (isEnabled() && isAdded()) {
            SimpleSectionedAdapter simpleSectionedAdapter = (SimpleSectionedAdapter) this.listView.getAdapter();
            simpleSectionedAdapter.clear();
            simpleSectionedAdapter.addAll(getItems());
            ListViewUtils.notifyDataSetChanged(this.listView);
        }
    }

    /* access modifiers changed from: private */
    public void handleClickedGoalItem(GoalListItem goalListItem, View view) {
        switch (goalListItem.getGoalType()) {
            case 0:
                confirmOperationWithUser(new Function0() {
                    public void execute() {
                        GoalsFragment.this.showEditGoalWeightDialog();
                    }
                });
                return;
            case 1:
                confirmOperationWithUser(new Function0() {
                    public void execute() {
                        WeightDialogFragment.newInstance(WeightType.CURRENT, BitmapDescriptorFactory.HUE_RED).show(GoalsFragment.this.getChildFragmentManager(), Fragments.CURRENT_WEIGHT_DIALOG);
                    }
                });
                return;
            case 2:
                confirmOperationWithUser(new Function0() {
                    public void execute() {
                        ActivityLevelDialogFragment.newInstance().show(GoalsFragment.this.getChildFragmentManager(), Fragments.ACTIVITY_LEVEL_DIALOG);
                    }
                });
                return;
            case 3:
                showCalorieAndMacroGoalsSetting();
                return;
            case 4:
                confirmOperationWithUser(new Function0() {
                    public void execute() {
                        WeightGoalDialogFragment.newInstance().show(GoalsFragment.this.getChildFragmentManager(), Fragments.WEIGHT_GOAL_DIALOG);
                    }
                });
                return;
            case 5:
                ExerciseGoalsDialogFragment.newInstance().show(getChildFragmentManager(), Fragments.EXERCISE_GOAL_DIALOG);
                return;
            case 6:
                showAdditionalNutrientGoal();
                return;
            case 7:
                showExerciseCalories();
                return;
            case 8:
                ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.STARTING_WEIGHT_CLICK);
                showDialogFragment(WeightDialogFragment.newInstance(WeightType.STARTING, BitmapDescriptorFactory.HUE_RED, true, ((UserWeightService) this.userWeightService.get()).getStartingWeightDate()), Fragments.STARTING_WEIGHT_DIALOG);
                return;
            case 9:
                showMealGoals();
                return;
            case 10:
                handleMealMacros(view);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void handlePremiumCtaItemClick() {
        getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PREMIUM_FEATURE_ID_GOALS_SCREEN_CTA)).startActivity();
    }

    private void handleMealMacros(View view) {
        if (!((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros)) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PremiumFeature.MealMacros)).startActivity();
            return;
        }
        CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(view, R.id.checkbox);
        boolean isChecked = compoundButton.isChecked();
        compoundButton.toggle();
        User user = getSession().getUser();
        user.setDisplayDiaryMealMacros(!isChecked);
        user.updatePropertyNamed(Basic.DISPLAY_DIARY_MEAL_MACROS);
        ((PremiumAnalyticsHelper) this.premiumAnalyticsHelper.get()).reportMealMacroSettingToggledFromGoalsScreen(!isChecked);
    }

    private void showExerciseCalories() {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.AssignExerciseCalories);
        if (featureAvailability == Availability.Available) {
            showAssignExerciseCalories();
        } else if (featureAvailability == Availability.Locked) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PremiumFeature.AssignExerciseCalories)).startActivity();
        } else if (featureAvailability == Availability.Revoked) {
            getMessageBus().post(new AlertEvent(getString(R.string.premium_feature_revoked)).asToast());
        }
    }

    private void showAdditionalNutrientGoal() {
        getNavigationHelper().withIntent(AdditionalNutrientGoalsActivity.newStartIntent(getActivity())).startActivity();
    }

    private void showCalorieAndMacroGoalsSetting() {
        getNavigationHelper().withIntent(EditCustomMacroGoalsActivity.newStartIntent(getActivity())).startActivity();
    }

    private void showAssignExerciseCalories() {
        getNavigationHelper().withIntent(ExerciseCaloriesActivity.newStartIntent(getActivity())).startActivity();
    }

    /* access modifiers changed from: private */
    public void showEditGoalWeightDialog() {
        double d;
        double d2;
        double currentHeightInInches = ((UserHeightService) this.userHeightService.get()).getCurrentHeightInInches();
        float currentWeightInPounds = ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds();
        if (((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() > BitmapDescriptorFactory.HUE_RED) {
            d = UpdateWeightProxy.calculateWeight(18.5d, currentHeightInInches);
            d2 = Math.max(((double) currentWeightInPounds) + 10.0d, UpdateWeightProxy.calculateWeight(25.0d, currentHeightInInches));
        } else {
            double d3 = ((double) currentWeightInPounds) + 10.0d;
            d = Math.min(UpdateWeightProxy.calculateWeight(18.5d, currentHeightInInches), d3);
            d2 = Math.max(d3, UpdateWeightProxy.calculateWeight(25.0d, currentHeightInInches));
        }
        LegacyWeightPickerFragment.newInstance(WeightType.GOAL, (double) ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds(), Math.ceil(d), Math.floor(d2), true).show(getChildFragmentManager(), WEIGHT_PICKER);
    }

    private void showMealGoals() {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.MealGoals);
        if (featureAvailability == Availability.Available) {
            getNavigationHelper().withIntent(MealGoalsActivity.newStartIntent(getActivity())).startActivity();
        } else if (featureAvailability == Availability.Locked) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), PremiumFeature.MealGoals)).startActivity();
        } else if (featureAvailability == Availability.Revoked) {
            getMessageBus().post(new AlertEvent(getString(R.string.premium_feature_revoked)).asToast());
        }
    }

    @Subscribe
    public void onGoalsRecalculated(GoalsRecalculatedEvent goalsRecalculatedEvent) {
        new EatingDisorderMaleCalorieGoalAlertUtil(getSession(), getNavigationHelper(), getActivity()).showRaisedMaleCalorieGoalAlertIfNecessary();
        refresh();
    }

    @Subscribe
    public void onWeightLossGoalDialogEvent(WeightLossGoalDialogEvent weightLossGoalDialogEvent) {
        User user = getSession().getUser();
        user.getUserV1GoalPreferences().setGoalLossPerWeek(weightLossGoalDialogEvent.getItem().getValue());
        user.updatePropertyNamed(Goals.GOAL_LOSS_PER_WEEK);
        recalculateGoals();
    }

    @Subscribe
    public void onGoalPreferencesUpdatedEvent(GoalPreferencesUpdatedEvent goalPreferencesUpdatedEvent) {
        User user = getSession().getUser();
        user.getUserV1GoalPreferences().setWorkoutsPerWeek(goalPreferencesUpdatedEvent.getWorkoutsPerWeek());
        user.getUserV1GoalPreferences().setMinutesPerWorkout(goalPreferencesUpdatedEvent.getMinutesPerWorkout());
        user.updatePropertyNamed(Goals.WORKOUTS_PER_WEEK);
        user.updatePropertyNamed(Goals.MIN_PER_WORKOUT);
        ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental);
        refresh();
    }

    @Subscribe
    public void onActivityLevelDialogEvent(ActivityLevelDialogEvent activityLevelDialogEvent) {
        User user = ((Session) this.session.get()).getUser();
        UserProfile profile = user.getProfile();
        profile.setLifestyleName(activityLevelDialogEvent.getItem().getName());
        user.setProfile(profile);
        user.updatePropertyNamed(Basic.LIFESTYLE_NAME);
        recalculateGoals();
    }

    @Subscribe
    public void onDialogWeightEvent(DialogWeightEvent dialogWeightEvent) {
        float weight = dialogWeightEvent.getWeight();
        WeightType weightType = dialogWeightEvent.getWeightType();
        if (weightType == WeightType.STARTING) {
            new UpdateUserV2StartingWeightTask(this.userWeightService, weight, dialogWeightEvent.getStartingWeightDate()).run(getRunner());
        } else if (weightType == WeightType.CURRENT) {
            new UpdateWeightProxy(getActivity(), getNavigationHelper(), getMessageBus()).updateWeightAndPromptForWarnings(weight, FinishMode.Back, UpdateMode.WeightAndGoalLoss);
            recalculateGoals();
        } else {
            ((UserWeightService) this.userWeightService.get()).setWeight(weight, weightType);
            if (weightType == WeightType.GOAL) {
                ((UserWeightService) this.userWeightService.get()).recalculateAndUpdateGoalLossPerWeek();
            }
            recalculateGoals();
        }
    }

    private void recalculateGoals() {
        setBusy(true);
        this.listView.setEnabled(false);
        new RecalculateNutrientGoalsTask(this.nutrientGoalsUtil, this.diaryService, this.nutrientGoalService, this.session).run(getRunner());
    }

    private ListAdapter createAdapter() {
        return new SimpleSectionedAdapter(getActivity(), getItems());
    }

    private void confirmOperationWithUser(Function0 function0) {
        RecalculateNutrientGoalsTask.confirmRecalculation(getActivity(), (LocalizedStringsUtil) this.localizedStringsUtil.get(), (UserEnergyService) this.userEnergyService.get(), function0, null);
    }

    private List<ListItem> getNutritionGoalsList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HeaderListItem(getString(R.string.nutrition_goals)));
        arrayList.add(GoalListItem.newInstance(3, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ENERGY_AND_MACRONUTRIENT_GOALS, this.userEnergyService)).setSubtitle(getString(R.string.energy_and_macronutrient_goals_subtitle)));
        if (((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.MealGoals) != Availability.Hidden) {
            arrayList.add(GoalListItem.newInstance(9, getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.meal_goals_cal_title : R.string.meal_goals_kj_title)).setSubtitle(getString(((UserEnergyService) this.userEnergyService.get()).isCalories() ? R.string.meal_goals_cal_subtitle : R.string.meal_goals_kl_subtitle)).setIconResId(!((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealGoals) ? R.drawable.ic_premium_lock : 0).setShowIconOnRight(true));
        }
        addMacrosByMealItemIfValid(arrayList);
        arrayList.add(GoalListItem.newInstance(6, getString(R.string.additional_nutrient_goals)));
        return arrayList;
    }

    private void addMacrosByMealItemIfValid(List<ListItem> list) {
        if (ConfigUtils.showMacrosByMealGoalScreenSetting(getConfigService()) && ((PremiumService) this.premiumService.get()).isFeatureAvailable(PremiumFeature.MealMacros)) {
            boolean showUpdatedMacrosByMealGoalStrings = ConfigUtils.showUpdatedMacrosByMealGoalStrings(getConfigService());
            GoalListItem subtitle = GoalListItem.newInstance(10, getString(showUpdatedMacrosByMealGoalStrings ? R.string.show_meal_macros_variant_b : R.string.show_meal_macros)).setSubtitle(getString(showUpdatedMacrosByMealGoalStrings ? R.string.show_carbs_protein_fat_by_gram_percent : R.string.show_meal_macros_subtext));
            if (((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.MealMacros)) {
                subtitle.setShowCheckBox(true, getSession().getUser().shouldDisplayDiaryMealMacros());
            } else {
                subtitle.setShowIconOnRight(true).setIconResId(R.drawable.ic_premium_lock);
            }
            list.add(subtitle);
        }
    }

    @Subscribe
    public void onUserV2StartingWeightUpdated(CompletedEvent completedEvent) {
        if (!completedEvent.isFrom(getRunner())) {
            return;
        }
        if (completedEvent.getFailure() != null) {
            postEvent(new AlertEvent(getString(R.string.error_could_not_set_goals)));
        } else {
            recalculateGoals();
        }
    }

    @Subscribe
    public void onNutrientGoalsRecalculated(RecalculateNutrientGoalsTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            this.listView.setEnabled(true);
            RecalculateNutrientGoalsTask.showErrorDialogIfFailed((MfpFragment) this, completedEvent);
            refresh();
        }
    }
}
