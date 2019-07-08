package com.myfitnesspal.feature.dashboard.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CustomNutrientDashboardSelectionFragment_ViewBinding implements Unbinder {
    private CustomNutrientDashboardSelectionFragment target;

    @UiThread
    public CustomNutrientDashboardSelectionFragment_ViewBinding(CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment, View view) {
        this.target = customNutrientDashboardSelectionFragment;
        customNutrientDashboardSelectionFragment.tvNutrientSelected = (TextView) Utils.findRequiredViewAsType(view, R.id.tvNutrientSelected, "field 'tvNutrientSelected'", TextView.class);
        customNutrientDashboardSelectionFragment.nutrientCheckBoxes = Utils.listOf((CheckBox) Utils.findRequiredViewAsType(view, R.id.cbProtein, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbFat, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbSatFat, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbPolyFat, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbMonoFat, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbTransFat, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbCholesterol, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbSodium, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbPotassium, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbCarbs, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbFiber, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbSugar, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbVitaminA, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbVitaminC, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbIron, "field 'nutrientCheckBoxes'", CheckBox.class), (CheckBox) Utils.findRequiredViewAsType(view, R.id.cbCalcium, "field 'nutrientCheckBoxes'", CheckBox.class));
    }

    @CallSuper
    public void unbind() {
        CustomNutrientDashboardSelectionFragment customNutrientDashboardSelectionFragment = this.target;
        if (customNutrientDashboardSelectionFragment != null) {
            this.target = null;
            customNutrientDashboardSelectionFragment.tvNutrientSelected = null;
            customNutrientDashboardSelectionFragment.nutrientCheckBoxes = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
