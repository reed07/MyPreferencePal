package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengesFragment_ViewBinding implements Unbinder {
    private ChallengesFragment target;

    @UiThread
    public ChallengesFragment_ViewBinding(ChallengesFragment challengesFragment, View view) {
        this.target = challengesFragment;
        challengesFragment.emptyState = Utils.findRequiredView(view, R.id.emptyState, "field 'emptyState'");
        challengesFragment.tvEmptyState = (TextView) Utils.findRequiredViewAsType(view, R.id.tvEmptyState, "field 'tvEmptyState'", TextView.class);
        challengesFragment.challengesList = (ListView) Utils.findRequiredViewAsType(view, R.id.lvChallengesSummary, "field 'challengesList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengesFragment challengesFragment = this.target;
        if (challengesFragment != null) {
            this.target = null;
            challengesFragment.emptyState = null;
            challengesFragment.tvEmptyState = null;
            challengesFragment.challengesList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
