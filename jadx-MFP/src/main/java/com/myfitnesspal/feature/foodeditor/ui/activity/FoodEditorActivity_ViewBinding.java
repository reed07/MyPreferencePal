package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FoodEditorActivity_ViewBinding implements Unbinder {
    private FoodEditorActivity target;

    @UiThread
    public FoodEditorActivity_ViewBinding(FoodEditorActivity foodEditorActivity) {
        this(foodEditorActivity, foodEditorActivity.getWindow().getDecorView());
    }

    @UiThread
    public FoodEditorActivity_ViewBinding(FoodEditorActivity foodEditorActivity, View view) {
        this.target = foodEditorActivity;
        foodEditorActivity.viewContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.parent_container, "field 'viewContainer'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        FoodEditorActivity foodEditorActivity = this.target;
        if (foodEditorActivity != null) {
            this.target = null;
            foodEditorActivity.viewContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
