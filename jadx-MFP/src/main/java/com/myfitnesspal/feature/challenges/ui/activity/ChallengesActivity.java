package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengesFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class ChallengesActivity extends MfpActivity {
    public static Intent newStartIntent(Context context) {
        return new Intent(context, ChallengesActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        setTitle(R.string.challenges_activity_title);
        initFragment(bundle);
    }

    private void initFragment(Bundle bundle) {
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ChallengesFragment.newInstance(), tag(ChallengesFragment.class)).commit();
        }
    }
}
