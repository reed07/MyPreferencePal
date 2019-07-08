package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class JoinedFriendsFragment_ViewBinding implements Unbinder {
    private JoinedFriendsFragment target;

    @UiThread
    public JoinedFriendsFragment_ViewBinding(JoinedFriendsFragment joinedFriendsFragment, View view) {
        this.target = joinedFriendsFragment;
        joinedFriendsFragment.friendsContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flJoinedFriendsContainer, "field 'friendsContainer'", FrameLayout.class);
        joinedFriendsFragment.invitesContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flJoinedInvitesContainer, "field 'invitesContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        JoinedFriendsFragment joinedFriendsFragment = this.target;
        if (joinedFriendsFragment != null) {
            this.target = null;
            joinedFriendsFragment.friendsContainer = null;
            joinedFriendsFragment.invitesContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
