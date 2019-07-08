package com.myfitnesspal.feature.profile.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MyItemsFragment_ViewBinding implements Unbinder {
    private MyItemsFragment target;

    @UiThread
    public MyItemsFragment_ViewBinding(MyItemsFragment myItemsFragment, View view) {
        this.target = myItemsFragment;
        myItemsFragment.root = Utils.findRequiredView(view, R.id.mainContainer, "field 'root'");
        myItemsFragment.mealsRecipesFoodsCard = Utils.findRequiredView(view, R.id.mealsRecipesFoodsCard, "field 'mealsRecipesFoodsCard'");
        myItemsFragment.mealsRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.meals, "field 'mealsRow'", ViewGroup.class);
        myItemsFragment.recipesRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.recipes, "field 'recipesRow'", ViewGroup.class);
        myItemsFragment.foodsRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.foods, "field 'foodsRow'", ViewGroup.class);
        myItemsFragment.cardioRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.cardio, "field 'cardioRow'", ViewGroup.class);
        myItemsFragment.strengthRow = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.strength, "field 'strengthRow'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        MyItemsFragment myItemsFragment = this.target;
        if (myItemsFragment != null) {
            this.target = null;
            myItemsFragment.root = null;
            myItemsFragment.mealsRecipesFoodsCard = null;
            myItemsFragment.mealsRow = null;
            myItemsFragment.recipesRow = null;
            myItemsFragment.foodsRow = null;
            myItemsFragment.cardioRow = null;
            myItemsFragment.strengthRow = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
