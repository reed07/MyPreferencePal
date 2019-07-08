package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NewStrength_ViewBinding extends GenericExercise_ViewBinding {
    private NewStrength target;

    @UiThread
    public NewStrength_ViewBinding(NewStrength newStrength) {
        this(newStrength, newStrength.getWindow().getDecorView());
    }

    @UiThread
    public NewStrength_ViewBinding(NewStrength newStrength, View view) {
        super(newStrength, view);
        this.target = newStrength;
        newStrength.setsText = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtSetCount, "field 'setsText'", EditText.class);
        newStrength.repetitionsText = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtRepetitionsPerSet, "field 'repetitionsText'", EditText.class);
        newStrength.weightText = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtWeightPerRepetition, "field 'weightText'", EditText.class);
        newStrength.caloriesBurned = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_burned, "field 'caloriesBurned'", TextView.class);
        newStrength.caloriesBurnedFaq = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_burned_faq, "field 'caloriesBurnedFaq'", TextView.class);
    }

    public void unbind() {
        NewStrength newStrength = this.target;
        if (newStrength != null) {
            this.target = null;
            newStrength.setsText = null;
            newStrength.repetitionsText = null;
            newStrength.weightText = null;
            newStrength.caloriesBurned = null;
            newStrength.caloriesBurnedFaq = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
