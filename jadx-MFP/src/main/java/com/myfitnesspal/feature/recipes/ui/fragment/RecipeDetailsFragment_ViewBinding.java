package com.myfitnesspal.feature.recipes.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer;

public class RecipeDetailsFragment_ViewBinding implements Unbinder {
    private RecipeDetailsFragment target;
    private View view2131362016;

    @UiThread
    public RecipeDetailsFragment_ViewBinding(final RecipeDetailsFragment recipeDetailsFragment, View view) {
        this.target = recipeDetailsFragment;
        recipeDetailsFragment.nameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.name_text, "field 'nameTextView'", TextView.class);
        recipeDetailsFragment.numServingsTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.num_servings, "field 'numServingsTextView'", TextView.class);
        recipeDetailsFragment.sourceTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.source_text, "field 'sourceTextView'", TextView.class);
        recipeDetailsFragment.caloriesPerServingTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.calories_per_serving, "field 'caloriesPerServingTextView'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_footer, "field 'addToDiaryButton' and method 'onAddToDiaryClick'");
        recipeDetailsFragment.addToDiaryButton = (TextView) Utils.castView(findRequiredView, R.id.btn_footer, "field 'addToDiaryButton'", TextView.class);
        this.view2131362016 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                recipeDetailsFragment.onAddToDiaryClick();
            }
        });
        recipeDetailsFragment.ingredientsContainer = (IngredientsContainer) Utils.findRequiredViewAsType(view, R.id.ingredients_container, "field 'ingredientsContainer'", IngredientsContainer.class);
        recipeDetailsFragment.recipeImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.recipe_image, "field 'recipeImageView'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        RecipeDetailsFragment recipeDetailsFragment = this.target;
        if (recipeDetailsFragment != null) {
            this.target = null;
            recipeDetailsFragment.nameTextView = null;
            recipeDetailsFragment.numServingsTextView = null;
            recipeDetailsFragment.sourceTextView = null;
            recipeDetailsFragment.caloriesPerServingTextView = null;
            recipeDetailsFragment.addToDiaryButton = null;
            recipeDetailsFragment.ingredientsContainer = null;
            recipeDetailsFragment.recipeImageView = null;
            this.view2131362016.setOnClickListener(null);
            this.view2131362016 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
