package com.myfitnesspal.feature.friends.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FriendRequestsFragment_ViewBinding implements Unbinder {
    private FriendRequestsFragment target;

    @UiThread
    public FriendRequestsFragment_ViewBinding(FriendRequestsFragment friendRequestsFragment, View view) {
        this.target = friendRequestsFragment;
        friendRequestsFragment.currentlyNoFriendsRequests = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_requests_no_friends, "field 'currentlyNoFriendsRequests'", TextView.class);
        friendRequestsFragment.loadingFriendsRequestsProgressIndicatorLayout = Utils.findRequiredView(view, R.id.friend_requests_progress_bar, "field 'loadingFriendsRequestsProgressIndicatorLayout'");
        friendRequestsFragment.requestsList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.friend_requests_recycler_view, "field 'requestsList'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        FriendRequestsFragment friendRequestsFragment = this.target;
        if (friendRequestsFragment != null) {
            this.target = null;
            friendRequestsFragment.currentlyNoFriendsRequests = null;
            friendRequestsFragment.loadingFriendsRequestsProgressIndicatorLayout = null;
            friendRequestsFragment.requestsList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
