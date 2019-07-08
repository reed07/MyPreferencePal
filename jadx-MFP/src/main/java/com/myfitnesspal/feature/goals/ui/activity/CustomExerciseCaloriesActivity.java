package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.ExerciseCaloriesUpdatedEvent;
import com.myfitnesspal.feature.goals.event.PickerValuesUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByPercentFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByPercentFragment.Mode;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Function1;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class CustomExerciseCaloriesActivity extends MfpActivity {
    private static final int ACTION_BAR_ACCEPT = 100;
    private static final int DEFAULT_CARBS_PERCENT = 55;
    private static final int DEFAULT_FAT_PERCENT = 30;
    private static final int DEFAULT_PROTEIN_PERCENT = 15;
    private static final String FRAGMENT_TAG;
    private static final String TAG = CustomExerciseCaloriesActivity.class.getCanonicalName();
    private EditMacroGoalsByPercentFragment customExerciseCaloriesFragment;
    /* access modifiers changed from: private */
    public ExerciseCaloriesUpdatedEvent customExerciseData;
    private boolean isTotalValid = true;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public String getAnalyticsScreenTag() {
        return Screens.CALORIE_MACRO_GOALS;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(".EDIT_BY_PERCENT_FRAGMENT");
        FRAGMENT_TAG = sb.toString();
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, CustomExerciseCaloriesActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_custom_exercise_calories);
        component().inject(this);
        if (bundle == null) {
            getCurrentExerciseGoals();
        } else {
            this.customExerciseCaloriesFragment = (EditMacroGoalsByPercentFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
    }

    private void getCurrentExerciseGoals() {
        Intent intent = getIntent();
        if (intent.hasExtra(Extras.EXERCISE_CALORIES_UPDATED)) {
            this.customExerciseData = (ExerciseCaloriesUpdatedEvent) intent.getParcelableExtra(Extras.EXERCISE_CALORIES_UPDATED);
        }
        ((NutrientGoalService) this.nutrientGoalService.get()).getNutrientGoal(new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) {
                MfpDailyGoal mfpDailyGoal = (MfpDailyGoal) mfpNutrientGoal.getDailyGoals().get(0);
                float currentEnergy = ((UserEnergyService) CustomExerciseCaloriesActivity.this.userEnergyService.get()).getCurrentEnergy(mfpDailyGoal.getEnergy());
                if (CustomExerciseCaloriesActivity.this.customExerciseData != null) {
                    CustomExerciseCaloriesActivity customExerciseCaloriesActivity = CustomExerciseCaloriesActivity.this;
                    customExerciseCaloriesActivity.setExerciseCalorieValuesAndInit(currentEnergy, customExerciseCaloriesActivity.customExerciseData.getCarbohydrates(), CustomExerciseCaloriesActivity.this.customExerciseData.getProtein(), CustomExerciseCaloriesActivity.this.customExerciseData.getFat());
                    return;
                }
                CustomExerciseCaloriesActivity.this.setExerciseCalorieValuesAndInit(currentEnergy, mfpDailyGoal.getExerciseCarbohydratesPercentage(), mfpDailyGoal.getExerciseProteinPercentage(), mfpDailyGoal.getExerciseFatPercentage());
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void setExerciseCalorieValuesAndInit(float f, int i, int i2, int i3) {
        if (i == 0 && i2 == 0 && i3 == 0) {
            i = 55;
            i2 = 15;
            i3 = 30;
        }
        this.customExerciseCaloriesFragment = EditMacroGoalsByPercentFragment.newInstance(Mode.ByPercent, Extras.EXERCISE_CALORIES_UPDATED, f, (float) i, (float) i2, (float) i3, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.exerciseCaloriesContainer, this.customExerciseCaloriesFragment, FRAGMENT_TAG).commit();
    }

    @Subscribe
    public void onPickerValueUpdated(PickerValuesUpdatedEvent pickerValuesUpdatedEvent) {
        this.isTotalValid = pickerValuesUpdatedEvent.isTotalValid();
        supportInvalidateOptionsMenu();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this.isTotalValid) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp), 2);
        } else {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(R.drawable.ic_check_disabled_white_24dp), 2);
        }
        menu.getItem(0).setEnabled(this.isTotalValid);
        return true;
    }

    public void onUpPressed() {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.customExerciseCaloriesFragment.execute();
        return true;
    }

    @Subscribe
    public void onExerciseCaloriesUpdatedEvent(ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent) {
        Intent intent = new Intent();
        intent.putExtra(Extras.EXERCISE_CALORIES_UPDATED, exerciseCaloriesUpdatedEvent);
        setResult(-1, intent);
        finish();
    }
}
