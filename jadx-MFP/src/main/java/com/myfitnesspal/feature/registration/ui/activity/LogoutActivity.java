package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduleProgressInfo;
import com.uacf.sync.engine.UacfSchedulerEngine.Callbacks;
import javax.inject.Inject;

public class LogoutActivity extends MfpActivity {
    private static final String DIALOG_ID = "progress_dialog";
    private static final int FORCE_BACK_COUNT = 5;
    private static final int FORCE_LOGOUT_TIMEOUT_MS = 1800000;
    private int backCount = 0;
    private Runnable forceLogoutRunnable = new Runnable() {
        public final void run() {
            LogoutActivity.this.logUserOut();
        }
    };
    private Handler handler = new Handler();
    @Inject
    Session session;
    private boolean syncCompleted;
    @Inject
    SyncService syncService;

    public boolean shouldShowTitle() {
        return false;
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, LogoutActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new View(this));
        component().inject(this);
        waitForSyncService();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ProgressDialogFragment newInstance = ProgressDialogFragment.newInstance(R.string.please_wait);
        newInstance.setCancelable(false);
        showDialogFragment(newInstance, "progress_dialog");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.forceLogoutRunnable);
    }

    public void onBackPressed() {
        int i = this.backCount + 1;
        this.backCount = i;
        if (i > 5) {
            super.onBackPressed();
        }
    }

    private void waitForSyncService() {
        this.syncService.enqueue(SyncType.Incremental);
        this.syncService.enqueue(SyncType.NoOp, new Callbacks<SyncType>() {
            public void onCompleted(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
                LogoutActivity.this.logUserOut();
            }

            public void onProgress(UacfScheduleProgressInfo<SyncType> uacfScheduleProgressInfo) {
                LogoutActivity.this.logUserOut();
            }
        });
        this.handler.removeCallbacks(this.forceLogoutRunnable);
        this.handler.postDelayed(this.forceLogoutRunnable, 1800000);
    }

    /* access modifiers changed from: private */
    public void logUserOut() {
        if (!this.syncCompleted) {
            this.syncCompleted = true;
            this.syncService.abortAndClearQueue();
            this.session.logoutAndNavigateToLoginActivity();
        }
    }
}
