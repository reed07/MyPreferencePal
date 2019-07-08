package com.myfitnesspal.feature.goals.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.CustomSwipeableViewPager;
import com.myfitnesspal.shared.ui.view.CustomTabLayout;
import com.myfitnesspal.shared.ui.view.CustomToggle;

public class MealGoalsActivity_ViewBinding implements Unbinder {
    private MealGoalsActivity target;

    @UiThread
    public MealGoalsActivity_ViewBinding(MealGoalsActivity mealGoalsActivity) {
        this(mealGoalsActivity, mealGoalsActivity.getWindow().getDecorView());
    }

    @UiThread
    public MealGoalsActivity_ViewBinding(MealGoalsActivity mealGoalsActivity, View view) {
        this.target = mealGoalsActivity;
        mealGoalsActivity.mealGoalsMain = Utils.findRequiredView(view, R.id.meal_goals_main, "field 'mealGoalsMain'");
        mealGoalsActivity.enableMealGoalsSW = (Switch) Utils.findRequiredViewAsType(view, R.id.enableMealGoalsSwitch, "field 'enableMealGoalsSW'", Switch.class);
        mealGoalsActivity.setDefaultMealGoalsPrompt = Utils.findRequiredView(view, R.id.set_goals_prompt, "field 'setDefaultMealGoalsPrompt'");
        mealGoalsActivity.tabLayoutContainer = (CustomTabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayoutContainer'", CustomTabLayout.class);
        mealGoalsActivity.mealGoalsViewPager = (CustomSwipeableViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'mealGoalsViewPager'", CustomSwipeableViewPager.class);
        mealGoalsActivity.inputModeToggle = (CustomToggle) Utils.findRequiredViewAsType(view, R.id.custom_mode_toggle, "field 'inputModeToggle'", CustomToggle.class);
    }

    @CallSuper
    public void unbind() {
        MealGoalsActivity mealGoalsActivity = this.target;
        if (mealGoalsActivity != null) {
            this.target = null;
            mealGoalsActivity.mealGoalsMain = null;
            mealGoalsActivity.enableMealGoalsSW = null;
            mealGoalsActivity.setDefaultMealGoalsPrompt = null;
            mealGoalsActivity.tabLayoutContainer = null;
            mealGoalsActivity.mealGoalsViewPager = null;
            mealGoalsActivity.inputModeToggle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
