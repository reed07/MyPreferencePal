package com.myfitnesspal.feature.goals.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MacroGoalEditorActivity_ViewBinding implements Unbinder {
    private MacroGoalEditorActivity target;

    @UiThread
    public MacroGoalEditorActivity_ViewBinding(MacroGoalEditorActivity macroGoalEditorActivity) {
        this(macroGoalEditorActivity, macroGoalEditorActivity.getWindow().getDecorView());
    }

    @UiThread
    public MacroGoalEditorActivity_ViewBinding(MacroGoalEditorActivity macroGoalEditorActivity, View view) {
        this.target = macroGoalEditorActivity;
        macroGoalEditorActivity.macrosViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.pager, "field 'macrosViewPager'", ViewPager.class);
        macroGoalEditorActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    }

    @CallSuper
    public void unbind() {
        MacroGoalEditorActivity macroGoalEditorActivity = this.target;
        if (macroGoalEditorActivity != null) {
            this.target = null;
            macroGoalEditorActivity.macrosViewPager = null;
            macroGoalEditorActivity.tabLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
