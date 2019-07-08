package com.myfitnesspal.feature.explore.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class PrototypeExploreFragment_ViewBinding implements Unbinder {
    private PrototypeExploreFragment target;

    @UiThread
    public PrototypeExploreFragment_ViewBinding(PrototypeExploreFragment prototypeExploreFragment, View view) {
        this.target = prototypeExploreFragment;
        prototypeExploreFragment.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
        prototypeExploreFragment.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.fragments_pager, "field 'viewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        PrototypeExploreFragment prototypeExploreFragment = this.target;
        if (prototypeExploreFragment != null) {
            this.target = null;
            prototypeExploreFragment.tabLayout = null;
            prototypeExploreFragment.viewPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
