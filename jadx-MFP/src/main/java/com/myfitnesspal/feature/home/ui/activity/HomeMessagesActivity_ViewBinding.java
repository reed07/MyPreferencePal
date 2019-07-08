package com.myfitnesspal.feature.home.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class HomeMessagesActivity_ViewBinding implements Unbinder {
    private HomeMessagesActivity target;

    @UiThread
    public HomeMessagesActivity_ViewBinding(HomeMessagesActivity homeMessagesActivity) {
        this(homeMessagesActivity, homeMessagesActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeMessagesActivity_ViewBinding(HomeMessagesActivity homeMessagesActivity, View view) {
        this.target = homeMessagesActivity;
        homeMessagesActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
        homeMessagesActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.fragments_pager, "field 'viewPager'", ViewPager.class);
        homeMessagesActivity.fab = (FloatingActionButton) Utils.findRequiredViewAsType(view, R.id.fab, "field 'fab'", FloatingActionButton.class);
    }

    @CallSuper
    public void unbind() {
        HomeMessagesActivity homeMessagesActivity = this.target;
        if (homeMessagesActivity != null) {
            this.target = null;
            homeMessagesActivity.tabLayout = null;
            homeMessagesActivity.viewPager = null;
            homeMessagesActivity.fab = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
