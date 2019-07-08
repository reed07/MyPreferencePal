package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel.ActionState;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel.DayOfWeek;
import com.myfitnesspal.feature.goals.model.MealGoalsActivityViewModel.Property;
import com.myfitnesspal.feature.goals.model.MealGoalsInputMode;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.adapter.MealGoalsPagerAdapter;
import com.myfitnesspal.feature.settings.ui.activity.CustomMealNames;
import com.myfitnesspal.feature.settings.ui.activity.LegacyDiarySettingsActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.view.CustomSwipeableViewPager;
import com.myfitnesspal.shared.ui.view.CustomTabLayout;
import com.myfitnesspal.shared.ui.view.CustomToggle;
import com.myfitnesspal.shared.ui.view.CustomToggle.OnToggleStateChangeListener;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class MealGoalsActivity extends MfpActivity implements MealGoalStateListener, OnToggleStateChangeListener {
    private static final int MENU_SAVE_MEAL_GOALS = 1010;
    @Inject
    Lazy<DiaryService> diaryService;
    @BindView(2131362469)
    Switch enableMealGoalsSW;
    @BindView(2131362262)
    CustomToggle inputModeToggle;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @BindView(2131363022)
    View mealGoalsMain;
    private MealGoalsPagerAdapter mealGoalsPagerAdapter;
    @BindView(2131364186)
    CustomSwipeableViewPager mealGoalsViewPager;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<Session> session;
    @BindView(2131363618)
    View setDefaultMealGoalsPrompt;
    @Inject
    Lazy<SyncService> syncService;
    @BindView(2131363774)
    CustomTabLayout tabLayoutContainer;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    private MealGoalsActivityViewModel viewModel;

    public String getAnalyticsScreenTag() {
        return Screens.MEAL_GOALS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, MealGoalsActivity.class);
    }

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, MealGoalsActivity.class).putExtra(Extras.CALLER, str);
    }

    public static Intent newStartIntent(Context context, String str, ArrayList<String> arrayList) {
        return newStartIntent(context, str).putStringArrayListExtra("meal_names", arrayList);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        Intent intent = getIntent();
        String string = ExtrasUtils.getString(intent, Extras.CALLER, "unknown");
        ArrayList stringArrayList = ExtrasUtils.getStringArrayList(intent, "meal_names");
        int indexForDayName = DayOfWeek.getIndexForDayName(ExtrasUtils.getString(intent, "day_of_week", DayOfWeek.MONDAY.getWeekDayName()));
        setContentView((int) R.layout.meal_goals);
        initViewModel(string, stringArrayList, indexForDayName);
        setupUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setBusy(true);
        this.viewModel.load(new Void[0]);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.viewModel.getActionState() == ActionState.Acting) {
            MenuItem add = menu.add(0, MENU_SAVE_MEAL_GOALS, 0, this.viewModel.isUserInCaloriesUnit() ? R.string.save_cal_meal_goals : R.string.save_kj_meal_goals);
            MealGoalsActivityViewModel mealGoalsActivityViewModel = this.viewModel;
            MenuItemCompat.setShowAsAction(add.setIcon((mealGoalsActivityViewModel == null || mealGoalsActivityViewModel.isMealGoalsValid()) ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != MENU_SAVE_MEAL_GOALS) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveGoals(false);
        return true;
    }

    public void onUpPressed() {
        onBackOrUpPressed();
    }

    public void onBackPressed() {
        onBackOrUpPressed();
    }

    private void onBackOrUpPressed() {
        setBusy(true);
        if (this.viewModel.isMealNamesCaller() || !this.viewModel.wasMealGoalsPresent()) {
            saveGoals(true);
        } else {
            this.viewModel.saveStateOfMealGoalEnabledFlagAndFinish();
        }
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.NUTRIENT_GOAL_DATA) {
            setupPager();
            setBusy(false);
            reportAnalyticsMealGoalsLoaded();
        } else if (i == Property.MEAL_GOAL_VALID_STATE) {
            invalidateOptionsMenu();
            this.mealGoalsViewPager.setSwipeEnabled(this.viewModel.isMealGoalsValid());
            this.tabLayoutContainer.setTapEnabled(this.viewModel.isMealGoalsValid());
        } else if (i == Property.ACTION_STATE) {
            setUIForActionState();
        } else if (i == Property.NUTRIENT_GOAL_DATA_SAVED) {
            setBusy(false);
            getImmHelper().hideSoftInput();
            this.viewModel.setActionState(ActionState.Viewing);
            reportAnalyticsMealGoalsSaved(true);
        } else if (i == Property.MEAL_NAMES_DATA_SAVED) {
            setBusy(false);
            getImmHelper().hideSoftInput();
            reportMealNamesSavedEvent();
            this.viewModel.setActionState(ActionState.Viewing);
        } else if (i == Property.MEAL_GOALS_SAVE_FAILED) {
            setBusy(false);
            getImmHelper().hideSoftInput();
            showFailedToSaveDialog();
            reportAnalyticsMealGoalsSaved(false);
        } else if (i == Property.INPUT_MODE_CHANGED) {
            MealGoalsPagerAdapter mealGoalsPagerAdapter2 = this.mealGoalsPagerAdapter;
            if (mealGoalsPagerAdapter2 != null) {
                mealGoalsPagerAdapter2.notifyDataSetChanged();
            }
            reportAnalyticsMealGoalsUnitToggled();
        } else if (i == Property.SHOULD_FINISH) {
            setBusy(false);
            getImmHelper().hideSoftInput();
            if (this.viewModel.isMealNamesCaller()) {
                reportMealNamesSavedEvent();
                getNavigationHelper().withIntent(LegacyDiarySettingsActivity.newStartIntent(this)).withClearTopAndSingleTop().finishActivityAfterNavigation().startActivity();
                return;
            }
            finish();
        }
    }

    public void onMealGoalStateChanged(boolean z) {
        this.viewModel.setMealGoalsValid(z);
    }

    public void persistDailyGoal(MfpDailyGoal mfpDailyGoal) {
        this.viewModel.persistDailyGoal(mfpDailyGoal);
    }

    public void onMealGoalFocusChanged() {
        this.viewModel.setActionState(ActionState.Acting);
    }

    public void onToggleStateChanged(boolean z) {
        MealGoalsActivityViewModel mealGoalsActivityViewModel = this.viewModel;
        if (mealGoalsActivityViewModel != null) {
            mealGoalsActivityViewModel.setInputMode(z ? MealGoalsInputMode.Energy : MealGoalsInputMode.Percent);
        }
    }

    private void setupPager() {
        setUIForActionState();
        MealGoalsPagerAdapter mealGoalsPagerAdapter2 = this.mealGoalsPagerAdapter;
        if (mealGoalsPagerAdapter2 == null) {
            this.mealGoalsPagerAdapter = new MealGoalsPagerAdapter(this, getSupportFragmentManager(), this.viewModel);
            this.mealGoalsViewPager.setAdapter(this.mealGoalsPagerAdapter);
            this.mealGoalsViewPager.setCurrentItem(this.viewModel.getViewPagerCurrentPosition());
            CustomTabLayout customTabLayout = this.tabLayoutContainer;
            if (customTabLayout != null) {
                customTabLayout.setupWithViewPager(this.mealGoalsViewPager);
                return;
            }
            return;
        }
        mealGoalsPagerAdapter2.setMealGoalsActivityViewModel(this.viewModel);
        this.mealGoalsPagerAdapter.notifyDataSetChanged();
    }

    private void initViewModel(String str, List<String> list, int i) {
        this.viewModel = (MealGoalsActivityViewModel) getViewModel();
        if (this.viewModel == null) {
            MealGoalsActivityViewModel mealGoalsActivityViewModel = new MealGoalsActivityViewModel(getRunner(), this.nutrientGoalsUtil, this.nutrientGoalService, this.diaryService, this.userEnergyService, this.localSettingsService, this.syncService, getAnalyticsService(), this.session, str, list, i);
            this.viewModel = (MealGoalsActivityViewModel) setViewModel(mealGoalsActivityViewModel);
        }
        if (this.viewModel.isLoaded()) {
            rebindView();
        }
    }

    private void setupUI() {
        this.enableMealGoalsSW.setChecked(this.viewModel.isMealGoalsEnabled());
        this.enableMealGoalsSW.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MealGoalsActivity.this.viewModel.setIsMealGoalsEnabled(z);
            }
        });
        this.inputModeToggle.toggleState(this.viewModel.isInEnergyInputMode());
        this.inputModeToggle.setOnToggleStateChangeListener(this);
        this.inputModeToggle.setLeftToggleText(this.viewModel.isUserInCaloriesUnit() ? R.string.calories : R.string.kilojoules);
        this.inputModeToggle.setRightToggleText(R.string.goals_percent);
    }

    private void setUIForActionState() {
        invalidateOptionsMenu();
        boolean isCustomGoalsPresent = this.viewModel.isCustomGoalsPresent();
        int i = this.viewModel.isUserInCaloriesUnit() ? R.string.meal_goals_cal_title : R.string.meal_goals_kj_title;
        if (this.viewModel.getActionState() == ActionState.Disabled) {
            setTitle(getString(i));
            ViewUtils.setVisible(true, this.enableMealGoalsSW);
            ViewUtils.setVisible(false, this.setDefaultMealGoalsPrompt);
            ViewUtils.setVisible(false, this.inputModeToggle);
            setPagerVisibility(false, isCustomGoalsPresent);
        } else if (this.viewModel.getActionState() == ActionState.Acting) {
            setTitle(getString(R.string.set_meal_goals));
            ViewUtils.setVisible(false, this.enableMealGoalsSW);
            ViewUtils.setVisible(false, this.setDefaultMealGoalsPrompt);
            ViewUtils.setVisible(true, this.inputModeToggle);
            setPagerVisibility(true, isCustomGoalsPresent);
        } else {
            setTitle(getString(i));
            ViewUtils.setVisible(true, this.enableMealGoalsSW);
            ViewUtils.setVisible(!isCustomGoalsPresent, this.setDefaultMealGoalsPrompt);
            ViewUtils.setVisible(false, this.inputModeToggle);
            setPagerVisibility(true, isCustomGoalsPresent);
            this.mealGoalsMain.requestFocus();
        }
    }

    private void setPagerVisibility(boolean z, boolean z2) {
        boolean z3 = z2 && z;
        ViewUtils.setVisible(z3, this.tabLayoutContainer);
        ViewUtils.setVisible(z, this.mealGoalsViewPager);
        this.mealGoalsViewPager.setSwipeEnabled(z3);
        this.tabLayoutContainer.setTapEnabled(z3);
    }

    private void showFailedToSaveDialog() {
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.alert).setMessage((int) R.string.meal_goals_could_not_be_saved).setPositiveButton((int) R.string.dismiss, (OnClickListener) null).show();
    }

    private void reportMealNamesSavedEvent() {
        CustomMealNames.reportMealNamesChangedEvent(this.viewModel.getMealNames().getNames(), getAnalyticsService(), true);
    }

    private void saveGoals(boolean z) {
        MealGoalsActivityViewModel mealGoalsActivityViewModel = this.viewModel;
        if (mealGoalsActivityViewModel != null && mealGoalsActivityViewModel.isMealGoalsValid()) {
            if (z) {
                this.viewModel.validateAndSaveMealGoalsAndFinish();
            } else {
                this.viewModel.validateAndSaveMealGoals();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAnalyticsMealGoalsUnitToggled() {
        getAnalyticsService().reportEvent(Events.MEAL_GOAL_UNIT_TOGGLED, MapUtil.createMap("unit", this.viewModel.getDisplayAttributeUnit()));
    }

    /* access modifiers changed from: 0000 */
    public void reportAnalyticsMealGoalsSaved(boolean z) {
        Map createMap = MapUtil.createMap("unit", this.viewModel.getDisplayAttributeUnit());
        if (z) {
            getAnalyticsService().reportEvent(Events.MEAL_GOALS_SAVED, createMap);
            return;
        }
        getAnalyticsService().reportEvent(Events.MEAL_GOALS_SAVED_FAILED, createMap);
        if (ConnectivityUtil.isOffline().booleanValue()) {
            getAnalyticsService().reportEvent(Events.MEAL_GOALS_OFFLINE);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAnalyticsMealGoalsLoaded() {
        getAnalyticsService().reportEvent(Events.MEAL_GOALS_LOADED, MapUtil.createMap(Attributes.HAS_CUSTOM_GOALS_BY_DAY, Boolean.toString(this.viewModel.isCustomGoalsPresent())));
    }
}
