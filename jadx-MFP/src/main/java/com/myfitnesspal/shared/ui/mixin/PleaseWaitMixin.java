package com.myfitnesspal.shared.ui.mixin;

import android.support.v4.app.Fragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;

public class PleaseWaitMixin extends RunnerLifecycleMixin {
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";

    public PleaseWaitMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
    }

    public void showPleaseWait() {
        MfpActivity mfpActivity = getMfpActivity();
        if (mfpActivity.getSupportFragmentManager().findFragmentByTag("progress_dialog") == null) {
            mfpActivity.showDialogFragment(ProgressDialogFragment.newInstance(R.string.please_wait), "progress_dialog");
        }
    }

    public void hidePleaseWait() {
        Fragment findFragmentByTag = getMfpActivity().getSupportFragmentManager().findFragmentByTag("progress_dialog");
        if (findFragmentByTag != null) {
            ((ProgressDialogFragment) findFragmentByTag).dismiss();
        }
    }
}
