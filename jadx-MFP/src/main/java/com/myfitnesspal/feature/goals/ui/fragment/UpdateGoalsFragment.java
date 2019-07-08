package com.myfitnesspal.feature.goals.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.event.UpdateGoalsCompleteEvent;
import com.myfitnesspal.feature.goals.event.WeightLossGoalDialogEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.NavigateToMarketingOptInEvent;
import com.myfitnesspal.feature.goals.ui.adapter.ActivityItem;
import com.myfitnesspal.feature.goals.ui.dialog.ActivityLevelDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.WeightGoalDialogFragment;
import com.myfitnesspal.feature.goals.util.EatingDisorderMaleCalorieGoalAlertUtil;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.task.RegionLookupTask;
import com.myfitnesspal.shared.constants.Constants.ABTest.ReactivatorAdjustGoalAndroid201412;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.SharedConstants.UserGoals;
import com.myfitnesspal.shared.event.ActivityLevelDialogEvent;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.event.GoalsRecalculatedEvent;
import com.myfitnesspal.shared.event.UpdateGoalsSetEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v1.UserWeight;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask.CompletedEvent;
import com.myfitnesspal.shared.task.UpdateUserV2StartingWeightTask;
import com.myfitnesspal.shared.ui.dialog.impl.WeightDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.myfitnesspal.shared.util.UpdateWeightProxy.FinishMode;
import com.myfitnesspal.shared.util.UpdateWeightProxy.Listener;
import com.myfitnesspal.shared.util.UpdateWeightProxy.UpdateMode;
import com.squareup.otto.Subscribe;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class UpdateGoalsFragment extends MfpFragment {
    private static final String ACTIVITY_LEVEL = "activity_level";
    private static final int EDIT_ACTIVITY_LEVEL = 2;
    private static final int EDIT_CURRENT_WEIGHT = 1;
    private static final int EDIT_GOAL_WEIGHT = 0;
    private static final int EDIT_WEIGHT_LOSS_GOAL = 3;
    private static final String ENABLED = "enabled";
    private static final String IS_STARTING_WEIGHT_RESET = "is_starting_weight_reset";
    private static final String MARKETING_EMAILS_ENABLED = "marketing_emails_enabled";
    private static final String NEW_ACTIVITY_LEVEL = "newActivityLevel";
    private static final String NEW_CURRENT_WEIGHT = "newCurrentWeight";
    private static final String NEW_GOAL_WEIGHT = "newGoalWeight";
    private static final String NEW_WEIGHT_LOSS_GOAL = "newWeightLossGoal";
    private static final String SEND_MEAL_REMINDERS = "send_meal_reminders";
    private static final String USER_CHANGED_CURRENT_WEIGHT = "user_changed_current_weight";
    private static final String USER_CHANGED_GOAL_WEIGHT = "user_changed_goal_weight";
    private static final String WEIGHT_LOSS_GOAL_RATE = "weight_loss_goal_rate";
    private static final String WEIGHT_LOSS_UNIT = "weight_loss_unit";
    private static final String WELCOME_BACK_SCREEN_SAVE_GOALS = "welcome_back_screen_save_goals";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    /* access modifiers changed from: private */
    public CheckBox autoRemindersCheckbox;
    @Inject
    Lazy<CountryService> countryService;
    @Inject
    Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public CheckBox emailOptInCheckbox;
    /* access modifiers changed from: private */
    public boolean isMarketingOptInEnabled;
    /* access modifiers changed from: private */
    public boolean isReactivationMarketingEnabled;
    /* access modifiers changed from: private */
    public boolean isRegionUS;
    private View listHeaderView;
    private ListView listView;
    private View listViewFooter;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    /* access modifiers changed from: private */
    public String newActivityLevel;
    /* access modifiers changed from: private */
    public float newCurrentWeight;
    /* access modifiers changed from: private */
    public float newGoalWeight;
    /* access modifiers changed from: private */
    public float newWeightLossGoal;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    /* access modifiers changed from: private */
    public float originalCurrentWeight;
    /* access modifiers changed from: private */
    public float originalGoalWeight;
    @Inject
    Lazy<RemindersService> remindersService;
    /* access modifiers changed from: private */
    public View saveButton;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<SignUpService> signUpService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public static class ProfileListAdapter extends ArrayAdapter<Integer> {
        private final UpdateGoalsFragment parent;

        public ProfileListAdapter(UpdateGoalsFragment updateGoalsFragment, int i, int i2, List<Integer> list) {
            super(updateGoalsFragment.getActivity(), i, i2, list);
            this.parent = updateGoalsFragment;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            Integer num = (Integer) getItem(i);
            TextView textView = (TextView) ViewUtils.findById(view2, R.id.tvItemValue);
            ((TextView) ViewUtils.findById(view2, 16908310)).setText(this.parent.getTitleForItem(num));
            textView.setText(this.parent.getValueForItem(num));
            return view2;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        this.listView.setBackgroundColor(getResources().getColor(R.color.white));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.isReactivationMarketingEnabled = ConfigUtils.isReactivationMarketingOptinOptimizationEnabled(getConfigService());
        User user = getSession().getUser();
        UserV1GoalPreferences userV1GoalPreferences = user.getUserV1GoalPreferences();
        UserProfile profile = user.getProfile();
        this.isMarketingOptInEnabled = user.isMarketingOptinEnabled();
        setupLayout();
        this.originalCurrentWeight = ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds();
        this.originalGoalWeight = ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds();
        String lifestyleName = profile.getLifestyleName();
        float goalPerWeekWeight = ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight();
        if (bundle != null) {
            this.newCurrentWeight = bundle.getFloat(NEW_CURRENT_WEIGHT);
            this.newGoalWeight = bundle.getFloat(NEW_GOAL_WEIGHT);
            this.newActivityLevel = bundle.getString(NEW_ACTIVITY_LEVEL);
            this.newWeightLossGoal = bundle.getFloat(NEW_WEIGHT_LOSS_GOAL);
        } else {
            this.newCurrentWeight = this.originalCurrentWeight;
            this.newGoalWeight = this.originalGoalWeight;
            this.newActivityLevel = lifestyleName;
            this.newWeightLossGoal = goalPerWeekWeight;
        }
        boolean isLastLoginDate60DaysOrMore = DateUtil.isLastLoginDate60DaysOrMore(new Date());
        boolean z = goalPerWeekWeight > BitmapDescriptorFactory.HUE_RED;
        if (isLastLoginDate60DaysOrMore && z) {
            if (profile.getGoalCalories() <= (profile.isFemale().booleanValue() ? UserGoals.LOW_CALORIES_WOMEN : UserGoals.LOW_CALORIES_MEN) && goalPerWeekWeight >= 1.0f) {
                userV1GoalPreferences.calculateCalorieGoalForUserForGoalLossPerWeek(user.getUserV1(), ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.KILOGRAMS ? 0.6f : 0.5f);
            }
        }
        setupList();
    }

    @Subscribe
    public void onGoalsTaskCompleted(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setWorking(false);
            RecalculateNutrientGoalsTask.showErrorDialogIfFailed((MfpFragment) this, completedEvent);
        }
    }

    private void setWorking(boolean z) {
        setBusy(z);
        this.listView.setEnabled(!z);
        this.listHeaderView.setEnabled(!z);
        this.listViewFooter.setEnabled(!z);
        if (!z) {
            refreshListItems();
            refreshHeaderAndFooter();
        }
    }

    private void recalculateGoals() {
        setWorking(true);
        new RecalculateNutrientGoalsTask(this.nutrientGoalsUtil, this.diaryService, this.nutrientGoalService, this.session).run(getRunner());
    }

    private void setupLayout() {
        this.listHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.update_goals_header, null);
        ((TextView) this.listHeaderView.findViewById(R.id.subtitle)).setText(getString(R.string.update_goals_text1));
        this.listViewFooter = LayoutInflater.from(getActivity()).inflate(R.layout.update_goals_footer, null);
        refreshHeaderAndFooter();
    }

    private void refreshHeaderAndFooter() {
        ((TextView) this.listViewFooter.findViewById(R.id.txt_goal_daily_calories)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.UPDATE_GOAL_DAILY_NEW, this.userEnergyService.get()));
        ((TextView) this.listViewFooter.findViewById(R.id.txt_goal_calories_value)).setText(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultEnergyGoalForDisplay());
        postEvent(new BusyEvent(1, false));
    }

    public void onResume() {
        super.onResume();
        refreshListItems();
        getMessageBus().register(this);
    }

    public void onPause() {
        super.onPause();
        getMessageBus().unregister(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(NEW_CURRENT_WEIGHT, this.newCurrentWeight);
        bundle.putFloat(NEW_GOAL_WEIGHT, this.newGoalWeight);
        bundle.putString(NEW_ACTIVITY_LEVEL, Strings.toString(this.newActivityLevel));
        bundle.putFloat(NEW_WEIGHT_LOSS_GOAL, this.newWeightLossGoal);
    }

    private void refreshListItems() {
        ListViewUtils.notifyDataSetChanged(this.listView);
    }

    private void setupList() {
        this.listView.addHeaderView(this.listHeaderView, null, false);
        this.listView.setHeaderDividersEnabled(false);
        this.listView.addFooterView(this.listViewFooter);
        this.listView.setFooterDividersEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.saveButton = this.listViewFooter.findViewById(R.id.btn_save);
        this.saveButton.setEnabled(!this.isReactivationMarketingEnabled);
        this.saveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UpdateGoalsFragment.this.setBusySaving(true);
                UpdateGoalsFragment.this.saveButton.post(new Runnable() {
                    public void run() {
                        ((AnalyticsService) UpdateGoalsFragment.this.analyticsService.get()).reportEvent(Events.REACTIVATION_SCREEN_USER_CHANGED_GOAL_WEIGHT, new Builder().put(UpdateGoalsFragment.USER_CHANGED_GOAL_WEIGHT, String.valueOf(!NumberUtils.areEffectivelyEqual(UpdateGoalsFragment.this.originalGoalWeight, UpdateGoalsFragment.this.newGoalWeight))).build(), ReactivatorAdjustGoalAndroid201412.NAME);
                        ((AnalyticsService) UpdateGoalsFragment.this.analyticsService.get()).reportEvent(Events.REACTIVATION_SCREEN_USER_CHANGED_CURRENT_WEIGHT, new Builder().put(UpdateGoalsFragment.USER_CHANGED_CURRENT_WEIGHT, String.valueOf(!NumberUtils.areEffectivelyEqual(UpdateGoalsFragment.this.originalCurrentWeight, UpdateGoalsFragment.this.newCurrentWeight))).build(), ReactivatorAdjustGoalAndroid201412.NAME);
                        ((AnalyticsService) UpdateGoalsFragment.this.analyticsService.get()).reportEvent(Events.REACTIVATION_SCREEN_USER_CHANGED_ACTIVITY_LEVEL, new Builder().put(UpdateGoalsFragment.ACTIVITY_LEVEL, UpdateGoalsFragment.this.newActivityLevel).build(), ReactivatorAdjustGoalAndroid201412.NAME);
                        ((AnalyticsService) UpdateGoalsFragment.this.analyticsService.get()).reportEvent(Events.REACTIVATION_SCREEN_USER_CHANGED_WEIGHT_LOSS_GOAL, new Builder().put(UpdateGoalsFragment.WEIGHT_LOSS_GOAL_RATE, String.valueOf(UpdateGoalsFragment.this.newWeightLossGoal)).put(UpdateGoalsFragment.WEIGHT_LOSS_UNIT, String.valueOf(((UserWeightService) UpdateGoalsFragment.this.userWeightService.get()).getUserCurrentWeightUnit())).build(), ReactivatorAdjustGoalAndroid201412.NAME);
                        new UpdateWeightProxy(UpdateGoalsFragment.this.getActivity(), UpdateGoalsFragment.this.getNavigationHelper(), UpdateGoalsFragment.this.getMessageBus()).updateWeightAndPromptForWarnings(UpdateGoalsFragment.this.newCurrentWeight, FinishMode.Home, UpdateMode.Weight, new Listener() {
                            public void onSuccess() {
                                UpdateGoalsFragment.this.setBusySaving(false);
                                UpdateGoalsFragment.this.saveButton.setEnabled(false);
                                UpdateGoalsFragment.this.emailOptInCheckbox.setEnabled(false);
                                UpdateGoalsFragment.this.autoRemindersCheckbox.setEnabled(false);
                                if (UpdateGoalsFragment.this.isRegionUS) {
                                    UpdateGoalsFragment.this.getSession().getUser().updateNewsletterSettings(UpdateGoalsFragment.this.emailOptInCheckbox.isChecked());
                                }
                                if (!UpdateGoalsFragment.this.isReactivationMarketingEnabled || UpdateGoalsFragment.this.isMarketingOptInEnabled) {
                                    UpdateGoalsFragment.this.postEvent(new UpdateGoalsSetEvent());
                                } else {
                                    UpdateGoalsFragment.this.postEvent(UpdateGoalsFragment.this.isRegionUS ? new UpdateGoalsSetEvent() : new NavigateToMarketingOptInEvent());
                                }
                                UpdateGoalsFragment.this.updateStartingWeightIfRequired();
                            }

                            public void onNavigatedForward() {
                                UpdateGoalsFragment.this.setBusySaving(false);
                                UpdateGoalsFragment.this.getMessageBus().post(new UpdateGoalsCompleteEvent(false));
                                UpdateGoalsFragment.this.updateStartingWeightIfRequired();
                                if (!UpdateGoalsFragment.this.isReactivationMarketingEnabled || UpdateGoalsFragment.this.isRegionUS || UpdateGoalsFragment.this.isMarketingOptInEnabled) {
                                    UpdateGoalsFragment.this.getActivity().finish();
                                } else {
                                    UpdateGoalsFragment.this.postEvent(new NavigateToMarketingOptInEvent());
                                }
                            }

                            public void onCancel() {
                                UpdateGoalsFragment.this.setBusySaving(false);
                            }
                        });
                        ((RemindersService) UpdateGoalsFragment.this.remindersService.get()).addDefaultRemindersIfNecessary(UpdateGoalsFragment.this.autoRemindersCheckbox.isChecked());
                        ((AnalyticsService) UpdateGoalsFragment.this.analyticsService.get()).reportEvent(Events.REACTIVATION_SCREEN_MEAL_REMINDERS, new Builder().put("enabled", String.valueOf(UpdateGoalsFragment.this.autoRemindersCheckbox.isChecked())).build(), ReactivatorAdjustGoalAndroid201412.NAME);
                    }
                });
            }
        });
        this.autoRemindersCheckbox = (CheckBox) ViewUtils.findById(this.listViewFooter, R.id.chk_box_reminders);
        this.emailOptInCheckbox = (CheckBox) ViewUtils.findById(this.listViewFooter, R.id.chk_box_email);
        if (this.isReactivationMarketingEnabled) {
            setupMarketingOptIn();
        }
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UpdateGoalsFragment.this.showDialog(((Integer) adapterView.getItemAtPosition(i)).intValue());
            }
        });
        this.listView.setAdapter(new ProfileListAdapter(this, R.layout.update_goals_item, 16908310, getItems()));
    }

    private void setupMarketingOptIn() {
        new RegionLookupTask(((CountryService) this.countryService.get()).getShortNameFromLongName(getSession().getUser().getProfile().getCountryName()), this.signUpService).run(getRunner());
    }

    /* access modifiers changed from: private */
    public void updateStartingWeightIfRequired() {
        HashMap hashMap = new HashMap();
        hashMap.put(SEND_MEAL_REMINDERS, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(this.autoRemindersCheckbox.isChecked()));
        if (ViewUtils.isVisible(this.emailOptInCheckbox)) {
            hashMap.put(MARKETING_EMAILS_ENABLED, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(this.emailOptInCheckbox.isChecked()));
        }
        boolean z = false;
        if (!ConfigUtils.isNewStartingWeightOn(getConfigService())) {
            hashMap.put(IS_STARTING_WEIGHT_RESET, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(false));
            ((AnalyticsService) this.analyticsService.get()).reportEvent(WELCOME_BACK_SCREEN_SAVE_GOALS, (Map<String, String>) hashMap);
            return;
        }
        boolean z2 = ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() > BitmapDescriptorFactory.HUE_RED;
        float currentWeightInPounds = ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds();
        float goalWeightInPounds = ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds();
        float startingWeightInPounds = ((UserWeightService) this.userWeightService.get()).getStartingWeightInPounds();
        if ((!z2 || currentWeightInPounds <= startingWeightInPounds || startingWeightInPounds <= goalWeightInPounds) && (z2 || currentWeightInPounds >= startingWeightInPounds || startingWeightInPounds >= goalWeightInPounds)) {
            currentWeightInPounds = startingWeightInPounds;
        } else {
            z = true;
        }
        if (z) {
            new UpdateUserV2StartingWeightTask(this.userWeightService, currentWeightInPounds, Format.newIso8601DateFormat().format(Calendar.getInstance().getTime())).run(getRunner());
        }
        hashMap.put(IS_STARTING_WEIGHT_RESET, AnalyticsUtil.getAnalyticsAttributeValueForBoolean(z));
        ((AnalyticsService) this.analyticsService.get()).reportEvent(WELCOME_BACK_SCREEN_SAVE_GOALS, (Map<String, String>) hashMap);
    }

    /* access modifiers changed from: protected */
    public void setupFrameForItem(ViewGroup viewGroup, Integer num) {
        TextView textView = (TextView) ViewUtils.findById(viewGroup, R.id.text);
        if (textView == null) {
            View.inflate(getActivity(), R.layout.preference_summary, viewGroup);
            textView = (TextView) ViewUtils.findById(viewGroup, R.id.text);
        }
        textView.setText(getValueForItem(num));
    }

    /* access modifiers changed from: protected */
    public List<Integer> getItems() {
        return Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(3), Integer.valueOf(2)});
    }

    /* access modifiers changed from: protected */
    public String getTitleForItem(Integer num) {
        int i;
        switch (num.intValue()) {
            case 0:
                i = R.string.goalWeightTxt;
                break;
            case 1:
                i = R.string.currentWeightTxt;
                break;
            case 2:
                i = R.string.activityLevelTxt;
                break;
            case 3:
                i = R.string.weekly_goal;
                break;
            default:
                i = 0;
                break;
        }
        return i > 0 ? getString(i) : "";
    }

    /* access modifiers changed from: protected */
    public String getValueForItem(Integer num) {
        String str = "";
        switch (num.intValue()) {
            case 0:
                return ((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.JUST_WEIGHT, this.newGoalWeight);
            case 1:
                return ((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.JUST_WEIGHT, this.newCurrentWeight);
            case 2:
                return ActivityItem.getActivityLabelFromName(getActivity(), this.newActivityLevel);
            case 3:
                return getStringForWeeklyGoal();
            default:
                return str;
        }
    }

    private String getStringForWeeklyGoal() {
        int i = (((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() > BitmapDescriptorFactory.HUE_RED ? 1 : (((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        if (i == 0) {
            return getString(R.string.maintain_weight);
        }
        return getString(i > 0 ? R.string.goalLosePerWeekTxt : R.string.goalGainPerWeekTxt, ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeightString(), ((UserWeightService) this.userWeightService.get()).getDisplayableLbsAndKgUnitString(((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeightString()));
    }

    @Subscribe
    public void onDialogWeightEvent(DialogWeightEvent dialogWeightEvent) {
        postEvent(new BusyEvent(1, true));
        User user = getSession().getUser();
        UserProfile profile = user.getProfile();
        WeightType weightType = dialogWeightEvent.getWeightType();
        if (weightType == WeightType.CURRENT) {
            this.newCurrentWeight = dialogWeightEvent.getWeight();
            profile.setStartingWeight(new UserWeight(this.newCurrentWeight));
            new UpdateWeightProxy(getActivity(), getNavigationHelper(), getMessageBus()).updateWeightAndPromptForWarnings(this.newCurrentWeight, FinishMode.Back, UpdateMode.WeightAndGoalLoss);
        } else if (weightType == WeightType.GOAL) {
            this.newGoalWeight = dialogWeightEvent.getWeight();
            profile.setGoalWeight(new UserWeight(this.newGoalWeight));
            user.updatePropertyNamed(Basic.GOAL_WEIGHT_IN_POUNDS);
            ((UserWeightService) this.userWeightService.get()).recalculateAndUpdateGoalLossPerWeek();
        }
        recalculateGoals();
    }

    @Subscribe
    public void onActivityLevelDialogEvent(ActivityLevelDialogEvent activityLevelDialogEvent) {
        postEvent(new BusyEvent(1, true));
        this.newActivityLevel = activityLevelDialogEvent.getItem().getName();
        User user = getSession().getUser();
        user.getProfile().setLifestyleName(this.newActivityLevel);
        user.updatePropertyNamed(Basic.LIFESTYLE_NAME);
        recalculateGoals();
    }

    @Subscribe
    public void onWeightLossGoalDialogEvent(WeightLossGoalDialogEvent weightLossGoalDialogEvent) {
        postEvent(new BusyEvent(1, true));
        this.newWeightLossGoal = weightLossGoalDialogEvent.getItem().getValue();
        User user = getSession().getUser();
        user.getUserV1GoalPreferences().setGoalLossPerWeek(this.newWeightLossGoal);
        user.updatePropertyNamed(Goals.GOAL_LOSS_PER_WEEK);
        recalculateGoals();
    }

    @Subscribe
    public void onGoalsRecalculatedEvent(GoalsRecalculatedEvent goalsRecalculatedEvent) {
        new EatingDisorderMaleCalorieGoalAlertUtil(getSession(), getNavigationHelper(), getActivity()).showRaisedMaleCalorieGoalAlertIfNecessary();
        refreshHeaderAndFooter();
        refreshListItems();
    }

    /* access modifiers changed from: protected */
    public void showDialog(int i) {
        String str;
        if (isEnabled()) {
            DialogFragment dialogFragment = null;
            switch (i) {
                case 0:
                    dialogFragment = WeightDialogFragment.newInstance(WeightType.GOAL, BitmapDescriptorFactory.HUE_RED);
                    str = Fragments.GOAL_WEIGHT_DIALOG;
                    break;
                case 1:
                    dialogFragment = WeightDialogFragment.newInstance(WeightType.CURRENT, BitmapDescriptorFactory.HUE_RED);
                    str = Fragments.CURRENT_WEIGHT_DIALOG;
                    break;
                case 2:
                    dialogFragment = ActivityLevelDialogFragment.newInstance();
                    str = Fragments.ACTIVITY_LEVEL_DIALOG;
                    break;
                case 3:
                    dialogFragment = WeightGoalDialogFragment.newInstance();
                    str = Fragments.WEIGHT_GOAL_DIALOG;
                    break;
                default:
                    str = null;
                    break;
            }
            if (dialogFragment != null) {
                dialogFragment.show(getActivity().getSupportFragmentManager(), str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setBusySaving(boolean z) {
        postEvent(new BusyEvent(1, z));
        this.saveButton.setEnabled(!z);
        this.autoRemindersCheckbox.setEnabled(!z);
        this.emailOptInCheckbox.setEnabled(!z);
    }

    @Subscribe
    public void onRegionLookupTaskApiResponseEvent(RegionLookupTask.CompletedEvent completedEvent) {
        this.saveButton.setEnabled(true);
        String str = (String) completedEvent.getResult();
        if (Strings.notEmpty(str)) {
            Ln.d(str, new Object[0]);
            this.isRegionUS = Strings.equals(str, Country.UNITED_STATES_SHORT);
            ViewUtils.setVisible(this.isRegionUS, this.emailOptInCheckbox);
        }
    }
}
