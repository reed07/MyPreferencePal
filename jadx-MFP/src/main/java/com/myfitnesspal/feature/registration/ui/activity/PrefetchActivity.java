package com.myfitnesspal.feature.registration.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.registration.service.PrefetchService;
import com.myfitnesspal.feature.registration.task.PrefetchTask;
import com.myfitnesspal.feature.registration.task.PrefetchTask.CompletedEvent;
import com.myfitnesspal.feature.registration.task.PrefetchTask.Result;
import com.myfitnesspal.feature.registration.ui.fragment.LoginPleaseWaitFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import javax.inject.Inject;

public class PrefetchActivity extends MfpActivity {
    private static final String PLEASE_WAIT_TAG = "please_wait_fragment";
    private static final String SYNC_REQUIRED_DIALOG_TAG = "sync_required_dialog";
    private DialogNegativeListener onSyncRequiredDialogCloseListener = new DialogNegativeListener() {
        public final void onClick() {
            PrefetchActivity.this.finish();
        }
    };
    private DialogPositiveListener onSyncRequiredDialogRetryListener = new DialogPositiveListener() {
        public final void onClick(Object obj) {
            PrefetchActivity.this.startPrefetch();
        }
    };
    @Inject
    Lazy<PrefetchService> prefetchService;

    public boolean backPressed() {
        return true;
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    public boolean showToolbar() {
        return false;
    }

    public static Intent getStartIntent(Context context, boolean z) {
        Intent intent = new Intent(context, PrefetchActivity.class);
        intent.putExtra(Extras.APP_JUST_STARTED, z);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setResult(0);
        setContentView((int) R.layout.login);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.center_fragment_container, LoginPleaseWaitFragment.newInstance(), PLEASE_WAIT_TAG).commit();
            startPrefetch();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (((PrefetchService) this.prefetchService.get()).isComplete()) {
            handleResult(((PrefetchService) this.prefetchService.get()).getTaskResult());
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(str, SYNC_REQUIRED_DIALOG_TAG)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        alertDialogFragment.setPositiveListener(this.onSyncRequiredDialogRetryListener);
        alertDialogFragment.setNegativeListener(this.onSyncRequiredDialogCloseListener);
        return true;
    }

    @Subscribe
    public void onPrefetchCompletedEvent(CompletedEvent completedEvent) {
        handleResult((Result) completedEvent.getResult());
    }

    private void handleResult(Result result) {
        if (result == Result.Success) {
            moveToHome();
        } else {
            showSyncRequiredDialog();
        }
    }

    private void showSyncRequiredDialog() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.connection_failed)).setTitle(R.string.sync_failed)).setPositiveText(R.string.retry, this.onSyncRequiredDialogRetryListener)).setNegativeText(R.string.close, this.onSyncRequiredDialogCloseListener);
        alertDialogFragment.setCancelable(false);
        showDialogFragment(alertDialogFragment, SYNC_REQUIRED_DIALOG_TAG);
    }

    /* access modifiers changed from: private */
    public void startPrefetch() {
        new PrefetchTask(this.prefetchService).setDedupeMode(DedupeMode.UseExisting).run(getRunner());
    }

    private void moveToHome() {
        getNavigationHelper().finishActivityAfterNavigation().withExtra(Extras.APP_JUST_STARTED, getIntent().getBooleanExtra(Extras.APP_JUST_STARTED, false)).withIntent(HomeActivity.newStartIntent(this)).startActivity();
    }
}
