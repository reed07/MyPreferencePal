package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.exercise.ui.activity.GenericExercise_ViewBinding;

public class EditCardioExercise_ViewBinding extends GenericExercise_ViewBinding {
    private EditCardioExercise target;

    @UiThread
    public EditCardioExercise_ViewBinding(EditCardioExercise editCardioExercise) {
        this(editCardioExercise, editCardioExercise.getWindow().getDecorView());
    }

    @UiThread
    public EditCardioExercise_ViewBinding(EditCardioExercise editCardioExercise, View view) {
        super(editCardioExercise, view);
        this.target = editCardioExercise;
        editCardioExercise.intervalView = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtExerciseInterval, "field 'intervalView'", EditText.class);
        editCardioExercise.caloriesView = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtCaloriesBurned, "field 'caloriesView'", EditText.class);
        editCardioExercise.caloriesBurnedView = (TextView) Utils.findRequiredViewAsType(view, R.id.caloriesBurned, "field 'caloriesBurnedView'", TextView.class);
    }

    public void unbind() {
        EditCardioExercise editCardioExercise = this.target;
        if (editCardioExercise != null) {
            this.target = null;
            editCardioExercise.intervalView = null;
            editCardioExercise.caloriesView = null;
            editCardioExercise.caloriesBurnedView = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
