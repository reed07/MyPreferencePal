package com.myfitnesspal.feature.profile.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.EmptyStateView;

public class ProfileFragment_ViewBinding implements Unbinder {
    private ProfileFragment target;

    @UiThread
    public ProfileFragment_ViewBinding(ProfileFragment profileFragment, View view) {
        this.target = profileFragment;
        profileFragment.rootView = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.fab_fragment_root, "field 'rootView'", ViewGroup.class);
        profileFragment.newsFeedRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.news_feed, "field 'newsFeedRecyclerView'", RecyclerView.class);
        profileFragment.refreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
        profileFragment.emptyView = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.empty_view, "field 'emptyView'", EmptyStateView.class);
    }

    @CallSuper
    public void unbind() {
        ProfileFragment profileFragment = this.target;
        if (profileFragment != null) {
            this.target = null;
            profileFragment.rootView = null;
            profileFragment.newsFeedRecyclerView = null;
            profileFragment.refreshLayout = null;
            profileFragment.emptyView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
