package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class EditCardio_ViewBinding implements Unbinder {
    private EditCardio target;

    @UiThread
    public EditCardio_ViewBinding(EditCardio editCardio) {
        this(editCardio, editCardio.getWindow().getDecorView());
    }

    @UiThread
    public EditCardio_ViewBinding(EditCardio editCardio, View view) {
        this.target = editCardio;
        editCardio.interval = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtExerciseInterval, "field 'interval'", EditText.class);
        editCardio.calories = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtCaloriesBurned, "field 'calories'", EditText.class);
        editCardio.startTime = (EditText) Utils.findRequiredViewAsType(view, R.id.txtStartTime, "field 'startTime'", EditText.class);
        editCardio.caloriesLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.caloriesBurned, "field 'caloriesLabel'", TextView.class);
        editCardio.startTimeLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.startTimeLinearLayout, "field 'startTimeLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        EditCardio editCardio = this.target;
        if (editCardio != null) {
            this.target = null;
            editCardio.interval = null;
            editCardio.calories = null;
            editCardio.startTime = null;
            editCardio.caloriesLabel = null;
            editCardio.startTimeLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
