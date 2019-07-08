package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MacroDetails_ViewBinding implements Unbinder {
    private MacroDetails target;

    @UiThread
    public MacroDetails_ViewBinding(MacroDetails macroDetails) {
        this(macroDetails, macroDetails);
    }

    @UiThread
    public MacroDetails_ViewBinding(MacroDetails macroDetails, View view) {
        this.target = macroDetails;
        macroDetails.macroValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_macro_value, "field 'macroValue'", TextView.class);
        macroDetails.macroName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_macro_name, "field 'macroName'", TextView.class);
        macroDetails.macroPercent = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_macro_percent, "field 'macroPercent'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        MacroDetails macroDetails = this.target;
        if (macroDetails != null) {
            this.target = null;
            macroDetails.macroValue = null;
            macroDetails.macroName = null;
            macroDetails.macroPercent = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
