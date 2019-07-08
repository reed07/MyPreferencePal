package com.myfitnesspal.feature.foodeditor.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MultiLineEditTextHint;

public class FoodNotesActivity_ViewBinding implements Unbinder {
    private FoodNotesActivity target;

    @UiThread
    public FoodNotesActivity_ViewBinding(FoodNotesActivity foodNotesActivity) {
        this(foodNotesActivity, foodNotesActivity.getWindow().getDecorView());
    }

    @UiThread
    public FoodNotesActivity_ViewBinding(FoodNotesActivity foodNotesActivity, View view) {
        this.target = foodNotesActivity;
        foodNotesActivity.multiLineEditHint = (MultiLineEditTextHint) Utils.findRequiredViewAsType(view, R.id.multiLineEditHint, "field 'multiLineEditHint'", MultiLineEditTextHint.class);
    }

    @CallSuper
    public void unbind() {
        FoodNotesActivity foodNotesActivity = this.target;
        if (foodNotesActivity != null) {
            this.target = null;
            foodNotesActivity.multiLineEditHint = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
