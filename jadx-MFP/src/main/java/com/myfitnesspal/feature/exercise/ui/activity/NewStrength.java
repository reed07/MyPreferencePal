package com.myfitnesspal.feature.exercise.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.ui.fragment.StrengthCalorieAlertFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class NewStrength extends GenericExercise {
    @BindView(2131362065)
    TextView caloriesBurned;
    @BindView(2131362066)
    TextView caloriesBurnedFaq;
    @Inject
    Lazy<ExerciseAnalyticsHelper> exerciseAnalyticsHelper;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131362412)
    EditText repetitionsText;
    @BindView(2131362419)
    EditText setsText;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    @BindView(2131362429)
    EditText weightText;

    public static Intent newStartIntent(Context context, boolean z) {
        return new Intent(context, NewStrength.class).putExtra(Extras.ADD_TO_DIARY_AFTER_CREATE, z);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.new_strength_exercise);
        this.exerciseType = 1;
    }

    public void onSetContentView() {
        super.onSetContentView();
        this.descriptionView.requestFocus();
        getImmHelper().showSoftInput(this.descriptionView);
        this.caloriesBurned.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        hookupUIEventListeners();
    }

    private void hookupUIEventListeners() {
        this.weightText.setOnKeyListener(this.addKeyListener);
        this.caloriesBurnedFaq.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewStrength.this.showStrengthCalorieDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showStrengthCalorieDialog() {
        showDialogFragment(StrengthCalorieAlertFragment.getStrengthCalorieAlertFragment(this, getNavigationHelper()), StrengthCalorieAlertFragment.STRENGTH_CALORIE_FRAGMENT);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        getImmHelper().hideSoftInput((View) this.descriptionView);
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        this.description = Strings.trimmed((Object) this.descriptionView.getText());
        this.repetitions = NumberUtils.tryParseInt(Strings.trimmed((Object) this.repetitionsText.getText()));
        this.sets = NumberUtils.tryParseInt(Strings.trimmed((Object) this.setsText.getText()));
        String trimmed = Strings.trimmed((Object) this.weightText.getText());
        this.weight = Strings.notEmpty(trimmed) ? ((UserWeightService) this.userWeightService.get()).getExerciseWeightInPounds(trimmed) : Float.MAX_VALUE;
        if (Strings.isEmpty(this.description)) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_description));
        } else if (this.sets == 0) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_sets));
        } else if (this.repetitions == 0) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_add_exercise_repetitions));
        } else if (this.weight <= BitmapDescriptorFactory.HUE_RED) {
            legacyAlertMixin.showAlertDialog(getString(R.string.exercise_weight_repetition));
        } else {
            if (this.weight == Float.MAX_VALUE) {
                this.weight = BitmapDescriptorFactory.HUE_RED;
            }
            if (this.repetitions <= 0 || this.sets <= 0) {
                legacyAlertMixin.showAlertDialog(getString(R.string.enter_numeric_value));
                return;
            }
            ((ExerciseAnalyticsHelper) this.exerciseAnalyticsHelper.get()).reportNewStrengthExerciseCreated();
            onSaveCompleted();
        }
    }
}
