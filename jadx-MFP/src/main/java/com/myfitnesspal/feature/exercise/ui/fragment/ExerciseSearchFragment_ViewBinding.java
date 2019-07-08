package com.myfitnesspal.feature.exercise.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ExerciseSearchFragment_ViewBinding implements Unbinder {
    private ExerciseSearchFragment target;

    @UiThread
    public ExerciseSearchFragment_ViewBinding(ExerciseSearchFragment exerciseSearchFragment, View view) {
        this.target = exerciseSearchFragment;
        exerciseSearchFragment.exerciseRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.exercisesListView, "field 'exerciseRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        ExerciseSearchFragment exerciseSearchFragment = this.target;
        if (exerciseSearchFragment != null) {
            this.target = null;
            exerciseSearchFragment.exerciseRecyclerView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
