package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class JoinChallengeActivity_ViewBinding implements Unbinder {
    private JoinChallengeActivity target;

    @UiThread
    public JoinChallengeActivity_ViewBinding(JoinChallengeActivity joinChallengeActivity) {
        this(joinChallengeActivity, joinChallengeActivity.getWindow().getDecorView());
    }

    @UiThread
    public JoinChallengeActivity_ViewBinding(JoinChallengeActivity joinChallengeActivity, View view) {
        this.target = joinChallengeActivity;
        joinChallengeActivity.viewGroup = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.container, "field 'viewGroup'", ViewGroup.class);
        joinChallengeActivity.button = Utils.findRequiredView(view, R.id.button, "field 'button'");
    }

    @CallSuper
    public void unbind() {
        JoinChallengeActivity joinChallengeActivity = this.target;
        if (joinChallengeActivity != null) {
            this.target = null;
            joinChallengeActivity.viewGroup = null;
            joinChallengeActivity.button = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
