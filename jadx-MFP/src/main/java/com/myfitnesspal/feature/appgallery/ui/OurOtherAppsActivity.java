package com.myfitnesspal.feature.appgallery.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class OurOtherAppsActivity extends MfpActivity {
    public String getAnalyticsScreenTag() {
        return Screens.OUR_OTHER_APPS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, OurOtherAppsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        loadFragment(bundle);
    }

    private void loadFragment(Bundle bundle) {
        Fragment findFragmentByTag = bundle != null ? getSupportFragmentManager().findFragmentByTag(OurOtherAppsFragment.class.getName()) : null;
        if (findFragmentByTag == null) {
            findFragmentByTag = OurOtherAppsFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, findFragmentByTag, OurOtherAppsFragment.class.getName()).commit();
    }
}
