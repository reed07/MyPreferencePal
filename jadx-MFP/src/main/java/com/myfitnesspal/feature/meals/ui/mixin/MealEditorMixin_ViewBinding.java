package com.myfitnesspal.feature.meals.ui.mixin;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase_ViewBinding;
import com.myfitnesspal.feature.foodeditor.ui.view.FoodEditorHint;

public class MealEditorMixin_ViewBinding extends FoodEditorMixinBase_ViewBinding {
    private MealEditorMixin target;

    @UiThread
    public MealEditorMixin_ViewBinding(MealEditorMixin mealEditorMixin, View view) {
        super(mealEditorMixin, view);
        this.target = mealEditorMixin;
        mealEditorMixin.bottomPadding = Utils.findRequiredView(view, R.id.meal_item_container_bottom_padding, "field 'bottomPadding'");
        mealEditorMixin.permissionsTableRow = Utils.findRequiredView(view, R.id.permissions_table_row, "field 'permissionsTableRow'");
        mealEditorMixin.permissionValueTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.permission_value, "field 'permissionValueTextView'", TextView.class);
        mealEditorMixin.mealShareButton = (ImageButton) Utils.findRequiredViewAsType(view, R.id.meal_share_btn, "field 'mealShareButton'", ImageButton.class);
        mealEditorMixin.mealNotes = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_notes, "field 'mealNotes'", TextView.class);
        mealEditorMixin.mealNotesEmpty = Utils.findRequiredView(view, R.id.meal_notes_empty, "field 'mealNotesEmpty'");
        mealEditorMixin.mealNotesButton = (TextView) Utils.findRequiredViewAsType(view, R.id.meal_notes_button, "field 'mealNotesButton'", TextView.class);
        mealEditorMixin.hintPanel = (FoodEditorHint) Utils.findRequiredViewAsType(view, R.id.food_editor_hint_panel, "field 'hintPanel'", FoodEditorHint.class);
    }

    public void unbind() {
        MealEditorMixin mealEditorMixin = this.target;
        if (mealEditorMixin != null) {
            this.target = null;
            mealEditorMixin.bottomPadding = null;
            mealEditorMixin.permissionsTableRow = null;
            mealEditorMixin.permissionValueTextView = null;
            mealEditorMixin.mealShareButton = null;
            mealEditorMixin.mealNotes = null;
            mealEditorMixin.mealNotesEmpty = null;
            mealEditorMixin.mealNotesButton = null;
            mealEditorMixin.hintPanel = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
