package com.myfitnesspal.feature.friends.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FriendsListFragment_ViewBinding implements Unbinder {
    private FriendsListFragment target;
    private View view2131362684;

    @UiThread
    public FriendsListFragment_ViewBinding(final FriendsListFragment friendsListFragment, View view) {
        this.target = friendsListFragment;
        friendsListFragment.friendsProgressBar = Utils.findRequiredView(view, R.id.friends_list_progress_bar, "field 'friendsProgressBar'");
        friendsListFragment.friendsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.friends_list_recycler_view, "field 'friendsRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.friends_list_no_friends, "field 'noFriendsLayout' and method 'onInviteFriendsClick'");
        friendsListFragment.noFriendsLayout = (ViewGroup) Utils.castView(findRequiredView, R.id.friends_list_no_friends, "field 'noFriendsLayout'", ViewGroup.class);
        this.view2131362684 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                friendsListFragment.onInviteFriendsClick();
            }
        });
    }

    @CallSuper
    public void unbind() {
        FriendsListFragment friendsListFragment = this.target;
        if (friendsListFragment != null) {
            this.target = null;
            friendsListFragment.friendsProgressBar = null;
            friendsListFragment.friendsRecyclerView = null;
            friendsListFragment.noFriendsLayout = null;
            this.view2131362684.setOnClickListener(null);
            this.view2131362684 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
