package com.myfitnesspal.feature.goals.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.ExerciseCaloriesUpdatedEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.CustomExerciseCaloriesActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class ExerciseCaloriesFragment extends MfpFragment {
    private static final String CUSTOM_EXERCISE_DATA = "custom_exercise_data";
    private static final String EXTRA_DAILY_GOAL;
    private static final String TAG = "ExerciseCaloriesFragment";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private ExerciseCaloriesUpdatedEvent customExerciseData;
    private MfpDailyGoal dailyGoal;
    @BindView(2131362802)
    TextView increaseMessage;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @BindView(2131363414)
    RadioButton rbCurrentMacro;
    @BindView(2131363415)
    RadioButton rbCustomMacro;
    @BindView(2131363494)
    View rlCustomExerciseCalories;
    @BindView(2131363763)
    Switch swExerciseCalories;
    @BindView(2131363902)
    TextView tvCustomExercise;
    @BindView(2131363910)
    TextView tvExerciseMacro;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(".daily_goal");
        EXTRA_DAILY_GOAL = sb.toString();
    }

    public static ExerciseCaloriesFragment newInstance(MfpDailyGoal mfpDailyGoal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DAILY_GOAL, mfpDailyGoal);
        ExerciseCaloriesFragment exerciseCaloriesFragment = new ExerciseCaloriesFragment();
        exerciseCaloriesFragment.setArguments(bundle);
        return exerciseCaloriesFragment;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(EXTRA_DAILY_GOAL, this.dailyGoal);
        bundle.putParcelable(CUSTOM_EXERCISE_DATA, this.customExerciseData);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.dailyGoal = (MfpDailyGoal) BundleUtils.getParcelable(bundle, EXTRA_DAILY_GOAL, MfpDailyGoal.class.getClassLoader());
        this.customExerciseData = (ExerciseCaloriesUpdatedEvent) BundleUtils.getParcelable(bundle, CUSTOM_EXERCISE_DATA, ExerciseCaloriesUpdatedEvent.class.getClassLoader());
        if (this.dailyGoal == null) {
            this.dailyGoal = (MfpDailyGoal) BundleUtils.getParcelable(getArguments(), EXTRA_DAILY_GOAL, MfpDailyGoal.class.getClassLoader());
        }
        initViews();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_exercise_calories, viewGroup, false);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 153 && i2 == -1) {
            this.customExerciseData = (ExerciseCaloriesUpdatedEvent) ExtrasUtils.getParcelable(intent, Extras.EXERCISE_CALORIES_UPDATED, ExerciseCaloriesUpdatedEvent.class.getClassLoader());
            ViewUtils.setVisible(true, this.tvCustomExercise);
            this.tvCustomExercise.setText(getCustomExerciseString());
        }
    }

    private void initViews() {
        this.increaseMessage.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.INCREASE_GOAL, this.userEnergyService));
        this.tvExerciseMacro.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.INCREASE_MACRO_NUTRIENT_CALORIES, this.userEnergyService));
        boolean isAssignExerciseEnergyOn = this.dailyGoal.isAssignExerciseEnergyOn();
        if (isAssignExerciseEnergyOn) {
            boolean equals = Strings.equals("nutrient_goal", this.dailyGoal.getAssignExerciseEnergy());
            ViewUtils.setVisible(!equals || this.customExerciseData != null, this.tvCustomExercise);
            if (!equals || this.customExerciseData != null) {
                this.rbCustomMacro.setChecked(true);
                this.tvCustomExercise.setText(getCustomExerciseString());
            } else {
                this.rbCurrentMacro.setChecked(true);
            }
        }
        this.swExerciseCalories.setChecked(isAssignExerciseEnergyOn);
        updateRadioVisibility(isAssignExerciseEnergyOn);
        this.swExerciseCalories.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ExerciseCaloriesFragment.this.updateRadioVisibility(z);
            }
        });
        this.rbCustomMacro.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExerciseCaloriesFragment.this.showEditMacrosByPercentActivity();
            }
        });
    }

    public MfpDailyGoal getDailyGoal() {
        return this.dailyGoal;
    }

    private String getCustomExerciseString() {
        int i;
        int i2;
        int i3;
        ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent = this.customExerciseData;
        if (exerciseCaloriesUpdatedEvent == null) {
            i3 = this.dailyGoal.getExerciseCarbohydratesPercentage();
            i2 = this.dailyGoal.getExerciseProteinPercentage();
            i = this.dailyGoal.getExerciseFatPercentage();
        } else {
            i3 = exerciseCaloriesUpdatedEvent.getCarbohydrates();
            i2 = this.customExerciseData.getProtein();
            i = this.customExerciseData.getFat();
        }
        return getString(R.string.exercise_calories_subtext_format, Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public void showEditMacrosByPercentActivity() {
        if (this.customExerciseData != null) {
            getNavigationHelper().fromFragment(this).withExtra(Extras.EXERCISE_CALORIES_UPDATED, (Parcelable) this.customExerciseData).withIntent(CustomExerciseCaloriesActivity.newStartIntent(getActivity())).startActivity(153);
        } else {
            getNavigationHelper().fromFragment(this).withIntent(CustomExerciseCaloriesActivity.newStartIntent(getActivity())).startActivity(153);
        }
    }

    private ExerciseCaloriesUpdatedEvent getDefaultExerciseData() {
        NutrientGoalsUtil nutrientGoalsUtil2 = (NutrientGoalsUtil) this.nutrientGoalsUtil.get();
        return new ExerciseCaloriesUpdatedEvent(this.dailyGoal.getExerciseCarbohydratesPercentage(), this.dailyGoal.getExerciseProteinPercentage(), this.dailyGoal.getExerciseFatPercentage());
    }

    public ExerciseCaloriesUpdatedEvent getCustomExerciseData() {
        ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent = this.customExerciseData;
        return exerciseCaloriesUpdatedEvent != null ? exerciseCaloriesUpdatedEvent : getDefaultExerciseData();
    }

    public String getAssignExerciseEnergy() {
        if (!this.swExerciseCalories.isChecked()) {
            return "off";
        }
        return this.rbCustomMacro.isChecked() ? "custom" : "nutrient_goal";
    }

    /* access modifiers changed from: private */
    public void updateRadioVisibility(boolean z) {
        ViewUtils.setVisible(z, this.rlCustomExerciseCalories);
    }

    public void reportAnalyticsEvents() {
        HashMap hashMap = new HashMap();
        hashMap.put("setting", this.swExerciseCalories.isChecked() ? "on" : "off");
        if (this.swExerciseCalories.isChecked()) {
            hashMap.put(Attributes.EXERCISE_ALLOCATION, this.rbCustomMacro.isChecked() ? "custom" : "default");
            if (this.rbCustomMacro.isChecked() && !(this.customExerciseData == null && this.dailyGoal == null)) {
                ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent = this.customExerciseData;
                float carbohydrates = exerciseCaloriesUpdatedEvent != null ? (float) exerciseCaloriesUpdatedEvent.getCarbohydrates() : this.dailyGoal.getCarbohydrates();
                ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent2 = this.customExerciseData;
                float protein = exerciseCaloriesUpdatedEvent2 != null ? (float) exerciseCaloriesUpdatedEvent2.getProtein() : this.dailyGoal.getProtein();
                ExerciseCaloriesUpdatedEvent exerciseCaloriesUpdatedEvent3 = this.customExerciseData;
                float fat = exerciseCaloriesUpdatedEvent3 != null ? (float) exerciseCaloriesUpdatedEvent3.getFat() : this.dailyGoal.getFat();
                hashMap.put(Attributes.EXERCISE_CUSTOM_CARB, Strings.toString(Float.valueOf(carbohydrates)));
                hashMap.put(Attributes.EXERCISE_CUSTOM_PROTEIN, Strings.toString(Float.valueOf(protein)));
                hashMap.put(Attributes.EXERCISE_CUSTOM_FAT, Strings.toString(Float.valueOf(fat)));
            }
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ASSIGN_EXERCISE, (Map<String, String>) hashMap);
    }
}
