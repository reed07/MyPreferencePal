package com.myfitnesspal.feature.friends.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FriendsActivity_ViewBinding implements Unbinder {
    private FriendsActivity target;

    @UiThread
    public FriendsActivity_ViewBinding(FriendsActivity friendsActivity) {
        this(friendsActivity, friendsActivity.getWindow().getDecorView());
    }

    @UiThread
    public FriendsActivity_ViewBinding(FriendsActivity friendsActivity, View view) {
        this.target = friendsActivity;
        friendsActivity.tabContainer = (TabLayout) Utils.findRequiredViewAsType(view, R.id.friends_tabs_container, "field 'tabContainer'", TabLayout.class);
        friendsActivity.friendsViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.friends_view_pager, "field 'friendsViewPager'", ViewPager.class);
    }

    @CallSuper
    public void unbind() {
        FriendsActivity friendsActivity = this.target;
        if (friendsActivity != null) {
            this.target = null;
            friendsActivity.tabContainer = null;
            friendsActivity.friendsViewPager = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
