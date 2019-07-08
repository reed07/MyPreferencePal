package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;

public class ChallengeFriendsFragment_ViewBinding implements Unbinder {
    private ChallengeFriendsFragment target;

    @UiThread
    public ChallengeFriendsFragment_ViewBinding(ChallengeFriendsFragment challengeFriendsFragment, View view) {
        this.target = challengeFriendsFragment;
        challengeFriendsFragment.bannerImageView = (ChallengeBannerImageView) Utils.findRequiredViewAsType(view, R.id.challenge_banner, "field 'bannerImageView'", ChallengeBannerImageView.class);
        challengeFriendsFragment.friends = (ListView) Utils.findRequiredViewAsType(view, R.id.lvFriends, "field 'friends'", ListView.class);
        challengeFriendsFragment.inviteCardContainer = Utils.findRequiredView(view, R.id.invite_card_container, "field 'inviteCardContainer'");
    }

    @CallSuper
    public void unbind() {
        ChallengeFriendsFragment challengeFriendsFragment = this.target;
        if (challengeFriendsFragment != null) {
            this.target = null;
            challengeFriendsFragment.bannerImageView = null;
            challengeFriendsFragment.friends = null;
            challengeFriendsFragment.inviteCardContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
