package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.fragment.LikesListFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.ExtrasUtils;

public class LikesActivity extends MfpActivity {
    public static Intent newStartIntent(Context context, String str) {
        return newStartIntent(context, str, null);
    }

    public static Intent newStartIntent(Context context, String str, String str2) {
        return new Intent(context, LikesActivity.class).putExtra(Extras.NEWSFEED_ENTRY_ID, str).putExtra(Extras.COMMENT_ID, str2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.likes);
        setTitle(R.string.likes);
        if (bundle == null) {
            Intent intent = getIntent();
            if (ExtrasUtils.hasExtra(intent, Extras.NEWSFEED_ENTRY_ID) && ExtrasUtils.hasExtra(intent, Extras.COMMENT_ID)) {
                LikesListFragment newInstance = LikesListFragment.newInstance(intent.getStringExtra(Extras.NEWSFEED_ENTRY_ID), intent.getStringExtra(Extras.COMMENT_ID));
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                beginTransaction.add(R.id.container, newInstance, LikesListFragment.class.getName());
                beginTransaction.commit();
            } else if (ExtrasUtils.hasExtra(intent, Extras.NEWSFEED_ENTRY_ID)) {
                LikesListFragment newInstance2 = LikesListFragment.newInstance(intent.getStringExtra(Extras.NEWSFEED_ENTRY_ID));
                FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
                beginTransaction2.add(R.id.container, newInstance2, LikesListFragment.class.getName());
                beginTransaction2.commit();
            } else {
                notifyCouldNotLoad();
                finish();
            }
        }
    }

    private void notifyCouldNotLoad() {
        Toaster.showLong((Activity) this, (int) R.string.failed_to_load_app_data);
    }
}
