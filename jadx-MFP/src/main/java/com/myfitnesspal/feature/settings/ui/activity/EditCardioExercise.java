package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.ui.activity.GenericExercise;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class EditCardioExercise extends GenericExercise {
    @BindView(2131362059)
    TextView caloriesBurnedView;
    @BindView(2131362395)
    EditText caloriesView;
    @BindView(2131362400)
    EditText intervalView;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static Intent newStartIntent(Context context, Exercise exercise) {
        return new Intent(context, EditCardioExercise.class).putExtra("exercise", exercise);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.edit_profile_cardio_exercise);
    }

    public void onSetContentView() {
        super.onSetContentView();
        loadInfo();
        addEventListeners();
    }

    private void loadInfo() {
        int cardioCaloriesBurnedForHours = this.exerciseToEdit.cardioCaloriesBurnedForHours(1.0f, getSession());
        this.intervalView.setText(String.valueOf(60));
        EditText editText = this.intervalView;
        editText.setSelection(editText.getText().length());
        Energy userCurrentEnergyUnit = ((UserEnergyService) this.userEnergyService.get()).getUserCurrentEnergyUnit();
        this.caloriesBurnedView.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED, this.userEnergyService.get()));
        this.caloriesView.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(userCurrentEnergyUnit, (double) cardioCaloriesBurnedForHours));
        EditText editText2 = this.caloriesView;
        editText2.setSelection(editText2.getText().length());
    }

    private void addEventListeners() {
        this.descriptionView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditCardioExercise.this.descriptionView.setSelection(EditCardioExercise.this.descriptionView.getText().length());
                }
            }
        });
        this.intervalView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditCardioExercise.this.intervalView.setSelection(EditCardioExercise.this.intervalView.getText().length());
                }
            }
        });
        this.caloriesView.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    EditCardioExercise.this.caloriesView.setSelection(EditCardioExercise.this.caloriesView.getText().length());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        this.caloriesBurned = ((UserEnergyService) this.userEnergyService.get()).getCalories(Strings.trimmed((Object) this.caloriesView.getText()));
        this.duration = NumberUtils.tryParseInt(Strings.trimmed((Object) this.intervalView.getText()));
        this.description = Strings.trimmed((Object) this.descriptionView.getText());
        LegacyAlertMixin legacyAlertMixin = (LegacyAlertMixin) mixin(LegacyAlertMixin.class);
        if (Strings.isEmpty(this.description)) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_description));
        } else if (this.duration == 0) {
            legacyAlertMixin.showAlertDialog(getString(R.string.alert_exercise_interval));
        } else if (this.caloriesBurned == BitmapDescriptorFactory.HUE_RED) {
            legacyAlertMixin.showAlertDialog(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ALERT_EXERCISE, this.userEnergyService.get()));
        } else {
            updateCustomExercise();
        }
    }
}
