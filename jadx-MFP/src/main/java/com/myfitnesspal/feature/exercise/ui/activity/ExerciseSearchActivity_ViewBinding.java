package com.myfitnesspal.feature.exercise.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ExerciseSearchActivity_ViewBinding implements Unbinder {
    private ExerciseSearchActivity target;

    @UiThread
    public ExerciseSearchActivity_ViewBinding(ExerciseSearchActivity exerciseSearchActivity) {
        this(exerciseSearchActivity, exerciseSearchActivity.getWindow().getDecorView());
    }

    @UiThread
    public ExerciseSearchActivity_ViewBinding(ExerciseSearchActivity exerciseSearchActivity, View view) {
        this.target = exerciseSearchActivity;
        exerciseSearchActivity.contentPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewPager, "field 'contentPager'", ViewPager.class);
        exerciseSearchActivity.newTabContainer = (TabLayout) Utils.findRequiredViewAsType(view, R.id.new_tabs_container, "field 'newTabContainer'", TabLayout.class);
        exerciseSearchActivity.searchEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.editTxtSearchExercise, "field 'searchEditText'", EditText.class);
        exerciseSearchActivity.createNewExerciseButton = (Button) Utils.findRequiredViewAsType(view, R.id.createNewExercise, "field 'createNewExerciseButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        ExerciseSearchActivity exerciseSearchActivity = this.target;
        if (exerciseSearchActivity != null) {
            this.target = null;
            exerciseSearchActivity.contentPager = null;
            exerciseSearchActivity.newTabContainer = null;
            exerciseSearchActivity.searchEditText = null;
            exerciseSearchActivity.createNewExerciseButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
