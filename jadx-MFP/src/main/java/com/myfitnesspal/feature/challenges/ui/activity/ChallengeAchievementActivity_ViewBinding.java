package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class ChallengeAchievementActivity_ViewBinding implements Unbinder {
    private ChallengeAchievementActivity target;

    @UiThread
    public ChallengeAchievementActivity_ViewBinding(ChallengeAchievementActivity challengeAchievementActivity) {
        this(challengeAchievementActivity, challengeAchievementActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChallengeAchievementActivity_ViewBinding(ChallengeAchievementActivity challengeAchievementActivity, View view) {
        this.target = challengeAchievementActivity;
        challengeAchievementActivity.achWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.ach_webview, "field 'achWebView'", WebView.class);
        challengeAchievementActivity.achContainer = Utils.findRequiredView(view, R.id.ach_container, "field 'achContainer'");
        challengeAchievementActivity.achImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.ach_image, "field 'achImage'", ImageView.class);
        challengeAchievementActivity.achDescription = (TextView) Utils.findRequiredViewAsType(view, R.id.ach_description, "field 'achDescription'", TextView.class);
        challengeAchievementActivity.achTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.ach_title, "field 'achTitle'", TextView.class);
        challengeAchievementActivity.progressContainer = Utils.findRequiredView(view, R.id.progress_container, "field 'progressContainer'");
        challengeAchievementActivity.achProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.ach_progressbar, "field 'achProgressBar'", ProgressBar.class);
        challengeAchievementActivity.achProgression = (TextView) Utils.findRequiredViewAsType(view, R.id.ach_progression, "field 'achProgression'", TextView.class);
        challengeAchievementActivity.achProgressionType = (TextView) Utils.findRequiredViewAsType(view, R.id.ach_progression_type, "field 'achProgressionType'", TextView.class);
        challengeAchievementActivity.achTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.ach_target, "field 'achTarget'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ChallengeAchievementActivity challengeAchievementActivity = this.target;
        if (challengeAchievementActivity != null) {
            this.target = null;
            challengeAchievementActivity.achWebView = null;
            challengeAchievementActivity.achContainer = null;
            challengeAchievementActivity.achImage = null;
            challengeAchievementActivity.achDescription = null;
            challengeAchievementActivity.achTitle = null;
            challengeAchievementActivity.progressContainer = null;
            challengeAchievementActivity.achProgressBar = null;
            challengeAchievementActivity.achProgression = null;
            challengeAchievementActivity.achProgressionType = null;
            challengeAchievementActivity.achTarget = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
