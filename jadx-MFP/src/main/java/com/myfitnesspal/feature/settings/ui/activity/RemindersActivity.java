package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.fragment.RemindersFragment;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class RemindersActivity extends MfpActivity {
    public static Intent newStartIntent(Context context) {
        return new Intent(context, RemindersActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        if (bundle == null) {
            RemindersFragment newInstance = RemindersFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newInstance, tag((Fragment) newInstance)).commit();
        }
    }
}
