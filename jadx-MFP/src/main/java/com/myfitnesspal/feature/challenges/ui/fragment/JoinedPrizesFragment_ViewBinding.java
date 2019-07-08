package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;

public class JoinedPrizesFragment_ViewBinding implements Unbinder {
    private JoinedPrizesFragment target;

    @UiThread
    public JoinedPrizesFragment_ViewBinding(JoinedPrizesFragment joinedPrizesFragment, View view) {
        this.target = joinedPrizesFragment;
        joinedPrizesFragment.bannerImageView = (ChallengeBannerImageView) Utils.findRequiredViewAsType(view, R.id.challenge_banner, "field 'bannerImageView'", ChallengeBannerImageView.class);
        joinedPrizesFragment.prizesContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flJoinedPrizesContainer, "field 'prizesContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        JoinedPrizesFragment joinedPrizesFragment = this.target;
        if (joinedPrizesFragment != null) {
            this.target = null;
            joinedPrizesFragment.bannerImageView = null;
            joinedPrizesFragment.prizesContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
