package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.fragment.EatingDisorderUpdateGoalCompleteFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class EatingDisorderAdjustGoalComplete extends MfpActivity {
    private static final String EATING_DISORDER_UPDATE_GOALS_COMPLETE_FRAGMENT = "EatingDisorderUpdateGoalCompleteFragment";
    private EatingDisorderUpdateGoalCompleteFragment completeFragment;

    public String getAnalyticsScreenTag() {
        return Screens.EATING_DISORDER_ADJUST_GOAL_COMPLETE;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, EatingDisorderAdjustGoalComplete.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.update_goals);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        this.completeFragment = (EatingDisorderUpdateGoalCompleteFragment) supportFragmentManager.findFragmentByTag(EATING_DISORDER_UPDATE_GOALS_COMPLETE_FRAGMENT);
        if (this.completeFragment == null) {
            this.completeFragment = new EatingDisorderUpdateGoalCompleteFragment();
            beginTransaction.add(R.id.container, this.completeFragment, EATING_DISORDER_UPDATE_GOALS_COMPLETE_FRAGMENT);
        }
        beginTransaction.show(this.completeFragment).commit();
    }
}
