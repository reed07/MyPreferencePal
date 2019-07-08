package com.myfitnesspal.feature.challenges.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class UserAchievementsActivity_ViewBinding implements Unbinder {
    private UserAchievementsActivity target;

    @UiThread
    public UserAchievementsActivity_ViewBinding(UserAchievementsActivity userAchievementsActivity) {
        this(userAchievementsActivity, userAchievementsActivity.getWindow().getDecorView());
    }

    @UiThread
    public UserAchievementsActivity_ViewBinding(UserAchievementsActivity userAchievementsActivity, View view) {
        this.target = userAchievementsActivity;
        userAchievementsActivity.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.lvUserAchievements, "field 'listView'", ListView.class);
        userAchievementsActivity.errorMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.error_message, "field 'errorMessage'", TextView.class);
        userAchievementsActivity.joinChallenge = (Button) Utils.findRequiredViewAsType(view, R.id.join_challenge, "field 'joinChallenge'", Button.class);
    }

    @CallSuper
    public void unbind() {
        UserAchievementsActivity userAchievementsActivity = this.target;
        if (userAchievementsActivity != null) {
            this.target = null;
            userAchievementsActivity.listView = null;
            userAchievementsActivity.errorMessage = null;
            userAchievementsActivity.joinChallenge = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
