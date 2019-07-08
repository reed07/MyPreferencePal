package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.ExerciseCaloriesUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.fragment.ExerciseCaloriesFragment;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class ExerciseCaloriesActivity extends MfpActivity {
    private static final int ACTION_BAR_ACCEPT = 100;
    private static final String EXERCISE_CALORIES_FRAGMENT = "exercise_calories_fragment";
    private MfpDailyGoal currentDailyGoal;
    private ExerciseCaloriesFragment exerciseCaloriesFragment;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutritionalGoalsUtil;
    @Inject
    Lazy<PremiumApiErrorUtil> premiumApiErrorUtil;
    private State state = State.Idle;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private enum State {
        Idle,
        Working
    }

    public String getAnalyticsScreenTag() {
        return Screens.EXERCISE_CALORIES;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ExerciseCaloriesActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setTitle(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.TITLE_ACTIVITY_EXERCISE, this.userEnergyService));
        setContentView((int) R.layout.activity_exercise_calories);
        if (CollectionUtils.notEmpty((Collection<?>) getSupportFragmentManager().getFragments())) {
            this.exerciseCaloriesFragment = (ExerciseCaloriesFragment) getSupportFragmentManager().findFragmentByTag(EXERCISE_CALORIES_FRAGMENT);
        }
        if (bundle == null) {
            getCurrentExerciseCaloriesSettings();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this.state == State.Idle) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
        return true;
    }

    public void onUpPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        save();
        return true;
    }

    private void initFragment() {
        this.exerciseCaloriesFragment = ExerciseCaloriesFragment.newInstance(this.currentDailyGoal);
        getSupportFragmentManager().beginTransaction().replace(R.id.exercise_calories_container, this.exerciseCaloriesFragment, EXERCISE_CALORIES_FRAGMENT).commit();
    }

    private void getCurrentExerciseCaloriesSettings() {
        setState(State.Working);
        ((NutrientGoalService) this.nutrientGoalService.get()).getDailyGoalForDayOfWeek(new Function1() {
            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$getCurrentExerciseCaloriesSettings$0(ExerciseCaloriesActivity.this, (MfpDailyGoal) obj);
            }
        }, new Function1() {
            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$getCurrentExerciseCaloriesSettings$1(ExerciseCaloriesActivity.this, (List) obj);
            }
        }, new Date());
    }

    public static /* synthetic */ void lambda$getCurrentExerciseCaloriesSettings$0(ExerciseCaloriesActivity exerciseCaloriesActivity, MfpDailyGoal mfpDailyGoal) throws RuntimeException {
        exerciseCaloriesActivity.setState(State.Idle);
        exerciseCaloriesActivity.currentDailyGoal = mfpDailyGoal;
        exerciseCaloriesActivity.initFragment();
    }

    public static /* synthetic */ void lambda$getCurrentExerciseCaloriesSettings$1(ExerciseCaloriesActivity exerciseCaloriesActivity, List list) throws RuntimeException {
        exerciseCaloriesActivity.setState(State.Idle);
        exerciseCaloriesActivity.getMessageBus().post(new AlertEvent(exerciseCaloriesActivity.getString(R.string.error_could_not_retrieve_exercise_goal)));
    }

    private void save() {
        String assignExerciseEnergy = this.exerciseCaloriesFragment.getAssignExerciseEnergy();
        ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent = new ExerciseCaloriesUpdatedEvent();
        if (Strings.equals(assignExerciseEnergy, "custom")) {
            ExerciseCaloriesUpdatedEvent customExerciseData = this.exerciseCaloriesFragment.getCustomExerciseData();
            exerciseCaloriesUpdatedEvent.setCarbohydrates(customExerciseData.getCarbohydrates());
            exerciseCaloriesUpdatedEvent.setFat(customExerciseData.getFat());
            exerciseCaloriesUpdatedEvent.setProtein(customExerciseData.getProtein());
        }
        writeToBackend(exerciseCaloriesUpdatedEvent, assignExerciseEnergy);
    }

    private void writeToBackend(ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent, String str) {
        setState(State.Working);
        ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(new Function1(str, exerciseCaloriesUpdatedEvent) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ ExerciseCaloriesUpdatedEvent f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$writeToBackend$4(ExerciseCaloriesActivity.this, this.f$1, this.f$2, (MfpNutrientGoal) obj);
            }
        }, new Function1() {
            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$writeToBackend$5(ExerciseCaloriesActivity.this, (List) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$writeToBackend$4(ExerciseCaloriesActivity exerciseCaloriesActivity, String str, ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent, MfpNutrientGoal mfpNutrientGoal) throws RuntimeException {
        List<MfpDailyGoal> dailyGoals = mfpNutrientGoal.getDailyGoals();
        for (MfpDailyGoal writeToGoal : dailyGoals) {
            exerciseCaloriesActivity.writeToGoal(writeToGoal, str, exerciseCaloriesUpdatedEvent);
        }
        exerciseCaloriesActivity.writeToGoal(mfpNutrientGoal.getDefaultGoal(), str, exerciseCaloriesUpdatedEvent);
        mfpNutrientGoal.setDailyGoals(dailyGoals);
        ((NutrientGoalService) exerciseCaloriesActivity.nutrientGoalService.get()).setNutrientGoalAsync(new Function1(exerciseCaloriesUpdatedEvent) {
            private final /* synthetic */ ExerciseCaloriesUpdatedEvent f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$null$2(ExerciseCaloriesActivity.this, this.f$1, (Boolean) obj);
            }
        }, new Function1() {
            public final void execute(Object obj) {
                ExerciseCaloriesActivity.lambda$null$3(ExerciseCaloriesActivity.this, (List) obj);
            }
        }, mfpNutrientGoal);
    }

    public static /* synthetic */ void lambda$null$2(ExerciseCaloriesActivity exerciseCaloriesActivity, ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent, Boolean bool) throws RuntimeException {
        if (exerciseCaloriesActivity.currentDailyGoal == null && exerciseCaloriesActivity.exerciseCaloriesFragment.getDailyGoal() != null) {
            exerciseCaloriesActivity.currentDailyGoal = exerciseCaloriesActivity.exerciseCaloriesFragment.getDailyGoal();
        }
        exerciseCaloriesActivity.currentDailyGoal.setCarbohydrates((float) exerciseCaloriesUpdatedEvent.getCarbohydrates());
        exerciseCaloriesActivity.currentDailyGoal.setProtein((float) exerciseCaloriesUpdatedEvent.getProtein());
        exerciseCaloriesActivity.currentDailyGoal.setFat((float) exerciseCaloriesUpdatedEvent.getFat());
        exerciseCaloriesActivity.exerciseCaloriesFragment.reportAnalyticsEvents();
        exerciseCaloriesActivity.finish();
    }

    public static /* synthetic */ void lambda$null$3(ExerciseCaloriesActivity exerciseCaloriesActivity, List list) throws RuntimeException {
        ((PremiumApiErrorUtil) exerciseCaloriesActivity.premiumApiErrorUtil.get()).showAlertForApiError(list, exerciseCaloriesActivity.getString(R.string.error_could_not_retrieve_exercise_goal));
        exerciseCaloriesActivity.setState(State.Idle);
    }

    public static /* synthetic */ void lambda$writeToBackend$5(ExerciseCaloriesActivity exerciseCaloriesActivity, List list) throws RuntimeException {
        Ln.e("getNutrientGoal Unsuccessful", new Object[0]);
        exerciseCaloriesActivity.getMessageBus().post(new AlertEvent(exerciseCaloriesActivity.getString(R.string.error_could_not_update_exercise_goal)).asToast());
        exerciseCaloriesActivity.finish();
    }

    private void writeToGoal(MfpDailyGoal mfpDailyGoal, String str, ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent) {
        if (!Strings.equals(str, "off")) {
            if (Strings.equals(str, "nutrient_goal")) {
                exerciseCaloriesUpdatedEvent.setCarbohydrates(Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroCarbohydratesPercentage(mfpDailyGoal.getCarbohydrates(), mfpDailyGoal.getProtein(), mfpDailyGoal.getFat())));
                exerciseCaloriesUpdatedEvent.setFat(Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroFatPercentage(mfpDailyGoal.getCarbohydrates(), mfpDailyGoal.getProtein(), mfpDailyGoal.getFat())));
                exerciseCaloriesUpdatedEvent.setProtein(Math.round(((NutrientGoalsUtil) this.nutritionalGoalsUtil.get()).getMacroProteinPercentage(mfpDailyGoal.getCarbohydrates(), mfpDailyGoal.getProtein(), mfpDailyGoal.getFat())));
            }
            mfpDailyGoal.setExerciseCarbohydratesPercentage(exerciseCaloriesUpdatedEvent.getCarbohydrates());
            mfpDailyGoal.setExerciseFatPercentage(exerciseCaloriesUpdatedEvent.getFat());
            mfpDailyGoal.setExerciseProteinPercentage(exerciseCaloriesUpdatedEvent.getProtein());
            int carbohydrates = (int) (((double) exerciseCaloriesUpdatedEvent.getCarbohydrates()) * 0.3d);
            if (carbohydrates > 15) {
                carbohydrates = 15;
            }
            int fat = (int) (((double) exerciseCaloriesUpdatedEvent.getFat()) * 0.33d);
            if (fat > 10) {
                fat = 10;
            }
            mfpDailyGoal.setExerciseSugarPercentage(carbohydrates);
            mfpDailyGoal.setExerciseSaturatedFatPercentage(fat);
        }
        mfpDailyGoal.setAssignExerciseEnergy(str);
    }

    private void setState(State state2) {
        if (state2 != this.state) {
            this.state = state2;
            setBusy(state2 != State.Idle);
            supportInvalidateOptionsMenu();
        }
    }
}
