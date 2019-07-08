package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditStrength_ViewBinding implements Unbinder {
    private EditStrength target;

    @UiThread
    public EditStrength_ViewBinding(EditStrength editStrength) {
        this(editStrength, editStrength.getWindow().getDecorView());
    }

    @UiThread
    public EditStrength_ViewBinding(EditStrength editStrength, View view) {
        this.target = editStrength;
        editStrength.sets = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtSetCount, "field 'sets'", EditText.class);
        editStrength.reps = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtRepetitionsPerSet, "field 'reps'", EditText.class);
        editStrength.weight = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtWeightPerRepetition, "field 'weight'", EditText.class);
        editStrength.caloriesBurned = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_burned, "field 'caloriesBurned'", TextView.class);
        editStrength.caloriesBurnedFaq = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_burned_faq, "field 'caloriesBurnedFaq'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        EditStrength editStrength = this.target;
        if (editStrength != null) {
            this.target = null;
            editStrength.sets = null;
            editStrength.reps = null;
            editStrength.weight = null;
            editStrength.caloriesBurned = null;
            editStrength.caloriesBurnedFaq = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
