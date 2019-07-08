package com.myfitnesspal.feature.search.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.ClearableEditText;

public class FoodSearchActivity_ViewBinding implements Unbinder {
    private FoodSearchActivity target;

    @UiThread
    public FoodSearchActivity_ViewBinding(FoodSearchActivity foodSearchActivity) {
        this(foodSearchActivity, foodSearchActivity.getWindow().getDecorView());
    }

    @UiThread
    public FoodSearchActivity_ViewBinding(FoodSearchActivity foodSearchActivity, View view) {
        this.target = foodSearchActivity;
        foodSearchActivity.foodSearchText = (ClearableEditText) Utils.findRequiredViewAsType(view, R.id.txt_food_search, "field 'foodSearchText'", ClearableEditText.class);
        foodSearchActivity.scanBtn = Utils.findRequiredView(view, R.id.btn_scan, "field 'scanBtn'");
        foodSearchActivity.contentPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.content_pager, "field 'contentPager'", ViewPager.class);
        foodSearchActivity.offlineSearchContainer = Utils.findRequiredView(view, R.id.offline_search_container, "field 'offlineSearchContainer'");
        foodSearchActivity.newTabContainer = (TabLayout) Utils.findRequiredViewAsType(view, R.id.new_tabs_container, "field 'newTabContainer'", TabLayout.class);
        foodSearchActivity.restaurantLoggingBtn = Utils.findRequiredView(view, R.id.btn_rl, "field 'restaurantLoggingBtn'");
    }

    @CallSuper
    public void unbind() {
        FoodSearchActivity foodSearchActivity = this.target;
        if (foodSearchActivity != null) {
            this.target = null;
            foodSearchActivity.foodSearchText = null;
            foodSearchActivity.scanBtn = null;
            foodSearchActivity.contentPager = null;
            foodSearchActivity.offlineSearchContainer = null;
            foodSearchActivity.newTabContainer = null;
            foodSearchActivity.restaurantLoggingBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
