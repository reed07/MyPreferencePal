package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class CustomMacroProgressBar_ViewBinding implements Unbinder {
    private CustomMacroProgressBar target;

    @UiThread
    public CustomMacroProgressBar_ViewBinding(CustomMacroProgressBar customMacroProgressBar) {
        this(customMacroProgressBar, customMacroProgressBar);
    }

    @UiThread
    public CustomMacroProgressBar_ViewBinding(CustomMacroProgressBar customMacroProgressBar, View view) {
        this.target = customMacroProgressBar;
        customMacroProgressBar.macroName = (TextView) Utils.findRequiredViewAsType(view, R.id.macro_name, "field 'macroName'", TextView.class);
        customMacroProgressBar.macroProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.macro_progress, "field 'macroProgress'", ProgressBar.class);
        customMacroProgressBar.goalPercent = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_goal_percent, "field 'goalPercent'", TextView.class);
        customMacroProgressBar.goalValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_goal_value, "field 'goalValue'", TextView.class);
        customMacroProgressBar.premiumLock = Utils.findRequiredView(view, R.id.iv_premium_lock, "field 'premiumLock'");
    }

    @CallSuper
    public void unbind() {
        CustomMacroProgressBar customMacroProgressBar = this.target;
        if (customMacroProgressBar != null) {
            this.target = null;
            customMacroProgressBar.macroName = null;
            customMacroProgressBar.macroProgress = null;
            customMacroProgressBar.goalPercent = null;
            customMacroProgressBar.goalValue = null;
            customMacroProgressBar.premiumLock = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
