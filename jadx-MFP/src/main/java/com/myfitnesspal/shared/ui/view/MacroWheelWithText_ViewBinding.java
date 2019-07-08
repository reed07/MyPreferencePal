package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.meals.ui.view.MacroWheel;

public class MacroWheelWithText_ViewBinding implements Unbinder {
    private MacroWheelWithText target;

    @UiThread
    public MacroWheelWithText_ViewBinding(MacroWheelWithText macroWheelWithText) {
        this(macroWheelWithText, macroWheelWithText);
    }

    @UiThread
    public MacroWheelWithText_ViewBinding(MacroWheelWithText macroWheelWithText, View view) {
        this.target = macroWheelWithText;
        macroWheelWithText.macroWheel = (MacroWheel) Utils.findRequiredViewAsType(view, R.id.macroWheel, "field 'macroWheel'", MacroWheel.class);
        macroWheelWithText.energyValue = (TextView) Utils.findRequiredViewAsType(view, R.id.energy_value, "field 'energyValue'", TextView.class);
        macroWheelWithText.energyUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.energy_unit, "field 'energyUnit'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        MacroWheelWithText macroWheelWithText = this.target;
        if (macroWheelWithText != null) {
            this.target = null;
            macroWheelWithText.macroWheel = null;
            macroWheelWithText.energyValue = null;
            macroWheelWithText.energyUnit = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
