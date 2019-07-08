package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeDetailsFragment_ViewBinding implements Unbinder {
    private ChallengeDetailsFragment target;

    @UiThread
    public ChallengeDetailsFragment_ViewBinding(ChallengeDetailsFragment challengeDetailsFragment, View view) {
        this.target = challengeDetailsFragment;
        challengeDetailsFragment.summaryContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flChallengeDetailsSummaryContainer, "field 'summaryContainer'", FrameLayout.class);
        challengeDetailsFragment.friendsContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flChallengeDetailsFriendsContainer, "field 'friendsContainer'", FrameLayout.class);
        challengeDetailsFragment.dynamicLinksContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dynamic_links_container, "field 'dynamicLinksContainer'", LinearLayout.class);
        challengeDetailsFragment.dynamicLinksCardView = Utils.findRequiredView(view, R.id.dynamic_links_card_view, "field 'dynamicLinksCardView'");
        challengeDetailsFragment.ach1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ach1, "field 'ach1'", ImageView.class);
        challengeDetailsFragment.ach2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ach2, "field 'ach2'", ImageView.class);
        challengeDetailsFragment.ach3 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_ach3, "field 'ach3'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeDetailsFragment challengeDetailsFragment = this.target;
        if (challengeDetailsFragment != null) {
            this.target = null;
            challengeDetailsFragment.summaryContainer = null;
            challengeDetailsFragment.friendsContainer = null;
            challengeDetailsFragment.dynamicLinksContainer = null;
            challengeDetailsFragment.dynamicLinksCardView = null;
            challengeDetailsFragment.ach1 = null;
            challengeDetailsFragment.ach2 = null;
            challengeDetailsFragment.ach3 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
