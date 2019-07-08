package com.myfitnesspal.feature.recipes.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.EmptyStateView;

public class IngredientMatchingActivity_ViewBinding implements Unbinder {
    private IngredientMatchingActivity target;
    private View view2131362020;

    @UiThread
    public IngredientMatchingActivity_ViewBinding(IngredientMatchingActivity ingredientMatchingActivity) {
        this(ingredientMatchingActivity, ingredientMatchingActivity.getWindow().getDecorView());
    }

    @UiThread
    public IngredientMatchingActivity_ViewBinding(final IngredientMatchingActivity ingredientMatchingActivity, View view) {
        this.target = ingredientMatchingActivity;
        ingredientMatchingActivity.ingredientsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.ingredients, "field 'ingredientsRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_next, "field 'nextButton' and method 'onNextClick'");
        ingredientMatchingActivity.nextButton = findRequiredView;
        this.view2131362020 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                ingredientMatchingActivity.onNextClick();
            }
        });
        ingredientMatchingActivity.emptyStateView = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.empty_state, "field 'emptyStateView'", EmptyStateView.class);
        ingredientMatchingActivity.loadingView = Utils.findRequiredView(view, R.id.loading, "field 'loadingView'");
    }

    @CallSuper
    public void unbind() {
        IngredientMatchingActivity ingredientMatchingActivity = this.target;
        if (ingredientMatchingActivity != null) {
            this.target = null;
            ingredientMatchingActivity.ingredientsRecyclerView = null;
            ingredientMatchingActivity.nextButton = null;
            ingredientMatchingActivity.emptyStateView = null;
            ingredientMatchingActivity.loadingView = null;
            this.view2131362020.setOnClickListener(null);
            this.view2131362020 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
