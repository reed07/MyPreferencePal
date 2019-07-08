package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeShareActivity_ViewBinding implements Unbinder {
    private ChallengeShareActivity target;

    @UiThread
    public ChallengeShareActivity_ViewBinding(ChallengeShareActivity challengeShareActivity) {
        this(challengeShareActivity, challengeShareActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChallengeShareActivity_ViewBinding(ChallengeShareActivity challengeShareActivity, View view) {
        this.target = challengeShareActivity;
        challengeShareActivity.svChallengeShareView = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.svChallengeShareView, "field 'svChallengeShareView'", ViewGroup.class);
        challengeShareActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeShareActivity challengeShareActivity = this.target;
        if (challengeShareActivity != null) {
            this.target = null;
            challengeShareActivity.svChallengeShareView = null;
            challengeShareActivity.titleTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
