package com.myfitnesspal.feature.foodeditor.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FoodEditorHint_ViewBinding implements Unbinder {
    private FoodEditorHint target;

    @UiThread
    public FoodEditorHint_ViewBinding(FoodEditorHint foodEditorHint) {
        this(foodEditorHint, foodEditorHint);
    }

    @UiThread
    public FoodEditorHint_ViewBinding(FoodEditorHint foodEditorHint, View view) {
        this.target = foodEditorHint;
        foodEditorHint.close = Utils.findRequiredView(view, R.id.food_editor_hint_close, "field 'close'");
        foodEditorHint.title = (TextView) Utils.findRequiredViewAsType(view, R.id.food_editor_hint_title, "field 'title'", TextView.class);
        foodEditorHint.message = (TextView) Utils.findRequiredViewAsType(view, R.id.food_editor_hint_message, "field 'message'", TextView.class);
        foodEditorHint.button = (TextView) Utils.findRequiredViewAsType(view, R.id.food_editor_hint_button, "field 'button'", TextView.class);
        foodEditorHint.container = Utils.findRequiredView(view, R.id.food_editor_hint_container, "field 'container'");
    }

    @CallSuper
    public void unbind() {
        FoodEditorHint foodEditorHint = this.target;
        if (foodEditorHint != null) {
            this.target = null;
            foodEditorHint.close = null;
            foodEditorHint.title = null;
            foodEditorHint.message = null;
            foodEditorHint.button = null;
            foodEditorHint.container = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
