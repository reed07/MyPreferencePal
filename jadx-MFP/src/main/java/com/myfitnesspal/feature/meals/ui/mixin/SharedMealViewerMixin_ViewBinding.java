package com.myfitnesspal.feature.meals.ui.mixin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class SharedMealViewerMixin_ViewBinding implements Unbinder {
    private SharedMealViewerMixin target;

    @UiThread
    public SharedMealViewerMixin_ViewBinding(SharedMealViewerMixin sharedMealViewerMixin, View view) {
        this.target = sharedMealViewerMixin;
        sharedMealViewerMixin.saveButton = (Button) Utils.findRequiredViewAsType(view, R.id.footer_full_width_button, "field 'saveButton'", Button.class);
        sharedMealViewerMixin.foodDescRowView = Utils.findRequiredView(view, R.id.food_desc_container, "field 'foodDescRowView'");
        sharedMealViewerMixin.noOfServingsRowView = Utils.findRequiredView(view, R.id.noOfServingsTableRow, "field 'noOfServingsRowView'");
        sharedMealViewerMixin.servingSizeRowView = Utils.findRequiredView(view, R.id.servingSizeTableRow, "field 'servingSizeRowView'");
        sharedMealViewerMixin.foodDescriptionTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.txtFoodDescription, "field 'foodDescriptionTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SharedMealViewerMixin sharedMealViewerMixin = this.target;
        if (sharedMealViewerMixin != null) {
            this.target = null;
            sharedMealViewerMixin.saveButton = null;
            sharedMealViewerMixin.foodDescRowView = null;
            sharedMealViewerMixin.noOfServingsRowView = null;
            sharedMealViewerMixin.servingSizeRowView = null;
            sharedMealViewerMixin.foodDescriptionTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
