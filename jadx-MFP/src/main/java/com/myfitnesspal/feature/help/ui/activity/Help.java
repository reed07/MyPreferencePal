package com.myfitnesspal.feature.help.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.Ln;

public class Help extends MfpActivity {
    public String getAnalyticsScreenTag() {
        return Screens.HELP;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, Help.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.help);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 82 && i2 == -1) {
            Ln.d("PROPS: news feed settings changed, starting sync", new Object[0]);
            scheduleSync();
        }
    }
}
