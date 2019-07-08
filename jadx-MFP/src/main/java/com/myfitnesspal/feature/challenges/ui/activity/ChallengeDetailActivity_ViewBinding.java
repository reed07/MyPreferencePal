package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeDetailActivity_ViewBinding implements Unbinder {
    private ChallengeDetailActivity target;

    @UiThread
    public ChallengeDetailActivity_ViewBinding(ChallengeDetailActivity challengeDetailActivity) {
        this(challengeDetailActivity, challengeDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChallengeDetailActivity_ViewBinding(ChallengeDetailActivity challengeDetailActivity, View view) {
        this.target = challengeDetailActivity;
        challengeDetailActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.vpSingleChallenge, "field 'viewPager'", ViewPager.class);
        challengeDetailActivity.joinButton = (Button) Utils.findRequiredViewAsType(view, R.id.btnJoinChallenge, "field 'joinButton'", Button.class);
        challengeDetailActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tlChallenges, "field 'tabLayout'", TabLayout.class);
        challengeDetailActivity.cbEmailConsent = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_email_consent, "field 'cbEmailConsent'", CheckBox.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeDetailActivity challengeDetailActivity = this.target;
        if (challengeDetailActivity != null) {
            this.target = null;
            challengeDetailActivity.viewPager = null;
            challengeDetailActivity.joinButton = null;
            challengeDetailActivity.tabLayout = null;
            challengeDetailActivity.cbEmailConsent = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
