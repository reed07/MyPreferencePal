package com.myfitnesspal.shared.ui.mixin;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.view.WindowManager.BadTokenException;
import com.myfitnesspal.android.R;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.event.HideProgressDialogEvent;
import com.myfitnesspal.shared.event.ShowProgressDialogEvent;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

@Deprecated
public class LegacyAlertMixin extends RunnerLifecycleMixin {
    private ProgressDialog progressDialog = null;

    public LegacyAlertMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
    }

    @Deprecated
    public void showAlertDialog(CharSequence charSequence) {
        showAlertDialogWithTitle(null, charSequence, null);
    }

    @Deprecated
    public void showAlertDialogWithTitle(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        showAlertDialogWithTitleAndListeners(charSequence, charSequence2, charSequence3, null, null, null);
    }

    @Deprecated
    public void showAlertDialogWithTitleAndListeners(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, OnClickListener onClickListener, CharSequence charSequence4, OnClickListener onClickListener2) {
        showAlertDialogWithInfo(charSequence, charSequence2, charSequence3, onClickListener, charSequence4, onClickListener2, null);
    }

    @Deprecated
    private void showAlertDialogWithInfo(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, OnClickListener onClickListener, CharSequence charSequence4, OnClickListener onClickListener2, Drawable drawable) {
        Activity activity = getActivity();
        String strings = Strings.toString(charSequence, activity.getString(R.string.alert));
        String strings2 = Strings.toString(charSequence3, activity.getString(R.string.dismiss));
        if (onClickListener == null) {
            onClickListener = $$Lambda$LegacyAlertMixin$qz2z4M3LY75UDtQmLGw9o4texcY.INSTANCE;
        }
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(activity);
        mfpAlertDialogBuilder.setTitle((CharSequence) strings).setIcon(drawable).setMessage(charSequence2).setPositiveButton((CharSequence) strings2, onClickListener).setNegativeButton(charSequence4, onClickListener2);
        try {
            mfpAlertDialogBuilder.show();
        } catch (BadTokenException e) {
            Ln.e(e);
        }
    }

    @Deprecated
    public void showGenericAlert(Throwable th) {
        Ln.e(th);
        Activity activity = getActivity();
        showAlertDialogWithTitle(activity.getString(R.string.error), activity.getString(R.string.generic_error_msg), activity.getString(R.string.dismiss));
    }

    @Deprecated
    public Dialog createProgressDialog(String str, String str2, boolean z, boolean z2) {
        try {
            this.progressDialog = ProgressDialog.show(getActivity(), Strings.toString(str), Strings.toString(str2), z2, z);
            return this.progressDialog;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    @Deprecated
    public boolean isProgressDialogShowing() {
        ProgressDialog progressDialog2 = this.progressDialog;
        return progressDialog2 != null && progressDialog2.isShowing();
    }

    @Deprecated
    public void dismissProgressDialog() {
        if (isProgressDialogShowing()) {
            try {
                this.progressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                Ln.e(e);
            } catch (Throwable th) {
                this.progressDialog = null;
                throw th;
            }
            this.progressDialog = null;
        }
    }

    @Subscribe
    public void onShowProgressDialogEvent(ShowProgressDialogEvent showProgressDialogEvent) {
        if (!isProgressDialogShowing()) {
            createProgressDialog(showProgressDialogEvent.getTitle(), showProgressDialogEvent.getMessage(), showProgressDialogEvent.isCancelable(), showProgressDialogEvent.isIndeterminate());
            if (getState() != State.Destroyed) {
                ProgressDialog progressDialog2 = this.progressDialog;
                if (progressDialog2 != null) {
                    progressDialog2.show();
                }
            }
        }
    }

    @Subscribe
    public void onHideProgressDialogEvent(HideProgressDialogEvent hideProgressDialogEvent) {
        dismissProgressDialog();
    }
}
