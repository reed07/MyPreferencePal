package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;

public class ChallengePrizesFragment_ViewBinding implements Unbinder {
    private ChallengePrizesFragment target;

    @UiThread
    public ChallengePrizesFragment_ViewBinding(ChallengePrizesFragment challengePrizesFragment, View view) {
        this.target = challengePrizesFragment;
        challengePrizesFragment.challengeBannerImageView = (ChallengeBannerImageView) Utils.findRequiredViewAsType(view, R.id.challenge_banner, "field 'challengeBannerImageView'", ChallengeBannerImageView.class);
        challengePrizesFragment.prizeContainer = (FrameLayout) Utils.findOptionalViewAsType(view, R.id.flChallengePrizeContainer, "field 'prizeContainer'", FrameLayout.class);
        challengePrizesFragment.achievementContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flChallengeAchievementContainer, "field 'achievementContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        ChallengePrizesFragment challengePrizesFragment = this.target;
        if (challengePrizesFragment != null) {
            this.target = null;
            challengePrizesFragment.challengeBannerImageView = null;
            challengePrizesFragment.prizeContainer = null;
            challengePrizesFragment.achievementContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
