package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class JoinedChallengeSummaryFragment_ViewBinding implements Unbinder {
    private JoinedChallengeSummaryFragment target;

    @UiThread
    public JoinedChallengeSummaryFragment_ViewBinding(JoinedChallengeSummaryFragment joinedChallengeSummaryFragment, View view) {
        this.target = joinedChallengeSummaryFragment;
        joinedChallengeSummaryFragment.summaryContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flJoinedSummaryContainer, "field 'summaryContainer'", FrameLayout.class);
        joinedChallengeSummaryFragment.achievementsContainer = (FrameLayout) Utils.findOptionalViewAsType(view, R.id.flJoinedAchievementsContainer, "field 'achievementsContainer'", FrameLayout.class);
        joinedChallengeSummaryFragment.leaveChallenge = (TextView) Utils.findRequiredViewAsType(view, R.id.btnLeaveChallenge, "field 'leaveChallenge'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        JoinedChallengeSummaryFragment joinedChallengeSummaryFragment = this.target;
        if (joinedChallengeSummaryFragment != null) {
            this.target = null;
            joinedChallengeSummaryFragment.summaryContainer = null;
            joinedChallengeSummaryFragment.achievementsContainer = null;
            joinedChallengeSummaryFragment.leaveChallenge = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
