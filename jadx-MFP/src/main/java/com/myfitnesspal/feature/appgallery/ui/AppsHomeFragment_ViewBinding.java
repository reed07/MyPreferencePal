package com.myfitnesspal.feature.appgallery.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class AppsHomeFragment_ViewBinding implements Unbinder {
    private AppsHomeFragment target;

    @UiThread
    public AppsHomeFragment_ViewBinding(AppsHomeFragment appsHomeFragment, View view) {
        this.target = appsHomeFragment;
        appsHomeFragment.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewPager'", ViewPager.class);
        appsHomeFragment.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.indicator, "field 'tabLayout'", TabLayout.class);
    }

    @CallSuper
    public void unbind() {
        AppsHomeFragment appsHomeFragment = this.target;
        if (appsHomeFragment != null) {
            this.target = null;
            appsHomeFragment.viewPager = null;
            appsHomeFragment.tabLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
