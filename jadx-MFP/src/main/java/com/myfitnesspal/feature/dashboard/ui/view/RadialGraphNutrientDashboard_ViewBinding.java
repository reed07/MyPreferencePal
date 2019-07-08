package com.myfitnesspal.feature.dashboard.ui.view;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class RadialGraphNutrientDashboard_ViewBinding extends NutrientDashboardBase_ViewBinding {
    private RadialGraphNutrientDashboard target;

    @UiThread
    public RadialGraphNutrientDashboard_ViewBinding(RadialGraphNutrientDashboard radialGraphNutrientDashboard, View view) {
        super(radialGraphNutrientDashboard, view);
        this.target = radialGraphNutrientDashboard;
        radialGraphNutrientDashboard.progress1 = Utils.findRequiredView(view, R.id.progress1, "field 'progress1'");
        radialGraphNutrientDashboard.progress2 = Utils.findRequiredView(view, R.id.progress2, "field 'progress2'");
        radialGraphNutrientDashboard.progress3 = Utils.findRequiredView(view, R.id.progress3, "field 'progress3'");
        radialGraphNutrientDashboard.progress4 = Utils.findRequiredView(view, R.id.progress4, "field 'progress4'");
    }

    public void unbind() {
        RadialGraphNutrientDashboard radialGraphNutrientDashboard = this.target;
        if (radialGraphNutrientDashboard != null) {
            this.target = null;
            radialGraphNutrientDashboard.progress1 = null;
            radialGraphNutrientDashboard.progress2 = null;
            radialGraphNutrientDashboard.progress3 = null;
            radialGraphNutrientDashboard.progress4 = null;
            super.unbind();
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
