package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class StepsSettings extends MfpActivity {
    public String getAnalyticsScreenTag() {
        return Screens.STEPS_SETTINGS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, StepsSettings.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.steps_settings);
    }
}
