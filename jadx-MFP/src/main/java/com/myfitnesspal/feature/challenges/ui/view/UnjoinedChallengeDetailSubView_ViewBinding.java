package com.myfitnesspal.feature.challenges.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class UnjoinedChallengeDetailSubView_ViewBinding implements Unbinder {
    private UnjoinedChallengeDetailSubView target;

    @UiThread
    public UnjoinedChallengeDetailSubView_ViewBinding(UnjoinedChallengeDetailSubView unjoinedChallengeDetailSubView) {
        this(unjoinedChallengeDetailSubView, unjoinedChallengeDetailSubView);
    }

    @UiThread
    public UnjoinedChallengeDetailSubView_ViewBinding(UnjoinedChallengeDetailSubView unjoinedChallengeDetailSubView, View view) {
        this.target = unjoinedChallengeDetailSubView;
        unjoinedChallengeDetailSubView.challengeGoal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_challenge_goal, "field 'challengeGoal'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        UnjoinedChallengeDetailSubView unjoinedChallengeDetailSubView = this.target;
        if (unjoinedChallengeDetailSubView != null) {
            this.target = null;
            unjoinedChallengeDetailSubView.challengeGoal = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
