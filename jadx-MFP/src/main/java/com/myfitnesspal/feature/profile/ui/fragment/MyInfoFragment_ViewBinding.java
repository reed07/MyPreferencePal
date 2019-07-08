package com.myfitnesspal.feature.profile.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.profile.ui.view.AppsCard;
import com.myfitnesspal.feature.profile.ui.view.ChallengesCard;
import com.myfitnesspal.feature.profile.ui.view.FriendsCard;
import com.myfitnesspal.feature.profile.ui.view.GoalsCard;
import com.myfitnesspal.feature.profile.ui.view.ProgressCard;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;

public class MyInfoFragment_ViewBinding implements Unbinder {
    private MyInfoFragment target;

    @UiThread
    public MyInfoFragment_ViewBinding(MyInfoFragment myInfoFragment, View view) {
        this.target = myInfoFragment;
        myInfoFragment.progress = (ProgressCard) Utils.findRequiredViewAsType(view, R.id.progressCard, "field 'progress'", ProgressCard.class);
        myInfoFragment.goals = (GoalsCard) Utils.findRequiredViewAsType(view, R.id.goalsCard, "field 'goals'", GoalsCard.class);
        myInfoFragment.challenges = (ChallengesCard) Utils.findRequiredViewAsType(view, R.id.challengesCard, "field 'challenges'", ChallengesCard.class);
        myInfoFragment.friends = (FriendsCard) Utils.findRequiredViewAsType(view, R.id.friendsCard, "field 'friends'", FriendsCard.class);
        myInfoFragment.apps = (AppsCard) Utils.findRequiredViewAsType(view, R.id.appsCard, "field 'apps'", AppsCard.class);
        myInfoFragment.scrollView = (StateAwareScrollView) Utils.findRequiredViewAsType(view, R.id.myInfoScrollView, "field 'scrollView'", StateAwareScrollView.class);
    }

    @CallSuper
    public void unbind() {
        MyInfoFragment myInfoFragment = this.target;
        if (myInfoFragment != null) {
            this.target = null;
            myInfoFragment.progress = null;
            myInfoFragment.goals = null;
            myInfoFragment.challenges = null;
            myInfoFragment.friends = null;
            myInfoFragment.apps = null;
            myInfoFragment.scrollView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
