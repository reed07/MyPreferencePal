package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.fragment.GoalsFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Fragments;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class Goals extends MfpActivity {
    public String getAnalyticsScreenTag() {
        return Screens.GOALS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, Goals.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.goals_activity);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.goals_container, GoalsFragment.newInstance(), Fragments.GOALS_PREMIUM_FRAGMENT).commit();
        }
    }
}
