package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class GenericExercise_ViewBinding implements Unbinder {
    private GenericExercise target;

    @UiThread
    public GenericExercise_ViewBinding(GenericExercise genericExercise) {
        this(genericExercise, genericExercise.getWindow().getDecorView());
    }

    @UiThread
    public GenericExercise_ViewBinding(GenericExercise genericExercise, View view) {
        this.target = genericExercise;
        genericExercise.descriptionView = (EditText) Utils.findRequiredViewAsType(view, R.id.description, "field 'descriptionView'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        GenericExercise genericExercise = this.target;
        if (genericExercise != null) {
            this.target = null;
            genericExercise.descriptionView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
