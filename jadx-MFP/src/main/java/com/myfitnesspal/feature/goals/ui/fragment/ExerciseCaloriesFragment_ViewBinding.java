package com.myfitnesspal.feature.goals.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ExerciseCaloriesFragment_ViewBinding implements Unbinder {
    private ExerciseCaloriesFragment target;

    @UiThread
    public ExerciseCaloriesFragment_ViewBinding(ExerciseCaloriesFragment exerciseCaloriesFragment, View view) {
        this.target = exerciseCaloriesFragment;
        exerciseCaloriesFragment.swExerciseCalories = (Switch) Utils.findRequiredViewAsType(view, R.id.swExerciseCalorie, "field 'swExerciseCalories'", Switch.class);
        exerciseCaloriesFragment.rbCustomMacro = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rbCustomMacro, "field 'rbCustomMacro'", RadioButton.class);
        exerciseCaloriesFragment.rbCurrentMacro = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rbCurrentMacro, "field 'rbCurrentMacro'", RadioButton.class);
        exerciseCaloriesFragment.rlCustomExerciseCalories = Utils.findRequiredView(view, R.id.rlCustomExerciseCalories, "field 'rlCustomExerciseCalories'");
        exerciseCaloriesFragment.tvCustomExercise = (TextView) Utils.findRequiredViewAsType(view, R.id.tvCustomExercise, "field 'tvCustomExercise'", TextView.class);
        exerciseCaloriesFragment.increaseMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.increase_message, "field 'increaseMessage'", TextView.class);
        exerciseCaloriesFragment.tvExerciseMacro = (TextView) Utils.findRequiredViewAsType(view, R.id.tvExerciseMacro, "field 'tvExerciseMacro'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ExerciseCaloriesFragment exerciseCaloriesFragment = this.target;
        if (exerciseCaloriesFragment != null) {
            this.target = null;
            exerciseCaloriesFragment.swExerciseCalories = null;
            exerciseCaloriesFragment.rbCustomMacro = null;
            exerciseCaloriesFragment.rbCurrentMacro = null;
            exerciseCaloriesFragment.rlCustomExerciseCalories = null;
            exerciseCaloriesFragment.tvCustomExercise = null;
            exerciseCaloriesFragment.increaseMessage = null;
            exerciseCaloriesFragment.tvExerciseMacro = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
