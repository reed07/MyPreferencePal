package com.myfitnesspal.feature.recipes.ui.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpPagedEditableActivity_ViewBinding;

public class RecipesAndFoods_ViewBinding extends MfpPagedEditableActivity_ViewBinding {
    private RecipesAndFoods target;

    @UiThread
    public RecipesAndFoods_ViewBinding(RecipesAndFoods recipesAndFoods) {
        this(recipesAndFoods, recipesAndFoods.getWindow().getDecorView());
    }

    @UiThread
    public RecipesAndFoods_ViewBinding(RecipesAndFoods recipesAndFoods, View view) {
        super(recipesAndFoods, view);
        this.target = recipesAndFoods;
        recipesAndFoods.searchText = (EditText) Utils.findRequiredViewAsType(view, R.id.search_text, "field 'searchText'", EditText.class);
    }

    public void unbind() {
        RecipesAndFoods recipesAndFoods = this.target;
        if (recipesAndFoods != null) {
            this.target = null;
            recipesAndFoods.searchText = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
