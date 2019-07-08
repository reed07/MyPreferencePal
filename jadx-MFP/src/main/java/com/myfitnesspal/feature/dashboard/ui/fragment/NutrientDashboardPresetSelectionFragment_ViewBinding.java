package com.myfitnesspal.feature.dashboard.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NutrientDashboardPresetSelectionFragment_ViewBinding implements Unbinder {
    private NutrientDashboardPresetSelectionFragment target;

    @UiThread
    public NutrientDashboardPresetSelectionFragment_ViewBinding(NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment, View view) {
        this.target = nutrientDashboardPresetSelectionFragment;
        nutrientDashboardPresetSelectionFragment.rbMacrosRem = Utils.findRequiredView(view, R.id.rbMacrosRem, "field 'rbMacrosRem'");
        nutrientDashboardPresetSelectionFragment.rbCaloriesRem = Utils.findRequiredView(view, R.id.rbCaloriesRem, "field 'rbCaloriesRem'");
        nutrientDashboardPresetSelectionFragment.rbHeartHealthy = Utils.findRequiredView(view, R.id.rbHeartHealthy, "field 'rbHeartHealthy'");
        nutrientDashboardPresetSelectionFragment.rbLowCarb = Utils.findRequiredView(view, R.id.rbLowCarb, "field 'rbLowCarb'");
        nutrientDashboardPresetSelectionFragment.rbCustomSummary = Utils.findRequiredView(view, R.id.rbCustomSummary, "field 'rbCustomSummary'");
        nutrientDashboardPresetSelectionFragment.displayImportantInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.display_important_info, "field 'displayImportantInfo'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        NutrientDashboardPresetSelectionFragment nutrientDashboardPresetSelectionFragment = this.target;
        if (nutrientDashboardPresetSelectionFragment != null) {
            this.target = null;
            nutrientDashboardPresetSelectionFragment.rbMacrosRem = null;
            nutrientDashboardPresetSelectionFragment.rbCaloriesRem = null;
            nutrientDashboardPresetSelectionFragment.rbHeartHealthy = null;
            nutrientDashboardPresetSelectionFragment.rbLowCarb = null;
            nutrientDashboardPresetSelectionFragment.rbCustomSummary = null;
            nutrientDashboardPresetSelectionFragment.displayImportantInfo = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
