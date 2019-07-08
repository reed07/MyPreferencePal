package com.myfitnesspal.feature.nutrition.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CaloriePieLegend_ViewBinding implements Unbinder {
    private CaloriePieLegend target;

    @UiThread
    public CaloriePieLegend_ViewBinding(CaloriePieLegend caloriePieLegend, View view) {
        this.target = caloriePieLegend;
        caloriePieLegend.legendTable = (TableLayout) Utils.findRequiredViewAsType(view, R.id.legendTable, "field 'legendTable'", TableLayout.class);
        caloriePieLegend.bottomRows = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.bottomRows, "field 'bottomRows'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        CaloriePieLegend caloriePieLegend = this.target;
        if (caloriePieLegend != null) {
            this.target = null;
            caloriePieLegend.legendTable = null;
            caloriePieLegend.bottomRows = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
