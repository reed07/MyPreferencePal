package com.myfitnesspal.feature.recipes.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MultiLineEditTextHint;

public class CreateRecipeManuallyActivity_ViewBinding implements Unbinder {
    private CreateRecipeManuallyActivity target;

    @UiThread
    public CreateRecipeManuallyActivity_ViewBinding(CreateRecipeManuallyActivity createRecipeManuallyActivity) {
        this(createRecipeManuallyActivity, createRecipeManuallyActivity.getWindow().getDecorView());
    }

    @UiThread
    public CreateRecipeManuallyActivity_ViewBinding(CreateRecipeManuallyActivity createRecipeManuallyActivity, View view) {
        this.target = createRecipeManuallyActivity;
        createRecipeManuallyActivity.nameInputView = (EditText) Utils.findRequiredViewAsType(view, R.id.name_input, "field 'nameInputView'", EditText.class);
        createRecipeManuallyActivity.servingsInputView = (EditText) Utils.findRequiredViewAsType(view, R.id.servings_input, "field 'servingsInputView'", EditText.class);
        createRecipeManuallyActivity.ingredientsInputView = (MultiLineEditTextHint) Utils.findRequiredViewAsType(view, R.id.ingredients_input, "field 'ingredientsInputView'", MultiLineEditTextHint.class);
        createRecipeManuallyActivity.bulkImportSwitch = (CompoundButton) Utils.findRequiredViewAsType(view, R.id.bulk_import_switch, "field 'bulkImportSwitch'", CompoundButton.class);
    }

    @CallSuper
    public void unbind() {
        CreateRecipeManuallyActivity createRecipeManuallyActivity = this.target;
        if (createRecipeManuallyActivity != null) {
            this.target = null;
            createRecipeManuallyActivity.nameInputView = null;
            createRecipeManuallyActivity.servingsInputView = null;
            createRecipeManuallyActivity.ingredientsInputView = null;
            createRecipeManuallyActivity.bulkImportSwitch = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
