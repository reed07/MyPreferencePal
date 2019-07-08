package com.myfitnesspal.feature.goals.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MealGoalsFragment_ViewBinding implements Unbinder {
    private MealGoalsFragment target;

    @UiThread
    public MealGoalsFragment_ViewBinding(MealGoalsFragment mealGoalsFragment, View view) {
        this.target = mealGoalsFragment;
        mealGoalsFragment.mealGoalsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.meal_goals_list, "field 'mealGoalsRecyclerView'", RecyclerView.class);
        mealGoalsFragment.totalName = (TextView) Utils.findRequiredViewAsType(view, R.id.total_name, "field 'totalName'", TextView.class);
        mealGoalsFragment.totalValue = (TextView) Utils.findRequiredViewAsType(view, R.id.total_value, "field 'totalValue'", TextView.class);
        mealGoalsFragment.totalPercent = Utils.findRequiredView(view, R.id.meal_goal_total_percent, "field 'totalPercent'");
        mealGoalsFragment.calculationError = (TextView) Utils.findRequiredViewAsType(view, R.id.calculation_error, "field 'calculationError'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        MealGoalsFragment mealGoalsFragment = this.target;
        if (mealGoalsFragment != null) {
            this.target = null;
            mealGoalsFragment.mealGoalsRecyclerView = null;
            mealGoalsFragment.totalName = null;
            mealGoalsFragment.totalValue = null;
            mealGoalsFragment.totalPercent = null;
            mealGoalsFragment.calculationError = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
