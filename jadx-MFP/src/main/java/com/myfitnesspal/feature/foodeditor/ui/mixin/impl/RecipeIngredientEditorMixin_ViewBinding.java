package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RecipeIngredientEditorMixin_ViewBinding extends FoodEditorMixinBase_ViewBinding {
    private RecipeIngredientEditorMixin target;

    @UiThread
    public RecipeIngredientEditorMixin_ViewBinding(RecipeIngredientEditorMixin recipeIngredientEditorMixin, View view) {
        super(recipeIngredientEditorMixin, view);
        this.target = recipeIngredientEditorMixin;
        recipeIngredientEditorMixin.replaceIngredientButton = (Button) Utils.findRequiredViewAsType(view, R.id.footer_full_width_button, "field 'replaceIngredientButton'", Button.class);
    }

    public void unbind() {
        RecipeIngredientEditorMixin recipeIngredientEditorMixin = this.target;
        if (recipeIngredientEditorMixin != null) {
            this.target = null;
            recipeIngredientEditorMixin.replaceIngredientButton = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
