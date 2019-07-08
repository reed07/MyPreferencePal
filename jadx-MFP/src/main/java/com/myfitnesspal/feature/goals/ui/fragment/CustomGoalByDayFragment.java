package com.myfitnesspal.feature.goals.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.service.FetchNutrientGoalTask;
import com.myfitnesspal.feature.goals.service.FetchNutrientGoalTask.CompletedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.MacroGoalEditorActivity;
import com.myfitnesspal.feature.goals.ui.adapter.EnergyListItem;
import com.myfitnesspal.feature.goals.ui.adapter.EnergyListItem.Display;
import com.myfitnesspal.feature.goals.ui.adapter.GoalListItem;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment.NetCalorieGoalDialogFragmentListener;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.adapter.CustomListItem;
import com.myfitnesspal.shared.ui.adapter.ListItem;
import com.myfitnesspal.shared.ui.adapter.SimpleSectionedAdapter;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class CustomGoalByDayFragment extends MfpFragment implements NetCalorieGoalDialogFragmentListener {
    private static final int ALL_DAYS_COUNT = 7;
    private static final String CACHED_DEFAULT_GOAL = "cached_default_goal";
    private static final String CURRENT_GOAL = "current_goal";
    private static final Map<Integer, String> DAY_TO_ATTRIBUTE_DAY = new HashMap();
    private static final int DONE = 100;
    private static final int MACRO_NUTRIENT_GOAL_LIST_ITEM = 1;
    private static final String SELECTED_DAYS = "selected_days";
    private static final Map<Integer, Integer> VIEW_ID_TO_DAY = new HashMap();
    private Map<String, String> analyticsData;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private MfpDailyGoal cachedDefaultGoal;
    /* access modifiers changed from: private */
    public float carb;
    private MfpNutrientGoal currentNutrientGoal;
    private boolean dataBoundToUi;
    private CustomListItem dayButtonsListItem;
    private View deleteButtonView;
    /* access modifiers changed from: private */
    public float fat;
    private Set<Integer> initialDayIdList;
    private SimpleSectionedAdapter listAdapter;
    private ListView listView;
    /* access modifiers changed from: private */
    public float localizedEnergyValue;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalUtils;
    private OnClickListener onDeleteClickedListener = new OnClickListener() {
        public void onClick(View view) {
            CustomGoalByDayFragment.this.delete();
        }
    };
    private OnItemClickListener onListViewItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Object itemAtPosition = adapterView.getItemAtPosition(i);
            if (itemAtPosition instanceof EnergyListItem) {
                if (!CustomGoalByDayFragment.this.valuesAreSpecifiedByGram()) {
                    NetEnergyGoalDialogFragment.newInstance(CustomGoalByDayFragment.this.localizedEnergyValue).show(CustomGoalByDayFragment.this.getFragmentManager(), Fragments.NET_ENERGY_DIALOG);
                } else {
                    CustomGoalByDayFragment.this.showNoCalorieAdjustmentInGramsDialog();
                }
            } else if (itemAtPosition instanceof GoalListItem) {
                CustomGoalByDayFragment.this.getNavigationHelper().fromFragment(CustomGoalByDayFragment.this).withIntent(MacroGoalEditorActivity.newStartIntent(CustomGoalByDayFragment.this.getActivity(), CustomGoalByDayFragment.this.localizedEnergyValue, CustomGoalByDayFragment.this.carb, CustomGoalByDayFragment.this.protein, CustomGoalByDayFragment.this.fat)).startActivity(149);
            }
        }
    };
    private float originalLocalizedEnergyValue;
    private MfpNutrientGoal originalNutrientGoal;
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    private boolean processing;
    /* access modifiers changed from: private */
    public float protein;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private static class SaveNutrientGoalTask extends EventedTaskBase<Boolean, Exception> {
        private final Lazy<NutrientGoalService> nutrientGoalService;
        private final MfpNutrientGoal saveModel;
        private final String taskName;

        public static class CompletedEvent extends TaskEventBase<Boolean, Exception> {
            private SaveMode saveMode;

            public CompletedEvent(SaveMode saveMode2) {
                this.saveMode = saveMode2;
            }

            public SaveMode getSaveMode() {
                return this.saveMode;
            }
        }

        public enum SaveMode {
            Update,
            Delete
        }

        public SaveNutrientGoalTask(Lazy<NutrientGoalService> lazy, SaveMode saveMode, MfpNutrientGoal mfpNutrientGoal) {
            super((TaskEventBase) new CompletedEvent(saveMode));
            StringBuilder sb = new StringBuilder();
            sb.append("SaveNutrientGoalTask-");
            sb.append(saveMode);
            this.taskName = sb.toString();
            this.nutrientGoalService = lazy;
            this.saveModel = mfpNutrientGoal;
        }

        /* access modifiers changed from: protected */
        public Boolean exec(Context context) throws Exception {
            ((NutrientGoalService) this.nutrientGoalService.get()).setNutrientGoal(this.saveModel);
            return Boolean.valueOf(true);
        }

        /* access modifiers changed from: protected */
        public String getTaskName() {
            return this.taskName;
        }
    }

    static {
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleMon), Integer.valueOf(2));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleTue), Integer.valueOf(3));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleWed), Integer.valueOf(4));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleThu), Integer.valueOf(5));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleFri), Integer.valueOf(6));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleSat), Integer.valueOf(7));
        VIEW_ID_TO_DAY.put(Integer.valueOf(R.id.toggleSun), Integer.valueOf(1));
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(2), Attributes.MONDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(3), Attributes.TUESDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(4), Attributes.WEDNESDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(5), Attributes.THURSDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(6), Attributes.FRIDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(7), Attributes.SATURDAY);
        DAY_TO_ATTRIBUTE_DAY.put(Integer.valueOf(1), Attributes.SUNDAY);
    }

    public static CustomGoalByDayFragment newInstance(ArrayList<String> arrayList) {
        CustomGoalByDayFragment customGoalByDayFragment = new CustomGoalByDayFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Extras.CUSTOM_DAYS, arrayList);
        customGoalByDayFragment.setArguments(bundle);
        return customGoalByDayFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 149 && i2 == -1) {
            MacroGoalsUpdatedEvent macroGoalsUpdatedEvent = (MacroGoalsUpdatedEvent) intent.getExtras().get(Extras.EVENT_SOURCE);
            if (macroGoalsUpdatedEvent != null) {
                this.localizedEnergyValue = macroGoalsUpdatedEvent.getLocalizedEnergyValue();
                this.carb = macroGoalsUpdatedEvent.getCarbohydrates();
                this.protein = macroGoalsUpdatedEvent.getProtein();
                this.fat = macroGoalsUpdatedEvent.getFat();
                this.analyticsData = (Map) new ApiJsonMapper().tryMapFrom(macroGoalsUpdatedEvent.getAnalyticsEventsJSON(), Map.class);
            }
            repopulateListAdapter();
        }
    }

    private void updateDailyGoalWithNewEnergyGoal(float f) {
        MfpDailyGoal mfpDailyGoal = new MfpDailyGoal();
        mfpDailyGoal.setEnergy(new MfpMeasuredValue(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit(), this.localizedEnergyValue));
        mfpDailyGoal.setCarbohydrates(this.carb);
        mfpDailyGoal.setProtein(this.protein);
        mfpDailyGoal.setFat(this.fat);
        pullStateFromDailyGoal(((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getDailyGoalWithNewEnergyValue(mfpDailyGoal, f));
    }

    public void onCalorieGoalUpdatedDialog(float f) {
        updateDailyGoalWithNewEnergyGoal(f);
        repopulateListAdapter();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (!this.processing) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        validateAndSave();
        return true;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        if (bundle == null) {
            this.initialDayIdList = dayNamesToIndices(BundleUtils.getStringArrayList(getArguments(), Extras.CUSTOM_DAYS));
        } else {
            this.currentNutrientGoal = (MfpNutrientGoal) BundleUtils.getParcelable(bundle, CURRENT_GOAL, MfpNutrientGoal.class.getClassLoader());
            this.cachedDefaultGoal = (MfpDailyGoal) BundleUtils.getParcelable(bundle, CACHED_DEFAULT_GOAL, MfpDailyGoal.class.getClassLoader());
            this.initialDayIdList = new HashSet(BundleUtils.getIntegerArrayList(bundle, SELECTED_DAYS));
        }
        this.dayButtonsListItem = new CustomListItem(getActivity(), R.layout.week_toggle_buttons);
        initialize();
    }

    public void onResume() {
        super.onResume();
        rebindUi();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(CURRENT_GOAL, createSaveModel());
        bundle.putParcelable(CACHED_DEFAULT_GOAL, createCacheDefaultModel());
        bundle.putIntegerArrayList(SELECTED_DAYS, new ArrayList(getSelectedDayIds()));
    }

    private void initialize() {
        if (this.currentNutrientGoal == null) {
            setBusyState(true);
            new FetchNutrientGoalTask(this.nutrientGoalService).run(getRunner());
            return;
        }
        rebindUi();
    }

    private void setBusyState(boolean z) {
        if (z != isBusy()) {
            setBusy(z);
            this.processing = z;
            invalidateOptionsMenu();
        }
    }

    private void pullStateFromDailyGoal(MfpDailyGoal mfpDailyGoal) {
        this.localizedEnergyValue = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy(mfpDailyGoal.getEnergy());
        this.carb = mfpDailyGoal.getCarbohydrates();
        this.protein = mfpDailyGoal.getProtein();
        this.fat = mfpDailyGoal.getFat();
    }

    private void rebindUi() {
        if (this.currentNutrientGoal != null && !this.dataBoundToUi) {
            boolean z = false;
            MfpDailyGoal mfpDailyGoal = null;
            if (CollectionUtils.notEmpty((Collection<?>) this.initialDayIdList)) {
                mfpDailyGoal = findDailyGoalForDay(this.currentNutrientGoal, ((Integer) this.initialDayIdList.iterator().next()).intValue());
                z = true;
            }
            if (mfpDailyGoal == null) {
                mfpDailyGoal = this.cachedDefaultGoal;
                if (mfpDailyGoal == null) {
                    mfpDailyGoal = this.currentNutrientGoal.getDefaultGoal();
                }
            }
            this.originalNutrientGoal = (MfpNutrientGoal) ParcelableUtil.clone(this.currentNutrientGoal, MfpNutrientGoal.CREATOR);
            this.originalLocalizedEnergyValue = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) mfpDailyGoal.getEnergy().getValue());
            pullStateFromDailyGoal(mfpDailyGoal);
            repopulateListAdapter();
            showDeleteButton(z);
            initializeDayButtons();
            this.dataBoundToUi = true;
        }
    }

    /* access modifiers changed from: private */
    public void save() {
        setBusyState(true);
        new SaveNutrientGoalTask(this.nutrientGoalService, SaveMode.Update, createSaveModel()).run(getRunner());
    }

    /* access modifiers changed from: private */
    public void delete() {
        setBusyState(true);
        new SaveNutrientGoalTask(this.nutrientGoalService, SaveMode.Delete, createDeleteModel()).run(getRunner());
    }

    @Subscribe
    public void onNutrientGoalFetched(CompletedEvent completedEvent) {
        setBusyState(false);
        if (!completedEvent.isFrom(getRunner())) {
            return;
        }
        if (completedEvent.successful()) {
            this.currentNutrientGoal = (MfpNutrientGoal) completedEvent.getResult();
            rebindUi();
            return;
        }
        postEvent(new AlertEvent(getResources().getString(R.string.error_could_not_set_macros_by_day)));
    }

    @Subscribe
    public void onSaveTaskCompleted(CompletedEvent completedEvent) {
        boolean z = false;
        setBusyState(false);
        if (completedEvent.getSaveMode() == SaveMode.Delete) {
            z = true;
        }
        if (completedEvent.successful()) {
            reportAnalyticsEvents();
            finishWithResult(z);
            return;
        }
        Exception exc = (Exception) completedEvent.getFailure();
        Ln.e(exc);
        ((PremiumApiErrorUtil) this.premiumApiErrorUtil.get()).showAlertForApiError(exc, getResources().getString(z ? R.string.error_could_not_delete_macros_by_day : R.string.error_could_not_set_macros_by_day));
    }

    private void reportAnalyticsEvents() {
        if (this.analyticsData == null) {
            this.analyticsData = new HashMap();
            populateMacrosData();
        }
        this.analyticsData.put("energy_unit", ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit());
        this.analyticsData.put(Attributes.ENERGY_SAVED, Strings.toString(Integer.valueOf(Math.round(this.localizedEnergyValue))));
        this.analyticsData.put(Attributes.ENERGY_CHANGE, Strings.toString(Integer.valueOf(Math.round(this.localizedEnergyValue - this.originalLocalizedEnergyValue))));
        populateDaysData();
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.DAILY_GOALS_SAVED, this.analyticsData);
    }

    private void populateDaysData() {
        Set selectedDayIds = getSelectedDayIds();
        for (Integer num : DAY_TO_ATTRIBUTE_DAY.keySet()) {
            this.analyticsData.put(DAY_TO_ATTRIBUTE_DAY.get(num), selectedDayIds.contains(num) ? "yes" : "no");
        }
    }

    public void populateMacrosData() {
        this.analyticsData.put(Attributes.MACRO_SETTING_CHANGE, "no");
        this.analyticsData.put(Attributes.MACRO_SETTING_SAVED, ((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams() ? "grams" : "percent");
        this.analyticsData.put(Attributes.PROTEIN_SAVED, Strings.toString(Integer.valueOf(Math.round(this.protein))));
        this.analyticsData.put(Attributes.PROTEIN_CHANGE, "0");
        this.analyticsData.put(Attributes.CARB_SAVED, Strings.toString(Integer.valueOf(Math.round(this.carb))));
        this.analyticsData.put(Attributes.CARB_CHANGE, "0");
        this.analyticsData.put(Attributes.FAT_SAVED, Strings.toString(Integer.valueOf(Math.round(this.fat))));
        this.analyticsData.put(Attributes.FAT_CHANGE, "0");
    }

    private void initializeListView() {
        this.listAdapter = new SimpleSectionedAdapter(getActivity(), new ArrayList());
        addDeleteButton();
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(this.onListViewItemClickListener);
    }

    private void repopulateListAdapter() {
        if (this.listAdapter == null) {
            initializeListView();
        }
        this.listAdapter.clear();
        this.listAdapter.addAll(createListItems());
    }

    /* access modifiers changed from: private */
    public boolean valuesAreSpecifiedByGram() {
        return ((Session) this.session.get()).getUser().getGoalPreferences().isMacroGoalFormatGrams();
    }

    private List<ListItem> createListItems() {
        String carbohydratesForDisplay = ((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getCarbohydratesForDisplay(this.carb);
        String proteinForDisplay = ((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getProteinForDisplay(this.protein);
        String fatForDisplay = ((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getFatForDisplay(this.fat);
        String formatPercent = formatPercent(((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getBase5MacroCarbohydratesPercentage(this.carb, this.protein, this.fat));
        String formatPercent2 = formatPercent(((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getBase5MacroProteinPercentage(this.carb, this.protein, this.fat));
        String formatPercent3 = formatPercent(((NutrientGoalsUtil) this.nutrientGoalUtils.get()).getBase5MacroFatPercentage(this.carb, this.protein, this.fat));
        String string = getString(R.string.gram_abbreviation);
        boolean valuesAreSpecifiedByGram = valuesAreSpecifiedByGram();
        ListItem[] listItemArr = new ListItem[5];
        listItemArr[0] = this.dayButtonsListItem;
        listItemArr[1] = new EnergyListItem(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.CUSTOM_GOAL_ENERGY_TYPE, this.userEnergyService), valuesAreSpecifiedByGram ? getString(R.string.calories_auto_calculated) : null, NumberUtils.localeStringFromInt(Math.round(this.localizedEnergyValue)), valuesAreSpecifiedByGram ? Display.Grams : Display.Percent);
        GoalListItem newInstance = GoalListItem.newInstance(1, getString(R.string.carbohydrates), valuesAreSpecifiedByGram ? carbohydratesForDisplay : formatPercent);
        if (valuesAreSpecifiedByGram) {
            carbohydratesForDisplay = formatPercent;
        }
        listItemArr[2] = newInstance.setSideTitle(carbohydratesForDisplay).setGoalValue(this.carb).setGoalUnitAbbreviation(string);
        GoalListItem newInstance2 = GoalListItem.newInstance(1, getString(R.string.protein), valuesAreSpecifiedByGram ? proteinForDisplay : formatPercent2);
        if (valuesAreSpecifiedByGram) {
            proteinForDisplay = formatPercent2;
        }
        listItemArr[3] = newInstance2.setSideTitle(proteinForDisplay).setGoalValue(this.protein).setGoalUnitAbbreviation(string);
        GoalListItem newInstance3 = GoalListItem.newInstance(1, getString(R.string.fat), valuesAreSpecifiedByGram ? fatForDisplay : formatPercent3);
        if (valuesAreSpecifiedByGram) {
            fatForDisplay = formatPercent3;
        }
        listItemArr[4] = newInstance3.setSideTitle(fatForDisplay).setGoalValue(this.fat).setGoalUnitAbbreviation(string);
        return new ArrayList(Arrays.asList(listItemArr));
    }

    @SuppressLint({"RestrictedApi"})
    private void addDeleteButton() {
        if (this.listView.getFooterViewsCount() <= 0) {
            this.deleteButtonView = getLayoutInflater(null).inflate(R.layout.delete_button, null);
            this.listView.addFooterView(this.deleteButtonView);
            this.listView.setFooterDividersEnabled(false);
            ((Button) ViewUtils.findById(this.deleteButtonView, R.id.deleteBtn)).setOnClickListener(this.onDeleteClickedListener);
        }
    }

    private void showDeleteButton(boolean z) {
        ViewUtils.setVisible(z, this.deleteButtonView);
    }

    private void showConfirmOverwriteDialog(String str) {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.confirm_changes).setMessage((CharSequence) String.format(getString(R.string.replace_previous_custom_goals), new Object[]{str})).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomGoalByDayFragment.this.save();
            }
        }).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    private void showEnergyGoalMustBeDifferentDialog() {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((int) R.string.custom_goal_cant_match_default).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private void showNoDaysSelectedDialog() {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((int) R.string.select_one_or_more_days).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private void showAllDaysSelectedDialog() {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((int) R.string.all_days_selected_custom_goal_alert).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    /* access modifiers changed from: private */
    public void showNoCalorieAdjustmentInGramsDialog() {
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.alert).setMessage((CharSequence) ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ENERGY_BY_GRAM_ERROR, this.userEnergyService)).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private boolean validate() {
        if (CollectionUtils.isEmpty((Collection<?>) getSelectedDayIds())) {
            showNoDaysSelectedDialog();
        } else if (CollectionUtils.size((Collection<?>) getSelectedDayIds()) == 7) {
            showAllDaysSelectedDialog();
        } else if (internalStateEqualsDefaultGoal()) {
            showEnergyGoalMustBeDifferentDialog();
        } else {
            Set conflictingGoalDayIds = getConflictingGoalDayIds();
            if (!CollectionUtils.notEmpty((Collection<?>) conflictingGoalDayIds)) {
                return true;
            }
            showConfirmOverwriteDialog(formatDayListForDialog(conflictingGoalDayIds));
        }
        return false;
    }

    private String formatDayListForDialog(Set<Integer> set) {
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : set) {
            arrayList.add(getActivity().getString(DateTimeUtils.getFormattedDayOFWeek(intValue.intValue())));
        }
        return Strings.join(", ", (Collection<T>) arrayList);
    }

    private void validateAndSave() {
        if (validate()) {
            save();
        }
    }

    private List<ToggleButton> getDayButtons() {
        return ViewUtils.findByType(this.dayButtonsListItem.getCustomView(), ToggleButton.class);
    }

    private void initializeDayButtons() {
        for (ToggleButton toggleButton : getDayButtons()) {
            toggleButton.setChecked(this.initialDayIdList.contains(Integer.valueOf(((Integer) VIEW_ID_TO_DAY.get(Integer.valueOf(toggleButton.getId()))).intValue())));
        }
    }

    private Set<Integer> getRemovedDayIds() {
        HashSet hashSet = new HashSet();
        for (ToggleButton toggleButton : getDayButtons()) {
            int intValue = ((Integer) VIEW_ID_TO_DAY.get(Integer.valueOf(toggleButton.getId()))).intValue();
            if (!toggleButton.isChecked() && this.initialDayIdList.contains(Integer.valueOf(intValue))) {
                hashSet.add(Integer.valueOf(intValue));
            }
        }
        return hashSet;
    }

    private Set<Integer> getSelectedDayIds() {
        HashSet hashSet = new HashSet();
        for (ToggleButton toggleButton : getDayButtons()) {
            if (toggleButton.isChecked()) {
                hashSet.add(VIEW_ID_TO_DAY.get(Integer.valueOf(toggleButton.getId())));
            }
        }
        return hashSet;
    }

    private Set<Integer> getConflictingGoalDayIds() {
        HashSet hashSet = new HashSet();
        Set<Integer> selectedDayIds = getSelectedDayIds();
        selectedDayIds.removeAll(this.initialDayIdList);
        String hashKey = this.currentNutrientGoal.getDefaultGoal().toHashKey();
        for (Integer num : selectedDayIds) {
            if (!Strings.equals(hashKey, findDailyGoalForDay(this.currentNutrientGoal, num.intValue()).toHashKey())) {
                hashSet.add(num);
            }
        }
        return hashSet;
    }

    private MfpNutrientGoal createDeleteModel() {
        MfpNutrientGoal mfpNutrientGoal = (MfpNutrientGoal) ParcelableUtil.clone(this.currentNutrientGoal, MfpNutrientGoal.CREATOR);
        for (Integer intValue : this.initialDayIdList) {
            resetDailyGoalForDay(mfpNutrientGoal, intValue.intValue());
        }
        ((NutrientGoalsUtil) this.nutrientGoalUtils.get()).setWeekDailyGoalsToDefaultDailyGoalIfNeeded(mfpNutrientGoal);
        return mfpNutrientGoal;
    }

    private MfpNutrientGoal createSaveModel() {
        MfpNutrientGoal mfpNutrientGoal = (MfpNutrientGoal) ParcelableUtil.clone(this.currentNutrientGoal, MfpNutrientGoal.CREATOR);
        for (Integer intValue : getRemovedDayIds()) {
            resetDailyGoalForDay(mfpNutrientGoal, intValue.intValue());
        }
        for (Integer intValue2 : getSelectedDayIds()) {
            MfpNutrientGoal mfpNutrientGoal2 = mfpNutrientGoal;
            updateDailyGoalValuesForDay((UserEnergyService) this.userEnergyService.get(), mfpNutrientGoal2, this.originalNutrientGoal, intValue2.intValue(), this.localizedEnergyValue, this.carb, this.protein, this.fat);
        }
        return mfpNutrientGoal;
    }

    private MfpDailyGoal createCacheDefaultModel() {
        MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) ParcelableUtil.clone(this.currentNutrientGoal.getDefaultGoal(), MfpDailyGoal.CREATOR);
        mfpDailyGoal.setEnergy(new MfpMeasuredValue(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit(), this.localizedEnergyValue));
        mfpDailyGoal.setCarbohydrates(this.carb);
        mfpDailyGoal.setProtein(this.protein);
        mfpDailyGoal.setFat(this.fat);
        return mfpDailyGoal;
    }

    private static MfpDailyGoal findDailyGoalForDay(MfpNutrientGoal mfpNutrientGoal, int i) {
        for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
            if (Strings.equals(mfpDailyGoal.getDayOfWeek(), DateTimeUtils.getDayString(Integer.valueOf(i)))) {
                return mfpDailyGoal;
            }
        }
        return null;
    }

    private static int getIndexForGoalDay(MfpNutrientGoal mfpNutrientGoal, int i) {
        String dayString = DateTimeUtils.getDayString(Integer.valueOf(i));
        for (int i2 = 0; i2 < mfpNutrientGoal.getDailyGoals().size(); i2++) {
            if (Strings.equals(((MfpDailyGoal) mfpNutrientGoal.getDailyGoals().get(i2)).getDayOfWeek(), dayString)) {
                return i2;
            }
        }
        throw new IndexOutOfBoundsException("dayId");
    }

    private static void resetDailyGoalForDay(MfpNutrientGoal mfpNutrientGoal, int i) {
        int indexForGoalDay = getIndexForGoalDay(mfpNutrientGoal, i);
        MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
        MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) mfpNutrientGoal.getDailyGoals().get(indexForGoalDay);
        mfpDailyGoal.setEnergy(defaultGoal.getEnergy());
        mfpDailyGoal.setCarbohydrates(defaultGoal.getCarbohydrates());
        mfpDailyGoal.setProtein(defaultGoal.getProtein());
        mfpDailyGoal.setFat(defaultGoal.getFat());
        mfpDailyGoal.setMealGoals(defaultGoal.getMealGoals());
    }

    private void updateDailyGoalValuesForDay(UserEnergyService userEnergyService2, MfpNutrientGoal mfpNutrientGoal, MfpNutrientGoal mfpNutrientGoal2, int i, float f, float f2, float f3, float f4) {
        MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) mfpNutrientGoal.getDailyGoals().get(getIndexForGoalDay(mfpNutrientGoal, i));
        MfpDailyGoal mfpDailyGoal2 = (MfpDailyGoal) mfpNutrientGoal2.getDailyGoals().get(getIndexForGoalDay(mfpNutrientGoal2, i));
        String unit = (mfpDailyGoal2 == null || mfpDailyGoal2.getEnergy() == null) ? "calories" : mfpDailyGoal2.getEnergy().getUnit();
        MfpMeasuredValue mfpMeasuredValue = new MfpMeasuredValue(unit, (float) (userEnergyService2.isCalories() ? LocalizedEnergy.fromCalories((double) f) : LocalizedEnergy.fromKilojoules((double) f)).getValue(Strings.equals(unit, "calories") ? Energy.CALORIES : Energy.KILOJOULES));
        mfpDailyGoal.setEnergy(mfpMeasuredValue);
        mfpDailyGoal.setCarbohydrates(f2);
        mfpDailyGoal.setProtein(f3);
        mfpDailyGoal.setFat(f4);
        ((NutrientGoalsUtil) this.nutrientGoalUtils.get()).normalizeMealGoalsForTotal(mfpDailyGoal, mfpDailyGoal2.getEnergy(), mfpMeasuredValue);
    }

    private MfpMeasuredValue getEnergyValue() {
        return new MfpMeasuredValue(((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit(), this.localizedEnergyValue);
    }

    private MfpMeasuredValue toCalories(MfpMeasuredValue mfpMeasuredValue) {
        return new MfpMeasuredValue("calories", ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, mfpMeasuredValue));
    }

    private boolean internalStateEqualsDefaultGoal() {
        MfpDailyGoal defaultGoal = this.currentNutrientGoal.getDefaultGoal();
        return NumberUtils.areEffectivelyEqual(toCalories(defaultGoal.getEnergy()).getValue(), toCalories(getEnergyValue()).getValue()) && Math.round(defaultGoal.getCarbohydrates()) == Math.round(this.carb) && Math.round(defaultGoal.getProtein()) == Math.round(this.protein) && Math.round(defaultGoal.getFat()) == Math.round(this.fat);
    }

    private String formatPercent(int i) {
        return getActivity().getString(R.string.percent_value, new Object[]{Integer.valueOf(i)});
    }

    private Set<Integer> dayNamesToIndices(List<String> list) {
        HashSet hashSet = new HashSet();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (String dayOfWeekIndex : list) {
                hashSet.add(Integer.valueOf(DateTimeUtils.getDayOfWeekIndex(dayOfWeekIndex)));
            }
        }
        return hashSet;
    }

    private void finishWithResult(boolean z) {
        boolean z2 = this.localizedEnergyValue != this.originalLocalizedEnergyValue;
        FragmentActivity activity = getActivity();
        Intent intent = activity.getIntent();
        intent.putExtra(Extras.ENERGY_CHANGED, z2);
        intent.putExtra(Extras.CUSTOM_GOAL_DELETED, z);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
