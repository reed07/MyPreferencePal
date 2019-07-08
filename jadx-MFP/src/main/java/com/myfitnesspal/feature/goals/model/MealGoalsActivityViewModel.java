package com.myfitnesspal.feature.goals.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.item.DiaryPromoItem;
import com.myfitnesspal.feature.goals.service.FetchNutrientGoalTask;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.service.SaveMealGoalsEnabledTask;
import com.myfitnesspal.feature.goals.service.SaveMealNamesTask;
import com.myfitnesspal.feature.goals.service.SaveNutrientGoalTask;
import com.myfitnesspal.feature.settings.ui.activity.CustomMealNames;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.DateTime;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v2.MealGoal;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MealGoalsActivityViewModel extends RunnerViewModel<Void> {
    public static final String DEFAULT = "default";
    private ActionState actionState = ActionState.Viewing;
    private final AnalyticsService analyticsService;
    private final String caller;
    private final Lazy<DiaryService> diaryService;
    private MealGoalsInputMode inputMode;
    private boolean isFinished;
    private boolean isMealGoalsEnabled;
    private boolean isMealGoalsValid = true;
    private final Lazy<LocalSettingsService> localSettingsService;
    private final List<String> mealNamesList;
    private MfpNutrientGoal nutrientGoal;
    private final Lazy<NutrientGoalService> nutrientGoalService;
    private final Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;
    private final Lazy<UserEnergyService> userEnergyService;
    private final int viewPagerCurrentPosition;
    private boolean wasMealGoalsPresent;

    public enum ActionState {
        Viewing,
        Acting,
        Disabled
    }

    public enum DayOfWeek {
        MONDAY(DateTime.MONDAY),
        TUESDAY(DateTime.TUESDAY),
        WEDNESDAY(DateTime.WEDNESDAY),
        THURSDAY(DateTime.THURSDAY),
        FRIDAY(DateTime.FRIDAY),
        SATURDAY(DateTime.SATURDAY),
        SUNDAY(DateTime.SUNDAY);
        
        private final String weekDayName;

        private DayOfWeek(String str) {
            this.weekDayName = str;
        }

        public String getWeekDayName() {
            return this.weekDayName;
        }

        public static int getIndexForDayName(String str) {
            DayOfWeek[] values;
            for (DayOfWeek dayOfWeek : values()) {
                if (Strings.equalsIgnoreCase(dayOfWeek, str)) {
                    return dayOfWeek.ordinal();
                }
            }
            return MONDAY.ordinal();
        }
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int ACTION_STATE = ViewModelPropertyId.next();
        public static final int INPUT_MODE_CHANGED = ViewModelPropertyId.next();
        public static final int MEAL_GOALS_SAVE_FAILED = ViewModelPropertyId.next();
        public static final int MEAL_GOAL_VALID_STATE = ViewModelPropertyId.next();
        public static final int MEAL_NAMES_DATA_SAVED = ViewModelPropertyId.next();
        public static final int NUTRIENT_GOAL_DATA = ViewModelPropertyId.next();
        public static final int NUTRIENT_GOAL_DATA_SAVED = ViewModelPropertyId.next();
        public static final int SHOULD_FINISH = ViewModelPropertyId.next();
    }

    public MealGoalsActivityViewModel(Runner runner, Lazy<NutrientGoalsUtil> lazy, Lazy<NutrientGoalService> lazy2, Lazy<DiaryService> lazy3, Lazy<UserEnergyService> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<SyncService> lazy6, AnalyticsService analyticsService2, Lazy<Session> lazy7, String str, List<String> list, int i) {
        super(runner);
        this.nutrientGoalsUtil = lazy;
        this.nutrientGoalService = lazy2;
        this.diaryService = lazy3;
        this.userEnergyService = lazy4;
        this.localSettingsService = lazy5;
        this.syncService = lazy6;
        this.analyticsService = analyticsService2;
        this.session = lazy7;
        this.caller = str;
        this.mealNamesList = list;
        this.viewPagerCurrentPosition = i;
        this.inputMode = MealGoalsInputMode.Energy;
        initializeActionState();
        setFlagsPerCaller();
    }

    public void load(Void... voidArr) {
        setState(State.Loading);
        new FetchNutrientGoalTask(this.nutrientGoalService).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), FetchNutrientGoalTask.class)) {
            onFetchTaskCompleted(taskDetails);
        } else if (taskDetails.matches(getRunner(), SaveNutrientGoalTask.class)) {
            onSaveNutrientGoalTaskCompleted(taskDetails);
        } else if (taskDetails.matches(getRunner(), SaveMealNamesTask.class)) {
            onSaveMealNamesTaskCompleted(taskDetails);
        } else if (taskDetails.matches(getRunner(), SaveMealGoalsEnabledTask.class)) {
            onSaveMealGoalsEnabledTask(taskDetails);
        }
    }

    public void setActionState(ActionState actionState2) {
        this.actionState = actionState2;
        notifyPropertyChanged(Property.ACTION_STATE);
    }

    public ActionState getActionState() {
        return this.actionState;
    }

    public MfpNutrientGoal getNutrientGoal() {
        return this.nutrientGoal;
    }

    public boolean isCustomGoalsPresent() {
        return ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).isCustomGoalsPresent(this.nutrientGoal);
    }

    public MfpDailyGoal getDefaultDailyGoal() {
        return ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultDailyGoal(this.nutrientGoal);
    }

    public MfpDailyGoal getDailyGoalForDayOfWeek(DayOfWeek dayOfWeek) {
        return ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDailyGoalForDayOfWeek(this.nutrientGoal, dayOfWeek.getWeekDayName());
    }

    public void setMealGoalsValid(boolean z) {
        this.isMealGoalsValid = z;
        notifyPropertyChanged(Property.MEAL_GOAL_VALID_STATE);
    }

    public boolean isMealGoalsValid() {
        return this.isMealGoalsValid;
    }

    public void persistDailyGoal(MfpDailyGoal mfpDailyGoal) {
        this.nutrientGoal = ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).saveMealGoalsForDailyGoal(this.nutrientGoal, mfpDailyGoal);
    }

    public void validateAndSaveMealGoalsAndFinish() {
        this.isFinished = true;
        validateAndSaveMealGoals();
    }

    public void validateAndSaveMealGoals() {
        new SaveNutrientGoalTask(this.nutrientGoalService, ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).setWeekDailyGoalsToDefaultDailyGoalIfNeeded(this.nutrientGoal)).run(getRunner());
    }

    public boolean isMealGoalsEnabled() {
        return this.isMealGoalsEnabled;
    }

    public void setIsMealGoalsEnabled(boolean z) {
        this.isMealGoalsEnabled = z;
        setActionState(z ? ActionState.Viewing : ActionState.Disabled);
    }

    public void saveStateOfMealGoalEnabledFlagAndFinish() {
        if (isUserMealGoalsEnabled() != this.isMealGoalsEnabled) {
            new SaveMealGoalsEnabledTask((Session) this.session.get(), (DiaryService) this.diaryService.get(), (SyncService) this.syncService.get(), this.isMealGoalsEnabled).run(getRunner());
        } else {
            notifyPropertyChanged(Property.SHOULD_FINISH);
        }
    }

    public MealNames getMealNames() {
        return isMealNamesCaller() ? new MealNames(MealNames.getMealNameListWithOutEmptyNames(this.mealNamesList)) : ((Session) this.session.get()).getUser().getMealNames();
    }

    public boolean isMealNamesCaller() {
        return Strings.equals(this.caller, CustomMealNames.CUSTOM_MEAL_NAMES);
    }

    public boolean wasMealGoalsPresent() {
        return this.wasMealGoalsPresent;
    }

    public boolean isUserInCaloriesUnit() {
        return ((UserEnergyService) this.userEnergyService.get()).isCalories();
    }

    public int getViewPagerCurrentPosition() {
        return this.viewPagerCurrentPosition;
    }

    public void setInputMode(MealGoalsInputMode mealGoalsInputMode) {
        if (this.inputMode != mealGoalsInputMode) {
            this.inputMode = mealGoalsInputMode;
            notifyPropertyChanged(Property.INPUT_MODE_CHANGED);
        }
    }

    public MealGoalsInputMode getInputMode() {
        return this.inputMode;
    }

    public boolean isInEnergyInputMode() {
        return this.inputMode == MealGoalsInputMode.Energy;
    }

    public String getDisplayAttributeUnit() {
        if (this.inputMode != MealGoalsInputMode.Energy) {
            return "percent";
        }
        return ((UserEnergyService) this.userEnergyService.get()).isCalories() ? Attributes.CAL : Attributes.KJ;
    }

    private void initializeActionState() {
        this.isMealGoalsEnabled = isUserMealGoalsEnabled();
        if (((LocalSettingsService) this.localSettingsService.get()).getMealGoalsScreenVisited() || this.isMealGoalsEnabled) {
            setActionState(this.isMealGoalsEnabled ? ActionState.Viewing : ActionState.Disabled);
            return;
        }
        setIsMealGoalsEnabled(true);
        ((LocalSettingsService) this.localSettingsService.get()).setMealGoalsScreenVisited(true);
    }

    private void onFetchTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            return;
        }
        if (mergeCurrentNutrientGoalsWithUpdated((MfpNutrientGoal) taskDetails.getResult())) {
            notifyPropertyChanged(Property.NUTRIENT_GOAL_DATA);
        }
        setState(State.Loaded);
    }

    private void onSaveNutrientGoalTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            notifyPropertyChanged(Property.MEAL_GOALS_SAVE_FAILED);
            return;
        }
        ((DiaryService) this.diaryService.get()).markDiaryDayCacheStale();
        if (isMealNamesCaller()) {
            saveMealNames();
        } else if (this.isFinished) {
            saveStateOfMealGoalEnabledFlagAndFinish();
        } else {
            notifyPropertyChanged(Property.NUTRIENT_GOAL_DATA_SAVED);
        }
    }

    private void onSaveMealNamesTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
            notifyPropertyChanged(Property.MEAL_GOALS_SAVE_FAILED);
        } else if (this.isFinished) {
            saveStateOfMealGoalEnabledFlagAndFinish();
        } else {
            notifyPropertyChanged(Property.MEAL_NAMES_DATA_SAVED);
        }
    }

    private void onSaveMealGoalsEnabledTask(TaskDetails taskDetails) {
        if (!taskDetails.successful()) {
            setError(taskDetails.getFailure());
        }
        this.isMealGoalsEnabled = isUserMealGoalsEnabled();
        reportAnalyticsMealGoalsEnabledToggled(this.isMealGoalsEnabled);
        notifyPropertyChanged(Property.SHOULD_FINISH);
    }

    private boolean mergeCurrentNutrientGoalsWithUpdated(MfpNutrientGoal mfpNutrientGoal) {
        MealNames mealNames = getMealNames();
        MfpNutrientGoal defaultMealGoalsIfNotPresent = setDefaultMealGoalsIfNotPresent(mfpNutrientGoal, mealNames);
        if (this.nutrientGoal == null) {
            this.nutrientGoal = defaultMealGoalsIfNotPresent;
            checkMealGoalsIntegrity(this.nutrientGoal, mealNames);
            return true;
        }
        boolean z = false;
        if (defaultMealGoalsIfNotPresent != null) {
            MfpDailyGoal defaultGoal = defaultMealGoalsIfNotPresent.getDefaultGoal();
            MfpDailyGoal defaultGoal2 = this.nutrientGoal.getDefaultGoal();
            if (defaultGoal.getEnergy().getValue() != defaultGoal2.getEnergy().getValue()) {
                defaultGoal2.setEnergy(defaultGoal.getEnergy());
                z = true;
            }
            Iterator it = defaultMealGoalsIfNotPresent.getDailyGoals().iterator();
            Iterator it2 = this.nutrientGoal.getDailyGoals().iterator();
            while (it.hasNext() && it2.hasNext()) {
                MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) it.next();
                MfpDailyGoal mfpDailyGoal2 = (MfpDailyGoal) it2.next();
                if (mfpDailyGoal.getEnergy().getValue() != mfpDailyGoal2.getEnergy().getValue()) {
                    mfpDailyGoal2.setEnergy(mfpDailyGoal.getEnergy());
                    z = true;
                }
                if (CollectionUtils.isEmpty((Collection<?>) mfpDailyGoal2.getMealGoals())) {
                    mfpDailyGoal2.setMealGoals(mfpDailyGoal.getMealGoals());
                    z = true;
                }
            }
        }
        if (getActionState() != ActionState.Acting && checkMealGoalsIntegrity(this.nutrientGoal, mealNames)) {
            z = true;
        }
        return z;
    }

    private MfpNutrientGoal setDefaultMealGoalsIfNotPresent(MfpNutrientGoal mfpNutrientGoal, MealNames mealNames) {
        if (mfpNutrientGoal != null) {
            MfpDailyGoal defaultGoal = mfpNutrientGoal.getDefaultGoal();
            this.wasMealGoalsPresent = CollectionUtils.notEmpty((Collection<?>) defaultGoal.getMealGoals());
            if (!this.wasMealGoalsPresent) {
                defaultGoal.setMealGoals(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultMealGoals(defaultGoal, mealNames));
            }
            for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
                if (CollectionUtils.isEmpty((Collection<?>) mfpDailyGoal.getMealGoals())) {
                    mfpDailyGoal.setMealGoals(((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultMealGoals(mfpDailyGoal, mealNames));
                }
            }
        }
        return mfpNutrientGoal;
    }

    private boolean checkMealGoalsIntegrity(MfpNutrientGoal mfpNutrientGoal, MealNames mealNames) {
        if (mfpNutrientGoal == null) {
            return false;
        }
        boolean checkMealGoalsForDiaryDay = checkMealGoalsForDiaryDay(mfpNutrientGoal.getDefaultGoal(), mealNames);
        List<MfpDailyGoal> dailyGoals = mfpNutrientGoal.getDailyGoals();
        if (!CollectionUtils.notEmpty((Collection<?>) dailyGoals)) {
            return checkMealGoalsForDiaryDay;
        }
        for (MfpDailyGoal checkMealGoalsForDiaryDay2 : dailyGoals) {
            checkMealGoalsForDiaryDay |= checkMealGoalsForDiaryDay(checkMealGoalsForDiaryDay2, mealNames);
        }
        return checkMealGoalsForDiaryDay;
    }

    private boolean checkMealGoalsForDiaryDay(MfpDailyGoal mfpDailyGoal, MealNames mealNames) {
        int size = mealNames.size();
        if (mfpDailyGoal != null) {
            List mealGoals = mfpDailyGoal.getMealGoals();
            String unit = mfpDailyGoal.getEnergy().getUnit();
            float value = mfpDailyGoal.getEnergy().getValue();
            float mealGoalsEnergySum = ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).mealGoalsEnergySum(mealGoals);
            int size2 = CollectionUtils.size((Collection<?>) mealGoals);
            int i = size - size2;
            if (i == 0) {
                if (NumberUtils.roundToNearestPlace(value, 1.0f) != NumberUtils.roundToNearestPlace(mealGoalsEnergySum, 1.0f)) {
                    ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).normalizeMealGoalsForTotal(mfpDailyGoal, new MfpMeasuredValue(unit, mealGoalsEnergySum), mfpDailyGoal.getEnergy());
                    return true;
                }
            } else if (i < 0) {
                ListIterator listIterator = mealGoals.listIterator();
                while (listIterator.hasNext()) {
                    MealGoal mealGoal = (MealGoal) listIterator.next();
                    String nameForIndex = mealGoal != null ? mealNames.nameForIndex(mealGoal.getMealIndex()) : null;
                    if (Strings.isEmpty(nameForIndex) || Strings.equals(nameForIndex, MealNames.UNKNOWN_MEAL_NAME)) {
                        listIterator.remove();
                    }
                }
                ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).normalizeMealGoalsForTotal(mfpDailyGoal, new MfpMeasuredValue(mfpDailyGoal.getEnergy().getUnit(), mealGoalsEnergySum), mfpDailyGoal.getEnergy());
                return true;
            } else {
                ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).normalizeMealGoalsForTotal(mfpDailyGoal, new MfpMeasuredValue(mfpDailyGoal.getEnergy().getUnit(), mealGoalsEnergySum), mfpDailyGoal.getEnergy());
                LinkedList linkedList = new LinkedList();
                for (int i2 = 0; i2 < size; i2++) {
                    int mealIndexForName = mealNames.mealIndexForName((String) mealNames.getNames().get(i2));
                    if (size2 > i2) {
                        MealGoal mealGoal2 = (MealGoal) mealGoals.get(i2);
                        if (mealGoal2 == null || mealGoal2.getMealIndex() != mealIndexForName) {
                            linkedList.add(new MealGoal(mealIndexForName, new MfpMeasuredValue(unit, BitmapDescriptorFactory.HUE_RED)));
                        } else {
                            linkedList.add(mealGoal2);
                        }
                    } else {
                        linkedList.add(new MealGoal(mealIndexForName, new MfpMeasuredValue(unit, BitmapDescriptorFactory.HUE_RED)));
                    }
                }
                mfpDailyGoal.setMealGoals(linkedList);
                return true;
            }
        }
        return false;
    }

    private void saveMealNames() {
        new SaveMealNamesTask((Session) this.session.get(), getMealNames()).run(getRunner());
    }

    private boolean isUserMealGoalsEnabled() {
        return ((Session) this.session.get()).getUser().isMealGoalsEnabled();
    }

    private void setFlagsPerCaller() {
        if (!Strings.equals(this.caller, DiaryPromoItem.DIARY_MEAL_GOAL_CARD)) {
            ((LocalSettingsService) this.localSettingsService.get()).setShowMealGoalsTip(false);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportAnalyticsMealGoalsEnabledToggled(boolean z) {
        AnalyticsService analyticsService2 = this.analyticsService;
        String str = Events.MEAL_GOALS_TOGGLED;
        String[] strArr = new String[2];
        strArr[0] = "status";
        strArr[1] = z ? "enabled" : "disabled";
        analyticsService2.reportEvent(str, MapUtil.createMap(strArr));
    }
}
