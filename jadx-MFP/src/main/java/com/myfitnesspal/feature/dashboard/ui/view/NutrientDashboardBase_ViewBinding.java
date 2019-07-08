package com.myfitnesspal.feature.dashboard.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class NutrientDashboardBase_ViewBinding implements Unbinder {
    private NutrientDashboardBase target;

    @UiThread
    public NutrientDashboardBase_ViewBinding(NutrientDashboardBase nutrientDashboardBase, View view) {
        this.target = nutrientDashboardBase;
        nutrientDashboardBase.stepsProgressBar = (ProgressBar) Utils.findOptionalViewAsType(view, R.id.steps_progress_bar, "field 'stepsProgressBar'", ProgressBar.class);
        nutrientDashboardBase.tvStepCount = (TextView) Utils.findOptionalViewAsType(view, R.id.tv_step_count, "field 'tvStepCount'", TextView.class);
        nutrientDashboardBase.tvPartnerName = (TextView) Utils.findOptionalViewAsType(view, R.id.tv_partner_label, "field 'tvPartnerName'", TextView.class);
        nutrientDashboardBase.tvStepGoal = (TextView) Utils.findOptionalViewAsType(view, R.id.tv_step_goal, "field 'tvStepGoal'", TextView.class);
        nutrientDashboardBase.homeStepsContainer = view.findViewById(R.id.home_steps_container);
        nutrientDashboardBase.titleRow = view.findViewById(R.id.dashboardTitle);
        nutrientDashboardBase.dropdownButton = view.findViewById(R.id.dashboardDropdownButton);
        nutrientDashboardBase.diaryShortcut = view.findViewById(R.id.diary_shortcut);
        nutrientDashboardBase.title = (TextView) Utils.findOptionalViewAsType(view, R.id.title, "field 'title'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        NutrientDashboardBase nutrientDashboardBase = this.target;
        if (nutrientDashboardBase != null) {
            this.target = null;
            nutrientDashboardBase.stepsProgressBar = null;
            nutrientDashboardBase.tvStepCount = null;
            nutrientDashboardBase.tvPartnerName = null;
            nutrientDashboardBase.tvStepGoal = null;
            nutrientDashboardBase.homeStepsContainer = null;
            nutrientDashboardBase.titleRow = null;
            nutrientDashboardBase.dropdownButton = null;
            nutrientDashboardBase.diaryShortcut = null;
            nutrientDashboardBase.title = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
