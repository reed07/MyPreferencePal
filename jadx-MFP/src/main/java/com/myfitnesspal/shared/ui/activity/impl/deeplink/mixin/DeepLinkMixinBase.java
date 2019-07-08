package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import dagger.Lazy;
import javax.inject.Inject;

public abstract class DeepLinkMixinBase extends RunnerLifecycleMixin {
    private static final String ERROR_DIALOG_TAG = "AddFoodDeepLinkProxy.ErrorDialog";
    @Inject
    Lazy<AnalyticsService> analytics;
    private DialogPositiveListener onDialogOkListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            DeepLinkMixinBase.this.getActivity().finish();
        }
    };

    public DeepLinkMixinBase(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AnalyticsService) this.analytics.get()).reportEvent(Events.APP_INDEXING_PAGE_VIEW);
    }

    /* access modifiers changed from: protected */
    public void onInvalidResponse(int i) {
        onInvalidResponse(getActivity().getString(i));
    }

    /* access modifiers changed from: protected */
    public void onInvalidResponse(String str) {
        showErrorDialog(str);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (ERROR_DIALOG_TAG.equals(str)) {
            ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onDialogOkListener);
        }
        return super.onRebindDialogFragment(dialogFragment, str);
    }

    private void showErrorDialog(String str) {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.app_name)).setMessage(str)).setPositiveText(R.string.ok, this.onDialogOkListener);
        alertDialogFragment.setCancelable(false);
        getComponentInterface().showDialogFragment(alertDialogFragment, ERROR_DIALOG_TAG);
    }
}
