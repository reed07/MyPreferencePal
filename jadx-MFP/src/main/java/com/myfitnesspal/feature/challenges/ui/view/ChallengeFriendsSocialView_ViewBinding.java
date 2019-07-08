package com.myfitnesspal.feature.challenges.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeFriendsSocialView_ViewBinding implements Unbinder {
    private ChallengeFriendsSocialView target;

    @UiThread
    public ChallengeFriendsSocialView_ViewBinding(ChallengeFriendsSocialView challengeFriendsSocialView) {
        this(challengeFriendsSocialView, challengeFriendsSocialView);
    }

    @UiThread
    public ChallengeFriendsSocialView_ViewBinding(ChallengeFriendsSocialView challengeFriendsSocialView, View view) {
        this.target = challengeFriendsSocialView;
        challengeFriendsSocialView.inviteFriends = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.invite_mfp_friends, "field 'inviteFriends'", ViewGroup.class);
        challengeFriendsSocialView.inviteEmail = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.invite_email, "field 'inviteEmail'", ViewGroup.class);
        challengeFriendsSocialView.inviteFacebook = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.invite_facebook, "field 'inviteFacebook'", ViewGroup.class);
        challengeFriendsSocialView.inviteTwitter = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.invite_twitter, "field 'inviteTwitter'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeFriendsSocialView challengeFriendsSocialView = this.target;
        if (challengeFriendsSocialView != null) {
            this.target = null;
            challengeFriendsSocialView.inviteFriends = null;
            challengeFriendsSocialView.inviteEmail = null;
            challengeFriendsSocialView.inviteFacebook = null;
            challengeFriendsSocialView.inviteTwitter = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
