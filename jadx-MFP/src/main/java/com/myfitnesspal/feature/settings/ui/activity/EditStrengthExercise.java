package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.ui.activity.GenericExercise;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class EditStrengthExercise extends GenericExercise {
    public static Intent newStartIntent(Context context, Exercise exercise) {
        return new Intent(context, EditStrengthExercise.class).putExtra("exercise", exercise);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.edit_profile_strength_exercise);
    }

    /* access modifiers changed from: protected */
    public void onSave() {
        String strings = Strings.toString(this.descriptionView.getText());
        if (Strings.isEmpty(strings)) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.alert_exercise_description));
            return;
        }
        try {
            this.description = strings;
            updateCustomExercise();
        } catch (NumberFormatException e) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.enter_numeric_value));
            Ln.e(e);
        }
    }
}
