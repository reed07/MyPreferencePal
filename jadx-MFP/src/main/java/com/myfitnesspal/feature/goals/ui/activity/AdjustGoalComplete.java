package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.UpdateGoalsRefreshEvent;
import com.myfitnesspal.feature.goals.ui.fragment.UpdateGoalsCompleteFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Produce;

public class AdjustGoalComplete extends MfpActivity {
    private static final String UPDATE_GOALS_COMPLETE_FRAGMENT = "UpdateGoalsCompleteFragment";
    private UpdateGoalsCompleteFragment completeFragment;

    public String getAnalyticsScreenTag() {
        return Screens.ADJUST_GOAL_COMPLETE;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AdjustGoalComplete.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.update_goals);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        this.completeFragment = (UpdateGoalsCompleteFragment) supportFragmentManager.findFragmentByTag(UPDATE_GOALS_COMPLETE_FRAGMENT);
        if (this.completeFragment == null) {
            this.completeFragment = new UpdateGoalsCompleteFragment();
            beginTransaction.add(R.id.container, this.completeFragment, UPDATE_GOALS_COMPLETE_FRAGMENT);
        }
        beginTransaction.show(this.completeFragment).commit();
    }

    @Produce
    public UpdateGoalsRefreshEvent produceUpdateGoalsRefreshEvent() {
        return new UpdateGoalsRefreshEvent();
    }
}
