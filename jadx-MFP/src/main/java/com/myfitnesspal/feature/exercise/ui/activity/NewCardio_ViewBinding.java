package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NewCardio_ViewBinding extends GenericExercise_ViewBinding {
    private NewCardio target;

    @UiThread
    public NewCardio_ViewBinding(NewCardio newCardio) {
        this(newCardio, newCardio.getWindow().getDecorView());
    }

    @UiThread
    public NewCardio_ViewBinding(NewCardio newCardio, View view) {
        super(newCardio, view);
        this.target = newCardio;
        newCardio.interval = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtExerciseInterval, "field 'interval'", EditText.class);
        newCardio.calories = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtCaloriesBurned, "field 'calories'", EditText.class);
        newCardio.startTime = (EditText) Utils.findRequiredViewAsType(view, R.id.txtStartTime, "field 'startTime'", EditText.class);
        newCardio.caloriesLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.caloriesBurned, "field 'caloriesLabel'", TextView.class);
        newCardio.startTimeLayout = Utils.findRequiredView(view, R.id.startTimeLinearLayout, "field 'startTimeLayout'");
    }

    public void unbind() {
        NewCardio newCardio = this.target;
        if (newCardio != null) {
            this.target = null;
            newCardio.interval = null;
            newCardio.calories = null;
            newCardio.startTime = null;
            newCardio.caloriesLabel = null;
            newCardio.startTimeLayout = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
