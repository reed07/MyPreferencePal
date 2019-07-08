package com.myfitnesspal.feature.challenges.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.MfpWebView;

public class JoinedChallengeDetailsFragment_ViewBinding implements Unbinder {
    private JoinedChallengeDetailsFragment target;

    @UiThread
    public JoinedChallengeDetailsFragment_ViewBinding(JoinedChallengeDetailsFragment joinedChallengeDetailsFragment, View view) {
        this.target = joinedChallengeDetailsFragment;
        joinedChallengeDetailsFragment.challengeWebView = (MfpWebView) Utils.findRequiredViewAsType(view, R.id.detailsWebView, "field 'challengeWebView'", MfpWebView.class);
        joinedChallengeDetailsFragment.tvDetailsHeadline = (TextView) Utils.findRequiredViewAsType(view, R.id.tvDetailsHeadline, "field 'tvDetailsHeadline'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        JoinedChallengeDetailsFragment joinedChallengeDetailsFragment = this.target;
        if (joinedChallengeDetailsFragment != null) {
            this.target = null;
            joinedChallengeDetailsFragment.challengeWebView = null;
            joinedChallengeDetailsFragment.tvDetailsHeadline = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
