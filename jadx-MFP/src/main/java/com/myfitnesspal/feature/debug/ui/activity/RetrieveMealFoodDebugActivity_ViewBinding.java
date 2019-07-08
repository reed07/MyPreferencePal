package com.myfitnesspal.feature.debug.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RetrieveMealFoodDebugActivity_ViewBinding implements Unbinder {
    private RetrieveMealFoodDebugActivity target;

    @UiThread
    public RetrieveMealFoodDebugActivity_ViewBinding(RetrieveMealFoodDebugActivity retrieveMealFoodDebugActivity) {
        this(retrieveMealFoodDebugActivity, retrieveMealFoodDebugActivity.getWindow().getDecorView());
    }

    @UiThread
    public RetrieveMealFoodDebugActivity_ViewBinding(RetrieveMealFoodDebugActivity retrieveMealFoodDebugActivity, View view) {
        this.target = retrieveMealFoodDebugActivity;
        retrieveMealFoodDebugActivity.mealFoodsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.meal_foods, "field 'mealFoodsRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        RetrieveMealFoodDebugActivity retrieveMealFoodDebugActivity = this.target;
        if (retrieveMealFoodDebugActivity != null) {
            this.target = null;
            retrieveMealFoodDebugActivity.mealFoodsRecyclerView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
