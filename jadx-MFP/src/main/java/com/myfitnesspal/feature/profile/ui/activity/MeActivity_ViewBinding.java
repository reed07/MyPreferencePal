package com.myfitnesspal.feature.profile.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class MeActivity_ViewBinding implements Unbinder {
    private MeActivity target;

    @UiThread
    public MeActivity_ViewBinding(MeActivity meActivity) {
        this(meActivity, meActivity.getWindow().getDecorView());
    }

    @UiThread
    public MeActivity_ViewBinding(MeActivity meActivity, View view) {
        this.target = meActivity;
        meActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
        meActivity.fragmentPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.fragments_pager, "field 'fragmentPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        MeActivity meActivity = this.target;
        if (meActivity != null) {
            this.target = null;
            meActivity.tabLayout = null;
            meActivity.fragmentPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
