package com.myfitnesspal.feature.recipes.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RecipeImportReviewActivity_ViewBinding implements Unbinder {
    private RecipeImportReviewActivity target;

    @UiThread
    public RecipeImportReviewActivity_ViewBinding(RecipeImportReviewActivity recipeImportReviewActivity) {
        this(recipeImportReviewActivity, recipeImportReviewActivity.getWindow().getDecorView());
    }

    @UiThread
    public RecipeImportReviewActivity_ViewBinding(RecipeImportReviewActivity recipeImportReviewActivity, View view) {
        this.target = recipeImportReviewActivity;
        recipeImportReviewActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs_container, "field 'tabLayout'", TabLayout.class);
        recipeImportReviewActivity.recipeReviewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.review_container, "field 'recipeReviewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        RecipeImportReviewActivity recipeImportReviewActivity = this.target;
        if (recipeImportReviewActivity != null) {
            this.target = null;
            recipeImportReviewActivity.tabLayout = null;
            recipeImportReviewActivity.recipeReviewPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
