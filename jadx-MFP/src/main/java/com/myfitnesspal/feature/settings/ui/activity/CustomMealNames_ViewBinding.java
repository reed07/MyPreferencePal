package com.myfitnesspal.feature.settings.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CustomMealNames_ViewBinding implements Unbinder {
    private CustomMealNames target;

    @UiThread
    public CustomMealNames_ViewBinding(CustomMealNames customMealNames) {
        this(customMealNames, customMealNames.getWindow().getDecorView());
    }

    @UiThread
    public CustomMealNames_ViewBinding(CustomMealNames customMealNames, View view) {
        this.target = customMealNames;
        customMealNames.mealsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.meal_list, "field 'mealsRecyclerView'", RecyclerView.class);
        customMealNames.mealNamesEduHint = (TextView) Utils.findRequiredViewAsType(view, R.id.action_point, "field 'mealNamesEduHint'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CustomMealNames customMealNames = this.target;
        if (customMealNames != null) {
            this.target = null;
            customMealNames.mealsRecyclerView = null;
            customMealNames.mealNamesEduHint = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
