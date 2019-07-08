package com.myfitnesspal.feature.recipes.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.ui.view.IngredientsContainer;

public class CreateOrEditRecipeFragment_ViewBinding implements Unbinder {
    private CreateOrEditRecipeFragment target;
    private View view2131362016;

    @UiThread
    public CreateOrEditRecipeFragment_ViewBinding(final CreateOrEditRecipeFragment createOrEditRecipeFragment, View view) {
        this.target = createOrEditRecipeFragment;
        createOrEditRecipeFragment.nameInputView = (EditText) Utils.findRequiredViewAsType(view, R.id.name_input, "field 'nameInputView'", EditText.class);
        createOrEditRecipeFragment.servingsInputView = (EditText) Utils.findRequiredViewAsType(view, R.id.servings_input, "field 'servingsInputView'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_footer, "field 'saveButton' and method 'onSaveClick'");
        createOrEditRecipeFragment.saveButton = (TextView) Utils.castView(findRequiredView, R.id.btn_footer, "field 'saveButton'", TextView.class);
        this.view2131362016 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createOrEditRecipeFragment.onSaveClick();
            }
        });
        createOrEditRecipeFragment.ingredientsContainer = (IngredientsContainer) Utils.findRequiredViewAsType(view, R.id.ingredients_container, "field 'ingredientsContainer'", IngredientsContainer.class);
        createOrEditRecipeFragment.loadingContainer = Utils.findRequiredView(view, R.id.loading_container, "field 'loadingContainer'");
        createOrEditRecipeFragment.recipeImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.recipe_image, "field 'recipeImageView'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        CreateOrEditRecipeFragment createOrEditRecipeFragment = this.target;
        if (createOrEditRecipeFragment != null) {
            this.target = null;
            createOrEditRecipeFragment.nameInputView = null;
            createOrEditRecipeFragment.servingsInputView = null;
            createOrEditRecipeFragment.saveButton = null;
            createOrEditRecipeFragment.ingredientsContainer = null;
            createOrEditRecipeFragment.loadingContainer = null;
            createOrEditRecipeFragment.recipeImageView = null;
            this.view2131362016.setOnClickListener(null);
            this.view2131362016 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
