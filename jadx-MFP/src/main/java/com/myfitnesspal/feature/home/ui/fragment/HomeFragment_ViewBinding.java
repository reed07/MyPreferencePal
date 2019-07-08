package com.myfitnesspal.feature.home.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class HomeFragment_ViewBinding implements Unbinder {
    private HomeFragment target;

    @UiThread
    public HomeFragment_ViewBinding(HomeFragment homeFragment, View view) {
        this.target = homeFragment;
        homeFragment.newsFeedRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.news_feed, "field 'newsFeedRecyclerView'", RecyclerView.class);
        homeFragment.refreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        HomeFragment homeFragment = this.target;
        if (homeFragment != null) {
            this.target = null;
            homeFragment.newsFeedRecyclerView = null;
            homeFragment.refreshLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
