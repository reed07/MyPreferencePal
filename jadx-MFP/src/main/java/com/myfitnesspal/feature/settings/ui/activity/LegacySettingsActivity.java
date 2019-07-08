package com.myfitnesspal.feature.settings.ui.activity;

import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class LegacySettingsActivity extends MfpActivity {
    public String getAnalyticsScreenTag() {
        return Screens.SETTINGS;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.legacy_settings_activity);
    }
}
