package com.myfitnesspal.feature.notificationinbox.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.ui.activity.HomeMessagesActivity;
import com.myfitnesspal.feature.notificationinbox.ui.fragment.NotificationInboxFragment;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.ConfigUtils;

public class NotificationInboxActivity extends MfpActivity {
    public boolean showAsTopLevelActivity() {
        return false;
    }

    public static Intent newStartIntent(Context context, ConfigService configService) {
        if (ConfigUtils.isAppNavUpdateHomeEnabled(configService)) {
            return HomeMessagesActivity.newStartIntent(context);
        }
        return new Intent(context, NotificationInboxActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        if (bundle == null) {
            NotificationInboxFragment newInstance = NotificationInboxFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newInstance, tag((Fragment) newInstance)).commit();
        }
    }
}
